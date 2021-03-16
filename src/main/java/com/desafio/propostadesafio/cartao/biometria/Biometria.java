package com.desafio.propostadesafio.cartao.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.desafio.propostadesafio.cartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime criadoEm = LocalDateTime.now();
	private String digital;
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Biometria() {
	}

	public Biometria(String digital, Cartao cartao) {
		this.digital = digital;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public String getDigital() {
		return digital;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
