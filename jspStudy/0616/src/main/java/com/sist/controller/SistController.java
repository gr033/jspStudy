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
import javax.servlet.http.HttpSession;

import com.sist.action.SistAction;

/**
 * Servlet implementation class SistController
 */
@WebServlet("*.do")
public class SistController extends HttpServlet {
	HashMap<String, SistAction> map;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		map = new HashMap<String, SistAction>();
		String path = config.getServletContext().getRealPath("WEB-INF");
		try {
			FileReader fr = new FileReader(path+"/sist.properties");
			Properties prop = new Properties();
			prop.load(fr);
			Iterator key_list = prop.keySet().iterator();
			while(key_list.hasNext()) {
				String key = (String)key_list.next();
				String clsName = prop.getProperty(key);
				SistAction action = (SistAction)Class.forName(clsName).newInstance();
				map.put(key, action);
				System.out.println(key);
				System.out.println(action);
				System.out.println("--------------------------");
			}
			System.out.println(map);
			fr.close();
		} catch (Exception e) {
			System.out.println("init error: "+e.getMessage());
		}
		
	}

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
		// TODO Auto-generated method stub
		pro(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		pro(request, response);
	}

	private void pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		
//		0616/login.do
//		0616/member/login.do
		
		int first = uri.indexOf("/")+1;
		String cmd = uri.substring(uri.indexOf("/", first)+1);
		System.out.println(cmd);
		
//		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		SistAction action = map.get(cmd);
		System.out.println("action: "+action);
		System.out.println("cmd: "+cmd);
		String view = action.pro(request, response);
		if(view.equals("listGoods.jsp")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("listGoods.jsp");
		}else {
			//세션을 파기 한 다음 새로운 세션을 연결해야 하기 때문에 
			//response.sendredirect해야함
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
}
