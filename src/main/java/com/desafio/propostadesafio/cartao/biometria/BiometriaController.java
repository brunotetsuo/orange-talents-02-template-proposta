package com.desafio.propostadesafio.cartao.biometria;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.desafio.propostadesafio.cartao.CartaoRepository;

@RestController
@RequestMapping(value = "/api/biometrias")
public class BiometriaController {

	@Autowired
	private BiometriaRepository biometriaRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@PostMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<?> criaBiometria(@RequestBody @Valid BiometriaRequest request, @PathVariable("idCartao") Long id,
			UriComponentsBuilder uriComponentsBuilder) {
		Biometria biometria = request.toModel(id, cartaoRepository);
		biometriaRepository.save(biometria);
		URI uri = uriComponentsBuilder.path("/api/biometrias/{idCartao}").buildAndExpand(biometria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
