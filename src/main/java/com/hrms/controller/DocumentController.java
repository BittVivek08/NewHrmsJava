package com.hrms.controller;

import java.io.IOException;
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
import com.hrms.response.bean.SuccessResponseBean;
import com.hrms.serviceImpl.EmployeeDocumentServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/file")
public class DocumentController {
	
	@Autowired
	private EmployeeDocumentServiceImpl empDocServ;
	
	@PostMapping("/uploadDocuments/{empId}")
	public SuccessResponseBean uploadDocuments(@RequestParam("file") MultipartFile file,@RequestBody EmployeeDocumentsEntity entity,@PathVariable("empId") String empId) throws IOException {
		
		return empDocServ.uploadDocuments(file,entity,empId);
	}
	
	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] fileData=empDocServ.downloadFile(fileName);
		
        // Determine the media type based on the file extension
        MediaType mediaType = empDocServ.getMediaTypeForFileName(fileName);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDispositionFormData(fileName, fileName);

        ByteArrayResource resource = new ByteArrayResource(fileData);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);

	}


}
