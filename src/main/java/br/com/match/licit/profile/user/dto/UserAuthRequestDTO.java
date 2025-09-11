package br.com.match.licit.profile.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RegisterForReflection
public class UserAuthRequestDTO {

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "senha")
    private String senha;

}
