package com.cos.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.action.board.BoardDeleteAction;
import com.cos.blog.action.board.BoardDetailAction;
import com.cos.blog.action.board.BoardHomeAction;
import com.cos.blog.action.board.BoardSearchAction;
import com.cos.blog.action.board.BoardUpdateAction;
import com.cos.blog.action.board.BoardUpdateProcAction;
import com.cos.blog.action.board.BoardWriteAction;
import com.cos.blog.action.board.BoardWriteProcAction;
import com.cos.blog.action.kakao.KakaoCallbackAction;

@WebServlet("/oauth/kakao")
public class KakaoController extends HttpServlet {
	private final static String TAG = "UsersControll : ";
	private static final long serialVersionUID = 1L;
       
    
    public KakaoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			session.setAttribute("path", request.getContextPath());
			
			// cmd 에는 callBack 들어와서 router 에서는 callBack 타기
			String cmd = request.getParameter("cmd");
			System.out.println(TAG + "router : " + cmd);
			Action action = router(cmd);
			action.execute(request, response);
	}
	
	public Action router(String cmd) {
		if(cmd.equals("callback")) { 
			return new KakaoCallbackAction(); 
		}
		return null;
	} 

}
