package com.desafio.propostadesafio.cartao.carteira;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.desafio.propostadesafio.cartao.Cartao;

@Entity
public class CarteiraDigital {

	@Id
	private Long id;
	private String email;
	private LocalDateTime associadaEm;
	private String emissor;
	@ManyToOne
	private Cartao cartao;

	@SuppressWarnings("unused")
	@Deprecated
	private CarteiraDigital() {
	}

	public CarteiraDigital(Long id, String email, LocalDateTime associadaEm, String emissor) {
		this.id = id;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
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

	public String getEmissor() {
		return emissor;
	}

}
