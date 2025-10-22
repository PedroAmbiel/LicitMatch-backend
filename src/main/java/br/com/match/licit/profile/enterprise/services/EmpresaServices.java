package br.com.match.licit.profile.enterprise.services;

import br.com.match.licit.profile.enterprise.dto.*;
import br.com.match.licit.profile.enterprise.entity.Cnae;
import br.com.match.licit.profile.enterprise.entity.Empresa;
import br.com.match.licit.profile.enterprise.entity.EmpresaContrato;
import br.com.match.licit.profile.enterprise.rn.EmpresaRN;
import br.com.match.licit.utils.exception.RegraDeNegocioException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/empresa")
public class EmpresaServices implements EmpresaServicesInterface {

    @Inject
    EmpresaRN empresaRN;

    @Override
    public Response listarCnaes() throws RegraDeNegocioException {
        List<Cnae> cnaesDisponiveis = empresaRN.listarTodosOsCnaesDisponiveis();
        return Response.ok().entity(cnaesDisponiveis).build();
    }

    @Override
    public Response cadastrarNovaEmpresa(NovaEmpresaResquestDTO novaEmpresa) throws RegraDeNegocioException {
        Empresa empresa = empresaRN.cadastrarNovaEmpresa(novaEmpresa);
        NovaEmpresaResponseDTO novaEmpresaResponseDTO = new NovaEmpresaResponseDTO(empresa.getId(), empresa.getRazaoSocial());
        return Response.ok().entity(novaEmpresaResponseDTO).build();
    }

    @Override
    public Response buscarEmpresaPorCodigoSenha(String codigo, String senha) throws RegraDeNegocioException {
        Empresa empresa = empresaRN.buscarEmpresaPorCodigoSenha(codigo, senha);
        if(empresa == null){
            return Response.ok().entity(null).build();
        }
        EmpresaResponseDTO empresaResponseDTO = new EmpresaResponseDTO(empresa);
        return Response.ok().entity(empresaResponseDTO).build();
    }

    @Override
    public Response vincularUsuarioEmpresa(VincularUsuarioEmpresaRequestDTO empresaUsuario) throws RegraDeNegocioException {
        empresaRN.vincularEmpresaUsuario(empresaUsuario.getIdUsuario(), empresaUsuario.getIdEmpresa());

        return Response.ok().build();
    }

    @Override
    public Response efetuarInscricaoContrato(InscricaoEmpresaContratoDTO inscricao) throws RegraDeNegocioException {
        EmpresaContrato empresaContrato = empresaRN.efetuarInscricaoEmpresaContrato(inscricao);
        return Response.ok().build();
    }
}
