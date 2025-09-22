package br.com.match.licit.pncp.dto;

import br.com.match.licit.contracts.entity.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PncpContratoFechadoDTO {

    public String numeroControlePncpCompra;
    public String codigoPaisFornecedor;
    public String niFornecedorSubContratado;
    public String nomeFornecedorSubContratado;
    public Short anoContrato;
    public TipoContratoDTO tipoContrato;
    public String numeroContratoEmpenho;
    public LocalDate dataAssinatura;
    public LocalDate dataVigenciaInicio;
    public LocalDate dataVigenciaFim;
    public String niFornecedor;
    public String tipoPessoa;
    public OrgaoEntidadeDTO orgaoEntidade;
    public CategoriaProcessoDTO categoriaProcesso;
    public LocalDateTime dataPublicacaoPncp;
    public LocalDateTime dataAtualizacao;
    public Integer sequencialContrato;
    public UnidadeOrgaoDTO unidadeOrgao;
    public String informacaoComplementar;
    public String processo;
    public UnidadeOrgaoDTO unidadeSubRogada;
    public Object orgaoSubRogado;
    public String nomeRazaoSocialFornecedor;

    @JsonProperty("numeroControlePNCP")
    public String numeroControlePNCP;

    public Integer numeroRetificacao;
    public String tipoPessoaSubContratada;
    public Boolean receita;
    public Integer numeroParcelas;
    public String objetoContrato;
    public BigDecimal valorInicial;
    public BigDecimal valorParcela;
    public BigDecimal valorGlobal;
    public BigDecimal valorAcumulado;
    public LocalDateTime dataAtualizacaoGlobal;
    public String identificadorCipi;
    public String urlCipi;
    public String usuarioNome;

    public PncpContratoFechadoDTO() {
    }

    public PncpContratoFechadoDTO(ContractClosed entity) {
        this.numeroControlePncpCompra = entity.numeroControlePncpCompra;
        this.codigoPaisFornecedor = entity.codigoPaisFornecedor;
        this.niFornecedorSubContratado = entity.niFornecedorSubContratado;
        this.nomeFornecedorSubContratado = entity.nomeFornecedorSubContratado;
        this.anoContrato = entity.anoContrato;
        this.numeroContratoEmpenho = entity.numeroContratoEmpenho;
        this.dataAssinatura = entity.dataAssinatura;
        this.dataVigenciaInicio = entity.dataVigenciaInicio;
        this.dataVigenciaFim = entity.dataVigenciaFim;
        this.niFornecedor = entity.niFornecedor;
        this.tipoPessoa = entity.tipoPessoa;
        this.dataPublicacaoPncp = entity.dataPublicacaoPncp;
        this.dataAtualizacao = entity.dataAtualizacao;
        this.sequencialContrato = entity.sequencialContrato;
        this.informacaoComplementar = entity.informacaoComplementar;
        this.processo = entity.processo;
        this.nomeRazaoSocialFornecedor = entity.nomeRazaoSocialFornecedor;
        this.numeroControlePNCP = entity.numeroControlePncp;
        this.numeroRetificacao = entity.numeroRetificacao;
        this.tipoPessoaSubContratada = entity.tipoPessoaSubContratada;
        this.receita = entity.receita;
        this.numeroParcelas = entity.numeroParcelas;
        this.objetoContrato = entity.objetoContrato;
        this.valorInicial = entity.valorInicial;
        this.valorParcela = entity.valorParcela;
        this.valorGlobal = entity.valorGlobal;
        this.valorAcumulado = entity.valorAcumulado;
        this.dataAtualizacaoGlobal = entity.dataAtualizacaoGlobal;
        this.identificadorCipi = entity.identificadorCipi;
        this.urlCipi = entity.urlCipi;
        this.usuarioNome = entity.usuarioNome;

        this.tipoContrato = new TipoContratoDTO(entity.tipoContrato);
        this.orgaoEntidade = new OrgaoEntidadeDTO(entity.orgaoEntidade);
        this.categoriaProcesso = new CategoriaProcessoDTO(entity.categoriaProcesso);
        this.unidadeOrgao = new UnidadeOrgaoDTO(entity.unidadeOrgao);

        if (entity.unidadeSubRogada != null) {
            this.unidadeSubRogada = new UnidadeOrgaoDTO(entity.unidadeSubRogada);
        }
    }

    @Getter
    @Setter
    public class UnidadeOrgaoDTO {
        public String ufNome;
        public String codigoUnidade;
        public String nomeUnidade;
        public String ufSigla;
        public String municipioNome;
        public String codigoIbge;

        public UnidadeOrgaoDTO(){ }

        public UnidadeOrgaoDTO(UnitOrgan entity) {
            if (entity != null) {
                this.ufNome = entity.ufNome;
                this.codigoUnidade = entity.codigoUnidade.toString();
                this.nomeUnidade = entity.nomeUnidade;
                this.ufSigla = entity.ufSigla;
                this.municipioNome = entity.municipioNome;
                this.codigoIbge = entity.codigoIbge.toString();
            }
        }
        public UnidadeOrgaoDTO(SubrogatedUnit entity) {
            if (entity != null) {
                this.ufNome = entity.ufNome;
                this.codigoUnidade = entity.codigoUnidade.toString();
                this.nomeUnidade = entity.nomeUnidade;
                this.ufSigla = entity.ufSigla;
                this.municipioNome = entity.municipioNome;
                this.codigoIbge = entity.codigoIbge.toString();
            }
        }
    }

    @Getter
    @Setter
    public class CategoriaProcessoDTO {
        public Integer id;
        public String nome;

        public CategoriaProcessoDTO(){ }

        public CategoriaProcessoDTO(ProcessCategory entity) {
            if (entity != null) {
                this.id = entity.id.intValue();
                this.nome = entity.nome;
            }
        }
    }

    @Getter
    @Setter
    public class OrgaoEntidadeDTO {
        public String cnpj;
        public String razaoSocial;
        public String poderId;
        public String esferaId;

        public OrgaoEntidadeDTO(){ }

        public OrgaoEntidadeDTO(EntityOrgan entity) {
            if (entity != null) {
                this.cnpj = entity.cnpj;
                this.razaoSocial = entity.razaoSocial;
                this.poderId = entity.poderId;
                this.esferaId = entity.esferaId;
            }
        }
    }

    @Getter
    @Setter
    public class TipoContratoDTO {
        public Integer id;
        public String nome;

        public TipoContratoDTO(){ }

        public TipoContratoDTO(ContractType entity) {
            if (entity != null) {
                this.id = entity.id.intValue();
                this.nome = entity.nome;
            }
        }
    }

}
