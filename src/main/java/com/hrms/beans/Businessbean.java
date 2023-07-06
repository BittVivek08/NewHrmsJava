package com.hrms.beans;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.response.bean.BusinessUnitResponseBean;

import lombok.Data;

@Data
@Component
public class Businessbean {
	
    private String message;
	
	private boolean status;
	
	private List<BusinessUnitResponseBean> listOfBU;
	
	

}
