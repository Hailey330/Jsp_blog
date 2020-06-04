package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UsersRepository;
import com.cos.blog.util.Script;

public class UsersLoginProcAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 유효성 검사 
		if
		(
				request.getParameter("username").equals("") ||
				request.getParameter("username") == null || 
				request.getParameter("password").equals("") ||
				request.getParameter("password") == null 
		) {
			return;
		}
		
		// 1. 파라메터 받기 (x-www-form-urlenconded 라는 MIME 타입 Key = Value)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 2. DB 연결 - UsersRepository의 findByUsernameAndPassword() 호출
		UsersRepository usersRepository = UsersRepository.getInstance(); 
		Users user = usersRepository.findByUsernameAndPassword(username, password);
		
		if(user != null) {
			HttpSession session = request.getSession(); // 이미 만들어진 session 에 접근하기
			session.setAttribute("principal", user); // principle - 인증 주체 
			
			if(request.getParameter("remember") != null) {
				// key → Set-Cookie
				// value → remember=ssar
				Cookie cookie = new Cookie("remember", user.getUsername());
				response.addCookie(cookie);
			
				// response.setHeader("Set-Cookie", "remember=ssar");
			} else {
				Cookie cookie = new Cookie("remember", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
			Script.href("로그인 성공", "/blog/board?cmd=home", response);
		} else {
			Script.back("로그인 실패", response);
		}
	}
}
