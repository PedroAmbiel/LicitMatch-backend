package br.com.match.licit.profile.enterprise.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cnae", schema = "licitmatch")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cnae extends PanacheEntityBase {

    @Id
    @Column(name = "codigo_cnae")
    public String codigo;

    @Column(name = "descricao")
    public String descricao;

}
