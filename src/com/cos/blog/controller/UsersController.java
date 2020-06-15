package com.cos.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.action.user.UsersJoinAction;
import com.cos.blog.action.user.UsersJoinProcAction;
import com.cos.blog.action.user.UsersLoginAction;
import com.cos.blog.action.user.UsersLoginProcAction;
import com.cos.blog.action.user.UsersLogoutAction;
import com.cos.blog.action.user.UsersProfileUploadAction;
import com.cos.blog.action.user.UsersProfileUploadProcAction;
import com.cos.blog.action.user.UsersUpdateAction;
import com.cos.blog.action.user.UsersUpdateProcAction;
import com.cos.blog.action.user.UsersUsernameCheckAction;

// http://localhost:8000/blog/user
@WebServlet("/user")
public class UsersController extends HttpServlet {
	private final static String TAG = "UsersControll : ";
	private static final long serialVersionUID = 1L;
       
     
    public UsersController() {
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
		if(cmd.equals("join")) {
			// 회원가입 페이지로 이동 → controller, view 
			return new UsersJoinAction();
		} else if (cmd.equals("joinProc")) {
			// 회원가입 진행한 후에 index.jsp 를 응답  → controller, model, view
			return new UsersJoinProcAction();
		} else if (cmd.equals("update")) {
			// 회원수정 페이지로 이동 → 세션에 User 오브젝트를 가지고 있을 예정이니까 view
			return new UsersUpdateAction();
		} else if (cmd.equals("updateProc")) {
			// 회원수정 진행한 후에 index.jsp 로 이동
			return new UsersUpdateProcAction();
		} else if (cmd.equals("delete")) {
			// 회원수정 페이지에서 회원 탈퇴 버튼 누르면 회원 삭제를 진행. 로그아웃(세션 해제) 후 index.jsp 응답
		} else if (cmd.equals("login")) {
			// 회원 로그인 페이지로 이동 → view
			return new UsersLoginAction();
		} else if (cmd.equals("loginProc")) {
			// 회원 로그인을 진행한 후에 세션에 등록하고 index.jsp 로 이동
			return new UsersLoginProcAction();
		} else if (cmd.equals("logout")) {
			// 회원 로그아웃
			return new UsersLogoutAction();
		} else if (cmd.equals("usernameCheck")) {
			// 회원 가입 아이디 중복확인
			return new UsersUsernameCheckAction();
		} else if (cmd.equals("profileUpload")) {
			// 회원 프로필 사진 
			return new UsersProfileUploadAction();
		} else if (cmd.equals("profileUploadProc")) {
			// 회원 프로필 사진 업로드
			return new UsersProfileUploadProcAction();
		}
		return null;
	} 

}
