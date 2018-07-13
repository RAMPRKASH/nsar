package com.nova.nsar.repository.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nova.nsar.common.utils.DateUtil;

@Entity
@Table(name="rnc_status_detail")
public class RncStatusDetailEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;
	
	@Column(name = "rnc_name")
	String rncName;
	
	@Column(name = "site_name")
	String siteName;
	
	@Column(name = "cell_name")
	String cellName;
	
	@Column(name = "sector_status")
	String sectorStatus;
	
	@Column(name = "status")
	Integer status;
	
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getSectorStatus() {
		return sectorStatus;
	}

	public void setSectorStatus(String sectorStatus) {
		this.sectorStatus = sectorStatus;
	}

	public String  getCycleTime() {
		return DateUtil.toString(cycleTime,"YYYY-MM-dd HH:mm:ss");
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RncStatusDetailEntity [id=" + id + ", rncName=" + rncName + ", siteName=" + siteName + ", cellName="
				+ cellName + ", sectorStatus=" + sectorStatus + ", status=" + status + ", cycleTime=" + cycleTime
				+ ", dbTimeStamp=" + dbTimeStamp + "]";
	}
	
}
