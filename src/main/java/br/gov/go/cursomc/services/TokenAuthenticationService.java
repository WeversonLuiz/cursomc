package br.gov.go.cursomc.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import br.gov.go.cursomc.security.JWTUtil;
import br.gov.go.cursomc.security.UserSS;

public class TokenAuthenticationService {

	private String tokenPrefix = "Bearer";
	private String headerString = "Authorization";

	public void addAuthentication(HttpServletResponse response, String username) {
		String token = new JWTUtil().generateToken(username);
		response.addHeader(headerString, tokenPrefix + " " + token);

	}
	
	public Authentication getAuthentication(HttpServletRequest request) {
		
		JWTUtil jwtUtil = new JWTUtil();
		String token = request.getHeader(headerString);

		if (jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			if (username != null){
				return (Authentication) new UserSS(username);
			}
		}
		return null;

	}

}