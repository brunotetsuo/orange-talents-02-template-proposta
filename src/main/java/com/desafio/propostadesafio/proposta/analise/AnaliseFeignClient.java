package com.desafio.propostadesafio.proposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analiseFeignClient", url = "${analiseFeignClient.targetUrl}")
public interface AnaliseFeignClient {

	@PostMapping(value = "/api/solicitacao")
	public AnaliseStatusResponse analiseStatus(AnaliseStatusRequest request);

}
