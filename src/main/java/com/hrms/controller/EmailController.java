package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.EmailDetails;
import com.hrms.serviceImpl.EmailServiceImpl;

@RestController
@RequestMapping("/mail")
public class EmailController {

	@Autowired
	private EmailServiceImpl service;
	
	@PostMapping("/sendMail")
	public String  sendEmail(@RequestBody EmailDetails bean) {
		
		String sendMail = this.service.sendSimpleMail(bean);
		
		return sendMail;
		
	}
	
	
}
