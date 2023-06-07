package com.sist.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.deptDAO;
import com.sist.vo.deptVO;

/**
 * Servlet implementation class listDept
 */
@WebServlet("/listDept")
public class listDept extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public listDept() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deptDAO dao = new deptDAO();
		ArrayList<deptVO> list = dao.findAll();
		
		request.setAttribute("list", list);
		//JSP에서 사용한 데이터를 상태유지한다.
		
		RequestDispacher dispacher = request.getRequestDispacher("listDept.jsp");
		//jsp로 이동시키기 위한 생성
		
		dispacher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
