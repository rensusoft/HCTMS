package com.rensu.education.hctms.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.service.MessageReceiveService;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.service.TrainPlanService;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.bean.VUserDetailInfo;
import com.rensu.education.hctms.userauth.dao.StudentInfoDao;
import com.rensu.education.hctms.userauth.service.MenuInfoService;
import com.rensu.education.hctms.userauth.service.OrgaInfoService;
import com.rensu.education.hctms.userauth.service.RoleInfoService;
import com.rensu.education.hctms.userauth.service.UserInfoService;
import com.rensu.education.hctms.userauth.service.VUserDetailInfoService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


/**
 * 浏览器登录操作
 * 
 * @date 2016年4月14日
 * @autor chen xiaoxiao
 */
@Controller
@RequestMapping(value="/loginweb")
public class LoginWebController {

	Logger log = Logger.getLogger(LoginWebController.class);
	
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	VUserDetailInfoService vUserDetailInfoService;
	@Autowired
	MenuInfoService menuInfoService;
	@Autowired
	RoleInfoService roleInfoService;
	@Autowired
	MessageReceiveService messageReceiveService;
	@Autowired
	TrainPlanService trainPlanservice;
	@Autowired
	StudentInfoDao studentInfoDao;
	@Autowired
	OrgaInfoService orgaInfoService;
	
	/**
	 * 用户第一次访问系统时判断访问来源并做分发处理。
	 * 来源分手机、电脑、pad（暂时么有）。
	 * @param req 
	 * @param res
	 * @date 2016年4月14日
	 * @autor chen xiaoxiao
	 */
	@RequestMapping(value="/dispatch")
	public String visitDispatcher(HttpServletRequest req, HttpServletResponse res) {
		log.info("start to dispatch visit...");
		//TODO 区分web还是手机端访问   记录日志。 考虑是否记录到session
		String param = req.getParameter("param");
		if ("1".equals(param)) {
			return "forward:/loginapp/loginpage.action?param=" + param;
		}
		
		return "forward:/loginweb/loginpage.action?param=" + param;
	}
	
	/**
	 * web登录页跳转操作。
	 * @param req
	 * @param res
	 * @return
	 * @date 2016年4月14日
	 * @autor chen xiaoxiao
	 */
	@RequestMapping(value="/loginpage")
	public String loginPage(HttpServletRequest req, HttpServletResponse res) {
		log.debug("web controller receive visit...param:" + req.getParameter("param"));
		System.out.println("web controller receive visit...param:" + req.getParameter("param"));
		
		return "web/login/login";
	}
	
	/**
	 * 跳转首页
	 * @return
	 * @date 2016年4月26日
	 * @autor chen xiaoxiao
	 */
	@RequestMapping(value="/mainpage")
	public String mainPage() {
		return "web/index/main";
	}
	
	/**
	 * 用户登录操作。
	 * 用户名和密码登录。
	 * @param userCode
	 * @param password
	 * @param req
	 * @param res
	 * @return
	 * @date 2016年4月25日
	 * @autor chen xiaoxiao
	 */
	@RequestMapping(value="/loginbyusercodeandpass")
	@ResponseBody
	public Object loginByUsercodeAndPass(
			@RequestParam("userCode") String userCode,
			@RequestParam("password") String password,
			@RequestParam("pHeight") Integer pHeight,
			@RequestParam("pWidth") Integer pWidth,
			HttpServletRequest req, HttpServletResponse res) {
		Map<String,Object> pramMap = new HashMap<String, Object>();
		pramMap.put("userCode", userCode);
		pramMap.put("password", password);
		List<UserInfo> userList = userInfoService.loginByCodePsd(pramMap);
		if (userList == null || userList.size() == 0) {
			return StringUtil.returns(false, "用户名或密码错误！");
		}
		//判断是否多角色
		UserInfo user = userList.get(0);//合法的用户
		VUserDetailInfo userDetail = new VUserDetailInfo();
		userDetail.setUser_id(user.getUser_id());
		userDetail.setState(Consts.STATUS_Y);
		List<VUserDetailInfo> userDetailList = vUserDetailInfoService.selectList(userDetail);
		if (userDetailList == null || userDetailList.size() == 0) {
			return StringUtil.returns(false, "您尚未分配用户角色，请联系管理员！");
		}
		boolean fl = true;
		//2、检查是否有科室接收 
		boolean trainStatus52Flag = false;
		//如果是学生，检查其学生信息是否已经维护
		if (userDetailList.get(0).getRole_id()==10) {
			int authId = userDetailList.get(0).getAuth_id();
			//1、检查是否有轮转计划配置
			TrainPlan tp = new TrainPlan();
			tp.setStu_auth_id(authId);
			tp.setState("Y");
			List<TrainPlan> tpList = trainPlanservice.selectList(tp);
			if(tpList==null||tpList.size()<=0){
				fl = false;
			}else{
				//2、检查是否有科室接收 
				String orgaStr = "";
				for(int i=0;tpList!=null&&i<tpList.size();i++){
					if(tpList.get(i).getTrain_status()==Consts.ProcessInfo.TRAINPROCESS0){ //是待入科状态
						OrgaInfo orgaInfo = orgaInfoService.selectOne(Integer.parseInt(tpList.get(i).getTrain_dept_id()));
						String orga_name = orgaInfo.getOrga_name();
						orgaStr += orga_name + "、";
						trainStatus52Flag = true;
					}
				}
				if (trainStatus52Flag) {
					orgaStr = orgaStr.substring(0,orgaStr.length()-1);
					return StringUtil.returns(false, "您在【" + orgaStr + "】未作入科报到！【请联系科室教学秘书办理入科】");
				}
			}
			
		}
		//根据上面的判断进行跳转
		if(!fl){
			return StringUtil.returns(false, "您还未生成轮转计划，请联系科教科处理此问题！");
		}
		
		//只有一个角色，则直接登录。
		else if (userDetailList.size() == 1) {
			loginHandle(userDetailList.get(0),pHeight,pWidth,req);
			Map<String, Object> map = new HashMap<String, Object>();
			if(roleInfoService.selectOne(userDetailList.get(0).getRole_id()).getRole_type()==1){
				map.put("student", "3");
			}
			if(!trainStatus52Flag&&userDetailList.get(0).getRole_id()==10){
				map.put("studentCode", "1");
			}
			map.put("success", true);
			map.put("loginType", "1");
			return map;
		}
		//有多个角色，返回登录页让用户选择。
		else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			map.put("loginType", "2");
			map.put("authList", userDetailList);
			return map;
		}
		
	}
	
	/***
	 * 点击登陆，如果多角色跳转角色选择弹出框
	 * @param req
	 * @param res
	 * @return
	 * @author yuanb
	 * @date 2016年12月2日
	 */
	@RequestMapping(value="/showSelRole")
	public ModelAndView showSelRole(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		String authList = req.getParameter("authList");
		System.out.println("--authList"+authList);
		mv.setViewName("web/login/showSelRole");
		mv.addObject("authList",authList);
		return mv;
	}
	
	/**
	 * 根据用户角色登录。用户出现多角色时，返回前台让用户选择。
	 * @param authId
	 * @return
	 * @date 2016年4月25日
	 * @autor chen xiaoxiao
	 */
	@RequestMapping(value="/loginbyauth")
	@ResponseBody
	public Object loginByAuth(@RequestParam("authId")Integer authId,@RequestParam("pHeight")Integer pHeight,@RequestParam("pWidth")Integer pWidth, HttpServletRequest req) {
		VUserDetailInfo vUserDetailInfo = vUserDetailInfoService.selectOne(authId);
		if (vUserDetailInfo == null) {
			return StringUtil.returns(false, "用户角色信息获取失败！");
		}
		this.loginHandle(vUserDetailInfo,pHeight,pWidth,req);
		
		return StringUtil.returns(true, "登录成功！");
	}
	
	/**
	 * 处理登录的相关操作：记录日志、读取用户信息并记录session等。
	 * @param userDetail
	 * @date 2016年4月25日
	 * @autor chen xiaoxiao
	 */
	private void loginHandle(VUserDetailInfo userDetail,int pHeight,int pWidth,HttpServletRequest req) {
		LoginBean loginBean = new LoginBean();
		//存储页面有效高度是多少
		loginBean.setAvailabeHeight(pHeight);
		loginBean.setAvailabeWidth(pWidth);
		if(userDetail.getRole_id()==10){
			StudentInfo stuInfo=studentInfoDao.selectOneByUserCode(userDetail.getUser_code());
			userDetail.setStu_num(stuInfo.getStu_num());	
		}
		loginBean.setvUserDetailInfo(userDetail);
		//读取菜单信息
		loginBean.setMenuList(menuInfoService.getMenusByRoleId(userDetail.getRole_id()));
		log.info("loginMenu:" + loginBean.getMenuList());
		//读取message消息数量
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("receiver_auth_id", userDetail.getAuth_id());
		paramMap.put("progress_state", "0");
		MessageReceive mr = new MessageReceive();
		mr.setReceiver_auth_id(userDetail.getAuth_id());
		mr.setProgress_state(0);
		mr.setState("Y");
		int msgCount = messageReceiveService.selectCount(mr);
		loginBean.setMsgCount(msgCount);
		//读取培训交流区的数量（待开发）
		int trainMsgCount = 0;
		//得到总数量
		loginBean.setAllMsgCount(msgCount + trainMsgCount);
		
		//读取代办事项
		//TODO 
		//记录登录日志。
//		loginLogService.add(new LoginLog(
//				loginLogService.getSequence(), 
//				userDetail.getAuth_id(), 
//				1, 
//				userDetail.getAuth_id(), 
//				StringUtil.getRemoteHost(req), 
//				userDetail.getUser_name() + "(" + userDetail.getOrga_name() + "-" + userDetail.getRole_name() + ")登录了系统。"
//		));
		req.getSession().setAttribute(Consts.SESSION_LOGIN_KEY, loginBean);
	}
	
	/**
	 * 系统退出操作。
	 * 
	 * @return
	 * @date 2016年5月11日
	 * @autor chen xiaoxiao
	 */
	@RequestMapping(value="/logout")
	@ResponseBody
	public Object logout(HttpServletRequest req) {
		log.info("退出系统处理。。。");
		//删除当前session
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		if (loginBean != null 
				&& loginBean.getvUserDetailInfo() != null
				&& loginBean.getvUserDetailInfo().getAuth_id() > 0) {
			req.getSession().removeAttribute(Consts.SESSION_LOGIN_KEY);
			
			//记录日志
//			VUserDetailInfo userDetail = loginBean.getvUserDetailInfo();
//			loginLogService.add(new LoginLog(
//					loginLogService.getSequence(), 
//					userDetail.getAuth_id(), 
//					2, 
//					userDetail.getAuth_id(), 
//					StringUtil.getRemoteHost(req), 
//					userDetail.getUser_name() + "(" + userDetail.getOrga_name() + "-" + userDetail.getRole_name() + ")退出了系统。"
//			));
			
//			log.info(userDetail.getUser_name() + "(" + userDetail.getOrga_name() + "-" + userDetail.getRole_name() + ")退出了系统。");
		} else {
//			log.info("session is null;");
		}
		
		
		return StringUtil.returns(true, "退出成功！");
	}
	
	/***
	 * 各角色登录系统后，主页面加载数据
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/initMainPageInfo")
	public Object initStuMainInfo(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
//		//从session中获取当前用户
//		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
//		String roleCode = loginBean.getvUserDetailInfo().getRole_code();
//		int authId = loginBean.getvUserDetailInfo().getAuth_id();
//		//先加载公共模块数据
//		map = this.initPublicInfo(req);
//		if(StringUtil.equals(roleCode, Consts.CRoleInfo.T_STU)){ 	//是学生
//			//首页 消息公告个数
//			MessageReceive mr = new MessageReceive();
//			mr.setReceiver_auth_id(authId);
//			mr.setProgress_state("1");
//			mr.setState("Y");
//			int msgNum = messageReceiveService.selectCount(mr);
//			//课前预习个数
//			int preNum = previewDealService.selectEffectiveCount(authId);
//			//学习任务个数
//			Map<String,Object> pramMap = new HashMap<String, Object>();
//			pramMap.put("authId", authId);
//			pramMap.put("proSql", "and a.progress_state = '1'");
//			int taskCount = taskDealService.selectCountForStudent(pramMap);
//			//本学期所有开课班
//			List<CourseClassInfo> cciList = courseClassInfoService.getCourseClassByAuthID(roleCode,authId, "", "",req);
//			//加载今日的课程列表
//			List<CurriculumSchedule> curriList = curriculumScheduleService.selectNowDateCurri(cciList,req);
//			map.put("msgNum", msgNum);
//			map.put("preNum", preNum);
//			map.put("taskNum", taskCount);
//			map.put("cciList", cciList);
//			map.put("curriList", curriList);
//		}else if(StringUtil.equals(roleCode, Consts.CRoleInfo.T_TEACH)){ //是教师
//			//首页 消息公告个数
//			MessageReceive mr = new MessageReceive();
//			mr.setReceiver_auth_id(authId);
//			mr.setProgress_state("1");
//			mr.setState("Y");
//			int msgNum = messageReceiveService.selectCount(mr);
//			//教学任务个数
//			Map<String,Object> pramMap = new HashMap<String, Object>();
//			pramMap.put("authId", authId);
//			pramMap.put("publishType", "a.publish_type_code in('3','7','8')");
//			String divType = "(select count(b.id) from task_deal b where a.id = b.tp_id)"+
//							 ">"+
//					         "(select count(b.id) from task_deal b where a.id = b.tp_id and b.progress_state = '3')";
//			pramMap.put("divType", divType);
//			int taskCount = taskPublishService.selectCountForTeacher(pramMap);
//			//本学期所有开课班
//			List<CourseClassInfo> cciList = courseClassInfoService.getCourseClassByAuthID(roleCode,authId, "", "",req);
//			//加载今日的课程列表
//			List<CurriculumSchedule> curriList = curriculumScheduleService.selectNowDateCurri(cciList,req);
//			map.put("msgNum", msgNum);
//			map.put("taskNum", taskCount);
//			map.put("cciList", cciList);
//			map.put("curriList", curriList);
//		}else{
//			
//		}
		return map;
	}
	
	
	public Map<String, Object> initPublicInfo(HttpServletRequest req){
		Map<String, Object> map = new HashMap<String, Object>();
//		//加载时间相关数据
//		String nowDateTime = DateUtil.getCurrDateTime("yyyy年MM月dd日")+"【"+DateUtil.getWeek(new Date())+"】";
//		//学期学年数据
//		String _date = DateUtil.getCurrDate();
//		int[] termInfo = courseClassInfoService.getYearAndTermArr(
//				Integer.parseInt(_date.substring(0, 4)),
//				Integer.parseInt(_date.substring(4, 6)),
//				Integer.parseInt(_date.substring(6, 8)),
//				req);
//		//根据学期开始日期与当前日期得到学期第几周
//		String weeks = DateUtil.getWeekNumByTwoDate(termInfo[3]+"", DateUtil.getCurrDate());
//		
//		map.put("success", true);
//		map.put("nowDateTime", nowDateTime);
//		map.put("termInfo", termInfo[0]+"-"+termInfo[1]+"学年第"+StringUtil.getChinaNum(termInfo[2])+"学期【第"+weeks+"周】");
		return map;
	}
	
}
