package com.acuicola2h.monitor.service;

import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	final String from = "no-reply@Acuicola2H.com";
    final String password = "AcNoReply!@2H";
    
    final String fromG = "Acuicola2H.no.reply@gmail.com";
    final String passwordG = "nioiykrjhtmifahq";

    // Recipient's email ID
    String to = "Erick.Hdez199721@gmail.com";

    // Assuming you are sending email through localhost
    String host = "localhost"; // Or IP address/hostname of your hMailServer

    // Get system properties object
    Properties properties = System.getProperties();
	
	public String sendEmail(List<String> list) {
	    
	    // Gmail SMTP
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true"); //TLS
	    // Get the default Session object
	    Session session = Session.getInstance(properties, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(fromG, passwordG);
	        }
	    });
		try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            
            // Set From: header field
            message.setFrom(new InternetAddress(from));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Fish Tank Data Requires Your Attention!");

            // Now set the actual message
            //message.setText("The below entries were found in your data.");
            StringBuilder sb = new StringBuilder();
            for (String string : list) {
            	sb.append(string);
            	sb.append('\n');
            }
            message.setText(sb.toString());
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
		return "done";
	}
	
}
