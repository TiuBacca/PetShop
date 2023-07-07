package com.baccarin.petshop;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Objects;
import java.util.Random;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.baccarin.petshop.vo.request.LoginRequest;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class Util {

	private final EntityManager em;

	private static final String CHARACTERS_SENHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
	private static final int TAMANHO_SENHA = 8;

	public static String generateRandomString() {
		Random random = new Random();
		StringBuilder sb = new StringBuilder(TAMANHO_SENHA);

		for (int i = 0; i < TAMANHO_SENHA; i++) {
			int randomIndex = random.nextInt(CHARACTERS_SENHA.length());
			char randomChar = CHARACTERS_SENHA.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}

	public static String criptografar(String texto) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
			return bytesParaHex(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String bytesParaHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	public String gerarToken(LoginRequest request) {
		return "ajustar serviço para gerar token";
	}

	public Connection getConnection() {
		return em.unwrap(Connection.class);
	}

	public EntityManager getEntityManager() {
		return this.em;
	}

	public static Boolean validaDiferenteNullAndDiferenteZero(Long id) {		
		return Objects.nonNull(id) && id != 0;
	}


}
