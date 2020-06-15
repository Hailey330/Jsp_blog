package com.cos.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.action.reply.ReplyWriteProcAction;
import com.cos.blog.action.reply.ReplydeleteProcAction;

// http://localhost:8000/blog/board
@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private final static String TAG = "ReplyController : ";
	private static final long serialVersionUID = 1L;
       
    
    public ReplyController() {
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
			// http://localhost:8000/blog/user?cmd=join
			String cmd = request.getParameter("cmd");
			System.out.println(TAG + "router : " + cmd);
			Action action = router(cmd);
			action.execute(request, response);
	}
	
	public Action router(String cmd) {
		if(cmd.equals("writeProc")) {
			return new ReplyWriteProcAction(); // Board 의 목록 
		} else if(cmd.equals("deleteProc")) {
			return new ReplydeleteProcAction(); // 댓글 삭제하기
		}
		return null;
	} 

}
