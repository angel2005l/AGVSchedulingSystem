package com.xh.entity;

import java.sql.Date;

public class VertexRunningRegulate {

	private String vrrCode;
	private String carCode;
	private String startVertexCode;
	private String endVertexCode;
	private double vrrOdo;
	private Date vrrStartTime;
	private Date vrrEndTime;
	private String vrrStatus;

	public VertexRunningRegulate() {
	}

	public String getVrrCode() {
		return vrrCode;
	}

	public void setVrrCode(String vrrCode) {
		this.vrrCode = vrrCode;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getStartVertexCode() {
		return startVertexCode;
	}

	public void setStartVertexCode(String startVertexCode) {
		this.startVertexCode = startVertexCode;
	}

	public String getEndVertexCode() {
		return endVertexCode;
	}

	public void setEndVertexCode(String endVertexCode) {
		this.endVertexCode = endVertexCode;
	}

	public double getVrrOdo() {
		return vrrOdo;
	}

	public void setVrrOdo(double vrrOdo) {
		this.vrrOdo = vrrOdo;
	}

	public Date getVrrStartTime() {
		return vrrStartTime;
	}

	public void setVrrStartTime(Date vrrStartTime) {
		this.vrrStartTime = vrrStartTime;
	}

	public Date getVrrEndTime() {
		return vrrEndTime;
	}

	public void setVrrEndTime(Date vrrEndTime) {
		this.vrrEndTime = vrrEndTime;
	}

	public String getVrrStatus() {
		return vrrStatus;
	}

	public void setVrrStatus(String vrrStatus) {
		this.vrrStatus = vrrStatus;
	}

	@Override
	public String toString() {
		return "VertexRunningRegulate [vrrCode=" + vrrCode + ", carCode=" + carCode + ", startVertexCode="
				+ startVertexCode + ", endVertexCode=" + endVertexCode + ", vrrOdo=" + vrrOdo + ", vrrStartTime="
				+ vrrStartTime + ", vrrEndTime=" + vrrEndTime + ", vrrStatus=" + vrrStatus + "]";
	}

}
