package br.gov.go.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.gov.go.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
