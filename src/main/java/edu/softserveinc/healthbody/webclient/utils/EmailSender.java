package edu.softserveinc.healthbody.webclient.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class EmailSender {

	private static final String username = "healthbodyservice@gmail.com";
	private static final String password = "healthbody185";
	private static final Properties properties = new Properties();

	static {
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
	}

	public static void sendMail(String recipient, String subject, String body)
			throws MessagingException {

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Runnable AsyncThread = new Runnable() {

			@Override
			public void run() {
				MimeMessage message = new MimeMessage(session);

				try {
					message.setFrom(new InternetAddress(username));
					message.addRecipient(Message.RecipientType.TO,
							new InternetAddress(recipient));

					message.setSubject(subject);

					message.setContent(body, "text/html; charset=utf-8");

					log.debug("send email to " + recipient);

					Transport.send(message);
				} catch (MessagingException e) {
					log.error("failed to send email", e);
				}
				
			}
		};

		
		new Thread(AsyncThread).start();
	}

}