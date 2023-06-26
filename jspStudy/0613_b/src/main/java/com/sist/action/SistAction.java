package com.sist.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//앞으로 만들 모든 action들이 반드시 오버라이딩 해야할 메소드 pro를 일반화
public interface SistAction {
	public String pro(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
