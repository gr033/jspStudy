package com.sist.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.action.DeleteBoardAction;
import com.sist.action.DeleteBoardOKAction;
import com.sist.action.DetailBoardAction;
import com.sist.action.InsertBoardAction;
import com.sist.action.InsertBoardOKAction;
import com.sist.action.ListBoardAction;
import com.sist.action.SistAction;
import com.sist.action.UpdateBoardAction;
import com.sist.action.UpdateBoardOKAction;
import com.sist.dao.BoardDAO;

/**
 * Servlet implementation class SistController
 */
@WebServlet("*.do")
public class SistController extends HttpServlet {
	
	HashMap<String, SistAction> map = new HashMap<>();
	//사용자가 요청하는 서비스 명을 key로 하고 
	//일처리 담당객체를 value로 담기 위한 map을 
	//servlet(front controller)의 멤버 변수로 선언
	
	
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

//	init 메소드는 서블릿이 가동될 때 맨 처음 한번만 동작하는 메소드
//	프론트 컨트롤러(서블릿)가 가동될 때 맨 처음 동작하는 init 메서드에서
//	map을 생성하고 사용자의 요청별 처리 담당객체를 map에 등록
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		map = new HashMap<String, SistAction>();
		map.put("listBoard.do", new ListBoardAction());
		map.put("insertBoard.do", new InsertBoardAction());
		map.put("insertBoardOK.do", new InsertBoardOKAction());
		map.put("detailBoard.do", new DetailBoardAction());
		map.put("deleteBoard.do", new DeleteBoardAction());
		map.put("deleteBoardOK.do", new DeleteBoardOKAction());
		map.put("updateBoard.do", new UpdateBoardAction());
		map.put("updateBoardOK.do", new UpdateBoardOKAction());
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
		
		SistAction action = map.get(cmd);
		
		view = action.pro(request, response);
		
		RequestDispatcher dispatcher
		= request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}

}
