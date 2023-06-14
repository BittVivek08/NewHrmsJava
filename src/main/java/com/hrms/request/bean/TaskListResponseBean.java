package com.hrms.request.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.beans.TasksDetailsResponseBean;

import lombok.Data;

@Data
@Component
public class TaskListResponseBean {

	private String message;
	
	private Boolean status;
	
	private List<TasksDetailsResponseBean> listOfTasks;
	
}
