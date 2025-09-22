package br.com.match.licit.contracts.repository;

import br.com.match.licit.contracts.dto.ContratoDetalhadoInformacaoDTO;
import br.com.match.licit.contracts.entity.ContractClosed;
import br.com.match.licit.contracts.entity.ContractPublished;
import jakarta.enterprise.context.ApplicationScoped;

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
        if (pagina < 1) { pagina = 1; }
        if (tamanhoPagina < 1) { tamanhoPagina = 20; }

        int pageIndex = pagina - 1;

        return ContractPublished.findAll().page(pageIndex, tamanhoPagina).list();
    }

    public ContractPublished buscarContratoPublicado(String idPncp) {
        return ContractPublished.findById(idPncp);
    }

}
