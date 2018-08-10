package com.xh.controller;

import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xh.entity.Car;
import com.xh.entity.Edge;
import com.xh.service.CarService;
import com.xh.service.EdgeService;
import com.xh.service.VertexRunningRegulateService;

public class AGVSchedulingController extends Thread {
	private static Logger log = LoggerFactory.getLogger(AGVSchedulingController.class);
	private CarService carService = new CarService();
	private EdgeService edgeService = new EdgeService();
	private VertexRunningRegulateService vvrService = new VertexRunningRegulateService();

	@Override
	public void run() {
		try {
			Car car = carService.initCar();// 初始化小车
			car.setRouteScheme(edgeService.initEdges(car.getStartVertexCode(), "", car.getEndVertexCode(), "", 0d,
					new ArrayList<Edge>()));// 获得全部线路
			car.initRunningStatus(car.getStartVertexCode());
			runingAgvCar(car);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @Title: runingAgvCar
	 * @Description: 模拟小车运行
	 * @author 黄官易
	 * @param car
	 * @return void
	 * @date 2018年8月9日
	 * @version 1.0
	 * @throws InterruptedException
	 */
	private void runingAgvCar(Car car) throws InterruptedException {
		String currentVertexCode = car.getStartVertexCode();// 获得当前所在顶点编码
		String[] runningPathArr = car.getRunningPathArr(); // 获得当前路线数组
		int currentIndex = car.getCurrentIndex(); // 获得当前选择路线下标
		int currentArrIndex = 0; // 当前路线数组下标
		if (null == runningPathArr || runningPathArr.length == 0) {
			System.err.println("无路线，任务结束");
			log.info("无路线，任务结束");
			return;
		}
		// 初始化当前数组下标
		for (int i = 0; i < runningPathArr.length; i++) {
			if (currentVertexCode.equals(runningPathArr[i])) {
				currentArrIndex = i;
				break;
			}
		}
		// 小车开始移动
		for (int i = currentArrIndex; i < runningPathArr.length - 1; i++) {
			if (vvrService.clockEdge(runningPathArr[i], runningPathArr[i + 1], car.getCarCode(), 0d)) {

			} else {
				while (currentIndex == car.getCurrentIndex() && car.getRouteScheme().size() > 1) {
					Thread.sleep(2 * 1000);// 线程休眠两秒
					car.initRunningStatus(currentVertexCode);// 切换线路
					/*
					 * System.err.println("当前线路被占用,进行线路切换。切换请求" + currentIndex +"---"+
					 * car.getCurrentIndex() + (currentIndex == car.getCurrentIndex() &&
					 * car.getRouteScheme().size() > 1));
					 */
				}
				runingAgvCar(car);
				break;
			}
			if (i == runningPathArr.length - 2) {
				System.err.println("AGV车" + car.getCarName() + "任务完成");
				log.info("AGV车" + car.getCarName() + "任务完成");
			}
		}

	}

	public static void main(String[] arg) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入模拟小车数(建议小车数量在20辆左右)：");
		String carNumStr = scanner.nextLine();
		int carNum = Integer.parseInt(carNumStr);
		for (int i = 0; i < carNum; i++) {
			new AGVSchedulingController().start();
		}
	}
}
