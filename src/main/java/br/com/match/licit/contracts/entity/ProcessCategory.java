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
@Table(name = "categoria_processo", schema = "licitmatch")
public class ProcessCategory extends PanacheEntity {

    public String nome;

    public ProcessCategory(PncpContratoFechadoDTO.CategoriaProcessoDTO categoria){
        this.id = categoria.id.longValue();
        this.nome = categoria.nome;
    }
}