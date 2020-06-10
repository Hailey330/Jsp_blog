package com.cos.blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.util.HtmlParser;
import com.cos.blog.util.Script;

public class BoardSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("keyword") == null || request.getParameter("keyword").equals("")) {
			Script.back("검색 키워드가 없습니다.", response);
			return;
		}

		int page = Integer.parseInt(request.getParameter("page"));
		String keyword = request.getParameter("keyword");

		// 1. DB 연결해서 Board 목록 다 불러오기
		BoardRepository boardRepository = BoardRepository.getInstance();

		// 2. 3건만 페이징하여 가져오기
		List<Board> boards = boardRepository.findAll(page, keyword);

		// 3. 본문 짧게 가공하기
		for (Board board : boards) {
			String preview = HtmlParser.getContentPreview(board.getContent());
			board.setContent(preview);
		}

		// 4. request 에 담아서
		request.setAttribute("boards", boards);

		// 마지막 페이지 확인 로직
//		boolean lastPage = false;
		int count = boardRepository.count(keyword);
//		if(count <= (page*3)+3) {
//			lastPage = true;
//		}
		int lastPage = (count - 1) / 3;

		// 전체 페이지가 전체의 몇 프로인지
		double currentPercent = (double) (page) / (lastPage) * 100;
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("currentPercent", currentPercent);

		// 이전 페이지 정보
		HttpSession session = request.getSession();
		session.setAttribute("backPage", page);
		session.setAttribute("backKeyword", keyword);

		// 5. home.jsp 로 이동하기
		RequestDispatcher dis = request.getRequestDispatcher("home.jsp");
		dis.forward(request, response);
	}

}
