package com.hrms.beans;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.response.bean.EmployeeFetchResponse;

import lombok.Data;

@Data
@Component
public class EmployeeResponseBean {

	private String message;

	private Boolean status;

	private List<EmployeeFetchResponse> listOfEmployee;

}
