package br.com.match.licit.contracts.rn;

import br.com.match.licit.contracts.dto.ContratoDetalhadoInformacaoDTO;
import br.com.match.licit.contracts.dto.ContratoMinimoInformacaoDTO;
import br.com.match.licit.contracts.entity.ContractClosed;
import br.com.match.licit.contracts.entity.ContractPublished;
import br.com.match.licit.contracts.repository.ContractRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ContractRN {

    @Inject
    ContractRepository contractRepository;

    public List<ContratoMinimoInformacaoDTO> buscarContratosPaginados(int pagina, int qtdRegistros){
       List<ContractPublished> contratos =  contractRepository.buscarPaginado(pagina, qtdRegistros);
       List<ContratoMinimoInformacaoDTO> contratoMinimoInformacaoDTOS = new ArrayList<>();
       for(ContractPublished contrato : contratos){
           ContratoMinimoInformacaoDTO novoContrato = new ContratoMinimoInformacaoDTO(contrato);

           contratoMinimoInformacaoDTOS.add(novoContrato);
       }

       return contratoMinimoInformacaoDTOS;
    }

    public ContratoDetalhadoInformacaoDTO buscarDetalhesContrato(String idPncp){
        ContractPublished contractPublished = contractRepository.buscarContratoPublicado(idPncp);

        if(contractPublished != null) {
            ContratoDetalhadoInformacaoDTO contratoDetalhe = new ContratoDetalhadoInformacaoDTO(contractPublished);
            return contratoDetalhe;
        }else{
            return null;
        }
    }

}
