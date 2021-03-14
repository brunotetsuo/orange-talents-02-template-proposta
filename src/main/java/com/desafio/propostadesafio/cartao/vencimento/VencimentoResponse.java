package com.desafio.propostadesafio.cartao.vencimento;

import java.time.LocalDateTime;

public class VencimentoResponse {

	private String id;
	private Integer dia;
	private String dataDeCriacao;

	public Vencimento toModel() {
		return new Vencimento(Long.parseLong(this.id), this.dia, LocalDateTime.parse(this.dataDeCriacao));
	}

	public String getId() {
		return id;
	}

	public Integer getDia() {
		return dia;
	}

	public String getDataDeCriacao() {
		return dataDeCriacao;
	}

}
