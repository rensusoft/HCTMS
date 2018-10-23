package com.rensu.education.hctms.score.controller;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rensu.education.hctms.basicdata.bean.ExamineTypeContent;
import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.TfcStutypeformRela;
import com.rensu.education.hctms.basicdata.service.ExamineTypeContentService;
import com.rensu.education.hctms.basicdata.service.FormInfoService;
import com.rensu.education.hctms.basicdata.service.TfcStutypeformRelaService;
import com.rensu.education.hctms.config.bean.SysConfig;
import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.service.TrainTeachOrderService;
import com.rensu.education.hctms.core.utils.SystemConfigInitListener;
import com.rensu.education.hctms.log.bean.OutdeptRecord;
import com.rensu.education.hctms.log.service.OutdeptRecordService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.score.bean.ScoFormDetail;
import com.rensu.education.hctms.score.bean.ScoFormMain;
import com.rensu.education.hctms.score.bean.ScoFormSub;
import com.rensu.education.hctms.score.bean.StuSco;
import com.rensu.education.hctms.score.service.ScoFormDetailService;
import com.rensu.education.hctms.score.service.ScoFormMainService;
import com.rensu.education.hctms.score.service.ScoFormSubService;
import com.rensu.education.hctms.score.service.StuScoService;
import com.rensu.education.hctms.teach.bean.StuTeachOrder;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.service.StuTeachOrderService;
import com.rensu.education.hctms.teach.service.TrainPlanService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;
import com.rensu.education.hctms.util.StringUtil;
 
@Controller
@RequestMapping(value="/scoreweb")
public class ScoreWebController{
	
	Logger log = Logger.getLogger(ScoreWebController.class);
	
	@Autowired
	protected ScoFormDetailService scoFormDetailService;
	@Autowired
	protected TrainPlanService trainPlanService;
	@Autowired
	protected StuTeachOrderService stuTeachOrderService; 
	@Autowired
	protected TfcStutypeformRelaService tfcStutypeformRelaService;
	@Autowired
	protected ScoFormMainService scoFormMainService; 
	@Autowired
	protected FormInfoService formInfoService; 

	@Autowired
	protected ScoFormSubService scoFormSubService;
	@Autowired
	protected OutdeptRecordService outdeptRecordService; 
	@Autowired
	protected StuScoService stuScoService;
	@Autowired
	protected TrainTeachOrderService trainTeachOrderService;
	@Autowired
	private SystemConfigInitListener systemConfigInitListener;
	@Autowired
	private ExamineTypeContentService examineTypeContentService;
	/**
	 * 得到出科审核的界面(老师，秘书，科教科)
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月31日
	 */
	@ResponseBody
	@RequestMapping(value="/getGradeInit")
	public Object getGradeInit(HttpServletRequest req){
		List<TrainPlan> lists=trainPlanService.getGradeInit(req);
		return StringUtil.returns(true, lists);
	}
	
	/**
	 * 根据学生的id   得到相应学生出科的相应信息
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年4月6日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuInfor")
	public Object getStuInfor(HttpServletRequest req ){
		Map<String, Object>  map=new HashMap<String, Object>();
		map=tfcStutypeformRelaService.getStuInfor(req);
		return map;
	}

	
	/***
	 * 根据学生是否已发起出科审核、出科前多少天学生可以发起出科审核的系统配置出科审核部分展现不同的页面
	 * @param req
	 * @param res
	 * @return
	 * @author guocc
	 * @date 2017年04月24日
	 */
	@ResponseBody
	@RequestMapping(value="/graduateCheckApplicationPage")
	public ModelAndView graduateCheckApplicationPage(HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		//前台取值
		String flagApplyAgain = req.getParameter("flagApplyAgain");
		//从session中获取当前用户和科室
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		int flag = 0;//  页面显示什么内容标识（-1：一句话：未到出科审核的日期； 1：展示整个页面； 2：页面左侧一句话：正在出科审核中...）
		int flagOfBtn = -1;//  【重新发起出科】按钮显示标识
		int applyAgainFlag = -1;//  重新发起出科标识
		if (flagApplyAgain != null && flagApplyAgain.equals("yes")) {//  重新发起出科
			//删除无效表单数据
			ScoFormMain scoFormMain = new ScoFormMain();
			scoFormMain.setState("X");
			scoFormMain.setUser_auth_id(stu_auth_id);
			scoFormMain.setOrga_id(orga_id);
			scoFormMain.setCreate_auth_id(stu_auth_id);
			scoFormMainService.updateState(scoFormMain);
			//
			flag = 1;
			applyAgainFlag = 1;
			mv.setViewName("web/score/graduateCheckApplication");
			mv.getModel().put("flagOfBtn", flagOfBtn);
			mv.addObject("flag",flag);
			mv.addObject("applyAgainFlag",applyAgainFlag);
			return mv;
		}
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setStu_auth_id(stu_auth_id);
		String train_dept_id = orga_id.toString();
		trainPlan.setTrain_dept_id(train_dept_id);
		trainPlan.setState("Y");
		Date train_end_time = trainPlanService.selectList(trainPlan).get(0).getTrain_end_time();
		int daysAwayFromTrainEndTime = 0;//  今天距离出科的日期
		try {
			daysAwayFromTrainEndTime = DateUtil.daysBetween(new Date(), train_end_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//得到系统配置的密码 （从系统内存中拿）
		@SuppressWarnings("unchecked")
		List<SysConfig> sysConfigList = (List<SysConfig>)req.getSession().getServletContext().getAttribute(Consts.SESSION_SYS_CONFIG);
		for(SysConfig sysConfig : sysConfigList){
			if(sysConfig.getConfig_code() != null && sysConfig.getConfig_code().equals("days_awayFrom_graduateCheckApplication")){
				if(sysConfig.getConfig_flag() == 1){//  开启了配置
					int days = Integer.parseInt(sysConfig.getConfig_data());//  系统配置的日期
					if(daysAwayFromTrainEndTime < days){//  到了可以提出出科审核的日期
						OutdeptRecord outdeptRecord = new OutdeptRecord();
						outdeptRecord.setStu_auth_id(stu_auth_id);
						outdeptRecord.setOrga_id(orga_id);
						outdeptRecord.setState("Y");
						List<OutdeptRecord> outdeptRecordList = outdeptRecordService.selectList(outdeptRecord);
						if (outdeptRecordList != null && !outdeptRecordList.isEmpty()) {
							flag = 2;
							OutdeptRecord outdeptRecordGet = outdeptRecordService.selectExamResult(outdeptRecord);//  查询学生在本科室的最后一条出科审批记录是否为重新发起审核状态0
							if (outdeptRecordGet.getExam_result() != null && outdeptRecordGet.getExam_result() == -1) {
								flagOfBtn = 1;
							}
						}else {//  没有审核记录，未提出审核
							flag = 1;
						}
					}else{
						flag = -1;
					}
				}else if(sysConfig.getConfig_flag() == -1){
					OutdeptRecord outdeptRecord = new OutdeptRecord();
					outdeptRecord.setStu_auth_id(stu_auth_id);
					outdeptRecord.setOrga_id(orga_id);
					outdeptRecord.setState("Y");
					List<OutdeptRecord> outdeptRecordList = outdeptRecordService.selectList(outdeptRecord);
					if (outdeptRecordList != null && !outdeptRecordList.isEmpty()) {
						flag = 2;
						OutdeptRecord outdeptRecordGet = outdeptRecordService.selectExamResult(outdeptRecord);
						if (outdeptRecordGet.getExam_result() != null && outdeptRecordGet.getExam_result() == -1) {
							flagOfBtn = 1;
						}
					}else {//  没有审核记录，未提出审核
						flag = 1;
					}
				}
			}
		}
		mv.setViewName("web/score/graduateCheckApplication");
		mv.getModel().put("flagOfBtn", flagOfBtn);
		mv.addObject("flag",flag);
		mv.addObject("applyAgainFlag",applyAgainFlag);
		return mv;
	}
	
	/**
	 * 得到出科审核页面的个人的基本信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月24日
	 */
	@ResponseBody
	@RequestMapping(value="/getBasicInformation")
	public Object getBasicInformation(HttpServletRequest req ){
		//从session中获取当前用户、科室和所属附属医院id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		String train_dept_id = orga_id.toString();
		
		
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setStu_auth_id(stu_auth_id);
		trainPlan.setTrain_dept_id(train_dept_id);
		trainPlan.setQueryCondition("and tp.train_status in(53,54,55,56,57)");
		TrainPlan trainPlanGet = trainPlanService.getBasicInformation(trainPlan);
		int daysAwayFromTrainEndTime = 0;//  今天距离出科的日期
		try {
			daysAwayFromTrainEndTime = DateUtil.daysBetween(new Date(), trainPlanGet.getTrain_end_time()) < 0 ? 0 : DateUtil.daysBetween(new Date(), trainPlanGet.getTrain_end_time());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trainPlanGet.setDaysAwayFromTrainEndTime(daysAwayFromTrainEndTime);
		//计算完成率
		int i = 0;
		TrainPlan tp = new TrainPlan();
		tp.setStu_auth_id(stu_auth_id);
		tp.setTrain_dept_id(orga_id.toString());
		tp.setQueryCondition("and train_status in(53,54,55,56,57)");
		tp.setState("Y");
		List<TrainPlan> tpList=trainPlanService.selectList(tp);
		TrainTeachOrder tto = new TrainTeachOrder();
		if (tpList != null && !tpList.isEmpty()) {
			tto.setTpc_id(tpList.get(0).getTpc_id());
		}
		List<TrainTeachOrder> trainTeachOrderList = trainTeachOrderService.selectList(tto);
		for (TrainTeachOrder trainTeachOrder : trainTeachOrderList) {
			if(trainTeachOrder.getOrder_num()!=null){
				i++;
			}
		}
		StuTeachOrder stuTeachOrder = new StuTeachOrder();
		stuTeachOrder.setDept_id(orga_id);
		stuTeachOrder.setStu_auth_id(stu_auth_id);
		List<StuTeachOrder>  stuTeachOrderList=  stuTeachOrderService.selectList(stuTeachOrder);
		float completion_sum = 0;
		//计算完成率
		for (StuTeachOrder _menu : stuTeachOrderList) {
			//计算完成率
			if(_menu.getOrder_num()!=null&&_menu.getOrder_num()!=0){
				float completion_rate1=(float)_menu.getFinnish_num()/(float)_menu.getOrder_num();
				if(completion_rate1<=1){
					completion_sum=completion_sum+completion_rate1;
				}else{
					completion_sum=completion_sum+1;
				}
			}
		}
		int num =(int)(completion_sum/i*100);
		if(num>100){
			trainPlanGet.setCompletion_rate(100);
		}else{
			trainPlanGet.setCompletion_rate(num);
		}
		return StringUtil.returns(true, trainPlanGet);
	}  
	
	/**
	 * 得到出科审核页面的表单配置列表
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月25日
	 */
	@ResponseBody
	@RequestMapping(value="/getFormList")
	public Object getFormList(HttpServletRequest req ){
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		String s_stu_type_id = req.getParameter("s_stu_type_id");
		String flag_teacher = req.getParameter("flag_teacher");
		String applyAgainFlag = req.getParameter("applyAgainFlag");
		//从session中获取当前用户角色id、类型等
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		String role_id = loginBean.getvUserDetailInfo().getRole_id().toString();
		Integer stu_type_id = loginBean.getvUserDetailInfo().getStu_type_id();
		Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		//根据配置查询表单列表
		TfcStutypeformRela tfcStutypeformRela = new TfcStutypeformRela();
		tfcStutypeformRela.setRole_id(role_id);
		if (s_orga_id != null && !s_orga_id.equals("")) {
			tfcStutypeformRela.setOrga_id(Integer.parseInt(s_orga_id));
		}
		if (flag_teacher != null && flag_teacher.equals("1")) {//  非学生角色从前台取值
			if (s_stu_type_id != null && !s_stu_type_id.equals("")) {
				tfcStutypeformRela.setStu_type_id(Integer.parseInt(s_stu_type_id));
			}
		}else{//  学生角色时
			tfcStutypeformRela.setStu_type_id(stu_type_id);
		}
		List<TfcStutypeformRela> tfcStutypeformRelaList = tfcStutypeformRelaService.getFormList(tfcStutypeformRela);		
		//查询当前组次
		OutdeptRecord outdeptRecord = new OutdeptRecord();
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			outdeptRecord.setStu_auth_id(Integer.parseInt(s_user_auth_id));
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			outdeptRecord.setOrga_id(Integer.parseInt(s_orga_id));
		}
		outdeptRecord.setOrderCondition("pub_num desc");
		outdeptRecord.setState("Y");
		List<OutdeptRecord> orList = outdeptRecordService.selectList(outdeptRecord);
		int pub_num = 0;//  当前组次
		if (orList != null && !orList.isEmpty()) {
			pub_num = orList.get(0).getPub_num();
		}
		//填写的表单是否为当前组次的表单
		ScoFormMain scoFormMain = new ScoFormMain();
		scoFormMain.setCreate_auth_id(create_auth_id);
		scoFormMain.setState("Y");
		scoFormMain.setType_id(2);//  出科审核标识
		scoFormMain.setOrderCondition("id desc");
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			scoFormMain.setUser_auth_id(Integer.parseInt(s_user_auth_id));
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			scoFormMain.setOrga_id(Integer.parseInt(s_orga_id));
		}
		for (TfcStutypeformRela tfcStutypeformRelaGet : tfcStutypeformRelaList) {
			int flag = -1;//  本组次中是否被编辑过的标识（1：已填写        -1：未填写）
			if (tfcStutypeformRelaGet.getForm_id() != null) {
				scoFormMain.setForm_id(tfcStutypeformRelaGet.getForm_id());
			}
			List<ScoFormMain> scoFormMainList = scoFormMainService.selectList(scoFormMain);
			for (ScoFormMain sFormMain : scoFormMainList) {
				if (sFormMain.getOr_id() != null) {
					if (pub_num == outdeptRecordService.selectOne(sFormMain.getOr_id()).getPub_num()) {//  填写的表单是否为当前组次的表单
						if (!"1".equals(applyAgainFlag)) {//  不是重新发起出科审核
							flag = 1;//  编辑过
							tfcStutypeformRelaGet.setSfm_id(sFormMain.getId());
							break;
						}
					}
				}else{
					flag = 1;//  编辑过
					tfcStutypeformRelaGet.setSfm_id(sFormMain.getId());
					break;
				}
			}
			tfcStutypeformRelaGet.setFlag(flag);
		}
		return StringUtil.returns(true, tfcStutypeformRelaList);
	}
	
	/**
	 * 从缓存里读数据，初始化出科审核流程图
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月8日
	 */
	@ResponseBody
	@RequestMapping(value="/getflowChartInfo")
	public Object getflowChartInfo(HttpServletRequest req){
		String pub_num = req.getParameter("pub_num");
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		OutdeptRecord outdeptRecord = new OutdeptRecord();	
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			outdeptRecord.setStu_auth_id(Integer.parseInt(s_user_auth_id));
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			outdeptRecord.setOrga_id(Integer.parseInt(s_orga_id));
		}
		if (pub_num != null && !pub_num.equals("")) {
			outdeptRecord.setPub_num(Integer.parseInt(pub_num));
		}
		outdeptRecord.setState("Y");
		outdeptRecord.setOrderCondition("id asc");
		List<OutdeptRecord> outdeptRecordList = outdeptRecordService.selectList(outdeptRecord);
		List<OutdeptRecord> orList = new ArrayList<OutdeptRecord>();
		for (OutdeptRecord or : outdeptRecordList) {
			if (or.getExam_result() != null && or.getExam_result() == -1) {
				orList.add(or);
				break;
			}
			orList.add(or);
		}
		return StringUtil.returns(true, orList);
//		Map<String, Object> map=new HashMap<String, Object>();
//		int flag = -1;//  驳回标识（-1：没驳回 / 1：驳回）
//		//更新缓存数据
//		systemConfigInitListener.setServletContext(req.getSession().getServletContext());
//		@SuppressWarnings("unchecked")
//		List<ProcessInfo> processList = (List<ProcessInfo>)req.getSession().getServletContext().getAttribute(Consts.SESSION_PROCESS_INFO);
//		List<ProcessInfo> processSelList = new ArrayList<ProcessInfo>();
//		if (outdeptRecordList != null && !outdeptRecordList.isEmpty()) {//  记录不为空
//			for (OutdeptRecord ord : outdeptRecordList) {
//				if (ord.getExam_result() != null && (ord.getExam_result() == 0 || ord.getExam_result() == -1)) {
//							//写的太死！！！
//					if (ord.getExam_role_id() != null && (ord.getExam_role_id() == 20 || ord.getExam_role_id() == 30)) {
//						for (ProcessInfo processInfo : processList) {
//							if(processInfo.getType_code().equals("TRAINPROCESS") && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESSNull && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS0
//									&& processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS1 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS6
//									&& processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS4 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS5){
//								processSelList.add(processInfo);
//							}
//						}
//					}else if (ord.getExam_role_id() != null && ord.getExam_role_id() == 40) {
//						for (ProcessInfo processInfo : processList) {
//							if(processInfo.getType_code().equals("TRAINPROCESS") && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESSNull && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS0
//									&& processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS1 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS6
//									&& processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS5){
//								processSelList.add(processInfo);
//							}
//						}
//					}else if (ord.getExam_role_id() != null && ord.getExam_role_id() == 50) {
//						for (ProcessInfo processInfo : processList) {
//							if(processInfo.getType_code().equals("TRAINPROCESS") && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESSNull && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS0
//									&& processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS1 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS6){
//								processSelList.add(processInfo);
//							}
//						}
//					}
//					flag = 1;
//					Collections.sort(processSelList, new Comparator<ProcessInfo>() {
//					          public int compare(ProcessInfo arg0, ProcessInfo arg1) {
//						          Integer code0 = arg0.getProc_num();
//						          Integer code1 = arg1.getProc_num();
//						          return code0.compareTo(code1);
//					          }
//				    });
//					map.put("processSelList", processSelList);
//					map.put("flag", flag);
//					return map;
//				}
//			}
//			for (ProcessInfo processInfo : processList) {
//				if(processInfo.getType_code().equals("TRAINPROCESS") && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESSNull && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS0
//						&& processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS1 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS6){
//					for (OutdeptRecord outdeptR : outdeptRecordList) {
//						if (outdeptR.getExam_role_id() != null && outdeptR.getExam_role_id() == 10) {
//							if(processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS2){
//								processInfo.setFlag_color(1);
//							}
//						}else if (outdeptR.getExam_role_id() != null && (outdeptR.getExam_role_id() == 20 || outdeptR.getExam_role_id() == 30)) {
//							if(processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS3){
//								processInfo.setFlag_color(1);
//							}
//						}else if (outdeptR.getExam_role_id() != null && outdeptR.getExam_role_id() == 40) {
//							if(processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS4){
//								processInfo.setFlag_color(1);
//							}
//						}else if (outdeptR.getExam_role_id() != null && outdeptR.getExam_role_id() == 50) {
//							if(processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS5){
//								processInfo.setFlag_color(1);
//							}
//						}
//					}
//					processSelList.add(processInfo);
//				}
//			}
//		}else{
//			for (ProcessInfo processInfo : processList) {
//				if(processInfo.getType_code().equals("TRAINPROCESS") && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESSNull && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS0
//						&& processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS1 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS6){
//					processSelList.add(processInfo);
//				}
//			}
//		}
//		Collections.sort(processSelList, new Comparator<ProcessInfo>() {
//            public int compare(ProcessInfo arg0, ProcessInfo arg1) {
//                Integer code0 = arg0.getProc_num();
//                Integer code1 = arg1.getProc_num();
//                return code0.compareTo(code1);
//            }
//        });
//		map.put("processSelList", processSelList);
//		map.put("flag", flag);
//		return map;
	}  
	
	/**
	 * 出科审核流程初始化
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月25日
	 */
	@ResponseBody
	@RequestMapping(value="/getProcessInfor")
	public Object getProcessInfor(HttpServletRequest req ) {
		Map<String, Object>  map=new HashMap<String, Object>();
		map=tfcStutypeformRelaService.getProcessInfor(req);
		return map;
	}

	/**
	 * 学生出科审核模块  判断表单类型（评分表单/普通表单）
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月27日
	 */
	@ResponseBody
	@RequestMapping(value="/getStuFormType")
	public Object getStuFormType(HttpServletRequest req ) {
		//
		String form_id = req.getParameter("form_id");
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		String pub_num = req.getParameter("pub_num");
		String create_auth_id_str = req.getParameter("create_auth_id");
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		FormInfo formInfo = new FormInfo();
		if (form_id != null && !form_id.equals("")) {
			formInfo = formInfoService.selectOne(Integer.parseInt(form_id));
		}
		//
		if (create_auth_id_str != null && create_auth_id_str.equals("-100")) {//  表单列表处触发请求
			ScoFormMain scoFormMain = new ScoFormMain();
			scoFormMain.setCreate_auth_id(create_auth_id);
			scoFormMain.setState("Y");
			scoFormMain.setType_id(2);//  出科审核标识
			if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
				scoFormMain.setUser_auth_id(Integer.parseInt(s_user_auth_id));//  老师复用，要从前台取数据
			}
			if (s_orga_id != null && !s_orga_id.equals("")) {
				scoFormMain.setOrga_id(Integer.parseInt(s_orga_id));//  老师复用，要从前台取数据
			}
			if (form_id != null && !form_id.equals("")) {
				scoFormMain.setForm_id(Integer.parseInt(form_id));
			}
			List<ScoFormMain> scoFMList = scoFormMainService.selectList(scoFormMain);
			for (ScoFormMain scoFM : scoFMList) {
				if (scoFM.getOr_id() == null) {
					formInfo.setFlag(1);//  已经填写
					formInfo.setSfm_id(scoFM.getId().toString());
					return StringUtil.returns(true,formInfo);
				}
			}
		}
		//
		OutdeptRecord outdeptRecord = new OutdeptRecord();
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			outdeptRecord.setStu_auth_id(Integer.parseInt(s_user_auth_id));
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			outdeptRecord.setOrga_id(Integer.parseInt(s_orga_id));
		}
		if (pub_num != null && !pub_num.equals("")) {
			outdeptRecord.setPub_num(Integer.parseInt(pub_num));
		}
		List<ScoFormMain> scoFormMainList = scoFormMainService.getOrIdByPubNum(outdeptRecord);
		ScoFormMain sFM = new ScoFormMain();
		if (create_auth_id_str != null && create_auth_id_str.equals("-100")) {//  列表处触发请求
			sFM.setCreate_auth_id(create_auth_id);
		}else if(create_auth_id_str != null && !create_auth_id_str.equals("-100")) {//  审核流程记录处触发请求
			sFM.setCreate_auth_id(Integer.parseInt(create_auth_id_str));
		}
//		sFM.setCreate_auth_id(create_auth_id);
		sFM.setState("Y");
		sFM.setType_id(2);//  出科审核标识
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			sFM.setUser_auth_id(Integer.parseInt(s_user_auth_id));
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			sFM.setOrga_id(Integer.parseInt(s_orga_id));
		}
		if (form_id != null && !form_id.equals("")) {
			sFM.setForm_id(Integer.parseInt(form_id));
		}
		Integer sfm_id = 0;
		int flag = -1;//  未编辑过
		for (ScoFormMain scoFormMainGet : scoFormMainList) {
			sFM.setOr_id(scoFormMainGet.getOr_id());
			ScoFormMain scoFM = scoFormMainService.selectByOrId(sFM);
			if (scoFM != null) {
				flag = 1;
				sfm_id = scoFM.getId();
				break;
			}
		}
		formInfo.setFlag(flag);
		formInfo.setSfm_id(sfm_id.toString());
		return StringUtil.returns(true,formInfo);
	}

	/**
	 * 普通表单的内容展示
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月28日
	 */
	@ResponseBody
	@RequestMapping(value="/showHtmlForm")
	public Object showHtmlForm(HttpServletRequest req) {
		return scoFormMainService.showHtmlForm(req);
	}  
	/**
	 * 出科审核   出科
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月24日
	 */
	@ResponseBody
	@RequestMapping(value="/applyForGraduateCheck")
	public Object applyForGraduateCheck(HttpServletRequest req ){
		return scoFormDetailService.applyForGraduateCheck(req);
	}
	/**
	 * 从数据表（相对于配置表）里取数据，初始化评分表单评分项列表
	 * @param req
	 * @param res
	 * @return
	 * @author guocc
	 * @date 2017年5月4日
	 */
	@ResponseBody
	@RequestMapping(value="/queryScoFormSubList")
	public Object queryScoFormSubList(HttpServletRequest req, HttpServletResponse res){
		String form_id = req.getParameter("form_id");
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		String create_auth_id_str = req.getParameter("create_auth_id");
		String sfm_id_str = req.getParameter("sfm_id");
		ScoFormMain scoFormMain = new ScoFormMain();
		if (create_auth_id_str != null && create_auth_id_str.equals("-100")) {//  列表处触发请求
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
			scoFormMain.setCreate_auth_id(create_auth_id);
		}else if(create_auth_id_str != null && !create_auth_id_str.equals("-100")) {//  审核流程记录处触发请求
			scoFormMain.setCreate_auth_id(Integer.parseInt(create_auth_id_str));
		}
		if (form_id != null && !form_id.equals("")) {
			scoFormMain.setForm_id(Integer.parseInt(form_id));
		}
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			scoFormMain.setUser_auth_id(Integer.parseInt(s_user_auth_id));//  老师复用，要从前台取数据
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			scoFormMain.setOrga_id(Integer.parseInt(s_orga_id));//  老师复用，要从前台取数据
		}
		Integer sfm_id = 0;
		if (sfm_id_str != null && !sfm_id_str.equals("")) {
			sfm_id = Integer.parseInt(sfm_id_str);
		}
//		Integer sfm_id = scoFormMainService.querySFMId(scoFormMain).getId();
		ScoFormSub scoFormSub = new ScoFormSub();
		scoFormSub.setSfm_id(sfm_id);
		scoFormSub.setState("Y");
		scoFormSub.setOrderCondition(" id,sort_num");
		List<ScoFormSub> sublist = scoFormSubService.selectList(scoFormSub);
		JSONObject jobj = new JSONObject();
		jobj.put("rows", sublist);
		return StringUtil.dnull(jobj.toString());
	}   
	/**
	 * 从数据表（相对于配置表）里取数据，初始化表单各项的打分方式及分数
	 * @param req
	 * @param res
	 * @return
	 * @author guocc
	 * @date 2017年5月4日
	 */
	@ResponseBody
	@RequestMapping(value="/queryScoFormDetailList")
	public Object queryScoFormDetailList(HttpServletRequest req, HttpServletResponse res){
		String sfs_id = req.getParameter("sfs_id");
		JSONObject jobj = new JSONObject();
		if(sfs_id!=null&&!sfs_id.equals("")){
			ScoFormDetail scoFormDetail = new ScoFormDetail();
			scoFormDetail.setSfs_id(Integer.parseInt(sfs_id));
			scoFormDetail.setState("Y");
			scoFormDetail.setOrderCondition(" sco_num desc");
			List<ScoFormDetail> list = scoFormDetailService.selectList(scoFormDetail);
			jobj.put("rows", list);
		}
		return StringUtil.dnull(jobj.toString());
	}

	/**
	 * 从数据表（相对于配置表）里取数据，初始化表单各项的打分方式及分数
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月4日
	 */
	@ResponseBody
	@RequestMapping(value="/queryMarksheetData")
	public Object queryMarksheetData(HttpServletRequest req){
		String form_id = req.getParameter("form_id");
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		String create_auth_id_str = req.getParameter("create_auth_id");
		String sfm_id_str = req.getParameter("sfm_id");
		ScoFormMain scoFormMain = new ScoFormMain();
		if (create_auth_id_str != null && create_auth_id_str.equals("-100")) {//  列表处触发请求
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
			scoFormMain.setCreate_auth_id(create_auth_id);
		}else if(create_auth_id_str != null && !create_auth_id_str.equals("-100")) {//  审核流程记录处触发请求
			scoFormMain.setCreate_auth_id(Integer.parseInt(create_auth_id_str));
		}
		if (form_id != null && !form_id.equals("")) {
			scoFormMain.setForm_id(Integer.parseInt(form_id));
		}
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			scoFormMain.setUser_auth_id(Integer.parseInt(s_user_auth_id));//  老师复用，要从前台取数据
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			scoFormMain.setOrga_id(Integer.parseInt(s_orga_id));//  老师复用，要从前台取数据
		}
		Integer sfm_id = 0;
		if (sfm_id_str != null && !sfm_id_str.equals("")) {
			sfm_id = Integer.parseInt(sfm_id_str);
		}
//		Integer sfm_id = scoFormMainService.querySFMId(scoFormMain).getId();
		ScoFormSub scoFormSub = new ScoFormSub();
		scoFormSub.setSfm_id(sfm_id);
		scoFormSub.setState("Y");
		List<ScoFormSub> sublist = scoFormSubService.selectList(scoFormSub);
		return StringUtil.returns(true, sublist);
	}  
	/**
	 * 表单配置  提交表单内容
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月27日
	 */
	@ResponseBody
	@RequestMapping(value="/stuIndept")
	public Object stuIndept(HttpServletRequest req){
		boolean bol = scoFormMainService.stuIndept(req);
		if(bol){
			return StringUtil.returns(true, "操作成功！");
		}else{
			return StringUtil.returns(false, "操作失败...");
		}
	}  
	/**
	 * 获取表单配置的表格内容
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月27日
	 */
	@ResponseBody
	@RequestMapping(value="/getTableContent")
	public Object getTableContent(HttpServletRequest req) {
		//获取页面参数
		String form_id = req.getParameter("form_id");
		String flag = req.getParameter("flag");//  表单是否编辑过标识
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		String create_auth_id_str = req.getParameter("create_auth_id");
		String sfm_id = req.getParameter("sfm_id");
		FormInfo formInfoGet = new FormInfo();
		if (flag != null && flag.equals("1")) {//  已经编辑了
			ScoFormMain scoFormMain = new ScoFormMain();
			if (create_auth_id_str != null && create_auth_id_str.equals("-100")) {//  列表处触发请求
				LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
				Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
				scoFormMain.setCreate_auth_id(create_auth_id);
			}else if(create_auth_id_str != null && !create_auth_id_str.equals("-100")) {//  审核流程记录处触发请求
				scoFormMain.setCreate_auth_id(Integer.parseInt(create_auth_id_str));
			}
			if (form_id != null && !form_id.equals("")) {
				scoFormMain.setForm_id(Integer.parseInt(form_id));
			}
//			scoFormMain.setForm_name(t_name);//  根据form_name查是有问题的！！！
			if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
				scoFormMain.setUser_auth_id(Integer.parseInt(s_user_auth_id));//  老师复用，要从前台取数据
			}
			if (s_orga_id != null && !s_orga_id.equals("")) {
				scoFormMain.setOrga_id(Integer.parseInt(s_orga_id));//  老师复用，要从前台取数据
			}
			if (sfm_id != null && !sfm_id.equals("")) {
				scoFormMain.setId(Integer.parseInt(sfm_id));
			}
			formInfoGet = formInfoService.getTableContentFromSFM(scoFormMain);
		}else if (flag != null && flag.equals("-1")) {
			FormInfo formInfo = new FormInfo();
			if (form_id != null && !form_id.equals("")) {
				formInfo.setId(Integer.parseInt(form_id));
			}
			formInfoGet = formInfoService.getTableContent(formInfo);
		}
		return StringUtil.returns(true,formInfoGet);
	} 
	/**
	 * 出科
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年4月17日
	 */
	@ResponseBody
	@RequestMapping(value="/beGrAdue")
	public Object beGrAdue(HttpServletRequest req ){
		return trainPlanService.beGrAdue(req);
	}
	
	/**
	 * 获取出科审核记录的组次
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月15日
	 */
	@ResponseBody
	@RequestMapping(value="/getGroups")
	public Object getGroups(HttpServletRequest req){
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		JSONObject jobj = new JSONObject();
		OutdeptRecord outdeptRecord = new OutdeptRecord();
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			outdeptRecord.setStu_auth_id(Integer.parseInt(s_user_auth_id));
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			outdeptRecord.setOrga_id(Integer.parseInt(s_orga_id));
		}
		List<OutdeptRecord> outdeptRecordList = outdeptRecordService.getGroups(outdeptRecord);
//		OutdeptRecord ord = new OutdeptRecord();
//		OutdeptRecord outdeptRecordGet = outdeptRecordService.selectExamResult(outdeptRecord);//  查询学生在本科室的最后一条出科审批记录是否为重新发起审核状态0
//		if (outdeptRecordGet != null && outdeptRecordGet.getExam_result() != null && outdeptRecordGet.getExam_result() == 0) {//  已经重新发起了出科审核申请
//			if (outdeptRecordGet.getPub_num() != null){
//				ord.setPub_num(outdeptRecordGet.getPub_num() + 1);
//			}
//			outdeptRecordList.add(ord);
//		}
		jobj.put("rows", outdeptRecordList);
		return StringUtil.dnull(jobj.toString());
	}
	
	/**
	 * 重新发起出科申请
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月15日
	 */
	@ResponseBody
	@RequestMapping(value="/applyForGraduateCheckAgain")
	public Object applyForGraduateCheckAgain(HttpServletRequest req){
		return outdeptRecordService.applyForGraduateCheckAgain(req);
	}  
	
	/***
	 * 导出录入学生成绩模板
	 * @author zhengc
	 * @date 2017年6月7日
	 */
	
	@ResponseBody
	@RequestMapping(value="/downStuscoInfo")
	public void downStuscoInfo(HttpServletRequest req, HttpServletResponse res) throws Exception{
		stuScoService.downStuscoInfo(req, res);
	}
	/***
	 * 上传录入学生成绩模板
	 * @author zhengc
	 * @date 2017年6月7日
	 */
	@RequestMapping(value="/uploadStoscoInfo")
	public void uploadStoscoInfo(HttpServletRequest req ,HttpServletResponse res)throws Exception
	{
		res.setContentType("text/html;chartset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload fileUpload=new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("UTF-8");
		List<FileItem> list=fileUpload.parseRequest(req);
		if(list!=null&&list.size()>0)
		{
			String fileName=list.get(0).getName();
			fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
			InputStream in=list.get(0).getInputStream();
			String msg=stuScoService.uploadStuSco(in, fileName, req);
			
			if(msg.equals("true")){
				res.getWriter().print(StringUtil.returnStr(true,"上传成功").toString());
			}else{
				res.getWriter().print(StringUtil.returnStr(false,msg).toString());
			}
			
		}
		
		
	}
	
	/**
	 * 学生成绩列表  初始化
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getStuscoList")
	public Object getStuscoList(@Param("rows") int rows, @Param("page") int page,HttpServletRequest req) {
		StuSco stuSco=new StuSco();
		String stu_name=req.getParameter("stu_name");
        stuSco.setStu_name(stu_name);
		String json="";
		json= stuScoService.selectPageWithUserInfo(page, rows,stuSco).toString();
		return StringUtil.dnull(json);
	}	
	
	/**
	 * 拼接审核不通过原因的复选框
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月1日
	 */
	@ResponseBody
	@RequestMapping(value="/organizeCheckBoxOfReason")
	public Object organizeCheckBoxOfReason(HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		ExamineTypeContent examineTypeContent = new ExamineTypeContent();
		examineTypeContent.setType_id(1);
		examineTypeContent.setState("Y");
		examineTypeContent.setOrderCondition(" order_num asc");
		List<ExamineTypeContent> examineTypeContentList = examineTypeContentService.selectList(examineTypeContent);
		jobj.put("rows", examineTypeContentList);
		return StringUtil.dnull(jobj.toString());
	} 
	
}

	
	

