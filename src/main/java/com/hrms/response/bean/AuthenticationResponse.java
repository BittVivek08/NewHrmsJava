package com.hrms.response.bean;

import lombok.Data;

@Data
public class AuthenticationResponse {

	public AuthenticationResponse (String jwtToken) {
		this.jwtToken = jwtToken;
	}
	String jwtToken;
}
