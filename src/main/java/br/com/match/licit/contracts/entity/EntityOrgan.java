package br.com.match.licit.contracts.entity;

import br.com.match.licit.pncp.dto.PncpContratoFechadoDTO;
import br.com.match.licit.pncp.dto.PncpContratoPublicadoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orgao_entidade", schema = "licitmatch")
public class EntityOrgan extends PanacheEntityBase {

    @Id
    public String cnpj;

    @Column(name = "razao_social")
    public String razaoSocial;

    @Column(name = "poder_id")
    public String poderId;

    @Column(name = "esfera_id")
    public String esferaId;

    public EntityOrgan(PncpContratoFechadoDTO.OrgaoEntidadeDTO entidade){
        this.cnpj = entidade.cnpj;
        this.razaoSocial = entidade.razaoSocial;
        this.poderId = entidade.poderId;
        this.esferaId = entidade.esferaId;
    }
    public EntityOrgan(PncpContratoPublicadoDTO.OrgaoEntidadeDTO entidade){
        this.cnpj = entidade.cnpj;
        this.razaoSocial = entidade.razaoSocial;
        this.poderId = entidade.poderId;
        this.esferaId = entidade.esferaId;
    }
}