package com.rensu.education.hctms.app.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi2.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.app.bean.StuOutDeptRecord;
import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.basicdata.bean.RoleProcRela;
import com.rensu.education.hctms.basicdata.dao.RoleProcRelaDao;
import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.TrainTeachOrderDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.OutdeptRecord;
import com.rensu.education.hctms.log.bean.ProcessRecord;
import com.rensu.education.hctms.log.bean.ProcrecordUserRela;
import com.rensu.education.hctms.log.dao.OutdeptRecordDao;
import com.rensu.education.hctms.log.dao.ProcessRecordDao;
import com.rensu.education.hctms.log.dao.ProcrecordUserRelaDao;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.teach.bean.CathedraCondition;
import com.rensu.education.hctms.teach.bean.CathedraDetail;
import com.rensu.education.hctms.teach.bean.CathedraPlan;
import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;
import com.rensu.education.hctms.teach.bean.StuDailyRecord;
import com.rensu.education.hctms.teach.bean.StuVacateInfo;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.CathedraPlanDao;
import com.rensu.education.hctms.teach.dao.ReviewLeaveDao;
import com.rensu.education.hctms.teach.dao.StuAttendanceInfoDao;
import com.rensu.education.hctms.teach.dao.StuDailyRecordDao;
import com.rensu.education.hctms.teach.dao.StuVacateInfoDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.bean.VUserDetailInfo;
import com.rensu.education.hctms.userauth.dao.OrgaInfoDao;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.userauth.dao.UserInfoDao;
import com.rensu.education.hctms.userauth.dao.VUserDetailInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;
import com.rensu.education.hctms.util.DesUtils;
import com.rensu.education.hctms.util.StringUtil;


@Service("appStuService")
public class AppStuService extends BaseService<T> {
	
	@Autowired
	protected UserInfoDao userInfoDao;
	@Autowired
	protected VUserDetailInfoDao vUserDetailInfoDao;
	@Autowired
	protected TrainPlanDao trainPlanDao;
	@Autowired
	protected StuAttendanceInfoDao stuAttendanceInfoDao;
	@Autowired
	protected MessageReceiveDao messageReceiveDao;
	@Autowired
	protected CathedraPlanDao cathedraPlanDao;
	@Autowired
	protected OrgaInfoDao orgaInfoDao;
	@Autowired
	protected StuDailyRecordDao stuDailyRecordDao;
	@Autowired
	protected RoleProcRelaDao roleProcRelaDao;
	@Autowired
	protected StuVacateInfoDao stuVacateInfoDao;
	@Autowired
	protected ProcessRecordDao processRecordDao;
	@Autowired
	protected ProcrecordUserRelaDao procrecordUserRelaDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	@Autowired
	protected MessagePublishDao messagePublishDao;
	@Autowired
	protected ReviewLeaveDao reviewLeaveDao;
	@Autowired
	protected TrainTeachOrderDao trainTeachOrderDao;
	@Autowired
	protected OutdeptRecordDao outdeptRecordDao;
	
	
	public String appLogin(String usercode,String password) throws ParseException{
		String rtnJson = "";
		UserInfo user = new UserInfo();
		user.setUser_code(usercode);
		user.setUser_password(password);
		user.setState("Y");
		List<UserInfo> userList = userInfoDao.selectList(user);
		if (userList == null || userList.size() == 0) {
			return "{\"success\":false,\"msg\":\"用户名或密码错误！\"}";
		}
		if("M".equals(userList.get(0).getState())){
			return "{\"success\":false,\"msg\":\"您的轮转计划正在编排或审核中！\"}";
		}
		
		//查询出几个角色
		VUserDetailInfo userDetail = new VUserDetailInfo();
		userDetail.setUser_id(userList.get(0).getUser_id());
		userDetail.setState(Consts.STATUS_Y);
		userDetail.setOrderCondition("role_id");
		List<VUserDetailInfo> userDetailList = vUserDetailInfoDao.selectList(userDetail);
		if (userDetailList == null || userDetailList.size() == 0) {
			return "{\"success\":false,\"msg\":\"您还未分配角色或科室，请联系管理员！\"}";
		}
		//如果是学生，检查其学生信息是否已经维护
		if (userDetailList.get(0).getRole_id()==10) {
			int authId = userDetailList.get(0).getAuth_id();
			//1、检查是否有轮转计划配置
			TrainPlan tp = new TrainPlan();
			tp.setStu_auth_id(authId);
			tp.setState("Y");
			tp.setOrderCondition("train_start_date");
			List<TrainPlan> tpList = trainPlanDao.selectList(tp);
			if(tpList==null||tpList.size()<=0){
				return "{\"success\":false,\"msg\":\"您还未生成轮转计划，请联系科教科处理此问题！\"}";
			}
		}
		boolean fl = true;
		//如果是学生，检查其学生信息是否已经维护
		if (userDetailList.get(0).getRole_id()==10) {
			int authId = userDetailList.get(0).getAuth_id();
			//1、检查是否有轮转计划配置
			TrainPlan tp = new TrainPlan();
			tp.setStu_auth_id(authId);
			tp.setState("Y");
			List<TrainPlan> tpList = trainPlanDao.selectList(tp);
			if(tpList==null||tpList.size()<=0){
				fl = false;
			}else{
				//2、检查是否有科室接收 
				boolean trainStatus52Flag = false;
				String orgaStr = "";
				for(int i=0;tpList!=null&&i<tpList.size();i++){
					if(tpList.get(i).getTrain_status()==Consts.ProcessInfo.TRAINPROCESS0){ //是待入科状态
						OrgaInfo orgaInfo = orgaInfoDao.selectOne(Integer.parseInt(tpList.get(i).getTrain_dept_id()));
						String orga_name = orgaInfo.getOrga_name();
						orgaStr += orga_name + "、";
						trainStatus52Flag = true;
					}
				}
				if (trainStatus52Flag) {
					orgaStr = orgaStr.substring(0,orgaStr.length()-1);
					return "{\"success\":false,\"msg\":\"您在【" + orgaStr + "】未作入科报到！【请联系科室教学秘书办理入科】\"}";
					//return StringUtil.returns(false, "您在【" + orgaStr + "】未作入科报到！【请联系科室教学秘书办理入科】");
				}
			}
			
		}
		
		//取第一个角色是什么
		if(userDetailList.get(0).getRole_id()==10){ //是学生
			rtnJson = jumpToStuMain(userDetailList);
		}else if(userDetailList.get(0).getRole_id()==20){ //是带教老师
			rtnJson = jumpToTeaMain(userDetailList);
		}else if(userDetailList.get(0).getRole_id()==30){ //是教学秘书
			rtnJson = jumpToStuMain(userDetailList);
		}else if(userDetailList.get(0).getRole_id()==40){ //是科主任
			rtnJson = jumpToStuMain(userDetailList);
		}else if(userDetailList.get(0).getRole_id()==50){ //是科教科
			rtnJson = jumpToStuMain(userDetailList);
		}else{
			rtnJson = "{\"success\":false,\"msg\":\"您还没有对应的角色！\"}";
		}
		return rtnJson;
	}
	
	public String jumpToStuMain(List<VUserDetailInfo> vList) throws ParseException{
		JSONArray json = JSONArray.fromObject(vList);
		//1、查询业务上的数据--今日考勤
		int attCount = -1;
		StuAttendanceInfo stuAtt = this.getAttCountInfo(vList.get(0).getAuth_id());
		if(stuAtt==null||stuAtt.getId()==null){
			attCount = -1;
		}else{
			attCount = 1;
		}
		//2、查询未读的通知公告的条数
		MessageReceive messageReceive=new MessageReceive();
		messageReceive.setReceiver_auth_id(vList.get(0).getAuth_id());
		messageReceive.setProgress_state(0);
		messageReceive.setState("Y");
		int msgCount = messageReceiveDao.selectCount(messageReceive);
		//3、查看一周之内有讲座的数量
		List<CathedraPlan> cpList = this.getCplistOneWeek(vList.get(0).getAuth_id());
		int cpCount = 0;
		if(cpList!=null&&cpList.size()>0){
			cpCount = cpList.size();
		}
		//4、
		String rtnJson = "{\"success\":true,"
				  + "\"vList\":"+json.toString()+","
				  + "\"attCount\":"+attCount+","
				  + "\"msgCount\":"+msgCount+","
				  + "\"cpCount\":"+cpCount+""
				  + "}";
		return rtnJson;
	}
	
	public String jumpToTeaMain(List<VUserDetailInfo> vList) throws ParseException{
		JSONArray json = JSONArray.fromObject(vList);
		//1、查询未读的通知公告的条数
		MessageReceive messageReceive=new MessageReceive();
		messageReceive.setReceiver_auth_id(vList.get(0).getAuth_id());
		messageReceive.setProgress_state(0);
		messageReceive.setState("Y");
		int msgCount = messageReceiveDao.selectCount(messageReceive);
		//2、查看未阅的学生日志
		StuDailyRecord stuDailyRecord = new StuDailyRecord();
		stuDailyRecord.setState("N");
		stuDailyRecord.setTeacher_auth_id(vList.get(0).getAuth_id());
		List<StuDailyRecord> dataList = stuDailyRecordDao.selectReviewedDaily(new RowBounds(),stuDailyRecord);
		int stuDRcount = 0;
		if(dataList!=null&&dataList.size()>0){
			stuDRcount = dataList.size();
		}
		//3、查看请假申请条数
		StuVacateInfo stuVacateInfo = new StuVacateInfo();
		stuVacateInfo.setTrain_status_str(Consts.ProcessInfo.TRAINPROCESSInAll);
		stuVacateInfo.setTeacher_auth_id(vList.get(0).getAuth_id());
		stuVacateInfo.setVacate_status(61);
		stuVacateInfo.setOrga_id(vList.get(0).getOrga_id());
		List<StuVacateInfo> sviList = stuVacateInfoDao.selectVacateList(new RowBounds(),stuVacateInfo);
		int stuAttcount = 0;
		if(sviList!=null&&sviList.size()>0){
			stuAttcount = sviList.size();
		}
		//组成json返回前台
		String rtnJson = "{\"success\":true,"
				  + "\"vList\":"+json.toString()+","
				  + "\"stuDRcount\":"+stuDRcount+","
				  + "\"stuAttcount\":"+stuAttcount+","
				  + "\"msgCount\":"+msgCount
				  + "}";
		return rtnJson;
	}
	
	public StuAttendanceInfo getAttCountInfo(int stuAuthId){
		//到考勤表中查询今天的考勤记录
		StuAttendanceInfo sai = new StuAttendanceInfo();
		sai.setStu_auth_id(stuAuthId);
		sai.setAttend_date(Integer.parseInt(DateUtil.getCurrDate()));
		sai.setAttend_state(1);
		sai.setState("Y");
		List<StuAttendanceInfo> saiList = stuAttendanceInfoDao.selectList(sai);
		if(saiList!=null&&saiList.size()>0){
			return saiList.get(0);
		}
		return null;
	}
	
	public List<CathedraPlan> getCplistOneWeek(int authId){
//		MessageReceive messageReceive=new MessageReceive();
//		messageReceive.setReceiver_auth_id(authId);
//		messageReceive.setProgress_state(0);
//		messageReceive.setState("Y");
//		int msgCount = messageReceiveDao.selectCount(messageReceive);
		//3、查看一周之内有讲座的数量
		String sDate = DateUtil.getCurrDate_Format()+" 00:00:00";
		Date dateAfter7 = DateUtil.getDateAfter(new Date(), 7);
		String eDate = DateUtil.format(dateAfter7, "yyyy-MM-dd")+" 23:59:59";
		
		CathedraDetail cathedraDetail = cathedraPlanDao.getClassAndTypeByAuthId(authId); //获取届次和类型
		CathedraPlan cathedraPlan = new CathedraPlan();
		cathedraPlan.setTimeStart(Timestamp.valueOf(sDate));
		cathedraPlan.setTimeEnd(Timestamp.valueOf(eDate));
		cathedraPlan.setStatus("Y");
		cathedraPlan.setOrderCondition(" cath_time");
		List<CathedraPlan> listCathedraPlanGet = cathedraPlanDao.selectCathedraNotice(cathedraPlan);
		CathedraCondition cathedraCondition = new CathedraCondition();
		List<CathedraPlan> listCathedraPlan = new ArrayList<CathedraPlan>();
		for(CathedraPlan cathPlan : listCathedraPlanGet){
			int condition_type = 1;//条件类型
			cathedraCondition.setCondition_type(condition_type);
			cathedraCondition.setCp_id(cathPlan.getId());
			CathedraCondition cathedraConditionGet = cathedraPlanDao.selectCathedraCondition(cathedraCondition);
			String[] stuClass = cathedraConditionGet.getCondition_value().split(",");
			for(int i = 0; i < stuClass.length; i++){
				if(cathedraDetail != null && cathedraDetail.getStu_class() != null && stuClass[i].equals(cathedraDetail.getStu_class())){  //判断届次
					condition_type = 2;
					cathedraCondition.setCondition_type(condition_type);
					cathedraConditionGet = cathedraPlanDao.selectCathedraCondition(cathedraCondition);
					String[] stuType = cathedraConditionGet.getCondition_value().split(",");
					for(int j = 0; j < stuType.length; j++){
						if(cathedraDetail.getType_name() != null && stuType[j].equals(cathedraDetail.getType_name())){  //判断类型
							listCathedraPlan.add(cathPlan);
						}
					}
				}
			}
		}
		return listCathedraPlan;
	}
	
	public String stuAttend(String scnData,String orgaId,String authId) throws ParseException{
		String rtnJson = "";
		//1、根据orga_id+当前日期生成加密的秘钥
		//得到服务器的当前日期
		String nowDate = DateUtil.getCurrDate();
		//组成格式：“标识:考勤日期-科室ID” 这种字符形式
		String qrStr = "attendance:" + nowDate + "-" + orgaId;
		//进行Des加密
		//1.首先得到秘钥
		String key = DesUtils.getStrDefaultKey();
		//2.进行加密处理
		String thisKey = "";
		try {
			//3.得到加密后的字符串
			DesUtils des= new DesUtils(key);
			thisKey = des.encrypt(qrStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(thisKey.equals(scnData)){ //是相同的，那就修改数据库进行考勤的修改
			//修改当前学生今天的考勤记录为已考勤
			Map<String,Object> pMap = new HashMap<String,Object>();
			pMap.put("authId", authId);
			pMap.put("orgaId", orgaId);
			pMap.put("nowDate", nowDate);
			if(stuAttendanceInfoDao.alreadyComplateAttendance(pMap)>0){
				rtnJson = "{\"success\":true,\"msg\":\"考勤成功！\"}";
			}else{
				rtnJson = "{\"success\":false,\"msg\":\"考勤失败...\"}";
			}
		}else{
			rtnJson = "{\"success\":false,\"msg\":\"考勤失败，无效的二维码！\"}";
		}
		return rtnJson;
	}
	
	
	public String getNewsListInfo(String userAuthId){
		//根据userAuthId到消息表中查到未读消息的列表
		MessageReceive mr = new MessageReceive();
		mr.setReceiver_auth_id(Integer.parseInt(userAuthId));
		mr.setState("Y");
		mr.setProgress_state(0);
		mr.setOrderCondition(" p.send_time desc");
		List<MessageReceive> lists = messageReceiveDao.selectPageAll(new RowBounds(0,50), mr);
		
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"newsList\":[");
		for(int i=0;lists!=null&&i<lists.size();i++){
			if(i==lists.size()-1){
				rtnJson.append("{\"id\":\""+lists.get(i).getId()+"\",\"title\":\""+lists.get(i).getTitle()+"\",\"sendName\":\""+lists.get(i).getSendName()+"\",\"sendTimeStr\":\""+lists.get(i).getSendTimeStr()+"\",\"type_Name\":\""+lists.get(i).getType_Name()+"\"}");
			}else{
				rtnJson.append("{\"id\":\""+lists.get(i).getId()+"\",\"title\":\""+lists.get(i).getTitle()+"\",\"sendName\":\""+lists.get(i).getSendName()+"\",\"sendTimeStr\":\""+lists.get(i).getSendTimeStr()+"\",\"type_Name\":\""+lists.get(i).getType_Name()+"\"},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	
	public String getnewsInfo(String id){
		String rtnJson = "";
		//根据消息接收表ID查询详情
		MessageReceive mr = new MessageReceive();
		mr.setId(Integer.parseInt(id));
		List<MessageReceive> list = messageReceiveDao.selectPageAll(new RowBounds(0,1), mr);
		if(list!=null&&list.size()>0){
			for (int i = 0; i <list.size(); i++) {
				mr=list.get(0);
				mr.setProgress_state(1);   //改变状态  已经查看过
				messageReceiveDao.update(mr);
			}
			JSONArray json = JSONArray.fromObject(list.get(0));
			rtnJson = "{\"success\":true,"
					  + "\"mr\":"+ json.toString()
					  + "}";
		}else{
			rtnJson = "{\"success\":false,"
					  + "\"msg\":\"读取数据出错！\""
					  + "}";
		}
		return rtnJson;
	}
	
	public String getCpInfo(String id){
		String rtnJson = "";
		//根据消息接收表ID查询详情
		CathedraPlan cp = cathedraPlanDao.selectOne(Integer.parseInt(id));
		if(cp!=null&&cp.getId()!=null){
			JSONArray json = JSONArray.fromObject(cp);
			rtnJson = "{\"success\":true,"
					  + "\"cp\":"+ json.toString()
					  + "}";
		}else{
			rtnJson = "{\"success\":false,"
					  + "\"msg\":\"读取数据出错！\""
					  + "}";
		}
		return rtnJson;
	}
	
	public String getStuDrInfo(String id,String teaFlag){
		String rtnJson = "";
		//如果teaFlag不为空，表示是老师查看日志，需要改变状态
		if(StringUtil.isNotEmpty(teaFlag)){
			StuDailyRecord _sdr = new StuDailyRecord();
			_sdr.setState("Y");
			_sdr.setId(Integer.parseInt(id));
			stuDailyRecordDao.update(_sdr);
		}
		//根据消息接收表ID查询详情
		StuDailyRecord sdr = stuDailyRecordDao.selectOne(Integer.parseInt(id));
		if(sdr!=null&&sdr.getId()!=null){
			JSONArray json = JSONArray.fromObject(sdr);
			rtnJson = "{\"success\":true,"
					  + "\"sdr\":"+ json.toString()
					  + "}";
		}else{
			rtnJson = "{\"success\":false,"
					  + "\"msg\":\"读取数据出错！\""
					  + "}";
		}
		return rtnJson;
	}
	
	
	public String updateAllMsgState(String stuAuthId){
		String rtnJson = "";
		//根据消息接收表ID查询详情
		MessageReceive mr = new MessageReceive();
		mr.setReceiver_auth_id(Integer.parseInt(stuAuthId));
		mr.setProgress_state(1);
		mr.setState("Y");
		if(messageReceiveDao.updateAllMsgStateByAuthId(mr)>0){
			rtnJson = "{\"success\":true,"
					+ "\"msg\":\"操作成功！\""
					  + "}";
		}else{
			rtnJson = "{\"success\":false,"
					  + "\"msg\":\"数据处理出错...\""
					  + "}";
		}
		return rtnJson;
	}
	
	
	public String getCpListInfo(String userAuthId){
		List<CathedraPlan> cpList = this.getCplistOneWeek(Integer.parseInt(userAuthId));
		
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"cpList\":[");
		for(int i=0;cpList!=null&&i<cpList.size();i++){
			if(i==cpList.size()-1){
				rtnJson.append("{\"id\":\""+cpList.get(i).getId()+"\",\"cath_title\":\""+cpList.get(i).getCath_title()+"\",\"cath_time\":\""+cpList.get(i).getCath_time_str()+"\",\"cath_place\":\""+cpList.get(i).getCath_place()+"\",\"speaker_name\":\""+cpList.get(i).getSpeaker_name()+"\"}");
			}else{
				rtnJson.append("{\"id\":\""+cpList.get(i).getId()+"\",\"cath_title\":\""+cpList.get(i).getCath_title()+"\",\"cath_time\":\""+cpList.get(i).getCath_time_str()+"\",\"cath_place\":\""+cpList.get(i).getCath_place()+"\",\"speaker_name\":\""+cpList.get(i).getSpeaker_name()+"\"},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	public String addStuDailyRecordInfo(String stuAuthId,String drType,String duration,String drContent){
		String rtnJson = "";
		//根据消息接收表ID查询详情
		StuDailyRecord sdr = new StuDailyRecord();
		sdr.setId(stuDailyRecordDao.getSequence());
		sdr.setType_id(Integer.parseInt(drType));
		sdr.setDuration(duration);
		sdr.setContent(drContent);
		java.sql.Timestamp create_time = new java.sql.Timestamp(new java.util.Date().getTime());
		sdr.setCreate_time(create_time);
		sdr.setCreate_auth_id(Integer.parseInt(stuAuthId));
		sdr.setState("N");
		if(stuDailyRecordDao.add(sdr)>0){
			rtnJson = "{\"success\":true,"
					+ "\"msg\":\"提交成功！\""
					  + "}";
		}else{
			rtnJson = "{\"success\":false,"
					  + "\"msg\":\"数据提交出错...\""
					  + "}";
		}
		return rtnJson;
	}
	
	public String getDRListInfo(String stuAuthId,String drType){
		//得到此学生的培训日志列表
		StuDailyRecord sdr = new StuDailyRecord();
		sdr.setCreate_auth_id(Integer.parseInt(stuAuthId));
		if(StringUtil.isNotEmpty(drType)){
			sdr.setSearchStr("1");
			sdr.setType_id(Integer.parseInt(drType));
		}else{
			sdr.setSearchStr("");
		}
		List<StuDailyRecord> sdrList = stuDailyRecordDao.selectDailyRecord(new RowBounds(),sdr);
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"sdrList\":[");
		for(int i=0;sdrList!=null&&i<sdrList.size();i++){
			if(i==sdrList.size()-1){
				rtnJson.append("{\"id\":\""+sdrList.get(i).getId()+"\",\"typeName\":\""+sdrList.get(i).getType_id_str()+"\",\"duration\":\""+sdrList.get(i).getDuration()+"\",\"state\":\""+sdrList.get(i).getState_str()+"\"}");
			}else{
				rtnJson.append("{\"id\":\""+sdrList.get(i).getId()+"\",\"typeName\":\""+sdrList.get(i).getType_id_str()+"\",\"duration\":\""+sdrList.get(i).getDuration()+"\",\"state\":\""+sdrList.get(i).getState_str()+"\"},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	
	public String addStuAttInfo(String stuAuthId,String stuOrgaId,String attTypeSel,String attTypeSelTxt,String sDate,
			String eDate,String attDateNum,String attContent){
		String rtnJson = "";
		Date date = new Date();       
		Timestamp create_time = new Timestamp(date.getTime());
		StuVacateInfo stuVacateInfo = new StuVacateInfo();
		stuVacateInfo.setCreate_auth_id(Integer.parseInt(stuAuthId));
		stuVacateInfo.setCreate_time(create_time);
		stuVacateInfo.setOrga_id(Integer.parseInt(stuOrgaId));
		stuVacateInfo.setState("Y");
		stuVacateInfo.setVacate_type_code(attTypeSel);
		stuVacateInfo.setVacate_type_name(attTypeSelTxt);
		stuVacateInfo.setStart_time(Timestamp.valueOf(sDate+" 00:00:00")); //转换时间字符串为Timestamp
		stuVacateInfo.setEnd_time(Timestamp.valueOf(eDate+" 00:00:00"));
		int start_date=Integer.parseInt(sDate.replace("-",""));
		stuVacateInfo.setStart_date(start_date);
		int end_date=Integer.parseInt(eDate.replace("-",""));
		stuVacateInfo.setEnd_date(end_date);
		if(StringUtil.isNotEmpty(attDateNum)){
			stuVacateInfo.setVacate_date_num(Integer.parseInt(attDateNum));
		}
		stuVacateInfo.setContent(attContent);
		
		List<RoleProcRela> roleProcList = new ArrayList<RoleProcRela>();
		//查询请假天数对应的流程，根据审批权限天数排序
		List<RoleProcRela> roleProc = roleProcRelaDao.selectRoleProc();
		//请假天数与最大权限比较,若大于最大天数返回提示
		if(Integer.parseInt(attDateNum)>roleProc.get(roleProc.size()-1).getRequire_datenum()){
			return "{\"success\":false,"
					  + "\"msg\":\""+roleProc.get(roleProc.size()-1).getRequire_datenum()+"天以上请通过学校相关部门申请!"+"\""
					  + "}";
		}
		if(attDateNum != null && (attDateNum.equals("-10") || attDateNum.equals("-20"))) {
			attDateNum = "1";
		}
		for (RoleProcRela roleProcRela : roleProc) {
				if(Integer.parseInt(attDateNum)<=roleProcRela.getRequire_datenum()){
					roleProcList.add(roleProcRela);
					break;
				}else{
					roleProcList.add(roleProcRela);
				}
		}
		if(roleProcList.get(0).getEnd_proc_id()!=null && !roleProcList.get(0).getEnd_proc_id().equals("")){
			stuVacateInfo.setVacate_status(roleProcList.get(0).getEnd_proc_id());
		}
		stuVacateInfo.setStu_auth_id(Integer.parseInt(stuAuthId));
		int stuVacateInfoId = stuVacateInfoDao.getSequence();
		stuVacateInfo.setId(stuVacateInfoId);
		if(stuVacateInfoDao.add(stuVacateInfo)>0){
			ProcessRecord processRecord = new ProcessRecord();
			ProcrecordUserRela procrecordUserRela = new ProcrecordUserRela();
			processRecord.setType_code(2);
			processRecord.setOrga_id(Integer.parseInt(stuOrgaId));
			//遍历ProcessInfoList 创建各个步骤
			for(int i = 0 ;i<roleProcList.size();i++){
				int processRecordId=processRecordDao.getSequence();
				//processRecord放入参数，为了新增
				processRecord.setId(processRecordId);
				processRecord.setStart_proc_id(roleProcList.get(i).getStart_proc_id());
				processRecord.setStart_proc_num(roleProcList.get(i).getStart_num());
				processRecord.setStart_proc_name(roleProcList.get(i).getStart_name());
				processRecord.setEnd_proc_id(roleProcList.get(i).getEnd_proc_id());
				processRecord.setEnd_proc_num(roleProcList.get(i).getEnd_num());
				processRecord.setEnd_proc_name(roleProcList.get(i).getEnd_name());
				processRecord.setRelated_id(stuVacateInfoId);
				processRecordDao.add(processRecord);
				procrecordUserRela.setPr_id(processRecordId);
				procrecordUserRela.setState("Y");
				procrecordUserRela.setId(procrecordUserRelaDao.getSequence());
				procrecordUserRelaDao.add(procrecordUserRela);
			}
			//发送消息
			//通过学生authId查找相关的学生信息
			UserAuthority _ua = new UserAuthority();
			_ua.setAuth_id(Integer.parseInt(stuAuthId));
			UserAuthority ua = userAuthorityDao.selectStuByAuthId(_ua);
			int mpId = 0;
			String title = ua.getName()  + "【" + ua.getOrga_name() + "】" +  "发起了请假申请，请及时审批！";
			// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
			MessagePublish mp = new MessagePublish();
			mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			mp.setTitle(title);
			mp.setContent(attContent);
			mp.setSend_time(create_time);
			mp.setSender_auth_id(Integer.parseInt(stuAuthId));
			mp.setState("Y");
			mp.setType_id(2);
			messagePublishDao.add(mp);                     //发布表
			MessageReceive mr = new MessageReceive();
			mr.setProgress_state(0);
			mr.setState("Y");
			mr.setMp_id(mpId);
			//根据学生authId查找出自己的带教老师
			TrainPlan _tp = new TrainPlan();
			_tp.setTrain_dept_id(stuOrgaId);
			_tp.setStu_auth_id(Integer.parseInt(stuAuthId));
			_tp.setState("Y");
			List<TrainPlan> tpList = trainPlanDao.selectList(_tp);
			int teaAuthId = -1;
			if(tpList!=null&&tpList.size()>0&&tpList.get(0).getTeacher_auth_id()>0){
				teaAuthId = tpList.get(0).getTeacher_auth_id();
				mr.setReceiver_auth_id(teaAuthId);
				mr.setId(messageReceiveDao.getSequence());
				messageReceiveDao.add(mr);            //接收表
			}
			rtnJson = "{\"success\":true,"
					+ "\"msg\":\"提交成功！\""
					  + "}";
		}else{
			rtnJson = "{\"success\":false,"
					+ "\"msg\":\"提交失败...\""
					  + "}";
		}
		return rtnJson;
	}
	
	
	
	public String getAttListInfo(String stuAuthId){
		//得到此学生的请假列表
		StuVacateInfo svi = new StuVacateInfo();
		svi.setStu_auth_id(Integer.parseInt(stuAuthId));
		svi.setState("Y");
		List<StuVacateInfo> sviList = stuVacateInfoDao.selectVacateInfo(new RowBounds(),svi);
		//转换一些数据
		for(int i=0;i<sviList.size();i++){
			sviList.get(i).setContent(sviList.get(i).getContent().replace("<p>", "").replace("</p>", "").replace("<br/>", ""));
			sviList.get(i).setVacate_time(sviList.get(i).getStart_time_str()+"~"+sviList.get(i).getEnd_time_str());
			if (sviList.get(i).getVacate_date_num() == -10) {
				sviList.get(i).setVacate_time(sviList.get(i).getStart_time_str());
				sviList.get(i).setVacate_date_num_str("上午");
			}else if(sviList.get(i).getVacate_date_num() == -20){
				sviList.get(i).setVacate_time(sviList.get(i).getStart_time_str());
				sviList.get(i).setVacate_date_num_str("下午");
			}else if(sviList.get(i).getVacate_date_num() == 1){
				sviList.get(i).setVacate_time(sviList.get(i).getStart_time_str());
				sviList.get(i).setVacate_date_num_str("1");
			}else{
				sviList.get(i).setVacate_date_num_str(sviList.get(i).getVacate_date_num().toString());
			}
		}
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"sviList\":[");
		for(int i=0;sviList!=null&&i<sviList.size();i++){
			if(i==sviList.size()-1){
				rtnJson.append("{\"id\":\""+sviList.get(i).getId()+"\",\"vacate_type_name\":\""+sviList.get(i).getVacate_type_name()+
						"\",\"orga_name\":\""+sviList.get(i).getOrga_name()+"\",\"vacate_time\":\""+sviList.get(i).getVacate_time()+
						"\",\"vacate_date_num_str\":\""+sviList.get(i).getVacate_date_num_str()+"\",\"content\":\""+sviList.get(i).getContent()+
						"\",\"proc_name\":\""+sviList.get(i).getProc_name()+"\"}");
			}else{
				rtnJson.append("{\"id\":\""+sviList.get(i).getId()+"\",\"vacate_type_name\":\""+sviList.get(i).getVacate_type_name()+
						"\",\"orga_name\":\""+sviList.get(i).getOrga_name()+"\",\"vacate_time\":\""+sviList.get(i).getVacate_time()+
						"\",\"vacate_date_num_str\":\""+sviList.get(i).getVacate_date_num_str()+"\",\"content\":\""+sviList.get(i).getContent()+
						"\",\"proc_name\":\""+sviList.get(i).getProc_name()+"\"},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	
	public String getAttendInfo(String id){
		String rtnJson = "";
		//根据消息接收表ID查询详情
		StuVacateInfo sv = stuVacateInfoDao.selectOne(Integer.parseInt(id));
		//转换一些数据
		sv.setContent(sv.getContent().replace("<p>", "").replace("</p>", "").replace("<br/>", ""));
		sv.setVacate_time(sv.getStart_time_str()+"~"+sv.getEnd_time_str());
		if (sv.getVacate_date_num() == -10) {
			sv.setVacate_time(sv.getStart_time_str());
			sv.setVacate_date_num_str("上午");
		}else if(sv.getVacate_date_num() == -20){
			sv.setVacate_time(sv.getStart_time_str());
			sv.setVacate_date_num_str("下午");
		}else if(sv.getVacate_date_num() == 1){
			sv.setVacate_time(sv.getStart_time_str());
			sv.setVacate_date_num_str("1");
		}else{
			sv.setVacate_date_num_str(sv.getVacate_date_num().toString());
		}
		if(sv!=null&&sv.getId()!=null){
			//组成流程图样式
			ProcessRecord pd = new ProcessRecord();
			pd.setRelated_id(Integer.parseInt(id));
			pd.setOrderCondition(" id");
			List<ProcessRecord> pdList = processRecordDao.selectList(pd);
			StringBuffer pdListJosn = new StringBuffer();
			for(int i=0;pdList!=null&&i<pdList.size();i++){
				if(i==pdList.size()-1){
					pdListJosn.append("{\"id\":\""+pdList.get(i).getId()+"\",\"end_proc_id\":\""+pdList.get(i).getEnd_proc_id()+
							"\",\"user_name\":\""+pdList.get(i).getUser_name()+"\",\"proc_result\":\""+pdList.get(i).getProc_result()+"\"}");
				}else{
					pdListJosn.append("{\"id\":\""+pdList.get(i).getId()+"\",\"end_proc_id\":\""+pdList.get(i).getEnd_proc_id()+
							"\",\"user_name\":\""+pdList.get(i).getUser_name()+"\",\"proc_result\":\""+pdList.get(i).getProc_result()+"\"},");
				}
			}
			JSONArray json = JSONArray.fromObject(sv);
			rtnJson = "{\"success\":true,"
					  + "\"sv\":"+ json 
					  + ","
					  + "\"pdList\":["+ pdListJosn.toString()+"]"
					  + "}";
		}else{
			rtnJson = "{\"success\":false,"
					  + "\"msg\":\"读取数据出错！\""
					  + "}";
		}
		return rtnJson;
	}
	
	
	public String getStuTpList(String stuAuthId){
		//得到此学生的培训日志列表
		TrainPlan tp = new TrainPlan();
		tp.setStu_auth_id(Integer.parseInt(stuAuthId));
		tp.setState("Y");
		tp.setOrderCondition(" train_start_date");
		List<TrainPlan> tpList = trainPlanDao.selectList(tp);
		
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"tpList\":[");
		for(int i=0;tpList!=null&&i<tpList.size();i++){
			//计算每个科室的大纲吻合度
			//查询完成的百分比
			List<TrainTeachOrder> trainTeachOrderList = trainTeachOrderDao.selectTrainTeachOrderList(tpList.get(i));
			int m = 0;//  计数
			int done_prec_flag = 1; //大纲吻合度背景色
			int status_flag = 1; //轮转状态背景色
			float completion_sum = 0;//  各项完成率相加的和
			for (TrainTeachOrder trainTeachOrder : trainTeachOrderList) {
				if(trainTeachOrder.getOrder_num()!=null){
					m++;
					float completion_rate1 = 0;
					if (trainTeachOrder.getFinnish_num()!=null) {
						completion_rate1 = (float)trainTeachOrder.getFinnish_num()/(float)trainTeachOrder.getOrder_num();
					}
					if(completion_rate1<=1){
						completion_sum=completion_sum+completion_rate1;
					}else{
						completion_sum=completion_sum+1;
					}
				}
			}
			int num =(int)(completion_sum/m*100);
			if(num>=100){
				num = 100;
			}
			//根据判断显示不同的背景色
			int status = tpList.get(i).getTrain_status();
			if(status==51){     //未轮转
				done_prec_flag = -1;
				status_flag = -1;
			}else if(status==52||status==53||status==54||status==55||status==56||status==57){
				if(num==100){
					done_prec_flag = 2;
				}else{
					done_prec_flag = 1;
				}
				status_flag = 1;
			}else if(status==58){
				done_prec_flag = 2;
				status_flag = 2;
			}
			
			if(i==tpList.size()-1){
				rtnJson.append("{\"id\":\""+tpList.get(i).getId()+"\",\"orga_name\":\""+tpList.get(i).getOrga_name()+"\","+
							   "\"done_prec\":\""+num+"%"+"\",\"train_status\":\""+tpList.get(i).getTrain_status_arry()+"\","+
							   "\"duration\":\""+tpList.get(i).getDuration()+tpList.get(i).getDuration_unit()+"\","+
							   "\"train_start_date\":\""+tpList.get(i).getTrain_start_str()+"\","+
							   "\"done_prec_flag\":\""+done_prec_flag+"\","+
							   "\"status_flag\":\""+status_flag+"\","+
							   "\"train_end_date\":\""+tpList.get(i).getTrain_end_str()+
							   "\"}");
			}else{
				rtnJson.append("{\"id\":\""+tpList.get(i).getId()+"\",\"orga_name\":\""+tpList.get(i).getOrga_name()+"\","+
							   "\"done_prec\":\""+num+"%"+"\",\"train_status\":\""+tpList.get(i).getTrain_status_arry()+"\","+
							   "\"duration\":\""+tpList.get(i).getDuration()+tpList.get(i).getDuration_unit()+"\","+
							   "\"train_start_date\":\""+tpList.get(i).getTrain_start_str()+"\","+
							   "\"done_prec_flag\":\""+done_prec_flag+"\","+
							   "\"status_flag\":\""+status_flag+"\","+
							   "\"train_end_date\":\""+tpList.get(i).getTrain_end_str()+
							   "\"},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	public String getStuAttList(String stuAuthId){
		Map<String,Object> map = new HashMap<String, Object>();
		//得到此学生的轮转过与正在轮转的科室列表
		List<TrainPlan> deptList = trainPlanDao.selectOrgaNameList(Integer.parseInt(stuAuthId));
		//取最后一个科室，以及这个科室的考勤信息
		if(deptList!=null&&deptList.size()>0){
			String lastOrgaId = deptList.get(deptList.size()-1).getTrain_dept_id();
			map = stuAttendanceInfoDao.getStuAttListByOrgaId(Integer.parseInt(lastOrgaId), Integer.parseInt(stuAuthId));
		}
		List<StuAttendanceInfo> attList = (List<StuAttendanceInfo>) map.get("attList");
		String sDate = (String) map.get("sDate");
		String eDate = (String) map.get("eDate");
		String attNum = (String) map.get("attNum");
		String attNotNum = (String) map.get("attNotNum");
		String vNum = (String) map.get("vNum");
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"sDate\":\"" + "入科："+ sDate + "\",");
		rtnJson.append("\"eDate\":\"" + "出科："+ eDate + "\",");
		rtnJson.append("\"attNum\":\"" + "到勤："+ attNum + "天" + "\",");
		rtnJson.append("\"attNotNum\":\"" + "缺勤："+ attNotNum + "天" + "\",");
		rtnJson.append("\"vNum\":\"" + "请假："+ vNum + "天" + "\",");
		rtnJson.append("\"deptList\":[");
		for(int i=0;deptList!=null&&i<deptList.size();i++){
			if(i==deptList.size()-1){
				rtnJson.append("{\"deptId\":\""+deptList.get(i).getTrain_dept_id()+"\","+
							   "\"deptName\":\""+deptList.get(i).getOrga_name()+"("+deptList.get(i).getTrain_status_arry()+")"+"\","+
							   "\"trainState\":\""+deptList.get(i).getTrain_status_arry()+"\","+
							   "\"trainStartStr\":\""+deptList.get(i).getTrain_start_str()+"\","+
							   "\"trainEndStr\":\""+deptList.get(i).getTrain_end_str()+"\""+
							   "}");
			}else{
				rtnJson.append("{\"deptId\":\""+deptList.get(i).getTrain_dept_id()+"\","+
						"\"deptName\":\""+deptList.get(i).getOrga_name()+"("+deptList.get(i).getTrain_status_arry()+")"+"\","+
						       "\"trainState\":\""+deptList.get(i).getTrain_status_arry()+"\","+
						       "\"trainStartStr\":\""+deptList.get(i).getTrain_start_str()+"\","+
						       "\"trainEndStr\":\""+deptList.get(i).getTrain_end_str()+"\""+
						       "},");
			}
		}
		rtnJson.append("],");
		rtnJson.append("\"attList\":[");
		for(int i=0;attList!=null&&i<attList.size();i++){
			if(i==attList.size()-1){
				rtnJson.append("{\"attDate\":\""+attList.get(i).getAttend_time_str()+"\","+
							   "\"attState\":\""+attList.get(i).getAttend_state_str()+"\""+
							   "}");
			}else{
				rtnJson.append("{\"attDate\":\""+attList.get(i).getAttend_time_str()+"\","+
						   	   "\"attState\":\""+attList.get(i).getAttend_state_str()+"\""+
						   	   "},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	public String getStuAttListByOrgaId(String stuAuthId,String orgaId){
		Map<String,Object> map = new HashMap<String, Object>();
		map = stuAttendanceInfoDao.getStuAttListByOrgaId(Integer.parseInt(orgaId), Integer.parseInt(stuAuthId));
		List<StuAttendanceInfo> attList = (List<StuAttendanceInfo>) map.get("attList");
		String sDate = (String) map.get("sDate");
		String eDate = (String) map.get("eDate");
		String attNum = (String) map.get("attNum");
		String attNotNum = (String) map.get("attNotNum");
		String vNum = (String) map.get("vNum");
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"sDate\":\"" + "入科："+ sDate + "\",");
		rtnJson.append("\"eDate\":\"" + "出科："+ eDate + "\",");
		rtnJson.append("\"attNum\":\"" + "到勤："+ attNum + "天" + "\",");
		rtnJson.append("\"attNotNum\":\"" + "缺勤："+ attNotNum + "天" + "\",");
		rtnJson.append("\"vNum\":\"" + "请假："+ vNum + "天" + "\",");
		rtnJson.append("\"attList\":[");
		for(int i=0;attList!=null&&i<attList.size();i++){
			if(i==attList.size()-1){
				rtnJson.append("{\"attDate\":\""+attList.get(i).getAttend_time_str()+"\","+
							   "\"attState\":\""+attList.get(i).getAttend_state_str()+"\""+
							   "}");
			}else{
				rtnJson.append("{\"attDate\":\""+attList.get(i).getAttend_time_str()+"\","+
						   	   "\"attState\":\""+attList.get(i).getAttend_state_str()+"\""+
						   	   "},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	public String stuLaunchOutDept(String stuAuthId,String stuOrgaId,String stuName,String stuOrgaName,HttpServletRequest req){
		//更新TRAIN_PLAN表轮转状态
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setStu_auth_id(Integer.parseInt(stuAuthId));
		trainPlan.setTrain_dept_id(stuOrgaId);
		int train_status = Consts.ProcessInfo.TRAINPROCESS2;
		trainPlan.setTrain_status(train_status);
		int num1 = trainPlanDao.updateTrainStatus(trainPlan);
		//往OUTDEPT_RECORD表写入审核记录
		List<ProcessInfo> processList = (List<ProcessInfo>)req.getSession().getServletContext().getAttribute(Consts.SESSION_PROCESS_INFO);
		List<ProcessInfo> processInfoList = new ArrayList<ProcessInfo>();
		for (ProcessInfo processInfo : processList) {
			if(processInfo.getType_code().equals("TRAINPROCESS") && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESSNull && 
					processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS0 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS1 && 
					processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS6 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS2){
				processInfoList.add(processInfo);
			}
		}
		Collections.sort(processInfoList, new Comparator<ProcessInfo>() {
	          public int compare(ProcessInfo arg0, ProcessInfo arg1) {
		          Integer code0 = arg0.getProc_num();
		          Integer code1 = arg1.getProc_num();
		          return code0.compareTo(code1);
	          }
		});
		//获取组次
		OutdeptRecord ord=new OutdeptRecord(); 
		ord.setStu_auth_id(Integer.parseInt(stuAuthId));
		ord.setOrga_id(Integer.parseInt(stuOrgaId));
		ord.setOrderCondition(" pub_num desc");
		OutdeptRecord outdeptRecordGet = outdeptRecordDao.getLastPubNum(ord);//  获取在本科室学生最后一次发起出科的组次
		Integer pub_num = 0;
		if (outdeptRecordGet != null) {
			pub_num = outdeptRecordGet.getPub_num() + 1;
		}else {
			pub_num = 1;
		}
		//生成学生发起出科的出科审核记录
		OutdeptRecord outdeptRecord = new OutdeptRecord();
		int or_id = outdeptRecordDao.getSequence();
		outdeptRecord.setId(or_id);
		outdeptRecord.setStu_auth_id(Integer.parseInt(stuAuthId));
		outdeptRecord.setExam_auth_id(Integer.parseInt(stuAuthId));
		outdeptRecord.setExam_datetime(new Timestamp((new Date()).getTime()));
		outdeptRecord.setOrga_id(Integer.parseInt(stuOrgaId));
		outdeptRecord.setExam_content("申请出科");
		outdeptRecord.setExam_role_id(10);//  10
		outdeptRecord.setState("Y");
		outdeptRecord.setPub_num(pub_num);
		outdeptRecord.setExam_result(1);
		int num2 = outdeptRecordDao.add(outdeptRecord);
		//根据轮转流程配置生成出科审核轨迹
		for (ProcessInfo processInfo : processInfoList) {
			OutdeptRecord or = new OutdeptRecord();
			or.setId(outdeptRecordDao.getSequence());
			or.setStu_auth_id(Integer.parseInt(stuAuthId));
			if (processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS3) {//  默认是老师角色，等到教秘审核时改为30
				or.setExam_role_id(20);
			}else if (processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS4) {
				or.setExam_role_id(40);
			}else if (processInfo.getId() == Consts.ProcessInfo.TRAINPROCESS5) {
				or.setExam_role_id(50);
			}
			or.setOrga_id(Integer.parseInt(stuOrgaId));
			or.setState("Y");
			or.setPub_num(pub_num);
			outdeptRecordDao.add(or);
		}
		
		//组成返回前台的json格式
		String rtnJson = "";
		if(num1>0&&num2>0){
			// 往消息发布表插入数据（MESSAGE_PUBLISH）
			MessagePublish mp = new MessagePublish();
			int mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			String title = stuName  + "【" + stuOrgaName + "】" +  "发起了出科申请，请及时审批！";
			mp.setTitle(title);
			mp.setContent("申请出科");
			mp.setSender_auth_id(Integer.parseInt(stuAuthId));
			Date date = new Date();
			Timestamp create_time = new Timestamp((date.getTime()));
			mp.setSend_time(create_time);
			mp.setType_id(2);
			mp.setRelated_table("MESSAGE_RECEIVE");
			mp.setState("Y");
			messagePublishDao.add(mp);
			// 往消息接收表插入数据（MESSAGE_RECEIVE）
			MessageReceive mr = new MessageReceive();
			mr.setProgress_state(0);
			mr.setMp_id(mpId);
			mr.setState("Y");
			@SuppressWarnings("unchecked")
			List<ProcessInfo> processSelList = new ArrayList<ProcessInfo>();
			for (ProcessInfo processInfo : processList) {
				if(processInfo.getType_code().equals("TRAINPROCESS") && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESSNull && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS0 && 
						processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS1 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS2 && processInfo.getId() != Consts.ProcessInfo.TRAINPROCESS6){
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
			List<UserAuthority> userAuthorityList = new ArrayList<UserAuthority>();
			UserAuthority userAuthority = new UserAuthority();
			userAuthority.setOrga_id(Integer.parseInt(stuOrgaId));
			userAuthority.setState("Y");
			for (ProcessInfo pio : processSelList) {
				if (pio.getId() == Consts.ProcessInfo.TRAINPROCESS3) {
					TrainPlan tp = new TrainPlan();
					tp.setState("Y");
					tp.setStu_auth_id(Integer.parseInt(stuAuthId));
					tp.setTrain_dept_id(stuOrgaId);
					List<TrainPlan> list = trainPlanDao.selectList(tp);
					if (list != null && !list.isEmpty() && list.get(0).getTeacher_auth_id() != null) {
						int teacher_auth_id = list.get(0).getTeacher_auth_id();
						int mrId=messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(teacher_auth_id);
						messageReceiveDao.add(mr); 
					}
					userAuthority.setRole_id(30);
					userAuthorityList = userAuthorityDao.selectList(userAuthority);
					for (UserAuthority userAuthorityGet : userAuthorityList) {
						int mrId=messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(userAuthorityGet.getAuth_id());
						messageReceiveDao.add(mr); 
					}
					break;
				}else if (pio.getId() == Consts.ProcessInfo.TRAINPROCESS4) {
					userAuthority.setRole_id(40);
					userAuthorityList = userAuthorityDao.selectList(userAuthority);
					for (UserAuthority userAuthorityGet : userAuthorityList) {
						int mrId=messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(userAuthorityGet.getAuth_id());
						messageReceiveDao.add(mr); 
					}
					break;
				}else if (pio.getId() == Consts.ProcessInfo.TRAINPROCESS5) {
					userAuthority.setRole_id(50);
					userAuthorityList = userAuthorityDao.selectList(userAuthority);
					for (UserAuthority userAuthorityGet : userAuthorityList) {
						int mrId=messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(userAuthorityGet.getAuth_id());
						messageReceiveDao.add(mr); 
					}
					break;
				}
			}
			rtnJson = "{\"success\":true," + "\"msg\":\"提交成功！\""
					  + "}";
		}else{
			rtnJson = "{\"success\":false,"
					  + "\"msg\":\"数据提交出错...\""
					  + "}";
		}
		return rtnJson;
	}
	
	public String stuOutDept(String stuAuthId){
		//得到此学生的轮转过与正在轮转的科室列表
		List<TrainPlan> deptList = trainPlanDao.selectOrgaNameList(Integer.parseInt(stuAuthId));
		//取最后一个科室，以及这个科室的考勤信息
		String lastOrgaId = "";
		if(deptList!=null&&deptList.size()>0){
			lastOrgaId = deptList.get(deptList.size()-1).getTrain_dept_id();
		}
		//根据学生authId查询出基本信息
		UserAuthority ua = userAuthorityDao.selectOneNameByAuth(Integer.parseInt(stuAuthId));
		//最新的科室轮转时长
		String trainDate = "";
		String teaName = "";
		String trainStatus = "";
		TrainPlan _tp = new TrainPlan();
		_tp.setStu_auth_id(Integer.parseInt(stuAuthId));
		_tp.setTrain_dept_id(lastOrgaId+"");
		_tp.setState("Y");
		List<TrainPlan> tpList = trainPlanDao.selectList(_tp);
		if(tpList!=null&&tpList.size()>0){
			trainStatus = tpList.get(0).getTrainStatus();
			teaName = tpList.get(0).getTeach_name();
			trainDate = tpList.get(0).getTrain_start_str()+" ~ "+tpList.get(0).getTrain_end_str();
		}else{
			return "{\"success\":false,"
					  + "\"msg\":\"未读到轮转科室信息！\""
					  + "}";
		}
		//查询该科室这个学生处在什么状态
		int odFlag = -1; //是否发起过出科申请
		int examFlag = -1; //是否审核通过
		int theorySco = -1; //理论考试分数
		int skillSco = -1;  //技能考试分数
		List<StuOutDeptRecord> sodrList = new ArrayList<StuOutDeptRecord>();
		if("53".equals(trainStatus)){ //学习中状态
			odFlag = -1;
		}else{ //肯定是待出科（出科审核中）或已出科
			odFlag = 1;
			//查询审核流程图
			OutdeptRecord or = new OutdeptRecord();
			or.setStu_auth_id(Integer.parseInt(stuAuthId));
			or.setOrga_id(Integer.parseInt(lastOrgaId));
			or.setState("Y");
			or.setOrderCondition(" o.pub_num,o.exam_role_id");
			List<OutdeptRecord> orList = outdeptRecordDao.selectFlowList(or);
			//查看最后一条记录看申请了几次，只要取最后的一次
			if(orList!=null&&orList.size()>0){
				int pubNum = orList.get(orList.size()-1).getPub_num();
				for(int i=0;i<orList.size();i++){
					if(orList.get(i).getPub_num()==pubNum){ //是这个组次的
						StuOutDeptRecord sodr = new StuOutDeptRecord();
						//看是否这条审核记录被驳回了，是驳回了就跳出循环
						if(orList.get(i).getExam_result()==1||orList.get(i).getExam_result()==-1){
							if(orList.get(i).getExam_role_id()==10){ //是学生申请
								sodr.setId(orList.get(i).getId());
								sodr.setUserRoleId(orList.get(i).getExam_role_id());
								sodr.setExamResult(orList.get(i).getExam_result());
								sodr.setCreateDateTime("申请时间："+orList.get(i).getDateTimeStr()+" "+orList.get(i).getDateTimeStrEnd());
								sodr.setExamContent1("学生申请："+orList.get(i).getUserName()+"发起出科申请");
							}else if(orList.get(i).getExam_role_id()==20||orList.get(i).getExam_role_id()==30){ //带教老师或教学秘书审批
								sodr.setId(orList.get(i).getId());
								sodr.setUserRoleId(orList.get(i).getExam_role_id());
								sodr.setExamResult(orList.get(i).getExam_result());
								sodr.setCreateDateTime("审批时间："+orList.get(i).getDateTimeStr()+" "+orList.get(i).getDateTimeStrEnd());
								sodr.setExamContent1("老师/教秘审核："+orList.get(i).getUserName()+"（"+orList.get(i).getRoleName()+"）");
								//记录考试成绩
								if(orList.get(i).getTheory_sco()!=null&&orList.get(i).getSkill_sco()!=null){
									theorySco = orList.get(i).getTheory_sco();
									skillSco = orList.get(i).getSkill_sco();
									sodr.setExamContent3("理论分数："+theorySco+"分，技能分数："+skillSco+"分");
								}
								if(orList.get(i).getExam_result()>0){
									sodr.setExamContent2("审核意见：通过！");
									examFlag = 1;
								}else{
									sodr.setExamContent2("审核意见：申请驳回！");
									examFlag = 0;
								}
							}else if(orList.get(i).getExam_role_id()==40){ //科主任审批
								sodr.setId(orList.get(i).getId());
								sodr.setUserRoleId(orList.get(i).getExam_role_id());
								sodr.setExamResult(orList.get(i).getExam_result());
								sodr.setCreateDateTime("审批时间："+orList.get(i).getDateTimeStr()+" "+orList.get(i).getDateTimeStrEnd());
								sodr.setExamContent1("科主任审核："+orList.get(i).getUserName()+"（"+orList.get(i).getRoleName()+"）");
								if(orList.get(i).getExam_result()>0){
									sodr.setExamContent2("审核意见：通过！");
									examFlag = 1;
								}else{
									sodr.setExamContent2("审核意见：申请驳回！");
									examFlag = 0;
								}
							}else if(orList.get(i).getExam_role_id()==50){ //科教科审批
								sodr.setId(orList.get(i).getId());
								sodr.setUserRoleId(orList.get(i).getExam_role_id());
								sodr.setExamResult(orList.get(i).getExam_result());
								sodr.setCreateDateTime("审批时间："+orList.get(i).getDateTimeStr()+" "+orList.get(i).getDateTimeStrEnd());
								sodr.setExamContent1("科教科审核："+orList.get(i).getUserName()+"（"+orList.get(i).getRoleName()+"）");
								if(orList.get(i).getExam_result()>0){
									sodr.setExamContent2("审核意见：通过！");
									examFlag = 1;
								}else{
									sodr.setExamContent2("审核意见：申请驳回！");
									examFlag = 0;
								}
							}
							sodrList.add(sodr);
						}
					}
				}
			}else{ //还未发起过出科申请
				odFlag = -1;
			}
		}
		//组成返回前台的json格式
		StringBuffer rtnJson = new StringBuffer();
		rtnJson.append("{\"success\":true,");
		rtnJson.append("\"stuName\":\"" + ua.getName() + "\",");
		rtnJson.append("\"teaName\":\"" + teaName + "\",");
		rtnJson.append("\"trainDate\":\"" + trainDate + "\",");
		rtnJson.append("\"deptList\":[");
		for(int i=0;deptList!=null&&i<deptList.size();i++){
			if(i==deptList.size()-1){
				rtnJson.append("{\"deptId\":\""+deptList.get(i).getTrain_dept_id()+"\","+
							   "\"deptName\":\""+deptList.get(i).getOrga_name()+"("+deptList.get(i).getTrain_status_arry()+")"+"\","+
							   "\"trainState\":\""+deptList.get(i).getTrain_status_arry()+"\","+
							   "\"trainStartStr\":\""+deptList.get(i).getTrain_start_str()+"\","+
							   "\"trainEndStr\":\""+deptList.get(i).getTrain_end_str()+"\""+
							   "}");
			}else{
				rtnJson.append("{\"deptId\":\""+deptList.get(i).getTrain_dept_id()+"\","+
							   "\"deptName\":\""+deptList.get(i).getOrga_name()+"("+deptList.get(i).getTrain_status_arry()+")"+"\","+
						       "\"trainState\":\""+deptList.get(i).getTrain_status_arry()+"\","+
						       "\"trainStartStr\":\""+deptList.get(i).getTrain_start_str()+"\","+
						       "\"trainEndStr\":\""+deptList.get(i).getTrain_end_str()+"\""+
						       "},");
			}
		}
		rtnJson.append("],");
		rtnJson.append("\"odFlag\":" + odFlag + ",");
		rtnJson.append("\"examFlag\":" + examFlag + ",");
		rtnJson.append("\"theorySco\":" + theorySco + ",");
		rtnJson.append("\"skillSco\":" + skillSco + ",");
		rtnJson.append("\"sodrList\":[");
		for(int i=0;sodrList!=null&&i<sodrList.size();i++){
			if(i==sodrList.size()-1){
				rtnJson.append("{\"id\":"+sodrList.get(i).getId()+","+
						       "\"userRoleId\":"+sodrList.get(i).getUserRoleId()+","+
							   "\"examResult\":\""+sodrList.get(i).getExamResult()+"\","+
							   "\"examDatetime\":\""+sodrList.get(i).getCreateDateTime()+"\","+
							   "\"examContent1\":\""+sodrList.get(i).getExamContent1()+"\","+
							   "\"examContent2\":\""+sodrList.get(i).getExamContent2()+"\","+
							   "\"examContent3\":\""+sodrList.get(i).getExamContent3()+"\""+
							   "}");
			}else{
				rtnJson.append("{\"id\":"+sodrList.get(i).getId()+","+
					           "\"userRoleId\":"+sodrList.get(i).getUserRoleId()+","+
							   "\"examResult\":\""+sodrList.get(i).getExamResult()+"\","+
						   	   "\"examDatetime\":\""+sodrList.get(i).getCreateDateTime()+"\","+
						   	   "\"examContent1\":\""+sodrList.get(i).getExamContent1()+"\","+
						       "\"examContent2\":\""+sodrList.get(i).getExamContent2()+"\","+
						       "\"examContent3\":\""+sodrList.get(i).getExamContent3()+"\""+
						       "},");
			}
		}
		rtnJson.append("]");
		rtnJson.append("}");
		return rtnJson.toString();
	}
	
	
	
}





