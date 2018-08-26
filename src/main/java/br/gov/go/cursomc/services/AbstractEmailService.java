package br.gov.go.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.gov.go.cursomc.domain.Cliente;
import br.gov.go.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
	
	/**Método responsável para retornar um html preenchido com os dados do pedido
	 * O Retorno será uma String com o HTML preenchido*/
	protected String htmlFromTemplatePedido(Pedido obj){
		Context context = new Context();
		context.setVariable("pedido", obj);
		context.setVariable("logo", "logo");
		//context.setVariable("background", "background");

		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj){
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		/**Pegando o pedido e gerando um objeto do tipo mimeMessage*/
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		/**Para atribuir valores a esta mensagem instanciamos o MimeMessageHelper*/
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		//mmh.addInline("background", new ClassPathResource("static/images/email/background_email.png"));
		//mmh.addInline("logo", new ClassPathResource("static/images/email/shop.png"), "image/png");
		ClassPathResource classPathResource = new ClassPathResource("static/images/email/shop.png");
		mmh.addInline("logo", classPathResource);
		
		//mmh.addAttachment("logo", new ClassPathResource("static/images/email/shop.png"), "image/png");
		mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass){
		SimpleMailMessage sm = prepareNewPasswordMail(cliente, newPass);
		sendEmail(sm);
	}

	private SimpleMailMessage prepareNewPasswordMail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha.");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass );
		return sm;
	}

}
