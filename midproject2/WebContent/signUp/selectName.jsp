<%@ page language="java" contentType="text/html; charset=UTF-8"
  	 import="java.sql.*,midproject2.EmpBean,midproject2.EmpDAO" %>
  	 
<%
EmpDAO EmpDAOObject = new EmpDAO();
StringBuffer str1 = EmpDAOObject.showId();
String[] strResult = str1.toString().split("@");
out.print(strResult[0]);
%>