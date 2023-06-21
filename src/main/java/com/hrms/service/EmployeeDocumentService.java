package com.hrms.service;

import java.io.IOException;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.entity.EmployeeDocumentsEntity;
import com.hrms.response.bean.EmployeeDocumentResponse;
import com.hrms.response.bean.SuccessResponseBean;

public interface EmployeeDocumentService {

	SuccessResponseBean uploadDocuments( MultipartFile file, EmployeeDocumentsEntity docsEntity,String empId)throws IOException;

	MediaType getMediaTypeForFileName(String fileName);

	byte[] getDocumentFile(String fileName);

	List<EmployeeDocumentResponse> getFileNameByEmpId(String empId);

	SuccessResponseBean updateDocument( MultipartFile file, EmployeeDocumentsEntity docsEntity,int id)throws IOException;

	SuccessResponseBean deleteFileNameByEmpId(String empId,String fileName);

	byte[] downloadDocumentFile(String empId,String fileName);

	public byte[] getDocumentNameFile(String documentName);


}
