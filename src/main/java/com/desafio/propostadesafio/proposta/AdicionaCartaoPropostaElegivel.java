package com.desafio.propostadesafio.proposta;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.desafio.propostadesafio.cartao.Cartao;
import com.desafio.propostadesafio.cartao.CartaoFeignClient;
import com.desafio.propostadesafio.cartao.CartaoRepository;
import com.desafio.propostadesafio.cartao.CartaoRequest;
import com.desafio.propostadesafio.cartao.CartaoResponse;

@Component
public class AdicionaCartaoPropostaElegivel {

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private CartaoFeignClient cartaoFeignClient;

	@Scheduled(initialDelayString = "${periocidade.adiciona-cartao}", fixedDelayString = "${periocidade.adiciona-cartao}")
	@Transactional
	private void adicionaCartaoNaProposta() {
		Page<Proposta> propostas = propostaRepository.findByStatusAndCartaoIsNull(PropostaStatus.ELEGIVEL,
				PageRequest.of(0, 5));
		if (propostas.isEmpty()) {
			return;
		}
		for (Proposta proposta : propostas) {
			CartaoResponse cartaoResponse = cartaoFeignClient.buscarCartao(new CartaoRequest(proposta));
			Assert.notNull(cartaoResponse, "Houve um erro ao buscar cartão");
			Cartao novoCartao = cartaoResponse.toModel(proposta);
			cartaoRepository.save(novoCartao);
			proposta.adicionaNumeroCartao(cartaoResponse.getId());
			propostaRepository.save(proposta);
		}
	}

}
