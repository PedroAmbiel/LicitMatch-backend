package br.com.match.licit.pncp.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

public interface PncpServicesImplementation {

    /**
     * Busca contratos finalizados em uma API externa dentro de um período e página especificados.
     *
     * @param dataInicio A data de início da busca.
     * @param dataFim A data final da busca.
     * @param paginacao O número da página a ser retornada.
     * @return Uma lista de DTOs de Contrato.
     */
    @GET
    @Path("/buscar_contratos_fechados")
    public Response obterContratosFechadosPorPeriodo(@QueryParam("dataInicio") LocalDate dataInicio,
                                                          @QueryParam("dataFim") LocalDate dataFim,
                                                          @QueryParam("paginacao") Integer paginacao);

    /**
     * Busca  abertos em uma API externa dentro de um período e página especificados.
     *
     * @param dataInicio A data de início da busca.
     * @param dataFim A data final da busca.
     * @param paginacao O número da página a ser retornada.
     * @return Uma lista de DTOs de Contrato.
     */
    @GET
    @Path("/buscar_contratos_publicados")
    public Response obterContratosPublicadosPorPeriodo(@QueryParam("dataInicio") LocalDate dataInicio,
                                                        @QueryParam("dataFim") LocalDate dataFim,
                                                        @QueryParam("paginacao") Integer paginacao,
                                                        @QueryParam("codigoModalidadeContratacao") Integer codigoModalidade);

}
