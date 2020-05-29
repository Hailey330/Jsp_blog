package com.cos.blog.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;

public class BoardHomeAction implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. DB 연결해서 Board 목록 다 불러오기 
		
		// 2. request 에 담아서 
		
		// 3. home.jsp 로 이동하기
		RequestDispatcher dis =
				request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
	}

}
