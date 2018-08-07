package com.xh.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

public class SqlPoolUtil {
	static Logger log = LoggerFactory.getLogger(SqlPoolUtil.class);
	private static SqlPoolUtil sqlPoolUtil = null;
	private static DruidDataSource druidDataSource = null;

	static {
		Properties properties = loadPropertyFile("/db.properties");
		try {
			druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties); // DruidDataSrouce工厂模式
		} catch (Exception e) {
			log.error("获取配置失败");
		}
	}

	/**
	 * 数据库连接池单例
	 * 
	 * @author 黄官易
	 * @date 2018年5月28日
	 * @version 1.0
	 * @return
	 */
	public static synchronized SqlPoolUtil getInstance() {
		if (null == sqlPoolUtil) {
			sqlPoolUtil = new SqlPoolUtil();
		}
		return sqlPoolUtil;
	}

	/**
	 * 返回druid数据库连接
	 * 
	 * @author 黄官易
	 * @date 2018年5月28日
	 * @version 1.0
	 * @return
	 * @throws SQLException
	 */
	public DruidPooledConnection getConnection() throws SQLException {
		return druidDataSource.getConnection();
	}

	/**
	 * 获取配置文件
	 * 
	 * @author 黄官易
	 * @date 2018年5月28日
	 * @version 1.0
	 * @param fullFile
	 * @return
	 */
	public static Properties loadPropertyFile(String fullFile) {
		if (null == fullFile || fullFile.equals(""))
			log.error("Properties file path can not be null : " + fullFile);
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = SqlPoolUtil.class.getResourceAsStream(fullFile);
			p = new Properties();
			p.load(inputStream);
		} catch (FileNotFoundException e) {
			log.error("Properties file not found: " + fullFile);
		} catch (IOException e) {
			log.error("Properties file can not be loading: " + fullFile);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	public static void closeConnection(DruidPooledConnection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException ex3) {
					ex3.printStackTrace();
				}
			}
		}
	}
}