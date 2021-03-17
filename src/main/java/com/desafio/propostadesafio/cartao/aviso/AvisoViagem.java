package com.desafio.propostadesafio.cartao.aviso;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private LocalDateTime criadoEm;
	private String ipCliente;
	private String agenteUsuario;

	@Deprecated
	public AvisoViagem() {
	}

	public AvisoViagem(LocalDate validoAte, String destino, Cartao cartao, String ipCliente, String agenteUsuario) {
		this.validoAte = validoAte;
		this.destino = destino;
		this.cartao = cartao;
		this.ipCliente = ipCliente;
		this.agenteUsuario = agenteUsuario;
		this.criadoEm = LocalDateTime.now();
		
		cartao.adicionaAviso(null);
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

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getAgenteUsuario() {
		return agenteUsuario;
	}

}
