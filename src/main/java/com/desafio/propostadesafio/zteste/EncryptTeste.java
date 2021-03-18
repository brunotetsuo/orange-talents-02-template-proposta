package com.desafio.propostadesafio.zteste;

import java.nio.charset.StandardCharsets;

import org.springframework.security.crypto.encrypt.Encryptors;

import com.google.common.hash.Hashing;

public class EncryptTeste {

	public static void main(String[] args) {

		String textoPuro = "Teste para testar";
		
		String textoEncrypt = Hashing.sha256().hashString(textoPuro, StandardCharsets.UTF_8).toString();
		
		System.out.println(textoEncrypt);
		
		System.out.println();
		
		String textoPuro2 = "Teste para testar";
		String textoEncrypt2 = Hashing.sha256().hashString(textoPuro2, StandardCharsets.UTF_8).toString();
		
		System.out.println(textoEncrypt2);
		
		System.out.println();

		String cpf = "97360817021";

		String encryptedText = Encryptors.text("abcabc", "cbacba").encrypt(cpf);
		System.out.println(encryptedText);

		System.out.println();

		String decryptedText = Encryptors.text("abcabc", "cbacba").decrypt(encryptedText);
		System.out.println(decryptedText);

	}

}
