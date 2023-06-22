package com.hrms.util;

import java.time.Instant;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.EmployeeLeaveDetailsEntity;
import com.hrms.entity.WorkFlow;
import com.hrms.repository.EmployeeLeaveDetailsRepository;
import com.hrms.repository.EmployeeLeaveTypeRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.WorkFlowMgntRepository;
import com.hrms.repository.WorkFlowRepository;


@Component
public class LeaveRequestBLogic {
	
     @Autowired
	private EmployeeLeaveDetailsRepository leaveDetailsRepo;
	
     
     @Autowired
    private EmployeeLeaveTypeRepository leaveTypeRepo;
     
     @Autowired
 	private WorkFlowMgntRepository workFlowMgntRepo;

 	@Autowired
 	private WorkFlowRepository workFlowRepo;
 	
 	@Autowired
	private EmployeeRepository employeeRepo;
	
	public void updateEmployeeLeaves(String leaveType, String empId, float days, String operation, String leaveStatus) {
		EmployeeLeaveDetailsEntity leaveDetailsEntity=new EmployeeLeaveDetailsEntity();
		//EmployeeLeaveDetailsEntity e1= leaveDetailsRepo.findByEmpId(empId);
		Year year = Year.now();
		
		try {
			if (operation.equalsIgnoreCase("save") && leaveType.equalsIgnoreCase("Casual") && (leaveDetailsRepo.findByEmpId(empId))==null) {
						leaveDetailsEntity.setNoOfDays(leaveTypeRepo.getNoOfDays(leaveType));
						leaveDetailsEntity.setYear(year.getValue());
						leaveDetailsEntity.setEmpId(empId);
						leaveDetailsEntity.setUsedCasualLeaves(0.0f);
						leaveDetailsEntity.setUsedSickLeaves(0.0f);
						leaveDetailsRepo.save(leaveDetailsEntity);
						
						
					}
				else if (operation.equalsIgnoreCase("save") && leaveType.equalsIgnoreCase("sick") && leaveDetailsRepo.findByEmpId(empId)==null) {
							leaveDetailsEntity.setNoOfDays(leaveTypeRepo.getNoOfDays(leaveType));
							leaveDetailsEntity.setYear(year.getValue());
							leaveDetailsEntity.setEmpId(empId);
							leaveDetailsEntity.setUsedCasualLeaves(0.0f);
							leaveDetailsEntity.setUsedSickLeaves(0.0f);
							leaveDetailsRepo.save(leaveDetailsEntity);
							
						}
			 else if (operation.equalsIgnoreCase("update") && !leaveStatus.equalsIgnoreCase("Approved")) {
				if (leaveType.equalsIgnoreCase("Casual"))
					leaveDetailsRepo.updateUsedCasualLeaves(days, empId);
				else if (leaveType.equalsIgnoreCase("Sick"))
					leaveDetailsRepo.updateUsedSickLeaves(days, empId);
			}
			
			else {
				System.out.println("failed to apply");
			}
		} catch (Exception e) {
		    throw e;
		
	}
		
		

			}
	
	
          	public void  workFlowInsetion(WorkFlow bean,String type, boolean timeSheetConfig) {
          		
          		Instant timestamp = Instant.now();
          		
          		EmployeeDetails empDetails = employeeRepo.findByEmpId(bean.getEmpid());
          		WorkFlow workFlow =null;
          		
          		if (workFlowMgntRepo.getType(type).equalsIgnoreCase("Hierarchical") && timeSheetConfig == false && employeeRepo.getReportingManagerId(bean.getApprovalManagerId())!=null) {
					
          			
          			
          			
          			workFlow=new WorkFlow();
					workFlow.setEmpid(bean.getEmpid());
					workFlow.setFeature(bean.getFeature());
					workFlow.setStatus(bean.getStatus());
					workFlow.setApprovalManagerId(bean.getApprovalManagerId());
					workFlow.setCreatedDate(timestamp);
					workFlow.setReqid(bean.getReqid());
					workFlow.setCreatedBy(bean.getEmpid());
					workFlowRepo.save(workFlow);
					
					System.out.println("<<<<<<<<<<"+type+" && Hierarchical"+">>>>>>>>>>>");
					
          		

				} else if  (workFlowMgntRepo.getType(type).equalsIgnoreCase("parallel") && timeSheetConfig == false){
					
					
					String empidtest =bean.getEmpid();
					String s2=empidtest;
					
					int i2 = 0; 
					int i1=workFlowMgntRepo.getManagerLeavel(type);
					

					while (s2!=null && i1!=i2) {

						workFlow=new WorkFlow();
						workFlow.setEmpid(bean.getEmpid());
						workFlow.setFeature(bean.getFeature());
						workFlow.setStatus(bean.getStatus());
					    workFlow.setApprovalManagerId(employeeRepo.getReportingManagerId(empidtest));
						workFlow.setCreatedDate(timestamp);
						workFlow.setCreatedBy(bean.getEmpid());
						workFlow.setReqid(bean.getReqid());						
						workFlowRepo.save(workFlow);
						 i2++;
						empidtest=workFlow.getApprovalManagerId();
						 s2=employeeRepo.getReportingManagerId(empidtest);
						System.out.println(employeeRepo.getReportingManagerId(empidtest));
                       
					}
				}
          	
				else {
					workFlow=new WorkFlow();
					workFlow.setEmpid(bean.getEmpid());
					workFlow.setFeature(bean.getFeature());
					workFlow.setStatus(bean.getStatus());
					workFlow.setApprovalManagerId(bean.getApprovalManagerId());
					workFlow.setCreatedDate(timestamp);
					workFlow.setCreatedBy(bean.getEmpid());
		
					workFlow.setReqid(bean.getReqid());						
					
					workFlowRepo.save(workFlow);
					
				}
				
       }
          	
          	
        //  	public void
          	
          	
}

