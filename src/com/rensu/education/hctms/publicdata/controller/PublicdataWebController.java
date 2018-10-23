package com.rensu.education.hctms.publicdata.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
















import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.publicdata.bean.ForumInfo;
import com.rensu.education.hctms.publicdata.bean.ForumSub;
import com.rensu.education.hctms.publicdata.bean.PublicData;
import com.rensu.education.hctms.publicdata.dao.PublicDataDao;
import com.rensu.education.hctms.publicdata.service.ForumInfoService;
import com.rensu.education.hctms.publicdata.service.ForumSubService;
import com.rensu.education.hctms.publicdata.service.PublicDataService;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.dao.StudentInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;
 
@Controller
@RequestMapping(value="/publicdataweb")
public class PublicdataWebController{
	
	Logger log = Logger.getLogger(PublicdataWebController.class);
	
	@Autowired
	protected PublicDataService publicDataService;
	@Autowired
	protected PublicDataDao publicDataDao; 
	@Autowired
	protected ForumInfoService forumInfoService; 
	@Autowired
	protected ForumSubService forumSubService;
    @Autowired
    protected StudentInfoDao studentInfoDao;
	/***
	 * 新增或修改共享资料（id为空则新增）
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月6日
	 */
	@ResponseBody
	@RequestMapping(value="/insterPublicData")
	public Object insterPublicData(HttpServletRequest req) {
		return publicDataService.insterPublicData(req);
	}
	/***
	 * 分页查询共享资料
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月9日
	 */
	@ResponseBody
	@RequestMapping(value="/selectPublicDataList")
	public Object selectPublicDataList(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		//获取页面的参数
		String state=req.getParameter("state");
		String searchStr=req.getParameter("searchStr");
		String type_code=req.getParameter("type_code");
		String flag=req.getParameter("flag");
		String role_code=req.getParameter("role_code");
		String json ="";
		PublicData publicData=new PublicData();
		publicData.setState(state);
		publicData.setSearchStr(searchStr);
		publicData.setType_code(type_code);
		if(flag!=null&&flag.equals("1")){//全部资料
				json=publicDataService.selectPageOfOthers(page, rows, publicData).toString();
		}else if(flag != null && flag.equals("2")) {//  我发布的资料
			if(role_code != null && role_code.equals("R_KJK_ADMIN")) {//  科教科
				LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
				String  publisher_auth_id=loginBean.getvUserDetailInfo().getAuth_id().toString();
				publicData.setPublisher_auth_id(publisher_auth_id);
				json=publicDataService.selectPageOfOthers(page, rows, publicData).toString();
			}
		}	
		return StringUtil.dnull(json);
		
			
//			String json ="";
//			PublicData publicData = new PublicData();
//			String state = req.getParameter("state");
//			String searchStr = req.getParameter("searchStr");
//			String type_code = req.getParameter("type_code");
//			publicData.setState(state);
//			publicData.setSearchStr(searchStr);
//			publicData.setType_code(type_code);
//			json = publicDataService.selectPage(page, rows, publicData).toString();
//		return StringUtil.dnull(json);
		} 
	
	/**
	 * 点击编辑实现页面跳转
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月9日
	 */
	
	@ResponseBody
	@RequestMapping(value="/updatePageJump")
	public Object updatePageJump(HttpServletRequest req){
		String id = req.getParameter("id");
		PublicData publicData = publicDataDao.selectOne(Integer.parseInt(id));
		return  publicData;
	}
	
	/***
	 * 根据ID删除共享资料
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月9日
	 */
	@ResponseBody
	@RequestMapping(value="/delPublicDate")
	public Object delPublicDate(HttpServletRequest req){
		return  publicDataService.delPublicDate(req);
	}
	/**
	 * 根据ID查看资料详情
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月9日
	 */
	@ResponseBody
	@RequestMapping(value="/selectOneById")
	public Object selectOneById(HttpServletRequest req){
		return  publicDataService.selectOneById(req);
	}
	
	/***
	 * 查询论坛交流信息的总条数 
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月27日
	 */
	@ResponseBody
	@RequestMapping(value="/selectForumsCount")
	public Object selectForumsCount(HttpServletRequest req){
		ForumInfo forumInfo = new ForumInfo();
		int totalCount = forumInfoService.selectCount(forumInfo);
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer publisher_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		forumInfo.setPublisher_auth_id(publisher_auth_id);
		int mineCount = forumInfoService.selectCount(forumInfo);
		forumInfo.setTotalCount(totalCount);
		forumInfo.setMineCount(mineCount);
		return StringUtil.returns(true, forumInfo);
	}
	
	/***
	 * 查询论坛交流信息列表 
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月27日
	 */
	@ResponseBody
	@RequestMapping(value="/selectForumsList")
	public Object selectForumsList(HttpServletRequest req){
		//获取页面的参数
		String flag = req.getParameter("flag");
		String searchStr = req.getParameter("searchStr");
		String state = req.getParameter("state");
		ForumInfo forumInfo = new ForumInfo();
		forumInfo.setSearchStr(searchStr);
		forumInfo.setState(state);
		List<ForumInfo> forumInfoList = new ArrayList<ForumInfo>();
		if(flag != null && flag.equals("0")){
			forumInfoList = forumInfoService.selectForumsList(forumInfo);
		}else if(flag != null && flag.equals("1")){
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Integer publisher_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
			forumInfo.setPublisher_auth_id(publisher_auth_id);
			forumInfoList = forumInfoService.selectForumsList(forumInfo);
		}
		if(forumInfoList!=null&&!forumInfoList.equals("")){
			for(int i=0;i<forumInfoList.size();i++){
				StudentInfo studentInfo =studentInfoDao.selectStuNumByAuthId(forumInfoList.get(i).getAuth_id());
				if(studentInfo!=null&&!studentInfo.equals("")){
					forumInfoList.get(i).setStu_num(studentInfo.getStuNum());
				}
			}
		}
		
		
		return StringUtil.returns(true, forumInfoList);
	} 
	
	/***
	 * 发布主题
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月29日
	 */
	@ResponseBody
	@RequestMapping(value="/publishTheme")
	public Object publishTheme(HttpServletRequest req){
		return forumInfoService.publishTheme(req);
	}   
	
	/***
	 * 查询论坛跟帖回复的总条数 
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月29日
	 */
	@ResponseBody
	@RequestMapping(value="/selectReplyCount")
	public Object selectReplyCount(HttpServletRequest req){
		//获取页面的参数
		String id = req.getParameter("id");
		ForumInfo forumInfo = new ForumInfo();
		if(id != null && !id.equals("")){
			forumInfo.setId(Integer.parseInt(id));
		}
		ForumInfo forumInfoGet = forumInfoService.selectReplyCount(forumInfo);
		return StringUtil.returns(true, forumInfoGet);
	}
	
	/***
	 * 根据ID查询论坛交流发帖信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月29日
	 */
	@ResponseBody
	@RequestMapping(value="/selectForumsById")
	public Object selectForumsById(HttpServletRequest req){
		//获取页面的参数
		String id = req.getParameter("id");
		ForumInfo forumInfo = new ForumInfo();
		if(id != null && !id.equals("")){
			forumInfo.setId(Integer.parseInt(id));
		}
		ForumInfo forumInfoGet = forumInfoService.selectForumsById(forumInfo);
		if(forumInfoGet.getRole_id()==10){
			String userName=forumInfoGet.getUser_name();
			StudentInfo studentInfo=new StudentInfo();
			studentInfo.setStu_name(userName);
			List<StudentInfo> stu=studentInfoDao.selectList(studentInfo);
            forumInfoGet.setStu_num(stu.get(0).getStu_num());
		}
		return StringUtil.returns(true, forumInfoGet);
	} 
	
	/***
	 * 根据ID查询论坛交流回帖信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月30日
	 */
	@ResponseBody
	@RequestMapping(value="/selectReplyInfo")
	public Object selectReplyInfo(HttpServletRequest req){
		List<ForumSub> forumSubList = forumInfoService.selectReplyInfo(req);
		return StringUtil.returns(true, forumSubList);
	} 
	
	/***
	 * 发布主题的回帖
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月30日
	 */
	@ResponseBody
	@RequestMapping(value="/publishReply")
	public Object publishReply(HttpServletRequest req){
		return forumSubService.publishReply(req);
	} 
	
	/***
	 * 查询被回复的回帖的信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月30日
	 */
	@ResponseBody
	@RequestMapping(value="/selectForumSubById")
	public Object selectForumSubById(HttpServletRequest req){
		//获取页面的参数
		String id = req.getParameter("cite_id");
		ForumSub forumSub = new ForumSub();
		if(id != null && !id.equals("")){
			forumSub.setId(Integer.parseInt(id));
		}
		ForumSub forumSubGet = forumSubService.selectForumSubById(forumSub);
		return StringUtil.returns(true, forumSubGet);
	}  
	
	/***
	 * 支持或反对
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月31日
	 */
	@ResponseBody
	@RequestMapping(value="/changeSupOrAgaNum")
	public Object changeSupOrAgaNum(HttpServletRequest req){
		return forumSubService.changeSupOrAgaNum(req);
	} 
	
	/***
	 * 浏览量增加
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月31日
	 */
	@ResponseBody
	@RequestMapping(value="/addCheckedNum")
	public Object addCheckedNum(HttpServletRequest req){
		//获取页面的参数
		String id = req.getParameter("id");
		if(((String)req.getSession().getAttribute(id)) != null && ((String)req.getSession().getAttribute(id)).equals("addAlready")){
			return StringUtil.returns(true, "操作成功！");
		}else{
			req.getSession().setAttribute(id, "addAlready");
			ForumInfo forumInfoGet = new ForumInfo();
			if(id != null && !id.equals("")){
				forumInfoGet = forumInfoService.selectOne(Integer.parseInt(id));
			}
			int checked_num = forumInfoGet.getChecked_num();
			checked_num++;
			ForumInfo forumInfo = new ForumInfo();
			forumInfo.setChecked_num(checked_num);
			if(id != null && !id.equals("")){
				forumInfo.setId(Integer.parseInt(id));
			}
			int num = 0;
			num = forumInfoService.update(forumInfo);
			if(num > 0){
				return StringUtil.returns(true, "操作成功！");
			}else{
				return StringUtil.returns(false, "操作失败！");
			}
		}
	}
	
}
