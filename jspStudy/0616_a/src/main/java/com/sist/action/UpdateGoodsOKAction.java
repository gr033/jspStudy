package com.sist.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

public class UpdateGoodsOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String path = request.getRealPath("goods");
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "utf-8");
		String oldFname = multi.getParameter("oldFname");
		String fname = multi.getFilesystemName("fname");
		GoodsVO g = new GoodsVO();
		g.setNo(Integer.parseInt(multi.getParameter("no")));
		g.setName(multi.getParameter("name"));
		g.setPrice(Integer.parseInt(multi.getParameter("price")));
		g.setQty(Integer.parseInt(multi.getParameter("qty")));
		g.setFname(oldFname);
		if(fname != null) {
			g.setFname(fname);
		}
		GoodsDAO dao = GoodsDAO.getInstance();
		int re = dao.updateGoods(g);
		if(re==1 && fname!=null) {
			File file = new File(path+"/"+oldFname);
			file.delete();
		}
		if(re==1) {
			request.setAttribute("msg", "상품 수정 성공");
		}else {
			File file = new File(path+"/"+fname);
			file.delete();
			request.setAttribute("msg", "상품 수정 실패");
		}
		return "updateGoodsOK.jsp";
	}

}
