package com.java.expressage.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.expressage.exception.BaseException;
import com.java.expressage.exception.lnvaildParamException;
import com.java.expressage.privilege.LoginFilter;
import com.java.expressage.privilege.PrivilegeFilter;
import com.java.expressage.privilege.WhiteFilter;
import com.java.expressage.uilt.E;
import com.java.expressage.uilt.Global;
import com.java.expressage.uilt.NameUtil;
@WebServlet("/book/*")
@MultipartConfig
public class First extends HttpServlet{
	public static final String PROJECT_NAME = "/MavenProject/book/";
	private static  final String PACKAGE_NAME="com.java.expressage.controller";
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static Logger log=LogManager.getLogger(First.class);
	protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		//获取路径
		String uri = req.getRequestURI();
		//记录用户请求参数和请求路径
		log.info(req.getRemoteHost() + "请求地址：" + uri);
		//去掉路径中的项目名称
		uri = uri.replace(PROJECT_NAME, "");
        //		将请求路径存储到req
		req.setAttribute(Global.REQ_PATH, uri);
		//过滤链
		MyFilterChain chain = new MyFilterChain();		
		//白名单验证		
		MyFilter whiteFilter = new WhiteFilter();
		//登陆验证
		MyFilter loginFilter = new LoginFilter();
		//权限验证
		MyFilter privilegeFilter = new PrivilegeFilter();
		
	    chain.addFilter(whiteFilter).addFilter(loginFilter).addFilter(privilegeFilter);
		
		ApiResult apiResult = new ApiResult();
		try {
			chain.doFilter(req, resp);
			Enumeration<String> headers = req.getHeaderNames();
			while(headers.hasMoreElements()) {
				String key = (String)headers.nextElement();
				System.out.println(key + ":" + req.getHeader(key));
				
			}
	        //		使用/分割字符串，结果为数组
			String[] reqUri = uri.split("/");
			if(reqUri.length < 2) {
				throw new lnvaildParamException(E.PATH_ERROR_CODE,E.PATH_ERROR_INFO);
			}
			//名称转化
			String cat = reqUri[0];
			String opt = reqUri[1];
			cat = NameUtil.convert2Caml(cat);
			cat = NameUtil.firstUpper(cat);
			opt = NameUtil.convert2Caml(opt);
			String catName = PACKAGE_NAME + "." + cat + "Controller";
			//获取指定类的类对象
			Class<?> clx = Class.forName(catName);
			//创建对象实例
			Object instance = clx.newInstance();
			//创建要调用的方法
			Method method = clx.getMethod(opt, HttpServletRequest.class,HttpServletResponse.class);
			Object obj = method.invoke(instance, req,resp);
			if(obj != null) {
				apiResult.setData(obj);
			}
		}  catch (InvocationTargetException e) {
			Throwable targetE = e.getTargetException();
			if(targetE instanceof BaseException) {
				BaseException srcException = (BaseException) targetE;
				apiResult.setCode(srcException.getCode());
				apiResult.setMsg(srcException.getMessage());
			}else if(targetE instanceof PersistenceException) {
				e.printStackTrace();
				apiResult.setCode(E.SELF_DEFINE_ERROR_CODE);
				apiResult.setMsg("数据已存在");
			}else {
				e.printStackTrace();
				apiResult.setCode(500);
				apiResult.setMsg("程序内部错误");
			}
		} catch (ReflectiveOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.printStackTrace();
			apiResult.setCode(E.PATH_ERROR_CODE);
			apiResult.setMsg(E.PATH_ERROR_INFO);
		} catch (BaseException e) {
			e.printStackTrace();
			apiResult.setCode(e.getCode());
			apiResult.setMsg(e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			apiResult.setCode(500);
			apiResult.setMsg("程序内部错误");
		}
		String reStr = objectMapper.writeValueAsString(apiResult);
		log.info("返回用户数据："+reStr);
		resp.getWriter().write(reStr);
	}
	
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
}
