package com.hrms.service;

import java.util.List;

import com.hrms.beans.SkillEnitityBean;
import com.hrms.entity.SkillEnitity;

public interface SkillEnitityService {

	SkillEnitityBean saveskills(String empId, SkillEnitity entity);

	List<SkillEnitity> getskillDetails();

	SkillEnitityBean updateskilldetails(int id,SkillEnitity entity);


}
