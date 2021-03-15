package com.desafio.propostadesafio.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.desafio.propostadesafio.proposta.analise.AnaliseFeignClient;
import com.desafio.propostadesafio.proposta.analise.AnaliseStatusRequest;
import com.desafio.propostadesafio.proposta.analise.AnaliseStatusResponse;
import com.desafio.propostadesafio.proposta.excecoes.ApiErrorException;

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
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Proposta já existe");
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
			throw new ApiErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Aconteceu um erro inesperado!");
		}
	}

	@GetMapping(value = "/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<?> buscaProposta(@PathVariable("id") Long id) {
		if (!propostaRepository.existsById(id)) {
			throw new ApiErrorException(HttpStatus.NOT_FOUND, "Proposta não existe");
		}
		PropostaResponse response = new PropostaResponse(propostaRepository.findById(id).get());
		return ResponseEntity.ok(response);

	}

}
