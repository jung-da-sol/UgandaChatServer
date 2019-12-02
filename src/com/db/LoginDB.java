package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDB {
	private static LoginDB instance = new LoginDB();

	public static LoginDB getInstance() {
		return instance;
	}

	public LoginDB() {
	}

	String jdbcUrl = "jdbc:oracle:thin:@192.168.15.116:1521:orcl";
	String userId = "uganda";
	String userPw = "uganda";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String sql = "";
	String sql2 = "";
	String returns = "�������";

	public synchronized String loginDB(String id, String pwd) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "SELECT sys.pkg_crypto.decrypt(pw), name FROM Member WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println(rs.getRow()+" "+id);

			if (rs.next()) {
				String pw = rs.getString(1);
				System.out.println(pw);
				System.out.println(new String(rs.getString(2).getBytes(),"UTF-8")+":�α����� �����Ǿ����ϴ�.");
				if (pw.equals(pwd)) {return rs.getString(2)+":�α����� �����Ǿ����ϴ�.";	} // �α��� ���� �޽��� �տ� ����� �̸��� ����
				else {return "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";	}
			}else {
				return "���̵� �������� �ʽ��ϴ�.";
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
