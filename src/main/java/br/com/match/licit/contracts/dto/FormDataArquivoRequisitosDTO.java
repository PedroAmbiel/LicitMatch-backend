package br.com.match.licit.contracts.dto;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Getter
@Setter
public class FormDataArquivoRequisitosDTO {

    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public FileUpload file;

    @FormParam("idEmpresaContrato")
    public Long idEmpresaContrato;

}
