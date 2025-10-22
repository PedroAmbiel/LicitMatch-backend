package br.com.match.licit.profile.enterprise.rn;

import br.com.match.licit.contracts.entity.ContractPublished;
import br.com.match.licit.contracts.rn.ContractRN;
import br.com.match.licit.profile.enterprise.dto.InscricaoEmpresaContratoDTO;
import br.com.match.licit.profile.enterprise.dto.NovaEmpresaResquestDTO;
import br.com.match.licit.profile.enterprise.entity.Cnae;
import br.com.match.licit.profile.enterprise.entity.Empresa;
import br.com.match.licit.profile.enterprise.entity.EmpresaContrato;
import br.com.match.licit.profile.enterprise.repository.EmpresaRepository;
import br.com.match.licit.profile.user.entity.Usuario;
import br.com.match.licit.profile.user.repository.UsuarioRepository;
import br.com.match.licit.profile.user.rn.UsuarioRN;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EmpresaRN {

    @Inject
    EmpresaRepository empresaRepository;
    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioRN usuarioRN;
    @Inject
    ContractRN contractRN;

    public List<Cnae> listarTodosOsCnaesDisponiveis(){
        return empresaRepository.listarTodosCnaes();
    }

    public Empresa cadastrarNovaEmpresa(NovaEmpresaResquestDTO novaEmpresaResquestDTO) throws RegraDeNegocioException {
        Empresa novaEmpresa = new Empresa(novaEmpresaResquestDTO);

        empresaRepository.cadastrarNovaEmpresa(novaEmpresa);

        usuarioRN.vincularUsuarioEmpresa(novaEmpresa, novaEmpresaResquestDTO.getIdUsuarioCriou());

        return novaEmpresa;
    }

    public Empresa buscarEmpresaPorCodigoSenha(String codigoConvite, String senhaConvite){

        Empresa empresa = empresaRepository.buscarEmpresaPorCodigoConvite(codigoConvite, senhaConvite);

        if(BcryptUtil.matches(senhaConvite, empresa.getSenhaConvite())) {
            return empresa;
        }else {
            return null;
        }
    }

    @Transactional
    public void vincularEmpresaUsuario(Long idUsuario, Long idEmpresa) throws RegraDeNegocioException {
        Usuario usuario = usuarioRN.findById(idUsuario);
        Empresa empresa = empresaRepository.findById(idEmpresa);

        usuario.setEmpresa(empresa);

        usuarioRepository.cadastrarNovoUsuario(usuario);
    }

    public EmpresaContrato efetuarInscricaoEmpresaContrato(InscricaoEmpresaContratoDTO inscricaoEmpresaContratoDTO) throws RegraDeNegocioException {
        EmpresaContrato novaEmpresaContrato = new EmpresaContrato();

        ContractPublished contrato = contractRN.findContractPublishedById(inscricaoEmpresaContratoDTO.getIdPCNP());
        Usuario usuario = usuarioRN.findById(inscricaoEmpresaContratoDTO.getIdUsuario());
        Empresa empresa = empresaRepository.findById(inscricaoEmpresaContratoDTO.getIdEmpresa());

        novaEmpresaContrato.setUsuario(usuario);
        novaEmpresaContrato.setContrato(contrato);
        novaEmpresaContrato.setEmpresa(empresa);

        empresaRepository.salvarEmpresaContrato(novaEmpresaContrato);


        return novaEmpresaContrato;
    }

}
