package com.hrms.serviceImpl;

import java.io.File;

//import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hrms.beans.EmailDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
   
	
	public String sendSimpleMail(EmailDetails details) {
		
		this.log.info("Entered mail send method in service");
		
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setFrom(sender);
			//mailMessage.setFrom("yadavtest45@gmail.com");
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			javaMailSender.send(mailMessage);
			this.log.info("Successfully  mail send method in service");
			return "Mail Sent Successfully...";
		} catch (Exception e) {
			e.printStackTrace();
			this.log.info("failed to send  mail method in service");
			return "Error while Sending Mail";
		}
	}

	
	// To send an email with attachment
	public String sendMailWithAttachment(EmailDetails details) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;

		try {
			// Setting multipart as true for attachments to be send
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(details.getRecipient());
			mimeMessageHelper.setText(details.getMsgBody());
			mimeMessageHelper.setSubject(details.getSubject());

			// Adding the attachment
			FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));

			mimeMessageHelper.addAttachment(file.getFilename(), file);

			javaMailSender.send(mimeMessage);
			return "Mail sent Successfully";
		} catch (MessagingException e) {
			return "Error while sending mail!!!";
		}
	}


}
	

