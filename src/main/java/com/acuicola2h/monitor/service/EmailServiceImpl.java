package com.acuicola2h.monitor.service;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
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
	
	@Value("${monitor.email.address}")
    String from;
	
	@Value("${monitor.email.password}")
    String password;
	
    @Value("${monitor.email.recipient}")
    String recipients;
    
    Properties properties = System.getProperties();
    
    Logger log = LoggerFactory.getLogger(getClass());
	
    @Async
	public Future<Boolean> sendEmail(List<String> list) {
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    Session session = Session.getInstance(properties, new Authenticator() {
	    	@Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(from, password);
	        }
	    });
		try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            if (list.size() > 1) {
            	message.setSubject("Fish Tank Data Requires Your Attention!");
            } else {
            	message.setSubject("All parameters are within acceptable bounds.");
            }
            String body = createEmailBody(list);
            
            message.setContent(body, "text/html");
            
            Transport.send(message);
            log.info("Email has been sent.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return CompletableFuture.completedFuture(false);
        }
		return CompletableFuture.completedFuture(true);
	}
    
    private String createEmailBody(List<String> list) {
        StringBuilder sb = new StringBuilder();

        sb.append("<html><head>");
        sb.append("<style>");
        sb.append("table { width: 100%; border-collapse: collapse; }"); // Ensure borders between cells are merged
        sb.append("th, td { border: 2px solid black; padding: 8px; text-align: center; }"); // Bolder borders, padding, and centered text
        sb.append("th { background-color: blue; color: white; }"); // Header background and text color
        sb.append("tr:nth-child(even) { background-color: #f2f2f2; }"); // Zebra striping for rows
        sb.append("</style>");
        sb.append("</head><body>");

        sb.append("<table>");
        sb.append("<tr><th>Data Out of Bounds</th></tr>"); // Header row

        for (String data : list) {
            sb.append("<tr><td>").append(data).append("</td></tr>");
        }

        sb.append("</table>");
        sb.append("</body></html>");

        return sb.toString();
    }
	
}
