package com.hrms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.DocResponseBean;
import com.hrms.entity.VisaDocumentsEntity;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.VisaDocRepository;
import com.hrms.service.VisaDocService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VisaDocServiceImpl implements VisaDocService {

	@Autowired
	private VisaDocRepository visaDocRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<VisaDocumentsEntity> getAllDoc() {

		return visaDocRepo.findAll();
	}

	@Override
	public DocResponseBean getDocName(String empId) {
		log.info("Class : "+this.getClass().getName());
		log.info("Method :" + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		DocResponseBean response = new DocResponseBean();

		List<String> documentsIds = new ArrayList<>();
		String visaDocById = empRepo.getSelectedDocumentName(empId);
		String[] split = visaDocById.split(",");
		for(String documentId  : split) {
		
		String visaDocument = visaDocRepo.getVisaDocumentById(Integer.parseInt(documentId));
		documentsIds.add(visaDocument);
		}
		if (visaDocById != null) {
			response.setMessage("Doc list fetched successfully");
			response.setStatus(true);
			response.setListOfDocuments(documentsIds);
		} else {
			response.setMessage("Doc List Fetching Failed..!");
			response.setStatus(false);
		}
		return response;
	}

	@Override
	public VisaDocumentsEntity getDocById(Integer id) {
		Optional<VisaDocumentsEntity> findById = visaDocRepo.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

}
