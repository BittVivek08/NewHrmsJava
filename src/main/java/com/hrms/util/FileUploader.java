package com.hrms.util;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import java.io.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


@Slf4j
@Component
public class FileUploader {
	
	// save uploaded file to new location
	private boolean writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		log.info("writeToFile method of File Uploader class");
		try {
			File delFile = new File(uploadedFileLocation);
			if (delFile.exists()) {
				delFile.delete();
			}
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Catch block of writeToFile" + e);
			return false;
		}
	}

	// Create Save Path for Uploads
//	public String getUploadPath(int userId, String fileName, InputStream uploadedInputStream) {
//		String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "HRMS_Uploads"
//				+ File.separator + "emp_" + userId;
//		logger.info("File Path Creating...");
//		File pathExist = new File(path);
//		if (!pathExist.exists()) // Create Path if Not Exists
//			pathExist.mkdirs();
//		if (writeToFile(uploadedInputStream, path + File.separator + fileName)) {
//			String uploadPath = "/HRMS_Uploads/emp_" + userId + "/" + fileName;
	//		logger.info("upload Path Created...");
	//		return uploadPath;
	//	} else {
	//		logger.error("upload Path Creation Failed...");
	//		return "";
	//	}
//	}
	
	// Create save path for upload(vivek)
	
	public String getUploadPath(int userId, String fileName, InputStream uploadedInputStream) {
		String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "HRMS_Uploads"
				+ File.separator + "emp_" + userId;
		log.info("File Path Creating...");
		try {
		File file = new File(path);
		if(!file.exists())
			file.mkdirs();
		if (writeToFile(uploadedInputStream, path + File.separator + fileName)) {
			String uploadPath = "/HRMS_Uploads/emp_" + userId + "/" + fileName;
			// fetching pdf content
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);
			document.close();
			log.info("upload path created..");
			return uploadPath;
			
		}else {
			log.error("upload Path Creation Failed...");
			
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}

	// Create Save Path for Uploads Resume
	public String getUploadResumePath(int applyId, String fileName, InputStream uploadedInputStream) {
		String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "HRMS_Uploads"
				+ File.separator + "External_user" + applyId;
		log.info("File Path Creating...");
		File pathExist = new File(path);
		if (!pathExist.exists()) // Create Path if Not Exists
			pathExist.mkdirs();
		if (writeToFile(uploadedInputStream, path + File.separator + fileName)) {
			String uploadResumePath = "/HRMS_Uploads/External_user" + applyId + "/" + fileName;
			log.info("upload Path Created...");
			return uploadResumePath;
		} else {
			log.error("upload Path Creation Failed...");
			return "";
		}
	}

	// post profile Uploading Resume path creation....
	public String getUploadPostProfilePath(int id, String fileName, InputStream uploadedInputStream) {
		String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "HRMS_Uploads"
				+ File.separator + "post_Profile" + id;
		log.info("file path created Successfully");
		File pathExist = new File(path);
		if (!pathExist.exists())
			pathExist.mkdirs();
		if (writeToFile(uploadedInputStream, path + File.separator + fileName)) {
			String uploadpostprofile = "/HRMS_Uploads/post_Profile" + id + "/" + fileName;
			log.info("post profile Uploaded into this path");
			return uploadpostprofile;
		} else {
			log.error("Upload Creation Path Failed....");
			return "";
		}
	}
}
