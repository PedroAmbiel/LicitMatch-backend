package br.com.match.licit.contracts.services;

import br.com.match.licit.contracts.client.AiServicesClient;
import br.com.match.licit.contracts.client.dto.*;
import br.com.match.licit.contracts.dto.*;
import br.com.match.licit.contracts.rn.ContractRN;
import br.com.match.licit.pncp.dto.RespostaPaginadaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/contratos")
public class ContractsServices implements ContractsImplementation{

    @Inject
    ContractRN contractRN;

    @RestClient
    AiServicesClient servicesClient;

    @Override
    public Response buscarContratosPublicadosPaginados(Integer qtdRegistros, Integer paginacao, Long idEmpresa) {
        RespostaPaginadaDTO<ContratoMinimoInformacaoDTO> contratoMinimo = contractRN.buscarContratosPaginados(paginacao, qtdRegistros, idEmpresa);

        return Response.ok().entity(contratoMinimo).build();
    }

    @Override
    public Response buscarDetalhesContratoPublicado(String idPncp) {
        return Response.ok().entity(contractRN.buscarDetalhesContrato(idPncp)).build();
    }

    @Override
    public Response buscarContratosEmpresaInscrito(Long idEmpresa, String situacao) {
        RespostaPaginadaDTO<ContratoInscritoMinimoDTO> contratos = contractRN.buscarContratosInscritos(idEmpresa, situacao);
        return Response.ok().entity(contratos).build();
    }

    @Override
    public Response buscarDetalhesContratoEmpresaInscrito(String idPncp, Long idEmpresa) {
        return Response.ok().entity(contractRN.buscarDetalhesEmpresaContrato(idPncp, idEmpresa)).build();
    }

    @Override
    public Response buscarRequisitosEmpresaContratoInscritoPorArquivo(FormDataArquivoRequisitosDTO novoRequisito) throws JsonProcessingException {
        final String PROMPT = "Eu sou de uma empresa e quero participar desse edital. Qual seção indica as necessidades para participar desse edital? Me indique somente o número raiz. Desconsidere a seção FORMA E CRITÉRIOS DE SELEÇÃO DO FORNECEDOR E REGIME DE EXECUÇÃO.";

        final String MODELO_OCR_SECAO = "ocr-req";
        final String MODELO_OCR_REQ = "ocr";
        UploadFileResponseDTO arquivoprocessado = servicesClient.enviarArquivoParaProcessamento(novoRequisito.file);

        ChatPerguntarSecaoRequisitosDTO perguntaSecao = new ChatPerguntarSecaoRequisitosDTO();

        perguntaSecao.setPrompt(PROMPT);
        perguntaSecao.setIdDocumento(arquivoprocessado.getIdGerado());
        perguntaSecao.setNomeModelo(MODELO_OCR_SECAO);
        ChatPerguntarSecaoRequisitosResponseDTO responseModelo = servicesClient.perguntarQualSecaoRequisitos(perguntaSecao);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseModelo.getResposta());
        String secao = jsonNode.get("secao").asText();


        ExtrairMarkdownTextDTO markdown = new ExtrairMarkdownTextDTO();
        markdown.setFile(novoRequisito.file);
        markdown.setSecao(secao);
        ExtrairMarkdownTextResponseDTO textoExtraido = servicesClient.extrairMarkdownTextoDocumento(markdown);

        ChatPerguntarResumirRequisitosDTO perguntarRequisito = new ChatPerguntarResumirRequisitosDTO();

        System.out.println(textoExtraido.getMarkdown());

        final String PROMPT_REQUI = textoExtraido.getMarkdown() + " \nDado as informações acima, quais as conformidades legais minha empresa para que seja possivel a participação no edital?";
        perguntarRequisito.setPrompt(PROMPT_REQUI);
        perguntarRequisito.setNomeModelo(MODELO_OCR_REQ);
        perguntarRequisito.setIdDoc("");

        ChatPerguntarResumirRequisitosResponseDTO responseModeloRequisito = servicesClient.perguntarResumirRequisitos(perguntarRequisito);

        System.out.println(responseModeloRequisito.getResposta());
        JsonNode jsonNode2 = mapper.readTree(responseModeloRequisito.getResposta());

        System.out.println(jsonNode2);

        JsonNode requisitosNode = jsonNode2.get("requisitos");
        System.out.println(requisitosNode);
        List<String> requisitos = new ArrayList<>();



        for (JsonNode requisitoNode : requisitosNode) {
            System.out.println(requisitoNode);
            String requisito = requisitoNode.get("requisito").asText();
            requisitos.add(requisito);
            System.out.println(requisito);
        }
        contractRN.cadastrarRequisitosParaEmpresaContrato(novoRequisito.getIdEmpresaContrato(), requisitos);

        return Response.ok().build();
    }

    @Override
    public Response atualizarEstadoEmpresaContratoRequisito(AlterarStatusIsCompletoRequisitoDTO alteracao) {
        contractRN.atualizarConcluidoEmpresaContratoCompleto(alteracao.getIdEmpresaContratoRequisito(), alteracao.getIsCompleto());
        return Response.ok().build();
    }

    @Override
    public Response salvarNovoEmpresaContratoRequisito(SalvarNovoEmpresaContratoRequisitoDTO novoRequisito) {
        contractRN.salvarNovoEmpresaContratoRequisito(novoRequisito);

        return Response.ok().build();
    }

    @Override
    public Response removerEmpresaContratoRequisito(Long idRequisito) {
        contractRN.removerEmpresaContratoRequisito(idRequisito);
        return Response.ok().build();
    }


}
