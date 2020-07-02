package com.pmrendszer.controller.api.error;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
		return buildResponseEntity(HttpStatus.NOT_FOUND, "Entity not found", "Nincs találat", getPath(request));
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		return buildResponseEntity(HttpStatus.FORBIDDEN, ex.getLocalizedMessage(), "Hozzáférés megtagadva!", getPath(request));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {

		if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
			return buildResponseEntity(HttpStatus.CONFLICT, ex.getLocalizedMessage(), "Adatbázis hiba",
					getPath(request));
		}
		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Adatbázis hiba",
				getPath(request));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {

		String message = String.format("A(z) '%s' paramétert melynek értéke '%s' nem lehet átalakítani '%s'",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

		return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), message, getPath(request));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

		StringBuilder message = new StringBuilder();
		message.append("Nem megfelelő adatok : [ ");
		for (javax.validation.ConstraintViolation error : ex.getConstraintViolations()) {
			message.append(error.getMessage()).append(", ");
		}
		String messageString = message.substring(0, message.length() - 2) + " ]";

		return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), messageString, getPath(request));
	}

	@ExceptionHandler(javax.persistence.EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex,
			WebRequest request) {
		return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "Nincs találat", getPath(request));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		StringBuilder message = new StringBuilder();
		message.append(ex.getParameterName());
		message.append(" paraméter hiányzik.");
		String path = ((ServletWebRequest) request).getRequest().getRequestURI();

		return buildResponseEntity(status, ex.getLocalizedMessage(), message.toString(), path);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		StringBuilder message = new StringBuilder();
		message.append(ex.getContentType());
		message.append(" nem támogatott.");

		return buildResponseEntity(status, ex.getLocalizedMessage(), message.toString(), getPath(request));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		StringBuilder message = new StringBuilder();
		message.append("Nem megfelelő adatok: [ ");

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			message.append(error.getDefaultMessage()).append(", ");
		}
		String messageString = message.substring(0, message.length() - 2) + " ]";

		return buildResponseEntity(status, ex.getLocalizedMessage(), messageString, getPath(request));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Rosszúl formázott JSON kérés",
				getPath(request));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Error writing JSON output";
		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Hiba a JSON írásakor",
				getPath(request));
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String message = String.format("Nincs találat %s metódussal erre az URL-re : %s", ex.getHttpMethod(),
				ex.getRequestURL());

		return buildResponseEntity(status, ex.getLocalizedMessage(), message, getPath(request));
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		StringBuilder message = new StringBuilder();
		message.append("A(z) ");
		message.append(ex.getMethod());
		message.append(" metódus nem támogatott ebben a kérésben. A támogatott metódusok: ");
		ex.getSupportedHttpMethods().forEach(t -> message.append(t + " "));

		return buildResponseEntity(status, ex.getLocalizedMessage(), message.toString(), getPath(request));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Hiba történt",
				getPath(request));
	}

	private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String error, String message, String path) {
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		body.put("error", error);
		body.put("message", message);
		body.put("path", path);

		return new ResponseEntity<>(body, status);
	}

	private String getPath(WebRequest request) {
		return ((ServletWebRequest) request).getRequest().getRequestURI();
	}
}