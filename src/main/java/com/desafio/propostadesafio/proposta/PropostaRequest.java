package com.desafio.propostadesafio.proposta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;

import com.desafio.propostadesafio.proposta.excecoes.ApiErrorException;
import com.desafio.propostadesafio.validadores.CPFOuCNPJ;
import com.sun.istack.NotNull;

public class PropostaRequest {

	@CPFOuCNPJ
	@NotBlank(message = "CPF ou CNPJ em branco ou inválido.")
	private String documento;
	@Email(message = "Email inválido")
	@NotBlank(message = "Email não pode estar em branco")
	private String email;
	@NotBlank(message = "Nome em branco ou inválido")
	private String nome;
	@NotBlank(message = "Endereco em branco ou inválido")
	private String endereco;
	@NotNull
	@Positive(message = "Valor deve ser positivo")
	private BigDecimal salario;

	public PropostaRequest(@NotBlank(message = "CPF ou CNPJ em branco ou inválido.") String documento,
			@Email(message = "Email inválido") String email,
			@NotBlank(message = "Nome em branco ou inválido") String nome,
			@NotBlank(message = "Endereco em branco ou inválido") String endereco,
			@Positive(message = "Valor deve ser positivo") BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
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

	@Override
	public String toString() {
		return "PropostaRequest [documento=" + documento + ", email=" + email + ", nome=" + nome + ", endereco="
				+ endereco + ", salario=" + salario + "]";
	}

	public Proposta toModel(PropostaRepository propostaRepository) {

		// Decodifica documentos das propostas no banco.
		List<Proposta> propostas = propostaRepository.findAll();
		List<String> documentos = new ArrayList<>();

		for (Proposta proposta : propostas) {
			String doc = Encryptors.text("abcabc", "cbacba").decrypt(proposta.getDocumento());
			documentos.add(doc);
		}

		// Verficia se já existe proposta para o documento.
		if (documentos.contains(this.documento)) {
			throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Proposta já existe");
		}

		return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
	}

}
