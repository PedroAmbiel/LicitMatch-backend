package br.com.match.licit.contracts.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalvarNovoEmpresaContratoRequisitoDTO {

    private Long idEmpresaContrato;
    private String descricaoRequisito;
    private Boolean isCompleto;
    private Long idUsuarioCadastro;

}
