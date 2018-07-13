package com.nova.nsar.repository.jpa.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="network_type")
public class NetworkTypeEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	Long id;
	
	@Column(name = "network_type")
	String networkType;
	
	@Column(name = "created_at")
	Date createdAt;
	
	@Column(name = "created_by")
	String createdBy;
	
	@Column(name = "updated_at")
	Date updatedAt;
	
	@Column(name = "updated_by")
	String updatedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(String networkType) {
		this.networkType = networkType;
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
		return "NetworkTypeEntity [id=" + id + ", networkType=" + networkType + ", createdAt=" + createdAt
				+ ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
	}

	
}
