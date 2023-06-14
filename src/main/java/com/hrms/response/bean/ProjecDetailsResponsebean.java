package com.hrms.response.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.beans.ProjechtRequiredFetchDetails;

import lombok.Data;

@Data
@Component
public class ProjecDetailsResponsebean {
	
	private String message;
	
	private Boolean status;
	
	private List<ProjechtRequiredFetchDetails> listProjectBean;

}
