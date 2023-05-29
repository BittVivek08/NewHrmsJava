package com.hrms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrms.entity.EmployeeLeaveDetailsEntity;
import com.hrms.repository.EmployeeLeaveDetailsRepository;
import com.hrms.repository.EmployeeLeaveTypeRepository;


@Component
public class LeaveRequestBLogic {
	
     @Autowired
	private EmployeeLeaveDetailsRepository leaveDetailsRepo;
	
     
     @Autowired
    private EmployeeLeaveTypeRepository leaveTypeRepo;
	
	public void updateEmployeeLeaves(String leaveType, String empId, float days, String operation, String leaveStatus) {
		EmployeeLeaveDetailsEntity leaveDetailsEntity=new EmployeeLeaveDetailsEntity();
		
		try {
			if (operation.equalsIgnoreCase("save")) {
				if (leaveType.equalsIgnoreCase("Casual")) {
					if(leaveDetailsRepo.findByEmpId(empId)==null) {
						leaveDetailsEntity.setNoOfDays(leaveTypeRepo.getNoOfDays());
						leaveDetailsEntity.setYear(0);
						leaveDetailsEntity.setEmp_id(empId);
						leaveDetailsEntity.setUsedCasualLeaves(0.0f);
						leaveDetailsEntity.setUsedSickLeaves(0.0f);
						leaveDetailsRepo.save(leaveDetailsEntity);
						
					}
					
           leaveDetailsRepo.updateUsedCasualLeavesByUserIdAndCurrentYear( days, empId);
           
           
           
				}
				
				else if (leaveType.equalsIgnoreCase("Sick")) {
					leaveDetailsRepo.updateUsedSickLeavesByUserIdAndCurrentYear(days, empId);
				}
			} else if (operation.equalsIgnoreCase("update") && !leaveStatus.equalsIgnoreCase("Approved")) {
				if (leaveType.equalsIgnoreCase("Casual"))
					leaveDetailsRepo.updateUsedCasualLeaves(days, empId);
				else if (leaveType.equalsIgnoreCase("Sick"))
					leaveDetailsRepo.updateUsedSickLeaves(days, empId);
			}
			
			else {
				System.out.println("failed to apply");
			}
		} catch (Exception e) {
		    e.printStackTrace();;
		
	}

	}
}
