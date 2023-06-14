package com.hrms.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeDocumentsEntity;
import com.hrms.repository.EmployeeDocumentsRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.response.bean.EmployeeDocumentResponse;
import com.hrms.response.bean.SuccessResponseBean;
import com.hrms.service.EmployeeDocumentService;
import com.hrms.util.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeDocumentServiceImpl implements EmployeeDocumentService {

	@Autowired
	private EmployeeDocumentsRepository empDocRepo;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private SuccessResponseBean response;

	@Override
	public SuccessResponseBean uploadDocuments(MultipartFile file, EmployeeDocumentsEntity docsEntity, String empId)
			throws IOException {
		log.info("upload document bussiness logic method");
		EmployeeDetails employee = this.employeeRepository.findByEmpId(empId);
		List<EmployeeDetails> employeeDetails = this.employeeRepository.findByUserId(docsEntity.getUserId());

		if (employee != null && employeeDetails != null && employeeDetails.size() > 0) {
			EmployeeDocumentsEntity docEntity = empDocRepo.save(EmployeeDocumentsEntity.builder()
					.documentName(file.getOriginalFilename()).data(FileUtils.compressImage(file.getBytes())).build());
			docEntity.setEmpdetails(employee);
			docEntity.setUserId(docsEntity.getUserId());
			docEntity.setIsactive(docsEntity.getIsactive());
			docEntity.setCreatedBy(docsEntity.getCreatedBy());
			docEntity.setDocumentId(docsEntity.getDocumentId());
			docEntity.setIsExpires(docsEntity.getIsExpires());
			docEntity.setDocumentFileName(docsEntity.getDocumentFileName());
			docEntity.setModifiedBy(docsEntity.getModifiedBy());
			docEntity.setCreatedBy(docsEntity.getCreatedBy());
			docEntity.setCreatedDate(docsEntity.getCreatedDate());
			docEntity.setFromDate(docsEntity.getFromDate());
			docEntity.setToDate(docsEntity.getToDate());
			docEntity.setModifiedDate(docsEntity.getModifiedDate());
			this.empDocRepo.save(docEntity);

			if (docEntity != null) {
				response.setMessage("uploaded successfully....");
				response.setStatus(true);
				return response;
			} else {
				response.setMessage("something is wrong....");
				response.setStatus(false);
				return response;
			}
		} else {
			response.setMessage("something is wrong....");
			response.setStatus(false);
			return response;
		}
	}

	@Override
	public byte[] downloadFile(String fileName) {
		log.info("get the document bussiness logic method");
		Optional<EmployeeDocumentsEntity> dbData = empDocRepo.findByDocumentFileName(fileName);
		byte[] images = FileUtils.decompressImage(dbData.get().getData());
		return images;
	}

	public boolean isFileNameMatch(String data, String fileName) {
		return data.equals(fileName);
	}

	@Override
	public MediaType getMediaTypeForFileName(String fileName) {
		log.info("get the mediaType checking bussiness logic method");
		String[] arr = fileName.split("\\.");
		String fileExtension = arr[arr.length - 1];

		switch (fileExtension.toLowerCase()) {
		case "png":
			return MediaType.IMAGE_PNG;
		case "pdf":
			return MediaType.APPLICATION_PDF;
		case "txt":
			return MediaType.TEXT_PLAIN;
		case "jpg":
			return MediaType.IMAGE_JPEG;
		case "doc":
			return MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//			return MediaType.APPLICATION_OCTET_STREAM;    
		default:
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}

	@Override
	public List<EmployeeDocumentResponse> getFileNameByEmpId(String empId) {
		log.info("get list of document filename by empId bussiness logic method");
		List<Object[]> result = empDocRepo.findByEmpId(empId);
		List<EmployeeDocumentResponse> response = new ArrayList<>();
		for (int i = 0; i < result.size(); i++) {
			Object[] obj = result.get(i);
			EmployeeDocumentResponse docResponse = new EmployeeDocumentResponse();
			docResponse.setEmpId(obj[1].toString());
			docResponse.setDocumentName(obj[0].toString());

			response.add(docResponse);
		}

		return response;
	}

	@Override
	public SuccessResponseBean updateDocument(MultipartFile file, EmployeeDocumentsEntity docsEntity, int id)
			throws IOException {
		log.info("update the document by empId bussiness logic method");
//		EmployeeDetails employee = this.employeeRepository.findByEmpId(empId);
		Optional<EmployeeDocumentsEntity> findById = empDocRepo.findById(id);
//		List<EmployeeDetails> employeeDetails = this.employeeRepository.findByUserId(docsEntity.getUserId());

		if (findById != null) {
			EmployeeDocumentsEntity docEntity = empDocRepo.save(EmployeeDocumentsEntity.builder()
					.documentName(file.getOriginalFilename()).data(FileUtils.compressImage(file.getBytes())).build());
//			docEntity.setEmpdetails(employee);
			docEntity.setUserId(docsEntity.getUserId());
			docEntity.setIsactive(docsEntity.getIsactive());
			docEntity.setCreatedBy(docsEntity.getCreatedBy());
			docEntity.setDocumentId(docsEntity.getDocumentId());
			docEntity.setIsExpires(docsEntity.getIsExpires());
			docEntity.setDocumentFileName(docsEntity.getDocumentFileName());
			docEntity.setModifiedBy(docsEntity.getModifiedBy());
			docEntity.setCreatedBy(docsEntity.getCreatedBy());
			docEntity.setCreatedDate(docsEntity.getCreatedDate());
			docEntity.setFromDate(docsEntity.getFromDate());
			docEntity.setToDate(docsEntity.getToDate());
			docEntity.setModifiedDate(docsEntity.getModifiedDate());
			this.empDocRepo.save(docEntity);

			if (docEntity != null) {
				response.setMessage("update document successfully....");
				response.setStatus(true);
				return response;
			} else {
				response.setMessage("something is wrong....");
				response.setStatus(false);
				return response;
			}
		} else {
			response.setMessage("something is wrong....");
			response.setStatus(false);
			return response;
		}
	}

	@Override
	public SuccessResponseBean deleteFileNameByEmpId(String empId, String fileName) {

		List<EmployeeDocumentsEntity> documents = empDocRepo.findByEmpIds(empId);

		// Find the document with the given file name
		Optional<EmployeeDocumentsEntity> documentOptional = documents.stream()
				.filter(doc -> doc.getDocumentFileName().equals(fileName)).findFirst();

		if (documentOptional.isPresent()) {
			EmployeeDocumentsEntity documentToDelete = documentOptional.get();

			// Delete the document
			empDocRepo.delete(documentToDelete);

			response.setMessage("File deleted successfully.");
			response.setStatus(true);
			return response;
		}

		response.setMessage("File not found.");
		response.setStatus(false);
		return response;
	}
}

/////////////////////BackUp////////////////////////////////////////////////
//public SuccessResponseBean uploadDocuments( MultipartFile file, EmployeeDocumentsEntity docsEntity,String eid) throws IOException {
//	
//	//EmployeeDocumentsEntity entity = new EmployeeDocumentsEntity();
//	
//	//EmployeeDetails details = employeeRepository.findByEmpId(entity.getEmpdetails().getEmpId());
//	EmployeeDetails employee = this.employeeRepository.findByEmpId(eid);
//	docsEntity.setEmpdetails(employee);
//	docsEntity.setUserId(docsEntity.getUserId());
//	EmployeeDocumentsEntity save = empDocRepo.save(docsEntity);
//	
//	if(save !=null) {
//	
//	EmployeeDocumentsEntity docEntity =empDocRepo.save(EmployeeDocumentsEntity.builder()
//			//empDocRepo.save(EmployeeDocumentsEntity.builder()
//			.documentName(file.getOriginalFilename())
//			.data(FileUtils.compressImage(file.getBytes())).build());
//	
//	if (docEntity != null) {
//		response.setMessage("uploaded successfully....");
//		response.setStatus(true);
//        return response;
//    }
//	}else {
//		response.setMessage("something is wrong....");
//		response.setStatus(false);
//        return response;
//	}
//	return response;
//}
