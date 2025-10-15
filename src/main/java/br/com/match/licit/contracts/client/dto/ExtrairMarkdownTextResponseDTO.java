package br.com.match.licit.contracts.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtrairMarkdownTextResponseDTO {

    @JsonProperty("secao")
    private String secao;

    @JsonProperty("markdown")
    private String markdown;

}
