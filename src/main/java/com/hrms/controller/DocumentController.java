package com.hrms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.hrms.entity.EmployeeDocumentsEntity;
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
	
	@PostMapping("/uploadDocuments/{empId}")
	public SuccessResponseBean uploadDocuments(@RequestParam("file") MultipartFile file,@RequestBody EmployeeDocumentsEntity entity,@PathVariable("empId") String empId) throws IOException {
		log.info("upload documents api");
		return empDocServ.uploadDocuments(file,entity,empId);
	}
	
	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		log.info("get the document api");
		byte[] fileData=empDocServ.downloadFile(fileName);
		
        // Determine the media type based on the file extension
        MediaType mediaType = empDocServ.getMediaTypeForFileName(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
//        headers.set("Content-Type", "application/msword");
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document.doc");
        headers.setContentDispositionFormData(fileName, fileName);

        ByteArrayResource resource = new ByteArrayResource(fileData);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);

	}

	 @GetMapping("/getallfiles/{empId}")
	    public List<EmployeeDocumentResponse> getFilesByEmpId(@PathVariable String empId) {
		 
		 
//	        List<byte[]> fileDataList = empDocServ.getFilesByEmpId(empId);
	        
//	        // Find the file data matching the provided fileName
//	        byte[] fileData = fileDataList.stream()
//	                .filter(data -> empDocServ.getFileName(data).equals(fileName))
//	                .findFirst()
//	                .orElse(null);
//
//	        if (fileData == null) {
//	            // Handle the case when the file is not found
//	            return ResponseEntity.notFound().build();
//	        }

//	        MediaType mediaType = empDocServ.getMediaTypeForFileName(fileName);
//
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(mediaType);
//	        headers.setContentDispositionFormData(fileName, fileName);
//
//	        ByteArrayResource resource = new ByteArrayResource(fileData);
//
//	        return ResponseEntity.ok()
//	                .headers(headers)
//	                .body(resource);
		 
		 List<EmployeeDocumentResponse> fileNameByEmpId = empDocServ.getFileNameByEmpId(empId);
	        return fileNameByEmpId;
	    }

}
