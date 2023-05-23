package com.hrms.response.bean;

import java.util.List;

import lombok.Data;
@Data
public class ProjectListResponse {
	private String message;
	private boolean status;

	private List<ProjectResponse> listOfProjectResponse;
}