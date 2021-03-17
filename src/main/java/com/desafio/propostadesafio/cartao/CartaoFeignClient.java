package com.desafio.propostadesafio.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.desafio.propostadesafio.cartao.aviso.AvisoViagemRequest;
import com.desafio.propostadesafio.cartao.aviso.AvisoViagemResponse;
import com.desafio.propostadesafio.cartao.bloqueio.BloqueioRequest;
import com.desafio.propostadesafio.cartao.bloqueio.BloqueioResponse;

@FeignClient(name = "cartaoFeignClient", url = "${cartaoFeignClient.targetUrl}")
public interface CartaoFeignClient {

	@PostMapping(value = "/api/cartoes")
	public CartaoResponse buscarCartao(CartaoRequest request);

	@PostMapping(value = "/api/cartoes/{id}/bloqueios")
	public BloqueioResponse bloqueio(@PathVariable String id, BloqueioRequest request);
	
	@PostMapping(value = "/api/cartoes/{id}/avisos")
	public AvisoViagemResponse avisoViagem(@PathVariable String id, AvisoViagemRequest request);

}
