package com.desafio.propostadesafio.cartao.carteira;

import java.time.LocalDateTime;

public class CarteiraDigitalResponse {

	private String id;
	private String email;
	private String associadaEm;
	private String emissor;

	public CarteiraDigital toModel() {
		return new CarteiraDigital(Long.parseLong(this.id), this.email, LocalDateTime.parse(this.associadaEm),
				this.emissor);
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getAssociadaEm() {
		return associadaEm;
	}

	public String getEmissor() {
		return emissor;
	}

}
