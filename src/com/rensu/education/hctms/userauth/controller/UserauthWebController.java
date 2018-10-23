package com.rensu.education.hctms.userauth.controller;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.rensu.education.hctms.config.bean.TrainPlanBefore;
import com.rensu.education.hctms.config.bean.TrainSchemeConfig;
import com.rensu.education.hctms.config.service.TrainPlanBeforeService;
import com.rensu.education.hctms.config.service.TrainSchemeConfigService;
import com.rensu.education.hctms.core.utils.SystemConfigInitHandler;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.RoleInfo;
import com.rensu.education.hctms.userauth.bean.StaffInfo;
import com.rensu.education.hctms.userauth.bean.StuClass;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.service.OrgaInfoService;
import com.rensu.education.hctms.userauth.service.RoleInfoService;
import com.rensu.education.hctms.userauth.service.StaffInfoService;
import com.rensu.education.hctms.userauth.service.StuClassService;
import com.rensu.education.hctms.userauth.service.StuTypeService;
import com.rensu.education.hctms.userauth.service.StudentInfoService;
import com.rensu.education.hctms.userauth.service.UserAuthorityService;
import com.rensu.education.hctms.userauth.service.UserInfoService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;

/**
 * 
 * 
 * @date 2016年12月29日
 * @autor Hu Qiang
 */
@Controller
@RequestMapping(value = "/userauthweb")
public class UserauthWebController {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private StudentInfoService studentInfoService;
	@Autowired
	private StaffInfoService staffInfoService;
	@Autowired
	private UserAuthorityService userAuthorityService;
	@Autowired
	private OrgaInfoService orgaInfoService;
	@Autowired
	private RoleInfoService roleInfoService;
	@Autowired
	private StuTypeService stuTypeService;
	@Autowired
	private StuClassService stuClassService;
	@Autowired
	private TrainSchemeConfigService trainSchemeConfigService;
	@Autowired
	private TrainPlanBeforeService trainPlanBeforeService;
	Logger log=Logger.getLogger(UserauthWebController.class);
		
	/**
	 * 用户列表  初始化
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 *@author huq
	 *@date 2016年12月30日
	 */
	@ResponseBody
	@RequestMapping(value="/userauth/userInfolist")
	public Object getUserInfoList(@Param("rows") int rows, @Param("page") int page, HttpServletRequest req) {
		//[rows:30][page:1][sidx:menu_id][sord:desc]>
		StringUtil.printRequestParams(req);
		UserInfo user = new UserInfo();
		String userCode = req.getParameter("userCode");
		user.setUser_code(userCode);
		if(req.getParameter("orga_id")!=null&&!req.getParameter("orga_id").equals("")){
			Integer orga_id=Integer.parseInt(req.getParameter("orga_id"));
				user.setOrga_id(orga_id);
		}
		if(req.getParameter("role_id")!=null&&!req.getParameter("role_id").equals("")){
			Integer role_id=Integer.parseInt(req.getParameter("role_id"));
				user.setRole_id(role_id);
		}
		user.setState("Y");
		JSONObject jobj = userInfoService.selectPageByMoHu(page, rows, user);
		return StringUtil.dnull(jobj.toString());
	}
	
	/**
	 * 详细信息查询
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 *@author huq
	 *@date 2016年12月30日
	 */
	@ResponseBody
	@RequestMapping(value="/userauth/information")
	public Object getinfromById(HttpServletRequest req) {
		int tpId = Integer.parseInt((req.getParameter("tpId")));
		UserInfo td=new UserInfo();
			td.setUser_id(tpId);
		JSONObject jobj = new JSONObject();
		jobj = userInfoService.selectById(td);
		
		return jobj;
	}
	
	/**
	 * 单个详细信息查询
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 *@author huq
	 *@date 2016年12月30日
	 */
	@ResponseBody
	@RequestMapping(value="/userauth/userInfoFindByid")
	public Object getinfromationById(HttpServletRequest req) {
		int tpId = Integer.parseInt((req.getParameter("ids")));
		UserInfo td=new UserInfo();
			td.setUser_id(tpId);
			UserInfo userinfo= userInfoService.selectOne(tpId);
			JSONObject jobj = new JSONObject();
			jobj.put("userinfo", userinfo);
			return jobj;
	}
	
	/**
	 * 用户信息添加
	 * @param req
	 * @return
	 * @author huq
	 * @date 2016年12月30日
	 */
	@RequestMapping(value="/usersave")
	public ModelAndView userSave(@Param("action")String action, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		String userId=req.getParameter("userId");
		int k=0;
		//修改
		if(userId!=null&&userId!=""){
			k=userInfoService.userUpdate(req,userId);
		}else{
			//添加
			k=userInfoService.userSave(action,req);
		}			
		if(k>0){
			mv.setViewName("web/userauth/userManage");
			mv.addObject("a",1);
			mv.addObject("userId",null);
		}else if(k==-1){
			mv.setViewName("web/userauth/userAddEdit");
			mv.addObject("tips", "保存失败，用户工号已存在;" );
		}else{
			mv.setViewName("web/userauth/userAddEdit");
			mv.addObject("tips", "保存失败;" );			
		}					
		return mv;
	}
	
	/**
	 * 删除 角色
	 * @param userId
	 * @param userName
	 * @param req
	 * @return
	 * @author huq
	 * @date 2017年1月3日
	 */
	@RequestMapping(value="/userauth/userInfoDelete")
	@ResponseBody
	public Object userDele(@Param("userId") int userId, @Param("userName")String userName, HttpServletRequest req) {
		boolean res = userInfoService.userDel(userId, userName, req);
		
		return StringUtil.returns(true, res ? "删除成功！" : "删除失败！");
	}
	
	
 /**
 * 用户编辑初始化操作
 * @param action
 * @param req
 * @return
 *@author huq
 *@date 2017年1月3日
 */
	@RequestMapping(value="/usereditinit")
	public ModelAndView userEditInit(@Param("action")String action, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		OrgaInfo t=new OrgaInfo();
		List<OrgaInfo> listOrga=orgaInfoService.selectList(t);
		mv.addObject("listOrga", listOrga);
	    RoleInfo a=new RoleInfo();
	    List<RoleInfo> listRole=roleInfoService.selectList(a);
	    	mv.addObject("listRole",listRole);
		try {
			if (Consts.OPT_EDIT.equals(action)) {//修改操作  读取角色信息和角色菜单关联信息。
				int userId = Integer.parseInt(req.getParameter("userId"));
				UserInfo user = userInfoService.selectOne(userId);
				StudentInfo stu =studentInfoService.selectOneByUserCode(user.getUser_code());				
				StaffInfo staff=staffInfoService.selectOneByUserCode(user.getUser_code());
				if(stu!=null){
					//得到学生信息
					user.setIdentity("S");
					mv.addObject("studentInfo", stu);
					
					//得到学生类型
					UserAuthority userAuthority=new UserAuthority();
					userAuthority.setUser_id(userId);
					//得到学生角色
					List<UserAuthority> userAuthorities=userAuthorityService.selectList(userAuthority);
					UserAuthority stuAuth=new UserAuthority();
					for (UserAuthority userAuthority2 : userAuthorities) {
						stuAuth=userAuthority2;
					}
					mv.addObject("stuAuth", stuAuth);					
				}else if(staff!=null){
					user.setIdentity("T");
					mv.addObject("staffInfo", staff);
				}
				//得到学生类型下拉
				 //所有学生的角色
			    StuType stu11=new StuType();
			    List<StuType> stuTypeList=stuTypeService.selectList(stu11);
			    mv.addObject("stuTypeList",stuTypeList);	
				mv.addObject("userInfo", user);
			}else{
				mv.addObject("userInfo", null);
				//得到学生类型下拉
				 //所有学生的角色
			    StuType stu11=new StuType();
			    List<StuType> stuTypeList=stuTypeService.selectList(stu11);
			    mv.addObject("stuTypeList",stuTypeList);	
			}
			mv.addObject("action", action);
			mv.setViewName("web/userauth/userAddEdit");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("web/error");
			mv.addObject("tips", e.getMessage());
		}
		
		return mv;
	}
	/**
	 * 查询所有组织和机构名称
	 * @param action
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年1月3日
	 */
	@RequestMapping(value="/findOrginAndRole")
	public ModelAndView findOrginAndRole() {
		ModelAndView mv = new ModelAndView();
		try {
			OrgaInfo t=new OrgaInfo();
			List<OrgaInfo> listOrga=orgaInfoService.selectList(t);
			mv.addObject("listOrga", listOrga);
		    RoleInfo a=new RoleInfo();
		    List<RoleInfo> listRole=roleInfoService.selectList(a);
			//得到学生类型下拉
			 //所有学生的角色
		    StuType stu11=new StuType();
		    List<StuType> stuTypeList=stuTypeService.selectList(stu11);
		    mv.addObject("stuTypeList",stuTypeList);	
		    	mv.addObject("listRole",listRole);
			mv.setViewName("web/userauth/userAddEdit");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("web/error");
			mv.addObject("tips", e.getMessage());
		}
		
		return mv;
	}
	
	/**
	 * hzx
	 * 分页查询OrgaInfo（科室管理）
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/selectOrgaInfoList")
	public Object selectOrgaInfoeList(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		//获取页面的参数
				String json ="";
				OrgaInfo orgaInfo = new OrgaInfo();
				String state = req.getParameter("state");
				orgaInfo.setState(state);
				json = orgaInfoService.selectPage(page, rows, orgaInfo).toString();
				return StringUtil.dnull(json);
	}
	
	/***
	 * 更新OrgaInfo（科室管理）
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月4日
	 */
	@ResponseBody
	@RequestMapping(value="/updateOrgaInfo")
	public Object updateOrgaInfo(HttpServletRequest req){
		return orgaInfoService.updateOrgaInfo(req);
	}
	/***
	 * 新增OrgaInfo（科室管理）
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月4日
	 */
	@ResponseBody
	@RequestMapping(value="/addOrgaInfo")
	public Object addOrgaInfo(HttpServletRequest req){
		return orgaInfoService.addOrgaInfo(req);
	}
	
	
	/***
	 * 加载获取一级机构名称
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月5日
	 * 科室查询添加状态为'Y'
	 * 郑昌于2017年11月13日修改
	 */
	@ResponseBody
	@RequestMapping(value="/selectOragName")
	public Object selectOragName(HttpServletRequest req){
		return orgaInfoService.selectOragName(req);
	}

	/**
	 * 用户新增权限初始化
	 * @param action
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年1月4日
	 */
	@RequestMapping(value="/userautheditinit")
	public Object userAuthEditInit(@Param("action")String action, HttpServletRequest req) {
		StringUtil.printRequestParams(req);
		ModelAndView mv = new ModelAndView();
		try {
			if (Consts.OPT_EDIT.equals(action)) {//修改
				String authId = req.getParameter("authId");
				UserAuthority vUserDetailInfo = userAuthorityService.selectOne(Integer.parseInt(authId));
				if(vUserDetailInfo.getRole_id()==10){
					 //所有学生的角色
				    StuType stu=new StuType();
				    List<StuType> stuTypeList=stuTypeService.selectList(stu);
				    mv.addObject("stuTypeList",stuTypeList);	
				    mv.addObject("roleType","student");
				}else{
					//所有非学生的角色
				    List<RoleInfo> teacherListRole=roleInfoService.selectTeacherList();
				    mv.addObject("teacherListRole",teacherListRole);
				    mv.addObject("roleType","teacher");
				}
				UserInfo detailList = userInfoService.selectOne(vUserDetailInfo.getUser_id());				
				mv.addObject("userDetail", vUserDetailInfo);
				mv.addObject("userId", vUserDetailInfo.getUser_id());
				mv.addObject("authId",Integer.parseInt(authId));
				mv.addObject("userName", detailList.getUser_name());
			} else {    //添加
				String userId = req.getParameter("userId");
				String userName = "";
				UserInfo userDetail = new UserInfo();
				userDetail.setUser_id(Integer.parseInt(userId));
				UserInfo detailList = userInfoService.selectOne(userDetail.getUser_id());//用户信息
				//判断角色类型
				UserAuthority userAuthority=new UserAuthority();
				userAuthority.setUser_id(Integer.parseInt(userId));
				List<UserAuthority> userAuthorities=userAuthorityService.selectList(userAuthority);
				if(userAuthorities.size()==0){
					 List<RoleInfo> teacherListRole=roleInfoService.selectTeacherList();
					    mv.addObject("teacherListRole",teacherListRole);
					    mv.addObject("roleType","teacher");
				}else{
				for (UserAuthority userAuthority2 : userAuthorities) {
					if(userAuthority2.getRole_id()==10){
						 //所有学生的角色
					    StuType stu=new StuType();
					    List<StuType> stuTypeList=stuTypeService.selectList(stu);
					    mv.addObject("stuTypeList",stuTypeList);
					    mv.addObject("roleType","student");
					    break;
					}else{
						//所有非学生的角色
					    List<RoleInfo> teacherListRole=roleInfoService.selectTeacherList();
					    mv.addObject("teacherListRole",teacherListRole);
					    mv.addObject("roleType","teacher");
					    break;
					}
				}
				}
				userName = detailList.getUser_name();
				mv.addObject("userId", userId);
				mv.addObject("userName", userName);
			}
			OrgaInfo t=new OrgaInfo();
			List<OrgaInfo> listOrga=orgaInfoService.selectList(t);
			mv.addObject("listOrga", listOrga);
			mv.addObject("action", action);
			mv.setViewName("web/userauth/userAuthAddEdit");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("web/error");
			mv.addObject("tips", e.getMessage());
		}
		
		return mv;
	}
	
	/**
	 * 权限保存
	 * @param action
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年1月4日
	 */
	@RequestMapping(value="/userauthsave")
	public ModelAndView userAuthSave( HttpServletRequest req) {
		String authId=req.getParameter("authId");
		
		ModelAndView mv = new ModelAndView();
		int i=0;
		if(authId!=null&&authId!=""){
			UserAuthority userAuthority=new UserAuthority();
			userAuthority.setAuth_id(Integer.parseInt(authId));
			if(req.getParameter("roleId")!=null){
				userAuthority.setRole_id(Integer.parseInt(req.getParameter("roleId")));	
			}else{
				userAuthority.setStu_type_id(Integer.parseInt(req.getParameter("typeId")));
			}
			
			userAuthority.setOrga_id(Integer.parseInt(req.getParameter("orgaId")));
			i=userAuthorityService.update(userAuthority); 
		}else {
			i=userAuthorityService.userAuthSave(req);			
		}
			if(i>0){
				mv.setViewName("web/userauth/userManage");
				mv.addObject("a",1);
			}
			else{
			mv.setViewName("web/error");
			mv.addObject("tips", "保存失败：");
			mv.addObject("userId",Integer.parseInt(req.getParameter("userId")));
			}
		return mv;
	}
	
	/**
	 * 用户权限删除操作
	 * @param authId
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年1月4日
	 */
	@RequestMapping(value="/userauthdel")
	@ResponseBody
	public Object userAuthDel(@Param("authId")int authId) {
		boolean res = userAuthorityService.userAuthDel(authId);
		
		return StringUtil.returns(res, res ? "删除成功！" : "删除失败！");
	}
	
	
	
	/**
	 * 修改密码初始化 操作
	 * @param action
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年1月5日
	 */
		@RequestMapping(value="/getPassword")
		public ModelAndView getPassword(HttpServletRequest req) {
			ModelAndView mv = new ModelAndView();
				int userId = Integer.parseInt(req.getParameter("userId"));
				UserInfo user=new UserInfo();
				user.setUser_id(userId);
				mv.addObject("userInfo", user);		
				mv.setViewName("web/userauth/modifyPwd");			
			return mv;
		}
		
		
		
		/**
		 * 修改密码
		 * @param req
		 * @param res
		 * @return
		 *@author huq
		 *@date 2017年1月5日
		 */
		@ResponseBody
		@RequestMapping(value="/modifypwd")
		public Object modifypwd(@Param("userId") int userId,HttpServletRequest req){
			
			String pwd = req.getParameter("pwd");
			String newPwd1 = req.getParameter("newPwd1");
			String newPwd2 = req.getParameter("newPwd2");
			
			if (StringUtil.isEmpty(pwd)
					|| StringUtil.isEmpty(newPwd1)
					|| StringUtil.isEmpty(newPwd2)) {
				return StringUtil.returns(false, "不能有空项！");
			}
			if (!newPwd1.equals(newPwd2)) {
				return StringUtil.returns(false, "两次密码不一致！");
			}
			//判断用户是否存在
			UserInfo userInfo = userInfoService.selectOne(userId);
			if (userInfo == null) {
				return StringUtil.returns(false, "用户不存在！");
			}
			//判断原密码是否正确 
			if (!pwd.equals(userInfo.getUser_password())) {
				return StringUtil.returns(false, "原密码不正确！");
			}
			//根据userId修改为新密码
			UserInfo pramUser = new UserInfo();
			pramUser.setUser_id(userId);
			pramUser.setUser_password(newPwd1);
			if(userInfoService.update(pramUser)>0){
				return StringUtil.returns(true, "修改成功！");
			}else{
				return StringUtil.returns(false, "修改失败...");
			}
		}
        /***
         * 非学生基本信息展示页面
         * @param req
         * @return
         * @author hezx
         * @date 2017年1月11日
         */
		@ResponseBody
		@RequestMapping(value="/selectTeaInfomation")
		public Object selectTeaInfomation(HttpServletRequest req){
			String user_code = req.getParameter("userCode");
			StaffInfo staffInfo = staffInfoService.selectOneByUserCode(user_code);
			return staffInfo;
		}
		
		/***
		 * 学生基本信息展示页面
		 * @param req
		 * @return
		 * @author hezx
		 * @date 2017年1月11日
		 */
		@ResponseBody
		@RequestMapping(value="/selectStuInfomation")
		public Object selectStuInfomation(HttpServletRequest req){
		    String user_code = req.getParameter("userCode");
		    StudentInfo studentInfo = studentInfoService.selectOneByUserCode(user_code);
			return studentInfo;
		}
		/**
		 * 导出学生模板
		 * @param req
		 * @param res
		 * @return
		 * @throws Exception
		 *@author huq
		 *@date 2017年1月11日
		 */
		@ResponseBody
		@RequestMapping(value="/downUserInfo")
		public void downUserInfo(HttpServletRequest req, HttpServletResponse res) throws Exception{
			 userInfoService.downUserInfo(req, res);
		}
		
		/**
		 * 上传学生表信息excel文档
		 * @param req
		 * @param res
		 * @return
		 * @throws Exception
		 *@author huq
		 *@date 2017年1月11日
		 */
		//@ResponseBody
		@RequestMapping(value="/uploadUserInfo")
		public void uploadTeachingTask(HttpServletRequest req, HttpServletResponse res,@Param("roleId") int roleId) throws Exception{
			res.setContentType("text/html;chartset=UTF-8"); 
			res.setCharacterEncoding("UTF-8"); 
				//1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				//2、创建一个文件上传解析器
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				List<FileItem> list = upload.parseRequest(req);
				if(list!=null&&list.size()>0){
					String filename = list.get(0).getName();
					filename = filename.substring(filename.lastIndexOf("\\")+1);
					InputStream in = list.get(0).getInputStream();
					String msg=null;
					if(roleId==1){
						msg = userInfoService.uploadTeachingTask(in,filename);	
					}else if(roleId==2){
						msg = userInfoService.uploadTeacherTeachingTask(in,filename);	
					}
					
					if(msg.equals("true")){
						res.getWriter().print(StringUtil.returnStr(true,"上传成功").toString());
					}else{
						res.getWriter().print(StringUtil.returnStr(false,msg).toString());
					}
				}
			
//			res.getWriter().print(StringUtil.returnStr(true,"Excel文件导入失败，请稍后重试！").toString());
			//return StringUtil.returnStr(true,"Excel文件导入失败，请稍后重试！");
		}
		

		/**
		 * 导出教职工模板
		 * @param req
		 * @param res
		 * @return
		 * @throws Exception
		 *@author huq
		 *@date 2017年1月11日
		 */
		@ResponseBody
		@RequestMapping(value="/downTeacherUserInfo")
		public void downTeacherUserInfo(HttpServletRequest req, HttpServletResponse res) throws Exception{
			 userInfoService.downTeacherUserInfo(req, res);
		}
	
	/**
	 * 从缓存中获取届次
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStuClassList")
	public Object getStuClassList(HttpServletRequest req){
		@SuppressWarnings("unchecked")
		List<StuClass> list = (List<StuClass>) req.getSession().getServletContext().getAttribute(Consts.STU_CLASS);
		JSONObject jobj = new JSONObject();
		jobj.put("rows", list);
		return StringUtil.dnull(jobj.toString());
	}

	/**
	 * 添加届次并更新缓存信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addStuClassInfo")
	public Object addStuClassInfo(HttpServletRequest req){
		int num = 0;
		String stu_class = req.getParameter("stuClass");
		if(stu_class!=null&&!stu_class.equals("")){
			StuClass stuClass = new StuClass();
			LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			if(loginBean!=null&&loginBean.getvUserDetailInfo()!=null){
				stuClass.setCreator(loginBean.getvUserDetailInfo().getAuth_id().toString());
			}
			stuClass.setId(stuClassService.getSequence());
			stuClass.setClass_year(stu_class.substring(0, 4));
			stuClass.setClass_month(stu_class.substring(4, 6));
			stuClass.setClass_day(stu_class.substring(6, 8));
			stuClass.setStu_class(Integer.parseInt(stu_class));
			stuClass.setAvailable("Y");
			num = stuClassService.add(stuClass);
			SystemConfigInitHandler.initStuClass(req.getSession().getServletContext(),stuClassService);
		}
		if(num>0){
			  return StringUtil.returns(true, "新增成功！");
		  }else{
			  return StringUtil.returns(false, "新增失败！");  
		  }
	}
	
	/**
	 * 编辑届次并更新缓存信息
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modifyStuClassInfo")
	public Object modifyStuClassInfo(HttpServletRequest req){
		
		SystemConfigInitHandler.initStuClass(req.getSession().getServletContext(),stuClassService);
		return null;
	}
	
	/**
	 * 轮转计划编排学生列表初始化前状态判断
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月22日
	 */
	@ResponseBody
    @RequestMapping(value="/getStudentInfoState")
    public Object getStudentInfoState(HttpServletRequest req){
        Map<String, Object> map = new HashMap<String, Object>();
        String stuClass = req.getParameter("stuClass");
        if(stuClass!=null&&!stuClass.equals("")){
            StudentInfo student = new StudentInfo();
            student.setStu_class(Integer.parseInt(stuClass));
            student.setState("M");
            int stateMCount = studentInfoService.selectStuCountByState(student);
            if (stateMCount > 0) {//  根据train_plan_before的记录查轮转方案
                TrainPlanBefore trainPlanBefore = trainPlanBeforeService.getTscIdFromTPBefore(student);
                map.put("trainPlanBefore", trainPlanBefore);
            }
            student.setState("N");
            int stateNCount = studentInfoService.selectStuCountByState(student);
            map.put("stateMCount", stateMCount);
            map.put("stateNCount", stateNCount);
        }
        return map;
    }
	
	/**
	 * 根据届次获取学生信息
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryStudentInfo")
	public Object queryStudentInfo(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		String json="";
		String stuClass = req.getParameter("stuClass");
		if(stuClass!=null&&!stuClass.equals("")){
			StudentInfo student = new StudentInfo();
			student.setQueryCondition(" and t.state!='Y' and t.state!='X'");
			student.setStu_class(Integer.parseInt(stuClass));
			json = studentInfoService.selectPage(page, rows, student).toString();
		}
		return StringUtil.dnull(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getStudentInfoForTrain")
	public Object getStudentInfoForTrain(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		String stuClass = req.getParameter("stuClass");
		if(stuClass!=null&&!stuClass.equals("")){
			StudentInfo student = new StudentInfo();
			student.setStu_class(Integer.parseInt(stuClass));
			student.setQueryCondition("and t.state='Y' ");
			student.setOrderCondition("id asc");
			List<StudentInfo> list = studentInfoService.selectStudentList(student);
			jobj.put("rows", list);
		}
		return StringUtil.dnull(jobj.toString());
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getStudentInfo")
	public Object getStudentInfo(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		String stuClass = req.getParameter("stuClass");
		if(stuClass!=null&&!stuClass.equals("")){
			StudentInfo student = new StudentInfo();
			student.setStu_class(Integer.parseInt(stuClass));
			student.setQueryCondition("and t.state!='Y' and t.state!='X'");
			student.setOrderCondition("id asc");
			List<StudentInfo> list = studentInfoService.selectStudentList(student);
			jobj.put("rows", list);
		}
		return StringUtil.dnull(jobj.toString());
	}
	
	@ResponseBody
	@RequestMapping(value="/getTrainSchemeList")
	public Object getTrainSchemeList(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		TrainSchemeConfig train = new TrainSchemeConfig();
		train.setState("Y");
		List<TrainSchemeConfig> list = trainSchemeConfigService.selectList(train);
		jobj.put("rows", list);
		return StringUtil.dnull(jobj.toString());
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadStuInfo")
	public void uploadStuInfo(HttpServletRequest req, HttpServletResponse res) throws Exception{
		res.setContentType("text/html;chartset=UTF-8"); 
		res.setCharacterEncoding("UTF-8"); 
		String msg = userInfoService.uploadStuInfo(req);
		if(msg.equals("true")){
			res.getWriter().print(StringUtil.returnStr(true,"上传成功").toString());
		}else{
			res.getWriter().print(StringUtil.returnStr(false,msg).toString());
		}
	}
	
	/***
	 * 批量上传学生头像信息
	 * @param req
	 * @param res
	 * @throws Exception
	 * @author yuanb
	 * @date 2017年4月1日
	 */
	@RequestMapping(value="/uploadHeadImgs",method = RequestMethod.POST)
	public void uploadHeadImgs(@RequestParam("headImgs") MultipartFile headImgs,HttpServletRequest req, HttpServletResponse res){
		String msg = "";
		try {
			msg = userInfoService.uploadHeadImgs(headImgs,req);
			res.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/countStatusList")
	public Object countStatusList(HttpServletRequest req){
		StudentInfo student = new StudentInfo();
		List<StudentInfo> list = studentInfoService.countStatusList(student);
		String data = "";
		if(list!=null&&list.size()>0){/*
			for(int i=0;i<list.size();i++){
				String hosName = list.get(i).getHos_name();
				if(list.get(i).getHos_name()==null){
					hosName = "未知";
				}
				data += "['"+hosName+"',"+list.get(i).getStu_num()+"],";
			}
			data = "["+data.substring(0, data.length()-1)+"]";*/
			for(int i=0;i<list.size();i++){
				data += "["+list.get(i).getStu_num()+"],";
			}
			data = "["+data.substring(0, data.length()-1)+"]";
		}
		return StringUtil.returns(true,data);
	}
	
	
	/***
	 * 学生综合查询页面展示
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年5月5日
	 */
	@ResponseBody
	@RequestMapping(value="/selectPageInfo")
	public Object selectPageInfo(HttpServletRequest req)throws ParseException{
		return studentInfoService.selectPageInfo(req);
	}
	
	/***
	 * 学生类型select填充
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年5月5日
	 */
	@ResponseBody
	@RequestMapping(value="/selectStuType")
	public Object selectStuType(HttpServletRequest req){
		//从缓存中拿出数据
		List<StuType> typeList = (List<StuType>)req.getSession().getServletContext().getAttribute(Consts.SESSION_STU_TYPE);
		return typeList;
	}
	
	/***
	 * 查询缺勤学生信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年5月11日
	 */
	@ResponseBody
	@RequestMapping(value="/absenceStuInfo")
	public Object absenceStuInfo(HttpServletRequest req){
		return studentInfoService.absenceStuInfo(req);
	}
	
	/***
	 * 查询请假学生信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年5月11日
	 */
	@ResponseBody
	@RequestMapping(value="/leaveStuInfo")
	public Object leaveStuInfo(HttpServletRequest req){
		return studentInfoService.leaveStuInfo(req);
	}
	/***
	 * 查询科室，拼接下拉框
	 * @author zhengc
	 * @date 2017.7.31
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/queryByOrga")
	public Object queryByOrga(HttpServletRequest req ){
		OrgaInfo orgaInfo =new OrgaInfo();
		orgaInfo.setState("Y");
		List<OrgaInfo> list=orgaInfoService.selectList(orgaInfo);
		return StringUtil.returns(true, list);
	}
	
	/***
	 * 删除导入学生
	 * @author zhengc
	 * @date 2018.8.9
	 */
	@ResponseBody
	@RequestMapping(value="/deleteStu")
	public Object deleteStu(HttpServletRequest req){
		boolean bol=studentInfoService.deleteStu(req);
		if(bol){
			return StringUtil.returns(true, "操作成功！");
		}else{
			return StringUtil.returns(false, "操作失败...");
		}
	}
	
	/**
	 * 轮转编排新增学生
	 * @param action
	 * @param req
	 * @return
	 *@author zhengc
	 *@date 2018年8月10日
	 */
	@ResponseBody
	@RequestMapping(value="/addStudent")
	public ModelAndView addStudent() {
		ModelAndView mv = new ModelAndView();
		try {
			OrgaInfo t=new OrgaInfo();
			//得到学生类型下拉
			 //所有学生的角色
		    StuType stu11=new StuType();
		    List<StuType> stuTypeList=stuTypeService.selectList(stu11);
		    TrainSchemeConfig trainSchemeConfig= new TrainSchemeConfig();
		    trainSchemeConfig.setState("Y");
		    List<TrainSchemeConfig> configs=trainSchemeConfigService.selectList(trainSchemeConfig);
		    mv.addObject("configs",configs);
		    mv.addObject("stuTypeList",stuTypeList);	
			mv.setViewName("web/userauth/userAdd");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("web/error");
			mv.addObject("tips", e.getMessage());
		}
		
		return mv;
	}
	/**
	 * 轮转编排新增学生
	 * @param action
	 * @param req
	 * @return
	 *@author zhengc
	 * @throws Exception 
	 *@date 2018年8月10日
	 */
	@ResponseBody
	@RequestMapping(value="/stuSave")
	public Object stuSave(HttpServletRequest req) throws Exception{
		Boolean f=studentInfoService.stuSave(req);
		if(f==true){
			return StringUtil.returns(true, "操作成功！");
		}else{
			return StringUtil.returns(false, "操作失败...");
		}
	}
	/**
	 * 查询学生工号是否存在
	 * @param action
	 * @param req
	 * @return
	 *@author zhengc
	 *@date 2018年8月14日
	 */
	@ResponseBody
	@RequestMapping(value="/selectUserCode")
	public Object selectUserCode(HttpServletRequest req){
		boolean f=studentInfoService.selectUserCode(req);
		if(f==true){
			return StringUtil.returns(true, "");
		}else{
			return StringUtil.returns(false, "");
		}
	}
	
	/**
	 * 导出学生轮转排班模板
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 *@author huq
	 *@date 2017年1月11日
	 */
	@ResponseBody
	@RequestMapping(value="/downUserTrainPlan")
	public void downUserTrainPlan(HttpServletRequest req, HttpServletResponse res) throws Exception{
		 userInfoService.downUserTrainPlan(req, res);
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadStuTrainPlanInfo")
	public void uploadStuTrainPlanInfo(HttpServletRequest req, HttpServletResponse res) throws Exception{
		res.setContentType("text/html;chartset=UTF-8"); 
		res.setCharacterEncoding("UTF-8"); 
		String stuClass=req.getParameter("stuClass");
		String msg = userInfoService.uploadStuTrainPlanInfo(req,stuClass);
		if(msg.equals("true")){
			res.getWriter().print(StringUtil.returnStr(true,"上传成功").toString());
		}else{
			res.getWriter().print(StringUtil.returnStr(false,msg).toString());
		}
	}
}

