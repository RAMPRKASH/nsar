package com.nova.nsar.repository.jpa.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rnc_details")
public class RncDetailsEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	Long id;
	
	@Column(name = "rnc_name")
	String rncName;
	
	@Column(name = "region")
	String region;
	
	// TODO To make this as the foreign key
	@Column(name = "network_type_id")
	Long networkTypeId;
	
	// TODO To make this as the foreign key
	@Column(name = "hardware_type_id")
	Long hardwareTypeId;

	@Column(name = "created_at")
	Date createdAt;
	
	@Column(name = "created_by")
	String createdBy;
	
	@Column(name = "updated_at")
	Date updatedAt;
	
	@Column(name = "updated_by")
	String updatedBy;

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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Long getNetworkTypeId() {
		return networkTypeId;
	}

	public void setNetworkTypeId(Long networkTypeId) {
		this.networkTypeId = networkTypeId;
	}

	public Long getHardwareTypeId() {
		return hardwareTypeId;
	}

	public void setHardwareTypeId(Long hardwareTypeId) {
		this.hardwareTypeId = hardwareTypeId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "RncDetailsEntity [id=" + id + ", rncName=" + rncName + ", region=" + region + ", networkTypeId="
				+ networkTypeId + ", hardwareTypeId=" + hardwareTypeId + ", createdAt=" + createdAt + ", createdBy="
				+ createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
	}

}
