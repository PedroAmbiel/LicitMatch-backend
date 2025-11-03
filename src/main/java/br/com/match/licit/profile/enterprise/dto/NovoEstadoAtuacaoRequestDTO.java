package br.com.match.licit.profile.enterprise.dto;

import lombok.Data;

import java.util.List;

@Data
public class NovoEstadoAtuacaoRequestDTO {

    private Long idEmpresa;
    private List<Long> idsEstados;

}
