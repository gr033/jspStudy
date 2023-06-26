package com.sist.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class ListBoardAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.selectAll();
		request.setAttribute("list", list);
		return "listBoard.jsp";
	}

}
