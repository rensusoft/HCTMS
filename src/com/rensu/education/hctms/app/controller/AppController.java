package com.rensu.education.hctms.app.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rensu.education.hctms.app.service.AppStuService;
import com.rensu.education.hctms.app.service.AppTeaService;
import com.rensu.education.hctms.util.StringUtil;


@RequestMapping("/app")
@Controller
public class AppController {
	
	@Autowired
	private AppStuService appStuService;
	@Autowired
	private AppTeaService appTeaService;
	
	
	/***
	 * APP登录
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @throws NoSuchAlgorithmException 
	 * @date 2016年12月28日
	 */
	@RequestMapping(value="/Vapp_login")
	@ResponseBody
	public void Vapp_login(HttpServletRequest req, HttpServletResponse res) throws IOException, NoSuchAlgorithmException {
		String usercode = req.getParameter("usercode");
		String password = req.getParameter("password");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(usercode)&&StringUtil.isNotEmpty(password)){
			try {
				rtnJson = appStuService.appLogin(usercode,password);
			} catch (ParseException e) {
				rtnJson = "{\"success\":false,\"msg\":\"异常错误！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/**
	 * 学生扫二维码进行考勤
	 * @param req
	 * @param res
	 * @author yuanb
	 * @throws IOException 
	 * @date 2017年8月1日
	 */
	@RequestMapping(value="/Vapp_stuAttend")
	@ResponseBody
	public void Vapp_stuAttend(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String scnData = req.getParameter("scnData");
		String orgaId = req.getParameter("orgaId");
		String authId = req.getParameter("authId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(scnData)&&StringUtil.isNotEmpty(orgaId)&&StringUtil.isNotEmpty(authId)){
			try {
				rtnJson = appStuService.stuAttend(scnData,orgaId,authId);
			} catch (ParseException e) {
				rtnJson = "{\"success\":false,\"msg\":\"异常错误！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 得到用户未读消息列表
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月4日
	 */
	@RequestMapping(value="/Vapp_getNewsListInfo")
	@ResponseBody
	public void Vapp_getNewsListInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String userAuthId = req.getParameter("userAuthId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(userAuthId)){
			try {
				rtnJson = appStuService.getNewsListInfo(userAuthId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"异常错误！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 点击消息查看详情信息
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月8日
	 */
	@RequestMapping(value="/Vapp_getNewsInfo")
	@ResponseBody
	public void Vapp_getNewsInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String id = req.getParameter("id");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(id)){
			try {
				rtnJson = appStuService.getnewsInfo(id);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 点击全部已读
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月8日
	 */
	@RequestMapping(value="/Vapp_updateAllMsgState")
	@ResponseBody
	public void Vapp_updateAllMsgState(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)){
			try {
				rtnJson = appStuService.updateAllMsgState(stuAuthId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生--讲座查看
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月8日
	 */
	@RequestMapping(value="/Vapp_getCpListInfo")
	@ResponseBody
	public void Vapp_getCpListInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String userAuthId = req.getParameter("userAuthId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(userAuthId)){
			try {
				rtnJson = appStuService.getCpListInfo(userAuthId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 点击讲座列表--查看讲座详情
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月9日
	 */
	@RequestMapping(value="/Vapp_getCpInfo")
	@ResponseBody
	public void Vapp_getCpInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String id = req.getParameter("id");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(id)){
			try {
				rtnJson = appStuService.getCpInfo(id);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生提交培训日志
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月10日
	 */
	@RequestMapping(value="/Vapp_addStuDRInfo")
	@ResponseBody
	public void Vapp_addStuDRInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String drType = req.getParameter("drType");
		String duration = req.getParameter("duration");
		String drContent = req.getParameter("drContent");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)&&StringUtil.isNotEmpty(drType)
				&&StringUtil.isNotEmpty(duration)&&StringUtil.isNotEmpty(drContent)){
			try {
				rtnJson = appStuService.addStuDailyRecordInfo(stuAuthId,drType,duration,drContent);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生得到培训日志列表
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月11日
	 */
	@RequestMapping(value="/Vapp_getDRListInfo")
	@ResponseBody
	public void Vapp_getDRListInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String drType = req.getParameter("drType");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)){
			try {
				rtnJson = appStuService.getDRListInfo(stuAuthId,drType);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 查看学生培训日志详情
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月11日
	 */
	@RequestMapping(value="/Vapp_getStuDrInfo")
	@ResponseBody
	public void Vapp_getStuDrInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String id = req.getParameter("id");
		String teaFlag = req.getParameter("teaFlag");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(id)){
			try {
				rtnJson = appStuService.getStuDrInfo(id,teaFlag);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生-请假申请
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月15日
	 */
	@RequestMapping(value="/Vapp_addStuAttInfo")
	@ResponseBody
	public void Vapp_addStuAttInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String stuOrgaId = req.getParameter("stuOrgaId");
		String attTypeSel = req.getParameter("attTypeSel");
		String attTypeSelTxt = req.getParameter("attTypeSelTxt");
		String sDate = req.getParameter("sDate");
		String eDate = req.getParameter("eDate");
		String attDateNum = req.getParameter("attDateNum");
		String attContent = req.getParameter("attContent");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)&&StringUtil.isNotEmpty(attTypeSel)
				&&StringUtil.isNotEmpty(sDate)&&StringUtil.isNotEmpty(eDate)
				&&StringUtil.isNotEmpty(attDateNum)&&StringUtil.isNotEmpty(attContent)){
			try {
				rtnJson = appStuService.addStuAttInfo(stuAuthId,stuOrgaId,attTypeSel,attTypeSelTxt,sDate,eDate,attDateNum,attContent);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生请假申请列表
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月15日
	 */
	@RequestMapping(value="/Vapp_getAttListInfo")
	@ResponseBody
	public void Vapp_getAttListInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)){
			try {
				rtnJson = appStuService.getAttListInfo(stuAuthId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生点击请假信息列表展现详情
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月16日
	 */
	@RequestMapping(value="/Vapp_getAttendInfo")
	@ResponseBody
	public void Vapp_getAttendInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String id = req.getParameter("id");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(id)){
			try {
				rtnJson = appStuService.getAttendInfo(id);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生-我的轮转查看轮转信息
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月17日
	 */
	@RequestMapping(value="/Vapp_getStuTpList")
	@ResponseBody
	public void Vapp_getStuTpList(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)){
			try {
				rtnJson = appStuService.getStuTpList(stuAuthId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生--我的考勤
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月18日
	 */
	@RequestMapping(value="/Vapp_getStuAttList")
	@ResponseBody
	public void Vapp_getStuAttList(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)){
			try {
				rtnJson = appStuService.getStuAttList(stuAuthId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生--我的考勤--选择科室列表
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月18日
	 */
	@RequestMapping(value="/Vapp_getStuAttListByOrgaId")
	@ResponseBody
	public void Vapp_getStuAttListByOrgaId(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String orgaId = req.getParameter("orgaId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)||StringUtil.isNotEmpty(orgaId)){
			try {
				rtnJson = appStuService.getStuAttListByOrgaId(stuAuthId,orgaId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生--出科申请页面展现
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月18日
	 */
	@RequestMapping(value="/Vapp_stuOutDept")
	@ResponseBody
	public void Vapp_stuOutDept(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)){
			try {
				rtnJson = appStuService.stuOutDept(stuAuthId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 学生发起出科申请
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年9月5日
	 */
	@RequestMapping(value="/Vapp_stuLaunchOutDept")
	@ResponseBody
	public void Vapp_stuLaunchOutDept(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String stuAuthId = req.getParameter("stuAuthId");
		String stuOrgaId = req.getParameter("stuOrgaId");
		String stuName = req.getParameter("stuName");
		String stuOrgaName = req.getParameter("stuOrgaName");
		
		String rtnJson = "";
		if(StringUtil.isNotEmpty(stuAuthId)&&StringUtil.isNotEmpty(stuOrgaId)){
			try {
				rtnJson = appStuService.stuLaunchOutDept(stuAuthId,stuOrgaId,stuName,stuOrgaName,req);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	
	
	/***
	 * 带教老师--考勤--得到二维码
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月24日
	 */
	@RequestMapping(value="/Vapp_getAttQrCode")
	@ResponseBody
	public void Vapp_getAttQrCode(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String userOrgaId = req.getParameter("userOrgaId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(userOrgaId)){
			try {
				rtnJson = appTeaService.getAttQrCode(userOrgaId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 带教老师--我的学生 展现学生列表
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月28日
	 */
	@RequestMapping(value="/Vapp_getStuListInfo")
	@ResponseBody
	public void Vapp_getStuListInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String userAuthId = req.getParameter("userAuthId");
		String userOrgaId = req.getParameter("userOrgaId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(userAuthId)){
			try {
				rtnJson = appTeaService.getStuListInfo(userAuthId,userOrgaId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 带教老师--我的学生 点击某学生展现集体的信息
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月29日
	 */
	@RequestMapping(value="/Vapp_getStuInfo")
	@ResponseBody
	public void Vapp_getStuInfo(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String tpId = req.getParameter("tpId");
		String stuAuthId = req.getParameter("stuAuthId");
		String stuOrgaId = req.getParameter("stuOrgaId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(tpId)||StringUtil.isNotEmpty(stuAuthId)||StringUtil.isNotEmpty(stuOrgaId)){
			try {
				rtnJson = appTeaService.getStuInfo(tpId,stuAuthId,stuOrgaId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 带教老师--查看学生日志列表
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月30日
	 */
	@RequestMapping(value="/Vapp_getStuDRList")
	@ResponseBody
	public void Vapp_getStuDRList(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String userAuthId = req.getParameter("userAuthId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(userAuthId)){
			try {
				rtnJson = appTeaService.getStuDRList(userAuthId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 带教老师--查看日志--批量审阅
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月30日
	 */
	@RequestMapping(value="/Vapp_updateAllStuDRState")
	@ResponseBody
	public void Vapp_updateAllStuDRState(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String userAuthId = req.getParameter("userAuthId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(userAuthId)){
			try {
				rtnJson = appTeaService.updateAllStuDRState(userAuthId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 带教老师--请假审批
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月31日
	 */
	@RequestMapping(value="/Vapp_getStuAttListTea")
	@ResponseBody
	public void Vapp_getStuAttListTea(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String userAuthId = req.getParameter("userAuthId");
		String userOrgaId = req.getParameter("userOrgaId");
		String roleId = req.getParameter("roleId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(userAuthId)||StringUtil.isNotEmpty(userOrgaId)){
			try {
				rtnJson = appTeaService.getStuAttListTea(userAuthId,userOrgaId,roleId);
			} catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	/***
	 * 带教老师--审批请假申请
	 * @param req
	 * @param res
	 * @throws IOException
	 * @author yuanb
	 * @date 2017年8月31日
	 */
	@RequestMapping(value="/Vapp_passStuAttInfoTea")
	@ResponseBody
	public void Vapp_passStuAttInfoTea(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String id = req.getParameter("id");
		String userAuthId = req.getParameter("userAuthId");
		String userOrgaId = req.getParameter("userOrgaId");
		String rtnJson = "";
		if(StringUtil.isNotEmpty(id)||StringUtil.isNotEmpty(userAuthId)||StringUtil.isNotEmpty(userOrgaId)){
			try {
				rtnJson = appTeaService.passStuAttInfoTea(req);
			}catch (Exception e) {
				rtnJson = "{\"success\":false,\"msg\":\"读取数据出错！\"}";
				e.printStackTrace();
			}
		}
		//返回app端json数据
		res.setContentType("text/html;charset=utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.getWriter().write(rtnJson);
	}
	
	
}
