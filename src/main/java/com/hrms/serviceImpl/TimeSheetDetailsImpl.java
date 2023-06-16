package com.hrms.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.protobuf.Timestamp;
import com.hrms.entity.ClientDetailsEntity;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.ProjectDetailsEntity;
import com.hrms.entity.SaveTimeSheet;
import com.hrms.entity.TaskDetailsEntity;
import com.hrms.entity.WorkFlow;
import com.hrms.repository.ClientDetailsRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ProjectDetailsRepository;
import com.hrms.repository.ProjectEmployeeRepository;
import com.hrms.repository.SaveTimeSheetRepo;
import com.hrms.repository.TaskDeatailsRepository;
import com.hrms.repository.WorkFlowRepository;
import com.hrms.response.bean.ProjectListResponse;
import com.hrms.response.bean.ProjectResponse;
import com.hrms.response.bean.TimeSheetRequestRepDate;
import com.hrms.response.bean.TimeSheetResponse;
import com.hrms.service.TimeSheetDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TimeSheetDetailsImpl implements TimeSheetDetails {
	final String status = "pendding";
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SaveTimeSheetRepo saveTimeSheetRepo;
	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	@Autowired
	private ProjectDetailsRepository pojectDetailsRepository;
	@Autowired
	private TaskDeatailsRepository taskDetailsRepository;

	@Autowired
	private TimeSheetResponse timeSheetResponse;
	
	@Autowired
	private ProjectEmployeeRepository projectEmployeeRepository;
	
	@Autowired
	private WorkFlowRepository workFlowRepository;
	
		public TimeSheetResponse saveTimeSheett(List<SaveTimeSheet> savetimesheetList) {
		log.info("Entered into saveTimeSheet  method of HrmsEmpTimeSheetService class");
		try {
			
		
		for (SaveTimeSheet savetimesheet : savetimesheetList) {
			EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
			ClientDetailsEntity client = this.clientDetailsRepository.findById(savetimesheet.getClient().getId()).get();
			ProjectDetailsEntity proj = this.pojectDetailsRepository
					.findByProjectId(savetimesheet.getProj().getProjectId());
			TaskDetailsEntity task = this.taskDetailsRepository.findById(savetimesheet.getTask().getTaskid()).get();
			savetimesheet.setTask(task);
			savetimesheet.setProj(proj);
			savetimesheet.setClient(client);
			savetimesheet.setEmp(emp1);
			savetimesheet.setStatus(status);
			savetimesheet.setCreatedBy(savetimesheet.getCreatedBy());
			savetimesheet.setWorkHours(savetimesheet.getWorkHours());
			savetimesheet.setCreatedDate(new Date());
			savetimesheet.setWorkDate(savetimesheet.getWorkDate());
			SaveTimeSheet tm = this.saveTimeSheetRepo.save(savetimesheet);
			timeSheetResponse.setMsg("timesheet detail save successfully");
			timeSheetResponse.setStatus(true);

			Instant timestamp = Instant.now();
			WorkFlow bean = new WorkFlow();
			bean.setReqid(tm.getId());
			bean.setCreatedBy(emp1.getEmpId());
			bean.setCreatedDate(timestamp);
			bean.setEmpid(emp1.getEmpId());
			bean.setFeature("TimeSheet");
			LocalDate date= pojectDetailsRepository.enddate(proj.getProjectId());					
				System.out.println(date);
		  if(proj.getEnddate()!=LocalDate.now()){
			bean.setApprovalManagerId(emp1.getReportingManagerId());
		  }
		  else {
			  bean.setApprovalManagerId(projectEmployeeRepository.findAll().iterator().next().getEmployee().getReportingManagerId())	;
			  }
			bean.setStatus(status);
          this.workFlowRepository.save(bean);
		}} catch (Exception e) {
		e.printStackTrace();
		}
		return timeSheetResponse;
	}

	public List<SaveTimeSheet> getTimeSheett(String empId) {

		return this.saveTimeSheetRepo.findByEmpId(empId);
	}

	public TimeSheetResponse UpdateTimeSheett(SaveTimeSheet savetimesheet, int id) {
		log.info("Entered into  UpdateTimeSheett  method of HrmsEmpTimeSheetService class");
		EmployeeDetails emp1 = this.employeeRepository.findByEmpId(savetimesheet.getEmp().getEmpId());
		ClientDetailsEntity client = this.clientDetailsRepository.findById(savetimesheet.getClient().getId()).get();
		ProjectDetailsEntity proj = this.pojectDetailsRepository
				.findByProjectId(savetimesheet.getProj().getProjectId());
		TaskDetailsEntity task = this.taskDetailsRepository.findById(savetimesheet.getTask().getTaskid()).get();
		savetimesheet.setTask(task);
		savetimesheet.setProj(proj);
		savetimesheet.setClient(client);
		savetimesheet.setEmp(emp1);
		savetimesheet.setStatus(status);
		savetimesheet.setCreatedBy(savetimesheet.getCreatedBy());
		savetimesheet.setWorkHours(savetimesheet.getWorkHours());
		savetimesheet.setModifiedDate(new Date());
		savetimesheet.setWorkDate(savetimesheet.getWorkDate());
		savetimesheet.setId(id);
		this.saveTimeSheetRepo.save(savetimesheet);

		timeSheetResponse.setMsg("successfully updated data");
		timeSheetResponse.setStatus(true);

		return timeSheetResponse;

	}

	public List<SaveTimeSheet> getTimeSheetByDate(TimeSheetRequestRepDate date) {
		log.info("Entered into getTimeSheetByDate  method of HrmsEmpTimeSheetService class");
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date.getDate());
			List<SaveTimeSheet> timesheet = saveTimeSheetRepo.getByDate(date1);
			return timesheet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<SaveTimeSheet> getTimeSheetByStartDateEndDate(TimeSheetRequestRepDate date) {
		log.info("Entered into getTimeSheetByStartDateEndDate  method of HrmsEmpTimeSheetService class");
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date.getStartDate());
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date.getEndDate());
			List<SaveTimeSheet> timesheet = saveTimeSheetRepo.getDetailsByStartDateEndDate(date1, date2);
			return timesheet;
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}

	public List<EmployeeDetails> getTimeSheetByRpId(String repId) {
		log.info("Entered into getTimeSheetByRpId  method of HrmsEmpTimeSheetService class");
		try {
			return this.employeeRepository.getTimesheetUsingRpId(repId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ProjectListResponse getProjectList() {
		log.info("Entered into getProjectList  method of HrmsEmpTimeSheetService class");
		ProjectListResponse response = new ProjectListResponse();
		try {
			ProjectResponse projectResponse = null;
			List<ProjectResponse> listOfProjectResponse = new ArrayList<>();
			List<ProjectDetailsEntity> ListProject = pojectDetailsRepository.findAll();

			for (ProjectDetailsEntity emp : ListProject) {
				projectResponse = new ProjectResponse();
				projectResponse.setProjectId(emp.getProjectId());
				projectResponse.setProjectName(emp.getProjectName());
				listOfProjectResponse.add(projectResponse);
			}
			if (listOfProjectResponse != null && listOfProjectResponse.size() > 0) {
				response.setListOfProjectResponse(listOfProjectResponse);
				response.setStatus(true);
				response.setMessage("Project Retrived Successful");
				return response;
			} else {
				response.setStatus(false);
				response.setMessage("Project Retrived UnSuccessful");
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public List<SaveTimeSheet> getTimeSheetByRpIdEmpId(String repId, String empId) {
		log.info("Entered into getTimeSheetByRpIdEmpId  method of HrmsEmpTimeSheetService class");
		return saveTimeSheetRepo.getTimeSheetUsingRepIdEmpId(repId, empId);
	}

	public List<SaveTimeSheet> getTimeSheetByRpIdDate(TimeSheetRequestRepDate timesheet) {
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(timesheet.getDate());
			List<SaveTimeSheet> savetimesheet = saveTimeSheetRepo.getDetailsByRepIdDate(timesheet.getRepId(), date1);
			return savetimesheet;
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}

	public List<SaveTimeSheet> getTimeSheetByMan(TimeSheetRequestRepDate timesheet) {
		log.info("Entered into getTimeSheetByMan method of HrmsEmpTimeSheetService class");
		try {
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(timesheet.getStartDate());
			Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(timesheet.getEndDate());
			return saveTimeSheetRepo.getDetails(timesheet.getEmpId(), date1, date2, timesheet.getRepId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
