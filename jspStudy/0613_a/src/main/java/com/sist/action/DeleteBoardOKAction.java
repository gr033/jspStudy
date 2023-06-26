package com.sist.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sist.dao.BoardDAO;

public class DeleteBoardOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		int no = Integer.parseInt(request.getParameter("no"));
		String fname = dao.selectBoard(no).getFname();
		String pwd = request.getParameter("pwd");
		int re = dao.deleteBoard(no, pwd);
		if(re==1) {
			String path = request.getRealPath("board");
			File file = new File(path+"/"+fname);
			file.delete();
			request.setAttribute("msg", "삭제 성공");
		}else {
			request.setAttribute("msg", "삭제 실패");
		}
		return "deleteBoardOK.jsp";
	}

}
