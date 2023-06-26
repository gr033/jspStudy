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

/**
 * Servlet implementation class SistController
 */
@WebServlet("*.do")
public class SistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//사용자의 요청별 일처리 담당객체를 담아두기 위한 맵을 선언
	public HashMap<String, SistAction> map;
	
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
		// TODO Auto-generated method stub
		pro(request, response);
	}

    //init은 사용자의 *.do 패턴의 최초의 요청일 때에 한번만 동작하도록 하는 메소드
    //그 때에 맵을 생성하고 사용자의 요청별 일처리 담당 객체가 담겨진(WEB-INF/sist.properties)
    //파일의 내용을 읽어 맵에 등록하도록 한다.
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		//map을 생성
		map = new HashMap<String, SistAction>();
		
		//WEB-INF의 실제 경로를 알아온다.
		String path = config.getServletContext().getRealPath("WEB-INF");
		
		//file의 내용을 읽어들여야 하니 예외처리를 한다.
		try {
			//sist.properties의 파일을 메모리로 읽어들이기 위하여 스트림을 생성
			FileReader fr = new FileReader(path+"/sist.properties");
			
			//파일의 내용을 key와 value로 분리하기 위한 properties 객체를 생성
			Properties prop = new Properties();
			
			//파일의 내용을 properties 객체로 읽어들임
			//이 때에 key는 key대로 value는 value대로 분리
			prop.load(fr);
			
			//properties 객체로부터 모든 key를 가지고 오는 메소드가 keySet()
			//이것을 반복 수행시키기 위해서 iterator로 변환
			Iterator iter = prop.keySet().iterator();
			
			//key의 목록이 있는 iter에 데이터가 있는 만큼 반복 실행
			//hasNext는 다음 데이터가 있으면 true 없으면 false를 반환
			while(iter.hasNext()) {
				
				//next()메소드는 key목록이 담겨진 iterator로부터 데이터를 하나씩 꺼내옴
				//next()는 Object를 반환하기 때문에 String으로 형변환

				//key : listBook.do
				String key = (String)iter.next();
				
				//ex) clsName : com.sist.action.ListBookAction
				String clsName = prop.getProperty(key);
				
				//문자열로 된 클래스 이름에 해당하는 객체를 생성하기 위하여
				//Class.forName().newInstance() 메소드를 이용
				//newInstance()는 Object를 반환하기 때문에 SistAction으로 형변환
				SistAction action = (SistAction)Class.forName(clsName).newInstance();
				
				//map에 사용자가 요청할 서비스명과 그에 따른 일처리 담당을 수행하게 될 객체를 map에 등록
				map.put(key, action);
			}
			fr.close();
		} catch (Exception e) {
			System.out.println("init error: "+e.getMessage());
		}
	}

	private void pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		SistAction action = map.get(cmd);
		String view = action.pro(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		pro(request, response);
	}

}
