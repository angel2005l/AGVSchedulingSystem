package com.xh.entity;

import java.sql.Date;

public class Vertex {
	private int id;
	private String verCode;
	private String verName;
	private String verFactoryCode;
	private String createUserName;
	private Date createTime;

	public Vertex() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	public String getVerName() {
		return verName;
	}

	public void setVerName(String verName) {
		this.verName = verName;
	}

	public String getVerFactoryCode() {
		return verFactoryCode;
	}

	public void setVerFactoryCode(String verFactoryCode) {
		this.verFactoryCode = verFactoryCode;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Vertex [id=" + id + ", verCode=" + verCode + ", verName=" + verName + ", verFactoryCode="
				+ verFactoryCode + ", createUserName=" + createUserName + ", createTime=" + createTime + "]";
	}

}
