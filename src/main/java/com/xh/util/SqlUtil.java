package com.xh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtil {

	// 连接驱动
	private static String jdbcDriver;
	// 用户名
	private static String userName;
	// 用户密码
	private static String userPass;
	// 连接地址
	private static String url;
	// 连接对象
	// private static Connection conn = null;

	// 在内部定义一个私有的静态实例
	private static SqlUtil util = new SqlUtil();

	private SqlUtil() {
		// userName = "xh_f7_mes";
		// userPass = "xinhai0574";
		// jdbcDriver = "com.mysql.jdbc.Driver";
		// url =
		// "jdbc:mysql://192.168.0.100:3306/xh_f7_mes?useUnicode=true&characterEncoding=UTF8";
		userName = "root";
		userPass = "123456";
		jdbcDriver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost:3306/xh_sprider?useUnicode=true&characterEncoding=UTF8";
		// userName = "root";
		// userPass = "1234";
		// jdbcDriver = "com.mysql.jdbc.Driver";
		// url =
		// "jdbc:mysql://localhost:3306/xh_f7_sprider?useUnicode=true&characterEncoding=UTF8";
	}

	static {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动错误");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (util == null) {
			util = new SqlUtil();
		}
		// if (conn == null) {
		try {
			return DriverManager.getConnection(url, userName, userPass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		// }
		// return conn;
	}

	public static void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) {
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
