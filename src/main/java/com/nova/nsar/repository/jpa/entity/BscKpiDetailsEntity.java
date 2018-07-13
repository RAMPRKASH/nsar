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
@Table(name="bsc_kpi_details")
public class BscKpiDetailsEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;
	
	@Column(name = "bsc_name")
	String bscName;
	
	@Column(name = "kpi_name")
	String kpiName;
	
	@Column(name = "kpi_value")
	Double kpiValue;
	
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

	public String getBscName() {
		return bscName;
	}

	public void setBscName(String bscName) {
		this.bscName = bscName;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public Double getKpiValue() {
		return kpiValue;
	}

	public void setKpiValue(Double kpiValue) {
		this.kpiValue = kpiValue;
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
		return "BscKpiDetailsEntity [id=" + id + ", bscName=" + bscName + ", kpiName=" + kpiName + ", kpiValue="
				+ kpiValue + ", cycleTime=" + cycleTime + ", dbTimeStamp=" + dbTimeStamp + "]";
	}

		
}
