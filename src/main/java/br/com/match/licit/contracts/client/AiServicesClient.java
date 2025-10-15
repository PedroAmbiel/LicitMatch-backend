package br.com.match.licit.contracts.client;

import br.com.match.licit.contracts.client.dto.*;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@RegisterRestClient(configKey = "ai-services")
public interface AiServicesClient {

    @POST
    @Path("uploadfile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public UploadFileResponseDTO enviarArquivoParaProcessamento(@RestForm("file") FileUpload file);

    @POST
    @Path("chat")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatPerguntarSecaoRequisitosResponseDTO perguntarQualSecaoRequisitos(ChatPerguntarSecaoRequisitosDTO arquivo);

    @POST
    @Path("chat")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatPerguntarResumirRequisitosResponseDTO perguntarResumirRequisitos(ChatPerguntarResumirRequisitosDTO arquivo);

    @POST
    @Path("extract")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public ExtrairMarkdownTextResponseDTO extrairMarkdownTextoDocumento(ExtrairMarkdownTextDTO body);

}
