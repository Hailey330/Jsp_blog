<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<%
		//response.sendRedirect("/blog/board?cmd=home");
%>
		
		<!--한번 써보기-->
		<c:redirect url="/board?cmd=home&page=0" /> 