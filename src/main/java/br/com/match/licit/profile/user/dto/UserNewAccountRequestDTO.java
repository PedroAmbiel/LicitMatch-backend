package br.com.match.licit.profile.user.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RegisterForReflection
public class UserNewAccountRequestDTO {

    //Dados Pessoa
    private String nomePessoa;
    private String cpf;
    private LocalDate dataNascimento;

    //Dados Usuario
    private String email;
    private String senha;
}
