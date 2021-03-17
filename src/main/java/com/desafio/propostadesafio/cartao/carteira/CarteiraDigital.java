package com.desafio.propostadesafio.cartao.carteira;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.desafio.propostadesafio.cartao.Cartao;

@Entity
public class CarteiraDigital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private LocalDateTime associadaEm;
	private String carteira;
	@ManyToOne
	private Cartao cartao;

	@SuppressWarnings("unused")
	@Deprecated
	private CarteiraDigital() {
	}

	public CarteiraDigital(String email, String carteira, Cartao cartao) {
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
		this.associadaEm = LocalDateTime.now();
		
		cartao.adicionaCarteiraDigital(this);
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getAssociadaEm() {
		return associadaEm;
	}

	public String getCarteira() {
		return carteira;
	}

}
