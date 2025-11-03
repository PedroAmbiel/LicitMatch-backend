package br.com.match.licit.profile.enterprise.dto;

import br.com.match.licit.address.entity.Endereco;
import br.com.match.licit.profile.enterprise.entity.Empresa;
import br.com.match.licit.profile.enterprise.entity.PerfilAtividadeEmpresa;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PerfilEmpresaDTO {

    private String razaoSocial;
    private String cnpj;
    private LocalDateTime dataCadastro;
    private String situacaoCadastral;
    private EnderecoDTO endereco;
    private String naturezaJuridica;
    private String porte;
    private String codigoConvite;
    private PerfilAtividadeDTO perfilAtividade;

    public PerfilEmpresaDTO(Empresa empresa){
        this.razaoSocial = empresa.getRazaoSocial();
        this.cnpj = empresa.getCnpj();
        this.dataCadastro = empresa.getDataCadastro();
        this.situacaoCadastral = empresa.getSituacaoCadastral();

        this.endereco = new EnderecoDTO(empresa.getEndereco());

        this.naturezaJuridica = empresa.getNaturezaJuridica();
        this.porte = empresa.getPorte();
        this.codigoConvite = empresa.getCodigoConvite();

        this.perfilAtividade = new PerfilAtividadeDTO(empresa.getPerfilAtividadeEmpresa());
    }

    @Getter
    @Setter
    public class EnderecoDTO {
        private String logradouro;
        private String cidade;
        private String estado;
        private String cep;

        public EnderecoDTO(Endereco endereco){
            this.logradouro = endereco.getLogradouro();
            this.cidade = endereco.getCidade();
            this.estado = endereco.getEstado();
            this.cep = endereco.getCep();
        }
    }

    @Getter
    @Setter
    public class PerfilAtividadeDTO{

        private String ramoAtividade;
        private String descricaoAtividade;
        private String cnaePrincipal;
        private List<String> cnaesSecundarios;
        private List<String> palavrasChave;
        private List<Long> idsEstadoAtuacao;
        private LocalDateTime dataUltimaAtualizacao;

        public PerfilAtividadeDTO(PerfilAtividadeEmpresa perfil){

            this.ramoAtividade = perfil.getRamo_atividade();
            this.descricaoAtividade = perfil.getDescricao_atividades();
            this.cnaePrincipal = perfil.getCnae_principal().getCodigo();
            this.cnaesSecundarios = perfil.getCodigos_cnae();
            this.palavrasChave = perfil.getPalavras_chave();
            this.idsEstadoAtuacao = perfil.getFk_estado_atuacao();
            this.dataUltimaAtualizacao = perfil.getData_atualizacao();
        }
    }
}
