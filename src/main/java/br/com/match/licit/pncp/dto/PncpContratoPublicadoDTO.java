package br.com.match.licit.pncp.dto;

import br.com.match.licit.contracts.entity.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PncpContratoPublicadoDTO {

    @JsonProperty("numeroControlePNCP")
    public String numeroControlePncp;
    public OrgaoEntidadeDTO orgaoEntidade;
    public AmparoLegalDTO amparoLegal;
    public UnidadeOrgaoDTO unidadeOrgao;

    public Boolean srp;
    public Short anoCompra;
    public Integer sequencialCompra;
    public String numeroCompra;
    public String processo;
    public String objetoCompra;
    public String informacaoComplementar;
    public Integer modalidadeId;
    public String modalidadeNome;
    public Integer modoDisputaId;
    public String modoDisputaNome;
    public Integer situacaoCompraId;
    public String situacaoCompraNome;
    public Integer tipoInstrumentoConvocatorioCodigo;
    public String tipoInstrumentoConvocatorioNome;

    public LocalDateTime dataInclusao;
    public LocalDateTime dataPublicacaoPncp;
    public LocalDateTime dataAtualizacao;
    public LocalDateTime dataAtualizacaoGlobal;
    public LocalDateTime dataAberturaProposta;
    public LocalDateTime dataEncerramentoProposta;

    public BigDecimal valorTotalEstimado;
    public BigDecimal valorTotalHomologado;

    public String linkSistemaOrigem;
    public String linkProcessoEletronico;
    public String justificativaPresencial;
//    public String fontesOrcamentarias;
    public String usuarioNome;

    public PncpContratoPublicadoDTO() {}

    public PncpContratoPublicadoDTO(ContractPublished entity) {
        this.numeroControlePncp = entity.numeroControlePncp;
        this.srp = entity.srp;
        this.anoCompra = entity.anoCompra;
        this.sequencialCompra = entity.sequencialCompra;
        this.numeroCompra = entity.numeroCompra;
        this.processo = entity.processo;
        this.objetoCompra = entity.objetoCompra;
        this.informacaoComplementar = entity.informacaoComplementar;
        this.modalidadeId = entity.modalidadeId;
        this.modalidadeNome = entity.modalidadeNome;
        this.modoDisputaId = entity.modoDisputaId;
        this.modoDisputaNome = entity.modoDisputaNome;
        this.situacaoCompraId = entity.situacaoCompraId;
        this.situacaoCompraNome = entity.situacaoCompraNome;
        this.tipoInstrumentoConvocatorioCodigo = entity.tipoInstrumentoConvocatorioCodigo;
        this.tipoInstrumentoConvocatorioNome = entity.tipoInstrumentoConvocatorioNome;
        this.dataInclusao = entity.dataInclusao;
        this.dataPublicacaoPncp = entity.dataPublicacaoPncp;
        this.dataAtualizacao = entity.dataAtualizacao;
        this.dataAtualizacaoGlobal = entity.dataAtualizacaoGlobal;
        this.dataAberturaProposta = entity.dataAberturaProposta;
        this.dataEncerramentoProposta = entity.dataEncerramentoProposta;
        this.valorTotalEstimado = entity.valorTotalEstimado;
        this.valorTotalHomologado = entity.valorTotalHomologado;
        this.linkSistemaOrigem = entity.linkSistemaOrigem;
        this.linkProcessoEletronico = entity.linkProcessoEletronico;
        this.justificativaPresencial = entity.justificativaPresencial;
//        this.fontesOrcamentarias = entity.fontesOrcamentarias;
        this.usuarioNome = entity.usuarioNome;

        this.orgaoEntidade = new OrgaoEntidadeDTO(entity.orgaoEntidade);
        this.amparoLegal = new AmparoLegalDTO(entity.amparoLegal);
        this.unidadeOrgao = new UnidadeOrgaoDTO(entity.unidadeOrgao);
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
    }

    @Getter
    @Setter
    public class AmparoLegalDTO {
        @JsonProperty("codigo")
        public Integer id;
        public String nome;
        public String descricao;

        public AmparoLegalDTO() {}

        public AmparoLegalDTO(LegalProtection entity) {
            if (entity != null) {
                this.id = entity.id;
                this.nome = entity.nome;
                this.descricao = entity.descricao;
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
}
