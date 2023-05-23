package com.hrms.response.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.entity.PositionEntity;

import lombok.Data;

@Data
@Component
public class ListOfPositionsResponseBean {
	
	private boolean status;
	private String msg;
	private List<PositionEntity> listOfPositions;

}
