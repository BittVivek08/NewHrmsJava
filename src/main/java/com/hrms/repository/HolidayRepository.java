package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrms.entity.HolidayCalendarEntity;

public interface HolidayRepository extends JpaRepository<HolidayCalendarEntity, Integer> {
	
	//List<HolidayCalendarEntity> findAllByDate(LocalDate date);

}
