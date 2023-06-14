package com.hrms.beans;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hrms.entity.Businessunit;

import lombok.Data;

@Data
@Component
public class OrganizationStructureResponseBean {
	private String name;
	private String designation;
	private List<Businessunit> subordinates;

}
