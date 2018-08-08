package com.xh.entity;

import java.sql.Date;

public class Edge {
	private int id;
	private String edgeCode;
	private String edgeName;
	private String startVertexCode;
	private String endVertexCode;
	private double edgeDistance;
	private String edgeStatus;
	private String createUserCode;
	private Date createTime;

	public Edge() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEdgeCode() {
		return edgeCode;
	}

	public void setEdgeCode(String edgeCode) {
		this.edgeCode = edgeCode;
	}

	public String getEdgeName() {
		return edgeName;
	}

	public void setEdgeName(String edgeName) {
		this.edgeName = edgeName;
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

	public double getEdgeDistance() {
		return edgeDistance;
	}

	public void setEdgeDistance(double edgeDistance) {
		this.edgeDistance = edgeDistance;
	}

	public String getEdgeStatus() {
		return edgeStatus;
	}

	public void setEdgeStatus(String edgeStatus) {
		this.edgeStatus = edgeStatus;
	}

	public String getCreateUserCode() {
		return createUserCode;
	}

	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Edge [id=" + id + ", edgeCode=" + edgeCode + ", edgeName=" + edgeName + ", startVertexCode="
				+ startVertexCode + ", endVertexCode=" + endVertexCode + ", edgeDistance=" + edgeDistance
				+ ", edgeStatus=" + edgeStatus + ", createUserCode=" + createUserCode + ", createTime=" + createTime
				+ "]";
	}

}
