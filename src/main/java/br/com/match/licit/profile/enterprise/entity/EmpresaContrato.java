package br.com.match.licit.profile.enterprise.entity;

import br.com.match.licit.contracts.entity.ContractPublished;
import br.com.match.licit.profile.user.entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "empresa_contrato", schema = "licitmatch")
@Getter
@Setter
public class EmpresaContrato extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_contrato_id_seq")
    @SequenceGenerator(name = "empresa_contrato_id_seq", sequenceName = "licitmatch.empresa_contrato_id_seq", allocationSize = 1)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_empresa")
    public Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_contrato_pncp")
    public ContractPublished contrato;

    @Column(name = "data_inscricao", nullable = false)
    public LocalDateTime dataInscricao = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario")
    public Usuario usuario;

    @Column(name = "situacao")
    public String situacao = "PRE_SELECAO";

    @PrePersist
    public void prePersist() {
        if (dataInscricao == null) {
            dataInscricao = LocalDateTime.now();
        }
    }
}
