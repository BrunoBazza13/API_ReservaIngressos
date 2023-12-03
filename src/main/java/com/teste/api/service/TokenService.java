package com.teste.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.teste.api.model.entidades.Usuario;

@Service
public class TokenService {

	@Value("{api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {

		if (usuario == null) {
			return null;
		}

		try {
			Algorithm algorihtm = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("scheduling").withSubject(usuario.getLogin())
					.withClaim("id", usuario.getId()).sign(algorihtm);

		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error while generating token", exception);

		}
	}

	public String validateToken(String token) {

		try {
			Algorithm algorihtm = Algorithm.HMAC256(secret);
			return JWT.require(algorihtm).withIssuer("scheduling").build().verify(token).getSubject();

		} catch (JWTVerificationException exceptionVerification) {
			return "";

		}
	}
}
