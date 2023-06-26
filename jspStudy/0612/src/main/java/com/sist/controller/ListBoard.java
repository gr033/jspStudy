package com.sist.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

/**
 * Servlet implementation class ListBoard
 */
@WebServlet("/listBoard")
public class ListBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//jsp에서 사용자 요청에 따라 무엇을 원하는지 판펼하여 해당 모델에게 일을 맡긴다.
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.selectAll();
		
		//view에서 사용할 모델을 상태유지한다.
		request.setAttribute("list", list);
		
		String view = "listBoard.jsp";
		//컨트롤러(서블릿)에서 request에 상태유지를 한 상태로 jsp로 이동시키기 위한 객체
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		
		System.out.println("3. 상태유지하고 view페이지로 이동");
		//3. 상태유지하고 view페이지로 이동
		//뷰페이지(jsp)로 이동시킴
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
