package com.desafio.propostadesafio.cartao.aviso;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.desafio.propostadesafio.cartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate validoAte;
	private String destino;
	@ManyToOne
	private Cartao cartao;
	
	@Deprecated
	public AvisoViagem() {
	}

	public AvisoViagem(LocalDate validoAte, String destino) {

		this.validoAte = validoAte;
		this.destino = destino;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public String getDestino() {
		return destino;
	}

}
