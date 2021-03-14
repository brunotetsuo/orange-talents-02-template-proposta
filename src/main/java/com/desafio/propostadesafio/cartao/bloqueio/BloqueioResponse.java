package com.desafio.propostadesafio.cartao.bloqueio;

import java.time.LocalDateTime;

public class BloqueioResponse {

	private String id;
	private String bloqueadoEm;
	private String sistemaResponsavel;
	private boolean ativo;

	public Bloqueio toModel() {
		return new Bloqueio(Long.parseLong(this.id), LocalDateTime.parse(this.bloqueadoEm), this.sistemaResponsavel, this.ativo);
	}
	
	public String getId() {
		return id;
	}

	public String getBloqueadoEm() {
		return bloqueadoEm;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public boolean isAtivo() {
		return ativo;
	}

}
