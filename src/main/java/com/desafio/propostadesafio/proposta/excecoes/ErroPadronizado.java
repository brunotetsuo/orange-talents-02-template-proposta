package com.desafio.propostadesafio.proposta.excecoes;

import java.util.Collection;

public class ErroPadronizado {

	private Collection<String> erros;

	public ErroPadronizado(Collection<String> erros) {
		this.erros = erros;
	}

	public Collection<String> getErros() {
		return erros;
	}

}
