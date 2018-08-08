package com.xh.entity;

import java.sql.Date;

public class Vertex {
	private String verCode;
	private String verName;
	private String verFactoryCode;
	private String create_user_name;
	private Date createTime;

	public Vertex() {
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

	public String getCreate_user_name() {
		return create_user_name;
	}

	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Vertex [verCode=" + verCode + ", verName=" + verName + ", verFactoryCode=" + verFactoryCode
				+ ", create_user_name=" + create_user_name + ", createTime=" + createTime + "]";
	}

}
