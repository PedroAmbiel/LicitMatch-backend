package br.com.match.licit.pncp.rn;

import br.com.match.licit.pncp.client.PncpClient;
import br.com.match.licit.pncp.dto.PncpContratoFechadoDTO;
import br.com.match.licit.pncp.dto.PncpContratoPublicadoDTO;
import br.com.match.licit.pncp.dto.RespostaPaginadaDTO;
import br.com.match.licit.pncp.repository.PncpRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class PncpRN {

    @RestClient
    PncpClient contratosRestClient;

    @Inject
    PncpRepository pncpRepository;

    private static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;


    public List<PncpContratoFechadoDTO> obterContratosFechadosPorPeriodo(LocalDate dataInicial, LocalDate dataFinal, int pagina) {
        String dataInicialStr = dataInicial.format(ISO_DATE_FORMATTER).replace("-", "");
        String dataFinalStr = dataFinal.format(ISO_DATE_FORMATTER).replace("-", "");

        RespostaPaginadaDTO<PncpContratoFechadoDTO> response = contratosRestClient.buscarContratosFechados(dataInicialStr, dataFinalStr, pagina);

        if (response != null && response.getData() != null) {
            System.out.println("Total de elementos na API: " + response.getTotalRegistros());

            pncpRepository.salvarContratosFechadosEmLote(response.getData());

            return response.getData();
        }

        return Collections.emptyList();
    }

    public List<PncpContratoPublicadoDTO> obterContratosPublicadosPorPeriodo(LocalDate dataInicial, LocalDate dataFinal,
                                                                             int pagina, int codigoModalidade) {
        String dataInicialStr = dataInicial.format(ISO_DATE_FORMATTER).replace("-", "");
        String dataFinalStr = dataFinal.format(ISO_DATE_FORMATTER).replace("-", "");

        RespostaPaginadaDTO<PncpContratoPublicadoDTO> response = contratosRestClient.buscarContratosPublicados(dataInicialStr,
                dataFinalStr, pagina, codigoModalidade);

        if (response != null && response.getData() != null) {
            System.out.println("Total de elementos na API: " + response.getTotalRegistros());

            pncpRepository.salvarContratosPublicadosEmLote(response.getData());

            return response.getData();
        }

        return Collections.emptyList();
    }

}
