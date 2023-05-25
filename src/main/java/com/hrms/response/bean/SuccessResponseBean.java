package com.hrms.response.bean;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class SuccessResponseBean {
	
	
    private boolean status;
    private String message;
    private String uploadURL;

 
}
