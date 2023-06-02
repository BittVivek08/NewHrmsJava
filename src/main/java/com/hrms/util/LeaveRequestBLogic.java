package com.hrms.util;

import java.time.Year;

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
}
