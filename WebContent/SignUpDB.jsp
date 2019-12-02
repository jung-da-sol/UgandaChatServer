<%@ page import="com.db.SignUpDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	SignUpDB signupDB = SignUpDB.getInstance();

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");

	String returns = signupDB.signupDB(id, pw, name, email);
	System.out.println(returns);

	out.println(returns);
%>

