package com.rensu.education.hctms.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 手机登录操作
 * 
 * @date 2016年4月14日
 * @autor chen xiaoxiao
 */
@Controller
@RequestMapping(value="/loginapp")
public class LoginAppController {
	Logger log = Logger.getLogger(LoginAppController.class);
	
	/**
	 * 手机app登录。
	 * 跳转到wap页面或返回手机端相关内容
	 * @return
	 * @date 2016年4月14日
	 * @autor chen xiaoxiao
	 */
	@RequestMapping(value="/loginpage")
	public String appLogin(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("app controller receive visit...param:" + req.getParameter("param"));
		log.debug("app controller receive visit...param:" + req.getParameter("param"));
		
		return "app/login";
	}
	
	
	@RequestMapping("/login")
	public void applogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setContentType("text/html;charset=utf-8");
		((HttpServletResponse)res).addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write("{\"success\":false,\"code\":400}");

		
	}
	
}
