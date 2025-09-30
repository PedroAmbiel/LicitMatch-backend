package br.com.match.licit.profile.enterprise.entity;

import br.com.match.licit.profile.enterprise.entity.conversor.LongListConverter;
import br.com.match.licit.profile.enterprise.entity.conversor.StringListConverter;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "perfil_atividade_empresa", schema = "licitmatch")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PerfilAtividadeEmpresa extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_atividade_empresa_id_seq")
    @SequenceGenerator(name = "perfil_atividade_empresa_id_seq", sequenceName = "licitmatch.perfil_atividade_empresa_id_seq", allocationSize = 1)
    public Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_empresa", nullable = false)
    public Empresa empresa;

    @Column(name = "ramo_atividade", nullable = false)
    public String ramo_atividade;

    @Column(name = "descricao_atividades", nullable = false, columnDefinition = "text")
    public String descricao_atividades;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnae_principal", referencedColumnName = "codigo_cnae")
    public Cnae cnae_principal;

    @Column(name = "codigos_cnae", columnDefinition = "varchar(10)[]")
    public List<String> codigos_cnae;

    @Column(name = "palavras_chave", columnDefinition = "text[]")
    public List<String> palavras_chave;

    @Column(name = "fk_estado_atuacao", columnDefinition = "bigint[]")
    public List<Long> fk_estado_atuacao;

    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    public LocalDateTime data_criacao;

    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    public LocalDateTime data_atualizacao;
}
