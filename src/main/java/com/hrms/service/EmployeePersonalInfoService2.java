package com.hrms.service;

import com.hrms.beans.EmployeeEducationBean;
import com.hrms.beans.ExperianceDetails;
import com.hrms.entity.EmpEducationDetailsEntity;
import com.hrms.entity.ExperinceEntity;

public interface EmployeePersonalInfoService2 {
	
	public ExperianceDetails saveEmployeeExperianceData(String empId,ExperinceEntity experianceentity);
	
	public ExperinceEntity getExperiancedetails(int id);

	public ExperianceDetails updateExperiancedetails(ExperinceEntity entity);
	
	//employee education details

	public EmployeeEducationBean saveEmployeeEducation(String empId, EmpEducationDetailsEntity employeeeducation);

	EmpEducationDetailsEntity getEducationdetails(int id);

	EmployeeEducationBean updateEmpEducationdetails(EmpEducationDetailsEntity educatonentity);

}
