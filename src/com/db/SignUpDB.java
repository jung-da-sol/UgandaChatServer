package com.db;

import java.sql.*;;

public class SignUpDB {
	private static SignUpDB instance = new SignUpDB();

	public static SignUpDB getInstance() {
		return instance;
	}

	private SignUpDB() {
	}

	String jdbcUrl = "jdbc:oracle:thin:@192.168.15.116:1521:orcl";
	String userId = "uganda";
	String userPw = "uganda";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "";
	String returns = "회원가입 실패";

	public synchronized String signupDB(String id, String pwd, String name, String email) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "INSERT INTO member VALUES(?,sys.pkg_crypto.encrypt(?),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			System.out.println(id);
			pstmt.setString(2, pwd);
			System.out.println(pwd);
			pstmt.setString(3, name);
			System.out.println(name);
			pstmt.setString(4, email);
			System.out.println(email);
			int member = pstmt.executeUpdate();
			if (member == 1) {
				returns = "회원 가입 성공!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			returns = "회원가입 실패";
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
