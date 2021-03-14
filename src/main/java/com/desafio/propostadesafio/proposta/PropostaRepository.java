package com.desafio.propostadesafio.proposta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	boolean existsByDocumento(String documento);

	Page<Proposta> findByStatusAndCartaoIsNull(PropostaStatus status, Pageable quantidadeProposta);

}
