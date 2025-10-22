package br.com.match.licit.profile.enterprise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VincularUsuarioEmpresaRequestDTO {

    private Long idEmpresa;
    private Long idUsuario;
}
