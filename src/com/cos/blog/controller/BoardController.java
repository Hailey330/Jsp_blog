package com.cos.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.action.board.BoardDeleteAction;
import com.cos.blog.action.board.BoardDetailAction;
import com.cos.blog.action.board.BoardHomeAction;
import com.cos.blog.action.board.BoardSearchAction;
import com.cos.blog.action.board.BoardUpdateAction;
import com.cos.blog.action.board.BoardUpdateProcAction;
import com.cos.blog.action.board.BoardWriteAction;
import com.cos.blog.action.board.BoardWriteProcAction;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;
import com.cos.blog.action.user.UsersLoginAction;

// http://localhost:8000/blog/board
@WebServlet("/board")
public class BoardController extends HttpServlet {
	private final static String TAG = "UsersControll : ";
	private static final long serialVersionUID = 1L;
       
    
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		router(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		router(request, response);
	}
	
	protected void router(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// http://localhost:8000/blog/user?cmd=join
			String cmd = request.getParameter("cmd");
			System.out.println(TAG + "router : " + cmd);
			Action action = router(cmd);
			action.execute(request, response);
	}
	
	public Action router(String cmd) {
		if(cmd.equals("home")) {
			// home 이동 
			return new BoardHomeAction(); // Board 의 목록 
		} else if(cmd.equals("write")) {
			return new BoardWriteAction(); // 글쓰기 화면
		} else if(cmd.equals("writeProc")) {
			return new BoardWriteProcAction(); // 글쓰기
		} else if(cmd.equals("detail")) {
			return new BoardDetailAction(); // 상세보기
		} else if(cmd.equals("update")) {
			return new BoardUpdateAction(); // 수정 페이지
		} else if(cmd.equals("updateProc")) {
			return new BoardUpdateProcAction(); // 수정 페이지
		} else if(cmd.equals("delete")) {
			return new BoardDeleteAction(); // 삭제하기
		} else if(cmd.equals("search")) {
			return new BoardSearchAction(); // 검색하기
		}
		return null;
	} 

}
