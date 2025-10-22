package br.com.match.licit.profile.enterprise.dto;

import br.com.match.licit.profile.enterprise.entity.Empresa;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpresaResponseDTO {

    private Long idEmpresa;
    private String nomeEmpresa;

    public EmpresaResponseDTO(Empresa empresa){
        this.idEmpresa = empresa.getId();
        this.nomeEmpresa = empresa.getRazaoSocial();
    }

}
