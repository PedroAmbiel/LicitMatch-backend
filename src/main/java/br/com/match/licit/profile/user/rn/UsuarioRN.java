package br.com.match.licit.profile.user.rn;

import br.com.match.licit.profile.enterprise.entity.Empresa;
import br.com.match.licit.profile.enterprise.repository.EmpresaRepository;
import br.com.match.licit.profile.user.dto.UserDTO;
import br.com.match.licit.profile.user.dto.UserNewAccountRequestDTO;
import br.com.match.licit.profile.user.entity.Usuario;
import br.com.match.licit.profile.user.repository.UsuarioRepository;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioRN {

    @Inject
    private UsuarioRepository usuarioRepository;

    @Inject
    private EmpresaRepository empresaRepository;

    public UserDTO validarLoginPorEmailSenha(String email, String senha) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(email);
        UserDTO usuarioValidado = null;
        boolean valido = compararSenhaLoginSenhaUsuario(senha, usuario.getSenha());

        if(valido){
            usuarioValidado = new UserDTO(usuario);
        }

        return usuarioValidado;
    }

    public UserDTO cadastrarNovoUsuarioSemEmpresa(UserNewAccountRequestDTO usuario) throws RegraDeNegocioException{
        Usuario novoUsuario = new Usuario(usuario);
        novoUsuario = usuarioRepository.cadastrarNovoUsuario(novoUsuario);
        return new UserDTO(novoUsuario);
    }


    private boolean compararSenhaLoginSenhaUsuario(String senhaInformada, String senhaUsuario){
       return BcryptUtil.matches(senhaInformada, senhaUsuario);
    }
    @Transactional
    public void vincularUsuarioEmpresa(Empresa empresa, Long idUSuario) throws RegraDeNegocioException {
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(idUSuario);
        usuario.setEmpresa(empresa);
        usuarioRepository.cadastrarNovoUsuario(usuario);
    }

}
