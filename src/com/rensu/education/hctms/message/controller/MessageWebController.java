package com.rensu.education.hctms.message.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.service.MessagePublishService;
import com.rensu.education.hctms.message.service.MessageReceiveService;
import com.rensu.education.hctms.teach.service.TrainPlanService;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.RoleInfo;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.service.RoleInfoService;
import com.rensu.education.hctms.userauth.service.StuTypeService;
import com.rensu.education.hctms.userauth.service.UserAuthorityService;
import com.rensu.education.hctms.userauth.service.UserInfoService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;
 
@Controller
@RequestMapping(value="/messageweb")
public class MessageWebController{
	
	Logger log = Logger.getLogger(MessageWebController.class);
	
	@Autowired
	protected MessageReceiveService messageReceiveService;
	@Autowired
	protected MessagePublishService messagePublishService;
	@Autowired
	protected UserInfoService userInfoService;
	@Autowired
	protected StuTypeService stuTypeService;
	@Autowired
	protected RoleInfoService roleInfoService;
	@Autowired
	protected TrainPlanService trainPlanService;
	@Autowired
	protected UserAuthorityService userAuthorityService;
	/**
	 * 得到当前自己发出的消息
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月27日
	 */
	@ResponseBody
	@RequestMapping(value="/getMyNews")
	public Object getMyNews(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req) {
		return messagePublishService.selectPageByAuthId(page, rows, req);
	}
	
	
	/**
	 * 得到从缓存中拿科室
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月27日
	 */
	@ResponseBody
	@RequestMapping(value="/getOrga")
	public Object getOrga(HttpServletRequest req) {
		@SuppressWarnings("unchecked")
		List<OrgaInfo> Orgalist = (List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);	
		return StringUtil.returns(true, Orgalist);
	}
	
	
	
	/**
	 * 接收到得消息
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月28日
	 */
	@ResponseBody
	@RequestMapping(value="/getAllNews")
	public Object getAllNews(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req) {
		return messageReceiveService.getAllNewsByAuthId(page, rows, req);
	}
	
	/**
	 * 根据接收表的id  查询这条数据的信息
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月28日
	 */
	@ResponseBody
	@RequestMapping(value="/getMessagebyId")
	public Object getMessagebyId(HttpServletRequest req) {
		MessageReceive messageReceive=messageReceiveService.getMessagebyId(req);
		return StringUtil.returns(true, messageReceive);
	}
	/**
	 * 得到所有的消息未读数量
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月28日
	 */
	@ResponseBody
	@RequestMapping(value="/getAllConunt")
	public Object getAllConunt(HttpServletRequest req) {
		//得到权限id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		Integer roleId=loginBean.getvUserDetailInfo().getRole_id();  //角色id
		MessageReceive messageReceive=new MessageReceive();
		messageReceive.setReceiver_auth_id(auth_id);
		int unreadCount=0;
		int generalNewsCount=0;
		int systemMessageCount=0;
		Map<String, Object> map = new HashMap<String, Object>();
		String progress_state=req.getParameter("progress_state");
		if(progress_state==""||progress_state==null){
		messageReceive.setState("Y");
		messageReceive.setProgress_state(0);
		unreadCount=messageReceiveService.selectCountByLei(messageReceive);
		messageReceive.setType_id(1);
		generalNewsCount=messageReceiveService.selectCountByLei(messageReceive);
		messageReceive.setType_id(2);
		systemMessageCount=messageReceiveService.selectCountByLei(messageReceive);		
		}else if(Integer.parseInt(progress_state)==0){
			messageReceive.setState("Y");
			messageReceive.setProgress_state(0);
			unreadCount=messageReceiveService.selectCountByLei(messageReceive);
			messageReceive.setType_id(1);
			generalNewsCount=messageReceiveService.selectCountByLei(messageReceive);
			messageReceive.setType_id(2);
			systemMessageCount=messageReceiveService.selectCountByLei(messageReceive);
		}
		map.put("roleId", roleId);
		map.put("systemMessageCount", systemMessageCount);
		map.put("unreadCount", unreadCount);
		map.put("success", true);
		return map;
	}
	
	/**
	 * 删除自己发出的消息   只是将状态有Y 变为X
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月28日
	 */
	@ResponseBody
	@RequestMapping(value="/deleteMyMessageByid")
	public Object deleteMyMessageByid(HttpServletRequest req) {
		int i=messagePublishService.updateState(req);
		if(i>0){
		return StringUtil.returns(true, "删除成功！");
		}else{
		return StringUtil.returns(false, "删除失败!");
		}
	}
	
	/**
	 * 消息中心--发送消息
	 * @param req
	 * @param res
	 * @return
	 *@author huq
	 *@date 2017年3月1日
	 */
	@ResponseBody
	@RequestMapping(value="/sendMsg")
	public Object sendMsg(HttpServletRequest req, HttpServletResponse res){
		return messagePublishService.sendMsg(req);
	}
	
	
	
	/**
	 * 检索所有用户的名字
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月1日
	 */
	@ResponseBody
	@RequestMapping(value="/selectNameList")
	public Object getClassCode(HttpServletRequest req){
        //获取页面参数
		String  user_name=req.getParameter("username");
		UserInfo userInfo=new UserInfo();
		userInfo.setUser_name(user_name);
		List<UserInfo> list = userInfoService.findAllUserName(userInfo);
		JSONObject jobj = new JSONObject();
		jobj.put("rows", list);
		return StringUtil.dnull(jobj.toString());		
	}
	
	
	/**
	 * 得到所有类型
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月1日
	 */
	@ResponseBody
	@RequestMapping(value="/getRoleType")
	public Object getStuType(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		StuType stuType=new StuType();
		stuType.setState("Y");
		List<StuType> lists=stuTypeService.selectList(stuType);   //学生类型
		
		List<RoleInfo> roleLists=roleInfoService.selectTeacherList();

		jobj.put("roleLists", roleLists);
		jobj.put("stuTypelist", lists);
		jobj.put("success", true);

	return StringUtil.dnull(jobj.toString());
	}
	
	
	/**
	 * 根据角色id发送消息
	 * @param req
	 * @param res
	 * @return
	 *@author huq
	 *@date 2017年3月2日
	 */
	@ResponseBody
	@RequestMapping(value="/sendMsgByRoleId")
	public Object sendMsgByRoleId(HttpServletRequest req, HttpServletResponse res){
		return messagePublishService.sendMsgByRoleId(req);
	}
	
	/**
	 * 获取自己发送的消息
	 * @param req
	 * @param res
	 * @return
	 *@author huq
	 *@date 2017年3月2日
	 */
	@ResponseBody
	@RequestMapping(value="/getMyMessagebyId")
	public Object getMyMessagebyId(HttpServletRequest req, HttpServletResponse res){
		MessageReceive messageReceive=messagePublishService.getMyMessagebyId(req);
		return StringUtil.returns(true, messageReceive);		
	}
	
	
	/**
	 * 根据科室发送消息
	 * @param req
	 * @param res
	 * @return
	 *@author huq
	 *@date 2017年3月2日
	 */
	@ResponseBody
	@RequestMapping(value="/sendMsgByOrga")
	public Object sendMsgByOrga(HttpServletRequest req, HttpServletResponse res){
		return messagePublishService.sendMsgByOrga(req);
	}
	
	
	
	/**
	 * 得到带教老师 可以发送的  所有信息
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月6日
	 */
	@ResponseBody
	@RequestMapping(value="/getTeachingType")
	public Object getTeachingType(HttpServletRequest req){
		Map<String, Object> rtnMap=trainPlanService.getTeachingType(req);
		return rtnMap;
	}
	
	
	/**
	 * 消息中心全部已读操作
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年7月10日
	 */
	@ResponseBody
	@RequestMapping(value="/readAllNews")
	public Object readAllNews(HttpServletRequest req){
		return messageReceiveService.readAllNews(req);
	}
	
	/**
	 * 根据科室得到所有角色人员
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年12月1日
	 */
	@ResponseBody
	@RequestMapping(value="/getPeoByOrga")
	public Object getPeoByOrga(HttpServletRequest req){
		return userAuthorityService.getPeoByOrga(req);
	}
	
}
