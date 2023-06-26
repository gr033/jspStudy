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
 * Servlet implementation class SistController
 */
@WebServlet("*.do")
public class SistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SistController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pro(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		pro(request,response);
	}
	
	public void pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		BoardDAO dao = new BoardDAO();
		String view  = "";
		request.setCharacterEncoding("utf-8");
		String path = request.getRealPath("board");
		System.out.println("path:"+path);
		
		if(cmd.equals("listBoard.do")) {
			request.setAttribute("list", dao.selectAll());
			view = "listBoard.jsp";
		}else if(cmd.equals("insertBoard.do")) {
			view = "insertBoard.jsp";
		}else if(cmd.equals("insertBoardOK.do")) {
			MultipartRequest multi 
			= new MultipartRequest(request, path, 1024*1024*5,"utf-8");
			BoardVO b = new BoardVO();
			b.setTitle(multi.getParameter("title"));
			b.setWriter(multi.getParameter("writer"));
			b.setPwd(multi.getParameter("pwd"));
			b.setContent(multi.getParameter("content"));
			b.setFname(multi.getFilesystemName("fname"));
			if( dao.insertBoard(b) == 1 ) {
				request.setAttribute("msg", "게시물 등록에 성공하였습니다.");
			}else {
				request.setAttribute("msg", "게시물 등록에 실패하였습니다.");
			}
			view = "insertBoardOK.jsp";
			
		}else if(cmd.equals("detailBoard.do")) {
			int no = Integer.parseInt(request.getParameter("no"));
			request.setAttribute("b", dao.selectBoard(no));
			view = "detailBoard.jsp";
		}else if(cmd.equals("updateBoard.do")) {
			
		}else if(cmd.equals("updateBoardOK.do")) {
			
		}else if(cmd.equals("deleteBoard.do")) {
			
		}else if(cmd.equals("deleteBoardOK.do")) {
			
		}
		
		RequestDispatcher dispatcher
		= request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}

}
