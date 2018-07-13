package com.nova.nsar.web.bean.form;

import java.util.Date;

public class Form {

	protected String createdBy;
	protected Date createdAt;
	protected String updatedBy;
	protected Date updatedAt;
	protected String formMode;
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getFormMode() {
		return formMode;
	}
	public void setFormMode(String formMode) {
		this.formMode = formMode;
	}
	
	
}
