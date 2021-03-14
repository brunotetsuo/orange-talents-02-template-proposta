package com.desafio.propostadesafio.cartao.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoResponse {

	private String id;
	private Integer quantidade;
	private Number valor;
	private String dataDeCriacao;

	public Renegociacao toModel() {
		return new Renegociacao(Long.parseLong(this.id), this.quantidade, new BigDecimal((Double) this.valor),
				LocalDateTime.parse(this.dataDeCriacao));
	}

	public String getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Number getValor() {
		return valor;
	}

	public String getDataDeCriacao() {
		return dataDeCriacao;
	}

}
