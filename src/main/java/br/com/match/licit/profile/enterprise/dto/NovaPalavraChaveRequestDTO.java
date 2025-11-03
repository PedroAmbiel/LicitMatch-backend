package br.com.match.licit.profile.enterprise.dto;

import lombok.Data;

import java.util.List;

@Data
public class NovaPalavraChaveRequestDTO {

    private Long idEmpresa;
    private List<String> novasPalavrasChave;

}
