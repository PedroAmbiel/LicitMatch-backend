package br.com.match.licit.profile.enterprise.entity;

import br.com.match.licit.address.entity.Endereco;
import br.com.match.licit.profile.enterprise.dto.NovaEmpresaResquestDTO;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.quarkus.security.jpa.Password;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    public LocalDateTime dataCadastro;

    @Column(name = "situacao_cadastral", length = 50)
    public String situacaoCadastral;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "fk_endereco", referencedColumnName = "id", nullable = false)
    public Endereco endereco;

    @Column(name = "natureza_juridica", length = 255)
    public String naturezaJuridica;

    @Column(name = "porte", length = 50)
    public String porte;

    @Password
    @Column(name = "senha_convite")
    public String senhaConvite;

    @Column(name = "codigo_convite")
    public String codigoConvite;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "empresa")
    private PerfilAtividadeEmpresa perfilAtividadeEmpresa;

    public Empresa(NovaEmpresaResquestDTO novaEmpresaResquestDTO){
        this.cnpj = novaEmpresaResquestDTO.getCnpj();
        this.razaoSocial = novaEmpresaResquestDTO.getRazaoSocial();
        this.situacaoCadastral = novaEmpresaResquestDTO.getSituacaoCadastral();
        this.naturezaJuridica = novaEmpresaResquestDTO.getNaturezaJuridica();
        this.porte = novaEmpresaResquestDTO.getPorte();
        this.codigoConvite = novaEmpresaResquestDTO.getCodigoConvite();
        this.senhaConvite = BcryptUtil.bcryptHash(novaEmpresaResquestDTO.getSenhaConvite());

        Endereco novoEndereco = new Endereco();
        novoEndereco.setCep(novaEmpresaResquestDTO.getEndereco().getCep());
        novoEndereco.setCidade(novaEmpresaResquestDTO.getEndereco().getCidade());
        novoEndereco.setEstado(novaEmpresaResquestDTO.getEndereco().getEstado());
        novoEndereco.setLogradouro(novaEmpresaResquestDTO.getEndereco().getLogradouro());

        PerfilAtividadeEmpresa novoPerfilAtividade = new PerfilAtividadeEmpresa();
        novoPerfilAtividade.setDescricao_atividades(novaEmpresaResquestDTO.getPerfilEmpresa().getDescricaoAtividades());
        novoPerfilAtividade.setCodigos_cnae(novaEmpresaResquestDTO.getPerfilEmpresa().getCodigosCnae());
        novoPerfilAtividade.setRamo_atividade(novaEmpresaResquestDTO.getPerfilEmpresa().getRamoAtividade());
        novoPerfilAtividade.setCnae_principal(Cnae.findById(novaEmpresaResquestDTO.getPerfilEmpresa().getCnaePrincipal()));
        novoPerfilAtividade.setPalavras_chave(novaEmpresaResquestDTO.getPerfilEmpresa().getPalavrasChave());
        novoPerfilAtividade.setFk_estado_atuacao(novaEmpresaResquestDTO.getPerfilEmpresa().getFkEstadoAtuacao());
        novoPerfilAtividade.setEmpresa(this);

        this.perfilAtividadeEmpresa = novoPerfilAtividade;
        this.endereco = novoEndereco;
    }
}