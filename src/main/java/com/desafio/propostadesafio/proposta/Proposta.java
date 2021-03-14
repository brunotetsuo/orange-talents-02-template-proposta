package com.desafio.propostadesafio.proposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String documento;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false)
	private BigDecimal salario;
	@Column(updatable = false)
	private LocalDateTime instanteCriado = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private PropostaStatus status = PropostaStatus.NAO_ELEGIVEL;
	private String cartao;

	@SuppressWarnings("unused")
	@Deprecated
	private Proposta() {
	}

	public Proposta(@NotBlank(message = "Está em branco ou inválido") String documento,
			@Email(message = "Email inválido") String email,
			@NotBlank(message = "Nome esta em branco ou inválido") String nome,
			@NotBlank(message = "Endereço esta em branco ou inválido") String endereco,
			@Positive(message = "Deve ser valor positivo") BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public LocalDateTime getInstanteCriado() {
		return instanteCriado;
	}

	public void updateStatus(PropostaStatus status) {
		this.status = status;
	}

	public String getCartao() {
		return cartao;
	}
	
	public PropostaStatus getStatus() {
		return status;
	}

	public void adicionaNumeroCartao(String cartao) {
		this.cartao = cartao;
	}
}
