package br.com.match.licit.contracts.rn;

import br.com.match.licit.contracts.dto.*;
import br.com.match.licit.contracts.entity.ContractPublished;
import br.com.match.licit.contracts.repository.ContractRepository;
import br.com.match.licit.pncp.dto.RespostaPaginadaDTO;
import br.com.match.licit.profile.enterprise.entity.EmpresaContrato;
import br.com.match.licit.profile.enterprise.entity.EmpresaContratoRequisito;
import br.com.match.licit.profile.enterprise.entity.PerfilAtividadeEmpresa;
import br.com.match.licit.profile.enterprise.repository.EmpresaRepository;
import br.com.match.licit.profile.user.entity.Usuario;
import br.com.match.licit.profile.user.rn.UsuarioRN;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import me.xdrop.fuzzywuzzy.FuzzySearch;

import static java.util.Map.entry;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ApplicationScoped
public class ContractRN {

    Map<Integer, String> estadosHashMap = Map.ofEntries(entry(1, "AC"), entry(2, "AL"), entry(3, "AP"), entry(4, "AM"), entry(5, "BA"), entry(6, "CE"), entry(7, "DF"), entry(8, "ES"), entry(9, "GO"), entry(10, "MA"), entry(11, "MT"), entry(12, "MS"), entry(13, "MG"), entry(14, "PA"), entry(15, "PB"), entry(16, "PR"), entry(17, "PE"), entry(18, "PI"), entry(19, "RJ"), entry(20, "RN"), entry(21, "RS"), entry(22, "RO"), entry(23, "RR"), entry(24, "SC"), entry(25, "SP"), entry(26, "SE"), entry(27, "TO"));
    @Inject
    ContractRepository contractRepository;
    @Inject
    EmpresaRepository empresaRepository;
    @Inject
    UsuarioRN usuarioRN;

    public RespostaPaginadaDTO<ContratoMinimoInformacaoDTO> buscarContratosPaginados(int pagina, int qtdRegistros, Long idEmpresa) {
        List<ContractPublished> contratos = contractRepository.buscarPaginado(pagina, qtdRegistros, idEmpresa);
        List<ContratoMinimoInformacaoDTO> contratoMinimoInformacaoDTOS = new ArrayList<>();
        for (ContractPublished contrato : contratos) {
            ContratoMinimoInformacaoDTO novoContrato = new ContratoMinimoInformacaoDTO(contrato);
            contratoMinimoInformacaoDTOS.add(novoContrato);
        }
        RespostaPaginadaDTO<ContratoMinimoInformacaoDTO> respostaPaginadaDTO = new RespostaPaginadaDTO<>();

        respostaPaginadaDTO.setData(contratoMinimoInformacaoDTOS);
        respostaPaginadaDTO.setNumeroPagina(pagina);

        respostaPaginadaDTO.setEmpty(true);

        if (!contratoMinimoInformacaoDTOS.isEmpty()) {
            respostaPaginadaDTO.setEmpty(false);
            respostaPaginadaDTO.setTotalRegistros(contractRepository.buscarTotalContratosPublicados(idEmpresa));
        }
        return respostaPaginadaDTO;
    }

    public ContratoDetalhadoInformacaoDTO buscarDetalhesContrato(String idPncp) {
        ContractPublished contractPublished = contractRepository.buscarContratoPublicado(idPncp);

        if (contractPublished != null) {
            return new ContratoDetalhadoInformacaoDTO(contractPublished);
        } else {
            return null;
        }
    }

    public ContratoInscritoDetalheInformacaoDTO buscarDetalhesEmpresaContrato(String idPncp, Long idEmpresa) {
        ContractPublished contractPublished = contractRepository.buscarContratoPublicado(idPncp);
        EmpresaContrato empresaContrato = contractRepository.buscarEmpresaContratoPorPncpControleIdEmpresa(idEmpresa, idPncp);
        List<EmpresaContratoRequisito> requisitos = contractRepository.buscarTodosRequisitosEmpresaContrato(empresaContrato);
        if (contractPublished != null && empresaContrato != null) {
            return new ContratoInscritoDetalheInformacaoDTO(contractPublished, requisitos, empresaContrato.getId());
        } else {
            return null;
        }
    }

    public ContractPublished findContractPublishedById(String idContrato) {
        return ContractPublished.findById(idContrato);
    }

    public RespostaPaginadaDTO<ContratoInscritoMinimoDTO> buscarContratosInscritos(Long idEmpresa, String situacao) {
        List<ContractPublished> contratos = contractRepository.buscarContratosDaEmpresaPorSituacao(idEmpresa, situacao);
        List<ContratoInscritoMinimoDTO> contratoMinimoInformacaoDTOS = new ArrayList<>();
        for (ContractPublished contrato : contratos) {
            EmpresaContrato empresaContrato = contractRepository.buscarEmpresaContratoPorPncpControleIdEmpresa(idEmpresa, contrato.numeroControlePncp);

            ContratoInscritoMinimoDTO novoContrato = new ContratoInscritoMinimoDTO(contrato, empresaContrato.dataInscricao, empresaContrato.situacao);
            contratoMinimoInformacaoDTOS.add(novoContrato);
        }
        RespostaPaginadaDTO<ContratoInscritoMinimoDTO> respostaPaginadaDTO = new RespostaPaginadaDTO<>();

        respostaPaginadaDTO.setData(contratoMinimoInformacaoDTOS);
        respostaPaginadaDTO.setNumeroPagina(0);

        respostaPaginadaDTO.setEmpty(true);

        if (!contratoMinimoInformacaoDTOS.isEmpty()) {
            respostaPaginadaDTO.setEmpty(false);
            respostaPaginadaDTO.setTotalRegistros(0);
        }
        return respostaPaginadaDTO;
    }

    public void cadastrarRequisitosParaEmpresaContrato(Long idEmpresaContrato, List<String> requisitos) {
        if (requisitos != null && !requisitos.isEmpty()) {

            EmpresaContrato empresaContrato = contractRepository.buscarEmpresaContratoPorId(idEmpresaContrato);
            Usuario usuario = usuarioRN.findById(20L);
            List<EmpresaContratoRequisito> empresaContratoRequisitos = new ArrayList<>();

            for (String req : requisitos) {
                EmpresaContratoRequisito novoRequisito = new EmpresaContratoRequisito(empresaContrato, req, usuario, false);
                empresaContratoRequisitos.add(novoRequisito);
            }

            contractRepository.salvarEmpresaContratoRequisitoEmLote(empresaContratoRequisitos);
        }
    }


    public void atualizarConcluidoEmpresaContratoCompleto(Long idEmpresaContratoRequisito, Boolean novoEstado) {
        EmpresaContratoRequisito contratoRequisito = contractRepository.buscarEmpresaContratoRequisitoPorId(idEmpresaContratoRequisito);
        contratoRequisito.setCompleto(novoEstado);
        contractRepository.salvarEmpresaContratoRequisito(contratoRequisito);
    }

    public void salvarNovoEmpresaContratoRequisito(SalvarNovoEmpresaContratoRequisitoDTO novoRequisito) {
        Usuario usuario = usuarioRN.findById(novoRequisito.getIdUsuarioCadastro());
        EmpresaContrato empresaContrato = contractRepository.buscarEmpresaContratoPorId(novoRequisito.getIdEmpresaContrato());

        EmpresaContratoRequisito novoEmpresaContratoRequisito = new EmpresaContratoRequisito(empresaContrato,
                novoRequisito.getDescricaoRequisito(), usuario, novoRequisito.getIsCompleto());

        contractRepository.salvarEmpresaContratoRequisito(novoEmpresaContratoRequisito);
    }

    public void removerEmpresaContratoRequisito(Long idRequisito) {
        EmpresaContratoRequisito contratoRequisito = contractRepository.buscarEmpresaContratoRequisitoPorId(idRequisito);
        contractRepository.removerEmpresaContratoRequisito(contratoRequisito);
    }

    public List<ContratoMinimoInformacaoDTO> buscarDestaqueEmpresa(Long idEmpresa) {
        List<ContractPublished> contratosDisponiveis = contractRepository.buscarTodosContratosExcetoInscritos(idEmpresa);
        PerfilAtividadeEmpresa perfilAtividadeEmpresa = empresaRepository.buscarPalavrasChavePerfilEmpresa(idEmpresa);
        List<String> palavrasChave = perfilAtividadeEmpresa.palavras_chave;

        List<Long> idsEstadoAtuacao = perfilAtividadeEmpresa.getFk_estado_atuacao();
        Set<String> siglasPermitidas = idsEstadoAtuacao.stream()
                .map(idLong -> estadosHashMap.get(idLong.intValue()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<ContractPublished> contratosFiltradosPorEstado = contratosDisponiveis.stream()
                .filter(contrato -> siglasPermitidas.contains(contrato.unidadeOrgao.ufSigla))
                .toList();

        Set<ContractPublished> contratosRelevantes = new HashSet<>();

        final int LIMIAR_DE_CORRESPONDENCIA = 70;

        List<String> palavrasChaveNorm = palavrasChave.stream()
                .map(this::normalizarString)
                .filter(Objects::nonNull)
                .toList();

        for (ContractPublished contrato : contratosFiltradosPorEstado) {

            String objetoCompraNorm = normalizarString(contrato.objetoCompra);
            if (objetoCompraNorm == null || objetoCompraNorm.isBlank()) {
                continue;
            }

            for (String palavraNorm : palavrasChaveNorm) {

                int ratio = FuzzySearch.tokenSetRatio(palavraNorm, objetoCompraNorm);

                if (ratio >= LIMIAR_DE_CORRESPONDENCIA) {
                    contratosRelevantes.add(contrato);

                    break;
                }
            }
        }

        List<ContratoMinimoInformacaoDTO> editaisDTO = new ArrayList<>();
        for (ContractPublished contrato : contratosRelevantes) {
            ContratoMinimoInformacaoDTO cont = new ContratoMinimoInformacaoDTO(contrato);
            editaisDTO.add(cont);
        }

        return editaisDTO;
    }

    private String normalizarString(String str) {
        Pattern DIACRITICS_PATTERN =
                Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        if (str == null) {
            return null;
        }
        String nfd = Normalizer.normalize(str.toLowerCase(), Normalizer.Form.NFD);
        return DIACRITICS_PATTERN.matcher(nfd).replaceAll("");
    }

    private record ContratoComScore(ContractPublished contrato, double score) {}

}
