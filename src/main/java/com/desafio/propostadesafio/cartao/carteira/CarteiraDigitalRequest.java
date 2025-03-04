package com.desafio.propostadesafio.cartao.carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CarteiraDigitalRequest {

	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String carteira;

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}

}
