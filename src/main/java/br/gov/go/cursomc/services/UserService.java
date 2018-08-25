package br.gov.go.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.gov.go.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS UsuarioAutenticado(){
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
