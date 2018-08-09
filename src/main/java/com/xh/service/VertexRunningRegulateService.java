package com.xh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xh.dao.VertexRunningRegulateDao;
import com.xh.entity.VertexRunningRegulate;
import com.xh.util.DateUtil;
import com.xh.util.StrUtil;

public class VertexRunningRegulateService {
	private static final Logger log = LoggerFactory.getLogger(VertexRunningRegulateService.class);
	private VertexRunningRegulateDao vvrDao = new VertexRunningRegulateDao();

	/**
	 * 
	 * @Title: clockEdge
	 * @Description: 模拟小车通过edge（边）
	 * @author 黄官易
	 * @param startVertexCode
	 * @param endVertexCode
	 * @param carCode
	 * @param odo
	 * @return
	 * @return boolean
	 * @date 2018年8月9日
	 * @version 1.0
	 */
	public boolean clockEdge(String startVertexCode, String endVertexCode, String carCode, double odo) {
		synchronized (this) {
			try {
				// 当前运行路径是否可以通过
				VertexRunningRegulate vvrObj = vvrDao.selectVRRByStartVertexCodeAndEndVertexCode(startVertexCode,
						endVertexCode);
				if (null != vvrObj) {
					return false;
				} else {
					vvrObj = new VertexRunningRegulate();

					vvrObj.setVrrCode("VVR" + DateUtil.curDateYMDHMSForService()
							+ StrUtil.getRandom((int) (Math.random() * 1000), 4));
					vvrObj.setCarCode(carCode);
					vvrObj.setStartVertexCode(startVertexCode);
					vvrObj.setEndVertexCode(endVertexCode);
					vvrObj.setVrrOdo(odo);
					vvrObj.setVrrStatus("process");
					String vvrCode = vvrDao.insertVRR(vvrObj);
					vvrDao.updateVrrByVrrId(vvrCode);
				}
			} catch (Exception e) {
				log.error("模拟小车通过edge（边）异常,异常原因:【" + e.toString() + "】");
			}
		}
		return true;
	}

}
