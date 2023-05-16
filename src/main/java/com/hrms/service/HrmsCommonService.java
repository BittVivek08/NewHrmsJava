package com.hrms.service;

import java.util.List;
import java.util.Optional;

import com.hrms.beans.CommonResponseBean;
import com.hrms.entity.GenderEntity;
import com.hrms.entity.JobTitlesEntity;
import com.hrms.entity.MaritalStatusEntity;
import com.hrms.entity.NationalityEntity;
import com.hrms.entity.SalaryAccountClassTypeEntity;
import com.hrms.entity.SalaryCurrencyEntity;
import com.hrms.request.bean.JobTitleBean;
import com.hrms.request.bean.PersonalGenderBean;
import com.hrms.request.bean.PersonalMaritalStatusBean;
import com.hrms.request.bean.PersonalNationalityBean;
import com.hrms.request.bean.SalaryAccountClassTypeRequestBean;
import com.hrms.request.bean.SalaryCurrencyRequestBean;


public interface HrmsCommonService {
	
	public CommonResponseBean saveSalaryCurrency(SalaryCurrencyRequestBean salaryCurrencyReqBean);
	
	public List<SalaryCurrencyEntity> getAllSalaryCurrency();
	
	public CommonResponseBean saveSalaryAccountType(SalaryAccountClassTypeRequestBean accountTypeReqBean);
	
	public List<SalaryAccountClassTypeEntity> getAllAccountType();
	
	public CommonResponseBean saveJobTitle(JobTitleBean bean);
	
	public Optional<JobTitlesEntity> getById(int id);
	
	public List<JobTitlesEntity> getAllDetails();
	
	public CommonResponseBean deleteById(int id);
	
	public CommonResponseBean updateByid(int id, JobTitleBean bean);
	
	public CommonResponseBean saveGenderDetails(PersonalGenderBean genderbean);
	
	public Optional<GenderEntity> getByGenderId(int id);
	
	public List<GenderEntity> getAllGenders();
	
	public CommonResponseBean deleteByGenderId(int id);
	
	public CommonResponseBean updateByGenderId(int id, PersonalGenderBean genderBean);
	
	public CommonResponseBean saveMaritialDetails(PersonalMaritalStatusBean maritalbean);
	
	public Optional<MaritalStatusEntity> getByMaritalId(int id);
	
	public List<MaritalStatusEntity> getAllMaritalStatus();
	
	public CommonResponseBean deleteByMaritalId(int id);
	
	public CommonResponseBean updateByMaritalId(int id, PersonalMaritalStatusBean maritalBean);

	public CommonResponseBean saveNationality(PersonalNationalityBean nationalityBean);
	
	public Optional<NationalityEntity> getByNationalityId(int id);
	
	public List<NationalityEntity> getAllNationality();
	
	public CommonResponseBean deleteByNatioalityId(int id);
	
	public CommonResponseBean updateByNationaliyId(int id, PersonalNationalityBean nationalityBean);

}
