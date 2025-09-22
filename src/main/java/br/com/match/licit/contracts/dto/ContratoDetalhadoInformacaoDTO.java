package br.com.match.licit.contracts.dto;

import br.com.match.licit.contracts.entity.ContractPublished;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContratoDetalhadoInformacaoDTO {

    private String pncpIdentificador;
    private String nomeUnidade;
    private String ufNome;
    private String ufSigla;
    private String municipioNome;
    private String modalidade;
    private LocalDateTime dataInclusao;
    private String descricaoContratacao;

    //Dados Gerais
    private String modoDisputa;
    private String situacaoPncp;
    private Boolean isRegistroPreco;
    private LocalDateTime inicioPropostas;
    private LocalDateTime fimPropostas;

    private String linkEdital;


    public ContratoDetalhadoInformacaoDTO(ContractPublished contractPublished) {
        this.pncpIdentificador = contractPublished.numeroControlePncp;
        this.nomeUnidade = contractPublished.unidadeOrgao.nomeUnidade;
        this.ufNome = contractPublished.unidadeOrgao.ufNome;
        this.ufSigla = contractPublished.unidadeOrgao.ufSigla;
        this.municipioNome = contractPublished.unidadeOrgao.municipioNome;
        this.modalidade = contractPublished.modalidadeNome;
        this.dataInclusao = contractPublished.dataInclusao;
        this.descricaoContratacao = contractPublished.objetoCompra;
        this.modoDisputa = contractPublished.modoDisputaNome;
        this.situacaoPncp = contractPublished.situacaoCompraNome;
        this.isRegistroPreco = contractPublished.srp;
        this.inicioPropostas = contractPublished.dataAberturaProposta;
        this.fimPropostas = contractPublished.dataEncerramentoProposta;
        this.linkEdital = contractPublished.linkSistemaOrigem;
    }
}
