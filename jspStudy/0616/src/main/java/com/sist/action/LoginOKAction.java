package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.MemberDAO;

public class LoginOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String view = "loginOK.jsp";
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println("id="+id);
		System.out.println("pwd="+pwd);
		MemberDAO dao = MemberDAO.getInstance();
		int re = dao.isMember(id, pwd);
		if(re!=1) {
			view = "login.jsp";
			if(re==0) {
				request.setAttribute("msg", "비밀번호가 일치하지 않습니다.");
			}else if(re==-1) {
				request.setAttribute("msg", "존재하지 않는 아이디입니다.");
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		return view;
	}
}