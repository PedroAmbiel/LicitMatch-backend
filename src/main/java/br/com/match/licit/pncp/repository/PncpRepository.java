package br.com.match.licit.pncp.repository;

import br.com.match.licit.contracts.entity.*;
import br.com.match.licit.pncp.dto.PncpContratoFechadoDTO;
import br.com.match.licit.pncp.dto.PncpContratoPublicadoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PncpRepository {

    @Transactional(rollbackOn = Exception.class)
    public void salvarContratosFechadosEmLote(List<PncpContratoFechadoDTO> contratos){
        for(PncpContratoFechadoDTO cont : contratos){
            ContractClosed contratoEntity = new ContractClosed(cont);

            //-Evitando duplicidade de chave-//
            contratoEntity.orgaoEntidade = encontrarOuCriarEntityOrgan(contratoEntity.orgaoEntidade);

            contratoEntity.unidadeOrgao = encontrarOuCriarUnitOrgan(contratoEntity.unidadeOrgao);

            contratoEntity.tipoContrato = encontrarOuCriarContractType(contratoEntity.tipoContrato);

            contratoEntity.categoriaProcesso = encontrarOuCriarProcessCategory(contratoEntity.categoriaProcesso);

            contratoEntity.unidadeSubRogada = encontrarOuCriarSubrogatedUnit(contratoEntity.unidadeSubRogada);

            if(!contratoFechadoJaCadastrado(cont.numeroControlePNCP)){
                ContractClosed.persist(contratoEntity);
            }
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void salvarContratosPublicadosEmLote(List<PncpContratoPublicadoDTO> contratos){
        for(PncpContratoPublicadoDTO cont : contratos){
            ContractPublished contratoEntity = new ContractPublished(cont);

            //-Evitando duplicidade de chave-//
            contratoEntity.orgaoEntidade = encontrarOuCriarEntityOrgan(contratoEntity.orgaoEntidade);

            contratoEntity.unidadeOrgao = encontrarOuCriarUnitOrgan(contratoEntity.unidadeOrgao);

            contratoEntity.amparoLegal = encontrarOuCriarLegalProtection(contratoEntity.amparoLegal);

            if(!contratoPublicadoJaCadastrado(cont.numeroControlePncp)){
                ContractPublished.persist(contratoEntity);
                ContractPublished.flush();
            }else{
                ContractPublished.getEntityManager().merge(contratoEntity);
            }
        }
    }

    private boolean contratoFechadoJaCadastrado(String numeroControlePncp){
        ContractClosed contrato = ContractClosed.findById(numeroControlePncp);

        return contrato != null;
    }

    private boolean contratoPublicadoJaCadastrado(String numeroControlePncp){
        ContractPublished contrato = ContractPublished.findById(numeroControlePncp);

        return contrato != null;
    }

    @Transactional(rollbackOn = Exception.class)
    public UnitOrgan encontrarOuCriarUnitOrgan(UnitOrgan unitOrgan) {
        if (unitOrgan == null || unitOrgan.nomeUnidade == null || unitOrgan.nomeUnidade.isBlank()) {
            return null;
        }

        UnitOrgan entidadePersistida = UnitOrgan.findById(unitOrgan.nomeUnidade);

        if (entidadePersistida != null) {
            return entidadePersistida;
        } else {
            UnitOrgan.persist(unitOrgan);
            return unitOrgan;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public ContractType encontrarOuCriarContractType(ContractType contractType) {

        return ContractType.findById(contractType.id);
    }

    @Transactional(rollbackOn = Exception.class)
    public ProcessCategory encontrarOuCriarProcessCategory(ProcessCategory processCategory) {

        return ProcessCategory.findById(processCategory.id);
    }

    @Transactional(rollbackOn = Exception.class)
    public LegalProtection encontrarOuCriarLegalProtection(LegalProtection legalProtection) {
        if (legalProtection == null || legalProtection.nome == null || legalProtection.descricao.isBlank()) {
            return null;
        }

        LegalProtection entidadePersistida = LegalProtection.findById(legalProtection.id);

        if (entidadePersistida != null) {
            return entidadePersistida;
        } else {
            LegalProtection.persist(legalProtection);
            return legalProtection;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public EntityOrgan encontrarOuCriarEntityOrgan(EntityOrgan orgaoEntidade) {
        if (orgaoEntidade == null || orgaoEntidade.cnpj == null || orgaoEntidade.cnpj.isBlank()) {
            return null;
        }

        EntityOrgan entidadePersistida = EntityOrgan.findById(orgaoEntidade.cnpj);

        if (entidadePersistida != null) {
            return entidadePersistida;
        } else {
            EntityOrgan.persist(orgaoEntidade);
            return orgaoEntidade;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public SubrogatedUnit encontrarOuCriarSubrogatedUnit(SubrogatedUnit subrogatedUnit) {
        if (subrogatedUnit == null || subrogatedUnit.nomeUnidade == null || subrogatedUnit.nomeUnidade.isBlank()) {
            return null;
        }

        SubrogatedUnit entidadePersistida = SubrogatedUnit.findById(subrogatedUnit.nomeUnidade);

        if (entidadePersistida != null) {
            return entidadePersistida;
        } else {
            SubrogatedUnit.persist(subrogatedUnit);
            return subrogatedUnit;
        }
    }

}
