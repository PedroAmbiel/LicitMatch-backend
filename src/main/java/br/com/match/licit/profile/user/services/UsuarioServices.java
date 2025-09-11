package br.com.match.licit.profile.user.services;

import br.com.match.licit.profile.user.dto.UserAuthRequestDTO;
import br.com.match.licit.profile.user.dto.UserDTO;
import br.com.match.licit.profile.user.dto.UserNewAccountRequestDTO;
import br.com.match.licit.profile.user.rn.UsuarioRN;
import br.com.match.licit.utils.exception.ErrorCodes;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UsuarioServices implements UsuarioServicesInterface {

    @Inject
    private UsuarioRN usuarioRN;

    @Override
    public Response autenticarUsuarioSenha(UserAuthRequestDTO usuario) throws RegraDeNegocioException {
        UserDTO usuarioValidado = usuarioRN.validarLoginPorEmailSenha(usuario.getEmail(), usuario.getSenha());
        Response response = null;

        if(usuarioValidado != null){
            response = Response.ok().entity(usuarioValidado).build();
        }else{
            response = Response.status(ErrorCodes.SENHA_INFORMADA_INCORRETA.getStatus())
                    .entity(ErrorCodes.SENHA_INFORMADA_INCORRETA.getDescricao()).build();
        }

        return response;
    }

    @Override
    public Response cadastrarNovoUsuario(UserNewAccountRequestDTO usuarioSenha) throws RegraDeNegocioException {
        UserDTO novoUsuario = usuarioRN.cadastrarNovoUsuarioSemEmpresa(usuarioSenha);

        return Response.ok().entity(novoUsuario).build();
    }


}
