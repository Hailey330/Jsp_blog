package com.cos.blog.action.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.Script;

public class BoardDeleteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if (session.getAttribute("principal") == null) {
			Script.getMessage("잘못된 접근입니다.", response);
			return; // 여기서 return시 아래의 코드를 타고 내려감
		}
		
		if(
				request.getParameter("id") == null ||
				request.getParameter("id").equals("")
		) {
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		
		BoardRepository boardRepository =
				BoardRepository.getInstance();
		int result = boardRepository.deleteById(id);
		System.out.println("BoardDeleteAction : result : " + result);
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
