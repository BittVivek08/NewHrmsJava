package com.hrms.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.AnnouncementBean;
import com.hrms.entity.CompanyAnnouncement;
import com.hrms.repository.AnnouncementRepo;

@Service
public class CompanyAnnouncementServiceImpl   implements CompanyAnnouncementService {
	@Autowired
	private AnnouncementRepo announcementrepo;

	@Autowired
	private AnnouncementBean announcementbean;
	


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
        	return null;
        }
        
		return an;
	
	}
	
}








//	@Override
//	public List<CompanyAnnouncement> getAllAnnouncements() {
//		
//		return announcementrepo.findAll();
//	}
//
//
//	@Override
//	public CompanyAnnouncement getempById(Integer empid) {
//Optional<CompanyAnnouncement> findById =announcementrepo.findById(empid);
//		
//		if(findById.isPresent()) {
//			return findById.get();
//			
//		}
//		else {
//			System.out.println("invalid empid");
//		}
//			return null;
//		
//	}
//
//	@Override
//	public AnnouncementBean deleteannoun(int empid) {
//		
//		
//		
//		announcementrepo.deleteById(empid);
//
//		announcementbean.setMessage("");
//		announcementbean.setStatus(true);
//		
//	
//		
//		return announcementbean;
//	}
//	
//}
//		
//	





