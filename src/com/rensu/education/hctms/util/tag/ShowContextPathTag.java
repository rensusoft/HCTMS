package com.rensu.education.hctms.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * 在页面展示上下文路径作用。
 * 
 * @date 2016年4月22日
 * @autor chen xiaoxiao
 */
public class ShowContextPathTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(ShowContextPathTag.class);

	private PageContext pctx;

	/**
	 * 显示上下文路径名。
	 * @author chenxiaoxiao
	 * @date 2016-04-22
	 */
	@Override
	public int doEndTag() throws JspException {
		String ctxPath = pctx.getRequest().getServletContext().getContextPath();
		try {
			pctx.getOut().print(ctxPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Tag.EVAL_PAGE;
	}

	/**
	 * @author chenxiaoxiao
	 * @date 2016-04-22
	 */
	@Override
	public void setPageContext(PageContext pageContext) {
		this.pctx = pageContext;
	}
	
}
