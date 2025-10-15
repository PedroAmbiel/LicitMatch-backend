package br.com.match.licit.contracts.dto;

import br.com.match.licit.contracts.entity.ContractPublished;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContratoInscritoMinimoDTO {

    private String pncpIdentificador;
    private String nomeUnidade;
    private String ufNome;
    private String ufSigla;
    private String municipioNome;
    private String modalidade;
    private LocalDateTime dataInclusao;
    private String descricaoContratacao;
    private LocalDateTime dataInscricao;
    private String situacao;

    public ContratoInscritoMinimoDTO(ContractPublished contractPublished, LocalDateTime dataInscricao, String situacao) {
        this.pncpIdentificador = contractPublished.numeroControlePncp;
        this.nomeUnidade = contractPublished.unidadeOrgao.nomeUnidade;
        this.ufNome = contractPublished.unidadeOrgao.ufNome;
        this.ufSigla = contractPublished.unidadeOrgao.ufSigla;
        this.municipioNome = contractPublished.unidadeOrgao.municipioNome;
        this.modalidade = contractPublished.modalidadeNome;
        this.dataInclusao = contractPublished.dataInclusao;
        this.descricaoContratacao = contractPublished.objetoCompra;
        this.dataInscricao = dataInscricao;
        this.situacao = situacao;
    }
}
