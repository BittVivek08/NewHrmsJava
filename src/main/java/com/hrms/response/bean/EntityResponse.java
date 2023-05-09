package com.hrms.response.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EntityResponse {

	private String msg;
	private boolean status;
}
