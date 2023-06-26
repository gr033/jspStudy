package com.sist.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.action.SistAction;
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
		
		try {
			//설정파일이 있는 WEB-INF의 실 경로를 읽어 옴
			String path = config.getServletContext().getRealPath("WEB-INF");
			
			System.out.println(path);
			
			//설정파일을 메모리로 읽어들이기 위한 파일리더 객체를 생성
			FileReader reader = new FileReader(path+"/sist.properties");
			
			System.out.println(reader);
			
			//설정파일의 key와 value를 분리하기 위한 Properties 객체를 생성
			Properties prop = new Properties();
			
			//설정파일의 내용을 Properties 객체로 읽어옴
			prop.load(reader);
			
			//Properties 객체에 key를 모두 읽어와 반복문을 수행시키기 위해 iterator로 만들어줌
			Iterator iter = prop.keySet().iterator();
			
			//key가 있는만큼 반복 실행
			while(iter.hasNext()) {
				
				//서비스명, key를 뽑아 옴
				String cmd = (String)iter.next();
				
				//클래스명, key에 해당하는 클래스 이름을 뽑아 옴
				String clsName = prop.getProperty(cmd);
				
				System.out.println(cmd);
				System.out.println(clsName);
				
				//map에 등록하기 위하여 문자열로 된 클래스 이름에 해당하는 객체를 생성
				SistAction action = (SistAction) Class.forName(clsName).newInstance();
				
				//서비스명과 그 처리를 위한 객체를 맵에 등록
				map.put(cmd, action);
				System.out.println("_____________________");
			}
			System.out.println(map);
			reader.close();
		} catch (Exception e) {
			System.out.println("SistController error: "+e.getMessage());
		}
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
		
		System.out.println(cmd);
		
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
