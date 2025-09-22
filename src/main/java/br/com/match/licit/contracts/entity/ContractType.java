package br.com.match.licit.contracts.entity;

import br.com.match.licit.pncp.dto.PncpContratoFechadoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tipo_contrato", schema = "licitmatch")
public class ContractType extends PanacheEntity {

    public String nome;

    public ContractType(PncpContratoFechadoDTO.TipoContratoDTO tipoContratoDTO){
        this.id = tipoContratoDTO.id.longValue();
        this.nome = tipoContratoDTO.nome;
    }


}
