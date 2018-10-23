package com.rensu.education.hctms.teach.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.basicdata.bean.TfcDeptformRela;
import com.rensu.education.hctms.basicdata.service.TfcDeptformRelaService;
import com.rensu.education.hctms.config.bean.SysConfig;
import com.rensu.education.hctms.config.bean.SysDictSub;
import com.rensu.education.hctms.config.bean.TrainPlanConfig;
import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.TrainPlanConfigDao;
import com.rensu.education.hctms.config.service.TrainTeachOrderService;
import com.rensu.education.hctms.log.service.ProcessRecordService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.service.MessageReceiveService;
import com.rensu.education.hctms.score.bean.ScoEval;
import com.rensu.education.hctms.score.service.ScoEvalService;
import com.rensu.education.hctms.score.service.ScoFormMainService;
import com.rensu.education.hctms.teach.bean.CathedraCondition;
import com.rensu.education.hctms.teach.bean.CathedraDetail;
import com.rensu.education.hctms.teach.bean.CathedraPlan;
import com.rensu.education.hctms.teach.bean.CathedraPlanCount;
import com.rensu.education.hctms.teach.bean.MedicalAdviceMain;
import com.rensu.education.hctms.teach.bean.MedicalAdviceSub;
import com.rensu.education.hctms.teach.bean.MedicalCaseDiscuss;
import com.rensu.education.hctms.teach.bean.MedicalCaseMain;
import com.rensu.education.hctms.teach.bean.MedicalCaseMain;
import com.rensu.education.hctms.teach.bean.MedicalDiagnose;
import com.rensu.education.hctms.teach.bean.MedicalRecord;
import com.rensu.education.hctms.teach.bean.StuActiveData;
import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;
import com.rensu.education.hctms.teach.bean.StuDailyRecord;
import com.rensu.education.hctms.teach.bean.StuTeachOrder;
import com.rensu.education.hctms.teach.bean.StuVacateInfo;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuVacateInfoDao;
import com.rensu.education.hctms.teach.service.CathedraConditionService;
import com.rensu.education.hctms.teach.service.CathedraPlanService;
import com.rensu.education.hctms.teach.service.DocumentCompare;
import com.rensu.education.hctms.teach.service.MedicalAdviceMainService;
import com.rensu.education.hctms.teach.service.MedicalAdviceSubService;
import com.rensu.education.hctms.teach.service.MedicalCaseDiscussService;
import com.rensu.education.hctms.teach.service.MedicalCaseMainService;
import com.rensu.education.hctms.teach.service.MedicalCaseMemberService;
import com.rensu.education.hctms.teach.service.MedicalCaseMainService;
import com.rensu.education.hctms.teach.service.MedicalDiagnoseService;
import com.rensu.education.hctms.teach.service.MedicalRecordService;
import com.rensu.education.hctms.teach.service.ReviewLeaveService;
import com.rensu.education.hctms.teach.service.StuActiveDataService;
import com.rensu.education.hctms.teach.service.StuAttendanceInfoService;
import com.rensu.education.hctms.teach.service.StuDailyRecordService;
import com.rensu.education.hctms.teach.service.StuTeachOrderService;
import com.rensu.education.hctms.teach.service.StuVacateInfoService;
import com.rensu.education.hctms.teach.service.TaskDealService;
import com.rensu.education.hctms.teach.service.TaskPublishService;
import com.rensu.education.hctms.teach.service.TrainPlanService;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.StuClass;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.service.OrgaInfoService;
import com.rensu.education.hctms.userauth.service.StudentInfoService;
import com.rensu.education.hctms.userauth.service.UserAuthorityService;
import com.rensu.education.hctms.userauth.service.UserInfoService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;
import com.rensu.education.hctms.util.StringUtil;
 
@Controller
@RequestMapping(value="/teachweb")
public class TeachWebController{
	
	Logger log = Logger.getLogger(TeachWebController.class);
	
	@Autowired
	protected StuAttendanceInfoService stuAttendanceInfoService;
	@Autowired
	protected TrainPlanService trainPlanService;
	@Autowired
	protected StuTeachOrderService stuTeachOrderService;
	@Autowired
	protected StuVacateInfoService stuVacateInfoService;
	@Autowired
	protected StuActiveDataService stuActiveDataService;
	@Autowired
	protected StuDailyRecordService stuDailyRecordService;
	@Autowired
	protected StudentInfoService studentInfoService;
	@Autowired
	protected CathedraPlanService cathedraPlanService;
	@Autowired
	protected CathedraConditionService cathedraConditionService;
	@Autowired
	protected UserInfoService userInfoService;
	@Autowired
	protected ReviewLeaveService reviewLeaveService;
	@Autowired
	protected TfcDeptformRelaService tfcDeptformRelaService;
	@Autowired
	protected MessageReceiveService messageReceiveService;
	@Autowired
	protected ScoFormMainService scoFormMainService;
	@Autowired
	protected StuVacateInfoDao stuVacateInfoDao;
	@Autowired
	protected ProcessRecordService processRecordService;
	@Autowired
	protected TrainTeachOrderService trainTeachOrderService;
	@Autowired
	protected UserAuthorityService userAuthorityService;
    @Autowired
    protected MedicalRecordService medicalRecordService;
    @Autowired
    protected MedicalAdviceMainService medicalAdviceMainService;
    @Autowired
    protected MedicalDiagnoseService medicalDiagnoseService;
    @Autowired
    protected MedicalAdviceSubService medicalAdviceSubService;
    @Autowired
    protected MedicalCaseMemberService medicalCaseMemberService;
    @Autowired
    protected MedicalCaseDiscussService medicalCaseDiscussService;
    @Autowired
    protected MedicalCaseMainService medicalCaseMainService;
    @Autowired
    protected TaskPublishService taskPublishService;
    @Autowired
    protected TaskDealService taskDealService;
    @Autowired
    protected ScoEvalService scoEvalService;
    @Autowired
    protected OrgaInfoService orgaInfoService;
    @Autowired
    protected TrainPlanConfigDao trainPlanConfigDao;

	/**
	 * 获取个人的考勤记录
	 * @param req
	 * @param res
	 * @throws Exception
	 *@author huq
	 *@date 2017年1月16日
	 */
	@ResponseBody
	@RequestMapping(value="/attendance")
	public Object getAttendance(HttpServletRequest req, HttpServletResponse res,@Param("authId") int authId,@Param("orgaId") int orgaId) throws Exception{
		JSONObject jobj = stuAttendanceInfoService.getAttendance(req, res,authId,orgaId);
		return StringUtil.dnull(jobj.toString());
	}
	
	
	/***
	 * 查询个人轮转计划
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月19日
	 */
	@ResponseBody
	@RequestMapping(value="/selectTrainPlan")
	public Object selectTrainPlan(HttpServletRequest req){
		return	trainPlanService.selectList(req);
	}

	/**
	 * 获取考勤百分比
	 * @param req
	 * @param res
	 * @param authId
	 * @param orgaId
	 * @return
	 * @throws Exception
	 *@author huq
	 *@date 2017年1月19日
	 */
	@ResponseBody
	@RequestMapping(value="/getPercent")
	public Object getPercent(){
		Map<String, Object> rtnMap = stuAttendanceInfoService.getPercent();
		return rtnMap;
	}
	/**
	 * 得到轮转科室
	 * @param req
	 * @param res
	 * @param authId
	 * @param orgaId
	 * @return
	 * @throws Exception
	 *@author huq
	 *@date 2017年1月19日
	 */
	@ResponseBody
	@RequestMapping(value="/getTrainOrga")
	public Object getTrainOrga(@Param("authId") int authId) throws Exception{
		StuAttendanceInfo stuAttendanceInfo=new StuAttendanceInfo();
		stuAttendanceInfo.setStu_auth_id(authId);
		JSONObject jobj = stuAttendanceInfoService.getTrainOrga(authId);
		return StringUtil.dnull(jobj.toString());
	}
	
	/***
	 * 查询轮转过程记录详情
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月22日
	 */
	@ResponseBody
	@RequestMapping(value="/selectRotateProcessRight")
	public Object selectTrainPlanRight(HttpServletRequest req){
		return	stuTeachOrderService.selectRotateProcessRight(req);
	}
	/***
	 * 查询急诊科轮转信息规范
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月22日
	 */
	@ResponseBody
	@RequestMapping(value="/selectReq_content")
	public Object selectReq_content(HttpServletRequest req){
		return	stuTeachOrderService.selectReq_content(req);
	}
	
	/***
	 * 提交请假申请预加载信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月6日
	 */
	@ResponseBody
	@RequestMapping(value="/getAddVacateInfo")
	public Object getAddVacateInfo(HttpServletRequest req){
		return	stuVacateInfoService.getAddVacateInfo(req);
	}
	
	
	/**
	 * 获取请假审批人
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getVacateApprover")
	public Object getVacateApprover(HttpServletRequest req){
		return	stuVacateInfoService.getVacateApprover(req);
	}
	
	/***
	 * 提交请假申请
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月6日
	 */
	@ResponseBody
	@RequestMapping(value="/addVacateInfo")
	public Object addVacateInfo(HttpServletRequest req){
		return	stuVacateInfoService.addVacateInfo(req);
	}
	/**
	 * 分页查询请假申请
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/selectVacateInfo")
	public Object selectVacateInfo(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		//获取页面的参数
		String json ="";
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		String state= req.getParameter("state");
		String vacate_status= req.getParameter("vacate_status");
		String vacate_type_code= req.getParameter("vacate_type_code");
		String start_time_str= req.getParameter("start_time_str"); 
		String end_time_str= req.getParameter("end_time_str"); 
		StuVacateInfo stuVacateInfo = new StuVacateInfo();
		if(null!=stu_auth_id&&!stu_auth_id.equals("")){
			stuVacateInfo.setStu_auth_id(stu_auth_id);
			if(vacate_status!=null&&!vacate_status.equals("")){
				stuVacateInfo.setVacate_status(Integer.parseInt(vacate_status));
			}
			stuVacateInfo.setVacate_type_code(vacate_type_code);
			stuVacateInfo.setStart_time_str(start_time_str);
			stuVacateInfo.setEnd_time_str(end_time_str);
			stuVacateInfo.setState(state);
			json = stuVacateInfoService.selectVacateInfo(page, rows, stuVacateInfo).toString();
		}
		return StringUtil.dnull(json);
	}
	/***
	 * 根据ID查询编辑申请详情
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/selectVacateById")
	public Object selectVacateById(HttpServletRequest req){
		return	stuVacateInfoService.selectVacateById(req);
	}
	/***
	 * 
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月8日
	 */
	@ResponseBody
	@RequestMapping(value="/delVacateInfo")
	public Object delVacateInfo(HttpServletRequest req){
		return	stuVacateInfoService.delVacateInfo(req);
	}
	
	/***
	 * 加载流程select下拉框
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月9日
	 */
	@ResponseBody
	@RequestMapping(value="/pressSelect")
	public Object pressSelect(HttpServletRequest req){
		List<ProcessInfo> processList = (List<ProcessInfo>)req.getSession().getServletContext().getAttribute(Consts.SESSION_PROCESS_INFO);
		List<ProcessInfo> processSelList = new ArrayList<ProcessInfo>();
		for (ProcessInfo processInfo : processList) {
			if(processInfo.getType_code().equals("VACATE")){
				processSelList.add(processInfo);
			}
		}
		Collections.sort(processSelList, new Comparator<ProcessInfo>() {
            public int compare(ProcessInfo arg0, ProcessInfo arg1) {
                Integer code0 = arg0.getProc_num();
                Integer code1 = arg1.getProc_num();
               return code0.compareTo(code1);
            }
        });
		return StringUtil.returns(true, processSelList);
	} 
	
	/***
	 * 流程展示
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月22日
	 */
	@ResponseBody
	@RequestMapping(value="/pressLeaveSelect")
	public Object pressLeaveSelect(HttpServletRequest req){
		return  processRecordService.selectListByRelatedId(req);
	}
	
	/***
	 * 查询请假流程审批详细信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月13日
	 */
	@ResponseBody
	@RequestMapping(value="/selectProcessInfo")
	public Object selectProcessInfo(HttpServletRequest req){
		return	stuVacateInfoService.selectProcessInfo(req);
	}
	
	/**
	 * 得到当前学生当前科室的
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getTable")
	public Object getTable(HttpServletRequest req){
		//从缓存中得到stu_dept_id
				LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
				Integer stu_dept_id=loginBean.getvUserDetailInfo().getOrga_id();   //当前学生的科室ID
				Integer auth_id=loginBean.getvUserDetailInfo().getAuth_id();     //当前学生的权限Id
//				StuActiveData  stuActiveData=new StuActiveData();
//				stuActiveData.setDept_id(stu_dept_id);
//				stuActiveData.setStu_auth_id(auth_id);
//				//查询此同学此刻科室下，所有审核通过的数据
//				List<StuActiveData> listSAD=stuActiveDataService.selectShenHeCountByOA(stuActiveData);   
//				
//				//根据数据改变当前学生大纲表中的完成数量
//				for (int i = 0; i < listSAD.size(); i++) {
//					StuTeachOrder stuTeachOrder=new StuTeachOrder();
//					stuTeachOrder.setId(listSAD.get(i).getSto_id());  //获取 大纲表中的id
//					stuTeachOrder.setFinnish_num(listSAD.get(i).getCount());   //完成的个数
//					stuTeachOrderService.update(stuTeachOrder);  //改变总数
//				}
//				
//				StuTeachOrder t=new StuTeachOrder();
//				t.setDept_id(stu_dept_id);
//				t.setStu_auth_id(auth_id);
//				
//				List<StuTeachOrder>  lists=new ArrayList<StuTeachOrder>();
//				lists=stuTeachOrderService.selectList(t);
//				List<StuTeachOrder> listSort=new ArrayList<StuTeachOrder>();
				
//				//判断type_id的个数
//						int i=0;
//						for (StuTeachOrder stuTeachOrder : lists) {
//							if(stuTeachOrder.getType_id()==0){   //先添加单项有值的
//								listSort.add(stuTeachOrder);
//							}
//							if(stuTeachOrder.getType_id()==1){
//								i++;
//							}
//						}
//						for (int j = 0; j < i; j++) {		
//						StuTeachOrder stu1=new StuTeachOrder();
//						for (StuTeachOrder stuTeachOrder : lists) {  //再添加主项
//							if(stuTeachOrder.getType_id()==1){
//								listSort.add(stuTeachOrder);
//								stu1=stuTeachOrder;
//								lists.remove(stuTeachOrder);
//								break;
//							}
//						}
//						int ttoId = stu1.getTto_id();
//
//						int romveZhu=-1;
//						if(lists.size()>0&&lists!=null){
//						for (StuTeachOrder stuTeachOrder : lists) {  //接着添加子项
//							int supId= -1;
//							if(stuTeachOrder!=null&&stuTeachOrder.getSup_id()!=null){
//								supId = stuTeachOrder.getSup_id();
//							}
//							if(supId==ttoId){
//								listSort.add(stuTeachOrder);
//								romveZhu=supId;
//							}
//							
//						}
//						}
//						if(romveZhu== -1){
//							if(listSort.size()>0){
//							listSort.remove(listSort.get(listSort.size()-1));
//							}
//						}
//						}
//						return StringUtil.returns(true, listSort);
				TrainPlan trainPlan=new TrainPlan();
				trainPlan.setStu_auth_id(auth_id);
				trainPlan.setTrain_dept_id(stu_dept_id.toString());
				TrainTeachOrder trainTeachOrder = new TrainTeachOrder();
				List<TrainPlan> list=trainPlanService.selectList(trainPlan);
				List<TrainTeachOrder> lists=new ArrayList<TrainTeachOrder>();
				if(list.get(0).getTpc_id()==null||list.get(0).getTpc_id().equals("")){
					lists=null;
				}else{
					trainTeachOrder.setTpc_id(list.get(0).getTpc_id());
					trainTeachOrder.setOrderCondition("sort_num asc");
					lists=trainTeachOrderService.selectList(trainTeachOrder);
					//
					StuTeachOrder sto=new StuTeachOrder();
					sto.setDept_id(stu_dept_id);
					sto.setStu_auth_id(auth_id);
					sto.setState("Y");
					for (TrainTeachOrder tto : lists) {
						sto.setTto_id(tto.getId());
						List<StuTeachOrder> stoList=stuTeachOrderService.selectList(sto);
						if (stoList != null && !stoList.isEmpty()) {
							tto.setFinnish_num(stoList.get(0).getFinnish_num());
						}
					}
					
				}
				return StringUtil.returns(true, lists);
	}
	
	/**
	 * 查询所有数据录入情况  （相应学生的）
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月15日
	 */
	@ResponseBody
	@RequestMapping(value="/findEntering")
	public Object findEntering(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		JSONObject json=new JSONObject();
		StuActiveData t=new StuActiveData();
		json = stuActiveDataService.selectPageEntering(page, rows, t,req);
		return	StringUtil.dnull(json.toString());
	}
	/**
	 * 保存录入信息
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月21日
	 */
	@ResponseBody
	@RequestMapping(value="/saveStuActiveData")
	public Object saveStuActiveData(HttpServletRequest req){
		int i=stuActiveDataService.saveStuActiveData(req);
		if(i>0){
			return	StringUtil.returns(true, "录入成功...");
		}else{
			return	StringUtil.returns(false, "录入失败！");	
		}
	}
	
	/**
	 * 查询培训日志
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年2月28日
	 */
	@ResponseBody
	@RequestMapping(value="/selectDailyRecord")
	public Object selectDailyRecord(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		//获取页面的参数
		String json ="";
		String searchStr = req.getParameter("searchStr");
		String type_id= req.getParameter("type_id");
		String state= req.getParameter("state");//加载数据时过滤失效数据
		StuDailyRecord stuDailyRecord = new StuDailyRecord();
		stuDailyRecord.setSearchStr(searchStr);
		//得到当前用户的authId
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer authId = loginBean.getvUserDetailInfo().getAuth_id();
		stuDailyRecord.setCreate_auth_id(authId);
		if(type_id != null && !type_id.equals("")){
			stuDailyRecord.setType_id(Integer.parseInt(type_id));
		}
		stuDailyRecord.setState(state);
		json = stuDailyRecordService.selectDailyRecord(page, rows, stuDailyRecord).toString();
		return StringUtil.dnull(json);
	}
	
	/**
	 * 查询医疗文书
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月18日
	 */
	@ResponseBody
	@RequestMapping(value="/selectMedicalDocument")
	public Object selectMedicalDocument(@Param("rows") int rows, @Param("page") int page, HttpServletRequest req){
		String json ="";
		//获取页面的参数
		String start_date = req.getParameter("start_date");
		String end_date= req.getParameter("end_date");
		String state= req.getParameter("state");//加载数据时过滤失效数据
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setState(state);
		if (StringUtil.isNotEmpty(start_date)) {
			medicalRecord.setStart_date(start_date+" 00:00:00");
		}
		if (StringUtil.isNotEmpty(end_date)) {
			medicalRecord.setEnd_date(end_date+" 23:59:59");
		}
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		medicalRecord.setStu_auth_id(stu_auth_id);
		medicalRecord.setOrderCondition("t.create_time desc");
		json = medicalRecordService.selectMedicalDocument(page, rows, medicalRecord).toString();
		return StringUtil.dnull(json);
	}
	
	/**
	 * 删除培训日志
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年2月28日
	 */
	@ResponseBody
	@RequestMapping(value="/delDailyRecord")
	public Object delDailyRecord(HttpServletRequest req){
		return stuDailyRecordService.delDailyRecord(req);
	}
	
	
	/**
	 * 更新前获得培训日志信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月2日
	 */
	@ResponseBody
	@RequestMapping(value="/updateDaily")
	public Object updateDaily(HttpServletRequest req){
		String id = req.getParameter("id");
		StuDailyRecord stuDailyRecord = stuDailyRecordService.selectOne(Integer.parseInt(id));
		return stuDailyRecord;//可直接反回一个对象
		
	}
	/**
	 * 保存培训日志
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年2月29日
	 */
	@ResponseBody
	@RequestMapping(value="/saveDaily")
	public Object saveDaily(HttpServletRequest req){
		return stuDailyRecordService.saveDaily(req);
	}
	
	/**
	 * 得到当前科室待轮转的学生列表
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月3日
	 */
	@ResponseBody
	@RequestMapping(value="/getTrainStu")
	public Object getTrainStu(HttpServletRequest req){
		List<TrainPlan> stulists=trainPlanService.getTrainStu(req);
		return StringUtil.returns(true, stulists);
	}
	/**
	 * 得到当前科室待轮转的学生列表
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月3日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuMessageById")
	public Object getStuMessageById(HttpServletRequest req){
		Map<String, Object> rtnMap=studentInfoService.getStuMessageById(req);
		return rtnMap;
	}
	
	/**
	 * 查询讲座编排
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月6日
	 */
	@ResponseBody
	@RequestMapping(value="/selectCathedraPlan")
	public Object selectCathedraPlan(HttpServletRequest req){
		//获取页面的参数
		String timeStart =req.getParameter("timeStart");
		String timeEnd =req.getParameter("timeEnd");
		String status = req.getParameter("status");
		CathedraPlan cathedraPlan = new CathedraPlan();
		cathedraPlan.setTimeStart(Timestamp.valueOf(timeStart));
		cathedraPlan.setTimeEnd(Timestamp.valueOf(timeEnd));
		cathedraPlan.setStatus(status);
		List<CathedraCondition> listCathedraCondition = new ArrayList<CathedraCondition>();
		List<CathedraDetail> listCathedraDetail = new ArrayList<CathedraDetail>();
		CathedraCondition cathedraCondition = new CathedraCondition();
		List<CathedraPlan> listCathedraPlan = cathedraPlanService.selectCathedraPlan(cathedraPlan);
		for(CathedraPlan cathPlan : listCathedraPlan){
			CathedraDetail cathedraDetail = new CathedraDetail();
			cathedraDetail.setCathedraPlan(cathPlan);
			cathedraCondition.setCp_id(cathPlan.getId());
			listCathedraCondition = cathedraConditionService.selectCathedraCondition(cathedraCondition);
			for(CathedraCondition cathCondition : listCathedraCondition){
				if(cathCondition.getCondition_type().intValue() == 1){
					cathedraDetail.setClass_condition_id(cathCondition.getId());
					cathedraDetail.setClass_condition_type(cathCondition.getCondition_type());
					cathedraDetail.setClass_condition_value(cathCondition.getCondition_value());
				}
				if(cathCondition.getCondition_type().intValue() == 2){
					cathedraDetail.setType_condition_id(cathCondition.getId());
					cathedraDetail.setType_condition_type(cathCondition.getCondition_type());
					cathedraDetail.setType_condition_value(cathCondition.getCondition_value());
				}
				
			}
			listCathedraDetail.add(cathedraDetail);
		}
		return listCathedraDetail;
	}
	
	/***
	 * 加载届次复选框
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月6日
	 */
	@ResponseBody
	@RequestMapping(value="/stuClassCheckbox")
	public Object stuClassCheckbox(HttpServletRequest req){
		List<StuClass> stuClassList = cathedraPlanService.stuClassCheckbox();
		return StringUtil.returns(true, stuClassList);
	}
	
	/***
	 * 加载学生类型复选框
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月6日
	 */
	@ResponseBody
	@RequestMapping(value="/stuTypeCheckbox")
	public Object stuTypeCheckbox(HttpServletRequest req){
		List<StuType> stuTypeList = cathedraPlanService.stuTypeCheckbox();
		return StringUtil.returns(true, stuTypeList);
	}
	
	/**
	 * 保存讲座编排
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年2月29日
	 */
	@ResponseBody
	@RequestMapping(value="/saveCathedraPlan")
	public Object saveCathedraPlan(HttpServletRequest req){
		String flag = req.getParameter("flag");//区分增加和修改的标志
		String cath_title = req.getParameter("cath_title");
		String speaker = req.getParameter("speaker");
		String speaker_name = req.getParameter("speaker_name");
		String cath_date = req.getParameter("cath_date");
		String cath_time = req.getParameter("cath_time_str");
		String cath_place = req.getParameter("cath_place");
		String class_condition_type = req.getParameter("class_condition_type");
		String stuClass_ids = req.getParameter("stuClass_ids");
		String type_condition_type = req.getParameter("type_condition_type");
		String stuType_ids = req.getParameter("stuType_ids");
		String content = req.getParameter("content");
		CathedraPlan cathedraPlan = new CathedraPlan();
		cathedraPlan.setCath_title(cath_title);
		if(speaker != null && !speaker.equals("")){
			cathedraPlan.setSpeaker(Integer.parseInt(speaker));
		}else{
			cathedraPlan.setSpeaker(-1);
		}
		cathedraPlan.setSpeaker_name(speaker_name);
		if(cath_date != null && !cath_date.equals("")){
			Integer cath_date_id = Integer.parseInt(cath_date.replace("-", ""));
			cathedraPlan.setCath_date(cath_date_id);
		}
		cathedraPlan.setCath_time(Timestamp.valueOf(cath_time));//?
		cathedraPlan.setCath_place(cath_place);
		cathedraPlan.setCath_content(content);
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer creater_authid = loginBean.getvUserDetailInfo().getUser_id();
		cathedraPlan.setCreater_authid(creater_authid);
		cathedraPlan.setCreate_time(new Timestamp((new Date()).getTime()));
		cathedraPlan.setStatus("Y");
		CathedraCondition cathedraCondition1 = new CathedraCondition();//一个plan对应两条condition数据，此为届次类型
		if(class_condition_type != null && !class_condition_type.equals("")){
			cathedraCondition1.setCondition_type(Integer.parseInt(class_condition_type));
		}
		cathedraCondition1.setCondition_value(stuClass_ids);//Integer与String的问题
		CathedraCondition cathedraCondition2 = new CathedraCondition();//一个plan对应两条condition数据，此为学生类型
		if(type_condition_type != null && !type_condition_type.equals("")){
			cathedraCondition2.setCondition_type(Integer.parseInt(type_condition_type));
		}
		cathedraCondition2.setCondition_value(stuType_ids);
		Integer cathedraPlanId = 0;//ID标识
		Integer cathedraConditionId1 = 0;
		Integer cathedraConditionId2 = 0;
		int num = 0;
		if(("").equals(flag)||null==flag){
			cathedraPlanId = cathedraPlanService.getSequence();
			cathedraPlan.setId(cathedraPlanId);
			cathedraConditionId1 = cathedraConditionService.getSequence();
			cathedraConditionId2 = cathedraConditionService.getSequence();
			cathedraCondition1.setCp_id(cathedraPlanId);
			cathedraCondition1.setId(cathedraConditionId1);
			cathedraCondition2.setCp_id(cathedraPlanId);
			cathedraCondition2.setId(cathedraConditionId2);
			num = cathedraPlanService.add(cathedraPlan)==0?cathedraPlanService.add(cathedraPlan):
				  cathedraConditionService.add(cathedraCondition1)==0?cathedraConditionService.add(cathedraCondition1):
				  cathedraConditionService.add(cathedraCondition2);
		}else{
			cathedraPlanId = Integer.parseInt(flag);
			cathedraPlan.setId(cathedraPlanId);
			cathedraConditionId1 = Integer.parseInt(req.getParameter("class_condition_id"));
			cathedraConditionId2 = Integer.parseInt(req.getParameter("type_condition_id"));
			cathedraCondition1.setCp_id(cathedraPlanId);
			cathedraCondition1.setId(cathedraConditionId1);
			cathedraCondition2.setCp_id(cathedraPlanId);
			cathedraCondition2.setId(cathedraConditionId2);
			num = cathedraPlanService.update(cathedraPlan)==0?cathedraPlanService.update(cathedraPlan):
				  cathedraConditionService.update(cathedraCondition1)==0?cathedraConditionService.update(cathedraCondition1):
				  cathedraConditionService.update(cathedraCondition2);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(num < 0){
			map.put("success", false);
			map.put("data", "操作失败!");
			return map;
		}
		map.put("success", true);
		map.put("data", "操作成功!");
		map.put("id", cathedraPlanId);
		return map;
	}
	
	/**
	 * 更新前获取讲座安排
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月8日
	 */
	@ResponseBody
	@RequestMapping(value="/updateSchedule")
	public Object updateSchedule(HttpServletRequest req){
		String id = req.getParameter("id");
		if(id == null || id.equals("")){
			return null;
		}
		CathedraPlan cathedraPlan = cathedraPlanService.selectOne(Integer.parseInt(id));
		List<CathedraCondition> listCathedraCondition = new ArrayList<CathedraCondition>();
		CathedraCondition cathedraCondition = new CathedraCondition();
		CathedraDetail cathedraDetail = new CathedraDetail();
		cathedraDetail.setCathedraPlan(cathedraPlan);
		cathedraCondition.setCp_id(Integer.parseInt(id));
		listCathedraCondition = cathedraConditionService.selectCathedraCondition(cathedraCondition);
		for(CathedraCondition cathCondition : listCathedraCondition){
			if(cathCondition.getCondition_type().intValue() == 1){
				cathedraDetail.setClass_condition_id(cathCondition.getId());
				cathedraDetail.setClass_condition_type(cathCondition.getCondition_type());
				cathedraDetail.setClass_condition_value(cathCondition.getCondition_value());
			}
			if(cathCondition.getCondition_type().intValue() == 2){
				cathedraDetail.setType_condition_id(cathCondition.getId());
				cathedraDetail.setType_condition_type(cathCondition.getCondition_type());
				cathedraDetail.setType_condition_value(cathCondition.getCondition_value());
			}
			
		}
		return cathedraDetail;//可直接反回一个对象
	}
	
	/**
	 * 删除讲座安排
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月9日
	 */
	@ResponseBody
	@RequestMapping(value="/deleteCathedraPlan")
	public Object deleteCathedraPlan(HttpServletRequest req){
		String id = req.getParameter("id");
		String state = req.getParameter("state");
		CathedraPlan cathedraPlan = new CathedraPlan();
		cathedraPlan.setStatus(state);
		if(id != null && !id.equals("")){
			cathedraPlan.setId(Integer.parseInt(id)); 
		}
		int flag = cathedraPlanService.update(cathedraPlan);
		if(flag < 0){
			return StringUtil.returns(false, "操作失败!");
		}
		return StringUtil.returns(true, "操作成功!");
	}
	
	/**
	 * 统计每月条数
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月9日
	 */
	@ResponseBody
	@RequestMapping(value="/countCathedraPlan")
	public Object countCathedraPlan(HttpServletRequest req){
		String date_time_str = req.getParameter("date");
		String[] date_times = date_time_str.split(" ");
//		CathedraPlan cathedraPlan = new CathedraPlan();
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		
		List<CathedraPlanCount> list = new ArrayList<CathedraPlanCount>();
		for(int i = 0; i < date_times.length; i++){
			CathedraPlanCount cathedraPlanCount = new CathedraPlanCount();//new 放里面和外面有区别
//			CathedraPlan cp = cathedraPlanService.countCathedraPlanDate(date_times[i].replace("-", ""));
			Integer cath_date = Integer.parseInt((date_times[i].replace("-","")));
//			if (cp != null){
//				cath_date = cp.getCath_date();
//			}
//			if(cath_date == null){
//				cath_date = Integer.parseInt((date_times[i].replace("-","")));
//			}
//			cathedraPlan.setCath_date(cath_date);
			Integer count = cathedraPlanService.countCathedraPlan((date_times[i].replace("-","")));
			cathedraPlanCount.setCath_date(cath_date);
			cathedraPlanCount.setCount(count);
		    list.add(cathedraPlanCount);
		}
		return StringUtil.returns(true, list);
	}
	
	/**
	 * 检索所有用户的名字
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年3月9日
	 */
	@ResponseBody
	@RequestMapping(value="/selectNameList")
	public Object getClassCode(HttpServletRequest req){
        //获取页面参数
		String  user_name=req.getParameter("username");
		UserInfo userInfo=new UserInfo();
		userInfo.setUser_name(user_name);
		List<UserInfo> list = userInfoService.findAllUserNameAndId(userInfo);
		JSONObject jobj = new JSONObject();
		jobj.put("rows", list); 
		return StringUtil.dnull(jobj.toString());
	}
	
	/**
	 * 检索所有的讲座编排日期
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年3月13日
	 */
	@ResponseBody
	@RequestMapping(value="/getCathDate")
	public Object getCathDate(HttpServletRequest req){
        //获取页面参数
		List<CathedraPlan> list = cathedraPlanService.getCathDate();
		return StringUtil.returns(true, list);
	}
	
	/**
	 * 将此学生入科
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月6日
	 */
	@ResponseBody
	@RequestMapping(value="/changStatus")
	public Object changStatus(HttpServletRequest req){
		int  i=studentInfoService.changStatus(req);
		if(i>0){
			return StringUtil.returns(true, "入科成功");
		}else{
			return StringUtil.returns(true, "入科失败");
		}
	}
	
	
	/**
	 * 得到所有的有待审核的  当前的学生
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月7日
	 */
	@ResponseBody
	@RequestMapping(value="/getPendingAudit")
	public Object getPendingAudit(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		//获取页面的参数
		String json ="";
		json = trainPlanService.getPendingAudit(page, rows, req).toString();
		return StringUtil.dnull(json);
	}
	
	/**
	 * 得到该学生的未审核条数
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月8日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuPendingCount")
	public Object getStuPendingCount(HttpServletRequest req){
		int  i=studentInfoService.getStuPendingCount(req);
			return StringUtil.returns(true, i);
	}	
	
	/**
	 * 学生录入信息   （未审核）
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月8日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuInputById")
	public Object getStuInputById(HttpServletRequest req){
		List<StuActiveData> lists=studentInfoService.getStuInputById(req);
			return StringUtil.returns(true, lists);
	}

	/**
	 * 根据id  得到录入数据
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月9日
	 */
	@ResponseBody
	@RequestMapping(value="/getActiveDataById")
	public Object getActiveDataById(HttpServletRequest req){
		String  content=studentInfoService.getActiveDataById(req);
			return StringUtil.returns(true, content);
	}
	
	/**
	 * 审核通过
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月9日
	 */
	@ResponseBody
	@RequestMapping(value="/auditThrough")
	public Object auditThrough(HttpServletRequest req){
		int  i=stuActiveDataService.auditThrough(req);
		if(i>0){
			return StringUtil.returns(true, "操作成功!");
		}else{
			return StringUtil.returns(true, "操作失败!");
		}
	}

	/**
	 * 审核不通过
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月9日
	 */
	@ResponseBody
	@RequestMapping(value="/auditNotApproved")
	public Object auditNotApproved(HttpServletRequest req){
		int  i=stuActiveDataService.auditNotApproved(req);
		if(i>0){
			return StringUtil.returns(true, "操作成功!");
		}else{
			return StringUtil.returns(true, "操作失败!");
		}
	}
	
	/**
	 * 查询待审批的请假申请
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月15日
	 */
	/*@ResponseBody
	@RequestMapping(value="/selectReviewLeave")
	public Object selectReviewLeave(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){   
		//获取页面的参数
		String json ="";
		String state= req.getParameter("state");//加载数据时过滤失效数据
		String role= req.getParameter("role");//加载数据时过滤失效数据
		ReviewLeaveInfo reviewLeaveInfo = new ReviewLeaveInfo();
		reviewLeaveInfo.setState(state);
		reviewLeaveInfo.setRole(role);
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer create_role_id = loginBean.getvUserDetailInfo().getRole_id();
		reviewLeaveInfo.setCreate_auth_id(create_auth_id);
		reviewLeaveInfo.setCreate_role_id(create_role_id);
		json = reviewLeaveService.selectReviewLeave(page, rows, reviewLeaveInfo).toString();
		return StringUtil.dnull(json);
	}*/
	
	/***
	 * 根据ID查询待审批的申请详情
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月16日
	 */
	@ResponseBody
	@RequestMapping(value="/selectReviewLeaveById")
	public Object selectReviewLeaveById(HttpServletRequest req){
		return	reviewLeaveService.selectReviewLeaveById(req);
	}
	
	/***
	 * 审批请假申请
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月16日
	 */
	@ResponseBody
	@RequestMapping(value="/submitReviewLeave")
	public Object submitReviewLeave(HttpServletRequest req){
		return	reviewLeaveService.submitReviewLeave(req);
	}
	
	
	/**
	 * 通过当前学生权限id 获得学生类型,再通过学生类型和科室id获得 formid
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月16日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuFormId")
	public Object getStuFormId(HttpServletRequest req){
		TfcDeptformRela tfcDeptformRela=tfcDeptformRelaService.getStuFormId(req);
		return StringUtil.returns(true, tfcDeptformRela);
	}
	
	
	/**
	 * 得到当前科室和下一个科室 和当前轮转结束时间
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月16日
	 */
	@ResponseBody
	@RequestMapping(value="/getOrgaNowAndNextTime")
	public Object getOrgaNowAndNextTime(HttpServletRequest req){
		return trainPlanService.getOrgaNowAndNextTime(req);
	}


	/**
	 * 得到考勤信息
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getIndexPercent")
	public Object getIndexPercent(HttpServletRequest req){
		return stuAttendanceInfoService.getIndexPercent(req);
	}
	 
	/**
	 * 得到大纲吻合度
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getOutlineCom")
	public Object getOutlineCom(HttpServletRequest req){
		return	trainPlanService.getOutlineCom(req);
	}
	
	/**
	 * 得到学生首页的    消息块信息
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getTodo")
	public Object getTodo(HttpServletRequest req){
		return	messageReceiveService.getTodo(req);
	}
	
	/***
	 * 表单
	 * @param req
	 * @return
	 * @author yuanb
	 * @date 2017年3月17日
	 */
	@ResponseBody
	@RequestMapping(value="/stuIndept")
	public Object stuIndept(HttpServletRequest req){
		boolean bol = trainPlanService.stuIndept(req);
		if(bol){
			return StringUtil.returns(true, "操作成功！");
		}else{
			return StringUtil.returns(false, "操作失败...");
		}
	}
	
	/***
	 * 查询请假流程详细信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月22日
	 */
	@ResponseBody
	@RequestMapping(value="/selectLeaveProcessInfo")
	public Object selectLeaveProcessInfo(HttpServletRequest req){
		return	reviewLeaveService.selectLeaveProcessInfo(req);
	}
	
	/**
	 * 得到该教师的学生数
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月21日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuAll")
	public Object getStuCount(HttpServletRequest req){
		Map<String, Object>  map=trainPlanService.getStuAll(req);
		return map;
	}
	
	
	/**
	 * 查询该老师下要请假审批的数据
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月23日
	 */
	@ResponseBody
	@RequestMapping(value="/getApprNum")
	public Object getApprNum(HttpServletRequest req){		
		Integer i= reviewLeaveService.getApprNum(req);
		return  StringUtil.returns(true, i);
	}
	
	
	/***
	 * 轮转过程记录-- 点击入科宣教 展现相关数据信息
	 * @param req
	 * @return
	 * @author yuanb
	 * @date 2017年3月21日
	 */
	@ResponseBody
	@RequestMapping(value="/getIndeptEducInfo")
	public Object getIndeptEducInfo(HttpServletRequest req){
		Map<String,Object> map = scoFormMainService.getIndeptEducInfo(req);
		return map;
	}
	/**
	 * 得到秘书首页的详情
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月28日
	 */
	@ResponseBody
	@RequestMapping(value="/getSecreDetails")
	public Object getSecreDetails(HttpServletRequest req){
		Map<String,Object> map = trainPlanService.getSecreDetails(req);
		return map;
	}

	/**
	 * 得到秘书下的数据审核数
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月28日
	 */
	@ResponseBody
	@RequestMapping(value="/getAudit")
	public Object getAudit(HttpServletRequest req){
		int i  = stuActiveDataService.getAudit(req);
		return StringUtil.returns(true, i);
	}
	
	/**
	 * 得到科主任下 的带教老师的人数
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月30日
	 */
	@ResponseBody
	@RequestMapping(value="/getTeaCount")
	public Object getTeaCount(HttpServletRequest req){
		Map<String,Object> map=new HashMap<String, Object>();
		map = userInfoService.getTeaCount(req);
		return map;
	}
	
	/**
	 * 读取缺勤人数
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年4月27日
	 */
	@ResponseBody
	@RequestMapping(value="/absenceNum")
	public Object absenceNum(HttpServletRequest req){
		return trainPlanService.absenceNum(req); 
	}
	
	/**
	 * 待出科人数
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年4月27日
	 */
	@ResponseBody
	@RequestMapping(value="/trainPlanWait")
	public Object trainPlanWait(HttpServletRequest req) {
		return trainPlanService.trainPlanWait(req); 
	}
	
	/**
	 * 得到科教科角色首页的全院轮转情况板块数据
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年4月18日
	 */
	@ResponseBody
	@RequestMapping(value="/getRoundRobin")
	public Object getRoundRobin(HttpServletRequest req){
		String json = trainPlanService.getRoundRobin(req).toString();
		return StringUtil.dnull(json);
	} 
	/**
	 * 得到科教科首页的    消息块信息
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年4月17日
	 */
	@ResponseBody
	@RequestMapping(value="/getNews")
	public Object getNews(HttpServletRequest req){
		return	messageReceiveService.getNews(req);
	} 

	/**
	 * 获取学生管理模块主页面数据
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月19日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuManageInfo")
	public Object getStuManageInfo(HttpServletRequest req){
		return trainPlanService.getStuManageInfo(req);
	} 
	
	@ResponseBody
	@RequestMapping(value="/selectNonAttendanceCount")
	public Object selectNonAttendanceCount(HttpServletRequest req){
		//获取页面数据
		String stu_auth_id = req.getParameter("stu_auth_id");
		String train_start_year = req.getParameter("train_start_year");
		String train_start_month = req.getParameter("train_start_month");
		String train_start_day = req.getParameter("train_start_day");
		String train_end_year = req.getParameter("train_end_year");
		String train_end_month = req.getParameter("train_end_month");
		String train_end_day = req.getParameter("train_end_day");
		 //轮转开始时间的年月日
		int ts_year = 2017;
		int ts_month = 04;
		int ts_day = 20;
		if(train_start_year != null && !train_start_year.equals("")){
			ts_year = Integer.parseInt(train_start_year);
		}
		if(train_start_month != null && !train_start_month.equals("")){
			ts_month = Integer.parseInt(train_start_month) - 1;
		}
		if(train_start_day != null && !train_start_day.equals("")){
			ts_day = Integer.parseInt(train_start_day);
		}
		StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
		if(stu_auth_id != null && !stu_auth_id.equals("")){
			stuAttendanceInfo.setStu_auth_id(Integer.parseInt(stu_auth_id));
		}
		//轮转结束时间的年月日
		int te_year = 2017;
		int te_month = 07;
		int te_day = 20;
		if(train_end_year != null && !train_end_year.equals("")){
			te_year = Integer.parseInt(train_end_year);
		}
		if(train_end_month != null && !train_end_month.equals("")){
			te_month = Integer.parseInt(train_end_month) - 1;
		}
		if(train_end_day != null && !train_end_day.equals("")){
			te_day = Integer.parseInt(train_end_day);
		}		
		// 获取当前时间的年月日
		Calendar cal = Calendar.getInstance();
	    int day = cal.get(Calendar.DATE);
	    int month = cal.get(Calendar.MONTH);
	    int year = cal.get(Calendar.YEAR);
	    //对两个日期之间所有日期的遍历
	    Calendar start = Calendar.getInstance();  
	    start.set(ts_year, ts_month, ts_day);  
	    Long startTIme = start.getTimeInMillis();
	  
	    Calendar end = Calendar.getInstance();  
	    end.set(te_year, te_month, te_day);  
	    Long endTime = end.getTimeInMillis();
	  
	    Calendar now = Calendar.getInstance();  
	    now.set(year, month, day);  
	    Long nowTime = now.getTimeInMillis();  
	    if (nowTime > endTime) {
	    	nowTime = endTime;
	    }  
	  
	    Long oneDay = 1000 * 60 * 60 * 24l;  
	  
	    double count = 0;
	    while (nowTime >= startTIme) {  
	    	count++;
	    	nowTime -= oneDay;  
	    }
		// 获取当前时间
		java.util.Date date = new java.util.Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Integer attend_date = Integer.parseInt(format.format(date));
		java.util.Date endDate = end.getTime();
		Integer end_date = Integer.parseInt(format.format(endDate));
		if (attend_date > end_date) {
			attend_date = end_date;
		}
		stuAttendanceInfo.setAttend_date(attend_date);
		java.util.Date startDate = start.getTime();
		Integer start_date = Integer.parseInt(format.format(startDate));
		stuAttendanceInfo.setStart_date(start_date);
		int non_attendance_count_all = trainPlanService.selectNonAttendanceCount(stuAttendanceInfo);
		int non_attendance_count_half = trainPlanService.selectNonAttendanceCountHalf(stuAttendanceInfo);
		double half_days = non_attendance_count_half * 0.5;
		double non_attendance_count = non_attendance_count_all + half_days;
		double attendance_count = count - non_attendance_count;//  考勤天数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("non_attendance_count", non_attendance_count);
		map.put("attendance_count", attendance_count);
		return map;
	}  
	@ResponseBody
	@RequestMapping(value="/formTeacherSelect")
	public Object formTeacherSelect(HttpServletRequest req){
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		//Integer hos_id = loginBean.getvUserDetailInfo().getHos_id();   //所属附属医院id
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();  //当前科室
		UserAuthority userAuthority = new UserAuthority();
		//userAuthority.setHos_id(hos_id);
		userAuthority.setOrga_id(orga_id);
		List<UserAuthority> userAuthorityList = trainPlanService.formTeacherSelect(userAuthority);
		return StringUtil.returns(true, userAuthorityList);
	} 
	
	@ResponseBody
	@RequestMapping(value="/changeRotateTeacher")
	public Object changeRotateTeacher(HttpServletRequest req){
		//获取页面数据
		String id = req.getParameter("id");
		String teacher_auth_id = req.getParameter("teacher_auth_id");
		TrainPlan trainPlan = new TrainPlan();
		if(id != null && !id.equals("")){
			trainPlan.setId(Integer.parseInt(id));
		}
		if(teacher_auth_id != null && !teacher_auth_id.equals("")){
			trainPlan.setTeacher_auth_id((Integer.parseInt(teacher_auth_id)));
		}
		int num = trainPlanService.update(trainPlan);
		if(num > 0){
			return StringUtil.returns(true, "操作成功！");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}  
	
	@ResponseBody
	@RequestMapping(value="/selectAttendanceList")
	public Object selectAttendanceList(HttpServletRequest req){
		return trainPlanService.selectAttendanceList(req);
	} 

	
	@ResponseBody
	@RequestMapping(value="/changeAttendanceState")
	public Object changeAttendanceState(HttpServletRequest req){
		return trainPlanService.changeAttendanceState(req); 
	}
	
	

	/**
	 * 查询待审批的请假申请
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年4月19日
	 */
	@ResponseBody
	@RequestMapping(value="/selectReviewLeave")
	public Object selectReviewLeave(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){   
		//获取页面的参数
		String proc_result= req.getParameter("proc_result");
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		Integer role_id = loginBean.getvUserDetailInfo().getRole_id();
		Integer teacher_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		StuVacateInfo stuVacateInfo = new StuVacateInfo();
		if(proc_result!=null && !"".equals(proc_result)){// 已审批
			stuVacateInfo.setProc_result(Integer.parseInt(proc_result));
			if (role_id == 30) {
				stuVacateInfo.setQueryCondition("and t.vacate_status in (63,65,0,4,5,6) and p.proc_result is not null");
				stuVacateInfo.setOrga_id(orga_id);
				stuVacateInfo.setEnd_proc_id(63);
			}else if (role_id == 50) {
				stuVacateInfo.setQueryCondition("and t.vacate_status in (0,65,4,5,6) and p.proc_result is not null");
				stuVacateInfo.setEnd_proc_id(64);
			}
		} else {
			if (role_id == 30) {
				stuVacateInfo.setQueryCondition("and t.vacate_status=61 and p.proc_result is null");
				stuVacateInfo.setOrga_id(orga_id);
				stuVacateInfo.setEnd_proc_id(63);
			}else if (role_id == 50) {
				stuVacateInfo.setQueryCondition("and t.vacate_status=63 and p.proc_result is null");
				stuVacateInfo.setEnd_proc_id(64);
			}	
		}
		stuVacateInfo.setCreate_auth_id(teacher_auth_id);
		stuVacateInfo.setRole_id(role_id);
		String json = stuVacateInfoService.selectVacateList(page, rows, stuVacateInfo).toString();
		return StringUtil.dnull(json);
	}
	
	/***
	 * 请假审批
	 * @param req
	 * @return
	 * @author hezxgetApprNum
	 * @date 2017年4月6日
	 */
	@ResponseBody
	@RequestMapping(value="/submitReviewLeaveRewrite")
	public Object submitReviewLeaveRewrite(HttpServletRequest req)throws Exception{
		return	reviewLeaveService.submitReviewLeaveRewrite(req);
	}

	@ResponseBody
	@RequestMapping(value="/selectCathedraNotice")
	public Object selectCathedraNotice(HttpServletRequest req){
		return cathedraPlanService.selectCathedraNotice(req);
	} 
	
	
	@ResponseBody
	@RequestMapping(value="/countCathedraNotice")
	public Object countCathedraNotice(HttpServletRequest req){
		return cathedraPlanService.countCathedraNotice(req);
	} 

	@ResponseBody
	@RequestMapping(value="/getStudentInfo")
	public Object getStudentInfo(HttpServletRequest req){
		return trainPlanService.getStudentInfo(req);
	} 

	/***
	 * 科教科学生综合查询页面
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年5月10日
	 */
	@ResponseBody
	@RequestMapping(value="/orgaPageInfo")
	public Object orgaPageInfo(HttpServletRequest req) {
		return trainPlanService.orgaPageInfo(req); 
	}

	/***
	 * 获取教学秘书下的待考勤学生信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月27日
	 */
	@ResponseBody
	@RequestMapping(value="/selectAttendanceStuInfo")
	public Object selectAttendanceStuInfo(HttpServletRequest req) {
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setTrain_dept_id(orga_id.toString());
		trainPlan.setTrain_status_str(Consts.ProcessInfo.TRAINPROCESSInAll);
		List<TrainPlan> trainPlanList = trainPlanService.selectListBySecretaryAuthId(trainPlan);
		List<StuAttendanceInfo> stuAttendanceInfoList = new ArrayList<StuAttendanceInfo>();
		StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
		// 获取当前时间
		java.util.Date date = new java.util.Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Integer attend_date = Integer.parseInt(format.format(date));
		stuAttendanceInfo.setAttend_date(attend_date);
		stuAttendanceInfo.setState("Y");
		for (TrainPlan tp : trainPlanList) {
			StuAttendanceInfo sai = new StuAttendanceInfo();
			stuAttendanceInfo.setStu_auth_id(tp.getStu_auth_id());
			stuAttendanceInfo.setOrga_id(Integer.parseInt(tp.getTrain_dept_id()));
			List<StuAttendanceInfo> stuAttendanceInfoGet = stuAttendanceInfoService.getStuAttendanceInfoByAttendDate(stuAttendanceInfo);
			if (stuAttendanceInfoGet != null && !stuAttendanceInfoGet.isEmpty() && (stuAttendanceInfoGet.get(0).getAttend_state() == 0 || stuAttendanceInfoGet.get(0).getAttend_state() == 1)) {
				sai.setId(stuAttendanceInfoGet.get(0).getId());
				sai.setStu_auth_id(tp.getStu_auth_id());
				sai.setStu_name(tp.getName());
				sai.setType_name(tp.getTypeName());
				sai.setAttend_state_str(stuAttendanceInfoGet.get(0).getAttend_state_str());
				stuAttendanceInfoList.add(sai);
			}else if(stuAttendanceInfoGet.isEmpty()) {
				sai.setStu_auth_id(tp.getStu_auth_id());
				sai.setStu_name(tp.getName());
				sai.setType_name(tp.getTypeName());
				sai.setAttend_state_str("已考勤");
				stuAttendanceInfoList.add(sai);
			}	
		}
		return StringUtil.returns(true, stuAttendanceInfoList);
	}
	/**
	 * 修改考勤状态
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月27日
	 */
	@ResponseBody
	@RequestMapping(value="/manageAttendState")
	public Object manageAttendState(HttpServletRequest req) {
		return stuAttendanceInfoService.manageAttendState(req);
	}  
	
	/***
	 * 查看系统配置的二维码考勤是否开启
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月31日
	 */
	@ResponseBody
	@RequestMapping(value="/getAbilityOfQRCode")
	public Object getAbilityOfQRCode(HttpServletRequest req) {
		int flag = 0;//  二维码考勤开关开启标识
		List<SysConfig> sysConfigList = (List<SysConfig>)req.getSession().getServletContext().getAttribute(Consts.SESSION_SYS_CONFIG);
		for(SysConfig sysConfig : sysConfigList){
			if(sysConfig.getConfig_code() != null && sysConfig.getConfig_code().equals("QRCode_attendance_config")){
				flag = sysConfig.getConfig_flag();
			}
		}
		return StringUtil.returns(true, flag);
	}  
	

	/***
	 * 查看系统配置的考勤二维码的有效时间
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月31日
	 */
	@ResponseBody
	@RequestMapping(value="/getQRCodeActiveTime")
	public Object getQRCodeActiveTime(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		int flag = 0;//  考勤二维码开关开启标识
		int active_time = 60;//  有效时间
		List<SysConfig> sysConfigList = (List<SysConfig>)req.getSession().getServletContext().getAttribute(Consts.SESSION_SYS_CONFIG);
		for(SysConfig sysConfig : sysConfigList){
			if(sysConfig.getConfig_code() != null && sysConfig.getConfig_code().equals("QRCode_active_time")){
				flag = sysConfig.getConfig_flag();
				if (flag == 1) {
					active_time = Integer.parseInt(sysConfig.getConfig_data());
					map.put("active_time", active_time);
				}
			}
		}
		map.put("flag", flag);
		return map;
	} 
	
	
	/**
	 * 得到当前科室的考勤二维码字符
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月31日
	 */
	@ResponseBody
	@RequestMapping(value="/getAttendanceQRStr")
	public Object getAttendanceQRStr(HttpServletRequest req) {
		String qrStr = stuAttendanceInfoService.getAttendanceQRStr(req);
		return StringUtil.returns(true, qrStr);
	}
	
	/***
	 * 获取考勤数
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月26日
	 */
	@ResponseBody
	@RequestMapping(value="/getAttendanceCount")
	public Object getAttendanceCount(HttpServletRequest req) {
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setTrain_dept_id(orga_id.toString());
		trainPlan.setTrain_status_str(Consts.ProcessInfo.TRAINPROCESSInAll);
		List<TrainPlan> trainPlanList = trainPlanService.selectListBySecretaryAuthId(trainPlan);
		int whole_count = 0;//  全部人数
		int attendance_count = 0;//  已考勤人数
		int no_attendance_count = 0;//  未考勤人数
		int leave_count = 0;//  请假人数
		StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
		// 获取当前时间
		java.util.Date date = new java.util.Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Integer attend_date = Integer.parseInt(format.format(date));
		stuAttendanceInfo.setAttend_date(attend_date);
		stuAttendanceInfo.setState("Y");
		for (TrainPlan tp : trainPlanList) {
			stuAttendanceInfo.setStu_auth_id(tp.getStu_auth_id());
			stuAttendanceInfo.setOrga_id(Integer.parseInt(tp.getTrain_dept_id()));
			List<StuAttendanceInfo> stuAttendanceInfoGet = stuAttendanceInfoService.getStuAttendanceInfoByAttendDate(stuAttendanceInfo);
			if (stuAttendanceInfoGet.isEmpty() || (stuAttendanceInfoGet != null && stuAttendanceInfoGet.get(0).getAttend_state() == 1)) {
				attendance_count++;
			}else if (stuAttendanceInfoGet != null && stuAttendanceInfoGet.get(0).getAttend_state() == 0) {
				no_attendance_count++;
			}else if (stuAttendanceInfoGet != null && (stuAttendanceInfoGet.get(0).getAttend_state() == 2 || stuAttendanceInfoGet.get(0).getAttend_state() == -10
					|| stuAttendanceInfoGet.get(0).getAttend_state() == -20)) {
				leave_count += stuAttendanceInfoGet.size();
			}
		}
		whole_count = attendance_count + no_attendance_count + leave_count;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("whole_count", whole_count);
		map.put("attendance_count", attendance_count);
		map.put("no_attendance_count", no_attendance_count);
		map.put("leave_count", leave_count);
		return map;
	}  
	
	

	/***
	 * 获取教学秘书下的考勤列表信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月26日
	 */
	@ResponseBody
	@RequestMapping(value="/selectStuAttendanceInfoList")
	public Object selectStuAttendanceInfoList(HttpServletRequest req) {
		String attend_state = req.getParameter("attend_state");
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setTrain_dept_id(orga_id.toString());
		trainPlan.setTrain_status_str(Consts.ProcessInfo.TRAINPROCESSInAll);
		List<TrainPlan> trainPlanList = trainPlanService.selectListBySecretaryAuthId(trainPlan);
		List<StuAttendanceInfo> stuAttendanceInfoList = new ArrayList<StuAttendanceInfo>();
		StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
		// 获取当前时间
		java.util.Date date = new java.util.Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Integer attend_date = Integer.parseInt(format.format(date));
		stuAttendanceInfo.setAttend_date(attend_date);
		stuAttendanceInfo.setState("Y");
		for (TrainPlan tp : trainPlanList) {
			StuAttendanceInfo sai = new StuAttendanceInfo();
			stuAttendanceInfo.setStu_auth_id(tp.getStu_auth_id());
			stuAttendanceInfo.setOrga_id(Integer.parseInt(tp.getTrain_dept_id()));
			List<StuAttendanceInfo> stuAttendanceInfoListGet = stuAttendanceInfoService.getStuAttendanceInfoByAttendDate(stuAttendanceInfo);
			if (attend_state != null && attend_state.equals("3")) {
				if (stuAttendanceInfoListGet != null && !stuAttendanceInfoListGet.isEmpty()) {
					for (StuAttendanceInfo stuAttendanceInfoGet : stuAttendanceInfoListGet) {
						StuAttendanceInfo saio = new StuAttendanceInfo();
						saio.setId(stuAttendanceInfoGet.getId());
						saio.setStu_auth_id(tp.getStu_auth_id());
						saio.setStu_name(tp.getName());
						saio.setType_name(tp.getTypeName());
						saio.setAttend_state(stuAttendanceInfoGet.getAttend_state());
						saio.setAttend_state_str(stuAttendanceInfoGet.getAttend_state_str());
						stuAttendanceInfoList.add(saio);
					}
				}else{
					sai.setStu_auth_id(tp.getStu_auth_id());
					sai.setStu_name(tp.getName());
					sai.setType_name(tp.getTypeName());
					sai.setAttend_state(1);
					sai.setAttend_state_str("已考勤");
					stuAttendanceInfoList.add(sai);
				}
			}else if (attend_state != null && attend_state.equals("1")) {
				if (stuAttendanceInfoListGet != null && !stuAttendanceInfoListGet.isEmpty() && stuAttendanceInfoListGet.get(0).getAttend_state() == 1) {
					sai.setId(stuAttendanceInfoListGet.get(0).getId());
					sai.setStu_auth_id(tp.getStu_auth_id());
					sai.setStu_name(tp.getName());
					sai.setType_name(tp.getTypeName());
					sai.setAttend_state(stuAttendanceInfoListGet.get(0).getAttend_state());
					sai.setAttend_state_str(stuAttendanceInfoListGet.get(0).getAttend_state_str());
					stuAttendanceInfoList.add(sai);
				}else if (stuAttendanceInfoListGet.isEmpty()) {
					sai.setStu_auth_id(tp.getStu_auth_id());
					sai.setStu_name(tp.getName());
					sai.setType_name(tp.getTypeName());
					sai.setAttend_state_str("已考勤");
					sai.setAttend_state(1);
					stuAttendanceInfoList.add(sai);
				}
				
			}else if (attend_state != null && attend_state.equals("0")) {
				if (stuAttendanceInfoListGet != null && !stuAttendanceInfoListGet.isEmpty() && stuAttendanceInfoListGet.get(0).getAttend_state() == 0) {
					sai.setId(stuAttendanceInfoListGet.get(0).getId());
					sai.setStu_auth_id(tp.getStu_auth_id());
					sai.setStu_name(tp.getName());
					sai.setType_name(tp.getTypeName());
					sai.setAttend_state(stuAttendanceInfoListGet.get(0).getAttend_state());
					sai.setAttend_state_str(stuAttendanceInfoListGet.get(0).getAttend_state_str());
					stuAttendanceInfoList.add(sai);
				}
				
			}else if (attend_state != null && attend_state.equals("2")) {
				if (stuAttendanceInfoListGet != null && !stuAttendanceInfoListGet.isEmpty()) {
					for (StuAttendanceInfo stuAttendanceInfoGet : stuAttendanceInfoListGet) {
						if (stuAttendanceInfoGet.getAttend_state() == 2 || stuAttendanceInfoGet.getAttend_state() == -10 || stuAttendanceInfoGet.getAttend_state() == -20) {
							StuAttendanceInfo sainfo = new StuAttendanceInfo();
							sainfo.setId(stuAttendanceInfoGet.getId());
							sainfo.setStu_auth_id(tp.getStu_auth_id());
							sainfo.setStu_name(tp.getName());
							sainfo.setType_name(tp.getTypeName());
							sainfo.setAttend_state(stuAttendanceInfoGet.getAttend_state());
							sainfo.setAttend_state_str(stuAttendanceInfoGet.getAttend_state_str());
							stuAttendanceInfoList.add(sainfo);
						}
					}
				}		
			}
		}
		return StringUtil.returns(true, stuAttendanceInfoList);
	}  
	/**
	 * 查询待审阅的日志
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月7日
	 */
	@ResponseBody
	@RequestMapping(value="/selectReviewedDaily")
	public Object selectReviewedDaily(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		//获取页面的参数
		String state= req.getParameter("state");//加载数据时过滤失效数据
		//得到当前用户的authId
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer authId = loginBean.getvUserDetailInfo().getAuth_id();
		StuDailyRecord stuDailyRecord = new StuDailyRecord();
		stuDailyRecord.setState(state);
		stuDailyRecord.setTeacher_auth_id(authId);
		String json ="";
		json = stuDailyRecordService.selectReviewedDaily(page, rows, stuDailyRecord).toString();
		return StringUtil.dnull(json);
	}  
	
	@RequestMapping(value="/selectDailyRecordById")
	public ModelAndView selectDailyRecordById(HttpServletRequest req) {
		return stuDailyRecordService.selectDailyRecordById(req);
	}
	/**
	 * 老师批量审阅日志
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月7日
	 */
	@ResponseBody
	@RequestMapping(value="/batchReview")
	public Object batchReview(HttpServletRequest req) {
		return stuDailyRecordService.batchReview(req);
	}
	
	/***
	 * 查看请假天数可以是半天的开关是否开启
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月8日
	 */
	@ResponseBody
	@RequestMapping(value="/getAbilityOfHalfDayLeave")
	public Object getAbilityOfHalfDayLeave(HttpServletRequest req) {
		int flag = 0;//  请假天数可以是半天开关开启标识
		List<SysConfig> sysConfigList = (List<SysConfig>)req.getSession().getServletContext().getAttribute(Consts.SESSION_SYS_CONFIG);
		for(SysConfig sysConfig : sysConfigList){
			if(sysConfig.getConfig_code() != null && sysConfig.getConfig_code().equals("half_day_leave_config")){
				flag = sysConfig.getConfig_flag();
			}
		}
		return StringUtil.returns(true, flag);
	}
	
	/**
	 * 完成轮转排班的学生列表
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年7月5日
	 */
	@ResponseBody
	@RequestMapping(value="/getStudentList")
	public Object getStudentList(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		StudentInfo student = new StudentInfo();
		student.setState("Y");
		student.setStu_status("2");//  学习中
		List<StudentInfo> list = studentInfoService.selectListStu(student);
		jobj.put("rows", list);
		return StringUtil.dnull(jobj.toString());
	}
	/**
	 * 查询学生的轮转计划
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年7月5日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuTrainPlan")
	public Object getStuTrainPlan(HttpServletRequest req){
		String stu_auth_id = req.getParameter("stu_auth_id");
		JSONObject jobj = new JSONObject();
		if(stu_auth_id!=null&&!stu_auth_id.equals("")){
			TrainPlan plan = new TrainPlan();
			plan.setStu_auth_id(Integer.parseInt(stu_auth_id));
			plan.setState("Y");
			plan.setOrderCondition(" train_start_time");
			List<TrainPlan> list = trainPlanService.selectPlanList(plan);
			jobj.put("rows", list);
		}
		return StringUtil.dnull(jobj.toString());
	}
	
	/**
	 * 轮转排班导出
	 * @param res
	 * @param req
	 */
	@ResponseBody
	@RequestMapping(value="/exportStuTrainPlan")
	public void exportStuTrainPlan(HttpServletResponse res,HttpServletRequest req){
		trainPlanService.exportStuTrainPlan(res,req);
	}
	/**
	 * 获取提前入科的学生列表
	 * @param req
	 * @return
	 * @author guocc
	 * @throws ParseException 
	 * @date 2017年7月3日
	 */
	@ResponseBody
	@RequestMapping(value="/getAdvanceStuInfo")
	public Object getAdvanceStuInfo(HttpServletRequest req) throws ParseException {
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setTrain_dept_id(orga_id.toString());
		trainPlan.setTrain_status(Consts.ProcessInfo.TRAINPROCESSNull);
		List<TrainPlan> trainPlanList = trainPlanService.getAdvanceStuInfo(trainPlan);
		// 获取当前时间
		java.util.Date date = new java.util.Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Integer today = Integer.parseInt(format.format(date));
		int days_advanceAdmission = 7;//默认7天
		//从缓存中读取配置的可以提前入科的天数
		List<SysConfig> sysConfigList = (List<SysConfig>)req.getSession().getServletContext().getAttribute(Consts.SESSION_SYS_CONFIG);
		for(SysConfig sysConfig : sysConfigList){
			if(sysConfig.getConfig_code() != null && sysConfig.getConfig_code().equals("days_advanceAdmission") && sysConfig.getConfig_flag() == 1){
				days_advanceAdmission = Integer.parseInt(sysConfig.getConfig_data());
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<TrainPlan> list =  new ArrayList<TrainPlan>();
		for (TrainPlan trainPlanGet : trainPlanList) {
			if (DateUtil.daysBetween(sdf.parse(today.toString()), sdf.parse(trainPlanGet.getTrain_start_date().toString())) > 0 && DateUtil.daysBetween(sdf.parse(today.toString()), sdf.parse(trainPlanGet.getTrain_start_date().toString())) <= days_advanceAdmission) {
				TrainPlan tp1 = trainPlanService.getOrga_name(trainPlanGet);//  当前科室的信息
				if (tp1 != null) {
					trainPlanGet.setOrga_name(tp1.getOrga_name());
				}
				trainPlanGet.setDays(DateUtil.daysBetween(sdf.parse(today.toString()), sdf.parse(trainPlanGet.getTrain_start_date().toString())));
				list.add(trainPlanGet);
			}
		}
		return StringUtil.returns(true, list);
	}
	
	/***
	 * 根据id查询审批评价
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月1日
	 */
	@ResponseBody
	@RequestMapping(value="/getExamineTextById")
	public Object getExamineTextById(HttpServletRequest req) {
		String id = req.getParameter("id");
		if(id != null && !id.equals("")) {
			StuActiveData stuActiveData = new StuActiveData();
			stuActiveData.setId(Integer.parseInt(id));
			StuActiveData stuActiveDataGet = stuActiveDataService.getExamineTextById(stuActiveData);
			return StringUtil.returns(true, stuActiveDataGet);
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
	
	/***
	 * 保存病程记录
	 * 
	 * 
	 */
	  @ResponseBody
	  @RequestMapping(value="/saveProgressNote")
	  public Object saveProgressNote(HttpServletRequest req){
		  return medicalRecordService.saveProgressNote(req);
	  }
	  
	/***
	 * 编辑前查询表单内容
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月16日
	 */
	@ResponseBody
	@RequestMapping(value="/updateStuActiveData")
	public Object updateStuActiveData(HttpServletRequest req) {
		String id = req.getParameter("id");
		StuActiveData stuActiveDataGet = new StuActiveData();
		if(id != null && !id.equals("")) {
			stuActiveDataGet = stuActiveDataService.selectOne(Integer.parseInt(id));
		}
		return StringUtil.returns(true, stuActiveDataGet);
	}
	
	/**
	 * 删除录入信息
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年8月16日
	 */
	@ResponseBody
	@RequestMapping(value="/delStuActiveData")
	public Object delStuActiveData(HttpServletRequest req){
		int i=stuActiveDataService.delStuActiveData(req);
		if(i>0){
			return	StringUtil.returns(true, "操作成功！");
		}else{
			return	StringUtil.returns(false, "操作失败！");	
		}
	}
	
	/**
	 * 查询所有数据录入情况  （相应学生的）
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年8月17日
	 */
	@ResponseBody
	@RequestMapping(value="/findStuActiveData")
	public Object findStuActiveData(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		String json ="";
		json = stuActiveDataService.findStuActiveData(page, rows, req).toString();
		return	StringUtil.dnull(json);
	}
	
	/***
	 * 带教老师查询学生提交的医疗文书
	 * @param req
	 * @return
	 * @author zhengc
	 * @date 2017年8月22日
	 */
	@ResponseBody
	@RequestMapping(value="/selectMedDocByStu")
	public Object selectMedDocByStu(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req){
		String json="";
		json=medicalRecordService.selectMedDocByStu(page,rows,req).toString();
		return StringUtil.dnull(json);
	}
	
	
	
	/**
	 * 保存医疗文书（除医嘱外）
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月18日
	 */
	@ResponseBody
	@RequestMapping(value="/saveMedicalRecord")
	public Object saveMedicalRecord(HttpServletRequest req) {
		return medicalRecordService.saveMedicalRecord(req);
	}
	
	/**
	 * 保存老师批改后的医疗文书（除医嘱外）
	 * @param req
	 * @return
	 * @author zhengc
	 * @date 2017年8月23日
	 */
	@ResponseBody
	@RequestMapping(value="/saveMedicalRecordTea")
	public Object saveMedicalRecordTea(HttpServletRequest req) {
		return medicalRecordService.saveMedicalRecordTea(req);
	}
	/**
	 * 保存医疗文书--医嘱
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月18日
	 */
	@ResponseBody
	@RequestMapping(value="/saveMedicalAdvice")
	public Object saveMedicalAdvice(HttpServletRequest req) {
		return medicalAdviceMainService.saveMedicalAdvice(req);
	}
	/**
	 * 保存老师批改后医疗文书--医嘱
	 * @param req
	 * @return
	 * @author zhengc
	 * @date 2017年8月24日
	 */
	@ResponseBody
	@RequestMapping(value="/saveMedicalAdviceTea")
	public Object saveMedicalAdviceTea(HttpServletRequest req) {
		return medicalAdviceMainService.saveMedicalAdviceTea(req);
	}
	/**
	 * 根据id查询医疗文书（除医嘱外）
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月21日
	 */
	@ResponseBody
	@RequestMapping(value="/selectMedicalRecordById")
	public Object selectMedicalRecordById(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		//从页面获取数据
		String id = req.getParameter("id");
		String type_id = req.getParameter("type_id");
		MedicalRecord medicalRecord = new MedicalRecord();
		if (id != null && !id.equals("")) {
			medicalRecord = medicalRecordService.selectOne(Integer.parseInt(id));
		}
		mv.addObject("medicalRecord", medicalRecord);
		if (type_id != null && type_id.equals("1")) {
			mv.setViewName("web/teach/medicalDocumentConTwo");
		}else if (type_id != null && type_id.equals("2")) {
			mv.setViewName("web/teach/medicalDocumentConThree");
		}else if (type_id != null && type_id.equals("3")) {
			mv.setViewName("web/teach/medicalDocumentConFour");
		}
		return mv;
	}
	
	/**
	 * 查询医疗文书--入院记录--诊断
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月21日
	 */
	@ResponseBody
	@RequestMapping(value="/selectMedicalDiagnose")
	public Object selectMedicalDiagnose(@Param("rows") int rows, @Param("page") int page, HttpServletRequest req){
		String json ="";
		//获取页面的参数
		String id = req.getParameter("id");
		MedicalDiagnose medicalDiagnose = new MedicalDiagnose();
		if (id != null && !id.equals("")) {
			medicalDiagnose.setMr_id(Integer.parseInt(id));
		}
		medicalDiagnose.setState("Y");
		medicalDiagnose.setOrderCondition("id asc");
		json = medicalDiagnoseService.selectMedicalDiagnose(page, rows, medicalDiagnose).toString();
		return StringUtil.dnull(json);
	}
	
	/**
	 * 根据id查询医疗文书--医嘱
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月21日
	 */
	@ResponseBody
	@RequestMapping(value="/selectMedicalAdviceById")
	public Object selectMedicalAdviceById(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		//从页面获取数据
		String id = req.getParameter("id");
		MedicalAdviceMain medicalAdviceMain = new MedicalAdviceMain();
		if (id != null && !id.equals("")) {
			medicalAdviceMain = medicalAdviceMainService.selectOne(Integer.parseInt(id));
		}
		MedicalAdviceSub medicalAdviceSub = new MedicalAdviceSub();
		if (medicalAdviceMain != null) {
			medicalAdviceSub.setMam_id(medicalAdviceMain.getId());
		}
		medicalAdviceSub.setState("Y");
		medicalAdviceSub.setOrderCondition("mas.id asc");
		List<MedicalAdviceSub> medicalAdviceSubList = medicalAdviceSubService.selectMedicalAdviceSubList(medicalAdviceSub);
		medicalAdviceMain.setMedicalAdviceSubList(medicalAdviceSubList);
		mv.addObject("medicalAdviceMain", medicalAdviceMain);
		mv.setViewName("web/teach/medicalDocumentConOne");
		return mv;
	}
	
	/**
	 * 根据id查询医疗文书（除医嘱外）
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月21日
	 */
	@ResponseBody
	@RequestMapping(value="/updateMedicalRecord")
	public Object updateMedicalRecord(HttpServletRequest req) {
		//从页面获取数据
		String id = req.getParameter("id");
		MedicalRecord medicalRecord = new MedicalRecord();
		if (id != null && !id.equals("")) {
			medicalRecord = medicalRecordService.selectOne(Integer.parseInt(id));
		}
		return StringUtil.returns(true, medicalRecord);
	}
	
	/**
	 * 根据id查询医疗文书--医嘱
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月21日
	 */
	@ResponseBody
	@RequestMapping(value="/updateMedicalAdvice")
	public Object updateMedicalAdvice(HttpServletRequest req) {
		//从页面获取数据
		String id = req.getParameter("id");
		MedicalAdviceMain medicalAdviceMain = new MedicalAdviceMain();
		if (id != null && !id.equals("")) {
			medicalAdviceMain = medicalAdviceMainService.selectOne(Integer.parseInt(id));
		}
		MedicalAdviceSub medicalAdviceSub = new MedicalAdviceSub();
		if (medicalAdviceMain != null) {
			medicalAdviceSub.setMam_id(medicalAdviceMain.getId());
		}
		medicalAdviceSub.setState("Y");
		medicalAdviceSub.setOrderCondition("id asc");
		List<MedicalAdviceSub> medicalAdviceSubList = medicalAdviceSubService.selectList(medicalAdviceSub);
		medicalAdviceMain.setMedicalAdviceSubList(medicalAdviceSubList);
		return StringUtil.returns(true, medicalAdviceMain);
	}
	
	/**
	 * 删除医疗文书
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月22日
	 */
	@ResponseBody
	@RequestMapping(value="/delMedicalDocument")
	public Object delMedicalDocument(HttpServletRequest req){
		return medicalRecordService.delMedicalDocument(req);
	}
	
	/**
	 * 医疗文书对比查看
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月23日
	 */
	@ResponseBody
	@RequestMapping(value="/medicalDocumentComparison")
	public Object medicalDocumentComparison(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		//获取前台参数
		String id = req.getParameter("id");
		String type_id = req.getParameter("type_id");
		if (type_id != null && (type_id.equals("1") || type_id.equals("2") || type_id.equals("3"))) {//  除医嘱外
			List<MedicalDiagnose> stuMedicalDiagnoseList = new ArrayList<MedicalDiagnose>();
			MedicalRecord stuMedicalRecord = new MedicalRecord();
			if (id != null && !id.equals("")) {
				stuMedicalRecord = medicalRecordService.selectMedicalRecordById(Integer.parseInt(id));
			}
			mv.addObject("stuMedicalDocument", stuMedicalRecord);
			//
			if (type_id.equals("1")) {//  入院记录
				MedicalDiagnose medicalDiagnose = new MedicalDiagnose();
				if (id != null && !id.equals("")) {
					medicalDiagnose.setMr_id(Integer.parseInt(id));
				}
				medicalDiagnose.setState("Y");
				medicalDiagnose.setOrderCondition("id asc");
				stuMedicalDiagnoseList = medicalDiagnoseService.selectList(medicalDiagnose);
				mv.addObject("stuMedicalDiagnoseList", stuMedicalDiagnoseList);
			}
			//
			if (stuMedicalRecord != null && stuMedicalRecord.getCorrect_status().equals("Y")) {//  老师做过批改
				MedicalRecord teaMedicalRecord = new MedicalRecord();
				teaMedicalRecord.setState("Y");
				teaMedicalRecord.setPk_code(stuMedicalRecord.getPk_code());
				teaMedicalRecord.setQueryCondition("and correct_status is null");
				List<MedicalRecord> medicalRecordList = medicalRecordService.selectList(teaMedicalRecord);
//				//对比
//				DocumentCompare docCompare = new DocumentCompare();
//			    docCompare.Compare(stuMedicalRecord.getContent(), medicalRecordList.get(0).getContent());
//			    String newContent = docCompare.getHtmlResult();
//			    medicalRecordList.get(0).setContent(newContent);
				mv.addObject("teaMedicalRecord", medicalRecordList.get(0));
				//
				if (type_id.equals("1")) {
					MedicalDiagnose medicalDiagnose = new MedicalDiagnose();
					medicalDiagnose.setMr_id(medicalRecordList.get(0).getId());
					medicalDiagnose.setState("Y");
					medicalDiagnose.setOrderCondition("id asc");
					List<MedicalDiagnose> teaMedicalDiagnoseList = medicalDiagnoseService.selectList(medicalDiagnose);
//					DocumentCompare dc = new DocumentCompare();
//					for (int i = 0; i < teaMedicalDiagnoseList.size(); i++) {
//						if (null == stuMedicalDiagnoseList || stuMedicalDiagnoseList.size() == 0) {
//							dc.Compare("", teaMedicalDiagnoseList.get(i).getDiag_name());
//						    teaMedicalDiagnoseList.get(i).setDiag_name(dc.getHtmlResult());
//						    dc.Compare("", teaMedicalDiagnoseList.get(i).getIcd_code());
//						    teaMedicalDiagnoseList.get(i).setIcd_code(dc.getHtmlResult());
//						}else if (i < stuMedicalDiagnoseList.size()) {
//							dc.Compare(stuMedicalDiagnoseList.get(i).getDiag_name(), teaMedicalDiagnoseList.get(i).getDiag_name());
//						    teaMedicalDiagnoseList.get(i).setDiag_name(dc.getHtmlResult());
//						    dc.Compare(stuMedicalDiagnoseList.get(i).getIcd_code(), teaMedicalDiagnoseList.get(i).getIcd_code());
//						    teaMedicalDiagnoseList.get(i).setIcd_code(dc.getHtmlResult());
//						}
//					}
					mv.addObject("teaMedicalDiagnoseList", teaMedicalDiagnoseList);
				}
			}
		}else{//  医嘱
			MedicalAdviceMain stuMedicalAdviceMain = new MedicalAdviceMain();
			if (id != null && !id.equals("")) {
				stuMedicalAdviceMain = medicalAdviceMainService.selectMedicalAdviceMainById(Integer.parseInt(id));
			}
			mv.addObject("stuMedicalDocument", stuMedicalAdviceMain);
			//医嘱子表--MEDICAL_ADVICE_SUB
			MedicalAdviceSub medicalAdviceSub = new MedicalAdviceSub();
			if (id != null && !id.equals("")) {
				medicalAdviceSub.setMam_id(Integer.parseInt(id));
			}
			medicalAdviceSub.setState("Y");
			medicalAdviceSub.setOrderCondition("mas.id asc");
			List<MedicalAdviceSub> stuMedicalAdviceSubList = medicalAdviceSubService.selectMedicalAdviceSubList(medicalAdviceSub);
			mv.addObject("stuMedicalAdviceSubList", stuMedicalAdviceSubList);
			//
			if (stuMedicalAdviceMain != null && stuMedicalAdviceMain.getCorrect_status().equals("Y")) {//  老师已经做过改批
				MedicalAdviceMain teaMedicalAdviceMain = new MedicalAdviceMain();
				teaMedicalAdviceMain.setState("Y");
				teaMedicalAdviceMain.setPk_code(stuMedicalAdviceMain.getPk_code());
				teaMedicalAdviceMain.setQueryCondition("and correct_status is null");
				List<MedicalAdviceMain> medicalAdviceMainList = medicalAdviceMainService.selectList(teaMedicalAdviceMain);
				mv.addObject("teaMedicalAdviceMain", medicalAdviceMainList.get(0));
				//医嘱子表--MEDICAL_ADVICE_SUB
				MedicalAdviceSub mas = new MedicalAdviceSub();
				mas.setMam_id(medicalAdviceMainList.get(0).getId());
				mas.setState("Y");
				mas.setOrderCondition("id asc");
				List<MedicalAdviceSub> teaMedicalAdviceSubList = medicalAdviceSubService.selectMedicalAdviceSubList(mas);
//				DocumentCompare dc = new DocumentCompare();
//				for (int i = 0; i < teaMedicalAdviceSubList.size(); i++) {
//					if (null == stuMedicalAdviceSubList || stuMedicalAdviceSubList.size() == 0) {
//						dc.Compare("", teaMedicalAdviceSubList.get(i).getAdvice_name());
//						teaMedicalAdviceSubList.get(i).setAdvice_name(dc.getHtmlResult());
//						dc.Compare("", teaMedicalAdviceSubList.get(i).getAdvice_spec());
//						teaMedicalAdviceSubList.get(i).setAdvice_spec(dc.getHtmlResult());
////						dc.Compare("", teaMedicalAdviceSubList.get(i).getAdvice_dose().toString());
////						teaMedicalAdviceSubList.get(i).setAdvice_dose(Integer.parseInt(dc.getHtmlResult()));
//						dc.Compare("", teaMedicalAdviceSubList.get(i).getDose_unit_code_str());
//						teaMedicalAdviceSubList.get(i).setDose_unit_code_str(dc.getHtmlResult());
//						dc.Compare("", teaMedicalAdviceSubList.get(i).getPath_code_str());
//						teaMedicalAdviceSubList.get(i).setPath_code_str(dc.getHtmlResult());
//						dc.Compare("", teaMedicalAdviceSubList.get(i).getFrequency_code_str());
//						teaMedicalAdviceSubList.get(i).setFrequency_code_str(dc.getHtmlResult());
////						dc.Compare("", teaMedicalAdviceSubList.get(i).getTotal_dose().toString());
////						teaMedicalAdviceSubList.get(i).setTotal_dose(Integer.parseInt(dc.getHtmlResult()));
//						dc.Compare("", teaMedicalAdviceSubList.get(i).getTotal_dose_unit_code_str());
//						teaMedicalAdviceSubList.get(i).setTotal_dose_unit_code_str(dc.getHtmlResult());
//					}else if (i < stuMedicalAdviceSubList.size()) {
//						dc.Compare(stuMedicalAdviceSubList.get(i).getAdvice_name(), teaMedicalAdviceSubList.get(i).getAdvice_name());
//						teaMedicalAdviceSubList.get(i).setAdvice_name(dc.getHtmlResult());
//						dc.Compare(stuMedicalAdviceSubList.get(i).getAdvice_spec(), teaMedicalAdviceSubList.get(i).getAdvice_spec());
//						teaMedicalAdviceSubList.get(i).setAdvice_spec(dc.getHtmlResult());
////						dc.Compare("", teaMedicalAdviceSubList.get(i).getAdvice_dose().toString());
////						teaMedicalAdviceSubList.get(i).setAdvice_dose(Integer.parseInt(dc.getHtmlResult()));
//						dc.Compare(stuMedicalAdviceSubList.get(i).getDose_unit_code_str(), teaMedicalAdviceSubList.get(i).getDose_unit_code_str());
//						teaMedicalAdviceSubList.get(i).setDose_unit_code_str(dc.getHtmlResult());
//						dc.Compare(stuMedicalAdviceSubList.get(i).getPath_code_str(), teaMedicalAdviceSubList.get(i).getPath_code_str());
//						teaMedicalAdviceSubList.get(i).setPath_code_str(dc.getHtmlResult());
//						dc.Compare(stuMedicalAdviceSubList.get(i).getFrequency_code_str(), teaMedicalAdviceSubList.get(i).getFrequency_code_str());
//						teaMedicalAdviceSubList.get(i).setFrequency_code_str(dc.getHtmlResult());
////						dc.Compare("", teaMedicalAdviceSubList.get(i).getTotal_dose().toString());
////						teaMedicalAdviceSubList.get(i).setTotal_dose(Integer.parseInt(dc.getHtmlResult()));
//						dc.Compare(stuMedicalAdviceSubList.get(i).getTotal_dose_unit_code_str(), teaMedicalAdviceSubList.get(i).getTotal_dose_unit_code_str());
//						teaMedicalAdviceSubList.get(i).setTotal_dose_unit_code_str(dc.getHtmlResult());
//					}
//				}
				mv.addObject("teaMedicalAdviceSubList", teaMedicalAdviceSubList);
			}
		}
		//
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		mv.addObject("orga_name", orga_name);
		mv.setViewName("web/teach/medicalDocumentComparison");
		return mv;
	}
	
	/**
	 * 查询带教老师，秘书下学生
	 * 
	 * 
	 */
	
	@ResponseBody
	@RequestMapping(value="/peopleChoose")
	public Object peopleChoose(HttpServletRequest req){
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		String role_code=loginBean.getvUserDetailInfo().getRole_code();
		Integer role_auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		Integer orga_id=loginBean.getvUserDetailInfo().getOrga_id();
		List<TrainPlan> trainPlan=new ArrayList<TrainPlan>();
		TrainPlan plan=new TrainPlan();
		if(role_code.equals("R_TEA")){
			plan.setTeacher_auth_id(role_auth_id);
			plan.setState("Y");
			trainPlan=trainPlanService.selectAllStuByTea(plan);//查询老师下学生
		} else{
			plan.setSecretary_auth_id(role_auth_id);
			plan.setTrain_dept_id(orga_id.toString());
			plan.setTrain_status_str(Consts.ProcessInfo.TRAINPROCESSAll);
			plan.setState("Y");
			trainPlan=trainPlanService.selectListByHomeworkAuthId(plan);//查询秘书下学生
		}
		return StringUtil.returns(true, trainPlan);
	}
	/**
	 * 
	 * 老师发布作业给学生
	 */
	@ResponseBody
	@RequestMapping(value="/sendHomeworkToStu")
	public Object sendHomeworkToStu(HttpServletRequest req){
		
		return taskPublishService.sendHomeworkToStu(req);
	}
	
	/***
	 * 
	 * 得到老师，秘书发布的资料
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/getTaskPub")
	public Object getTaskPub(@Param("rows")int rows,@Param("page") int page,HttpServletRequest req){
		return taskPublishService.getTaskPub(page,rows,req);
	}
	
	/**
	 * 删除发布的作业（老师，秘书）
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/delTask")
	public Object delTask(HttpServletRequest req){
		return taskPublishService.delTask(req);
	}
	/**
	 *查看发布的作业（老师，秘书）
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/updateTask")
	public Object updateTask(HttpServletRequest req){
		return taskPublishService.updateTask(req);
	}
	
  /**
   * 
   * 修改发布的作业（老师，秘书）
   * 
   */
	@ResponseBody
	@RequestMapping(value="/sendHomeworkEditToStu")
	public Object sendHomeworkEditToStu(HttpServletRequest req){
		return taskPublishService.sendHomeworkEditToStu(req);
	}
	
	/**
	 * 
	 * 学生查询老师发布的作业
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/getTaskPubStu")
	public Object getTaskPubStu(@Param("rows")int rows,@Param("page") int page,HttpServletRequest req){
		return taskDealService.getTaskPubStu(page,rows,req);
	}
	
	/**
	 * 
	 * 学生查看作业
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/answerTask")
	public Object answerTask(HttpServletRequest req){
		return taskDealService.answerTask(req);
	}
	
	/**
	 * 
	 *学生解答作业
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/saveStuContent")
	public Object saveStuContent(HttpServletRequest req){
		return taskDealService.saveStuContent(req);
	}
	
	
	
	/**
	 * 拼接组织医嘱部分下拉框选项
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月28日
	 */
	@ResponseBody
	@RequestMapping(value="/formMedicalAdviceOpt")
	public Object formMedicalAdviceOpt(HttpServletRequest req) {
		List<SysDictSub> sysDictSubList = (List<SysDictSub>)req.getSession().getServletContext().getAttribute(Consts.SESSION_SYS_DICT_SUB);
		return StringUtil.returns(true, sysDictSubList);
	}

	
	/**
	 * 学生获取病例讨论信息
	 * @param req
	 * @return
	 * @author xudk
	 * @date 2017年9月4日
	 */
	@ResponseBody
	@RequestMapping(value="/findDiscuss")
	public Object findDiscuss(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req) {
		JSONObject json=new JSONObject();
		MedicalCaseMain medicalCaseMain=new MedicalCaseMain();
		json = medicalCaseMainService.selectPageDiscuss(page, rows, medicalCaseMain,req);
		return	StringUtil.dnull(json.toString());
	}	
	
	
	
	/**
	 * 获取病例讨论人数
	 * @param req
	 * @return
	 * @author xudk
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/discussStudentNum")
	public Object discussStudentNum(HttpServletRequest req) {
		
		return	medicalCaseMemberService.selectNum(req);
	}	
	/**
	 * 提交阐述内容
	 * @param req
	 * @return
	 * @author xudk
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/submitExponent")
	public Object submitExponent(HttpServletRequest req) {
		String mcm_id=req.getParameter("mcm_id");
		String exponent_content=req.getParameter("exponent_content");
		Timestamp start_time = new Timestamp(System.currentTimeMillis()); 
		
		MedicalCaseMain medicalCaseMain=new MedicalCaseMain();
		medicalCaseMain.setId(Integer.parseInt(mcm_id));
		medicalCaseMain.setExponent_content(exponent_content);
		medicalCaseMain.setStart_time(start_time);
		medicalCaseMain.setStatus("2");
		int num= medicalCaseMainService.update(medicalCaseMain);
		if(num>0){
			return StringUtil.returns(true, "提交成功");
		}else{
			return StringUtil.returns(false, "提交失败");
		}
	}
	
	
	
	
	
	/**
	 * 获取病例讨论内容
	 * @param req
	 * @return
	 * @author xudk
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/discussinfomation")
	public Object discussinfomation(HttpServletRequest req) {
		
		return	medicalCaseDiscussService.discussinfomation(req);
	}	
	
	
	
	/**
	 * 提交讨论内容
	 * @param req
	 * @return
	 * @author xudk
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/submitStuDiscuss")
	public Object submitStuDiscuss(HttpServletRequest req) {
		String mcm_id=req.getParameter("mcm_id");
		String content=req.getParameter("content");
		String discuss_auth_id=req.getParameter("discuss_auth_id");
		Timestamp discuss_time = new Timestamp(System.currentTimeMillis()); 
		
		MedicalCaseMain medicalCaseMain=new MedicalCaseMain();
		medicalCaseMain.setStatus("3");
		medicalCaseMain.setId(Integer.parseInt(mcm_id));
		int num=medicalCaseMainService.update(medicalCaseMain);
		
		MedicalCaseDiscuss medicalCaseDiscuss=new MedicalCaseDiscuss();
		medicalCaseDiscuss.setId(medicalCaseDiscussService.getSequence());
		medicalCaseDiscuss.setMcm_id(Integer.parseInt(mcm_id));
		medicalCaseDiscuss.setContent(content);
		medicalCaseDiscuss.setDiscuss_auth_id(Integer.parseInt(discuss_auth_id));
		medicalCaseDiscuss.setDiscuss_time(discuss_time);
		medicalCaseDiscuss.setState("Y");
		
		int status= medicalCaseDiscussService.add(medicalCaseDiscuss);
		if(status>0&&num>0){
			return StringUtil.returns(true, "提交成功");
		}else{
			return StringUtil.returns(false, "提交失败");
		}
	}
	
	/**
	 * 获取病例讨论内容1
	 * @param req
	 * @return
	 * @author xudk
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/discussinfomation1")
	public Object discussinfomation1(HttpServletRequest req) {
		
		return	medicalCaseDiscussService.discussinfomation1(req);
	}	
	/**
	 * 获取病例讨论内容其他学生
	 * @param req
	 * @return
	 * @author xudk
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/discussinfomation2")
	public Object discussinfomation2(HttpServletRequest req) {
		
		return	medicalCaseDiscussService.discussinfomation2(req);
	}
	
	
	
	/**
	 * 
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月1日
	 */
	@ResponseBody
	@RequestMapping(value="/makeComparison")
	public Object makeComparison(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		//取前台数据
		String id = req.getParameter("id");
		String type_id = req.getParameter("type_id");
		String compareContent = "";
		if (type_id != null && (type_id.equals("1") || type_id.equals("2") || type_id.equals("3"))) {
			MedicalRecord medicalRecord = new MedicalRecord();
			if (id != null && !id.equals("")) {
				medicalRecord = medicalRecordService.selectOne(Integer.parseInt(id));
			}
			MedicalRecord teaMedicalRecord = new MedicalRecord();
			teaMedicalRecord.setState("Y");
			teaMedicalRecord.setPk_code(medicalRecord.getPk_code());
			teaMedicalRecord.setQueryCondition("and correct_status is null");
			List<MedicalRecord> medicalRecordList = medicalRecordService.selectList(teaMedicalRecord);
			//内容对比
			DocumentCompare docCompare = new DocumentCompare();
		    docCompare.Compare(medicalRecord.getContent(), medicalRecordList.get(0).getContent());
		    compareContent += docCompare.getHtmlResult();
		    if (type_id.equals("1")) {//  入院记录
		    	MedicalDiagnose medicalDiagnose = new MedicalDiagnose();
				if (id != null && !id.equals("")) {
					medicalDiagnose.setMr_id(Integer.parseInt(id));
				}
				medicalDiagnose.setState("Y");
				medicalDiagnose.setOrderCondition("id asc");
				List<MedicalDiagnose> stuMedicalDiagnoseList = medicalDiagnoseService.selectList(medicalDiagnose);
				MedicalDiagnose md = new MedicalDiagnose();
				md.setMr_id(medicalRecordList.get(0).getId());
				md.setState("Y");
				md.setOrderCondition("id asc");
				List<MedicalDiagnose> teaMedicalDiagnoseList = medicalDiagnoseService.selectList(md);
				compareContent += "<font style=\"font-size:14px;font-weight:bold;\">诊断：</font>";
				compareContent += medicalRecordService.getTableCompareContent(1, stuMedicalDiagnoseList, teaMedicalDiagnoseList);
		    }
		}else if (type_id != null && type_id.equals("4")) {//  医嘱
			MedicalAdviceMain stuMedicalAdviceMain = new MedicalAdviceMain();
			if (id != null && !id.equals("")) {
				stuMedicalAdviceMain = medicalAdviceMainService.selectMedicalAdviceMainById(Integer.parseInt(id));
			}
			//医嘱子表--MEDICAL_ADVICE_SUB
			MedicalAdviceSub medicalAdviceSub = new MedicalAdviceSub();
			if (id != null && !id.equals("")) {
				medicalAdviceSub.setMam_id(Integer.parseInt(id));
			}
			medicalAdviceSub.setState("Y");
			medicalAdviceSub.setOrderCondition("mas.id asc");
			List<MedicalAdviceSub> stuMedicalAdviceSubList = medicalAdviceSubService.selectMedicalAdviceSubList(medicalAdviceSub);
			//
			MedicalAdviceMain teaMedicalAdviceMain = new MedicalAdviceMain();
			teaMedicalAdviceMain.setState("Y");
			teaMedicalAdviceMain.setPk_code(stuMedicalAdviceMain.getPk_code());
			teaMedicalAdviceMain.setQueryCondition("and correct_status is null");
			List<MedicalAdviceMain> medicalAdviceMainList = medicalAdviceMainService.selectList(teaMedicalAdviceMain);
			//医嘱子表--MEDICAL_ADVICE_SUB
			MedicalAdviceSub mas = new MedicalAdviceSub();
			mas.setMam_id(medicalAdviceMainList.get(0).getId());
			mas.setState("Y");
			mas.setOrderCondition("id asc");
			List<MedicalAdviceSub> teaMedicalAdviceSubList = medicalAdviceSubService.selectMedicalAdviceSubList(mas);
			compareContent += medicalRecordService.getTableCompareContent(4, stuMedicalAdviceSubList, teaMedicalAdviceSubList);	
		}
		map.put("compareContent", compareContent);
		return map;
	}
	
	/**
	 * 加载组织讨论人和阐述人信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月4日
	 */
	@ResponseBody
	@RequestMapping(value="/formDiscussantInfo")
	public Object formDiscussantInfo(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer role_id = loginBean.getvUserDetailInfo().getRole_id();
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setState("Y");
		trainPlan.setTrain_dept_id(orga_id.toString());
		if (role_id == 20) {
			trainPlan.setTeacher_auth_id(auth_id);
		}
		trainPlan.setOrderCondition("tp.id asc");
		//阐述人--正在本科室轮转的学生
		trainPlan.setQueryCondition("and tp.train_status in ("+Consts.ProcessInfo.TRAINPROCESSInAll+")");
		List<TrainPlan> exponents = trainPlanService.selectDiscussantInfo(trainPlan);
		map.put("exponents", exponents);
		//在本科室轮转过的学生
		trainPlan.setTrain_status(58);
		List<TrainPlan> discussants = trainPlanService.selectDiscussantInfo(trainPlan);
		map.put("discussants", discussants);
		map.put("orga_name", orga_name);
		return map;
	}
	
	/**
	 * 发布病例讨论
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月4日
	 */
	@ResponseBody
	@RequestMapping(value="/releaseMedicalCaseDiscuss")
	public Object releaseMedicalCaseDiscuss(HttpServletRequest req) {
		return medicalCaseMainService.releaseMedicalCaseDiscuss(req);
	}
	
	/**
	 * 发表评论
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/submitDiscuss")
	public Object submitDiscuss(HttpServletRequest req) {
		return medicalCaseMainService.submitDiscuss(req);
	}
	
	/**
	 * 查询病例讨论列表
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/selectMedicalCaseDiscuss")
	public Object selectMedicalCaseDiscuss(@Param("rows") int rows, @Param("page") int page, HttpServletRequest req){
		String json ="";
		//获取页面的参数
		String state= req.getParameter("state");
		String p_pid= req.getParameter("p_pid");
		String search_start_time= req.getParameter("search_start_time");
		String search_end_time= req.getParameter("search_end_time");
		String status= req.getParameter("status");
		//
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer creator_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		MedicalCaseMain medicalCaseMain = new MedicalCaseMain();
		medicalCaseMain.setCreator_auth_id(creator_auth_id);
		medicalCaseMain.setState(state);
		if (StringUtil.isNotEmpty(search_start_time)) {
			medicalCaseMain.setSearch_start_time(search_start_time+" 00:00:00");
		}
		if (StringUtil.isNotEmpty(search_end_time)) {
			medicalCaseMain.setSearch_end_time(search_end_time+" 23:59:59");
		}
		medicalCaseMain.setStatus(status);
		medicalCaseMain.setOrderCondition("mcm.create_time desc");
		json = medicalCaseMainService.selectMedicalCaseDiscuss(page, rows, medicalCaseMain).toString();
		return StringUtil.dnull(json);
	}
	
	/**
	 * 获取阐述人和评论人
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/getDiscussants")
	public Object getDiscussants(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		//前台数据
		String id = req.getParameter("id");
		MedicalCaseMain medicalCaseMain = new MedicalCaseMain();
		if (id != null && !id.equals("")) {
			medicalCaseMain.setId(Integer.parseInt(id));
		}
		medicalCaseMain.setOrderCondition("t2.id asc");
		List<MedicalCaseMain> medicalCaseMainList = medicalCaseMainService.getDiscussants(medicalCaseMain);
		map.put("discussants", medicalCaseMainList);
		return map;
	}
	
	/**
	 * 讨论记录流程显示
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月5日
	 */
	@ResponseBody
	@RequestMapping(value="/getDiscussRecords")
	public Object getDiscussRecords(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		//前台数据
		String id = req.getParameter("id");
		String auth_id = req.getParameter("auth_id");
		MedicalCaseMain medicalCaseMain = new MedicalCaseMain();
		if (id != null && !id.equals("")) {
			medicalCaseMain.setId(Integer.parseInt(id));
		}
		if (auth_id != null && !auth_id.equals("")) {
			medicalCaseMain.setDiscuss_auth_id(Integer.parseInt(auth_id));
		}
		medicalCaseMain.setOrderCondition("mcd.discuss_time asc");
		List<MedicalCaseMain> medicalCaseMainList = medicalCaseMainService.getDiscussRecords(medicalCaseMain);
		map.put("discussRecords", medicalCaseMainList);
		return map;
	}
	
	/**
	 * 
	 * 获取发布作业页面左侧学生列表
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/getStuWithTask")
	public Object getStuWithTask(HttpServletRequest req){
        return taskDealService.getStuWithTask(req);
	}
	
	/**
	 * 
	 * 老师批改页面点击学生显示作业详情
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/getStuTaskById")
	public Object getStuTaskById(HttpServletRequest req){
		return taskDealService.getStuTaskById(req);
	}
	
	/**
	 * 
	 * 保存老师评价的内容和分数
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/saveScoTeach")
	public Object saveScoTeach(HttpServletRequest req){
		return scoEvalService.saveScoTeach(req);
	}
	
	/**
	 * 提交病例讨论老师对学生评价
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月6日
	 */
	@ResponseBody
	@RequestMapping(value="/submitGrade")
	public Object submitGrade(HttpServletRequest req) {
		return scoEvalService.submitGrade(req);
	}
	
	/**
	 * 发起销假申请
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月2日
	 */
	@ResponseBody
	@RequestMapping(value="/reportBackAfteLeave")
	public Object reportBackAfteLeave(HttpServletRequest req) {
		return stuVacateInfoService.reportBackAfteLeave(req);
	}
	
	/**
	 * 销假申请审核
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月2日
	 */
	@ResponseBody
	@RequestMapping(value="/checkForReportBack")
	public Object checkForReportBack(HttpServletRequest req) {
		return stuVacateInfoService.checkForReportBack(req);
	}
	
	/***
	 * 教学秘书--入科报到（给学生办理入科）
	 * @param req
	 * @return
	 * @author yuanb
	 * @date 2017年3月17日
	 */
	@ResponseBody
	@RequestMapping(value="/intoOrga")
	public Object intoOrga(HttpServletRequest req){
		boolean bol = trainPlanService.intoOrga(req);
		if(bol){
			return StringUtil.returns(true, "操作成功！");
		}else{
			return StringUtil.returns(false, "操作失败...");
		}
	}
	
	/**
	 *获取科室
	 */
	@ResponseBody
	@RequestMapping(value="/getOrgaInfo")
	public Object getOrgaInfo(HttpServletRequest req){
		String stu_auth_id=req.getParameter("stu_auth_id");
		TrainPlanConfig trainPlanConfig = new TrainPlanConfig();
		List<TrainPlanConfig> trainPlanConfigs = new ArrayList<TrainPlanConfig>();
		if(StringUtil.isNotEmpty(stu_auth_id)){
			trainPlanConfig.setAuth_id(Integer.parseInt(stu_auth_id));
			trainPlanConfig.setState("Y");
			trainPlanConfigs=trainPlanConfigDao.selectOrgaInfo(trainPlanConfig);
		}
		
		return trainPlanConfigs;
	}
	
	/**
	 * 获取当前科室带入科学生
	 * @param req
	 * @return
	 * @author zhengc
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value="/getTrainStudent")
	public Object getTrainStudent(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req) throws Exception{
		String json ="";
		/**
		 * 从缓存中拿到当前科室
		 */
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();

		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setTrain_dept_id(orga_id.toString()); // 当前的轮转科室
		trainPlan.setTrain_status(52); // 轮转状态是 入科报到的
		trainPlan.setState("Y");
		json=trainPlanService.getTrainStudent(page, rows, trainPlan,req).toString();
		return StringUtil.dnull(json);
	}
	
	@ResponseBody
	@RequestMapping(value="/getOrgaTea")
	public Object getOrgaTea(HttpServletRequest req){
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		UserInfo info =new UserInfo();
		info.setState("Y");
		info.setRole_id(20);
		info.setOrga_id(orga_id);
		List<UserInfo> list =userInfoService.selectTeaList(info);
		return list;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getOrga")
	public Object getOrga(HttpServletRequest req){
		OrgaInfo orgaInfo=new OrgaInfo();
		orgaInfo.setState("Y");
		orgaInfo.setQueryCondition("order by orga_id asc");
		List<OrgaInfo> list =orgaInfoService.selectList(orgaInfo);
		return list;
	}
	
	
}