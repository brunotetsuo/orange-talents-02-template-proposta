package com.desafio.propostadesafio.cartao.bloqueio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.propostadesafio.cartao.Cartao;
import com.desafio.propostadesafio.cartao.CartaoFeignClient;
import com.desafio.propostadesafio.cartao.CartaoRepository;
import com.desafio.propostadesafio.proposta.excecoes.ApiErrorException;

import feign.FeignException;

@RestController
@RequestMapping("/api/bloqueios/{id}")
public class BloqueioController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BloqueioRepository bloqueioRepository;

	@Autowired
	private CartaoFeignClient cartaoFeignClient;

	@PostMapping
	@Transactional
	public ResponseEntity<?> bloqueioCartao(@PathVariable("id") Long id,
			@RequestHeader(value = "User-Agent") String userAgent, HttpServletRequest userIp) {
		if (!cartaoRepository.existsById(id)) {
			throw new ApiErrorException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
		}

		Cartao cartao = cartaoRepository.findById(id).get();
		Assert.notNull(cartao, "Cartão está inválido");
		try {
			cartaoFeignClient.bloqueio(cartao.getNumeroCartao(), new BloqueioRequest(userAgent));
		} catch (FeignException e) {
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já esta bloqueado");
		}
		Bloqueio bloqueio = new Bloqueio(userAgent, cartao, userIp.getRemoteAddr());
		bloqueioRepository.save(bloqueio);
		return ResponseEntity.ok().build();
	}

}
