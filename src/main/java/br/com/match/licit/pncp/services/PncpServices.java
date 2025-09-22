package br.com.match.licit.pncp.services;

import br.com.match.licit.pncp.dto.PncpContratoFechadoDTO;
import br.com.match.licit.pncp.dto.PncpContratoPublicadoDTO;
import br.com.match.licit.pncp.rn.PncpRN;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Path("/pncp")
public class PncpServices implements PncpServicesImplementation{

    @Inject
    PncpRN pncpRN;

    @Override
    public Response obterContratosFechadosPorPeriodo(LocalDate dataInicio, LocalDate dataFim, Integer paginacao) {

        if (dataInicio == null || dataFim == null || paginacao == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Os parâmetros 'dataInicio', 'dataFim' e 'paginacao' são obrigatórios.")
                    .build();
        }

        List<PncpContratoFechadoDTO> contratos = pncpRN.obterContratosFechadosPorPeriodo(dataInicio, dataFim, paginacao);

        return Response.ok(contratos).build();
    }

    @Override
    public Response obterContratosPublicadosPorPeriodo(LocalDate dataInicio, LocalDate dataFim, Integer paginacao, Integer codigoModalidade) {
        if (dataInicio == null || dataFim == null || paginacao == null || codigoModalidade == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Os parâmetros 'dataInicio', 'dataFim', 'paginacao' e 'codigoModalidadeContratacao' são obrigatórios.")
                    .build();
        }

        List<PncpContratoPublicadoDTO> contratos = pncpRN.obterContratosPublicadosPorPeriodo(dataInicio, dataFim, paginacao, codigoModalidade);

        return Response.ok(contratos).build();
    }
}
