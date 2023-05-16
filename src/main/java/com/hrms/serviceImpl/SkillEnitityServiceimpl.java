package com.hrms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.beans.SkillEnitityBean;
import com.hrms.entity.EmployeeDetails;
import com.hrms.entity.SkillEnitity;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.SkillEnitityServiceRepository;
import com.hrms.service.SkillEnitityService;
@Service
public class SkillEnitityServiceimpl implements SkillEnitityService{
	@Autowired
	private SkillEnitityServiceRepository skillentityrepo;
	@Autowired
	private SkillEnitityBean skillentitybean;
	
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public SkillEnitityBean saveskills(String empId, SkillEnitity entity) {
		
		
		
		EmployeeDetails employeeDetails=empRepo.findByEmpId(empId);
		entity.setEmployeeDetails(employeeDetails);
		//entity.setEmployeeDetails(employeeDetails);
		SkillEnitity skillentity = skillentityrepo.save(entity);

		if (skillentity != null) {
			skillentitybean.setMessage("skills saved successfully");
			skillentitybean.setStatus(true);
		} else {
			skillentitybean.setMessage("skills saved failed");
			skillentitybean.setStatus(false);
		}
		
		return skillentitybean;
	}}

//	@Override
//	public SkillEnitity getskillsById(Integer id) {
//		
//		Optional<SkillEnitity> skill=skillentityrepo.findById(id);
//		if(skill.isPresent())
//		return skill.get();
//		return null;
//		
//	}
//	@Override
//	public List<SkillEnitity> getAllskilldetals() {
//
//		return skillentityrepo.findAll();
//	}
//
//	@Override
//	public SkillEnitityBean updateskilldetails(SkillEnitity skillentity) {
//		EmployeeDetails employeeDetails=empRepo.findByEmpId(skillentity.getEmployeeDetails().getEmpId());
//		skillentity.setEmployeeDetails(employeeDetails);
//
//		SkillEnitity entity = skillentityrepo.save(skillentity);
//
//		if (entity != null) {
//			skillentitybean.setMessage("EmployeeEducation details updated successfully");
//			skillentitybean.setStatus(true);
//		} else {
//			skillentitybean.setMessage("EmployeeEducation details Failed !");
//			skillentitybean.setStatus(false);
//		}
//
//		return skillentitybean ;
//	
//
//		
//	
//	}
//
//}
