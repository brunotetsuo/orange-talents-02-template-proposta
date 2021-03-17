package com.desafio.propostadesafio.cartao.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.desafio.propostadesafio.cartao.Cartao;
import com.desafio.propostadesafio.cartao.EstadoCartao;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime bloqueadoEm;
	private String sistemaResponsavel;
	private boolean ativo = false;
	@ManyToOne
	private Cartao cartao;
	private String ipCliente;

	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(String sistemaResponsavel, Cartao cartao, String ipCliente) {
		this.bloqueadoEm = LocalDateTime.now();
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = true;
		this.cartao = cartao;
		this.ipCliente = ipCliente;

		cartao.adicionaBloqueio(this);
		cartao.setEstadoCartao(EstadoCartao.BLOQUEADO);
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getBloqueadoEm() {
		return bloqueadoEm;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public String getIpCliente() {
		return ipCliente;
	}

}
