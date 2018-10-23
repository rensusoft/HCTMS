package com.rensu.education.hctms.basicdata.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.HtmlTemplate;
import com.rensu.education.hctms.basicdata.dao.FormInfoDao;
import com.rensu.education.hctms.basicdata.dao.HtmlTemplateDao;


@Service("htmlTemplateService")
public class HtmlTemplateService extends BaseService<HtmlTemplate> {
	
	Logger log = Logger.getLogger(HtmlTemplateService.class);
	
	@Autowired
	protected HtmlTemplateDao htmlTemplateDao;
	@Autowired
	protected FormInfoDao formInfoDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	
	
	public boolean addHtmlTemplat(HttpServletRequest req){
		boolean flag = false;
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		String type = req.getParameter("type");
		String name = req.getParameter("name");
		String validity = req.getParameter("validity");
		String content = req.getParameter("content");
		String state = req.getParameter("state");
		if(name!=null&&!name.trim().equals("")
			&&content!=null&&!content.trim().equals("")
			&&type!=null&&!type.trim().equals("")
			&&validity!=null&&!validity.trim().equals("")
			&&state!=null&&!state.trim().equals("")){
			HtmlTemplate html = new HtmlTemplate();
			int num = htmlTemplateDao.getSequence();
			html.setId(num);
			html.setT_name(name);
			html.setT_content(content);
			html.setValidity(validity);
			html.setState(state);
			html.setCreate_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
			int htmlresult = htmlTemplateDao.add(html);
			FormInfo form = new FormInfo();
			form.setId(formInfoDao.getSequence());
			form.setType_id(Integer.parseInt(type));
			form.setRelated_id(num);
			form.setAvailability(validity);
			form.setState(state);
			int formresult = formInfoDao.add(form);
			if(htmlresult>0&&formresult>0){
				flag = true;
			}
		}
		return flag;
	}
	
	public Map<String,Object> getHTMLInfo(HttpServletRequest req){
		if(StringUtil.isEmpty(req.getParameter("mmid"))){
			  return null;
	    }
	    String mmid = req.getParameter("mmid");
	    //读取当前操作人与科室
	    LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
	    		Consts.SESSION_LOGIN_KEY);
	    String indeptEducName = loginBean.getvUserDetailInfo().getUser_name();
	    String deptName = loginBean.getvUserDetailInfo().getOrga_name();
	    Integer stuAuthId = loginBean.getvUserDetailInfo().getAuth_id();
	    
	    HtmlTemplate ht = htmlTemplateDao.selectOne(Integer.parseInt(mmid));
	    //根据stuAuthId查询学生的基本信息
	    UserAuthority _ua = new UserAuthority();
	    _ua.setAuth_id(stuAuthId);
	    UserAuthority ua = userAuthorityDao.selectStuByAuthId(_ua);
	    //返回Map
		Map<String,Object> rtnMap = new HashMap<String, Object>();
	    rtnMap.put("success", true);
	    rtnMap.put("htData", ht);
	    rtnMap.put("stuData", ua);
	    rtnMap.put("indeptEducName", indeptEducName);
	    rtnMap.put("deptName", deptName);
		return rtnMap;
	}
	
	
}
