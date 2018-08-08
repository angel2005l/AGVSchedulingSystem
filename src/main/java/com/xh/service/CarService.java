package com.xh.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.xh.dao.CarDao;
import com.xh.dao.VertexDao;
import com.xh.entity.Car;
import com.xh.entity.Vertex;
import com.xh.util.DateUtil;
import com.xh.util.StrUtil;

public class CarService {
	private CarDao dao = new CarDao();
	private VertexDao vtd = new VertexDao();

	// 添加小车信息
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

	public List<Car> initCar() throws Exception {
		List<Car> carList = dao.selectCarAll();
		for (Car car : carList) {
			// [1,11) 前闭后开
			int startVertexNo = (int) (Math.random() * 10) + 1;
			int endVertexNo = (int) (Math.random() * 10) + 1;
			Vertex startVertex = vtd.selectVertexById(startVertexNo);
			Vertex endVertex = vtd.selectVertexById(endVertexNo);
			car.setStartVertexCode(startVertex.getVerCode());
			car.setEndVertexCode(endVertex.getVerCode());
			System.err.println("小车名:"+car.getCarName());
			System.err.println("起始站点:"+startVertex.getVerName());
			System.err.println("结束站点："+endVertex.getVerName());
			System.err.println("-------------------------------------------");
		}
		return carList;
	}

	public static void main(String[] arg) {
		CarService service = new CarService();

		try {
			List<Car> initCar = service.initCar();
			for (Car car : initCar) {
				System.err.println(car);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
