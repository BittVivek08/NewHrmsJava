package com.hrms.service;

import java.util.List;

import com.hrms.beans.DocResponseBean;
import com.hrms.entity.VisaDocumentsEntity;

public interface VisaDocService {
	
	public List<VisaDocumentsEntity> getAllDoc();
	
	public DocResponseBean getDocName(String empId);
	
	public VisaDocumentsEntity getDocById(Integer id);

}
