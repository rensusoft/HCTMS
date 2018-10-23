package com.rensu.education.hctms.config.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rensu.education.hctms.config.bean.SysDictMain;
import com.rensu.education.hctms.config.bean.SysDictSub;
import com.rensu.education.hctms.config.bean.TrainPlanBefore;
import com.rensu.education.hctms.config.bean.TrainSchemeConfig;
import com.rensu.education.hctms.config.service.SysConfigService;
import com.rensu.education.hctms.config.service.SysDictMainService;
import com.rensu.education.hctms.config.service.SysDictSubService;
import com.rensu.education.hctms.config.service.TrainPlanBeforeService;
import com.rensu.education.hctms.config.service.TrainPlanConfigService;
import com.rensu.education.hctms.config.service.TrainSchemeConfigService;
import com.rensu.education.hctms.config.service.TrainTeachOrderService;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.service.TrainPlanService;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.userauth.service.OrgaInfoService;
import com.rensu.education.hctms.userauth.service.StuTypeService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;
 
@Controller
@RequestMapping(value="/configweb")
public class ConfigWebController{
	
	Logger log = Logger.getLogger(ConfigWebController.class);
	
	@Autowired
	protected SysDictSubService sysDictSubService;
	@Autowired
	protected SysDictMainService sysDictMainService;
	@Autowired
	protected TrainPlanConfigService trainPlanConfigService;
	@Autowired
	protected StuTypeService stuTypeService;
	@Autowired
	protected TrainSchemeConfigService trainSchemeConfigService;
	@Autowired
	protected OrgaInfoService orgaInfoService;
	@Autowired
	protected TrainPlanBeforeService trainPlanBeforeService;
	@Autowired
	protected TrainTeachOrderService trainTeachOrderService;  
	@Autowired
	protected SysConfigService sysConfigService;
	@Autowired
	protected TrainPlanService trainPlanService;
	
	
	/***
	 * 系统字典 分页查询
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月12日
	 */
	@ResponseBody
	@RequestMapping(value="/selectDictMainList")
	public Object selectDictMainList(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		//获取页面的参数
		String json ="";
		SysDictMain sysDictMain = new SysDictMain();
		String state = req.getParameter("state");
		String availability = req.getParameter("availability");
		sysDictMain.setState(state);
		if(!availability.equals("")&&availability!=null){
			sysDictMain.setAvailability(Integer.parseInt(availability));
		}
		json = sysDictMainService.selectPage(page, rows, sysDictMain).toString();
		return StringUtil.dnull(json);
	}
	/***
	 * 系统字典详情 分页查询
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月12日
	 */
	@ResponseBody
	@RequestMapping(value="/selectDictSubList")
	public Object selectDictSubList(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		//获取页面的参数
		String json ="";
		String item_code= req.getParameter("item_code");
		String state= req.getParameter("state");
		SysDictSub sysDictSub = new SysDictSub();
		if(null!=item_code&&!item_code.equals("")){
			sysDictSub.setSup_code(item_code);
			sysDictSub.setState(state);
			json = sysDictSubService.selectPage(page, rows, sysDictSub).toString();
		}
		return StringUtil.dnull(json);
	}
    /***
     * 系统字典 新增
     * @param rows
     * @param page
     * @param req
     * @return
     * @author hezx
     * @date 2017年1月12日
     */
	@ResponseBody
	@RequestMapping(value="/addSysDictMain")
	public Object addSysDictMain(HttpServletRequest req){
		return sysDictMainService.addSysDictMain(req);
		
	}
	/***
	 * 系统字典明细 新增
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月13日
	 */
	@ResponseBody
	@RequestMapping(value="/addSysDictSub")
	public Object addSysDictSub(HttpServletRequest req){
		return sysDictSubService.addSysDictSub(req);
		
	}
	/***
	 * item_code失去光标事件，检索是否存在
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月16日
	 */
	@ResponseBody
	@RequestMapping(value="/selectSysDictmainById")
	public Object selectSysDictmainById(HttpServletRequest req){
		return sysDictMainService.selectSysDictmainById(req);
		
	}
	
	/***
	 * 得到所有科室轮转计划安排
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月6日
	 */
	@ResponseBody
	@RequestMapping(value="/findRotaryDepartment")
	public Object findRotaryDepartment(HttpServletRequest req){		
		return trainPlanConfigService.findRotaryDepartment(req);	
	}
	/**
	 * 得到所有学生类型
	 * @return
	 *@author huq
	 *@date 2017年2月6日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuType")
	public Object getStuType(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		StuType stuType=new StuType();
		stuType.setState("Y");
		List<StuType> lists=stuTypeService.selectList(stuType);
		//得到培训专业名称  （从系统子表中拿）
		List<SysDictSub> SysDictSubs = (List<SysDictSub>)req.getSession().getServletContext().getAttribute(Consts.SESSION_SYS_DICT_SUB);
		List<SysDictSub> SysDictSub=new ArrayList<SysDictSub>();
		for (SysDictSub sysDictSub : SysDictSubs) {
			if(sysDictSub.getSup_code().equals("marjor")){
				SysDictSub.add(sysDictSub);			}
		}
		jobj.put("stuTypelist", lists);
		jobj.put("sysDictSubs", SysDictSub);
		jobj.put("success", true);

	return StringUtil.dnull(jobj.toString());
	}
	/**
	 * 得到轮转规则
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/getTrainConfig")
	public Object getTrainConfig(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		String stu_type_name=req.getParameter("stuTypeId");
		String item_code=req.getParameter("item_code");
		TrainSchemeConfig trainSchemeConfig=new TrainSchemeConfig();//根据前台获取的条件查询
		if(!stu_type_name.equals("-1")){
			trainSchemeConfig.setStu_type_name(stu_type_name);
		};
		if(!item_code.equals("-1")){
			trainSchemeConfig.setMajor(item_code);
		};
		String json=trainSchemeConfigService.selectPageByTiao(page, rows, trainSchemeConfig).toString();
		return StringUtil.dnull(json);
	}
	/**
	 * 添加轮转规则
	 * @return
	 *@author huq
	 *@date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/addTrain")
	public Object addTrain(HttpServletRequest req){
		int i=trainSchemeConfigService.addTrain(req);
		if(i>0){
//			jobj.put("success", true);
//			jobj.put("data","添加方案成功");
			return StringUtil.returns(true, "添加方案成功");
		}else{
//			jobj.put("success", false);
//			jobj.put("data","添加方案失败");
			return StringUtil.returns(false, "添加方案失败");
		}
	}
	
	/**
	 * 删除培训方案表数据
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/deleteTrainConfig")
	public Object deleteTrainConfig(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		int i=trainSchemeConfigService.deleteTrainConfig(req);
		if(i>0){
			jobj.put("success", true);
			jobj.put("data","删除方案成功");
		}else{
			jobj.put("success", false);
			jobj.put("data","删除方案失败");
		}
		return jobj;
	}
	
	/**
	 * 启用培训方案
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/updateAvail")
	public Object updateAvail(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		int i=trainSchemeConfigService.updateAvail(req);
		if(i>0){
			jobj.put("success", true);
			jobj.put("data","启用方案成功");
		}else{
			jobj.put("success", false);
			jobj.put("data","启用方案失败");
		}
		return jobj;
	}
	
	
	
	/**
	 * 关闭培训方案
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/updateNoAvail")
	public Object updateNoAvail(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		int i=trainSchemeConfigService.updateNoAvail(req);
		if(i>0){
			jobj.put("success", true);
			jobj.put("data","关闭方案成功");
		}else{
			jobj.put("success", false);
			jobj.put("data","关闭方案失败");
		}
		return jobj;
	}
	
	/**
	 * 同步科室
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/addTrainPlan")
	public Object addTrainPlan(HttpServletRequest req){
		return trainSchemeConfigService.addTrainPlan(req);
	}
	
	
	/**
	 * 修改科室
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月9日
	 */
	@ResponseBody
	@RequestMapping(value="/updateCongfig")
	public Object updateCongfig(HttpServletRequest req){
		System.out.println(req.getParameter("rowid"));
		System.out.println(req.getParameter("cellvalue"));
		int i=0;
		if(i>0){
			return StringUtil.returns(true,"删除科室成功");
		}else{
			return StringUtil.returns(true,"删除科室失败");
		}
	}	
	
	
	/**
	 * 批量添加保存科室
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/addConfig")
	public Object addConfig(HttpServletRequest req){
		int i=trainPlanConfigService.addConfig(req);
		if(i>0){
			return StringUtil.returns(true,"保存成功");
		}else{
			return StringUtil.returns(true,"保存失败");
		}
	}
	/**
	 * 根据tscid 查询是否有数据
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月14日
	 */
	@ResponseBody
	@RequestMapping(value="/findTrainConfig")
	public Object findTrainConfig(HttpServletRequest req){
		int i=trainPlanConfigService.findTrainConfig(req);
		if(i>0){
			return StringUtil.returns(true,"1");
		}else{
			return StringUtil.returns(true,"0");
		}
	}	
	
	/**
	 * 获取TRAIN_PLAN_CONFIG   中的文本
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月14日
	 */
	@ResponseBody
	@RequestMapping(value="/getReqContent")
	public Object getReqContent(HttpServletRequest req){
		String text=trainPlanConfigService.getReqContent(req);
		if(text.equals("-1")){
			return StringUtil.returns(true,-1);
		}else{
			return StringUtil.returns(true,text);
		}
				
	}
	
	/**
	 * 保存TRAIN_PLAN_CONFIG  中的文本
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月14日
	 */
	@ResponseBody
	@RequestMapping(value="/updateReqContent")
	public Object updateReqContent(HttpServletRequest req){
		int i=trainPlanConfigService.updateReqContent(req);
		if(i>0){
			return StringUtil.returns(true,"方案保存成功!");
		}else{
			return StringUtil.returns(false,"方案保存失败,请先保存数据，再添加方案!");
		}
	}

	@ResponseBody
	@RequestMapping(value="/doTrainPlanBefore")
	public Object doTrainPlanBefore(HttpServletRequest req){
		boolean flag = trainPlanBeforeService.doTrainPlanBefore(req);
		return StringUtil.returns(flag,"");
	}
	
	@ResponseBody
	@RequestMapping(value="/getTrainPlanBefore")
	public Object getTrainPlanBefore(HttpServletRequest req) throws Exception{
		String stu_auth_id = req.getParameter("stu_auth_id");
		JSONObject jobj = new JSONObject();
		if(stu_auth_id!=null&&!stu_auth_id.equals("")){
			TrainPlanBefore plan = new TrainPlanBefore();
			plan.setStu_auth_id(Integer.parseInt(stu_auth_id));
			plan.setState("Y");
			plan.setOrderCondition("train_start_time asc");
			List<TrainPlanBefore> list = trainPlanBeforeService.selectList(plan);
			for (int i = 0; list != null && i < list.size(); i++) {
				String dbtime1 =list.get(i).getTrain_start_date().toString();
				String dbtime2 =list.get(i).getTrain_end_date().toString();
//				String day1=dbtime1.substring(0,4)+"-"+dbtime1.substring(4,6)+"-"+dbtime1.substring(6,8);
//				String day2=dbtime2.substring(0,4)+"-"+dbtime2.substring(4,6)+"-"+dbtime2.substring(6,8);
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				 String newDate=null;
				    try {
				        Date parse = format.parse(dbtime2);
				        Calendar calendar = Calendar.getInstance();
				        calendar.setTime(parse);
				        calendar.add(Calendar.DAY_OF_MONTH, 1);
				        newDate = format.format(calendar.getTime());
				    } catch (ParseException e) {
				        e.printStackTrace();
				    }
				int month1=Integer.parseInt(dbtime1.substring(4,6));
				int month2=Integer.parseInt(newDate.substring(4,6));
				int year1=Integer.parseInt(dbtime1.substring(0,4));
				int year2=Integer.parseInt(newDate.substring(0,4));
				int monthNext=(year2-year1)*12+(month2-month1);
				if(monthNext<0) {
					monthNext=0;
				}
				list.get(i).setDays(monthNext);
                list.get(i).setOrga_order(i + 1);
            }
			jobj.put("rows", list);
		}
		return StringUtil.dnull(jobj.toString());
	}
	
	@ResponseBody
	@RequestMapping(value="/getTrainPlanNow")
	public Object getTrainPlanNow(HttpServletRequest req) throws Exception{
		String stu_auth_id = req.getParameter("stu_auth_id");
		JSONObject jobj = new JSONObject();
		if(stu_auth_id!=null&&!stu_auth_id.equals("")){
			TrainPlan plan = new TrainPlan();
			plan.setStu_auth_id(Integer.parseInt(stu_auth_id));
			plan.setState("Y");
			plan.setTrain_status(51);
			plan.setOrderCondition("train_start_time asc");
			List<TrainPlan> list = trainPlanService.selectListTrain(plan);
			for (int i = 0; list != null && i < list.size(); i++) {
				String dbtime1 =list.get(i).getTrain_start_date().toString();
				String dbtime2 =list.get(i).getTrain_end_date().toString();
				String day1=dbtime1.substring(0,4)+"-"+dbtime1.substring(4,6)+"-"+dbtime1.substring(6,8);
				String day2=dbtime2.substring(0,4)+"-"+dbtime2.substring(4,6)+"-"+dbtime2.substring(6,8);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = format.parse(day1);
				Date date2 = format.parse(day2);
				int a = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
//				 String newDate=null;
//				    try {
//				        Date parse = format.parse(dbtime2);
//				        Calendar calendar = Calendar.getInstance();
//				        calendar.setTime(parse);
//				        calendar.add(Calendar.DAY_OF_MONTH, 1);
//				        newDate = format.format(calendar.getTime());
//				    } catch (ParseException e) {
//				        e.printStackTrace();
//				    }
//				int month1=Integer.parseInt(dbtime1.substring(4,6));
//				int month2=Integer.parseInt(newDate.substring(4,6));
//				int year1=Integer.parseInt(dbtime1.substring(0,4));
//				int year2=Integer.parseInt(newDate.substring(0,4));
//				int monthNext=(year2-year1)*12+(month2-month1);
//				if(monthNext<0) {
//					monthNext=0;
//				}
				list.get(i).setDays(a+1);
            }
			jobj.put("rows", list);
		}
		return StringUtil.dnull(jobj.toString());
	}
	
	@ResponseBody
	@RequestMapping(value="/changeTrainPlanBefore")
	public Object changeTrainPlanBefore(HttpServletRequest req){
		boolean flag = trainPlanBeforeService.changeTrainPlanBefore(req);
		return StringUtil.returns(flag,"");
	}
	
	/**
	 * 按顺序码微调轮转计划
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月22日
	 */
	@ResponseBody
	@RequestMapping(value="/resetTrainPlanBefore")
	public Object resetTrainPlanBefore(HttpServletRequest req){
	    boolean flag = trainPlanBeforeService.resetTrainPlanBefore(req);
	    return StringUtil.returns(flag,"");
	}
	
	@ResponseBody
	@RequestMapping(value="/resetTrainPlan")
	public Object resetTrainPlan(HttpServletRequest req){
	    boolean flag = trainPlanBeforeService.resetTrainPlan(req);
	    return StringUtil.returns(flag,"");
	}
	
	@ResponseBody
	@RequestMapping(value="/createTrainPlan")
	public Object createTrainPlan(HttpServletRequest req){
		boolean flag = trainPlanBeforeService.createTrainPlan(req);
		return StringUtil.returns(flag,"");
	}
	 /***
	  * 新增STU_TEACH_ORDER  中的信息
	  * @param req
	  * @return
	  * @author hezx
	  * @date 2017年3月14日
	  */
	@ResponseBody
	@RequestMapping(value="/insTrainTeaOrder")
	public Object insTrainTeaOrder(HttpServletRequest req){
		return trainTeachOrderService.insTrainTeaOrder(req);
	}
	
	/***
	 * 轮转大纲展示页面的信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年3月14日
	 */
	@ResponseBody
	@RequestMapping(value="/outlineExhibition")
	public Object outlineExhibition(HttpServletRequest req){
		return trainTeachOrderService.outlineExhibition(req);
	}
	
	/***
	 * 查询系统开关配置 
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月5日
	 */
	@ResponseBody
	@RequestMapping(value="/selectSysConfig")
	public Object selectSysConfig(HttpServletRequest req){
		return sysConfigService.selectSysConfig(req);
	}

	/***
	 * 保存系统开关配置 
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月6日
	 */
	@ResponseBody
	@RequestMapping(value="/saveSysConfig")
	public Object saveSysConfig(HttpServletRequest req){
		return sysConfigService.saveSysConfig(req);
	}
}
