package br.com.match.licit.profile.user.entity;

import br.com.match.licit.profile.enterprise.entity.Empresa;
import br.com.match.licit.profile.user.dto.UserNewAccountRequestDTO;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario", schema = "licitmatch")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class Usuario extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
    @SequenceGenerator(name = "usuario_id_seq", sequenceName = "licitmatch.usuario_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    @Username
    private String email;

    @Column(name = "senha", nullable = false)
    @Password
    private String senha;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_pessoa")
    private Pessoa pessoa;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_empresa")
    private Empresa empresa;

    @Column(name = "ativo")
    private boolean ativo = true;

    @Column(name = "data_desativado")
    private LocalDateTime dataDesativado;

    public Usuario(UserNewAccountRequestDTO usuario){
        this.email = usuario.getEmail();
        setSenha(usuario.getSenha());

        this.pessoa = new Pessoa();

        this.pessoa.setCpf(usuario.getCpf());
        this.pessoa.setNome(usuario.getNomePessoa());
        this.pessoa.setDataNascimento(usuario.getDataNascimento());
    }

    public void setSenha(String senha){
        this.senha = BcryptUtil.bcryptHash(senha);
    }
}