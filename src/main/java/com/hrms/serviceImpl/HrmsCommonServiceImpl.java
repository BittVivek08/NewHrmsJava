package com.hrms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.CommonResponseBean;
import com.hrms.entity.SalaryAccountClassTypeEntity;
import com.hrms.entity.SalaryCurrencyEntity;
import com.hrms.repository.SalaryAccountClassTypeRepo;
import com.hrms.repository.SalaryCurrencyRepo;
import com.hrms.request.bean.SalaryAccountClassTypeRequestBean;
import com.hrms.request.bean.SalaryCurrencyRequestBean;
import com.hrms.service.HrmsCommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HrmsCommonServiceImpl implements HrmsCommonService {

	@Autowired
	private SalaryCurrencyRepo salCurrRepo;
	
	@Autowired
	private SalaryAccountClassTypeRepo accTypeRepo;

	@Autowired
	private CommonResponseBean comResBean;

	@Override
	public CommonResponseBean saveSalaryCurrency(SalaryCurrencyRequestBean salaryCurrencyReqBean) {
		log.info("Entered into saving salaryCurrency  serviceImpl method");
		SalaryCurrencyEntity entity = new SalaryCurrencyEntity();
		
		try {
			
		entity.setCurrencyName(salaryCurrencyReqBean.getCurrencyName());
		entity.setCurrencyCode(salaryCurrencyReqBean.getCurrencyCode());
		entity.setDiscription(salaryCurrencyReqBean.getDiscription());
		entity.setIsActive(salaryCurrencyReqBean.getIsActive());
		entity.setCreatedDate(salaryCurrencyReqBean.getCreatedDate());
		entity.setModifiedDate(salaryCurrencyReqBean.getModifiedDate());
		
		SalaryCurrencyEntity saved = salCurrRepo.save(entity);

		if (saved != null) {
			comResBean.setMsg("Salry Currency Added Successfully ");
			comResBean.setStatus(true);
		}
		}catch (Exception e) {
			e.printStackTrace();
		
			comResBean.setMsg("Salary currency Addition Failed : " + e.getMessage() );
			comResBean.setStatus(false);
		}
		
		return comResBean;
		
	}

	@Override
	public List<SalaryCurrencyEntity> getAllSalaryCurrency() {
		log.info("Fething all salary currency");

		return salCurrRepo.findAll();
	}

	@Override
	public CommonResponseBean saveSalaryAccountType(SalaryAccountClassTypeRequestBean accountTypeReqBean) {
		log.info("Entered into Saving Salary AccountType method");
		SalaryAccountClassTypeEntity entity = new SalaryAccountClassTypeEntity();
		try {
			entity.setAccountClassType(accountTypeReqBean.getAccountClassType());
			entity.setDiscription(accountTypeReqBean.getDiscription());
			entity.setIsActive(accountTypeReqBean.getIsActive());
			entity.setCreatedDate(accountTypeReqBean.getCreatedDate());
			entity.setModifiedDate(accountTypeReqBean.getModifiedDate());
			
			SalaryAccountClassTypeEntity saved = accTypeRepo.save(entity);
			
			if(saved != null) {
				comResBean.setMsg("Account Type Saved Successfully");
				comResBean.setStatus(true);
			}
		}catch (Exception e){
			e.printStackTrace();
			comResBean.setMsg("Account Type Saving Failed : " + e.getMessage());
			comResBean.setStatus(false);
		}
		return comResBean;
	}

	@Override
	public List<SalaryAccountClassTypeEntity> getAllAccountType() {
		log.info("Fetching All Account Type");
		return accTypeRepo.findAll();
	}

}
