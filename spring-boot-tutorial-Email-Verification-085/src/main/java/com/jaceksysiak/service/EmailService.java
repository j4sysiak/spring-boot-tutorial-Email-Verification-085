package com.jaceksysiak.service;

import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.enable}")
	private Boolean enable;
	
	private void send(MimeMessagePreparator preparator){
		
		if(enable){
			mailSender.send(preparator);
		}
	}
	
	public void sendVerificationEmail(String emailAddress){
		
//		Context context = new Context();
//		
//		context.setVariable("name", "Jacek");
//		
//		final String emailContents = templateEngine.process("verifyemail", context);
//		
//		System.out.println(emailContents);
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				 
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				
				message.setTo(emailAddress);
				message.setFrom(new InternetAddress("jaceksysiak@wp.pl"));
				message.setSubject("Please Verify Your Email Address");
				message.setSentDate(new Date());
				
				message.setText("Hello there");
				 
			}
		};
		
		send(preparator);
		
	}

}
