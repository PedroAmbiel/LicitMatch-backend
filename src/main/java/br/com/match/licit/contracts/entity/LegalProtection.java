package br.com.match.licit.contracts.entity;

import br.com.match.licit.pncp.dto.PncpContratoPublicadoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "amparo_legal", schema = "licitmatch")
@AllArgsConstructor
@NoArgsConstructor
public class LegalProtection extends PanacheEntityBase {
    @Id
    public Integer id;

    @Column(nullable = false)
    public String nome;

    @Column(columnDefinition = "TEXT")
    public String descricao;

    public LegalProtection(PncpContratoPublicadoDTO.AmparoLegalDTO amparoLegalDTO){
        this.id = amparoLegalDTO.id;
        this.nome = amparoLegalDTO.nome;
        this.descricao = amparoLegalDTO.descricao;
    }
}
