package br.com.match.licit.profile.enterprise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NovaEmpresaResquestDTO {

    private Long idUsuarioCriou;
    private String cnpj;
    private String razaoSocial;
    private String naturezaJuridica;
    private String porte;
    private String situacaoCadastral = "Ativa";
    private String codigoConvite;
    private String senhaConvite;
    private EnderecoDTO endereco;
    private PerfilEmpresaDTO perfilEmpresa;


    @Getter
    @Setter
    public class EnderecoDTO {
        private String logradouro;
        private String cidade;
        private String estado;
        private String cep;
    }

    @Getter
    @Setter
    public class PerfilEmpresaDTO {
        private String ramoAtividade;
        private String descricaoAtividades;
        private String cnaePrincipal;
        private List<String> codigosCnae;
        private List<String> palavrasChave;
        private List<Long> fkEstadoAtuacao;

    }

}
