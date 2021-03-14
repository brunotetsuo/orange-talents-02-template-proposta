package com.desafio.propostadesafio.cartao.parcela;

import java.math.BigDecimal;

public class ParcelaResponse {

	private String id;
	private Integer quantidade;
	private Number valor;

	public Parcela toModel() {
		return new Parcela(Long.parseLong(this.id), this.quantidade, new BigDecimal((Double) this.valor));
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

}
