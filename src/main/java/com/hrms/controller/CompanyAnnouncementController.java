
package com.hrms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.beans.AnnouncementBean;
import com.hrms.entity.CompanyAnnouncement;
import com.hrms.serviceImpl.CompanyAnnouncementServiceImpl;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/announcement")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyAnnouncementController {

	@Autowired
	private CompanyAnnouncementServiceImpl companyannouncementservice;

	@PostMapping("/saveAnnouncemetdetails")
	public AnnouncementBean saveAnnouncementDetails(@RequestBody CompanyAnnouncement announcement) {
		log.info("entered into saveAnnouncementDetails method in controller");
		return companyannouncementservice.saveAnnouncementDetails(announcement);
	}
	
	@GetMapping("/getAnnouncements")
	public List<CompanyAnnouncement> getAnnouncementsBetweendates()
	{
		log.info("entered into getAnnouncemtsBetweendates method in controller");
		List<CompanyAnnouncement> an =  companyannouncementservice.Announcements();
		log.info("succesfully fetched announcemnt details into controller");
		//System.out.println(an.get(0).getDescription());
		return an;
 }
	
	@PutMapping("/updateAnnouncementDetails/{id}")    
	 public ResponseEntity<AnnouncementBean> updateAnnouncement(@PathVariable(value = "id") int id, @RequestBody CompanyAnnouncement announcement) {
		log.info("entered into updateAnnouncement method into controller");
		 AnnouncementBean updatedannouncent =companyannouncementservice.updateAnnouncement(id, announcement);
	        log.info("successfully updated announcements in controller");
	        return ResponseEntity.ok(updatedannouncent);
	    }
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AnnouncementBean> deleteById(@PathVariable("id") int id) {
		log.info("entered into deletebyid method of controller");
		AnnouncementBean deleteannouncement=companyannouncementservice.deleteannoun(id);
		log.info("successfully announcement details are deleted");
		return new ResponseEntity<>(deleteannouncement,HttpStatus.OK);
	}
}

