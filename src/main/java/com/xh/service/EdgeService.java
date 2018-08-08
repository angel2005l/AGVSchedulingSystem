package com.xh.service;

import java.sql.Date;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] arg) {
		EdgeService service = new EdgeService();
		service.addEdge();
	}

}
