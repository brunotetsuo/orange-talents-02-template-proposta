package com.desafio.propostadesafio.cartao.aviso;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.propostadesafio.cartao.Cartao;
import com.desafio.propostadesafio.cartao.CartaoFeignClient;
import com.desafio.propostadesafio.cartao.CartaoRepository;
import com.desafio.propostadesafio.proposta.excecoes.ApiErrorException;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/avisos/{id}")
public class AvisoViagemController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private AvisoViagemRepository avisoRepository;

	@Autowired
	private CartaoFeignClient cartaoFeignClient;

	@PostMapping
	@Transactional
	public ResponseEntity<?> avisoViagem(@PathVariable("id") Long id,
			@RequestHeader(value = "User-Agent") String agenteUsuario, HttpServletRequest userIp,
			@RequestBody @Valid AvisoViagemRequest request) {
		if (!cartaoRepository.existsById(id)) {
			throw new ApiErrorException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
		}
		Cartao cartao = cartaoRepository.findById(id).get();
		Assert.notNull(cartao, "Cartão inválido");
		try {
			cartaoFeignClient.avisoViagem(cartao.getNumeroCartao(), request);
		} catch (FeignException e) {
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Aviso de viagem já criado");
		} catch (Exception e) {
			throw new ApiErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Aconteceu um erro inesperado!");
		}
		AvisoViagem aviso = new AvisoViagem(request.getValidoAte(), request.getDestino(), cartao,
				userIp.getRemoteAddr(), agenteUsuario);
		avisoRepository.save(aviso);
		return ResponseEntity.ok().build();
	}

}
