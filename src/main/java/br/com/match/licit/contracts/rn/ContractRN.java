package br.com.match.licit.contracts.rn;

import br.com.match.licit.contracts.dto.ContratoDetalhadoInformacaoDTO;
import br.com.match.licit.contracts.dto.ContratoInscritoDetalheInformacaoDTO;
import br.com.match.licit.contracts.dto.ContratoInscritoMinimoDTO;
import br.com.match.licit.contracts.dto.ContratoMinimoInformacaoDTO;
import br.com.match.licit.contracts.entity.ContractClosed;
import br.com.match.licit.contracts.entity.ContractPublished;
import br.com.match.licit.contracts.repository.ContractRepository;
import br.com.match.licit.pncp.dto.RespostaPaginadaDTO;
import br.com.match.licit.profile.enterprise.entity.Empresa;
import br.com.match.licit.profile.enterprise.entity.EmpresaContrato;
import br.com.match.licit.profile.enterprise.entity.EmpresaContratoRequisito;
import br.com.match.licit.profile.enterprise.repository.EmpresaRepository;
import br.com.match.licit.profile.user.entity.Usuario;
import br.com.match.licit.profile.user.rn.UsuarioRN;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ContractRN {

    @Inject
    ContractRepository contractRepository;
    @Inject
    EmpresaRepository empresaRepository;
    @Inject
    UsuarioRN usuarioRN;

    public RespostaPaginadaDTO<ContratoMinimoInformacaoDTO> buscarContratosPaginados(int pagina, int qtdRegistros, Long idEmpresa){
       List<ContractPublished> contratos =  contractRepository.buscarPaginado(pagina, qtdRegistros, idEmpresa);
       List<ContratoMinimoInformacaoDTO> contratoMinimoInformacaoDTOS = new ArrayList<>();
       for(ContractPublished contrato : contratos){
           ContratoMinimoInformacaoDTO novoContrato = new ContratoMinimoInformacaoDTO(contrato);
           contratoMinimoInformacaoDTOS.add(novoContrato);
       }
       RespostaPaginadaDTO<ContratoMinimoInformacaoDTO> respostaPaginadaDTO = new RespostaPaginadaDTO<>();

       respostaPaginadaDTO.setData(contratoMinimoInformacaoDTOS);
       respostaPaginadaDTO.setNumeroPagina(pagina);

        respostaPaginadaDTO.setEmpty(true);

       if(!contratoMinimoInformacaoDTOS.isEmpty()){
           respostaPaginadaDTO.setEmpty(false);
           respostaPaginadaDTO.setTotalRegistros(contractRepository.buscarTotalContratosPublicados(idEmpresa));
       }
       return respostaPaginadaDTO;
    }

    public ContratoDetalhadoInformacaoDTO buscarDetalhesContrato(String idPncp){
        ContractPublished contractPublished = contractRepository.buscarContratoPublicado(idPncp);

        if(contractPublished != null) {
            return new ContratoDetalhadoInformacaoDTO(contractPublished);
        }else{
            return null;
        }
    }

    public ContratoInscritoDetalheInformacaoDTO buscarDetalhesEmpresaContrato(String idPncp, Long idEmpresa){
        ContractPublished contractPublished = contractRepository.buscarContratoPublicado(idPncp);
        EmpresaContrato empresaContrato = contractRepository.buscarEmpresaContratoPorPncpControleIdEmpresa(idEmpresa, idPncp);
        List<EmpresaContratoRequisito> requisitos = contractRepository.buscarTodosRequisitosEmpresaContrato(empresaContrato);
        if(contractPublished != null && empresaContrato != null) {
            return new ContratoInscritoDetalheInformacaoDTO(contractPublished, requisitos, empresaContrato.getId());
        }else{
            return null;
        }
    }

    public ContractPublished findContractPublishedById(String idContrato){
        return ContractPublished.findById(idContrato);
    }

    public RespostaPaginadaDTO<ContratoInscritoMinimoDTO> buscarContratosInscritos(Long idEmpresa, String situacao){
        List<ContractPublished> contratos =  contractRepository.buscarContratosDaEmpresaPorSituacao(idEmpresa, situacao);
        List<ContratoInscritoMinimoDTO> contratoMinimoInformacaoDTOS = new ArrayList<>();
        for(ContractPublished contrato : contratos){
            EmpresaContrato empresaContrato = contractRepository.buscarEmpresaContratoPorPncpControleIdEmpresa(idEmpresa, contrato.numeroControlePncp);

            ContratoInscritoMinimoDTO novoContrato = new ContratoInscritoMinimoDTO(contrato, empresaContrato.dataInscricao, empresaContrato.situacao);
            contratoMinimoInformacaoDTOS.add(novoContrato);
        }
        RespostaPaginadaDTO<ContratoInscritoMinimoDTO> respostaPaginadaDTO = new RespostaPaginadaDTO<>();

        respostaPaginadaDTO.setData(contratoMinimoInformacaoDTOS);
        respostaPaginadaDTO.setNumeroPagina(0);

        respostaPaginadaDTO.setEmpty(true);

        if(!contratoMinimoInformacaoDTOS.isEmpty()){
            respostaPaginadaDTO.setEmpty(false);
            respostaPaginadaDTO.setTotalRegistros(0);
        }
        return respostaPaginadaDTO;
    }

    public void cadastrarRequisitosParaEmpresaContrato(Long idEmpresaContrato, List<String> requisitos){
        if(requisitos != null && !requisitos.isEmpty()){

            EmpresaContrato empresaContrato = contractRepository.buscarEmpresaContratoPorId(idEmpresaContrato);
            Usuario usuario = usuarioRN.findById(71L);
            List<EmpresaContratoRequisito> empresaContratoRequisitos = new ArrayList<>();

            for (String req : requisitos){
                EmpresaContratoRequisito novoRequisito = new EmpresaContratoRequisito(empresaContrato, req, usuario, false);
                empresaContratoRequisitos.add(novoRequisito);
            }

            contractRepository.salvarEmpresaContratoRequisitoEmLote(empresaContratoRequisitos);
        }
    }

}
