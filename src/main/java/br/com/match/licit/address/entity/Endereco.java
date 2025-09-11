package br.com.match.licit.address.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco", schema = "licitmatch")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Endereco extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "endereco_id_seq", sequenceName = "licitmatch.endereco_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_id_seq")
    public Long id;

    @Column(name = "logradouro", nullable = false, length = 255)
    public String logradouro;

    @Column(name = "cidade", nullable = false, length = 100)
    public String cidade;

    @Column(name = "estado", nullable = false, length = 2)
    public String estado;

    @Column(name = "cep", nullable = false, length = 8)
    public String cep;
}