package com.desafio.propostadesafio.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.desafio.propostadesafio.proposta.analise.AnaliseFeignClient;
import com.desafio.propostadesafio.proposta.analise.AnaliseStatusRequest;
import com.desafio.propostadesafio.proposta.analise.AnaliseStatusResponse;

import feign.FeignException;

@RestController
@RequestMapping(value = "/api/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private AnaliseFeignClient analiseFeignClient;

	@PostMapping
	@Transactional
	public ResponseEntity<?> postNovaProposta(@RequestBody @Valid PropostaRequest request,
			UriComponentsBuilder uriComponentsBuilder) {
		if (propostaRepository.existsByDocumento(request.getDocumento())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Já existe proposta para esse documento = " + request.getDocumento());
		}
		// grava no banco
		Proposta novaProposta = request.toModel();
		propostaRepository.save(novaProposta);

		// envia para analise
		PropostaStatus status = fazerAnalise(novaProposta);
		novaProposta.updateStatus(status);

		URI uri = uriComponentsBuilder.path("/api/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(uri).body(novaProposta);
	}

	private PropostaStatus fazerAnalise(Proposta novaProposta) {
		try {
			AnaliseStatusRequest analiseRequest = new AnaliseStatusRequest(novaProposta);
			AnaliseStatusResponse analiseResponse = analiseFeignClient.analiseStatus(analiseRequest);
			return analiseResponse.toModel();
		} catch (FeignException.UnprocessableEntity e) {
			return PropostaStatus.NAO_ELEGIVEL;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Aconteceu um erro inesperado!");
		}
	}

}
