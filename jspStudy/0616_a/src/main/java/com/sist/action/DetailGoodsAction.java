package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.GoodsDAO;

public class DetailGoodsAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GoodsDAO dao = GoodsDAO.getInstance();
		int no = Integer.parseInt(request.getParameter("no"));
		request.setAttribute("g", dao.findByNo(no));
		return "detailGoods.jsp";
	}

}
