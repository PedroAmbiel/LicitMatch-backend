package br.com.match.licit.utils.exception.mapper;

public class ExceptionResponse {
	private Boolean success = false;
	private String exception;
	private String message;
	private String property;
	private Object value;

	public ExceptionResponse() {	}

	public ExceptionResponse(String exception, String message) {
		this.exception = exception;
		this.message = message;
	}

	public ExceptionResponse(String exception, String message, String property, Object value) {
		this(exception, message);
		this.property = property;
		this.value = value;
	}

	public ExceptionResponse(Exception exception) {
		this(exception.getClass().getName(), exception.getMessage());
	}
	
	public ExceptionResponse(Exception exception, String property, Object value) {
		this(exception.getClass().getName(), exception.getMessage());
		this.property = property;
		this.value = value;
	}

	public Boolean getSuccess() {
		return success;
	}

	public String getException() {
		return exception;
	}

	public String getMessage() {
		return message;
	}

	public String getProperty() {
		return property;
	}

	public Object getValue() {
		return value;
	}
}
