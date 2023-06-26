package com.sist.controller;

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
 * Servlet implementation class InsertBoardOK
 */
@WebServlet("/insertBoardOK")
public class InsertBoardOK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBoardOK() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String path = request.getRealPath("board");
		System.out.println(path);
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "UTF-8");
		BoardVO b = new BoardVO();
		b.setTitle(multi.getParameter("title"));
		b.setWriter(multi.getParameter("writer"));
		b.setContent(multi.getParameter("content"));
		b.setPwd(multi.getParameter("pwd"));
		b.setFname(multi.getFilesystemName("fname"));
		BoardDAO dao = new BoardDAO();
		int re = dao.insertBoard(b);
		String view = "insertBoardOK.jsp";
		
		if(re==1) {
			request.setAttribute("msg", "게시물 등록에 성공하였습니다.");
			request.setAttribute("b", b);
		}else {
			request.setAttribute("msg", "게시물 등록에 실패하였습니다.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}

}
