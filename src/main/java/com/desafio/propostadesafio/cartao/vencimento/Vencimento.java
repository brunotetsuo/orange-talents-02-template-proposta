package com.desafio.propostadesafio.cartao.vencimento;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.desafio.propostadesafio.cartao.Cartao;

@Entity
public class Vencimento {

	@Id
	private Long id;
	private Integer dia;
	private LocalDateTime dataDeCriacao;
	@OneToOne
	private Cartao cartao;

	@Deprecated
	public Vencimento() {
	}

	public Vencimento(Long id, Integer dia, LocalDateTime dataDeCriacao) {
		this.id = id;
		this.dia = dia;
		this.dataDeCriacao = dataDeCriacao;
	}

	public Long getId() {
		return id;
	}

	public Integer getDia() {
		return dia;
	}

	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}

}
