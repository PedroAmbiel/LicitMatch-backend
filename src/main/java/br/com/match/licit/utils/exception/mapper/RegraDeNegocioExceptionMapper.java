package br.com.match.licit.utils.exception.mapper;

import br.com.match.licit.utils.exception.RegraDeNegocioException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class RegraDeNegocioExceptionMapper implements ExceptionMapper<RegraDeNegocioException> {

	@Override
	public Response toResponse(RegraDeNegocioException exception) {

		List<ExceptionResponse> exceptions = List.of(new ExceptionResponse(exception, exception.getProperty(), exception.getValue()));

		return Response
				.status(Response.Status.BAD_REQUEST)
				.entity(exceptions)
				.build();
	}

}
