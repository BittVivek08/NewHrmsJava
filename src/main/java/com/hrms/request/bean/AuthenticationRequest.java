package com.hrms.request.bean;

import lombok.Data;

@Data
public class AuthenticationRequest {

	String email;
	String password;
}
