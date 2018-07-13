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
@Table(name = "rnc_status_summary")
public class RncStatusSummaryEntity  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;
	
	@Column(name = "rnc_name")
	String rncName;
	
	@Column(name = "totalcell_count")
	Integer totalCellCount;
	
	@Column(name = "upcell_count")
	Integer upCellCount;
	
	@Column(name = "downcell_count")
	Integer downCellCount;

	@Column(name = "totalsite_count")
	Integer totalSiteCount;
	
	@Column(name = "upsite_count" )
	Integer upSiteCount;
	
	@Column(name = "downsite_cout")
	Integer downSiteCount;
	
	@Column(name = "cycle_time")
    Date cycleTime;
	
	@Column(name = "db_time_stamp")
	 Date db_time_stamp;

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

	public Integer getTotalCellCount() {
		return totalCellCount;
	}

	public void setTotalCellCount(Integer totalCellCount) {
		this.totalCellCount = totalCellCount;
	}

	public Integer getUpCellCount() {
		return upCellCount;
	}

	public void setUpCellCount(Integer upCellCount) {
		this.upCellCount = upCellCount;
	}

	public Integer getDownCellCount() {
		return downCellCount;
	}

	public void setDownCellCount(Integer downCellCount) {
		this.downCellCount = downCellCount;
	}

	public Integer getTotalSiteCount() {
		return totalSiteCount;
	}

	public void setTotalSiteCount(Integer totalSiteCount) {
		this.totalSiteCount = totalSiteCount;
	}

	public Integer getUpSiteCount() {
		return upSiteCount;
	}

	public void setUpSiteCount(Integer upsiteCount) {
		this.upSiteCount = upsiteCount;
	}

	public Integer getDownSiteCount() {
		return downSiteCount;
	}

	public void setDownSiteCount(Integer downSiteCout) {
		this.downSiteCount = downSiteCout;
	}

	public Date getCycleTime() {
		return cycleTime;
	}

	public void setCycleTime(Date cycleTime) {
		this.cycleTime = cycleTime;
	}

	public Date getDb_time_stamp() {
		return db_time_stamp;
	}

	public void setDb_time_stamp(Date db_time_stamp) {
		this.db_time_stamp = db_time_stamp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
