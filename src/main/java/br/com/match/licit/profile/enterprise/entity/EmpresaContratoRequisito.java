package br.com.match.licit.profile.enterprise.entity;

import br.com.match.licit.profile.user.entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "empresa_contrato_requisitos", schema = "licitmatch")
@Getter
@Setter
public class EmpresaContratoRequisito extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empresa_contrato_requisitos_id_seq")
    @SequenceGenerator(name = "empresa_contrato_requisitos_id_seq", sequenceName = "licitmatch.empresa_contrato_requisitos_id_seq", allocationSize = 1)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_empresa_contrato", nullable = false)
    public EmpresaContrato empresaContrato;

    @Column(nullable = false)
    public String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_usuario_cadastro", nullable = false)
    public Usuario usuarioCadastro;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    public LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "is_completo", nullable = false)
    public boolean isCompleto;

    public EmpresaContratoRequisito(){}

    public EmpresaContratoRequisito(EmpresaContrato empresaContrato, String descricao, Usuario usuarioCadastro, boolean isCompleto) {
        this.empresaContrato = empresaContrato;
        this.descricao = descricao;
        this.usuarioCadastro = usuarioCadastro;
        this.isCompleto = isCompleto;
    }
}
