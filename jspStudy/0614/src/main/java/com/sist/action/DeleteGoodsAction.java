package com.sist.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.GoodsDAO;

public class DeleteGoodsAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no = Integer.parseInt(request.getParameter("no"));
		GoodsDAO dao = GoodsDAO.getInstance();
		String fname = dao.findByNo(no).getFname();
		int re = dao.deleteGoods(no);
		if(re==1) {
			String path = request.getRealPath("goods");
			File file = new File(path+"/"+fname);
			file.delete();
			request.setAttribute("msg", "삭제 성공");
		}else {
			request.setAttribute("msg", "삭제 실패");
		}
		return "deleteGoodsOK.jsp";
	}

}
