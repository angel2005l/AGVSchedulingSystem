package com.xh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.xh.entity.Car;
import com.xh.util.SqlPoolUtil;

public class CarDao {
	private static final Logger log = LoggerFactory.getLogger(CarDao.class);

	private SqlPoolUtil instance = SqlPoolUtil.getInstance();

	/**
	 * 
	 * @Title: selectCarAll
	 * @Description: 查询所有的小车进行测试
	 * @author 黄官易
	 * @return
	 * @throws Exception
	 * @return List<Car>
	 * @date 2018年8月8日
	 * @version 1.0
	 */
	public List<Car> selectCarAll() throws Exception {
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Car> carList = new ArrayList<Car>();
		try {
			String sql = "select * from agv_car ";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Car carObj = new Car();
				carObj.setCarCode("car_code");
				carObj.setCarName("car_name");
				carObj.setCarFactoryCode(rs.getString("car_factory_code"));
				carObj.setCarAverageSpeed(rs.getDouble("car_average_speed"));
				carObj.setCreateUserName("create_user_code");
				carObj.setCreateTime(rs.getDate("create_time"));
				carList.add(carObj);
			}
		} catch (SQLException e) {
			log.error("查询小车数据库操作异常,异常原因:【" + e.toString() + "】");
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, rs);
		}
		return carList;
	}

	/**
	 * 
	 * @Title: insertBacthCar
	 * @Description: 批量新增模拟小车信息
	 * @author 黄官易
	 * @param insertObj
	 * @return
	 * @throws Exception
	 * @return int
	 * @date 2018年8月8日
	 * @version 1.0
	 */
	public int insertBacthCar(List<Car> insertObj) throws Exception {
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		conn.setAutoCommit(false);
		String sql = "insert into agv_car(car_code,car_name,car_factory_code,car_average_speed,create_user_name,create_time) values(?,?,?,?,?,?)";
		try {
			for (Car car : insertObj) {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, car.getCarCode());
				psmt.setString(2, car.getCarName());
				psmt.setString(3, car.getCarFactoryCode());
				psmt.setDouble(4, car.getCarAverageSpeed());
				psmt.setString(5, car.getCreateUserName());
				psmt.setDate(6, car.getCreateTime());
				psmt.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			log.error("批量新增小车信息异常,异常原因:【" + e.toString() + "】");
			return 1;
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, null);
		}
		return 0;
	}

}
