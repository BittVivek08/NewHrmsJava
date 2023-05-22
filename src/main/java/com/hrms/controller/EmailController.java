package com.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.EmailDetails;
import com.hrms.serviceImpl.EmailServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/business")
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailServiceImpl emailService;

	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails emailDetails) {
		log.info("EmailController , sendMail");
		return emailService.sendSimpleMail(emailDetails);
	}
}
