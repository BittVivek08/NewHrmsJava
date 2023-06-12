package com.hrms.service;

import com.hrms.beans.EmployeeEducationBean;
import com.hrms.beans.ExperianceDetails;
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.EmployeeEducationDetails;
import com.hrms.entity.ExperinceEntity;

public interface EmployeePersonalInfoService {
	
	public ExperianceDetails saveEmployeeExperianceData(String empId,ExperinceEntity experianceentity);
	
	public ExperinceEntity getExperiancedetails(int id);

	public ExperianceDetails updateExperiancedetails(ExperinceEntity entity,String empId);
	
	//employee education details

	public EmployeeEducationBean saveEmployeeEducation(String empId, EmployeeEducationDetails employeeeducation);

	EmployeeEducationDetails getEducationdetails(int id);

	EmployeeEducationBean updateEmpEducationdetails(EmployeeEducationDetails educatonentity);

}
