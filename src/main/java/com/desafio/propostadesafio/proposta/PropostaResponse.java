package com.desafio.propostadesafio.proposta;

public class PropostaResponse {

	private Long id;
	private String nome;
	private String email;
	private PropostaStatus status;

	public PropostaResponse(Proposta proposta) {
		this.id = proposta.getId();
		this.nome = proposta.getNome();
		this.email = proposta.getEmail();
		this.status = proposta.getStatus();
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public PropostaStatus getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}

}
