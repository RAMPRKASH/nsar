package com.nova.nsar.service.dto;

import java.util.Date;

import com.nova.nsar.common.utils.DateUtil;

public class RncKpiDetails {

	Long id;
	String rncName;
	String counterName;
	Float counterValue;
	Date cycleTime;
	Date dbTimeStamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRncName() {
		return rncName;
	}

	public void setRncName(String rncName) {
		this.rncName = rncName;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public Float getCounterValue() {
		return counterValue;
	}

	public void setCounterValue(Float counterValue) {
		this.counterValue = counterValue;
	}

	public String getCycleTime() {
		return DateUtil.toString(cycleTime, "yyyy-MM-dd HH:mm:00");
	}

	public void setCycleTime(Date cycleTime) {
		this.cycleTime = cycleTime;
	}

	public String getDbTimeStamp() {
		return DateUtil.toString(dbTimeStamp, "yyyy-MM-dd HH:mm:00");
	}

	public void setDbTimeStamp(Date dbTimeStamp) {
		this.dbTimeStamp = dbTimeStamp;
	}
}
