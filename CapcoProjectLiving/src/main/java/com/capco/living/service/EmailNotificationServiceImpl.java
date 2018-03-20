package com.capco.living.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.capco.living.custom.exception.LivingException.LivingServiceException;

@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String to,String sub,String msg) throws LivingServiceException {
		
		MimeMessage mail = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail);
		try {
			
			helper.setTo(to);
			helper.setSubject(sub);
			helper.setText(msg);
			javaMailSender.send(mail);
			
		} catch (MessagingException e) {
			
			throw new LivingServiceException(e);
		}
		
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	   
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("donotreply99099@gmail.com");
	    mailSender.setPassword("zxcvbnm1234567890");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}
}