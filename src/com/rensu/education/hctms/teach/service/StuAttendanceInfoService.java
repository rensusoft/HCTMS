package com.rensu.education.hctms.teach.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuAttendanceInfoDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.userauth.dao.VUserDetailInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;
import com.rensu.education.hctms.util.DesUtils;
import com.rensu.education.hctms.util.StringUtil;


@Service("stuAttendanceInfoService")
public class StuAttendanceInfoService extends BaseService<StuAttendanceInfo> {
	
	Logger log = Logger.getLogger(StuAttendanceInfoService.class);
	
	@Autowired
	protected StuAttendanceInfoDao stuAttendanceInfoDao;
	@Autowired
	protected VUserDetailInfoDao vUserDetailInfoDao;
	@Autowired
	protected TrainPlanDao trainPlanDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	
	 double attendance=0;//考勤 数量
	 double noAttendance=0;//未考勤数量
	 double leave=0;//请假
	 /**
		 * 得到科室的考勤信息
		 * @param req
		 * @param res
		 * @param userid
		 *@author huq
		 * @param orgaId 
		 * @return 
		 *@date 2017年1月16日
		 */
		public JSONObject getAttendance(HttpServletRequest req, HttpServletResponse res,
				int authId, int orgaId) {
				
				List<StuAttendanceInfo> listAll=new ArrayList<StuAttendanceInfo>();  //用来存储轮转日子里所有的数据
				
				JSONObject jobj = new JSONObject();
				attendance=0;//考勤 数量
				noAttendance=0;//未考勤数量
				leave=0;//请假
				//根据缓存中的信息得到当前的科室 
				StuAttendanceInfo stuAttendanceInfo=new StuAttendanceInfo();
				stuAttendanceInfo.setStu_auth_id(authId);
				if(orgaId!=0)
				stuAttendanceInfo.setOrga_id(orgaId);
				List<StuAttendanceInfo> lists = null;	
			
				//得到当前轮转的开始时间和结束时间           还有现在的日期        
				TrainPlan trainPlan=new TrainPlan();
				trainPlan.setStu_auth_id(authId);
				trainPlan.setQueryCondition(" and train_status in(53,54,55,56,57)");
				String orga=req.getParameter("orga");
				if(orga.equals("-1")){
					orga=Integer.toString(orgaId);
				}
					List<TrainPlan> trainList=trainPlanDao.selectList(trainPlan);//得到该用户轮转的所有信息
					TrainPlan trainPlan3=null;
					for (TrainPlan trainPlan2 : trainList) {
						if(orga.equals(trainPlan2.getTrain_dept_id())){
							trainPlan3=trainPlan2; //   对应的轮转计划
						}
					}
					if(trainPlan3!=null){//没有这个科室的轮转
					stuAttendanceInfo.setOrga_id(Integer.parseInt(trainPlan3.getTrain_dept_id()));
				
					//科室轮转期间所有的日子中放值
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
					String startDate1=trainPlan3.getTrain_start_date().toString()+"000000";     //轮转的开始时间
					String endDate1=trainPlan3.getTrain_end_date().toString()+"000000";	   //轮转的结束时间
					stuAttendanceInfo.setStart_time(trainPlan3.getTrain_start_time());
					stuAttendanceInfo.setEnd_time(trainPlan3.getTrain_end_time());
					lists = stuAttendanceInfoDao.selectListBiao(stuAttendanceInfo);	//相应科室的签到状况
					Date startDate;
					Date endDate;
					Date newDate=new Date();
					//计算两个日期的差
					int day=0;			
					try {
						startDate = sdf.parse(startDate1);
						endDate = sdf.parse(endDate1);
						if(DateUtil.daysBetween(endDate, newDate)>0){
							day = DateUtil.daysBetween(startDate, endDate)+1;
						}else
						{
							day=DateUtil.daysBetween(startDate, newDate)+1;
						}
					} catch (ParseException e1) {					
						e1.printStackTrace();
					};
					int id_num = -1;
					for(int i=0;i<day;i++){					 	
						 int c=0;
						 for (StuAttendanceInfo stuAttendanceInfo2 : lists) {
							 if(stuAttendanceInfo2.getAttend_date().toString().equals(DateUtil.addDate(trainPlan3.getTrain_start_date().toString()+"000000", i,"yyyyMMdd"))){//考勤的
											listAll.add(stuAttendanceInfo2);
											c=listAll.size();
											//System.out.println(stuAttendanceInfo2.getAttend_state());
											if(stuAttendanceInfo2.getAttend_name().equals("已考勤")){
												attendance++;	
											}else if(stuAttendanceInfo2.getAttend_name().equals("未考勤")){
												noAttendance++;
											}else{
												leave++;
											}
											break;
										}
									}
						 			if(c==0){//已考勤
						 				id_num -= 1;
						 				StuAttendanceInfo e=new StuAttendanceInfo();
						 				String date=null; 
						 				date=DateUtil.addDate(startDate1, i,"yyyyMMdd");
						 				e.setId(id_num);
										e.setAttendeDate_Str(DateUtil.getFormatDateString(date.toString()));
							 			e.setAttend_state(0);
							 			e.setAttend_name("已考勤");
							 			e.setState_id(-1);
							 			attendance++;
							 			listAll.add(e);
							 		}else{
							 			continue;
							 		}
						 		}
					}else{
						listAll=null;
					};
				
				List<StuAttendanceInfo>	 lists1=new ArrayList<StuAttendanceInfo>();
				for (int i = listAll.size()-1; i >=0; i--) {
					lists1.add(listAll.get(i));
				}
				jobj.put("rows", lists1);
				if(jobj!=null){
					String jsonStr = StringUtil.dnull(jobj.toString());
					jobj = JSONObject.fromObject(jsonStr);
				}
			
				return jobj;
		}
	public Map<String, Object> getPercent() {
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		if(leave==0&&noAttendance==0){
			attendance=1;
		}
		rtnMap.put("success", true);
		rtnMap.put("attendance",attendance);
		rtnMap.put("noAttendance",noAttendance);
		rtnMap.put("leave",leave);
		return rtnMap;
	}
	public JSONObject getTrainOrga(int auth_id) {
		List<TrainPlan> trainList=trainPlanDao.selectOrgaNameList(auth_id);//得到该用户轮转的所有信息
		JSONObject jobj = new JSONObject();
		jobj.put("trainList", trainList);
		return jobj;
	}
	public Map<String, Object> getIndexPercent(HttpServletRequest req) {
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer authId = loginBean.getvUserDetailInfo().getAuth_id();   //权限id
		Integer orgaId=loginBean.getvUserDetailInfo().getOrga_id();  //当前科室

		List<StuAttendanceInfo> listAll=new ArrayList<StuAttendanceInfo>();  //用来存储轮转日子里所有的数据		
		attendance=0;//考勤 数量
		noAttendance=0;//未考勤数量
		leave=0;//请假
		//根据缓存中的信息得到当前的科室 
		StuAttendanceInfo stuAttendanceInfo=new StuAttendanceInfo();
		stuAttendanceInfo.setStu_auth_id(authId);
		stuAttendanceInfo.setOrga_id(orgaId);
		List<StuAttendanceInfo> lists = null;	
	
		//得到当前轮转的开始时间和结束时间           还有现在的日期        
		TrainPlan trainPlan=new TrainPlan();
		trainPlan.setStu_auth_id(authId);
		
		String orga=req.getParameter("orga");
			orga=Integer.toString(orgaId);
			List<TrainPlan> trainList=trainPlanDao.selectList(trainPlan);//得到该用户轮转的所有信息
			TrainPlan trainPlan3=null;
			for (TrainPlan trainPlan2 : trainList) {
				if(orga.equals(trainPlan2.getTrain_dept_id())){
					trainPlan3=trainPlan2; //   对应的轮转计划
				}
			}
			if(trainPlan3!=null){//没有这个科室的轮转
			stuAttendanceInfo.setOrga_id(Integer.parseInt(trainPlan3.getTrain_dept_id()));
			//科室轮转期间所有的日子中放值
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String startDate1=trainPlan3.getTrain_start_date().toString()+"000000";     //轮转的开始时间
			String endDate1=trainPlan3.getTrain_end_date().toString()+"000000";	   //轮转的结束时间
			stuAttendanceInfo.setStart_time(trainPlan3.getTrain_start_time());
			stuAttendanceInfo.setEnd_time(trainPlan3.getTrain_end_time());
			lists = stuAttendanceInfoDao.selectListBiao(stuAttendanceInfo);	//相应科室的签到状况
			Date startDate;
			Date endDate;
			Date newDate=new Date();
			//计算两个日期的差
			//计算两个日期的差
			int day=0;			
			try {
				startDate = sdf.parse(startDate1);
				endDate = sdf.parse(endDate1);
				if(DateUtil.daysBetween(endDate, newDate)>0){
					day = DateUtil.daysBetween(startDate, endDate)+1;
				}else{
					day=DateUtil.daysBetween(startDate, newDate)+1;
				}
			} catch (ParseException e1) {					
				e1.printStackTrace();
			};
			int id_num = -1;
			for(int i=0;i<day;i++){					 	
				 int c=0;
				 for (StuAttendanceInfo stuAttendanceInfo2 : lists) {
					 if(stuAttendanceInfo2.getAttend_date().toString().equals(DateUtil.addDate(trainPlan3.getTrain_start_date().toString()+"000000", i,"yyyyMMdd"))){//考勤的
									listAll.add(stuAttendanceInfo2);
									c=listAll.size();
									//System.out.println(stuAttendanceInfo2.getAttend_state());
									if(stuAttendanceInfo2.getAttend_name().equals("已考勤")){
										attendance++;	
									}else if(stuAttendanceInfo2.getAttend_name().equals("未考勤")){
										noAttendance++;
									}else{
										leave++;
									}
									break;
								}
							}
				 			if(c==0){//未考勤
				 				id_num -= 1;
				 				StuAttendanceInfo e=new StuAttendanceInfo();
				 				String date=null; 
				 				date=DateUtil.addDate(startDate1, i,"yyyyMMdd");
				 				e.setId(id_num);
								e.setAttendeDate_Str(DateUtil.getFormatDateString(date.toString()));
					 			e.setAttend_state(0);
					 			e.setAttend_name("已考勤");
					 			e.setState_id(-1);
					 			attendance++;
					 			listAll.add(e);
					 		}else{
					 			continue;
					 		}
				 		}
			}else{
				listAll=null;
			};
			Map<String, Object> rtnMap = new HashMap<String, Object>();
			if(noAttendance==0&&leave==0){
				attendance=1;
			}
			BigDecimal   b   =   new   BigDecimal(attendance/(leave+noAttendance+attendance));
			double   f1   =   b.setScale(2,   RoundingMode.HALF_UP).doubleValue();
			
			BigDecimal   b1   =   new   BigDecimal(leave/(leave+noAttendance+attendance));
			double   f2   =   b1.setScale(2,   RoundingMode.HALF_UP).doubleValue();
			
			BigDecimal   b2   =   new   BigDecimal(noAttendance/(leave+noAttendance+attendance));
			double   f3   =   b2.setScale(2,   RoundingMode.HALF_UP).doubleValue();
			

			rtnMap.put("success", true);
			rtnMap.put("attendance",f1);
			rtnMap.put("noAttendance",f3);
			rtnMap.put("leave",f2);
			return rtnMap;
	}
	/**
	 * 查询学生的每日考勤状态
	 * @param stuAttendanceInfo
	 * @return StuAttendanceInfo
	 * @author guocc
	 * @date 2017年5月26日
	 */
	public List<StuAttendanceInfo> getStuAttendanceInfoByAttendDate(StuAttendanceInfo stuAttendanceInfo) {
		return stuAttendanceInfoDao.getStuAttendanceInfoByAttendDate(stuAttendanceInfo);
	}
	/**
	 * 修改考勤状态
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月27日
	 */
	public Object manageAttendState(HttpServletRequest req) {
		String id = req.getParameter("id");
		String stu_auth_id = req.getParameter("stu_auth_id");
		String attend_state = req.getParameter("attend_state");
		//从缓存中得到auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer attend_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orga_id=loginBean.getvUserDetailInfo().getOrga_id();
		if (id != null && !id.equals("") && attend_state != null && !attend_state.equals("") && stu_auth_id != null && !stu_auth_id.equals("")) {
			StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
			stuAttendanceInfo.setAttend_state(Integer.parseInt(attend_state));
			int num = 0;
			if(id != null && id.indexOf("line") == -1){//  修改
				stuAttendanceInfo.setId(Integer.parseInt(id));
				stuAttendanceInfo.setAttend_auth_id(attend_auth_id);
				num = stuAttendanceInfoDao.update(stuAttendanceInfo);
			}else if(id != null && id.indexOf("line") != -1){//   新增
				if (attend_state.equals("0")) {
					stuAttendanceInfo.setId(stuAttendanceInfoDao.getSequence());
					stuAttendanceInfo.setStu_auth_id(Integer.parseInt(stu_auth_id));
					// 获取当前时间
					java.util.Date date = new java.util.Date();
					DateFormat format = new SimpleDateFormat("yyyyMMdd");
					Integer attend_date = Integer.parseInt(format.format(date));
					stuAttendanceInfo.setAttend_date(attend_date);
					stuAttendanceInfo.setOrga_id(orga_id);
					stuAttendanceInfo.setAttend_auth_id(attend_auth_id);
					stuAttendanceInfo.setAttend_time(new Timestamp((new Date()).getTime()));
					stuAttendanceInfo.setState("Y");
					num = stuAttendanceInfoDao.add(stuAttendanceInfo);
				}else {
					num = 1; 
				}
			}
			if(num > 0){
				return StringUtil.returns(true, "操作成功！");
			}else {
				return StringUtil.returns(false, "操作失败！");
			}
		}else {
			return StringUtil.returns(false, "操作失败！");
		}
	}
	/***
	 * 得到当前科室的考勤二维码字符
	 * @return
	 * @author yuanb
	 * @date 2017年5月17日
	 */
	public String getAttendanceQRStr(HttpServletRequest req){
		//得到当前用户的科室
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id(); //当前用户的科室ID
		Integer attend_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		//得到服务器的当前日期
		String nowDate = DateUtil.getCurrDate();
		//组成格式：“标识:考勤日期-科室ID” 这种字符形式
		String qrStr = "attendance:" + nowDate + "-" + orga_id;
		//进行Des加密
		//1.首先得到秘钥
		String key = DesUtils.getStrDefaultKey();
		//2.进行加密处理
		String rtn = "";
		try {
			//3.得到加密后的字符串
			DesUtils des= new DesUtils(key);
			rtn = des.encrypt(qrStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//得到本科室需要考勤的学生列表（今日请假的不算在内）
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setState("Y");
		trainPlan.setTrain_dept_id(orga_id.toString());
		trainPlan.setQueryCondition("and train_status in (" + Consts.ProcessInfo.TRAINPROCESSInAll + ")");
		List<TrainPlan> trainPlanList = trainPlanDao.selectList(trainPlan);
		StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
		stuAttendanceInfo.setAttend_date(Integer.parseInt(nowDate));
		stuAttendanceInfo.setState("Y");
		stuAttendanceInfo.setOrga_id(orga_id);
		StuAttendanceInfo sai = new StuAttendanceInfo();
		sai.setState("Y");
		sai.setAttend_state(0);
		sai.setAttend_auth_id(attend_auth_id);
		sai.setAttend_date(Integer.parseInt(nowDate)); 
		Timestamp attend_time = DateUtil.str2Timestamp(nowDate+"120000","yyyyMMddHHmmss");
		sai.setAttend_time(attend_time);
		sai.setOrga_id(orga_id);
		for (TrainPlan tp : trainPlanList) {
			UserAuthority userAuthority = userAuthorityDao.selectOne(tp.getStu_auth_id());
			if (userAuthority != null && tp.getTrain_dept_id().equals(userAuthority.getOrga_id().toString())) {
				stuAttendanceInfo.setStu_auth_id(tp.getStu_auth_id());
				List<StuAttendanceInfo> list = stuAttendanceInfoDao.selectList(stuAttendanceInfo);
				if (list != null && !list.isEmpty()) {
					
				}else{
					sai.setId(stuAttendanceInfoDao.getSequence());
					sai.setStu_auth_id(tp.getStu_auth_id());
					stuAttendanceInfoDao.add(sai);
				}
			}
		}
		//然后批量插入这些学生的考勤记录（默认都是未考勤） ***只有手机扫过二维码后才能变成已考勤***
		
		
		return rtn;
	}
	

}
