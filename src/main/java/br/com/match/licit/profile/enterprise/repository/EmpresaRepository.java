package br.com.match.licit.profile.enterprise.repository;

import br.com.match.licit.address.entity.Endereco;
import br.com.match.licit.profile.enterprise.entity.Cnae;
import br.com.match.licit.profile.enterprise.entity.Empresa;
import br.com.match.licit.profile.enterprise.entity.EmpresaContrato;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

@ApplicationScoped
public class EmpresaRepository {

    public List<Cnae> listarTodosCnaes(){
        return Cnae.findAll().list();
    }

    public Empresa buscarEmpresaPorCodigoConvite(String codigoAcesso, String senhaConvite){
        try{
            return Empresa.find("WHERE codigoConvite = :CODIGO AND senhaConvite = :SENHA",
                    Parameters.with("CODIGO", codigoAcesso).and("SENHA", BcryptUtil.bcryptHash(senhaConvite)))
                    .singleResult();
        }catch(NoResultException ex){
            return null;
        }
    }

    public Empresa buscarEmpresaPorCnpj(String cnpj){
        return Empresa.find("WHERE cnpj = :CNPJ", Parameters.with("CNPJ", cnpj)).singleResult();
    }

    @Transactional(rollbackOn = Exception.class)
    public void cadastrarNovaEmpresa(Empresa empresa){
        empresa.endereco = buscarOuCadastrarNovoEndereco(empresa.endereco);

        Endereco.getEntityManager().refresh(empresa.endereco);

        Empresa.persist(empresa);
        Empresa.flush();
    }

    @Transactional(rollbackOn = Exception.class)
    public Endereco buscarOuCadastrarNovoEndereco(Endereco endereco){
        if (endereco == null || endereco.cep == null || endereco.cep.isBlank()) {
            return null;
        }
        Endereco entidadePersistida;
        try{
            entidadePersistida = Endereco.find("WHERE cep = :CEP ", Parameters.with("CEP", endereco.cep)).singleResult();
        } catch (NoResultException e) {
            Endereco.persist(endereco);
            Endereco.flush();
            return endereco;
        }

        return entidadePersistida;
    }

    @Transactional(rollbackOn = Exception.class)
    public EmpresaContrato salvarEmpresaContrato(EmpresaContrato empresaContrato) throws RegraDeNegocioException {
        try{
            Empresa.persist(empresaContrato);
        }catch(ConstraintViolationException ex){
            throw new RegraDeNegocioException("Empresa já efetuou a inscrição neste documento");
        }
        return empresaContrato;
    }

    public Empresa findById(Long idEmpresa){
        return Empresa.findById(idEmpresa);
    }


}
