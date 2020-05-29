package com.cos.blog.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	
	// 뒤로 가기 
	public static void back(String msg, HttpServletResponse response) { 
		try {
			response.setCharacterEncoding("UTF-8"); // 실제 인코딩을 UTF-8로 해주고
			response.setContentType("text/html;charset=UTF-8"); // 받는 쪽에서 UTF-8로 해석할 수 있도록 알려줌
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("history.back();");
			out.println("</script>");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 페이지 이동시 메시지 O
	public static void href(String msg, String uri, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("location.href='" + uri + "';");
			out.println("</script>");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 페이지 이동시 메시지 X
	public static void href(String uri, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("location.href='" + uri + "';");
			out.println("</script>");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
