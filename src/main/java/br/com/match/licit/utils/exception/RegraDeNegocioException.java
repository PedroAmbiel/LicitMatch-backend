package br.com.match.licit.utils.exception;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
@RegisterForReflection
public class RegraDeNegocioException extends Exception {

	private static final long serialVersionUID = -2703782615712952882L;

	private String property;
    private Object value;

	public RegraDeNegocioException(String msg) {
		super(msg);
	}

	public RegraDeNegocioException(String msg, String property, Object value) {
		super(msg);
		this.property = property;
		this.value = value;
	}

}
