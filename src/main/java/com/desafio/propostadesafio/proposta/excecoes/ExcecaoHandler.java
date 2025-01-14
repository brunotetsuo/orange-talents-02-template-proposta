package com.desafio.propostadesafio.proposta.excecoes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcecaoHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidacaoExceptionDto> validarMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		ValidacaoExceptionDto ved = new ValidacaoExceptionDto();

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
		String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

		ved.setTitulo(fields);
		ved.setDetalhe(fieldMessage);
		ved.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
		ved.setHora(LocalDateTime.now(ZoneId.of("UTC")));

		return new ResponseEntity<>(ved, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ValidacaoExceptionDto> validarValidationException(ValidationException ex) {
		ValidacaoExceptionDto ved = new ValidacaoExceptionDto();

		ved.setTitulo(ex.getCause().toString());
		ved.setDetalhe(ex.getMessage());
		ved.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));
		ved.setHora(LocalDateTime.now(ZoneId.of("UTC")));

		return new ResponseEntity<>(ved, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ApiErrorException.class)
	public ResponseEntity<ErroPadronizado> apiErrorException(ApiErrorException apiErrorException) {
		Collection<String> mensagens = new ArrayList<>();
		mensagens.add(apiErrorException.getRazao());

		ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);
		return ResponseEntity.status(apiErrorException.getHttpStatus()).body(erroPadronizado);
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErroPadronizado> illegalStateException(IllegalStateException illegalStateException) {
		Collection<String> mensagens = new ArrayList<>();
		mensagens.add(illegalStateException.getMessage());

		ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erroPadronizado);
	}

}