package com.teste.api.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.teste.api.model.entidades.Usuario;
import com.teste.api.service.UsuarioService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTUtils {

	@Autowired
	private static UsuarioService usuarioService;

	@Value("${api.security.token.secret:my-key}")
	private static String secretKey;
	
	
	public void decodeToken(String token) {
		//header.playload.
	}
	
	
	

//	public static DecodedJWT decodeJWT(String token) {
//		return JWT.decode(token);
//	}
//
//	public static Usuario getIdUserFrom(String token) {
//		return extractUserFromToken(token);
//		
//	}
//
//	private static Usuario extractUserFromToken(String token) {
//		try {
//			Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.replace("Bearer ", ""))
//					.getBody();
//			Integer userId = Integer.parseInt(claims.getSubject());
//			
//			
//			
//			return usuarioService.obterUsuarioPorId(userId);
//		} catch (Exception e) {
//			return null;
//		}
//	}

}
