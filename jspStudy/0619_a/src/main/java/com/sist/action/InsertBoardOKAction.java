package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

public class InsertBoardOKAction implements SistAction {

	@Override
	public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("keyword");
		session.removeAttribute("search_column");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		//새로운 글번호를 위한 메소드를 통하여 글번호를 발행
		int no = dao.getNextNo();
		
		int b_ref = no;

		//일단 새글이라고 가정
		int b_level = 0;
		int b_step = 0;
		request.setCharacterEncoding("utf-8");
		String path = request.getRealPath("board");
		System.out.println(path);
		
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "utf-8");
		
		//부모글의 글번호를 받아옴
		//새글이라면 0
		//답글이면 0이 아닌 값(부모글의 글번호)
		int pno = Integer.parseInt(multi.getParameter("no"));
		//만약 답글 작성이라면
		if(pno != 0) {
			
			//부모글의 정보를 가지고 온다.
			BoardVO p = dao.findByNo(pno, false);
			
			//부모글의 b_ref, b_level, b_step을 가지고 온다.
			b_ref = p.getB_ref();
			b_level = p.getB_level();
			b_step = p.getB_step();
			
			//이미 달려있는 답글들의 b_step을 1씩 증가한다.
			dao.updateStep(b_ref, b_step);
			
			//부모글의 b_level+1로 설정
			b_level++;
			
			//부모글의 b_step+1로 설정
			b_step++;
		}
		
		BoardVO b = new BoardVO();
		b.setNo(no);
		b.setTitle(multi.getParameter("title"));
		b.setWriter(multi.getParameter("writer"));
		b.setPwd(multi.getParameter("pwd"));
		b.setContent(multi.getParameter("content"));
		b.setFname(multi.getFilesystemName("fname"));
		b.setB_ref(b_ref);
		b.setB_level(b_level);
		b.setB_step(b_step);
		
		int re = dao.insertBoard(b);
		if(re==1) {
			request.setAttribute("msg", "등록 성공");
		}else {
			request.setAttribute("msg", "등록 실패");
		}
		
		return "insertBoardOK.jsp";
	}
}
