package com.desafio.propostadesafio.cartao.carteira;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.desafio.propostadesafio.cartao.Cartao;
import com.desafio.propostadesafio.cartao.CartaoFeignClient;
import com.desafio.propostadesafio.cartao.CartaoRepository;
import com.desafio.propostadesafio.proposta.excecoes.ApiErrorException;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/carteiras/{id}")
public class CarteiraDigitalController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CartaoFeignClient cartaoFeignClient;

	@Autowired
	private CarteiraDigitalRepository carteiraDigitalRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criaCarteira(@PathVariable("id") Long id,
			@RequestBody @Valid CarteiraDigitalRequest request, UriComponentsBuilder uriComponentsBuilder) {
		if (!cartaoRepository.existsById(id)) {
			throw new ApiErrorException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
		}
		Cartao cartao = cartaoRepository.findById(id).get();
		Assert.notNull(cartao, "Cartão inválido");
		try {
			cartaoFeignClient.incluirCarteira(cartao.getNumeroCartao(), request);
		} catch (FeignException e) {
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Cartão já incluido na carteira " + request.getCarteira());
		}
		CarteiraDigital carteira = new CarteiraDigital(request.getEmail(), request.getCarteira(), cartao);
		carteiraDigitalRepository.save(carteira);
		URI uri = uriComponentsBuilder.path("/api/carteiras/" + cartao.getId() + "/{id}")
				.buildAndExpand(carteira.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
