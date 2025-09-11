package br.com.match.licit.profile.enterprise.entity;

import br.com.match.licit.address.entity.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "empresa", schema = "licitmatch")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class Empresa extends PanacheEntityBase {

    /**
     * Identificador único da empresa, gerado via sequence do banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_id_seq")
    @SequenceGenerator(name = "empresa_id_seq", sequenceName = "licitmatch.empresa_id_seq", allocationSize = 1)
    public Long id;

    @Column(name = "cnpj", nullable = false, unique = true, length = 14)
    public String cnpj;

    @Column(name = "razao_social", nullable = false, length = 255)
    public String razaoSocial;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    public OffsetDateTime dataCadastro;

    @Column(name = "situacao_cadastral", length = 50)
    public String situacaoCadastral;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco", referencedColumnName = "id", nullable = false)
    public Endereco endereco;

    @Column(name = "cnae_principal", length = 7)
    public String cnaePrincipal;

    @Column(name = "cnae_secundario", length = 7)
    public String cnaeSecundario;

    @Column(name = "natureza_juridica", length = 255)
    public String naturezaJuridica;

    @Column(name = "porte", length = 50)
    public String porte;
}