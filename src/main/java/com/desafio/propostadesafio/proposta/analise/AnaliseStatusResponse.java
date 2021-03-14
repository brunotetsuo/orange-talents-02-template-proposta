package com.desafio.propostadesafio.proposta.analise;

import com.desafio.propostadesafio.proposta.PropostaStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnaliseStatusResponse {

	private String idProposta;
	@JsonProperty("resultadoSolicitacao")
	private String status;

	public String getIdProposta() {
		return idProposta;
	}

	public String getStatus() {
		return status;
	}

	public PropostaStatus toModel() {
		if("COM_RESTRICAO".equals(status)) {
			return PropostaStatus.NAO_ELEGIVEL;
		}
		return PropostaStatus.ELEGIVEL;
	}

}
