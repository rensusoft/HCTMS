package com.rensu.education.hctms.util.tag;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.util.Consts;


public class SessionFilter implements Filter {

	@Override
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
//			HttpServletResponse httpResponse = (HttpServletResponse) response;
//			HttpSession session = httpRequest.getSession();
			// 登陆url
			String loginUrl = httpRequest.getContextPath();
			String url = httpRequest.getRequestURI();
			String path = url.substring(url.lastIndexOf("/"));
			if((loginUrl+"/").equals(url)||"/showSelRole.action".equals(path)||"/loginbyauth.action".equals(path)||"/loginbyusercodeandpass.action".equals(path)
					||"/logout.action".equals(path)
					||"/uploadHeadImgs.action".equals(path)
					/*下面-----App的action都不要拦截--------- */
					||path.indexOf("Vapp_")>-1){
				chain.doFilter(request, response);
				return;
			}else{
				LoginBean loginBean = (LoginBean) httpRequest.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
				if(loginBean==null||loginBean.getvUserDetailInfo()==null){
						String str = "<script language='javascript'>alert('页面已过期，请重新登录！');"
								+ "window.top.location.href='"
								+ loginUrl
								+ "';</script>";
						response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
					try {
						PrintWriter writer = response.getWriter();
						writer.write(str);
						writer.flush();
						writer.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
//				// 超时处理，ajax请求超时设置超时状态，页面请求超时则返回提示并重定向
//				if (path.indexOf(".action") != -1
//				&& session.getAttribute("LOGIN_SUCCESS") == null) {
//					System.out.println("---开始处理----");
//				// 判断是否为ajax请求
//				if (httpRequest.getHeader("x-requested-with") != null
//				&& httpRequest.getHeader("x-requested-with")
//				.equalsIgnoreCase("XMLHttpRequest")) {
//				httpResponse.addHeader("sessionstatus", "timeOut");
//				httpResponse.addHeader("loginPath", loginUrl);
//				chain.doFilter(request, response);// 不可少，否则请求会出错
//				} else {
//				String str = "<script language='javascript'>alert('会话过期,请重新登录');"
//				+ "window.top.location.href='"
//				+ loginUrl
//				+ "';</script>";
//				response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
//				try {
//				PrintWriter writer = response.getWriter();
//				writer.write(str);
//				writer.flush();
//				writer.close();
//				} catch (Exception e) {
//				e.printStackTrace();
//				}
//				}
//				} else {
//				chain.doFilter(request, response);
//				}
			chain.doFilter(request, response);
			}

//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp,
//			FilterChain chain) throws IOException, ServletException {
//		HttpServletRequest httpReq = (HttpServletRequest) req;
//		HttpServletResponse httpRes = (HttpServletResponse) resp;
//		HttpSession session = httpReq.getSession();
//		String url = StringUtil.dnull(httpReq.getRequestURI());
//		LoginBean loginInfo = (LoginBean)httpReq.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
//		
//		System.out.println("url:" + url + "; loginInfo:" + (loginInfo == null ? "loginInfo is null" : loginInfo.toString()));
//		//如果是登录等请求，不验证session
//		if (url.indexOf("/user/loginPage") > 0
//				|| url.indexOf("login.jsp") > 0) {
//			chain.doFilter(req, resp);
//			
//			return ;
//		} else {
//			if (loginInfo == null 
//					|| loginInfo.getvUserDetailInfo().getUser_id() == null) {
//				httpReq.getRequestDispatcher("/TPMS").forward(req, resp);
//				
//				return;
//			}
//		}
//		chain.doFilter(req, resp);;
//	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
