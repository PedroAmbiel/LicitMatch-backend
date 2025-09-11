package br.com.match.licit.utils.exception.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		exception.printStackTrace();
		/*List<ExceptionResponse> exceptions = List.of(new ExceptionResponse(exception));*/

		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(new ExceptionResponse(exception))
				.build();
	}

}