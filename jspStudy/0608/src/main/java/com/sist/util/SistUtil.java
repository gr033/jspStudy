package com.sist.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class SistUtil {
	public static String getCookie(HttpServletRequest request, String name) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals(name)) {
					value = c.getValue();
					break;
				}
			}	
		}
		return value;
	}
}
