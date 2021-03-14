package com.desafio.propostadesafio.cartao.renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.desafio.propostadesafio.cartao.Cartao;

@Entity
public class Renegociacao {

	@Id
	private Long id;
	private Integer quantidade;
	private BigDecimal valor;
	private LocalDateTime dataDeCriacao;
	@OneToOne
	private Cartao cartao;

	@Deprecated
	public Renegociacao() {
	}

	public Renegociacao(Long id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.quantidade = quantidade;
		this.valor = valor;
		this.dataDeCriacao = dataDeCriacao;
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

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

}
