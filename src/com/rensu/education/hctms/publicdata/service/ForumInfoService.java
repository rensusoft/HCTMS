package com.rensu.education.hctms.publicdata.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.publicdata.bean.ForumInfo;
import com.rensu.education.hctms.publicdata.bean.ForumSub;
import com.rensu.education.hctms.publicdata.dao.ForumInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("forumInfoService")
public class ForumInfoService extends BaseService<ForumInfo> {
	
	Logger log = Logger.getLogger(ForumInfoService.class);
	
	@Autowired
	protected ForumInfoDao forumInfoDao;
	
	/***
     * 查询论坛交流信息列表
     * @param forumInfo
     * @return List<ForumInfo>
     * @author guocc
     * @date 2017年3月27日
     */
	public List<ForumInfo> selectForumsList(ForumInfo forumInfo) {
		return forumInfoDao.selectForumsList(forumInfo);
	} 
	
	/***
     * 发布主题
     * @param req
     * @return 
     * @author guocc
     * @date 2017年3月29日
     */
	public Object publishTheme(HttpServletRequest req) {
		//获取页面的参数
		String type_code = req.getParameter("type_code");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		ForumInfo forumInfo = new ForumInfo();
		forumInfo.setId(forumInfoDao.getSequence());
		forumInfo.setType_code(type_code);
		forumInfo.setTitle(title);
		forumInfo.setContent(content);
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer publisher_auth_id  = loginBean.getvUserDetailInfo().getAuth_id();
		forumInfo.setPublisher_auth_id(publisher_auth_id);
		Date date = new Date();       
		Timestamp publish_time = new Timestamp(date.getTime());
		forumInfo.setPublish_time(publish_time);
		forumInfo.setState("Y");
		forumInfo.setChecked_num(0);
		int num = forumInfoDao.add(forumInfo);
		if(num > 0){
			return StringUtil.returns(true, "操作成功");
		}else{
			return StringUtil.returns(true, "操作异常");
		}
	}  
	
	/***
     * 查询论坛跟帖回复总条数
     * @param forumInfo
     * @return ForumInfo
     * @author guocc
     * @date 2017年3月29日
     */
	public ForumInfo selectReplyCount(ForumInfo forumInfo) {
		return forumInfoDao.selectReplyCount(forumInfo);
	} 
	
	/***
     * 根据ID查询论坛交流发帖信息
     * @param forumInfo
     * @return ForumInfo
     * @author guocc
     * @date 2017年3月29日
     */
	public ForumInfo selectForumsById(ForumInfo forumInfo) {
		return forumInfoDao.selectForumsById(forumInfo);
	}
	
	/***
	 * 根据ID查询论坛交流回帖信息
	 * @param req
	 * @return List<ForumSub>
	 * @author guocc
	 * @date 2017年3月30日
	 */
	public List<ForumSub> selectReplyInfo(HttpServletRequest req){
		//获取页面的参数
		String id = req.getParameter("id");
		ForumInfo forumInfo = new ForumInfo();
		if(id != null && !id.equals("")){
			forumInfo.setId(Integer.parseInt(id));
		}
		List<ForumSub> forumSubList = forumInfoDao.selectReplyInfo(forumInfo);
		for (ForumSub forumSub : forumSubList){
			if(forumSub.getCite_state() != null && forumSub.getCite_state() == 1){
				ForumSub forumSubCite = forumInfoDao.selectCiteInfo(forumSub);
				forumSub.setCite_user_name(forumSubCite.getCite_user_name());
				forumSub.setCite_publish_time_str(forumSubCite.getCite_publish_time_str());
				forumSub.setCite_content(forumSubCite.getCite_content());
			}
		}
		return forumSubList;
	}
	
}
