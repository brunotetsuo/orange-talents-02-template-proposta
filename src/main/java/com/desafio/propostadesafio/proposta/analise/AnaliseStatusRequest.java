package com.desafio.propostadesafio.proposta.analise;

import org.springframework.security.crypto.encrypt.Encryptors;

import com.desafio.propostadesafio.proposta.Proposta;

public class AnaliseStatusRequest {

	private String documento;
	private String nome;
	private String idProposta;

	public AnaliseStatusRequest(Proposta proposta) {
		this.documento = Encryptors.text("abcabc", "cbacba").decrypt(proposta.getDocumento());
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId().toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

}
