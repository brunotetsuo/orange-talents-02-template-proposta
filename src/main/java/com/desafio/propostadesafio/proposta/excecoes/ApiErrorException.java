package com.desafio.propostadesafio.proposta.excecoes;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ApiErrorException extends RuntimeException {

	private final HttpStatus httpStatus;
	private final String razao;

	public ApiErrorException(HttpStatus httpStatus, String razao) {
		super(razao);
		this.httpStatus = httpStatus;
		this.razao = razao;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getRazao() {
		return razao;
	}

}
