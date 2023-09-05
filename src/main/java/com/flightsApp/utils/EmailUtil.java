package com.flightsApp.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender sender;
	
	public void sendItinerary(String toAddress, String filePath) {
		System.out.println(filePath);
			
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
			messageHelper.setTo(toAddress);
			messageHelper.setSubject("Details of your flight reservarion");
			messageHelper.setText("Please find the attached");
			messageHelper.addAttachment("Itinerary", new File(filePath) );
			sender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
			
	}
}
