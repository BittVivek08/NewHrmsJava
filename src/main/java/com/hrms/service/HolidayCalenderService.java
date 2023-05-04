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
	
	/*
	 * public HolidayCalenderEntity UpdateHoliday(HolidayCalenderEntity
	 * updateHoliday);
	 */
	
	public HolidayCalenderEntity updateHoliday(int id,HolidayCalenderEntity updateHoliday);
	
	public HolidayCalenderEntity getHolidayBydate(LocalDate holidayDate);

    public HolidayCalenderEntity getHolidayById(int id);
    
    public EntityBeanResponseCommon deleteHoliday(int id);
    
	/*
	 * //findAllDates public List<Date> getAllDates();
	 */
    
    //FindlocaldatesOfHolidays
     public List<LocalDate> getalllocaldates();
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	

}