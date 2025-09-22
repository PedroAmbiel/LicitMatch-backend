package br.com.match.licit.contracts.entity;

import br.com.match.licit.pncp.dto.PncpContratoFechadoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contratos", schema = "licitmatch")
public class ContractClosed extends PanacheEntityBase {

    @Id
    @Column(name = "numero_controle_pncp")
    public String numeroControlePncp;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fk_orgao_entidade", nullable = false)
    public EntityOrgan orgaoEntidade;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fk_unidade_orgao", nullable = false)
    public UnitOrgan unidadeOrgao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_tipo_contrato", nullable = false)
    public ContractType tipoContrato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_categoria_processo", nullable = false)
    public ProcessCategory categoriaProcesso;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "fk_unidade_sub_rogada")
    public SubrogatedUnit unidadeSubRogada;

    @Column(nullable = false)
    public String processo;

    @Column(name = "objeto_contrato", columnDefinition = "TEXT")
    public String objetoContrato;

    @Column(name = "ano_contrato", nullable = false)
    public Short anoContrato;

    @Column(name = "sequencial_contrato")
    public Integer sequencialContrato;

    @Column(name = "numero_contrato_empenho")
    public String numeroContratoEmpenho;

    @Column(name = "numero_retificacao")
    public Integer numeroRetificacao;

    @Column(name = "informacao_complementar", columnDefinition = "TEXT")
    public String informacaoComplementar;

    public Boolean receita;

    @Column(name = "data_assinatura")
    public LocalDate dataAssinatura;

    @Column(name = "data_vigencia_inicio")
    public LocalDate dataVigenciaInicio;

    @Column(name = "data_vigencia_fim")
    public LocalDate dataVigenciaFim;

    @Column(name = "data_publicacao_pncp")
    public LocalDateTime dataPublicacaoPncp;

    @Column(name = "data_atualizacao")
    public LocalDateTime dataAtualizacao;

    @Column(name = "data_atualizacao_global")
    public LocalDateTime dataAtualizacaoGlobal;

    @Column(name = "valor_inicial", precision = 15, scale = 2)
    public BigDecimal valorInicial;

    @Column(name = "valor_global", precision = 15, scale = 2)
    public BigDecimal valorGlobal;

    @Column(name = "valor_acumulado", precision = 15, scale = 2)
    public BigDecimal valorAcumulado;

    @Column(name = "valor_parcela", precision = 15, scale = 2)
    public BigDecimal valorParcela;

    @Column(name = "numero_parcelas")
    public Integer numeroParcelas;

    @Column(name = "ni_fornecedor")
    public String niFornecedor;

    @Column(name = "nome_razao_social_fornecedor")
    public String nomeRazaoSocialFornecedor;

    @Column(name = "tipo_pessoa")
    public String tipoPessoa;

    @Column(name = "codigo_pais_fornecedor")
    public String codigoPaisFornecedor;

    @Column(name = "ni_fornecedor_sub_contratado")
    public String niFornecedorSubContratado;

    @Column(name = "nome_fornecedor_sub_contratado")
    public String nomeFornecedorSubContratado;

    @Column(name = "tipo_pessoa_sub_contratada")
    public String tipoPessoaSubContratada;

    @Column(name = "numero_controle_pncp_compra")
    public String numeroControlePncpCompra;

    @Column(name = "identificador_cipi")
    public String identificadorCipi;

    @Column(name = "url_cipi", columnDefinition = "TEXT")
    public String urlCipi;

    @Column(name = "usuario_nome")
    public String usuarioNome;


    public ContractClosed(PncpContratoFechadoDTO contrato) {
        this.numeroControlePncp = contrato.numeroControlePNCP;

        if(contrato.orgaoEntidade != null)
            this.orgaoEntidade = new EntityOrgan(contrato.orgaoEntidade);

        if(contrato.unidadeOrgao != null)
            this.unidadeOrgao = new UnitOrgan(contrato.unidadeOrgao);

        if(contrato.tipoContrato != null)
            this.tipoContrato = new ContractType(contrato.tipoContrato);

        if(contrato.categoriaProcesso != null)
            this.categoriaProcesso = new ProcessCategory(contrato.categoriaProcesso);

        if(contrato.unidadeSubRogada != null)
            this.unidadeSubRogada = new SubrogatedUnit(contrato.unidadeSubRogada);

        this.processo = contrato.processo;
        this.objetoContrato = contrato.objetoContrato;
        this.anoContrato = contrato.anoContrato;
        this.sequencialContrato = contrato.sequencialContrato;
        this.numeroContratoEmpenho = contrato.numeroContratoEmpenho;
        this.numeroRetificacao = contrato.numeroRetificacao;
        this.informacaoComplementar = contrato.informacaoComplementar;
        this.receita = contrato.receita;
        this.dataAssinatura = contrato.dataAssinatura;
        this.dataVigenciaInicio = contrato.dataVigenciaInicio;
        this.dataVigenciaFim = contrato.dataVigenciaFim;
        this.dataPublicacaoPncp = contrato.dataPublicacaoPncp;
        this.dataAtualizacao = contrato.dataAtualizacao;
        this.dataAtualizacaoGlobal = contrato.dataAtualizacaoGlobal;
        this.valorInicial = contrato.valorInicial;
        this.valorGlobal = contrato.valorGlobal;
        this.valorAcumulado = contrato.valorAcumulado;
        this.valorParcela = contrato.valorParcela;
        this.numeroParcelas = contrato.numeroParcelas;
        this.niFornecedor = contrato.niFornecedor;
        this.nomeRazaoSocialFornecedor = contrato.nomeRazaoSocialFornecedor;
        this.tipoPessoa = contrato.tipoPessoa;
        this.codigoPaisFornecedor = contrato.codigoPaisFornecedor;
        this.niFornecedorSubContratado = contrato.niFornecedorSubContratado;
        this.nomeFornecedorSubContratado = contrato.nomeFornecedorSubContratado;
        this.tipoPessoaSubContratada = contrato.tipoPessoaSubContratada;
        this.numeroControlePncpCompra = contrato.numeroControlePncpCompra;
        this.identificadorCipi = contrato.identificadorCipi;
        this.urlCipi = contrato.urlCipi;
        this.usuarioNome = contrato.usuarioNome;
    }
}