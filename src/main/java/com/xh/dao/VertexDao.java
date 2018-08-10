package com.xh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.xh.entity.Vertex;
import com.xh.util.SqlPoolUtil;

public class VertexDao {
	private static Logger log = LoggerFactory.getLogger(VertexDao.class);
	private SqlPoolUtil instance = SqlPoolUtil.getInstance();

	/**
	 * 
	 * @Title: selectVertexAll
	 * @Description: 查询全部的点
	 * @author 黄官易
	 * @return
	 * @throws Exception
	 * @return List<Vertex>
	 * @date 2018年8月8日
	 * @version 1.0
	 */
	public List<Vertex> selectVertexAll() throws Exception {
		List<Vertex> vertexList = new ArrayList<Vertex>();
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from agv_vertex";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Vertex vertexObj = new Vertex();
				vertexObj.setVerCode(rs.getString("ver_code"));
				vertexObj.setVerName(rs.getString("ver_name"));
				vertexObj.setVerFactoryCode(rs.getString("ver_factory_code"));
				vertexObj.setCreateUserName(rs.getString("create_user_name"));
				vertexObj.setCreateTime(rs.getDate("create_time"));
				vertexList.add(vertexObj);
			}
		} catch (SQLException e) {
			log.error("查询全部点数据库操作异常,异常原因:【" + e.toString() + "】");
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, rs);
		}
		return vertexList;
	}

	/**
	 * 
	 * @Title: selectVertexByVercode
	 * @Description: 查询特定的点根据点编码
	 * @author 黄官易
	 * @param vercode
	 * @return
	 * @throws Exception
	 * @return Vertex
	 * @date 2018年8月8日
	 * @version 1.0
	 */
	public Vertex selectVertexByVercode(String vercode) throws Exception {
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from agv_vertex where ver_code = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vercode);
			rs = psmt.executeQuery();
			if (rs.next()) {
				Vertex vertexObj = new Vertex();
				vertexObj.setVerCode(rs.getString("ver_code"));
				vertexObj.setVerName(rs.getString("ver_name"));
				vertexObj.setVerFactoryCode(rs.getString("ver_factory_code"));
				vertexObj.setCreateUserName(rs.getString("create_user_name"));
				vertexObj.setCreateTime(rs.getDate("create_time"));
				return vertexObj;
			}
		} catch (Exception e) {
			log.error("根据编码查询特定点数据库操作异常,异常原因:【" + e.toString() + "】");
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, rs);
		}
		return null;
	}

	/**
	 * 
	 * @Title: selectVertexById
	 * @Description: 查询特定的点根据id
	 * @author 黄官易
	 * @param vId
	 * @return
	 * @throws Exception
	 * @return Vertex
	 * @date 2018年8月8日
	 * @version 1.0
	 */
	public Vertex selectVertexById(int vId) throws Exception {
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from agv_vertex where id = ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				Vertex vertexObj = new Vertex();
				vertexObj.setVerCode(rs.getString("ver_code"));
				vertexObj.setVerName(rs.getString("ver_name"));
				vertexObj.setVerFactoryCode(rs.getString("ver_factory_code"));
				vertexObj.setCreateUserName(rs.getString("create_user_name"));
				vertexObj.setCreateTime(rs.getDate("create_time"));
				return vertexObj;
			}
		} catch (Exception e) {
			log.error("根据id查询特定点数据库操作异常,异常原因:【" + e.toString() + "】");
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, rs);
		}
		return null;
	}

	/**
	 * 
	 * @Title: insertVertex
	 * @Description: 新增点信息
	 * @param insertObj
	 * @return
	 * @throws Exception
	 * @return int
	 * @date 2018年8月10日
	 * @version 1.0
	 */
	public int insertVertex(Vertex insertObj) throws Exception {
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		try {
			String sql = "insert into agv_vertex(ver_code,ver_name,ver_factory_code,create_user_name,create_time) values(?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, insertObj.getVerCode());
			psmt.setString(2, insertObj.getVerName());
			psmt.setString(3, insertObj.getVerFactoryCode());
			psmt.setString(4, insertObj.getCreateUserName());
			psmt.setDate(5, insertObj.getCreateTime());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			log.error("新增点数据库操作异常,异常原因:【" + e.toString() + "】");
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, null);
		}
		return 0;
	}
}
