package br.com.match.licit.contracts.entity;

import br.com.match.licit.pncp.dto.PncpContratoFechadoDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unidade_sub_rogada", schema = "licitmatch")
@AllArgsConstructor
@NoArgsConstructor
public class SubrogatedUnit extends PanacheEntityBase {

    @Column(name = "codigo_unidade")
    public String codigoUnidade;

    @Column(name = "codigo_ibge")
    public String codigoIbge;

    @Id
    @Column(name = "nome_unidade")
    public String nomeUnidade;

    @Column(name = "municipio_nome")
    public String municipioNome;

    @Column(name = "uf_sigla")
    public String ufSigla;

    @Column(name = "uf_nome")
    public String ufNome;

    public SubrogatedUnit(PncpContratoFechadoDTO.UnidadeOrgaoDTO orgao){
        this.codigoIbge = orgao.codigoIbge;
        this.codigoUnidade = orgao.codigoUnidade;
        this.municipioNome = orgao.municipioNome;
        this.ufSigla = orgao.ufSigla;
        this.ufNome = orgao.ufNome;
        this.nomeUnidade = orgao.nomeUnidade;
    }
}
