package com.desafio.propostadesafio.cartao.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.desafio.propostadesafio.cartao.Cartao;

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
	private String userAgent;

	@Deprecated
	public Bloqueio() {
	}

	public Bloqueio(String sistemaResponsavel, Cartao cartao, String ipCliente, String userAgent) {
		this.bloqueadoEm = LocalDateTime.now();
		this.sistemaResponsavel = sistemaResponsavel;
		this.ativo = true;
		this.cartao = cartao;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
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

	public String getUserAgent() {
		return userAgent;
	}

}
