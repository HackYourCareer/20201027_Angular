package com.devx.angular.shop.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

@Service
public class DefaultJwtService {
		private String SECRET = "SECRET";


	public String generateJwt(String userId) {
		return JWT.create()
				.withSubject(userId)
				.sign(Algorithm.HMAC256(SECRET));
	}

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256(SECRET))
				.build()
				.verify(token)
				.getSubject();
	}
}
