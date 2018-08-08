package com.xh.entity;

import java.sql.Date;

public class Car {
	private String carCode;
	private String carName;
	private String carFactoryCode;
	private double carAverageSpeed;
	private String createUserName;
	private Date createTime;

	public Car() {
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarFactoryCode() {
		return carFactoryCode;
	}

	public void setCarFactoryCode(String carFactoryCode) {
		this.carFactoryCode = carFactoryCode;
	}

	public double getCarAverageSpeed() {
		return carAverageSpeed;
	}

	public void setCarAverageSpeed(double carAverageSpeed) {
		this.carAverageSpeed = carAverageSpeed;
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
		return "Car [carCode=" + carCode + ", carName=" + carName + ", carFactoryCode=" + carFactoryCode
				+ ", carAverageSpeed=" + carAverageSpeed + ", createUserName=" + createUserName + ", createTime="
				+ createTime + "]";
	}

}
