package com.desafio.propostadesafio.cartao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.desafio.propostadesafio.cartao.aviso.AvisoViagem;
import com.desafio.propostadesafio.cartao.biometria.Biometria;
import com.desafio.propostadesafio.cartao.bloqueio.Bloqueio;
import com.desafio.propostadesafio.cartao.carteira.CarteiraDigital;
import com.desafio.propostadesafio.cartao.parcela.Parcela;
import com.desafio.propostadesafio.cartao.renegociacao.Renegociacao;
import com.desafio.propostadesafio.cartao.vencimento.Vencimento;
import com.desafio.propostadesafio.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numeroCartao;
	private LocalDateTime emitidoEm;
	private String titular;
	@OneToMany(mappedBy = "cartao")
	private Set<Bloqueio> bloqueios = new HashSet<>();
	@OneToMany(mappedBy = "cartao")
	private Set<AvisoViagem> avisos = new HashSet<>();
	@OneToMany(mappedBy = "cartao")
	private Set<CarteiraDigital> carteiras = new HashSet<>();
	@OneToMany(mappedBy = "cartao")
	private Set<Parcela> parcelas = new HashSet<>();
	private Integer limite;
	@OneToOne(mappedBy = "cartao")
	private Renegociacao renegociacao;
	@OneToOne(mappedBy = "cartao")
	private Vencimento vencimento;
	@OneToOne
	private Proposta Proposta;
	@OneToMany(mappedBy = "cartao")
	private List<Biometria> biometrias = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private EstadoCartao estadoCartao;

	@Deprecated
	public Cartao() {
	}

	public Cartao(String numeroCartao, LocalDateTime emitidoEm, String titular, Integer limite, Proposta proposta) {
		this.numeroCartao = numeroCartao;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		Proposta = proposta;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public List<Biometria> getBiometrias() {
		return biometrias;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public Set<Bloqueio> getBloqueios() {
		return bloqueios;
	}

	public Set<AvisoViagem> getAvisos() {
		return avisos;
	}

	public Set<CarteiraDigital> getCarteiras() {
		return carteiras;
	}

	public Set<Parcela> getParcelas() {
		return parcelas;
	}

	public Integer getLimite() {
		return limite;
	}

	public Renegociacao getRenegociacao() {
		return renegociacao;
	}

	public Vencimento getVencimento() {
		return vencimento;
	}

	public Proposta getProposta() {
		return Proposta;
	}

	public void adicionaBloqueio(Bloqueio bloqueio) {
		this.bloqueios.add(bloqueio);
	}

	public EstadoCartao getEstadoCartao() {
		return estadoCartao;
	}

	public void setEstadoCartao(EstadoCartao estadoCartao) {
		this.estadoCartao = estadoCartao;
	}

}
