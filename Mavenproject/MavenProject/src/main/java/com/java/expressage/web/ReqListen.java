package com.java.expressage.web;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.java.expressage.uilt.D;


@WebListener
public class ReqListen implements ServletRequestListener{

	public void requestDestroyed(ServletRequestEvent sre) {
		D.closeConn();
	}
	public void requestInitialized(ServletRequestEvent sre) {
		D.getConn();
	}
}
