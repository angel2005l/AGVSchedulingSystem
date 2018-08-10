package com.xh.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xh.dao.CarDao;
import com.xh.dao.VertexDao;
import com.xh.entity.Car;
import com.xh.entity.Vertex;
import com.xh.util.DateUtil;
import com.xh.util.StrUtil;

public class CarService {
	private static Logger log = LoggerFactory.getLogger(CarService.class);
	private CarDao dao = new CarDao();
	private VertexDao vtd = new VertexDao();

	/**
	 * 
	 * @Title: addCar
	 * @Description: 添加小车信息（随机，默认）
	 * @author 黄官易
	 * @return void
	 * @date 2018年8月10日
	 * @version 1.0
	 */
	public void addCar() {
		List<Car> insertObj = new ArrayList<Car>();
		for (int i = 1; i < 11; i++) {
			Car car = new Car();
			car.setCarCode(
					"CAR" + DateUtil.curDateYMDHMSForService() + StrUtil.getRandom((int) (Math.random() * 1000), 4));
			car.setCarName("AGV小车_" + i);
			car.setCarFactoryCode("FAC" + DateUtil.curDateYMDHMSForService() + 4158);
			car.setCarAverageSpeed(1d);
			car.setCreateUserName("新海管理员");
			car.setCreateTime(new Date(System.currentTimeMillis()));
			insertObj.add(car);
		}
		try {
			if (dao.insertBacthCar(insertObj) > 0) {
				System.err.println("执行成功");
			} else {
				System.err.println("执行失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: initCar
	 * @Description: 初始化小车
	 * @author 黄官易
	 * @return
	 * @throws Exception
	 * @return Car
	 * @date 2018年8月10日
	 * @version 1.0
	 */
	public Car initCar() throws Exception {
		synchronized (this) {
			List<Car> carList = dao.selectCarAll();
			// [0,10)前闭后开 取10辆车任意一辆
			Car car = carList.get((int) (Math.random() * 10));
			// [1,11) 前闭后开 取10个顶点的任意一点
			int startVertexNo = (int) (Math.random() * 10) + 1;
			int endVertexNo = (int) (Math.random() * 10) + 1;
			Vertex startVertex = vtd.selectVertexById(startVertexNo);
			Vertex endVertex = vtd.selectVertexById(endVertexNo);
			car.setStartVertexCode(startVertex.getVerCode());
			car.setEndVertexCode(endVertex.getVerCode());
			System.err.println("小车名:" + car.getCarName());
			System.err.println("起始站点:" + startVertex.getVerName());
			System.err.println("结束站点：" + endVertex.getVerName());
			System.err.println("-------------------------------------------");
			log.info("小车名:" + car.getCarName());
			log.info("起始站点:" + startVertex.getVerName());
			log.info("结束站点：" + endVertex.getVerName());
			log.info("-------------------------------------------");
			return car;
		}
	}

}
