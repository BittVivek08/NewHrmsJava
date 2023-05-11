package com.hrms.service;

import java.util.List;

import com.hrms.beans.CommonResponseBean;
import com.hrms.entity.SalaryAccountClassTypeEntity;
import com.hrms.entity.SalaryCurrencyEntity;
import com.hrms.request.bean.SalaryAccountClassTypeRequestBean;
import com.hrms.request.bean.SalaryCurrencyRequestBean;

public interface HrmsCommonService {
	
	public CommonResponseBean saveSalaryCurrency(SalaryCurrencyRequestBean salaryCurrencyReqBean);
	
	public List<SalaryCurrencyEntity> getAllSalaryCurrency();
	
	public CommonResponseBean saveSalaryAccountType(SalaryAccountClassTypeRequestBean accountTypeReqBean);
	
	public List<SalaryAccountClassTypeEntity> getAllAccountType();

}
