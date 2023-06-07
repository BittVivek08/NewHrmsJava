package com.hrms.serviceImpl;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.AnnouncementBean;
import com.hrms.entity.CompanyAnnouncement;
import com.hrms.repository.AnnouncementRepo;
import com.hrms.service.CompanyAnnouncementService;

@Service
public class CompanyAnnouncementServiceImpl   implements CompanyAnnouncementService {
	Logger logging = LoggerFactory.getLogger(CompanyAnnouncementServiceImpl.class);
	@Autowired
	private AnnouncementRepo announcementrepo;

	@Autowired
	private AnnouncementBean announcementbean;
	
	@Override
	public AnnouncementBean saveAnnouncementDetails(CompanyAnnouncement companyannouncement) {
		logging.info("entered saveAnnouncementsDetails method in service class implementation");
		CompanyAnnouncement save = announcementrepo.save(companyannouncement);
		if(save !=null ) {
			announcementbean.setMessage("success");
			announcementbean.setStatus(true);
		}else {
			announcementbean.setMessage("fail");
			announcementbean.setStatus(false);
		}
		return announcementbean;

	}
	@Override
	public List<CompanyAnnouncement> Announcements() {
		logging.info("entered Announcements method for fetching Announcents in service class implementation");
		List<CompanyAnnouncement> an =announcementrepo.getCurrentAnnouncementDetails();
		if(an.isEmpty()) {
        	return new ArrayList<CompanyAnnouncement>();
        }
		return an;

	}

	public AnnouncementBean updateAnnouncement(int id, CompanyAnnouncement announcement) {
		
		logging.info("entered updateAnnouncement method in service class implementation");
		java.util.Optional<CompanyAnnouncement> CompanyOptional = announcementrepo.findById(id);
		try {
	    if (CompanyOptional.isPresent()) {
	   
	    	CompanyAnnouncement details = CompanyOptional.get();
	        
	    	details.setDescription(announcement.getDescription());
	    	details.setEnddate(announcement.getEnddate());
	    	details.setStartdate(announcement.getStartdate());
	    	details.setSubject(announcement.getSubject());
	    	details.setAnnouncementname(announcement.getAnnouncementname());
	    	details.setAnnouncementtime(announcement.getAnnouncementtime());
	    	details.setDepartmentid(announcement.getDepartmentid());
	    	details.setDeleted(announcement.getDeleted());
	    	details.setEmployeeuserid(announcement.getEmployeeuserid());
	    	details.setBusinessunitid(announcement.getBusinessunitid());
	        announcementrepo.save(details);
	        announcementbean.setMessage("announcement details updated succefully");
	        announcementbean.setStatus(true);
	        
	        return announcementbean;
	
	        
	    }
	    else {
	    	announcementbean.setMessage("inavalied id");
	    	announcementbean.setStatus(false);
	    	return announcementbean;
	    }
	    
		}
	    catch(Exception e) {
	    	e.printStackTrace();
	  
		
	}
		return announcementbean;
	
	}
	
	public AnnouncementBean deleteannoun(int id) {
		logging.info("entered deleteannoun method in service class implementation");
		CompanyAnnouncement bean = this.announcementrepo.getById(id);
		if(bean!=null) {
			this.announcementrepo.delete(bean);
			announcementbean.setMessage("announcement details deleted successfully");
			announcementbean.setStatus(true);
		} else {
			announcementbean.setMessage("Failed to  Delete  announcement details ");
			announcementbean.setStatus(false);
		}
		return announcementbean;
		
		
	}
}






