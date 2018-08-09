package com.xh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.xh.entity.Edge;
import com.xh.util.SqlPoolUtil;

public class EdgeDao {

	private static final Logger log = LoggerFactory.getLogger(EdgeDao.class);
	private SqlPoolUtil instance = SqlPoolUtil.getInstance();

	/**
	 * 
	 * @Title: selectEdgeAll
	 * @Description: 查询所有的关联
	 * @author 黄官易
	 * @return
	 * @throws Exception
	 * @return List<Edge>
	 * @date 2018年8月8日
	 * @version 1.0
	 */
	public List<Edge> selectEdgeAll() throws Exception {
		List<Edge> edgeList = new ArrayList<Edge>();
		String sql = "select * from agv_egde";
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Edge edgeObj = new Edge();
				edgeObj.setEdgeCode(rs.getString("edge_code"));
				edgeObj.setEdgeName(rs.getString("edge_name"));
				edgeObj.setStartVertexCode(rs.getString("start_vertex_code"));
				edgeObj.setEndVertexCode(rs.getString("end_vertex_code"));
				edgeObj.setEdgeDistance(rs.getDouble("edge_distance"));
				edgeObj.setEdgeStatus(rs.getString("edge_status"));
				edgeObj.setCreateUserCode(rs.getString("create_user_code"));
				edgeObj.setCreateTime(rs.getDate("create_time"));
				edgeList.add(edgeObj);
			}
		} catch (SQLException e) {
			log.error("查询所有边数据库查询异常,异常原因:【" + e.toString() + "】");
		}
		return edgeList;
	}

	// 根据首/结点查询所有的文件信息
	public Edge selectEdgeByStartVertexCodeWithEndVertexCode(String startVertexCode, String endVertexCode)
			throws Exception {

		// 待定 测试sql语句

		return null;
	}

	/**
	 * 
	 * @Title: insertEdge
	 * @Description: 新增边（点点关系）
	 * @author 黄官易
	 * @param insertObj
	 * @return
	 * @throws Exception
	 * @return int
	 * @date 2018年8月8日
	 * @version 1.0
	 */
	public int insertEdge(Edge insertObj) throws Exception {
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		try {
			String sql = "insert into agv_edge(edge_code,edge_name,start_vertex_code,end_vertex_code,edge_distance,edge_status,create_user_code,create_time) values(?,?,?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, insertObj.getEdgeCode());
			psmt.setString(2, insertObj.getEdgeName());
			psmt.setString(3, insertObj.getStartVertexCode());
			psmt.setString(4, insertObj.getEndVertexCode());
			psmt.setDouble(5, insertObj.getEdgeDistance());
			psmt.setString(6, insertObj.getEdgeStatus());
			psmt.setString(7, insertObj.getCreateUserCode());
			psmt.setDate(8, insertObj.getCreateTime());
			return psmt.executeUpdate();
		} catch (SQLException e) {
			log.error("新增边数据库操作异常,异常原因:【" + e.toString() + "】");
			return 0;
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, null);
		}
	}

	// 逻辑计算层
	// 搜索相邻的所有节点除了之前的初始节点外
	public List<Edge> selectEdgeForInit(String currentVertexCode, String lastVertexCode) throws Exception {
		StringBuffer sql = new StringBuffer(
				"SELECT vertex_code,edge_status,edge_distance FROM (SELECT end_vertex_code AS vertex_code ,edge_status,edge_distance FROM agv_edge WHERE start_vertex_code =? UNION SELECT start_vertex_code AS vertex_code,edge_status,edge_distance FROM agv_edge WHERE end_vertex_code=?) a WHERE edge_status='normal' and vertex_code NOT IN (?)");
		DruidPooledConnection conn = instance.getConnection();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<Edge> result = new ArrayList<Edge>();
		try {
			psmt = conn.prepareStatement(sql.toString());
			psmt.setString(1, currentVertexCode);
			psmt.setString(2, currentVertexCode);
			psmt.setString(3, lastVertexCode);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Edge edge = new Edge();
				edge.setEndVertexCode(rs.getString("vertex_code"));
				edge.setEdgeDistance(rs.getDouble("edge_distance"));
				result.add(edge);
			}
		} catch (Exception e) {
			log.error("查询相邻顶点数据库操作异常,异常原因:【" + e.toString() + "】");
		} finally {
			SqlPoolUtil.closeConnection(conn, psmt, rs);
		}
		return result;
	}

}
