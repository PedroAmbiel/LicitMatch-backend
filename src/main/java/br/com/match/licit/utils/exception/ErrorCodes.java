package br.com.match.licit.utils.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

    USUARIO_NAO_CADASTRADO("O usuário cadastrado não foi encontrado", Response.Status.NOT_FOUND),
    SENHA_INFORMADA_INCORRETA("Senha informada está incorreta", Response.Status.BAD_REQUEST),
    CADASTRO_NAO_REALIZADO("Não foi possível finalizar o cadastro de usuário", Response.Status.INTERNAL_SERVER_ERROR);

    private String descricao;
    private Response.Status status;

}
