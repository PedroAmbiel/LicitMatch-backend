package br.com.match.licit.contracts.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

public interface ContractsImplementation {

    @GET
    @Path("/listar_todos")
    public Response buscarContratosPublicadosPaginados(@QueryParam("qtdRegistros") Integer qtdRegistros,
                                             @QueryParam("paginacao") Integer paginacao);

    @GET
    @Path("/detalhes")
    public Response buscarDetalhesContratoPublicado(@QueryParam("idPncp") String qtdRegistros);

}
