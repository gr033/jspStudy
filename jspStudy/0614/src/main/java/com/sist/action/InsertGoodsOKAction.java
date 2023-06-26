package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

public class InsertGoodsOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getRealPath("goods");
		System.out.println(path);
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "utf-8");
		String name = multi.getParameter("name");
		int price = Integer.parseInt(multi.getParameter("price"));
		int qty = Integer.parseInt(multi.getParameter("qty"));
		String fname = multi.getFilesystemName("fname");
		GoodsDAO dao = GoodsDAO.getInstance();
		GoodsVO g = new GoodsVO(0, name, price, qty, fname);
		int re = dao.insertGoods(g);
		if(re==1) {
			request.setAttribute("msg", "등록 성공");
		}
		else {
			request.setAttribute("msg", "등록 실패");
		}
		return "insertGoodsOK.jsp";
	}

}
