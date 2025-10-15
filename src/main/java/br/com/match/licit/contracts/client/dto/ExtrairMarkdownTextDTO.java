package br.com.match.licit.contracts.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Getter
@Setter
public class ExtrairMarkdownTextDTO {

    @RestForm("file")
    private FileUpload file;

    @RestForm("secao")
    private String secao;

}
