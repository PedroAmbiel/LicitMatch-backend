package br.com.match.licit.profile.user.repository;

import br.com.match.licit.profile.user.entity.Usuario;
import br.com.match.licit.utils.exception.ErrorCodes;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;

@ApplicationScoped
public class UsuarioRepository {

    public Usuario buscarUsuarioPorEmail(String email) throws RegraDeNegocioException {
        Usuario usuario = null;

        try{
            usuario = Usuario.find("WHERE email = :email", Parameters.with("email", email)).singleResult();
        }catch(NoResultException ex){
            throw new RegraDeNegocioException(ex.getMessage(),
                    "Usuario", ErrorCodes.USUARIO_NAO_CADASTRADO);
        }

        return usuario;
    }

    public Usuario buscarUsuarioPorId(Long id){
        return Usuario.findById(id);
    }

    @Transactional(rollbackOn = {Exception.class})
    public Usuario cadastrarNovoUsuario(Usuario usuario) throws RegraDeNegocioException{
        try{
            Usuario.persist(usuario);
            Usuario.flush();
            Usuario.getEntityManager().refresh(usuario);
        }catch (ConstraintViolationException ex){
            throw new RegraDeNegocioException(ErrorCodes.CADASTRO_NAO_REALIZADO.getDescricao(),
                    ex.getConstraintName(), usuario.getEmail());
        }
        return usuario;
    }

}
