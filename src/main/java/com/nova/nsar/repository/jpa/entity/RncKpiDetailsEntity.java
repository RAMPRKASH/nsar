package com.nova.nsar.repository.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rnc_kpi_details")
public class RncKpiDetailsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;
	
	@Column(name = "rnc_name")
	String rncName;
	
	@Column(name = "counter_name")
	String counterName;
	
	@Column(name = "counter_value")
	Float counterValue;
	
	@Column(name = "cycle_time")
	Date cycleTime;
	
	@Column(name = "db_time_stamp")
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

	public Date getCycleTime() {
		return cycleTime;
	}

	public void setCycleTime(Date cycleTime) {
		this.cycleTime = cycleTime;
	}

	public Date getDbTimeStamp() {
		return dbTimeStamp;
	}

	public void setDbTimeStamp(Date dbTimeStamp) {
		this.dbTimeStamp = dbTimeStamp;
	}

	@Override
	public String toString() {
		return "RncKpiDetailsEntity [id=" + id + ", rncName=" + rncName + ", counterName=" + counterName
				+ ", counterValue=" + counterValue + ", cycleTime=" + cycleTime + ", dbTimeStamp=" + dbTimeStamp + "]";
	}
	
}
