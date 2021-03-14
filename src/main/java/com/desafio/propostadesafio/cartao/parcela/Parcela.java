package com.desafio.propostadesafio.cartao.parcela;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.desafio.propostadesafio.cartao.Cartao;

@Entity
public class Parcela {

	@Id
	private Long id;
	private Integer quantidade;
	private BigDecimal valor;
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Parcela() {
	}

	public Parcela(Long id, Integer quantidade, BigDecimal valor) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

}
