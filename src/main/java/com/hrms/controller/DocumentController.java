


package com.hrms.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.beans.DocResponseBean;
import com.hrms.entity.EmployeeDocumentsEntity;
import com.hrms.repository.EmployeeDocumentsRepository;
import com.hrms.response.bean.EmployeeDocumentResponse;
import com.hrms.response.bean.SuccessResponseBean;
import com.hrms.serviceImpl.EmployeeDocumentServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/file")
public class DocumentController {

	@Autowired
	private EmployeeDocumentServiceImpl empDocServ;

	@Autowired
	private EmployeeDocumentsRepository empDocRepo;

	@PostMapping("/uploadDocuments/{empId}")
	public SuccessResponseBean uploadDocuments(@RequestParam("file") MultipartFile file,
			@RequestBody EmployeeDocumentsEntity entity, @PathVariable("empId") String empId) throws IOException {
		log.info("upload documentFile api");
		return empDocServ.uploadDocuments(file, entity, empId);
	}

	@GetMapping("/fileName/{fileName}")
	public ResponseEntity<?> getFileName(@PathVariable String fileName) {
		log.info("get the documents by using fileName no extention api");

		Optional<EmployeeDocumentsEntity> dbData = empDocRepo.findByFileName(fileName);
		// Determine the media type based on the file extension
		MediaType mediaType = empDocServ.getMediaTypeForFileName(dbData.get().getDocumentName());

		byte[] fileData = empDocServ.getDocumentFile(fileName);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);

		//			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//	        headers.set("Content-Type", "application/msword");
		//	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document.doc");

		headers.setContentDispositionFormData(fileName, fileName);

		ByteArrayResource resource = new ByteArrayResource(fileData);

		return ResponseEntity.ok().headers(headers).body(resource);

	}

	@GetMapping("/getallfiles/{empId}")
	public List<EmployeeDocumentResponse> getFilesByEmpId(@PathVariable String empId) {
		log.info("get all files by empId documents api");
		List<EmployeeDocumentResponse> fileNameByEmpId = empDocServ.getFileNameByEmpId(empId);
		return fileNameByEmpId;
	}

	@PutMapping("/updateDocuments/{id}")
	public SuccessResponseBean updateDocuments(@RequestParam("file") MultipartFile file,
			@RequestBody EmployeeDocumentsEntity entity, @PathVariable("id") int id) throws IOException {

		return empDocServ.updateDocument(file, entity, id);
	}

	@DeleteMapping("/employee/{empId}/documents/{fileName}")
	public SuccessResponseBean deleteEmployeeDocument(@PathVariable String empId, @PathVariable String fileName) {
		return empDocServ.deleteFileNameByEmpId(empId, fileName);
	}

	@GetMapping("/downloadfile/{empId}/{fileName}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String empId, @PathVariable String fileName)
			throws FileNotFoundException {

		// Retrieve the file from the database based on empId and documentFilename
		byte[] fileData = empDocServ.downloadDocumentFile(empId, fileName);

		if (fileData != null) {
			// Create a ByteArrayResource from the file data
			ByteArrayResource resource = new ByteArrayResource(fileData);

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
					.contentLength(fileData.length).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/documentName/{documentName}")
	public ResponseEntity<?> getDocumentFile(@PathVariable String documentName) {
		log.info("get the document api");

		// Determine the media type based on the file extension
		MediaType mediaType = empDocServ.getMediaTypeForFileName(documentName);

		byte[] fileData = empDocServ.getDocumentNameFile(documentName);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);

		//			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//	        headers.set("Content-Type", "application/msword");
		//	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document.doc");

		headers.setContentDispositionFormData(documentName, documentName);

		ByteArrayResource resource = new ByteArrayResource(fileData);

		return ResponseEntity.ok().headers(headers).body(resource);

	}

}