package br.com.match.licit.pncp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespostaPaginadaDTO<T> {

    @JsonProperty("data")
    private List<T> data;

    @JsonProperty("numeroPagina")
    private int numeroPagina;

    @JsonProperty("totalPaginas")
    private int totalPaginas;

    @JsonProperty("totalRegistros")
    private long totalRegistros;

    @JsonProperty("paginasRestantes")
    private int paginasRestantes;

    @JsonProperty("empty")
    private boolean empty;

    public RespostaPaginadaDTO() {}
}
