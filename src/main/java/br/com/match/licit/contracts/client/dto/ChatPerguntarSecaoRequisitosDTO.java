package br.com.match.licit.contracts.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatPerguntarSecaoRequisitosDTO {

    @JsonProperty("model")
    private String nomeModelo;

    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("id_doc")
    private String idDocumento;

}
