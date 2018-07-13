package com.nova.nsar.repository.jpa.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rnc_cell_details")
public class RncCellDetailsEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", nullable = false)
	Long id;
	
	@Column(name = "cell_name")
	String cellName;
	
	// TODO To make this as the foreign key
	@Column(name = "rnc_id")
	Long rncId;
	
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

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public Long getRncId() {
		return rncId;
	}

	public void setRncId(Long rncId) {
		this.rncId = rncId;
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
		return "RncCellDetailsEntity [id=" + id + ", cellName=" + cellName + ", rncId=" + rncId + ", createdAt=" + createdAt
				+ ", createdBy=" + createdBy + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
	}
	
	
}
