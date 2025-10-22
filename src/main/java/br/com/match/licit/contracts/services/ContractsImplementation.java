package br.com.match.licit.contracts.services;

import br.com.match.licit.contracts.dto.AlterarStatusIsCompletoRequisitoDTO;
import br.com.match.licit.contracts.dto.FormDataArquivoRequisitosDTO;
import br.com.match.licit.contracts.dto.SalvarNovoEmpresaContratoRequisitoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public interface ContractsImplementation {

    @GET
    @Path("/listar_todos")
    public Response buscarContratosPublicadosPaginados(@QueryParam("qtdRegistros") Integer qtdRegistros,
                                             @QueryParam("paginacao") Integer paginacao, @QueryParam("idEmpresa") Long idEmpresa);

    @GET
    @Path("/detalhes")
    public Response buscarDetalhesContratoPublicado(@QueryParam("idPncp") String qtdRegistros);

    @GET
    @Path("/bucarinscritos")
    public Response buscarContratosEmpresaInscrito(@QueryParam("idEmpresa") Long idEmpresa, @QueryParam("situacao") String situacao);

    @GET
    @Path("/detalhesinscrito")
    public Response buscarDetalhesContratoEmpresaInscrito(@QueryParam("idPncp") String idPncp, @QueryParam("idEmpresa") Long idEmpresa);

    @POST
    @Path("/gerarrequisitosarquivo")
    public Response buscarRequisitosEmpresaContratoInscritoPorArquivo(FormDataArquivoRequisitosDTO novoRequisito) throws JsonProcessingException;

    @PUT
    @Path("/atualizarestadoconcluido")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarEstadoEmpresaContratoRequisito(AlterarStatusIsCompletoRequisitoDTO alteracao);

    @POST
    @Path("/salvarnovorequisito")
    public Response salvarNovoEmpresaContratoRequisito(SalvarNovoEmpresaContratoRequisitoDTO novoRequisito);

    @DELETE
    @Path("/removerrequisito")
    public Response removerEmpresaContratoRequisito(@QueryParam("idRequisito") Long idRequisito);

    @GET
    @Path("/buscareditaisdestaque")
    public Response buscarEditaisEmDestaque(@QueryParam("qtdRegistros") Integer qtdRegistros,
                                        @QueryParam("paginacao") Integer paginacao, @QueryParam("idEmpresa") Long idEmpresa);

}
