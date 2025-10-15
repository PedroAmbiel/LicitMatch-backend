package br.com.match.licit.contracts.dto;

import br.com.match.licit.contracts.entity.ContractPublished;
import br.com.match.licit.profile.enterprise.entity.EmpresaContratoRequisito;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ContratoInscritoDetalheInformacaoDTO {

    private Long idEmpresaContrato;
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

    private List<Requisitos> requisitos = new ArrayList<>();


    public ContratoInscritoDetalheInformacaoDTO(ContractPublished contractPublished, List<EmpresaContratoRequisito> requisitos, Long idEmpresaContrato) {
        this.idEmpresaContrato = idEmpresaContrato;
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
        if(requisitos != null && !requisitos.isEmpty()){
            for (EmpresaContratoRequisito req : requisitos){
                Requisitos novoRequisito = new Requisitos(req.getId(), req.usuarioCadastro.getPessoa().getNome(), req.descricao,
                        req.dataCadastro, req.isCompleto);

                this.requisitos.add(novoRequisito);
            }
        }
    }

    @Getter
    @Setter
    public class Requisitos{

        private Long idRequisito;
        private String nomeCadastrou;
        private LocalDateTime dataInclusao;
        private String descricaoRequisito;
        private Boolean isCompleto;

        public Requisitos(Long idRequisito, String nomeCadastrou, String descricaoRequisito, LocalDateTime dataInclusao, Boolean isCompleto) {
            this.idRequisito = idRequisito;
            this.nomeCadastrou = nomeCadastrou;
            this.descricaoRequisito = descricaoRequisito;
            this.dataInclusao = dataInclusao;
            this.isCompleto = isCompleto;
        }

    }

}
