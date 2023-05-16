package com.hrms.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hrms.entity.HolidayCalenderEntity;

public interface HolidayCalenderRepository extends JpaRepository<HolidayCalenderEntity, Integer> {

//	 getHloidayByName
	public HolidayCalenderEntity findByDate(LocalDate date);

	/*
	 * @Query("SELECT l.date FROM HolidayCalenderEntity l") public List<Date>
	 * findAllDate();
	 */

	@Query("SELECT l.date FROM HolidayCalenderEntity l")
	public List<LocalDate> finDates();

	List<HolidayCalenderEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

//    @Query("select date from HolidayCalenderEntity where id=?1 ")
//    @Query("select date from HolidayCalenderEntity where  date:?1") 
//	public Date findByDateDate(LocalDate date);
//	
	@Query("select date from HolidayCalenderEntity where date=?1")
	public LocalDate findByDate1(LocalDate date);

}
