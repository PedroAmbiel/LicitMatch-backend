package br.com.match.licit.contracts.repository;

import br.com.match.licit.contracts.dto.ContratoDetalhadoInformacaoDTO;
import br.com.match.licit.contracts.entity.ContractClosed;
import br.com.match.licit.contracts.entity.ContractPublished;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ContractRepository {

    /**
     * Busca uma lista paginada de contratos.
     *
     * @param pagina O número da página desejada (começando em 1).
     * @param tamanhoPagina A quantidade de registros por página.
     * @return Uma lista de entidades Contrato.
     */
    public List<ContractPublished> buscarPaginado(int pagina, int tamanhoPagina) {
        if (pagina < 1) { pagina = 0; }
        if (tamanhoPagina < 10) { tamanhoPagina = 10; }

//        int pageIndex = pagina - 1;

        return ContractPublished.findAll(Sort.by("dataPublicacaoPncp", Sort.Direction.Descending))
                .page(pagina, tamanhoPagina)
                .list();
    }

    public Long buscarTotalContratosPublicados(){
        return ContractPublished.findAll().count();
    }

    public ContractPublished buscarContratoPublicado(String idPncp) {
        return ContractPublished.findById(idPncp);
    }

}
