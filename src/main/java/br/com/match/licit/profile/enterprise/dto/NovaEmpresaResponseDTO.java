package br.com.match.licit.profile.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class NovaEmpresaResponseDTO {
    private Long id;
    private String razaoSocial;
}
