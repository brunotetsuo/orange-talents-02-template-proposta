package com.desafio.propostadesafio.cartao.bloqueio;

import javax.validation.constraints.NotBlank;

public class BloqueioRequest {

	@NotBlank
	private String sistemaResponsavel;

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public void setSistemaResponsavel(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

}
