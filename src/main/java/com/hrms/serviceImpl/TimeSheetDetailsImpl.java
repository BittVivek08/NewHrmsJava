package com.hrms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.hrms.beans.EntityBeanResponse;
//import com.hrms.entity.EmployeeWorkStatusEntity;
import com.hrms.entity.SaveTimeSheet;
//import com.hrms.entity.ProjectTaskEntity;
//import com.hrms.entity.SavingTimeSheet;
//import com.hrms.entity.Task;
import com.hrms.repository.EmployeeRepository;
//import com.hrms.repository.EmployeeWorkStatusRepo;
import com.hrms.repository.SaveTimeSheetRepo;
import com.hrms.service.TimeSheetDetails;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@ComponentScan({"com.hrms.repository","com.hrms.entity.TimeSheetApprovalStatus"})
public class TimeSheetDetailsImpl implements TimeSheetDetails {
	
	/*
	 * @Autowired(required = true) private EmployeeDetails employeeDetails;
	 */
	     @Autowired
	     private  EntityBeanResponse  entityBeanResponse;
//	   @Autowired
//      private EntityBeanResponse Response;
//	   @Autowired
//	   private CommonResponseBean timeSheetRes;
//	   @Autowired
//	   private EmployeeWorkStatusEntity empWorkStatus;
	   @Autowired
	   private SaveTimeSheet timeSheet;
////	   @Autowired
////	   private SimpleDateFormat dateFormat;
//	   @Autowired
//	   private Task tmTask;
//	   @Autowired
//	   private ProjectTaskEntity projTask;

	   //Repo
//	   @Autowired
//		private ProjectTaskEntityRepo projectTaskEntityRepo;
//		@Autowired
//		private SavingTimeSheetRepo SavingTimeSheetRepo;
//		@Autowired
//		private TaskRepo taskRepo;
//		@Autowired
//	    private EmployeeWorkStatusRepo  employeeWorkStatusRepo;
	    @Autowired
		private EmployeeRepository employeeRepository;
		@Autowired
	  private SaveTimeSheetRepo hrmsEntityRepo;
		/*
		 * @Autowired private TimeSheetApprovalStatus timeSheetApprovalStatus;
		 */
//	@Override
//	public void getMonthlyDataInTimeSheet(int userid, String monthStartDate, String monthEndDate) {
//		
//	}
//
//	public SavingTimeSheet getTimeSheet(Integer id) {
//		return this.SavingTimeSheetRepo.findById(id).get();
//		
//	}
//
//	public void deleteUser(int id) {
//		
//		this.SavingTimeSheetRepo.deleteById(id);
//	}

	public SaveTimeSheet saveTimeSheett(SaveTimeSheet hrmss) {
		int cal=Integer.parseInt(hrmss.getFri_hours())+Integer.parseInt(hrmss.getMon_hours())+Integer.parseInt(hrmss.getSat_hours())+Integer.parseInt(hrmss.getThurs_hours())+Integer.parseInt(hrmss.getSun_hours())+Integer.parseInt(hrmss.getTue_hours())+Integer.parseInt(hrmss.getWed_hours());
		hrmss.setTotalWeekHours(String.valueOf(cal));
		hrmss.setStatus("pending");
//		hrmss.setTotalHoursWork(cal); 
//	  String name=(employeeRepository.empFirstName(hrmss.getEmpId()));
//    hrmss.setEmpname(name);
		SaveTimeSheet  result=  this.hrmsEntityRepo.save(hrmss);
	  
			
			return hrmss;
		}
		  
		
	
	
public void deleteTimeSheet(int id) {
		this.hrmsEntityRepo.deleteById(id);
	}
public SaveTimeSheet getTimeSheetDetail(int id) {
	return this.hrmsEntityRepo.findById(id).get();
}

public void updateTimeSheet(SaveTimeSheet hrmss, int id) {
	int cal=Integer.parseInt(hrmss.getFri_hours())+Integer.parseInt(hrmss.getMon_hours())+Integer.parseInt(hrmss.getSat_hours())+Integer.parseInt(hrmss.getThurs_hours())+Integer.parseInt(hrmss.getSun_hours())+Integer.parseInt(hrmss.getTue_hours())+Integer.parseInt(hrmss.getWed_hours());
	hrmss.setTotalWeekHours(String.valueOf(cal));
//	hrmss.setTotalHoursWork(cal);
//	 String name=(employeeRepository.empFirstName(hrmss.getEmpId()));
//     
//	  hrmss.setEmpname(name);
		  hrmss.setId(id);
		  hrmss.setStatus("pending");
		  this.hrmsEntityRepo.save(hrmss);
   
}







public SaveTimeSheet getTimeSheetDetails(int month, int year, int calweek,int id) {

	return this.hrmsEntityRepo.GetAllDetails(month, year, calweek,id);

//return k;
}




public List<Object[]> getTimeSheetDetailsByMonth(int month,int empid,int year) { 
	  
	return this.hrmsEntityRepo.getDetailsByMon(month, empid, year);
	
}




/*public TimeSheetApprovalStatus timeSheetApproval(int empid) {
	log.info("entered into timeSheetApproval Method of TimeSheetDetails BusinessClass..");
		String status1=hrmsEntityRepo.getStatus(empid);
		if(status1=="Approve") {
timeSheetApprovalStatus.setTimeSheetId(hrmsEntityRepo.getTimeSheetId(empid));
timeSheetApprovalStatus.setApproverName("Nilesh");
		}else {
			System.out.println("wrong id");
		}
return timeSheetApprovalStatus;*/
	

//public List<SaveTimeSheet> getEmpByReportingId(int repId) {
//	
//	return this.hrmsEntityRepo.getDetailByRepId(repId);
//}
	}
	
	


