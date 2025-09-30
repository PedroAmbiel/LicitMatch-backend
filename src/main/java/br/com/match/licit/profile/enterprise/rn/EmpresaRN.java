package br.com.match.licit.profile.enterprise.rn;

import br.com.match.licit.address.entity.Endereco;
import br.com.match.licit.contracts.entity.EntityOrgan;
import br.com.match.licit.profile.enterprise.dto.NovaEmpresaResquestDTO;
import br.com.match.licit.profile.enterprise.entity.Cnae;
import br.com.match.licit.profile.enterprise.entity.Empresa;
import br.com.match.licit.profile.enterprise.repository.EmpresaRepository;
import br.com.match.licit.profile.user.rn.UsuarioRN;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmpresaRN {

    @Inject
    EmpresaRepository empresaRepository;

    @Inject
    UsuarioRN usuarioRN;

    public List<Cnae> listarTodosOsCnaesDisponiveis(){
        return empresaRepository.listarTodosCnaes();
    }

    public Empresa cadastrarNovaEmpresa(NovaEmpresaResquestDTO novaEmpresaResquestDTO) throws RegraDeNegocioException {
        Empresa novaEmpresa = new Empresa(novaEmpresaResquestDTO);

        novaEmpresa.endereco = empresaRepository.buscarOuCadastrarNovoEndereco(novaEmpresa.endereco);

        empresaRepository.cadastrarNovaEmpresa(novaEmpresa);

        usuarioRN.vincularUsuarioEmpresa(novaEmpresa, novaEmpresaResquestDTO.getIdUsuarioCriou());

        return novaEmpresa;
    }

    public Empresa buscarEmpresaPorCodigoSenha(String codigoConvite, String senhaConvite){

        return empresaRepository.buscarEmpresaPorCodigoConvite(codigoConvite, senhaConvite);
    }

}
