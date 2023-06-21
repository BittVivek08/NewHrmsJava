package com.hrms.controller;

import java.util.List;

import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.DocResponseBean;
import com.hrms.entity.VisaDocumentsEntity;
import com.hrms.service.VisaDocService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/visaDoc")
public class VisaDocController {
	
	@Autowired
	private VisaDocService visaDocService;
	
	@GetMapping("/getAllListOfDoc")
	public ResponseEntity<List<VisaDocumentsEntity>> getAllListOfDoc(){
		log.info("Class : "+this.getClass().getName());
		log.info("Method :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		List<VisaDocumentsEntity> allDoc = visaDocService.getAllDoc();
		return new ResponseEntity<>(allDoc,HttpStatus.OK);
	}
	
	@GetMapping("/getDocNameByEmpId")
	public DocResponseBean getDocNameByEmpId(@QueryParam(value="empId") String empId){
		log.info("Class : "+this.getClass().getName());
		log.info("Method :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		
	return visaDocService.getDocName(empId);
		 
	}
	@GetMapping("/getVisaNameById")
	public VisaDocumentsEntity getDocNameById(@QueryParam (value = "id") Integer id) {
		log.info("Class : "+this.getClass().getName());
		log.info("Method :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		return visaDocService.getDocById(id);
	}

}
