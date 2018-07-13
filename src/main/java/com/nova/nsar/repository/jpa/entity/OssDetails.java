package com.nova.nsar.repository.jpa.entity;

public class OssDetails {

	private String nodeIp;
	private String nodeUser;
	private String password;
	private String nodeName;
	private String nodeType;
	private String region;
	
	public String getNodeIp() {
		return nodeIp;
	}
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}
	public String getNodeUser() {
		return nodeUser;
	}
	public void setNodeUser(String nodeUser) {
		this.nodeUser = nodeUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
}