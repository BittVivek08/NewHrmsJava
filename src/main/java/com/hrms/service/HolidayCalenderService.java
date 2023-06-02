package com.hrms.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.hrms.beans.EntityBeanResponse;
import com.hrms.beans.EntityBeanResponseCommon;
import com.hrms.entity.HolidayCalenderEntity;

public interface HolidayCalenderService {
	
	
	public EntityBeanResponseCommon saveHoliday(HolidayCalenderEntity holiday);
	
	public List<HolidayCalenderEntity> getAllHolidays();
	
	public HolidayCalenderEntity updateHoliday(int id,HolidayCalenderEntity updateHoliday);
	
	public HolidayCalenderEntity getHolidayBydate(LocalDate holidayDate);

    public HolidayCalenderEntity getHolidayById(int id);
    
    public EntityBeanResponseCommon deleteHoliday(int id);
    
    
    public EntityBeanResponseCommon updateHolidayById(int id,HolidayCalenderEntity update);
   
    //FindlocaldatesOfHolidays
     public List<LocalDate> getalllocaldates();
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	

}
