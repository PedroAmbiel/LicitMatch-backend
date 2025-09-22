package br.com.match.licit.pncp.client;

import br.com.match.licit.pncp.dto.PncpContratoFechadoDTO;
import br.com.match.licit.pncp.dto.PncpContratoPublicadoDTO;
import br.com.match.licit.pncp.dto.RespostaPaginadaDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface PncpClient {

    @GET
    @Path("/v1/contratos")
    public RespostaPaginadaDTO<PncpContratoFechadoDTO> buscarContratosFechados(@QueryParam("dataInicial") String dataInicial,
                                                                               @QueryParam("dataFinal") String dataFinal,
                                                                               @QueryParam("pagina") int pagina);

    @GET
    @Path("/v1/contratacoes/publicacao")
    public RespostaPaginadaDTO<PncpContratoPublicadoDTO> buscarContratosPublicados(@QueryParam("dataInicial") String dataInicial,
                                                                                   @QueryParam("dataFinal") String dataFinal,
                                                                                   @QueryParam("pagina") int pagina,
                                                                                   @QueryParam("codigoModalidadeContratacao") Integer codigoModalidade);

}
