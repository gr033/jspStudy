package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class InsertBoardAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String headerline = "새글 작성";
		String title="";
		
		//일단 새 글이라 보고 no에 0을 저장
		int no = 0;
		
		//만약 답글달기라면 부모글의 글 번호를 no에 저장
		if(request.getParameter("no") != null) {
			no = Integer.parseInt(request.getParameter("no"));
			headerline = "답글 작성";
			BoardDAO dao = BoardDAO.getInstance();
			title = "답글>"+dao.findByNo(no, false).getTitle();
		}
		
		//부모글의 글 번호를 상태 유지
		request.setAttribute("no", no);
		request.setAttribute("headerline", headerline);
		request.setAttribute("title", title);
		return "insertBoard.jsp";
	}
}
