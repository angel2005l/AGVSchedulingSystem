package com.xh.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Car {
	private int id;
	private String carCode;
	private String carName;
	private String carFactoryCode;
	private double carAverageSpeed;
	private String createUserName;
	private Date createTime;
	private String startVertexCode;
	private String endVertexCode;
	private int currentIndex;
	private String[] runningPathArr;
	private List<Edge> routeScheme;

	public Car() {
		// 初始化路线规划
		this.currentIndex = -1;// 未开始
		this.routeScheme = new ArrayList<Edge>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String[] getRunningPathArr() {
		return runningPathArr;
	}

	public void setRunningPathArr(String[] runningPathArr) {
		this.runningPathArr = runningPathArr;
	}

	public List<Edge> getRouteScheme() {
		return routeScheme;
	}

	/**
	 * 
	 * @Title: initRunningStatus
	 * @Description: 初始化主线路
	 * @author 黄官易
	 * @param currentVertexCode
	 * @return void
	 * @date 2018年8月10日
	 * @version 1.0
	 */
	public void initRunningStatus(String currentVertexCode) {
		// 开始遍历
		List<Edge> edgeList = this.getRouteScheme();
		int nextIndex = this.getCurrentIndex() + 1;
		for (int i = nextIndex; i < edgeList.size(); i++) {
			if (i == this.currentIndex) {
				continue;
			}
			Edge edge = edgeList.get(i);
			String vertexCode = edge.getEndVertexCode();// 获取当前的顶点编码
			if (vertexCode.indexOf(currentVertexCode) > -1) {
				this.setCurrentIndex(i);// 选取当前的线路下标
				this.setRunningPathArr(vertexCode.split(","));
				break;
			}
		}
		System.err.println("agv小车【" + this.carName + "】,运行配置初始化完成 ,当前已切换到【" + (this.currentIndex + 1) + "】线路,共有路线【"
				+ this.routeScheme.size() + "】条");
		if (nextIndex >= edgeList.size()) {// 当下边大于或等于当前线路集合时，重置下标
			this.setCurrentIndex(-1);
		}
	}

	public void setRouteScheme(List<Edge> routeScheme) {
		// 实现自定义的list排序
		Comparator<Edge> edgeCpt = new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				double double1 = o1.getEdgeDistance();
				double double2 = o2.getEdgeDistance();
				if (Double.compare(double1, double2) > 0) {
					return 1;
				} else if (Double.compare(double1, double2) == 0) {
					if (o1.getEndVertexCode().length() > o2.getEndVertexCode().length()) {
						return 1;
					}
				}
				return -1;
			}

		};
		routeScheme.sort(edgeCpt);
		this.routeScheme = routeScheme;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", carCode=" + carCode + ", carName=" + carName + ", carFactoryCode=" + carFactoryCode
				+ ", carAverageSpeed=" + carAverageSpeed + ", createUserName=" + createUserName + ", createTime="
				+ createTime + ", startVertexCode=" + startVertexCode + ", endVertexCode=" + endVertexCode + "]";
	}

}
