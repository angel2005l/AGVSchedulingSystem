package com.xh.entity;

import java.sql.Date;

public class Edge {
	private String edgeCode;
	private String edgeName;
	private String startVertexCode;
	private String endVertexCode;
	private double edge_distance;
	private String edge_status;
	private String createUserCode;
	private Date createTime;

	public Edge() {
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

	public double getEdge_distance() {
		return edge_distance;
	}

	public void setEdge_distance(double edge_distance) {
		this.edge_distance = edge_distance;
	}

	public String getEdge_status() {
		return edge_status;
	}

	public void setEdge_status(String edge_status) {
		this.edge_status = edge_status;
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
		return "Edge [edgeCode=" + edgeCode + ", edgeName=" + edgeName + ", startVertexCode=" + startVertexCode
				+ ", endVertexCode=" + endVertexCode + ", edge_distance=" + edge_distance + ", edge_status="
				+ edge_status + ", createUserCode=" + createUserCode + ", createTime=" + createTime + "]";
	}

}
