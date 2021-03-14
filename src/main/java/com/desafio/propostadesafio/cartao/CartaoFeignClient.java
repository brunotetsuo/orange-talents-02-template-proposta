package com.desafio.propostadesafio.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cartaoFeignClient", url = "${cartaoFeignClient.targetUrl}")
public interface CartaoFeignClient {
	
	@PostMapping(value = "/api/cartoes")
	public CartaoResponse buscarCartao(CartaoRequest request);

}
