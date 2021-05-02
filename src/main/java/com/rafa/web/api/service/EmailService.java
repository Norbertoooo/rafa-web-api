package com.rafa.web.api.service;

import com.rafa.web.api.domain.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.rafa.web.api.shared.Constantes.SENHA_PADRAO_RESPONSAVEL;

@Service
@Slf4j
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void enviarEmail(String email, String mensagem) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Rafa - Web");
        message.setText(mensagem);
        emailSender.send(message);
    }

    public void enviarEmail(String email, Login login) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Rafa-Web - Credencias de acesso");
        message.setText("Credencias para login: \nEmail: " + login.getEmail() + " \nSenha: " + SENHA_PADRAO_RESPONSAVEL);
        log.debug("Enviando email para: {}, com texto: \n {}", login.getEmail(), message);
        emailSender.send(message);
    }

    public void enviarEmail(Login login) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(login.getEmail());
        message.setSubject("Rafa-Web - Credencias de acesso");
        message.setText("Credencias para login: \nEmail: " + login.getEmail() + " \nSenha: " + SENHA_PADRAO_RESPONSAVEL);
        log.debug("Enviando email para: {}, com texto: \n {}", login.getEmail(), message);
        emailSender.send(message);
    }
}
