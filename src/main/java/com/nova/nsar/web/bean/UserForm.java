package com.nova.nsar.web.bean;

import com.nova.nsar.web.bean.form.Form;

public class UserForm extends Form {

	private Integer userId;
	private String loginCode;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String password;
	private Boolean isActive;
	private Integer roleId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserForm [userId=" + userId + ", loginCode=" + loginCode + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", mobile=" + mobile + ", password=" + password + ", isActive="
				+ isActive + ", roleId=" + roleId + "]";
	}

}