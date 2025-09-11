package br.com.match.licit.profile.user.dto;

import br.com.match.licit.profile.user.entity.Usuario;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RegisterForReflection
public class UserDTO {

    //Dados Pessoa
    private String nomePessoa;
    private LocalDate dataNascimento;

    //Dados Usuario
    private Long idUsuario;
    private String email;
    private boolean ativo;
    private Long idEmpresa;
    private String nomeEmpresa;

    public UserDTO(Usuario usuario){
        this.nomePessoa = usuario.getPessoa().getNome();
        this.dataNascimento = usuario.getPessoa().getDataNascimento();
        this.email = usuario.getEmail();
        this.ativo = usuario.isAtivo();
        this.idUsuario = usuario.getId();
        if(usuario.getEmpresa() != null) {
            this.idEmpresa = usuario.getEmpresa().getId();
            this.nomeEmpresa = usuario.getEmpresa().getRazaoSocial();
        }
    }

}
