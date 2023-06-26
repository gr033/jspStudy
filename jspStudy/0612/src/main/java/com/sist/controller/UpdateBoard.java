package com.sist.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

/**
 * Servlet implementation class updateBoard
 */
@WebServlet("/updateBoard")
public class UpdateBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = new BoardDAO();
		BoardVO b = dao.selectBoard(no);
		request.setAttribute("b", b);
		String view = "updateBoard.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String path = request.getRealPath("board");
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "utf-8");
		String oldFname = multi.getParameter("oldFname");
		BoardVO b = new BoardVO();
		b.setNo(Integer.parseInt(multi.getParameter("no")));
		b.setTitle(multi.getParameter("title"));
		b.setPwd(multi.getParameter("pwd"));
		b.setContent(multi.getParameter("content"));
		b.setFname(oldFname);
		String fname = multi.getFilesystemName("fname");
		
		if(fname!=null) {
			b.setFname(fname);
		}
		BoardDAO dao = new BoardDAO();
		int re = dao.updateBoard(b);
		//수정에 성공했고 사진도 수정했다면 원래 사진은 삭제
		if(re==1 && fname!=null) {
			File file = new File(path+"/"+oldFname);
			file.delete();
		}
		String view = "updateBoardOK.jsp";
		if(re==1) {
			request.setAttribute("msg", "수정에 성공하였습니다.");
		}else {
			request.setAttribute("msg", "수정에 실패하였습니다.");
			File file = new File(path+"/"+fname);
			file.delete();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
