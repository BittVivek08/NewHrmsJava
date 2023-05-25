package com.hrms.service;

import com.hrms.beans.CommonResponseBean;

public interface OrganizationInfoService {
	
	public CommonResponseBean getOrganizationInfo(int roleId, int menuId);
	
	public CommonResponseBean getDepartmentBasedOnBu(int businessId);

}
