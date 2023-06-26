package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.GoodsDAO;

public class ListGoodsAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String keyword = null;
		
		HttpSession session = request.getSession();
		if(session.getAttribute("keyword") != null) {
			keyword = (String)session.getAttribute("keyword");
		}
		
		if(request.getParameter("keyword") != null) {
			keyword = request.getParameter("keyword");
		}
		
		
		int pageNUM = 1;
		if(request.getParameter("pageNUM") != null) {
			pageNUM= Integer.parseInt(request.getParameter("pageNUM"));
		}
		GoodsDAO dao = GoodsDAO.getInstance();
		
		System.out.println("pageNUM:"+pageNUM);
		System.out.println("totalRecord:"+dao.totalRecord);
		System.out.println("totalPage:"+dao.totalPage);
		
		
		request.setAttribute("list", dao.findAll(pageNUM, keyword));
		String str = "";
		for(int i=1; i<=GoodsDAO.totalPage; i++) {
			str += "<a href='listGoods.do?pageNUM="+i+"'>"+i+"</a> ";
		}
		
		System.out.println("str:"+str);
		request.setAttribute("pageStr", str);
		session.setAttribute("keyword", keyword);
		
		return "listGoods.jsp";
	}

}
