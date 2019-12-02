package com.db;

import java.sql.*;

public class FindPW {

	private static FindPW instance = new FindPW();

	public static FindPW getInstance() {
		return instance;
	}

	public FindPW() {
	}

	// oracle ����
	String jdbcUrl = "jdbc:oracle:thin:@192.168.15.116:1521:orcl";
	String userId = "uganda";
	String userPw = "uganda";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "";
	String returns = "pwã�� ����";

	public synchronized String findPW(String id) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "SELECT sys.pkg_crypto.decrypt(pw) FROM member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println(rs.getRow() + " ");

			if (rs.next()) {
				String pw = rs.getString(1);
				returns = pw;
			} else {
				returns = "pwã�� ����";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return returns;
	}
}
