package com.rensu.education.hctms.util.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.util.Consts;

/**
 * 给jsp引入公共的js和css文件。
 * 
 * @date 2016年4月21日
 * @autor chen xiaoxiao
 */
public class IncludeCommonJsAndCssTag extends TagSupport {

	private PageContext pctx;
	private String isapp;
	private String jqgrid;//是否引入jqgrid控件。
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * @return the isapp
	 */
	public String getIsapp() {
		return isapp;
	}

	/**
	 * @param isapp the isapp to set
	 */
	public void setIsapp(String isapp) {
		this.isapp = isapp;
	}

	/**
	 * @return the jqgrid
	 */
	public String getJqgrid() {
		return jqgrid;
	}

	/**
	 * @param jqgrid the jqgrid to set
	 */
	public void setJqgrid(String jqgrid) {
		this.jqgrid = jqgrid;
	}

	/**
	 * 打印输出引用的公共js和css文件。
	 * app端访问时，会引入jquery2.x min和jquery ui min版本；
	 * web端访问时，考虑浏览器兼容问题，会引入jquery 1.x min版本。
	 * @author chenxiaoxiao
	 * @date 2016-04-22
	 */
	@Override
	public int doEndTag() throws JspException {
		
		//<script language="javascript" src="<%=ctx%>/js/searchCommon.js"></script>
		//<link href="../css/mainFunccss.css" rel="stylesheet" type="text/css" />
		String ctxPath = pctx.getRequest().getServletContext().getContextPath();
		try {
			StringBuffer sb = new StringBuffer();
			//app端访问  jquery引入2.x版本
			if ("1".equals(isapp)) {
				sb.append("<link href='" + ctxPath + "/css/jqueryui/jquery-ui.min.css' rel='stylesheet' type='text/css' />");
				sb.append("<script src='" + ctxPath + "/js/jquery/jquery-2.2.3.min.js' language='javascript'></script>");
				sb.append("<script src='" + ctxPath + "/js/jqueryui/jquery-ui.min.js' language='javascript'></script>");
				
			} 
			//非app端访问，默认web端访问。web端jquery引入1.x版本
			else {
				
			}
			
			if ("true".equals(jqgrid)) {
				
				//sb.append("<link rel='stylesheet' type='text/css' media='screen' href='" + ctxPath + "/css/jqgrid/ui.jqgrid.css' />");
				//sb.append("<script type='text/javascript' src='" + ctxPath + "/js/jqgrid/i18n/grid.locale-cn.js'></script>");
				//sb.append("<script type='text/javascript'> jQuery.jgrid.useJSON = true;  </script>");
				//sb.append("<script type='text/javascript' src='" + ctxPath + "/js/jqgrid/jquery.jqGrid.min.js'></script>");
			}
			//定义js端的ctx
			sb.append("<script language='javascript'>var ctx='" + ctxPath + "';</script>");
			//向前台传高度宽度
			HttpServletRequest req = (HttpServletRequest) pctx.getRequest();
			LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			if(loginBean != null 
				  && loginBean.getvUserDetailInfo()!= null
				  && loginBean.getvUserDetailInfo().getAuth_id()>0){
				sb.append("<script language='javascript'>var pHeight ='" + loginBean.getAvailabeHeight() + "';</script>");
				sb.append("<script language='javascript'>var pWidth ='" + loginBean.getAvailabeWidth() + "';</script>");
			}
			pctx.getOut().println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.EVAL_PAGE;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#setPageContext(javax.servlet.jsp.PageContext)
	 */
	@Override
	public void setPageContext(PageContext pageContext) {
		this.pctx = pageContext;
	}

	
}
