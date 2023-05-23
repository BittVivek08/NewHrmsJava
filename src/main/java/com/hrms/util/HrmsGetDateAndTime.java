package com.hrms.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import ch.qos.logback.classic.Logger;



import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class HrmsGetDateAndTime {

	private final static Logger logger =(Logger) LoggerFactory.getLogger(HrmsGetDateAndTime.class);
	
	

	static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final String time = "yyyy-MM-dd";

	public Timestamp GetUTCdatetimeAsString() {
		logger.info("entered into GetUTCdatetimeAsString of utility class");
		Timestamp utctimestamp = null;
		String utcTime = null;
		try {
			ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
			utcTime = utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
			utctimestamp = Timestamp.valueOf(utcTime);
		} catch (Exception e) {
			logger.error("catch block of GetUTCdatetimeAsString method of utility class:" + e);
			e.printStackTrace();
		}
		return utctimestamp;
	}

	public java.sql.Date GetUTCDateAsString(String date) {
		logger.info("entered into GetUTCDateAsString of utility class");
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date parsedDate = dateFormat.parse(date);
			java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
			return sqlDate;
		} catch (Exception e) {
			logger.error("catch block of GetUTCDateAsString method of utility class:" + e);
			return null;
		}
	}

	// only Auto Date Saving method ....
	public String getDateOnly() {
		String utcDate = null;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();
			utcDate = DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utcDate;
	}
}
