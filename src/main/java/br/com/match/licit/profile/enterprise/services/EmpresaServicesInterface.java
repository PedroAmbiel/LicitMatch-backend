package br.com.match.licit.profile.enterprise.services;

import br.com.match.licit.profile.enterprise.dto.NovaEmpresaResquestDTO;
import br.com.match.licit.profile.user.dto.UserAuthRequestDTO;
import br.com.match.licit.profile.user.dto.UserNewAccountRequestDTO;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

public interface EmpresaServicesInterface {

    @GET
    @Path("/listarcnaes")
    Response listarCnaes() throws RegraDeNegocioException;


    @POST
    @Path("/cadastrarnovaempresa")
    Response cadastrarNovaEmpresa(
            @NotNull NovaEmpresaResquestDTO novaEmpresa) throws RegraDeNegocioException;

    @POST
    @Path("/buscarempresacodigo")
    Response buscarEmpresaPorCodigoSenha(
            @NotNull @QueryParam("codigo") String codigo, @NotNull @QueryParam("senha") String senha) throws RegraDeNegocioException;
}







