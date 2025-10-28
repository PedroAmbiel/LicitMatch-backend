package br.com.match.licit.contracts.entity;

import br.com.match.licit.pncp.dto.PncpContratoFechadoDTO;
import br.com.match.licit.pncp.dto.PncpContratoPublicadoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "contrato_publicacao", schema = "licitmatch")
@AllArgsConstructor
@NoArgsConstructor
public class ContractPublished extends PanacheEntityBase {

    @Id
    @Column(name = "numero_controle_pncp")
    public String numeroControlePncp;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fk_orgao_entidade", nullable = false)
    public EntityOrgan orgaoEntidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_amparo_legal", nullable = false)
    public LegalProtection amparoLegal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fk_unidade_orgao", referencedColumnName = "nome_unidade", nullable = false)
    public UnitOrgan unidadeOrgao;

    @Column(nullable = false)
    public Boolean srp;

    @Column(name = "ano_compra", nullable = false)
    public Short anoCompra;

    @Column(name = "sequencial_compra", nullable = false)
    public Integer sequencialCompra;

    @Column(name = "numero_compra")
    public String numeroCompra;

    public String processo;

    @Column(name = "objeto_compra", columnDefinition = "TEXT")
    public String objetoCompra;

    @Column(name = "informacao_complementar", columnDefinition = "TEXT")
    public String informacaoComplementar;

    @Column(name = "modalidade_id")
    public Integer modalidadeId;

    @Column(name = "modalidade_nome")
    public String modalidadeNome;

    @Column(name = "modo_disputa_id")
    public Integer modoDisputaId;

    @Column(name = "modo_disputa_nome")
    public String modoDisputaNome;

    @Column(name = "situacao_compra_id")
    public Integer situacaoCompraId;

    @Column(name = "situacao_compra_nome")
    public String situacaoCompraNome;

    @Column(name = "tipo_instrumento_convocatorio_codigo")
    public Integer tipoInstrumentoConvocatorioCodigo;

    @Column(name = "tipo_instrumento_convocatorio_nome")
    public String tipoInstrumentoConvocatorioNome;


    @Column(name = "data_inclusao")
    public LocalDateTime dataInclusao;

    @Column(name = "data_publicacao_pncp")
    public LocalDateTime dataPublicacaoPncp;

    @Column(name = "data_atualizacao")
    public LocalDateTime dataAtualizacao;

    @Column(name = "data_atualizacao_global")
    public LocalDateTime dataAtualizacaoGlobal;

    @Column(name = "data_abertura_proposta")
    public LocalDateTime dataAberturaProposta;

    @Column(name = "data_encerramento_proposta")
    public LocalDateTime dataEncerramentoProposta;


    @Column(name = "valor_total_estimado", precision = 15, scale = 2)
    public BigDecimal valorTotalEstimado;

    @Column(name = "valor_total_homologado", precision = 15, scale = 2)
    public BigDecimal valorTotalHomologado;


    @Column(name = "link_sistema_origem", columnDefinition = "TEXT")
    public String linkSistemaOrigem;

    @Column(name = "link_processo_eletronico", columnDefinition = "TEXT")
    public String linkProcessoEletronico;

    @Column(name = "justificativa_presencial", columnDefinition = "TEXT")
    public String justificativaPresencial;

//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(name = "fontes_orcamentarias", columnDefinition = "jsonb")
//    public String fontesOrcamentarias;

    @Column(name = "usuario_nome")
    public String usuarioNome;

    public ContractPublished(PncpContratoPublicadoDTO contrato) {
        this.numeroControlePncp = contrato.numeroControlePncp;

        if(contrato.orgaoEntidade != null)
            this.orgaoEntidade = new EntityOrgan(contrato.orgaoEntidade);

        if(contrato.unidadeOrgao != null)
            this.unidadeOrgao = new UnitOrgan(contrato.unidadeOrgao);

        if(contrato.amparoLegal != null)
            this.amparoLegal = new LegalProtection(contrato.amparoLegal);


        this.srp = contrato.srp;
        this.anoCompra = contrato.anoCompra;
        this.sequencialCompra = contrato.sequencialCompra;

        if(contrato.numeroCompra != null)
            this.numeroCompra = contrato.numeroCompra.trim();

        if(contrato.processo != null)
            this.processo = contrato.processo.trim();

        if(contrato.objetoCompra != null)
            this.objetoCompra = contrato.objetoCompra.trim();

        if(contrato.informacaoComplementar != null)
            this.informacaoComplementar = contrato.informacaoComplementar.trim();

        this.modalidadeId = contrato.modalidadeId;

        if(contrato.modalidadeNome != null)
            this.modalidadeNome = contrato.modalidadeNome.trim();

        this.modoDisputaId = contrato.modoDisputaId;

        if(contrato.modoDisputaNome != null)
            this.modoDisputaNome = contrato.modoDisputaNome.trim();

        this.situacaoCompraId = contrato.situacaoCompraId;

        if(contrato.situacaoCompraNome != null)
            this.situacaoCompraNome = contrato.situacaoCompraNome.trim();

        this.tipoInstrumentoConvocatorioCodigo = contrato.tipoInstrumentoConvocatorioCodigo;

        if(contrato.tipoInstrumentoConvocatorioNome != null)
            this.tipoInstrumentoConvocatorioNome = contrato.tipoInstrumentoConvocatorioNome.trim();
        this.dataInclusao = contrato.dataInclusao;
        this.dataPublicacaoPncp = contrato.dataPublicacaoPncp;
        this.dataAtualizacao = contrato.dataAtualizacao;
        this.dataAtualizacaoGlobal = contrato.dataAtualizacaoGlobal;
        this.dataAberturaProposta = contrato.dataAberturaProposta;
        this.dataEncerramentoProposta = contrato.dataEncerramentoProposta;
        this.valorTotalEstimado = contrato.valorTotalEstimado;
        this.valorTotalHomologado = contrato.valorTotalHomologado;
        if(contrato.linkSistemaOrigem != null)
            this.linkSistemaOrigem = contrato.linkSistemaOrigem.trim();

        if(contrato.linkProcessoEletronico != null)
            this.linkProcessoEletronico = contrato.linkProcessoEletronico.trim();

        if(contrato.justificativaPresencial != null)
            this.justificativaPresencial = contrato.justificativaPresencial.trim();
//        this.fontesOrcamentarias = contrato.fontesOrcamentarias;
        if(contrato.usuarioNome != null)
            this.usuarioNome = contrato.usuarioNome.trim();

    }


}
