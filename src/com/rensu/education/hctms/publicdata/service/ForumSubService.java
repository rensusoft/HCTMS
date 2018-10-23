package com.rensu.education.hctms.publicdata.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.publicdata.bean.ForumSub;
import com.rensu.education.hctms.publicdata.dao.ForumSubDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("forumSubService")
public class ForumSubService extends BaseService<ForumSub> {
	
	Logger log = Logger.getLogger(ForumSubService.class);
	
	@Autowired
	protected ForumSubDao forumSubDao; 
	
	/***
     * 发布主题的回帖
     * @param req
     * @return 
     * @author guocc
     * @date 2017年3月30日
     */
	public Object publishReply(HttpServletRequest req) {
		//获取页面的参数
		String id = req.getParameter("id");
		String cite_id = req.getParameter("cite_id");
		String reply_content = req.getParameter("reply_content");
		ForumSub forumSub = new ForumSub();
		forumSub.setId(forumSubDao.getSequence());
		if(id != null && !id.equals("")){
			forumSub.setFi_id(Integer.parseInt(id));
		}
		if(cite_id != null && !cite_id.equals("")){
			forumSub.setCite_state(1);
			forumSub.setCite_id(Integer.parseInt(cite_id));
		}else{
			forumSub.setCite_state(0);
		}
		forumSub.setContent(reply_content);
		forumSub.setSupport_num(0);
		forumSub.setAgainst_num(0);
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer publisher_auth_id  = loginBean.getvUserDetailInfo().getAuth_id();
		forumSub.setPublisher_auth_id(publisher_auth_id);
		Date date = new Date();       
		Timestamp publish_time = new Timestamp(date.getTime());
		forumSub.setPublish_time(publish_time);
		forumSub.setState("Y");
		int num = forumSubDao.add(forumSub);
		if(num > 0){
			return StringUtil.returns(true, "操作成功");
		}else{
			return StringUtil.returns(false, "操作异常");
		}
	}
	
	/***
     * 根据ID查询论坛交流被引用的回帖的信息
     * @param forumSub
     * @return ForumSub
     * @author guocc
     * @date 2017年3月30日
     */
	public ForumSub selectForumSubById(ForumSub forumSub) {
		return forumSubDao.selectForumSubById(forumSub);
	}   
	
	/***
     * 支持或反对
     * @param req
     * @return ForumSub
     * @author guocc
     * @date 2017年3月31日
     */
	public Object changeSupOrAgaNum(HttpServletRequest req) {
		//获取页面的参数
		String id = req.getParameter("id");
		String forum_id = req.getParameter("forum_id");
		String flag = req.getParameter("flag");
		if(((String)req.getSession().getAttribute(forum_id + id)) != null && ((String)req.getSession().getAttribute(forum_id + id)).equals("supOrAgaAlready")){
			ForumSub forumSubGet = new ForumSub();
			if(id != null && !id.equals("")){
				forumSubGet = forumSubDao.selectOne(Integer.parseInt(id));
			}
			return StringUtil.returns(true, forumSubGet);
		}else{
			req.getSession().setAttribute((forum_id + id), "supOrAgaAlready");
			ForumSub forumSubGet = new ForumSub();
			if(id != null && !id.equals("")){
				forumSubGet = forumSubDao.selectOne(Integer.parseInt(id));
			}
			int support_num = forumSubGet.getSupport_num();
			int against_num = forumSubGet.getAgainst_num();
			ForumSub forumSub = new ForumSub();
			if(id != null && !id.equals("")){
				forumSub.setId(Integer.parseInt(id));
			}
			if(flag != null && flag.equals("1")){
				support_num++;
				forumSub.setSupport_num(support_num);
			}else if(flag != null && flag.equals("-1")){
				against_num++;
				forumSub.setAgainst_num(against_num);
			}
			int num = forumSubDao.update(forumSub);
			if(num > 0){
				if(id != null && !id.equals("")){
					forumSubGet = forumSubDao.selectOne(Integer.parseInt(id));
				}
				return StringUtil.returns(true, forumSubGet);
			}else{
				return StringUtil.returns(false, "操作异常");
			}
		}
	}
			
			

}
