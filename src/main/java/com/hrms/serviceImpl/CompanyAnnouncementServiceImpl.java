package com.hrms.serviceImpl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.AnnouncementBean;
import com.hrms.entity.CompanyAnnouncement;
import com.hrms.repository.AnnouncementRepo;
import com.hrms.service.CompanyAnnouncementService;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class CompanyAnnouncementServiceImpl   implements CompanyAnnouncementService {
	@Autowired
	private AnnouncementRepo announcementrepo;

	@Autowired
	private AnnouncementBean announcementbean;
	
	@Autowired
	CompanyAnnouncement companyanoun;


	@Override
	public AnnouncementBean saveAnnouncementDetails(CompanyAnnouncement companyannouncement) {

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
		
		List<CompanyAnnouncement> an =announcementrepo.getCurrentAnnouncementDetails();
		if(an.isEmpty()) {
        	return new ArrayList<CompanyAnnouncement>();
        }
		return an;

	}

	public AnnouncementBean updateAnnouncement(int id, CompanyAnnouncement announcement) {
		
		
		java.util.Optional<CompanyAnnouncement> CompanyOptional = announcementrepo.findById(id);
		try {
	    if (CompanyOptional.isPresent()) {
	   
	    	CompanyAnnouncement details = CompanyOptional.get();
	        
	    	details.setDescription(announcement.getDescription());
	    	details.setEnddate(announcement.getEnddate());
	    	details.setStartdate(announcement.getStartdate());
	    	details.setSubject(announcement.getSubject());
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






