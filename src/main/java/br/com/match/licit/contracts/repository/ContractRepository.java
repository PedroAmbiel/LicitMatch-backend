package br.com.match.licit.contracts.repository;

import br.com.match.licit.contracts.dto.ContratoDetalhadoInformacaoDTO;
import br.com.match.licit.contracts.entity.ContractClosed;
import br.com.match.licit.contracts.entity.ContractPublished;
import br.com.match.licit.profile.enterprise.entity.EmpresaContrato;
import br.com.match.licit.profile.enterprise.entity.EmpresaContratoRequisito;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

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
    public List<ContractPublished> buscarPaginado(int pagina, int tamanhoPagina, Long idEmpresa) {
        if (pagina < 1) { pagina = 0; }
        if (tamanhoPagina < 10) { tamanhoPagina = 10; }

        String sql = " select * from licitmatch.contrato_publicacao cp " +
                " WHERE NOT EXISTS " +
                " (SELECT 1 FROM licitmatch.empresa_contrato ec WHERE cp.numero_controle_pncp = ec.fk_contrato_pncp " +
                " AND ec.fk_empresa = :idEmpresa) ORDER BY cp.data_publicacao_pncp DESC ";

        List<ContractPublished> results = Panache.getEntityManager()
                .createNativeQuery(sql, ContractPublished.class)
                .setParameter("idEmpresa", idEmpresa)
                .setFirstResult(pagina * tamanhoPagina)
                .setMaxResults(tamanhoPagina)
                .getResultList();

        return results;
    }

    public Long buscarTotalContratosPublicados(Long idEmpresa){
        String countSql = "SELECT COUNT(*) FROM licitmatch.contrato_publicacao cp " +
                "WHERE NOT EXISTS (" +
                "  SELECT 1 FROM licitmatch.empresa_contrato ec " +
                "  WHERE cp.numero_controle_pncp = ec.fk_contrato_pncp " +
                "  AND ec.fk_empresa = :idEmpresa" +
                ")";

        Long total = ((Number) Panache.getEntityManager()
                .createNativeQuery(countSql)
                .setParameter("idEmpresa", idEmpresa)
                .getSingleResult()).longValue();

        return total;
    }

    public ContractPublished buscarContratoPublicado(String idPncp) {
        return ContractPublished.findById(idPncp);
    }

    public List<ContractPublished> buscarContratosDaEmpresaPorSituacao(Long idEmpresa, String situacao){
        String sql = " select * from licitmatch.contrato_publicacao cp " +
                " WHERE EXISTS " +
                " (SELECT 1 FROM licitmatch.empresa_contrato ec WHERE cp.numero_controle_pncp = ec.fk_contrato_pncp " +
                " AND ec.fk_empresa = :IDEMPRESA AND ec.situacao = :SITUACAO)" +
                " ORDER BY cp.data_publicacao_pncp DESC ";

        List<ContractPublished> results = Panache.getEntityManager()
                .createNativeQuery(sql, ContractPublished.class)
                .setParameter("IDEMPRESA", idEmpresa)
                .setParameter("SITUACAO", situacao)
                .getResultList();

        return results;
    }

    public EmpresaContrato buscarEmpresaContratoPorPncpControleIdEmpresa(Long idEmpresa, String idPncp){
        return EmpresaContrato.find("empresa.id = :IDEMPRESA and contrato.numeroControlePncp = :CONTROLEPNCP",
                Parameters.with("IDEMPRESA", idEmpresa).and("CONTROLEPNCP", idPncp)).singleResult();
    }

    public EmpresaContrato buscarEmpresaContratoPorId(Long idEmpresaContrato){
        return EmpresaContrato.findById(idEmpresaContrato);
    }

    public List<EmpresaContratoRequisito> buscarTodosRequisitosEmpresaContrato(EmpresaContrato empresaContrato){
        return EmpresaContratoRequisito.find("empresaContrato = :EMPRESACONTRATO", Sort.ascending("isCompleto"),
                Parameters.with("EMPRESACONTRATO", empresaContrato)).list();
    }

    public EmpresaContratoRequisito buscarEmpresaContratoRequisitoPorId(Long idEmpresaContratoRequisito){
        return EmpresaContratoRequisito.findById(idEmpresaContratoRequisito);
    }

    @Transactional(rollbackOn = Exception.class)
    public void salvarEmpresaContratoRequisito(EmpresaContratoRequisito empresaContratoRequisito){
        if(empresaContratoRequisito.getId() != null)
            EmpresaContratoRequisito.getEntityManager().merge(empresaContratoRequisito);
        else
            EmpresaContratoRequisito.persist(empresaContratoRequisito);
    }

    @Transactional(rollbackOn = Exception.class)
    public void salvarEmpresaContratoRequisitoEmLote(List<EmpresaContratoRequisito> empresaContratoRequisitos){
        EmpresaContratoRequisito.persist(empresaContratoRequisitos);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removerEmpresaContratoRequisito(EmpresaContratoRequisito empresaContratoRequisito){
        empresaContratoRequisito.delete();
    }

    public List<ContractPublished> buscarTodosContratosExcetoInscritos(Long idEmpresa){
        String sql = "select * from licitmatch.contrato_publicacao cp" +
                "   WHERE NOT EXISTS " +
                "   (SELECT 1 FROM licitmatch.empresa_contrato ec WHERE cp.numero_controle_pncp = ec.fk_contrato_pncp " +
                "   AND ec.fk_empresa = :idEmpresa) ORDER BY cp.data_publicacao_pncp DESC ";

        List<ContractPublished> results = Panache.getEntityManager()
                .createNativeQuery(sql, ContractPublished.class)
                .setParameter("idEmpresa", idEmpresa)
                .getResultList();

        return results;
    }

}
