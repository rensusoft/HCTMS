package com.rensu.education.hctms.userauth.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.config.bean.SysConfig;
import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.TrainTeachOrderDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.teach.bean.StuActiveData;
import com.rensu.education.hctms.teach.bean.StuTeachOrder;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuActiveDataDao;
import com.rensu.education.hctms.teach.dao.StuTeachOrderDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.dao.StudentInfoDao;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.userauth.dao.UserInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;
import com.rensu.education.hctms.util.StringUtil;


@Service("studentInfoService")
public class StudentInfoService extends BaseService<StudentInfo> {
	
	Logger log = Logger.getLogger(StudentInfoService.class);
	
	@Autowired
	protected StudentInfoDao studentInfoDao;
	@Autowired
	protected UserInfoDao userInfoDao;
	@Autowired
	protected TrainPlanDao tainPlanDao;
	@Autowired
	protected StuActiveDataDao  stuActiveDataDao;
	@Autowired
	protected StuTeachOrderDao  stuTeachOrderDao;
	@Autowired
	protected TrainTeachOrderDao  trainTeachOrderDao;
	@Autowired
	protected TrainPlanDao  trainPlanDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	
	

	public StudentInfo selectOneByUserCode(String user_code) {
		return studentInfoDao.selectOneByUserCode(user_code);
	}
	
	public List<StudentInfo> selectStuList(StudentInfo student) {
		return studentInfoDao.selectStuList(student);
	}
	
	public List<StudentInfo> selectStudentList(StudentInfo student) {
	    return studentInfoDao.selectStudentList(student);
	}

	public Map<String, Object> getStuMessageById(HttpServletRequest req) {
		Map<String, Object> map=new HashMap<String, Object>();
		String userCode=req.getParameter("userCode");
		String _kind=req.getParameter("_kind");
		StudentInfo stu=studentInfoDao.selectOneByUserCode(userCode);    //学生的个人信息
//		if(stu.getStu_birthday()!=null&&stu.getStu_birthday().length()>0){
//			BigDecimal db=new BigDecimal(stu.getStu_birthday());
//			stu.setStu_birthday(db.toPlainString());
//		}
//		if(stu.getStu_num()!=null&&stu.getStu_num().length()>0)
//		{
//		BigDecimal db1=new BigDecimal(stu.getStu_num());
//		stu.setStu_num(db1.toPlainString());
//		}
		map.put("stuInfo", stu);
		
		//从缓存中拿到用户当前所在的科室
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id=loginBean.getvUserDetailInfo().getOrga_id();  
		//从缓存中拿到当前科室名字；
		String orga_name=loginBean.getvUserDetailInfo().getOrga_name(); 
//		@SuppressWarnings("unchecked")
//		List<OrgaInfo> Orgalist = (List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
//		for (int i = 0; i < Orgalist.size(); i++) {
//			if(Orgalist.get(i).getOrga_id()==orga_id){
//				map.put("orga", Orgalist.get(i).getOrga_name());
//			}
//		}
		map.put("orga", orga_name);
		UserInfo userInfo= new UserInfo();
		userInfo.setOrga_id(orga_id);
		userInfo.setRole_id(20);
		List<UserInfo>  teachingteachers=userInfoDao.selectTeachersList(userInfo);   //带教老师的信息
		map.put("teacherList", teachingteachers);
		map.put("success", true);
		
		Integer stu_auth_id=Integer.parseInt(req.getParameter("stu_auth_id"));
		TrainPlan trainPlan=new TrainPlan();
		trainPlan.setStu_auth_id(stu_auth_id);
		trainPlan.setTrain_dept_id(orga_id.toString());
		if (_kind != null && _kind.equals("1")) {
			trainPlan.setTrain_status(Consts.ProcessInfo.TRAINPROCESS0);
		}else if (_kind != null && _kind.equals("-1")) {
			trainPlan.setTrain_status(Consts.ProcessInfo.TRAINPROCESSNull);
		}
		TrainPlan train=tainPlanDao.selectOneTrain(trainPlan);
		map.put("train", train);
		return map;
	}

	public int changStatus(HttpServletRequest req) {
		//从缓存中拿到用户当前所在的科室
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id=loginBean.getvUserDetailInfo().getOrga_id();  
		Integer stu_auth_id=Integer.parseInt(req.getParameter("stu_auth_id"));
		TrainPlan trainPlan=new TrainPlan();
		trainPlan.setStu_auth_id(stu_auth_id);
		trainPlan.setTrain_dept_id(orga_id.toString());
		trainPlan.setTrain_status(53);
		
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		trainPlan.setDept_receive_date(d);
		
		
		int i =tainPlanDao.updateTrainStatus(trainPlan);
		return i;
	}
	
	
	public JSONObject selectPage(int pageIndex, int rows, StudentInfo student){
		JSONObject jobj = new JSONObject();
		student.setOrderCondition("t.id asc");
		List<StudentInfo> dataList = studentInfoDao.selectPageWithStuType(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows), student);
		int totalCount = dataList.size();
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}
	
	public int selectStuCountByState(StudentInfo student){
	    int totalCount = studentInfoDao.selectCount(student);
	    return totalCount;
	}
	
	public int getStuPendingCount(HttpServletRequest req) {
		//从缓存中拿到用户当前所在的科室
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id=loginBean.getvUserDetailInfo().getOrga_id();  
		StuActiveData stuActiveData=new StuActiveData();
		stuActiveData.setDept_id(orga_id);
		Integer   stuAuthId=Integer.parseInt(req.getParameter("stuAuthId"));
		stuActiveData.setStu_auth_id(stuAuthId);
		stuActiveData.setExamine_state(0);;
		int i=stuActiveDataDao.selectCount(stuActiveData);
		return i;
	}

	public List<StuActiveData> getStuInputById(HttpServletRequest req) {
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id=loginBean.getvUserDetailInfo().getOrga_id();  
		StuActiveData stuActiveData=new StuActiveData();
		stuActiveData.setDept_id(orga_id);
		Integer stu_auth_id=Integer.parseInt(req.getParameter("stuAuthId"));
		stuActiveData.setStu_auth_id(stu_auth_id);
		stuActiveData.setExamine_state(0);
		
		//查询当前学生当前科室 提交的数据
		List<StuActiveData> lists=stuActiveDataDao.getStuInput(stuActiveData);
		for (int i = 0; i < lists.size(); i++) {
			if(lists.get(i).getmOrder_name()!=null&&lists.get(i).getmOrder_name()!=""){
				lists.get(i).setOrder_name(lists.get(i).getmOrder_name()+"["+lists.get(i).getOrder_name()+"]");
			}
		}
		return lists;
	}

	public String getActiveDataById(HttpServletRequest req) {
		Integer id=Integer.parseInt(req.getParameter("id"));
		StuActiveData stuActiveData=stuActiveDataDao.selectOne(id);
		return stuActiveData.getContent();
	}

	public List<StudentInfo> countStatusList(StudentInfo studentInfo) {
		return studentInfoDao.countStatusList(studentInfo);
	}
	public int updateStuInfomation(HttpServletRequest req) {
		StudentInfo studentInfo=new StudentInfo();
		String userCode=req.getParameter("userCode");
		studentInfo.setUser_code(userCode);
		String stu_sex=req.getParameter("stu_sex");
		studentInfo.setStu_sex(stu_sex);
		String stu_age=req.getParameter("stu_age");
		if(stu_age!=""||stu_age!=null){
		studentInfo.setStu_age(Integer.parseInt(stu_age));
		}
		String stu_birthday=req.getParameter("stu_birthday");
		studentInfo.setStu_birthday(stu_birthday);
		String stu_phone=req.getParameter("stu_phone");
		studentInfo.setStu_phone(stu_phone);
		String stu_country=req.getParameter("stu_country");
		studentInfo.setStu_country(stu_country);;
		String stu_native=req.getParameter("stu_native");
		studentInfo.setStu_native(stu_native);
		String stu_nationality=req.getParameter("stu_nationality");
		studentInfo.setStu_nationality(stu_nationality);
		String stu_hk_address=req.getParameter("stu_hk_address");
		studentInfo.setStu_hk_address(stu_hk_address);
		String stu_home_address=req.getParameter("stu_home_address");
		studentInfo.setStu_home_address(stu_home_address);
		String political_status=req.getParameter("political_status");
		studentInfo.setPolitical_status(political_status);
		String interesting_speciality=req.getParameter("interesting_speciality");
		studentInfo.setInteresting_speciality(interesting_speciality);
		
		int i=studentInfoDao.updateStuInfomation(studentInfo);
		return i;
	}

	/***
	 * 学生综合查询页面展示
	 * @param req
	 * @return
	 * @author hezx
	 * @throws ParseException 
	 * @date 2017年5月5日
	 */
	public Object selectPageInfo(HttpServletRequest req) throws ParseException {
		String stu_name= req.getParameter("name");
		String stu_type= req.getParameter("stu_type");
		String orga_id= req.getParameter("orga_id");
		 String attend_state = "0,2";
		//从缓存中拿取数据
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer role_id=loginBean.getvUserDetailInfo().getRole_id();  
		Integer auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		Integer orgaId =0;
		if(orga_id!=null&&orga_id!=""){
			orgaId=Integer.parseInt(orga_id);
		}else{
			orgaId=loginBean.getvUserDetailInfo().getOrga_id();
		}
		// 获取当前时间
		Date date = new Date();       
		Date now = new Date(); 
		StudentInfo studentInfo = new StudentInfo();
		studentInfo.setStu_name(stu_name);
		studentInfo.setStu_type(stu_type);
		studentInfo.setTrain_dept_id(orgaId);
		if(role_id==20){
			studentInfo.setTeacher_auth_id(auth_id);
		}else if(role_id==30){
			studentInfo.setSecretary_auth_id(auth_id);
		}else if(role_id==40){
			studentInfo.setDirector_auth_id(auth_id);
		}
		//在科
		String ztrainStatusStr = Consts.ProcessInfo.TRAINPROCESZD + "";
		studentInfo.setZtrainStatusStr(ztrainStatusStr);
		studentInfo.setAttend_state(attend_state);
		//状态为Y
	    String state = Consts.STATUS_Y;
	    studentInfo.setState(state);
	    studentInfo.setAttend_state_b("-10,-20");
		List<StudentInfo> stuList= studentInfoDao.selectPageInfo(studentInfo);
		for (StudentInfo pageInfo : stuList) {
			Integer stu_auth_id = pageInfo.getStu_auth_id();
			TrainPlan trainPlan = new TrainPlan();
			trainPlan.setState("Y");
			trainPlan.setTrain_dept_id(orgaId.toString());
			trainPlan.setStu_auth_id(stu_auth_id);
			List<TrainPlan> trainPlanList = trainPlanDao.selectList(trainPlan);
			float completion_sum =0;//  各项完成率相加的和
			int sumOrder=0;//  需要完成的份数
			int outlineCom=0;//  返回的值
			if(trainPlanList.get(0).getTpc_id()!=null){
				int tpcId = trainPlanList.get(0).getTpc_id();
				TrainTeachOrder trainTeachOrder= new TrainTeachOrder();
				trainTeachOrder.setTpc_id(tpcId);
				trainTeachOrder.setState("Y");
				List<TrainTeachOrder> trainTeachOrderList=trainTeachOrderDao.selectList(trainTeachOrder);
				// 计算完成率
				for (TrainTeachOrder _menu : trainTeachOrderList) {
					int ttoId=_menu.getId();
					//根据 stu_auth_id 和 科室ID加载 train_teach_order的数据
					StuTeachOrder stuTeachOrder = new StuTeachOrder();
					stuTeachOrder.setStu_auth_id(stu_auth_id);
					stuTeachOrder.setDept_id(orgaId);
					stuTeachOrder.setTto_id(ttoId);
					stuTeachOrder.setState("Y");
					List<StuTeachOrder>  stuTeachOrderList=  stuTeachOrderDao.selectList(stuTeachOrder);
					if(stuTeachOrderList.size()>0&&stuTeachOrderList.get(0).getFinnish_num()!=null){
						_menu.setFinnish_num(stuTeachOrderList.get(0).getFinnish_num());
					}else{
						_menu.setFinnish_num(0);
					}
					// 计算完成率
					if (_menu.getOrder_num() != null && _menu.getOrder_num() != 0) {
						sumOrder++;
						float completion_rate1 = (float) _menu.getFinnish_num()/ (float) _menu.getOrder_num();
						if (completion_rate1 <= 1) {
							completion_sum = completion_sum + completion_rate1;
						} else {
							completion_sum = completion_sum + 1;
						}
					}
				}
			}
			int num =0;
			if(sumOrder!=0){
				num=(int) (completion_sum / sumOrder * 100);
			}
            if(num>100){
                outlineCom = 100;
            }else{
                outlineCom = num;
            }
            pageInfo.setOutlineCom(outlineCom);
			Timestamp ts = pageInfo.getTrain_end_time();   
	            date = ts; 
	            int number =   DateUtil.daysBetween(now,date);
			if(number>0){
				pageInfo.setOver_day(number);
			}else{
				pageInfo.setOver_day(0);
			}
		}
		return stuList;
	}

	/***
	 * 查询缺勤学生信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年5月11日
	 */
	public Object absenceStuInfo(HttpServletRequest req) {		 
		  String  orga_id=req.getParameter("orga_id");
		  StudentInfo studentInfo = new StudentInfo();
		  //在科
		  String ztrainStatusStr = Consts.ProcessInfo.TRAINPROCESSInAll + "";
		  String attend_state = "0,2,-10,-20";
		  if(orga_id!=null&&orga_id!="")
			  studentInfo.setTrain_dept_id(Integer.parseInt(orga_id));
		  String state = Consts.STATUS_Y;
		  studentInfo.setState(state);
		  studentInfo.setAttend_state(attend_state);
		  studentInfo.setZtrainStatusStr(ztrainStatusStr);
		  List<StudentInfo> stuInfoList=studentInfoDao.absenceStuInfo(studentInfo);
		return stuInfoList;
	}

	/***
	 * 查询请假学生信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年5月11日
	 */
	public Object leaveStuInfo(HttpServletRequest req) {
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id=loginBean.getvUserDetailInfo().getOrga_id();
		  StudentInfo studentInfo = new StudentInfo();
		  //在科
		  String ztrainStatusStr = Consts.ProcessInfo.TRAINPROCESSInAll + "";
		  String attend_state = "2,-10,-20";
		  if(orga_id!=null)
		  studentInfo.setTrain_dept_id(orga_id);
		  String state = Consts.STATUS_Y;
		  studentInfo.setState(state);
		  studentInfo.setAttend_state(attend_state);
		  studentInfo.setZtrainStatusStr(ztrainStatusStr);
		  List<StudentInfo> stuInfoList=studentInfoDao.absenceStuInfo(studentInfo);
		return stuInfoList;
	}

	public List<StudentInfo> selectListStu(StudentInfo student) {
		return studentInfoDao.selectListStu(student);
	}

	public StudentInfo selectStuNameByAuthId(Integer auth_id) {
		return studentInfoDao.selectStuNameByAuthId(auth_id);
	}

	public boolean deleteStu(HttpServletRequest req) {
		String id=req.getParameter("id");
		int num1=0;
		int num2=0;
		int num3=0;
		if(StringUtil.isNotEmpty(id)){
			StudentInfo studentInfo=new StudentInfo();
			studentInfo=studentInfoDao.selectUserAndAuth(Integer.parseInt(id));
			StudentInfo info= new StudentInfo();
			info.setState("X");
			info.setId(studentInfo.getId());
			num1=studentInfoDao.update(info);
			UserInfo userInfo =new UserInfo();
			userInfo.setUser_id(studentInfo.getUserId());
			userInfo.setState("X");
			num2=userInfoDao.update(userInfo);
			UserAuthority authority=new UserAuthority();
			authority.setState("X");
			authority.setAuth_id(studentInfo.getStu_auth_id());
			num3=userAuthorityDao.update(authority);
		}
		if(num1>0&&num2>0&&num3>0){
			return true;
		}else{
			 return false;
		}
	}

	public Boolean stuSave(HttpServletRequest req) throws Exception {
		String stu_class=req.getParameter("stu_class");	
		String user_code=req.getParameter("userCode");		
		String user_name=req.getParameter("userName");
		String mobile=req.getParameter("mobile");
		String stu_type_id=req.getParameter("stu_type_id");
		String stu_sex=req.getParameter("stu_sex");
		String stu_age=req.getParameter("stu_age");
		String stu_birthday=req.getParameter("stu_birthday");
		String stu_country=req.getParameter("stu_country");
		String stu_nationality=req.getParameter("stu_nationality");
		String stu_native=req.getParameter("stu_native");
		String stu_hk_address=req.getParameter("stu_hk_address");
		String stu_home_address=req.getParameter("stu_home_address");
		String political_status=req.getParameter("political_status");
		String interesting_speciality=req.getParameter("interesting_speciality");
		String stu_school_name=req.getParameter("stu_school_name");
		String stu_num=req.getParameter("stu_num");
		String stu_status=req.getParameter("stu_status");
		String stu_position=req.getParameter("stu_position");
		String stu_edu_level=req.getParameter("stu_edu_level");
		String stu_major_name=req.getParameter("stu_major_name");
		String stu_major_direction=req.getParameter("stu_major_direction");
		String sup_doc_name=req.getParameter("sup_doc_name");
		String tutor_name=req.getParameter("tutor_name");
		String tutor_workplace=req.getParameter("tutor_workplace");
		String tsc_id=req.getParameter("tsc_id");
		Timestamp d = new Timestamp(System.currentTimeMillis());
		
		StudentInfo student=new StudentInfo();
		student.setId(studentInfoDao.getSequence());
		student.setUser_code(user_code);
		student.setStu_name(user_name);
		student.setStu_sex(stu_sex);
		if(StringUtil.isNotEmpty(stu_age)){
			student.setStu_age(Integer.parseInt(stu_age));
		}
		student.setStu_school_name(stu_school_name);
		student.setStu_num(stu_num);
		student.setStu_edu_level(stu_edu_level);
		student.setStu_major_name(stu_major_name);
		student.setStu_type(stu_type_id);
		student.setSup_doc_name(sup_doc_name);
		student.setState("N");
		student.setCreate_time(d);
		student.setStu_birthday(stu_birthday);
		student.setStu_country(stu_country);
		student.setStu_nationality(stu_nationality);
		student.setStu_native(stu_native);
		student.setStu_phone(mobile);
		student.setStu_hk_address(stu_hk_address);
		student.setStu_home_address(stu_home_address);
		student.setTsc_id(Integer.parseInt(tsc_id));
		if(StringUtil.isNotEmpty(stu_class)){
			student.setStu_class(Integer.parseInt(stu_class));
		}
		student.setPolitical_status(political_status);
		student.setInteresting_speciality(interesting_speciality);
		student.setStu_position(stu_position);
		student.setStu_major_direction(stu_major_direction);
		student.setTutor_workplace(tutor_workplace);
		student.setTutor_name(tutor_name);
		int num1=studentInfoDao.add(student);
		UserInfo info = new UserInfo();
		Integer userId=userInfoDao.getSequence();
		info.setUser_id(userId);
		info.setUser_code(user_code);
		info.setUser_password(StringUtil.shaEncodeSignature(StringUtil.md5EncodeSignature("123")));
		info.setUser_name(user_name);
		info.setMobile(mobile);
		info.setState("N");
		info.setCreate_time(d);
		int num2=userInfoDao.add(info);
		
		UserAuthority userAuthority =new UserAuthority();
		userAuthority.setAuth_id(userAuthorityDao.getSequence());
		userAuthority.setUser_id(userId);
		userAuthority.setRole_id(10);
		userAuthority.setState("N");
		userAuthority.setCreate_time(d);
		if(StringUtil.isNotEmpty(stu_type_id)){
			userAuthority.setStu_type_id(Integer.parseInt(stu_type_id));
		}
		int num3=userAuthorityDao.add(userAuthority);
		if(num1>0&&num2>0&&num3>0){
			return true;
		}else{
			 return false;
		}
	   
	}

	public boolean selectUserCode(HttpServletRequest req) {
		String userCode=req.getParameter("userCode");
		int num=0;
		if(StringUtil.isNotEmpty(userCode)){
			StudentInfo info=new StudentInfo();
			info.setUser_code(userCode);
			info.setQueryCondition("and state !='X'");
			num=studentInfoDao.selectCount(info);
		}
		if(num>0){
			return true;
		}else{
			return false;
		}
	}
}
