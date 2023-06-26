package com.sist.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class UpdateBoardOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String path = request.getRealPath("board");
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "utf-8");
		String oldFname = multi.getParameter("oldFname");
		String fname = multi.getFilesystemName("fname");
		BoardVO b = new BoardVO();
		b.setNo(Integer.parseInt(multi.getParameter("no")));
		b.setContent(multi.getParameter("content"));
		b.setTitle(multi.getParameter("title"));
		b.setPwd(multi.getParameter("pwd"));
		b.setWriter(multi.getParameter("writer"));
		b.setFname(oldFname);
		if(fname != null) {
			b.setFname(fname);
		}
		BoardDAO dao = new BoardDAO();
		int re = dao.updateBoard(b);
		if(re==1 && fname != null) {
			File file = new File(path+"/"+oldFname);
			file.delete();
		}
		
		if(re==1) {
			request.setAttribute("msg", "게시물 수정 성공");
		}else {
			request.setAttribute("msg", "게시물 수정 실패");
		}
		return "updateBoardOK.jsp";
	}

}
