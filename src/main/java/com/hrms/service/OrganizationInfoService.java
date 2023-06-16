package com.hrms.service;

import java.util.List;

import com.hrms.beans.CommonResponseBean;
import com.hrms.beans.OrganizationStructureResponseBean;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.OrganizationInfoEntity;

public interface OrganizationInfoService {
	
	public CommonResponseBean getOrganizationInfo(int roleId, int menuId);
	
	public CommonResponseBean getDepartmentBasedOnBu(int businessId);
	public OrganizationStructureResponseBean getOrganizationStructure();
	public List<EmployeeDetails> getOrganizationHirarchy();

	public CommonResponseBean saveorganization(OrganizationInfoEntity infoentity);

	public List<OrganizationInfoEntity> getorganizationInfo();

}
