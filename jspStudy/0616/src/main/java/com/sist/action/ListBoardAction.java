package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.BoardDAO;

public class ListBoardAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		String all = request.getParameter("all");
		System.out.println("all: "+all);
		
		//request.getparameter가 session보다 우선순위가 높아 설정 필요
		//(새로운 검색어를 입력할 수도 있으니까 getparameter를 나중에 설정)
		String keyword = null;
		String search_column=null;
		if(session.getAttribute("keyword") != null) {
			keyword = (String)session.getAttribute("keyword");
			search_column = (String)session.getAttribute("search_column");
		}
		
		if(request.getParameter("keyword") != null) {
			keyword = request.getParameter("keyword");
			search_column = request.getParameter("searchColumn");
		}
		
		//우선순위가 제일 높아야 하므로 맨 뒤에 입력
		if(all != null && all.equals("yes")) {
			keyword="";
		}
		
		System.out.println("검색카테고리:" + search_column);
		System.out.println("키워드: "+keyword);
		
		// TODO Auto-generated method stub
		BoardDAO dao = BoardDAO.getInstance();
		int pageNUM = 1;
		if(request.getParameter("pageNUM") != null) {
			pageNUM = Integer.parseInt(request.getParameter("pageNUM"));
		}
		System.out.println(pageNUM+"pageNUM");
		request.setAttribute("list", dao.findAll(pageNUM, keyword, search_column));
		request.setAttribute("totalPage", dao.totalPage);
	
		//검색어를 세션에 상태유지
		session.setAttribute("keyword", keyword);
		session.setAttribute("search_column", search_column);
		
		return "listBoard.jsp";
	}
}