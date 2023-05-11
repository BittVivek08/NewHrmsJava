package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.CommonResponseBean;
import com.hrms.entity.SalaryAccountClassTypeEntity;
import com.hrms.entity.SalaryCurrencyEntity;
import com.hrms.request.bean.SalaryAccountClassTypeRequestBean;
import com.hrms.request.bean.SalaryCurrencyRequestBean;
import com.hrms.service.HrmsCommonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/common")
public class CommonServiceController {

	@Autowired
	private HrmsCommonService hrmsCommomService;

	@PostMapping("/addCurrency")
	public CommonResponseBean saveSalaryCurrency(@RequestBody SalaryCurrencyRequestBean salaryCurrencyReqBean) {
		log.info("Saving Salary Currency Details");
		return hrmsCommomService.saveSalaryCurrency(salaryCurrencyReqBean);
	}

	@GetMapping("/getAllCurrency")
	public ResponseEntity<List<SalaryCurrencyEntity>> getAllCurrencyDetails() {
		log.info("Fetching all Currency Details");
		List<SalaryCurrencyEntity> allSalaryCurrency = hrmsCommomService.getAllSalaryCurrency();
		return new ResponseEntity<>(allSalaryCurrency, HttpStatus.OK);
	}
	
	@PostMapping("/addAccountType")
	public CommonResponseBean saveSalaryAccountType(@RequestBody SalaryAccountClassTypeRequestBean salaryAccountTypeReqBean) {
		log.info("Saving salary Account Type Method");
		return hrmsCommomService.saveSalaryAccountType(salaryAccountTypeReqBean);
	}
	
	@GetMapping("/getAllAccountType")
    public ResponseEntity<List<SalaryAccountClassTypeEntity>> getAllAccountType(){
    	log.info("Fetching All Account Type");
    	List<SalaryAccountClassTypeEntity> allAccountType = hrmsCommomService.getAllAccountType();
    	return new ResponseEntity<>(allAccountType, HttpStatus.OK);
    }
}
