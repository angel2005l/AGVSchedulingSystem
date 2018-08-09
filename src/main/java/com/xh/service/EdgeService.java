package com.xh.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xh.dao.EdgeDao;
import com.xh.entity.Edge;
import com.xh.util.DateUtil;
import com.xh.util.StrUtil;

public class EdgeService {
	private static Logger log = LoggerFactory.getLogger(EdgeService.class);

	private EdgeDao dao = new EdgeDao();

	public void addEdge() {
		Edge insertObj = new Edge();
		insertObj.setEdgeCode(
				"EDG" + DateUtil.curDateYMDHMSForService() + StrUtil.getRandom((int) (Math.random() * 1000), 4));
		insertObj.setEdgeName("线路_6");
		insertObj.setStartVertexCode("VER201808081559530479");
		insertObj.setEndVertexCode("VER201808081559530160");
		insertObj.setEdgeDistance(1d);
		insertObj.setEdgeStatus("normal");
		insertObj.setCreateUserCode("新海管理员");
		insertObj.setCreateTime(new Date(System.currentTimeMillis()));
		try {
			dao.insertEdge(insertObj);
		} catch (Exception e) {
			log.error("添加边（关联关系）异常,异常原因:【" + e.toString() + "】");
			e.printStackTrace();
		}
	}

	// 建立模型
	public List<Edge> initEdges(String currentVertexCode, String lastVertexCode, String endVertexCode, String tempStr,
			double tempDistance, List<Edge> roots) {
		// 校验数据 当前顶点与目标顶点不能为空
		if (StrUtil.isBlank(currentVertexCode) || StrUtil.isBlank(endVertexCode)) {
			return null;
		}
		String str = tempStr;
		double distance = tempDistance;
		// 1.查询当前顶点除了历史顶点外的所有相邻顶点
		try {
			List<Edge> result = dao.selectEdgeForInit(currentVertexCode, lastVertexCode);
			if (result.isEmpty()) {// 当无相邻顶点
				// 当前顶点为目标顶点时
				if (currentVertexCode.equals(endVertexCode)) {
					// 将节点保存 结束
					str += currentVertexCode;
					Edge edge = new Edge();
					edge.setEndVertexCode(str);
					edge.setEdgeDistance(distance);
					roots.add(edge);
				}
				/*
				 * else {// 反之结束，这是一条无效路径 System.err.println("无效路径"); }
				 */
			} else {// 不为空
					// 将当前节点保存
				if (str.indexOf(currentVertexCode) == -1 || currentVertexCode.equals(endVertexCode)) {//
					if (StrUtil.notBlank(lastVertexCode) && currentVertexCode.equals(endVertexCode)) {
						str += currentVertexCode;
						Edge edge = new Edge();
						edge.setEndVertexCode(str);
						edge.setEdgeDistance(distance);
						roots.add(edge);
					} else {
						str += currentVertexCode + ",";
						for (Edge obj : result) {// 再次执行递归算法
							initEdges(obj.getEndVertexCode(), currentVertexCode, endVertexCode, str,
									(distance + obj.getEdgeDistance()), roots);
						}
					}
				}
			}

		} catch (Exception e) {
			log.error("数据");
		}
		return roots;
	}

	public static void main(String[] arg) {
		EdgeService service = new EdgeService();
		List<Edge> initEdges = service.initEdges("VER201808081559530532", "", "VER201808081559530975", "", 0d,
				new ArrayList<Edge>());
		if (null != initEdges && initEdges.isEmpty()) {
			return;
		}
		for (Edge obj : initEdges) {
			System.err.println("路线" + obj.getEndVertexCode());
			System.err.println("路线公里数" + obj.getEdgeDistance());
		}

	}

}
