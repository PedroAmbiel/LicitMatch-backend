package br.com.match.licit.profile.enterprise.services;

import br.com.match.licit.profile.enterprise.dto.*;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

public interface EmpresaServicesInterface {

    @GET
    @Path("/listarcnaes")
    Response listarCnaes() throws RegraDeNegocioException;


    @POST
    @Path("/cadastrarnovaempresa")
    Response cadastrarNovaEmpresa(
            @NotNull NovaEmpresaResquestDTO novaEmpresa) throws RegraDeNegocioException;

    @GET
    @Path("/buscarempresacodigo")
    Response buscarEmpresaPorCodigoSenha(
            @NotNull @QueryParam("codigo") String codigo, @NotNull @QueryParam("senha") String senha) throws RegraDeNegocioException;

    @PUT
    @Path("/vincularusuarioempresa")
    Response vincularUsuarioEmpresa(
            VincularUsuarioEmpresaRequestDTO empresaUsuario) throws RegraDeNegocioException;

    @POST
    @Path("/inscricao")
    public Response efetuarInscricaoContrato(InscricaoEmpresaContratoDTO inscricao) throws RegraDeNegocioException;

    @GET
    @Path("/indicadores-dashboard")
    Response buscarIndicadoresDashboard(@QueryParam("idEmpresa") Long idEmpresa);

    @GET
    @Path("/perfil-empresa")
    Response buscarDadosPerfilEmpresa(@QueryParam("idEmpresa") Long idEmpresa);

    @PUT
    @Path("/atualizar-palavra-chave")
    Response atualizarPalavraChaveEmpresa(NovaPalavraChaveRequestDTO palavraChaveRequestDTO) throws RegraDeNegocioException;

    @PUT
    @Path("/atualizar-estados-atuacao")
    Response atualizarEstadosAtuacaoEmpresa(NovoEstadoAtuacaoRequestDTO novoEstadoAtuacaoRequestDTO) throws RegraDeNegocioException;
}







