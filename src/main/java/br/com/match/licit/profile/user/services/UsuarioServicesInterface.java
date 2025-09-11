package br.com.match.licit.profile.user.services;

import br.com.match.licit.profile.user.dto.UserAuthRequestDTO;
import br.com.match.licit.profile.user.dto.UserNewAccountRequestDTO;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import io.smallrye.common.constraint.NotNull;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

public interface UsuarioServicesInterface {

    @POST
    @Path("/auth")
    Response autenticarUsuarioSenha(
            @NotNull UserAuthRequestDTO usuarioSenha) throws RegraDeNegocioException;


    @POST
    @Path("/signup")
    Response cadastrarNovoUsuario(
            @NotNull UserNewAccountRequestDTO usuarioSenha) throws RegraDeNegocioException;

}
