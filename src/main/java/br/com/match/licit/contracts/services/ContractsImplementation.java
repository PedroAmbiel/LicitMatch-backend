package br.com.match.licit.contracts.services;

import br.com.match.licit.contracts.dto.FormDataArquivoRequisitosDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
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
}
