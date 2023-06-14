package com.hrms.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.CommonResponseBean;
import com.hrms.beans.OrganizationStructureResponseBean;
import com.hrms.entity.Businessunit;
import com.hrms.entity.EmployeeDetails;
import com.hrms.service.OrganizationInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/Organization")
public class OrganizationInfoController {

	@Autowired
	private OrganizationInfoService orgInfoService;

	@GetMapping("/getOrganizationInfo")
	public CommonResponseBean getOrganizationInfo(@QueryParam(value = "roleId") int roleId,
			@QueryParam(value = "menuId") int menuId) {
		log.info("Inside getOrganizationInfo()");
		return orgInfoService.getOrganizationInfo(roleId, menuId);
	}

	@GetMapping("/getDUbasedOnBu")
	public CommonResponseBean getDepartmentBasedOnBu(@QueryParam(value = "businessId") int businessId) {
		log.info("fetchin department based on busineddId");
		return orgInfoService.getDepartmentBasedOnBu(businessId);
	}
	@GetMapping("/getAllBusinessUnitData")
	public OrganizationStructureResponseBean getAllOrganizationStucture(){
		log.info("fetching BusinessUnit data ");
		return orgInfoService.getOrganizationStructure();
	}
	@GetMapping("/getOrganizationHierarchy")
	public List<EmployeeDetails> getAllOrganizationHierarchy(){
		log.info("fetching EmployeeDetails data ");
		return orgInfoService.getOrganizationHirarchy();
	}
}
