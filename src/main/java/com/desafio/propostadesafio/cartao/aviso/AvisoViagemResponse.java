package com.desafio.propostadesafio.cartao.aviso;

import java.time.LocalDate;

public class AvisoViagemResponse {
	
	private String validoAte;
	private String destino;
	
	public AvisoViagem toModel() {
		return new AvisoViagem(LocalDate.parse(this.validoAte), this.destino);
	}

	public String getValidoAte() {
		return validoAte;
	}

	public String getDestino() {
		return destino;
	}

	
}
