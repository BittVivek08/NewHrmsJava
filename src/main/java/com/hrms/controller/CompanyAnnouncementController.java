
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



@RestController
@RequestMapping("/announcement")
@CrossOrigin
public class CompanyAnnouncementController {

	@Autowired
	private CompanyAnnouncementServiceImpl companyannouncementservice;

	@PostMapping("/saveAnnouncemetdetails")
	public AnnouncementBean saveAnnouncementDetails(@RequestBody CompanyAnnouncement announcement) {

		return companyannouncementservice.saveAnnouncementDetails(announcement);
	}
	
	@GetMapping("/getAnnouncements")
	public List<CompanyAnnouncement> getAnnouncementsBetweendates()
	{
		List<CompanyAnnouncement> an =  companyannouncementservice.Announcements();
		//System.out.println(an.get(0).getDescription());
		return an;
 }
	
	@PutMapping("/updateAnnouncementDetails/{id}")    
	 public ResponseEntity<AnnouncementBean> updateAnnouncement(@PathVariable(value = "id") int id, @RequestBody CompanyAnnouncement announcement) {
		 AnnouncementBean updatedannouncent =companyannouncementservice.updateAnnouncement(id, announcement);
	        
	        return ResponseEntity.ok(updatedannouncent);
	    }
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AnnouncementBean> deleteById(@PathVariable("id") int id) {
		AnnouncementBean deleteannouncement=companyannouncementservice.deleteannoun(id);
		
		return new ResponseEntity<>(deleteannouncement,HttpStatus.OK);
	}
}




