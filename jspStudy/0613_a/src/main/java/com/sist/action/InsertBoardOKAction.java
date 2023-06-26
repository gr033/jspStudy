package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class InsertBoardOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		BoardVO b = new BoardVO();
		String path = request.getRealPath("board");
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "utf-8");
		b.setTitle(multi.getParameter("title"));
		b.setWriter(multi.getParameter("writer"));
		b.setContent(multi.getParameter("content"));
		b.setFname(multi.getFilesystemName("fname"));
		b.setPwd(multi.getParameter("pwd"));
		dao.insertBoard(b);
		return "insertBoardOK.jsp";
	}

}
