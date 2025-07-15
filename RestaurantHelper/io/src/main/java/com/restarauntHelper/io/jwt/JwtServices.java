package com.restarauntHelper.io.jwt;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServices {

	@Value("jwt.secret")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long jwtExpiration;
	
	
	private Key getSginatureKey() { //Gera a asignartura sha no jwt??
		byte[] keyEmBytes = Decoders.BASE64.decode(jwtSecret);
		return Keys.hmacShaKeyFor(keyEmBytes);
	}
	
	public String generateToken(UserDetail userDetails) {
		
	}
	
	
	
}
