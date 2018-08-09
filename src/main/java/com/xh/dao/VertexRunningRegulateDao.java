package com.xh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.xh.entity.VertexRunningRegulate;
import com.xh.util.SqlPoolUtil;

public class VertexRunningRegulateDao {
	private static Logger log = LoggerFactory.getLogger(VertexRunningRegulateDao.class);

	private SqlPoolUtil instance = SqlPoolUtil.getInstance();

	/**
	 * 
	 * @Title: selectVRRByStartVertexCodeAndEndVertexCode
	 * @Description: 查询当前使用的情况
	 * @author 黄官易
	 * @param startVertexCode
	 * @param endVertexCode
	 * @return
	 * @throws Exception
	 * @return VertexRunningRegulate
	 * @date 2018年8月8日
	 * @version 1.0
	 */
	public VertexRunningRegulate selectVRRByStartVertexCodeAndEndVertexCode(String startVertexCode,
			String endVertexCode) throws Exception {
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer(
					"select * from agv_vertex_running_regulate where start_vertex_code =? and end_vertex_code =? and vrr_status = 'process'");
			sql.append(
					" union select * from agv_vertex_running_regulate where start_vertex_code =? and end_vertex_code =? and vrr_status = 'process'");
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, startVertexCode);
			psmt.setString(2, endVertexCode);
			psmt.setString(3, endVertexCode);
			psmt.setString(4, startVertexCode);
			rs = psmt.executeQuery();
			if (rs.next()) {
				VertexRunningRegulate vrrObj = new VertexRunningRegulate();
				vrrObj.setVrrCode(rs.getString("vrr_code"));
				vrrObj.setCarCode(rs.getString("car_code"));
				vrrObj.setStartVertexCode(rs.getString("start_vertex_code"));
				vrrObj.setEndVertexCode(rs.getString("end_vertex_code"));
				vrrObj.setVrrOdo(rs.getDouble("vrr_odo"));
				vrrObj.setVrrStartTime(rs.getDate("vrr_start_time"));
				vrrObj.setVrrEndTime(rs.getDate("vrr_end_time"));
				vrrObj.setVrrStatus(rs.getString("vrr_status"));
				return vrrObj;
			}
		} catch (SQLException e) {
			log.error("查询调度任务数据库操作异常,异常原因:【" + e.toString() + "】");
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, rs);
		}
		return null;
	}

	/**
	 * 
	 * @Title: insertVRR
	 * @Description: 添加调度信息
	 * @author 黄官易
	 * @param insertObj
	 * @return
	 * @throws Exception
	 * @return int
	 * @date 2018年8月8日
	 * @version 1.0
	 */
	public String insertVRR(VertexRunningRegulate insertObj) throws Exception {
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		try {
			String sql = "insert into agv_vertex_running_regulate(vrr_code,car_code,start_vertex_code,end_vertex_code,vrr_odo,vrr_start_time,vrr_end_time,vrr_status) values(?,?,?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, insertObj.getVrrCode());
			psmt.setString(2, insertObj.getCarCode());
			psmt.setString(3, insertObj.getStartVertexCode());
			psmt.setString(4, insertObj.getEndVertexCode());
			psmt.setDouble(5, insertObj.getVrrOdo());
			psmt.setDate(6, insertObj.getVrrStartTime());
			psmt.setDate(7, insertObj.getVrrEndTime());
			psmt.setString(8, insertObj.getVrrStatus());
			return psmt.executeUpdate() > 0 ? insertObj.getVrrCode() : "";
		} catch (SQLException e) {
			log.error("添加调度信息数据库操作异常,异常原因:【" + e.toString() + "】");
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, null);
		}
		return "";
	}

	/**
	 * 
	 * @Title: updateVrrByVrrId
	 * @Description: 更新调度日志
	 * @author 黄官易
	 * @param vrrCode
	 * @return
	 * @throws Exception
	 * @return int
	 * @date 2018年8月9日
	 * @version 1.0
	 */
	public int updateVrrByVrrId(String vrrCode) throws Exception {
		DruidPooledConnection conn = instance.getConnection();
		String sql = "update agv_vertex_running_regulate set vrr_status = ? where vrr_code = ?";
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "finish");
			psmt.setString(2, vrrCode);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			log.error("更新调度日志数据库操作异常,异常原因:【" + e.toString() + "】");
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, null);
		}
		return 0;
	}

}
