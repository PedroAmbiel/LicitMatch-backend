package br.com.match.licit.contracts.services;

import br.com.match.licit.contracts.dto.ContratoMinimoInformacaoDTO;
import br.com.match.licit.contracts.rn.ContractRN;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/contratos")
public class ContractsServices implements ContractsImplementation{

    @Inject
    ContractRN contractRN;

    @Override
    public Response buscarContratosPublicadosPaginados(Integer qtdRegistros, Integer paginacao) {
        List<ContratoMinimoInformacaoDTO> contratoMinimo = contractRN.buscarContratosPaginados(paginacao, qtdRegistros);

        return Response.ok().entity(contratoMinimo).build();
    }

    @Override
    public Response buscarDetalhesContratoPublicado(String idPncp) {
        return Response.ok().entity(contractRN.buscarDetalhesContrato(idPncp)).build();
    }
}
