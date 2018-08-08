package com.xh.service;

import java.sql.Date;

import com.xh.dao.VertexDao;
import com.xh.entity.Vertex;
import com.xh.util.DateUtil;
import com.xh.util.StrUtil;

public class VertexService {
	private VertexDao dao = new VertexDao();

	public void addVertex() {
		for (int i = 1; i < 11; i++) {
			Vertex insertObj = new Vertex();
			insertObj.setVerCode("VER" + DateUtil.curDateYMDHMSForService() + StrUtil.getRandom((int) (Math.random() * 1000), 4));
			insertObj.setVerName("站点_"+i);
			insertObj.setVerFactoryCode("FAC201808081550364158");
			insertObj.setCreateUserName("新海管理员");
			insertObj.setCreateTime(new Date(System.currentTimeMillis()));
			try {
				dao.insertVertex(insertObj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}

	}
	

}
