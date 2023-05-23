package com.hrms.response.bean;

import java.util.List;

import com.hrms.entity.ProjectDetailsEntity;

import lombok.Data;
@Data
public class ProjectListResponse {
private String message;
private boolean status;

private ProjectResponse listOfProjectResponse;
}