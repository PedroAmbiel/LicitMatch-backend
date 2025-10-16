package br.com.match.licit.contracts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlterarStatusIsCompletoRequisitoDTO {

    private Long idEmpresaContratoRequisito;
    private Boolean isCompleto;

}
