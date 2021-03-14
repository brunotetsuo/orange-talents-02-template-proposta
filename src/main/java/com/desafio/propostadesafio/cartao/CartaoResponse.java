package com.desafio.propostadesafio.cartao;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.propostadesafio.cartao.aviso.AvisoViagemResponse;
import com.desafio.propostadesafio.cartao.bloqueio.BloqueioResponse;
import com.desafio.propostadesafio.cartao.carteira.CarteiraDigitalResponse;
import com.desafio.propostadesafio.cartao.parcela.ParcelaResponse;
import com.desafio.propostadesafio.cartao.renegociacao.RenegociacaoResponse;
import com.desafio.propostadesafio.cartao.vencimento.VencimentoResponse;
import com.desafio.propostadesafio.proposta.Proposta;
import com.desafio.propostadesafio.proposta.PropostaRepository;

public class CartaoResponse {

	@Autowired
	private PropostaRepository propostaRepository;

	private String id;
	private String emitidoEm;
	private String titular;
	private Set<BloqueioResponse> bloqueios;
	private Set<AvisoViagemResponse> avisos;
	private Set<CarteiraDigitalResponse> carteiras;
	private Set<ParcelaResponse> parcelas;
	private Integer limite;
	private RenegociacaoResponse renegociacao;
	private VencimentoResponse vencimento;
	private String idProposta;

	public CartaoResponse(String id, String emitidoEm, String titular, Integer limite, String idProposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
	}

	public Cartao toModel(Proposta proposta) {
		return new Cartao(this.id, LocalDateTime.parse(this.emitidoEm), this.titular, this.limite, proposta);
	}

	public PropostaRepository getPropostaRepository() {
		return propostaRepository;
	}

	public String getId() {
		return id;
	}

	public String getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public Set<BloqueioResponse> getBloqueios() {
		return bloqueios;
	}

	public Set<AvisoViagemResponse> getAvisos() {
		return avisos;
	}

	public Set<CarteiraDigitalResponse> getCarteiras() {
		return carteiras;
	}

	public Set<ParcelaResponse> getParcelas() {
		return parcelas;
	}

	public Integer getLimite() {
		return limite;
	}

	public RenegociacaoResponse getRenegociacao() {
		return renegociacao;
	}

	public VencimentoResponse getVencimento() {
		return vencimento;
	}

	public String getIdProposta() {
		return idProposta;
	}

}
