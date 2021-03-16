package com.desafio.propostadesafio.cartao.biometria;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;

import com.desafio.propostadesafio.cartao.Cartao;
import com.desafio.propostadesafio.cartao.CartaoRepository;
import com.desafio.propostadesafio.proposta.excecoes.ApiErrorException;

public class BiometriaRequest {

	@NotBlank
	private String digital;

	public void setDigital(String digital) {
		this.digital = digital;
	}

	public Biometria toModel(Long id, CartaoRepository cartaoRepository) {
		if (!cartaoRepository.existsById(id)) {
			throw new ApiErrorException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
		}
		Cartao cartao = cartaoRepository.findById(id).get();
		String biometriaCodificada = Base64.getEncoder().encodeToString(this.digital.getBytes());
		return new Biometria(biometriaCodificada, cartao);
	}

}
