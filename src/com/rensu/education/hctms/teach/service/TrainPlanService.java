package com.rensu.education.hctms.teach.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.config.bean.SysConfig;
import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.SysConfigDao;
import com.rensu.education.hctms.config.dao.TrainTeachOrderDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.OutdeptRecord;
import com.rensu.education.hctms.log.dao.OutdeptRecordDao;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.score.bean.ScoFormMain;
import com.rensu.education.hctms.score.dao.ScoFormMainDao;
import com.rensu.education.hctms.teach.bean.StuActiveData;
import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;
import com.rensu.education.hctms.teach.bean.StuTeachOrder;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuActiveDataDao;
import com.rensu.education.hctms.teach.dao.StuAttendanceInfoDao;
import com.rensu.education.hctms.teach.dao.StuTeachOrderDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.dao.OrgaInfoDao;
import com.rensu.education.hctms.userauth.dao.StudentInfoDao;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;
import com.rensu.education.hctms.util.StringUtil;

@Service("trainPlanService")
public class TrainPlanService extends BaseService<TrainPlan> {

	Logger log = Logger.getLogger(TrainPlanService.class);

	@Autowired
	protected TrainPlanDao trainPlanDao;
	@Autowired
	protected StuTeachOrderDao stuTeachOrderDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	@Autowired
	protected ScoFormMainDao scoFormMainDao;
	@Autowired
	protected StuActiveDataDao stuActiveDataDao;
	@Autowired
	protected MessagePublishDao messagePublishDao;
	@Autowired
	protected MessageReceiveDao messageReceiveDao;
	@Autowired
	protected OrgaInfoDao orgaInfoDao;
	@Autowired
	protected StuAttendanceInfoDao stuAttendanceInfoDao;
	@Autowired
	protected OutdeptRecordDao outdeptRecordDao;
	@Autowired
	protected StudentInfoDao studentInfoDao;
	@Autowired
	protected TrainTeachOrderDao trainTeachOrderDao;
	@Autowired
	protected SysConfigDao sysConfigDao;

	/***
	 * 查询个人轮转计划
	 * 
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月19日
	 */
	public Object selectList(HttpServletRequest req) {
		JSONObject jobj = new JSONObject();
		// 从缓存中得到stu_auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		List<TrainPlan> dataList1 = trainPlanDao
				.selectBystu_auth_id(stu_auth_id);
		List<TrainPlan> dataList = new ArrayList<TrainPlan>();
		// 查询完成的百分比
		for (int i = 0; i < dataList1.size(); i++) {
			// 查询完成的百分比
			List<TrainTeachOrder> trainTeachOrderList = trainTeachOrderDao
					.selectTrainTeachOrderList(dataList1.get(i));
			float completion_sum = 0;// 各项完成率相加的和
			int sumOrder = 0;// 需要完成的份数
			// 计算完成率
			for (TrainTeachOrder _menu : trainTeachOrderList) {
				int tto = _menu.getId();
				// 根据 stu_auth_id 和 科室ID加载 train_teach_order的数据
				StuTeachOrder stuTeachOrder = new StuTeachOrder();
				stuTeachOrder.setStu_auth_id(stu_auth_id);
				stuTeachOrder.setDept_id(orga_id);
				stuTeachOrder.setTto_id(tto);
				stuTeachOrder.setState("Y");
				List<StuTeachOrder> stuTeachOrderList = stuTeachOrderDao
						.selectList(stuTeachOrder);
				if (stuTeachOrderList.size() > 0
						&& stuTeachOrderList.get(0).getFinnish_num() != null) {
					_menu.setFinnish_num(stuTeachOrderList.get(0)
							.getFinnish_num());
				} else {
					_menu.setFinnish_num(0);
				}
				// 计算完成率
				if (_menu.getOrder_num() != null && _menu.getOrder_num() != 0) {
					sumOrder++;
					float completion_rate1 = (float) _menu.getFinnish_num()
							/ (float) _menu.getOrder_num();
					if (completion_rate1 <= 1) {
						completion_sum = completion_sum + completion_rate1;
					} else {
						completion_sum = completion_sum + 1;
					}
				}
			}
			int num = 0;
			if (sumOrder != 0) {
				num = (int) (completion_sum / sumOrder * 100);
			}
			TrainPlan trainPlan = trainPlanDao.selectOneById(dataList1.get(i)
					.getId());
			if (num > 100) {
				trainPlan.setCompletion_rate(100);
			} else {
				trainPlan.setCompletion_rate(num);
			}
			// 查询学生分数
			OutdeptRecord outdeptRecord = new OutdeptRecord();
			if (stu_auth_id != null && !stu_auth_id.equals("")) {
				outdeptRecord.setStu_auth_id(stu_auth_id);
			}
			if (trainPlan.getTrain_dept_id() != null
					&& !trainPlan.getTrain_dept_id().equals("")) {
				outdeptRecord.setOrga_id(Integer.parseInt(trainPlan
						.getTrain_dept_id()));
			}
			outdeptRecord.setExam_role_id(20);
			OutdeptRecord outdeptRecordGet = outdeptRecordDao
					.selectExamResult(outdeptRecord);// 查询学生在本科室的最后一条出科审批记录是否为重新发起审核状态0
			if (outdeptRecordGet != null && !outdeptRecordGet.equals("")) {
				Integer theory_sco = outdeptRecordGet.getTheory_sco();
				Integer skill_sco = outdeptRecordGet.getSkill_sco();
				trainPlan.setTheory_sco(theory_sco);
				trainPlan.setSkill_sco(skill_sco);
			}
			dataList.add(trainPlan);
		}
		jobj.put("rows", dataList);
		return StringUtil.dnull(jobj.toString());
	}

	/**
	 * 得到当前科室下所有待轮转的学生id 和姓名
	 * 
	 * @param req
	 * @return
	 * @author huq
	 * @date 2017年3月3日
	 */
	public List<TrainPlan> getTrainStu(HttpServletRequest req) {
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
		List<TrainPlan> lists = trainPlanDao.selectToRotateList(trainPlan);
		return lists;
	}

	public Map<String, Object> getTeachingType(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		// 从缓存中得到带教老师所在的科室
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer teacher_OrgaId = loginBean.getvUserDetailInfo().getOrga_id(); // 当前带教老师的科室ID
		Integer teacher_AuthId = loginBean.getvUserDetailInfo().getAuth_id(); // 当前带教老师的权限id
		Integer roleId = loginBean.getvUserDetailInfo().getRole_id(); // 角色id

		TrainPlan train = new TrainPlan();
		train.setState("Y");
		if (roleId != 50) { // 科教科就是得到所有的
			train.setTrain_dept_id(teacher_OrgaId.toString());
			train.setTrain_status_str(Consts.ProcessInfo.TRAINPROCESSZ); // 当前科室状态52——57
		}
		// 得到当前科室下所有的学生
		List<TrainPlan> allStu = trainPlanDao.selectAllStuByTJ(train);
		map.put("allStu", allStu);
		train.setTeacher_auth_id(teacher_AuthId);
		// 得到当前科室下当前带教老师所带的学生
		train.setTrain_start_str(Consts.ProcessInfo.TRAINPROCESSInAll); // 53-58
		List<TrainPlan> stuWith = trainPlanDao.selectAllStuByTJ(train);
		map.put("stuWith", stuWith);

		// 得到当前科室下所有带教老师
		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setState("Y");
		userAuthority.setRole_id(20);
		if (roleId != 50) { // 科教科就是得到所有的
			userAuthority.setOrga_id(teacher_OrgaId);
		}
		List<UserAuthority> teacherWith = userAuthorityDao
				.selectTeacherList(userAuthority);
		map.put("teacherWith", teacherWith);

		// 得到当前科室下所有的教学秘书
		userAuthority.setRole_id(30);
		List<UserAuthority> teachingSecretary = userAuthorityDao
				.selectTeacherList(userAuthority);
		map.put("teachingSecretary", teachingSecretary);

		// 得到当前科室下的所有科主任
		userAuthority.setRole_id(40);
		List<UserAuthority> teacherDirector = userAuthorityDao
				.selectTeacherList(userAuthority);
		map.put("teacherDirector", teacherDirector);

		return map;
	}

	// 得到所有的学生
	public JSONObject getPendingAudit(int page, int rows, HttpServletRequest req) {
		JSONObject jobj = new JSONObject();

		// 从缓存中得到带教老师所在的科室
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		Integer roleId = loginBean.getvUserDetailInfo().getRole_id(); // 获得角色id
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setExamine_state(0);
		if (roleId == 20) { // 如果是带教老师，就获取老师所带的学生
			Integer teacherAuthId = loginBean.getvUserDetailInfo().getAuth_id();// 获取带教老师权限id
			trainPlan.setTeacher_auth_id(teacherAuthId);
		}
		// 如果是秘书 获取所有的当前科室的轮转学生
		trainPlan.setTrain_dept_id(orga_id.toString());
		trainPlan.setTrainStatus(Consts.ProcessInfo.TRAINPROCESSInAll);
		String name = req.getParameter("name");
		if (name != "" && name != null) {
			trainPlan.setName(name);
		}
		// 查询有审核条件的学生的条数
		int totalCount = trainPlanDao.getPendingAuditCount(trainPlan);

		List<TrainPlan> stuList = trainPlanDao.getPendingAudit(new RowBounds(
				(page - 1) * rows, page * rows), trainPlan); // 查找所有有待审核 的学生
		for (int i = 0; i < stuList.size(); i++) {
			stuList.get(i).setTrain_start_str(
					stuList.get(i).getTrain_start_str() + " ~ "
							+ stuList.get(i).getTrain_end_str());
		}
		;
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", page);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", stuList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}

	public Map<String, Object> getOrgaNowAndNextTime(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);

		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id(); // 权限id
		Integer stu_dept_id = loginBean.getvUserDetailInfo().getOrga_id(); // 当前科室

		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setStu_auth_id(stu_auth_id);
		Timestamp timestamp = new Timestamp(8);
		String OrgaName = ""; // 当前科室名
		String NextOrgaName = ""; // 下一个科室名
		Integer tranTime=null;
		Integer trainEndTime=null;
		int now = 0;// 当前科室的序号
		boolean flag = true;
		trainPlan.setQueryCondition(" and  train_status in (53,54,55,56,57)");
		trainPlan.setOrderCondition(" train_start_time asc ");
		List<TrainPlan> lists = trainPlanDao.selectList(trainPlan);
		List<OrgaInfo> stuTypeList = (List<OrgaInfo>) req.getSession()
				.getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
		for (int i = 0; i < lists.size(); i++) {
			if ((lists.get(i).getTrain_dept_id()).equals((stu_dept_id
					.toString()))) {
				if (i == lists.size() - 1) {
					flag = false;
				}
				now = i + 1;
				timestamp = lists.get(i).getTrain_end_time(); // 得到当前轮转科室的结束时间

				Integer teacherId = lists.get(i).getTeacher_auth_id(); // 当前带教老师id
				UserAuthority teacher = userAuthorityDao
						.selectOneNameByAuth(teacherId);
				if (teacher != null)
					map.put("teacherName", teacher.getName());
				Integer secretaryId = lists.get(i).getSecretary_auth_id(); // 教学秘书id
				UserAuthority secretary = userAuthorityDao
						.selectOneNameByAuth(secretaryId);
				if (secretary == null
						|| StringUtil.isEmpty(secretary.getName())) {
					map.put("secretaryName", "-");
				} else {
					map.put("secretaryName", secretary.getName());
				}

				for (int j = 0; j < stuTypeList.size(); j++) {
					if (stuTypeList.get(j).getOrga_id().toString()
							.equals(stu_dept_id.toString())) {
						OrgaName = stuTypeList.get(j).getOrga_name();
						map.put("OrgaName", OrgaName); // 得到当前科室名
					}
					;
				}
			}

		}
		if (flag) {
			for (int j = 0; j < stuTypeList.size(); j++) {
				if (stuTypeList.get(j).getOrga_id().toString()
						.equals(lists.get(now).getTrain_dept_id())) {
					NextOrgaName = stuTypeList.get(j).getOrga_name();
					map.put("NextOrgaName", NextOrgaName); // 得到下一个科室名
				}
				;
			}
		} else {
			map.put("NextOrgaName", NextOrgaName); // 得到下一个科室名
		}

		Date d = new Date(timestamp.getTime());
		java.util.Date data = new java.util.Date();
		try {
			int a = DateUtil.daysBetween(data, d);
			map.put("days", a);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return map;
	}

	/***
	 * 办理入科操作
	 * 
	 * @param req
	 * @return
	 * @author yuanb
	 * @date 2017年3月17日
	 */
	public boolean stuIndept(HttpServletRequest req) {
		boolean fl = false;
		String dataType = req.getParameter("dataType");
		if ("html".equals(dataType)) { // html表单提交
			fl = this.saveHtmlData(req);
		} else { // 评分表单提交

		}
		return fl;
	}

	public boolean intoOrga(HttpServletRequest req) {
		// 从内存中读取当前操作人
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		// String content = req.getParameter("content");
		String stuAuthId = req.getParameter("stuAuthId");
		String teacherId = req.getParameter("teacherId");
		String _kind = req.getParameter("_kind");
		int f2=0;
		// html表单存入表单记录表
		// 1、先判断是否有数据，先删除掉之前的数据
		int orgaId = loginBean.getvUserDetailInfo().getOrga_id();
		// if(StringUtil.isNotEmpty(stuAuthId)&&orgaId>0){
		// ScoFormMain _sfm = new ScoFormMain();
		// _sfm.setType_id(1);
		// _sfm.setUser_auth_id(Integer.parseInt(stuAuthId));
		// _sfm.setOrga_id(orgaId);
		// scoFormMainDao.delStuOldData(_sfm);
		// }
		// //2、然后新增数据进去
		// ScoFormMain sfm = new ScoFormMain();
		// sfm.setId(scoFormMainDao.getSequence());
		// sfm.setType_id(1);
		// sfm.setForm_type(2);
		// sfm.setUser_auth_id(Integer.parseInt(stuAuthId));
		// sfm.setOrga_id(loginBean.getvUserDetailInfo().getOrga_id());
		// sfm.setHtml_content(content);
		// sfm.setCreate_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
		// sfm.setCreate_time(new java.sql.Timestamp(new
		// java.util.Date().getTime()));
		// sfm.setState("Y");
		// int f1 = scoFormMainDao.add(sfm);
		// 改变学生轮转状态、当前轮转科室、科室带教老师
		TrainPlan tp = new TrainPlan();
		tp.setTrain_status(52);
		tp.setState("Y");
		tp.setStu_auth_id(Integer.parseInt(stuAuthId));
		tp=trainPlanDao.selectOneTrain(tp);
		if (_kind != null && _kind.equals("1")) {// 正常入科，改变轮转状态
			tp.setTrain_status(Consts.ProcessInfo.TRAINPROCESS1);
		}
		tp.setTeacher_auth_id(Integer.parseInt(teacherId));
		tp.setStu_auth_id(Integer.parseInt(stuAuthId));
		tp.setTrain_dept_id(orgaId + "");
		tp.setDept_receive_date(new java.sql.Timestamp(new java.util.Date()
				.getTime()));
		 f2 = trainPlanDao.updateTrainStatus(tp);
		// 改变userAuthority表的当前科室
		if (_kind != null && _kind.equals("1")) {// 正常入科，改变科室
			UserAuthority _ua = new UserAuthority();
			_ua.setAuth_id(Integer.parseInt(stuAuthId));
			_ua.setOrga_id(loginBean.getvUserDetailInfo().getOrga_id());
			int f3 = userAuthorityDao.update(_ua);
		}
		// 给学生发送一条入科告知消息
		// int f4 = sendMsgToStu(loginBean,stuAuthId);
		// 判断
		// 判断
		if (f2 > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int sendMsgToStu(String content, LoginBean loginBean,
			String teaAuthId) {
		// 主表插入数据
		int mpId = messagePublishDao.getSequence();
		MessagePublish mp = new MessagePublish();
		mp.setId(mpId);
		mp.setTitle("入科宣教已读告知");
		mp.setContent(content);
		mp.setSend_time(new java.sql.Timestamp(new java.util.Date().getTime()));
		mp.setSender_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
		mp.setState("Y");
		mp.setType_id(2);
		int f1 = messagePublishDao.add(mp);
		// 插入子表
		MessageReceive mr = new MessageReceive();
		mr.setReceiver_auth_id(Integer.parseInt(teaAuthId));
		mr.setId(messageReceiveDao.getSequence());
		mr.setProgress_state(0);
		mr.setState("Y");
		mr.setMp_id(mpId);
		int f2 = messageReceiveDao.add(mr); // 接收表
		if (f1 > 0 && f2 > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	public Object getOutlineCom(HttpServletRequest req) {
		// 从缓存中得到stu_auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orgaId = loginBean.getvUserDetailInfo().getOrga_id();
		List<TrainPlan> dataList = trainPlanDao
				.selectBystu_auth_id(stu_auth_id);
		//
		int i = 0;
		TrainPlan tp = new TrainPlan();
		tp.setStu_auth_id(stu_auth_id);
		tp.setTrain_dept_id(orgaId.toString());
		tp.setState("Y");
		List<TrainPlan> tpList = trainPlanDao.selectList(tp);
		TrainTeachOrder tto = new TrainTeachOrder();
		if (tpList != null && !tpList.isEmpty()) {
			tto.setTpc_id(tpList.get(0).getTpc_id());
		}
		tto.setState("Y");
		List<TrainTeachOrder> trainTeachOrderList = trainTeachOrderDao
				.selectList(tto);
		float completion_sum = 0;// 各项完成率相加的和
		int sumOrder = 0;// 需要完成的份数
		int outlineCom = 0;// 返回的值
		// 计算完成率
		for (TrainTeachOrder _menu : trainTeachOrderList) {
			int ttoId = _menu.getId();
			// 根据 stu_auth_id 和 科室ID加载 train_teach_order的数据
			StuTeachOrder stuTeachOrder = new StuTeachOrder();
			stuTeachOrder.setStu_auth_id(stu_auth_id);
			stuTeachOrder.setDept_id(orgaId);
			stuTeachOrder.setTto_id(ttoId);
			stuTeachOrder.setState("Y");
			List<StuTeachOrder> stuTeachOrderList = stuTeachOrderDao
					.selectList(stuTeachOrder);
			if (stuTeachOrderList.size() > 0
					&& stuTeachOrderList.get(0).getFinnish_num() != null) {
				_menu.setFinnish_num(stuTeachOrderList.get(0).getFinnish_num());
			} else {
				_menu.setFinnish_num(0);
			}
			// 计算完成率
			if (_menu.getOrder_num() != null && _menu.getOrder_num() != 0) {
				sumOrder++;
				float completion_rate1 = (float) _menu.getFinnish_num()
						/ (float) _menu.getOrder_num();
				if (completion_rate1 <= 1) {
					completion_sum = completion_sum + completion_rate1;
				} else {
					completion_sum = completion_sum + 1;
				}
			}
		}
		int num =0;
		if(sumOrder!=0){
			num = (int) (completion_sum / sumOrder * 100);
		}
		if (num > 100) {
			outlineCom = 100;
		} else {
			outlineCom = num;
		}
		return StringUtil.returns(true, outlineCom);
	}

	public Map<String, Object> getStuAll(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		// 从缓存中得到stu_auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer tea_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orgaId = loginBean.getvUserDetailInfo().getOrga_id();
		TrainPlan trainPlan1 = new TrainPlan();
		trainPlan1.setTeacher_auth_id(tea_auth_id);
		trainPlan1.setTrain_dept_id(orgaId.toString());
		trainPlan1.setState("Y");
		trainPlan1.setTrainStatus("53");// 轮转中
		List<TrainPlan> list1 = trainPlanDao.selectAllStuByTJ(trainPlan1);
		map.put("stuCount", list1.size()); // 轮转中学生个数

		trainPlan1.setTrainStatus("52");// 待入科 学生
		List<TrainPlan> list2 = trainPlanDao.selectAllStuByTJ(trainPlan1);
		map.put("enrollStuCounts", list2.size()); // 待入科轮转学生个数

		trainPlan1.setTrainStatus("54"); // 待出科审核数
		List<TrainPlan> _list = trainPlanDao.selectAllStuByTJ(trainPlan1);
		map.put("chuKeCount", _list.size()); // 待出科审核学生个数

		trainPlan1.setTrainStatus("54,55,56,57");// 待出科 学生
		List<TrainPlan> list3 = trainPlanDao.selectAllStuByTJ(trainPlan1);
		map.put("gradStuCount", list3.size()); // 轮转中学生个数

		// 得到当前科室，当前老师，所带正在轮转中的学生
		trainPlan1.setTrainStatus("53,54,55,56,57");
		List<TrainPlan> lists = trainPlanDao.selectAllStuByTJ(trainPlan1);

		List<TrainPlan> trainPlans = new ArrayList<TrainPlan>();
		int shenHeCount = 0;
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i) != null) {
				Integer stu_auth_id = lists.get(i).getStu_auth_id(); // 得到学生的权限id
				StuActiveData stuActiveData = new StuActiveData();
				stuActiveData.setState("Y");
				stuActiveData.setStu_auth_id(stu_auth_id);
				stuActiveData.setDept_id(orgaId);
				stuActiveData.setExamine_state(0);
				shenHeCount += stuActiveDataDao.selectCount(stuActiveData);

				List<TrainPlan> dataList = trainPlanDao
						.selectBystu_auth_id(stu_auth_id);
				int outlineCom = 0;
				Timestamp timestamp = new Timestamp(8);
				TrainPlan trainPlan2 = new TrainPlan();
				for (TrainPlan trainPlan : dataList) {
					if (trainPlan.getTrain_dept_id().equals(orgaId.toString())) {
						//
						int j = 0;
						TrainPlan tp = new TrainPlan();
						tp.setStu_auth_id(stu_auth_id);
						tp.setTrain_dept_id(orgaId.toString());
						tp.setState("Y");
						List<TrainPlan> tpList = trainPlanDao.selectList(tp);
						TrainTeachOrder tto = new TrainTeachOrder();
						if (tpList != null && !tpList.isEmpty()) {
							tto.setTpc_id(tpList.get(0).getTpc_id());
						}
						List<TrainTeachOrder> trainTeachOrderList = trainTeachOrderDao
								.selectList(tto);
						for (TrainTeachOrder trainTeachOrder : trainTeachOrderList) {
							if (trainTeachOrder.getOrder_num() != null) {
								j++;
							}
						}
						//
						StuTeachOrder stuTeachOrder = new StuTeachOrder();
						stuTeachOrder.setDept_id(orgaId);
						stuTeachOrder.setStu_auth_id(stu_auth_id);
						List<StuTeachOrder> stuTeachOrderList = stuTeachOrderDao
								.selectList(stuTeachOrder);
						float completion_sum = 0;
						// 计算完成率
						for (StuTeachOrder _menu : stuTeachOrderList) {
							// 计算完成率
							if (_menu.getOrder_num() != null
									&& _menu.getOrder_num() != 0) {
								float completion_rate1 = (float) _menu
										.getFinnish_num()
										/ (float) _menu.getOrder_num();
								if (completion_rate1 <= 1) {
									completion_sum = completion_sum
											+ completion_rate1;
								} else {
									completion_sum = completion_sum + 1;
								}
							}
						}
						int num = (int) (completion_sum / j * 100);
						if (num > 100) {
							outlineCom = 100;
						} else {
							outlineCom = num;
						}

						trainPlan2.setCompletion_rate(outlineCom); // 学生的轮转完成度
						// 得到的轮转剩余天数
						timestamp = trainPlan.getTrain_end_time(); // 得到当前轮转科室的结束时间
						Date d = new Date(timestamp.getTime());
						java.util.Date data = new java.util.Date();
						try {
							int a = DateUtil.daysBetween(data, d);
							trainPlan2.setLastDay(a); // 学生轮转的剩余天数
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}

				}
				trainPlan2.setName(lists.get(i).getName()); // 学生的姓名
				trainPlans.add(trainPlan2);
			}
		}
		map.put("dataAudit", shenHeCount);
		map.put("trainPlans", trainPlans); // 学生详情
		return map;
	}

	/**
	 * 得到秘书角色或科主任角色下的学生详情（学生的类型）
	 * 
	 * @param req
	 * @return
	 * @author huq
	 * @date 2017年3月28日
	 */
	public Map<String, Object> getSecreDetails(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		// 从缓存中得到secre_auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		// Integer secre_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orgaId = loginBean.getvUserDetailInfo().getOrga_id();
		// Integer roleId=loginBean.getvUserDetailInfo().getRole_id();
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setTrain_dept_id(orgaId.toString());// 科室
		// if(roleId==30){
		// trainPlan.setSecretary_auth_id(secre_auth_id);
		// }
		List<TrainPlan> lists = new ArrayList<TrainPlan>();
		trainPlan.setTrainStatus("52");// 待入科 学生;
		lists = trainPlanDao.getSecreDetails(trainPlan);
		Integer enrollStuCounts = 0; // 待入科 学生数
		for (int i = 0; i < lists.size(); i++) { // 得到该角色下待入科学生数
			enrollStuCounts += lists.get(i).getCounts();
		}
		map.put("enrollStuCounts", enrollStuCounts);

		trainPlan.setTrainStatus("54,55,56,57");// 待出科 学生
		lists = trainPlanDao.getSecreDetails(trainPlan);
		Integer gradStuCount = 0; // 待入科 学生数
		for (int i = 0; i < lists.size(); i++) { // 得到该角色下待入科学生数
			gradStuCount += lists.get(i).getCounts();
		}
		map.put("gradStuCount", gradStuCount); // 轮转中学生个数

		trainPlan.setTrainStatus("53");// 轮转中
		lists = trainPlanDao.getSecreDetails(trainPlan);
		Integer stuCount = 0; // 待入科 学生数
		for (int i = 0; i < lists.size(); i++) { // 得到该角色下待入科学生数
			stuCount += lists.get(i).getCounts();
		}
		map.put("stuCount", stuCount); // 轮转中学生个数

		trainPlan.setTrainStatus("53,54,55,56,57");
		lists = trainPlanDao.getSecreDetails(trainPlan); // 所有的轮转中的学生
		Integer count = 0; // 所有的总数
		for (int i = 0; i < lists.size(); i++) { // 得到该角色下，所有的学生数
			count += lists.get(i).getCounts();
		}
		for (int i = 0; i < lists.size(); i++) { // 循环得到每个学生类型的百分比
			BigDecimal b = new BigDecimal((double) lists.get(i).getCounts()
					/ (double) count);
			double f1 = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
			lists.get(i).setCompletion_type(f1);
		}
		map.put("lists", lists);
		return map;
	}

	public List<TrainPlan> getGradeInit(HttpServletRequest req) {
		// 得到前台参数
		String name = req.getParameter("name"); // 学生姓名
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer orgaId = loginBean.getvUserDetailInfo().getOrga_id();// 当前科室
		Integer roleId = loginBean.getvUserDetailInfo().getRole_id();// 当前角色类型id
		Integer authId = loginBean.getvUserDetailInfo().getAuth_id();// 当前角色authid
		// 查询带教的学生
		TrainPlan tp = new TrainPlan();
		tp.setName(name);
		tp.setState("Y");
		if (roleId != 50) {
			tp.setTrain_dept_id(orgaId.toString());
		}
		if (roleId == 20) {
			tp.setTeacher_auth_id(authId);
		}
		tp.setQueryCondition("and tp.train_status in ("
				+ Consts.ProcessInfo.TRAINPROCESSInAll + ")");
		List<TrainPlan> tpList = trainPlanDao.selectListByName(tp);
		// 查询学生出科审核轨迹
		List<TrainPlan> trainPlans = new ArrayList<TrainPlan>();
		OutdeptRecord or = new OutdeptRecord();
		or.setState("Y");
		for (TrainPlan tpGet : tpList) {
			TrainPlan trainPlan = new TrainPlan();
			trainPlan.setName(tpGet.getName()); // 学生的姓名
			trainPlan.setStu_auth_id(tpGet.getStu_auth_id()); // 学生权限id
			trainPlan.setTrain_dept_id(tpGet.getTrain_dept_id()); // 学生科室id
			trainPlan.setStu_type_id(tpGet.getStu_type_id()); // 学生类型id
			trainPlan.setDept_receive_str(tpGet.getTrain_start_str() + "~"
					+ tpGet.getTrain_end_str());// 开始结束时间
			trainPlan.setTypeName(tpGet.getTypeName()); // 学生类型名
			trainPlan.setTrain_status(tpGet.getTrain_status());
			trainPlan.setStuTypeId(tpGet.getStuTypeId());
			trainPlan.setStu_num(tpGet.getStu_num());
			// 得到的轮转剩余天数
			Timestamp train_end_time = tpGet.getTrain_end_time(); // 得到当前轮转科室的结束时间
			Date d = new Date(train_end_time.getTime());
			java.util.Date data = new java.util.Date();
			try {
				int a = DateUtil.daysBetween(data, d);
				trainPlan.setLastDay(a); // 学生轮转的剩余天数
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//
			or.setOrga_id(Integer.parseInt(tpGet.getTrain_dept_id()));
			or.setStu_auth_id(tpGet.getStu_auth_id());
			List<OutdeptRecord> orList = outdeptRecordDao.selectOrList(or);// 查询学生的出科审核轨迹
			if (orList != null && !orList.isEmpty()) {
				String train_status_str = "";
				for (OutdeptRecord orGet : orList) {
					train_status_str += orGet.getExam_role_id() + "-";
				}
				if (roleId == 20 || roleId == 30) { // 带教老师
					if (train_status_str.indexOf("20") != -1) {
						if (Consts.ProcessInfo.TRAINPROCESS2 == tpGet
								.getTrain_status()) {
							trainPlan
									.setCompletion_rate(getPercentComplete(tpGet));
							trainPlans.add(trainPlan);
						}
					}
				} else if (roleId == 40) {
					if (train_status_str.indexOf("40") != -1) {
						if (train_status_str.indexOf("20") != -1
								|| train_status_str.indexOf("30") != -1) {
							if (Consts.ProcessInfo.TRAINPROCESS3 == tpGet
									.getTrain_status()) {
								trainPlan
										.setCompletion_rate(getPercentComplete(tpGet));
								trainPlans.add(trainPlan);
							}
						} else {
							if (Consts.ProcessInfo.TRAINPROCESS2 == tpGet
									.getTrain_status()) {
								trainPlan
										.setCompletion_rate(getPercentComplete(tpGet));
								trainPlans.add(trainPlan);
							}
						}
					}
				} else if (roleId == 50) {
					if (train_status_str.indexOf("50") != -1) {
						if (train_status_str.indexOf("20") != -1
								|| train_status_str.indexOf("30") != -1) {
							if (train_status_str.indexOf("40") != -1) {
								if (Consts.ProcessInfo.TRAINPROCESS4 == tpGet
										.getTrain_status()) {
									trainPlan
											.setCompletion_rate(getPercentComplete(tpGet));
									trainPlans.add(trainPlan);
								}
							} else {
								if (Consts.ProcessInfo.TRAINPROCESS3 == tpGet
										.getTrain_status()) {
									trainPlan
											.setCompletion_rate(getPercentComplete(tpGet));
									trainPlans.add(trainPlan);
								}
							}
						} else {
							if (train_status_str.indexOf("40") != -1) {
								if (Consts.ProcessInfo.TRAINPROCESS4 == tpGet
										.getTrain_status()) {
									trainPlan
											.setCompletion_rate(getPercentComplete(tpGet));
									trainPlans.add(trainPlan);
								}
							} else {
								if (Consts.ProcessInfo.TRAINPROCESS2 == tpGet
										.getTrain_status()) {
									trainPlan
											.setCompletion_rate(getPercentComplete(tpGet));
									trainPlans.add(trainPlan);
								}
							}
						}
					}
				}
			}
		}
		return trainPlans;
	}

	/***
	 * 查询完成的百分比
	 * 
	 * @param trainPlan
	 * @return
	 * @author guocc
	 * @date 2017年8月31日
	 */
	public int getPercentComplete(TrainPlan trainPlan) {
		// 查询完成的百分比
		List<TrainTeachOrder> trainTeachOrderList = trainTeachOrderDao
				.selectTrainTeachOrderList(trainPlan);
		int i = 0;// 计数
		float completion_sum = 0;// 各项完成率相加的和
		for (TrainTeachOrder trainTeachOrder : trainTeachOrderList) {
			if (trainTeachOrder.getOrder_num() != null) {
				i++;
				float completion_rate1 = 0;
				if (trainTeachOrder.getFinnish_num() != null) {
					completion_rate1 = (float) trainTeachOrder.getFinnish_num()
							/ (float) trainTeachOrder.getOrder_num();
				}
				if (completion_rate1 <= 1) {
					completion_sum = completion_sum + completion_rate1;
				} else {
					completion_sum = completion_sum + 1;
				}
			}
		}
		int num = (int) (completion_sum / i * 100);
		if (num > 100) {
			num = 100;
		}
		return num;
	}

	// /**
	// * 根据学生的id 得到相应学生出科的相应信息
	// * @param req
	// * @return
	// *@author huq
	// *@date 2017年4月6日
	// */
	// public Map<String, Object> getStuInfor(HttpServletRequest req) {
	// Integer stuId=Integer.parseInt(req.getParameter("stuId"));
	// //得到当前角色
	// LoginBean
	// loginBean=(LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
	// //当前科室
	// Integer orgaId=loginBean.getvUserDetailInfo().getOrga_id();
	//
	// TrainPlan trainPlan=new TrainPlan(); //查询条件
	// trainPlan.setState("Y");
	// trainPlan.setStu_auth_id(stuId); //学生id
	// trainPlan.setTrain_dept_id(orgaId.toString()); //科室id
	// List<TrainPlan> lists=trainPlanDao.getGradeInit(trainPlan); //得到单个学生信息
	//
	// return null ;
	// }

	/***
	 * 缺勤人数
	 * 
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年4月27日
	 */
	public Object absenceNum(HttpServletRequest req) {
		StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
		// 获取当前时间
		java.util.Date date = new java.util.Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Integer attend_date = Integer.parseInt(format.format(date));
		stuAttendanceInfo.setAttend_date(attend_date);
		stuAttendanceInfo.setState("Y");
		// 到勤人数
		stuAttendanceInfo.setAttend_state(1);// 到勤人数
		int absenceAllNum = trainPlanDao.absenceNum(stuAttendanceInfo);
		// 缺勤人数
		stuAttendanceInfo.setAttend_state(0);// 缺勤人数
		int absenceNum = trainPlanDao.absenceNum(stuAttendanceInfo);
		// 请假人数
		stuAttendanceInfo.setAttend_state(2);// 请假人数
		int leaveNum = trainPlanDao.absenceNum(stuAttendanceInfo);

		float absenceAll_rate = 0;
		float absence_rate = 0;
		float leave_rate = 0;
		if (leaveNum + leaveNum + absenceAllNum != 0) {
			// 到勤人数率
			absenceAll_rate = (float) absenceAllNum
					/ ((float) absenceNum + (float) leaveNum + (float) absenceAllNum);
			// 缺勤人数率
			absence_rate = (float) absenceNum
					/ ((float) leaveNum + (float) absenceNum + (float) absenceAllNum);
			// 请假人数率
			leave_rate = (float) leaveNum
					/ ((float) leaveNum + (float) absenceNum + (float) absenceAllNum);
		} else {
			absenceAll_rate = 1;
		}
		stuAttendanceInfo.setAbsenceNum(absenceNum);
		stuAttendanceInfo.setAbsenceAllNum(absenceAllNum);
		stuAttendanceInfo.setLeaveNum(leaveNum);
		absenceAll_rate = (float) (Math.round(absenceAll_rate * 100)) / 100;
		absence_rate = (float) (Math.round(absence_rate * 100)) / 100;
		leave_rate = (float) (Math.round(leave_rate * 100)) / 100;
		stuAttendanceInfo.setAbsence_rate(absence_rate);
		stuAttendanceInfo.setAbsenceAll_rate(absenceAll_rate);
		stuAttendanceInfo.setLeave_rate(leave_rate);
		return stuAttendanceInfo;
	}

	/**
	 * 待出科人数
	 * 
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年4月27日
	 */
	public Object trainPlanWait(HttpServletRequest req) {
		TrainPlan trainPlan = new TrainPlan();
		String trainStatusStr = Consts.ProcessInfo.TRAINPROCESSOut + "";
		trainPlan.setTrainStatus(trainStatusStr);
		Integer didCount = trainPlanDao.getRoundRobin(trainPlan);
		return didCount;
	}

	/**
	 * 得到科教科角色首页的全院轮转情况板块数据
	 * 
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月18日
	 */
	public Object getRoundRobin(HttpServletRequest req) {
		JSONObject jobj = new JSONObject();
		List<OrgaInfo> orgaInfoList = orgaInfoDao.selectOrgaInfoList();
		TrainPlan trainPlan = new TrainPlan();
		String trainStatusStr = "";
		ProcessInfo processInfo = new ProcessInfo();
		processInfo.setType_code("TRAINPROCESS");
		List<TrainPlan> trainPlanList = new ArrayList<TrainPlan>();
		for (OrgaInfo orgaInfoGet : orgaInfoList) {
			TrainPlan trainPlanGet = new TrainPlan();
			trainPlanGet.setOrga_name(orgaInfoGet.getOrga_name());

			trainPlan.setTrain_dept_id(orgaInfoGet.getOrga_id().toString());
			// 入科报到人数
			trainStatusStr = Consts.ProcessInfo.TRAINPROCESS0 + "";
			trainPlan.setTrainStatus(trainStatusStr);
			Integer todoCount = trainPlanDao.getRoundRobin(trainPlan);// 入科报到人数
			trainPlanGet.setTodoCount(todoCount);
			// 轮转中人数
			trainStatusStr = Consts.ProcessInfo.TRAINPROCESS1 + "";
			trainPlan.setTrainStatus(trainStatusStr);
			Integer doingCount = trainPlanDao.getRoundRobin(trainPlan);
			trainPlanGet.setDoingCount(doingCount);
			// 待出科人数
			trainStatusStr = Consts.ProcessInfo.TRAINPROCESSOut + "";
			trainPlan.setTrainStatus(trainStatusStr);
			Integer didCount = trainPlanDao.getRoundRobin(trainPlan);
			trainPlanGet.setDidCount(didCount);
			trainPlanList.add(trainPlanGet);
		}
		jobj.put("row", trainPlanList);
		return jobj;
	}

	/**
	 * 获取学生管理模块主页面数据
	 * 
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月19日
	 */
	public Object getStuManageInfo(HttpServletRequest req) {
		// 获取页面数据
		String train_status_flag = req.getParameter("train_status_flag");// 状态标识
		String name = req.getParameter("stu_name");
		TrainPlan trainPlan = new TrainPlan();
		if (train_status_flag != null && !train_status_flag.equals("")) {
			if (train_status_flag.equals("1")) {
				trainPlan.setTrain_status_arry(Consts.ProcessInfo.TRAINPROCESS1
						+ "");
			} else if (train_status_flag.equals("2")) {
				trainPlan
						.setTrain_status_arry(Consts.ProcessInfo.TRAINPROCESSOutBesidesFinsh);
			}
		}
		trainPlan.setName(name);
		// 获取当前时间
		java.util.Date date = new java.util.Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Integer attend_date = Integer.parseInt(format.format(date));
		// 从缓存中得到hos_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		trainPlan.setTrain_dept_id(orga_id.toString());
		List<TrainPlan> trainPlanList = trainPlanDao
				.getStuManageInfo(trainPlan);
		for (int i = 0; i < trainPlanList.size(); i++) {
			StudentInfo studentInfo = studentInfoDao
					.selectStuNumByAuthId(trainPlanList.get(i).getStu_auth_id());
			if (studentInfo != null && !studentInfo.equals("")) {
				trainPlanList.get(i).setStu_num(studentInfo.getStuNum());
			}

		}

		StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
		stuAttendanceInfo.setAttend_date(attend_date);
		stuAttendanceInfo.setState("Y");
		for (TrainPlan trainPlanGet : trainPlanList) {
			stuAttendanceInfo.setStu_auth_id(trainPlanGet.getStu_auth_id());
			stuAttendanceInfo.setOrga_id(Integer.parseInt(trainPlanGet
					.getTrain_dept_id()));
			List<StuAttendanceInfo> list = stuAttendanceInfoDao
					.selectList(stuAttendanceInfo);
			if (list != null && !list.isEmpty()) {
				if (list.get(0).getAttend_state() == 0) {
					trainPlanGet.setAttend_state_str("未签到");
				} else if (list.get(0).getAttend_state() == 1) {
					trainPlanGet.setAttend_state_str("已签到");
				} else if (list.get(0).getAttend_state() == 2
						|| list.get(0).getAttend_state() == -10
						|| list.get(0).getAttend_state() == -20) {
					trainPlanGet.setAttend_state_str("请假");
				}
			}
		}
		return StringUtil.returns(true, trainPlanList);
	}

	public int selectNonAttendanceCount(StuAttendanceInfo stuAttendanceInfo) {
		return trainPlanDao.selectNonAttendanceCount(stuAttendanceInfo);
	}

	public List<UserAuthority> formTeacherSelect(UserAuthority userAuthority) {
		return trainPlanDao.formTeacherSelect(userAuthority);
	}

	public Object selectAttendanceList(HttpServletRequest req) {
		// 获取页面数据
		String stu_auth_id = req.getParameter("stu_auth_id");
		String train_start_year = req.getParameter("train_start_year");
		String train_start_month = req.getParameter("train_start_month");
		String train_start_day = req.getParameter("train_start_day");
		String train_end_year = req.getParameter("train_end_year");
		String train_end_month = req.getParameter("train_end_month");
		String train_end_day = req.getParameter("train_end_day");
		StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
		if (stu_auth_id != null && !stu_auth_id.equals("")) {
			stuAttendanceInfo.setStu_auth_id(Integer.parseInt(stu_auth_id));
		}
		// 轮转开始时间的年月日
		int ts_year = 2017;
		int ts_month = 04;
		int ts_day = 20;
		if (train_start_year != null && !train_start_year.equals("")) {
			ts_year = Integer.parseInt(train_start_year);
		}
		if (train_start_month != null && !train_start_month.equals("")) {
			ts_month = Integer.parseInt(train_start_month) - 1;
		}
		if (train_start_day != null && !train_start_day.equals("")) {
			ts_day = Integer.parseInt(train_start_day);
		}
		// 轮转结束时间的年月日
		int te_year = 2017;
		int te_month = 07;
		int te_day = 20;
		if (train_end_year != null && !train_end_year.equals("")) {
			te_year = Integer.parseInt(train_end_year);
		}
		if (train_end_month != null && !train_end_month.equals("")) {
			te_month = Integer.parseInt(train_end_month) - 1;
		}
		if (train_end_day != null && !train_end_day.equals("")) {
			te_day = Integer.parseInt(train_end_day);
		}
		// 获取当前时间的年月日
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		// 对两个日期之间所有日期的遍历
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

		List<StuAttendanceInfo> stuAttendanceInfoList = new ArrayList<StuAttendanceInfo>();
		while (nowTime >= startTIme) {
			List<StuAttendanceInfo> stuAttendanceInfoListGet = new ArrayList<StuAttendanceInfo>();
			java.util.Date d = new java.util.Date(nowTime);
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			DateFormat dfGet = new SimpleDateFormat("yyyy-MM-dd");
			// System.out.println(df.format(d));
			Integer attend_date = Integer.parseInt(df.format(d));
			stuAttendanceInfo.setAttend_date(attend_date);
			stuAttendanceInfoListGet = trainPlanDao
					.selectAttendanceList(stuAttendanceInfo);
			if (stuAttendanceInfoListGet == null
					|| stuAttendanceInfoListGet.isEmpty()) {
				StuAttendanceInfo stuAttendanceInfoGetOfNull = new StuAttendanceInfo();
				// stuAttendanceInfoGetOfNull.setId(stuAttendanceInfoDao.getSequence());//此处用序列，方便修改时设置id
				stuAttendanceInfoGetOfNull.setAttend_state_str("已签到");
				stuAttendanceInfoGetOfNull.setAttend_time_str(dfGet.format(d));
				stuAttendanceInfoList.add(stuAttendanceInfoGetOfNull);
			} else {
				stuAttendanceInfoList.addAll(stuAttendanceInfoListGet);
			}
			nowTime -= oneDay;
		}
		//
		return StringUtil.returns(true, stuAttendanceInfoList);
	}

	public Object changeAttendanceState(HttpServletRequest req) {
		// 获取页面数据
		String id = req.getParameter("id");
		String stu_auth_id = req.getParameter("stu_auth_id");
		String attend_time_str = req.getParameter("attend_time_str");
		String attend_state = req.getParameter("attend_state");
		// 从缓存中得到auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer attend_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		StuAttendanceInfo stuAttendanceInfo = new StuAttendanceInfo();
		if (attend_state != null && !attend_state.equals("")) {
			stuAttendanceInfo.setAttend_state(Integer.parseInt(attend_state));
		}
		int num = 0;
		if (id != null && id.indexOf("line") == -1) {// 修改
			if (id != null && !id.equals("")) {
				stuAttendanceInfo.setId(Integer.parseInt(id));
			}
			num = stuAttendanceInfoDao.update(stuAttendanceInfo);
		} else if (id != null && id.indexOf("line") != -1) {// 新增
			stuAttendanceInfo.setId(stuAttendanceInfoDao.getSequence());
			if (stu_auth_id != null && !stu_auth_id.equals("")) {
				stuAttendanceInfo.setStu_auth_id(Integer.parseInt(stu_auth_id));
			}
			if (attend_time_str != null && !attend_time_str.equals("")) {
				Integer attend_date = Integer.parseInt(attend_time_str.replace(
						"-", ""));
				stuAttendanceInfo.setAttend_date(attend_date);
				stuAttendanceInfo.setAttend_time(Timestamp
						.valueOf(attend_time_str + " 00:00:00"));
			}
			stuAttendanceInfo.setOrga_id(orga_id);
			stuAttendanceInfo.setAttend_auth_id(attend_auth_id);
			stuAttendanceInfo.setState("Y");
			num = stuAttendanceInfoDao.add(stuAttendanceInfo);
		}
		if (num > 0) {
			return StringUtil.returns(true, "操作成功！");
		} else {
			return StringUtil.returns(false, "操作失败！");
		}
	}

	public Object getStudentInfo(HttpServletRequest req) {
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setTrainStatus(Consts.ProcessInfo.TRAINPROCESSInAll);
		List<TrainPlan> listTrainPlan = trainPlanDao.getStudentInfo(trainPlan);
		Integer count = 0; // 所有的总数
		for (TrainPlan trainPlanGet : listTrainPlan) {
			count += trainPlanGet.getCounts();
		}
		for (TrainPlan trainPlanGet : listTrainPlan) { // 循环得到每个学生类型的百分比
			BigDecimal b = new BigDecimal((double) trainPlanGet.getCounts()
					/ (double) count);
			double f1 = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
			trainPlanGet.setCompletion_type(f1);
		}
		return StringUtil.returns(true, listTrainPlan);
	}

	/**
	 * 得到出科审核页面的个人的基本信息
	 * 
	 * @param trainPlan
	 * @return TrainPlan
	 * @author guocc
	 * @date 2017年4月24日
	 */
	public TrainPlan getBasicInformation(TrainPlan trainPlan) {
		return trainPlanDao.getBasicInformation(trainPlan);
	}

	/**
	 * 出科
	 * 
	 * @param req
	 * @return
	 * @author huq
	 * @date 2017年4月17日
	 */
	public Object beGrAdue(HttpServletRequest req) {
		String form_id_str = req.getParameter("form_id_str");
		String theory_sco = req.getParameter("theory_sco");
		String skill_sco = req.getParameter("skill_sco");
		String exam_result = req.getParameter("exam_result");
		String s_orga_id = req.getParameter("s_orga_id");
		// 改变TRAIN_PLAN表轮转状态
		TrainPlan trainPlan = new TrainPlan();// 条件
		Integer sutId = Integer.parseInt(req.getParameter("stuId")); // 要出科的学生id
		trainPlan.setStu_auth_id(sutId);
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		// Integer orgaId=loginBean.getvUserDetailInfo().getOrga_id();//当前科室id

		String user_name = loginBean.getvUserDetailInfo().getUser_name();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		trainPlan.setTrain_dept_id(s_orga_id);
		// Integer hos_id = loginBean.getvUserDetailInfo().getHos_id();
		// trainPlan.setHos_id(hos_id);
		Integer roleId = loginBean.getvUserDetailInfo().getRole_id(); // 当前角色id
		if (exam_result != null && exam_result.equals("1")) {// 同意出科
			if (roleId == 20) { // 带教老师
				trainPlan.setTrain_status(Consts.ProcessInfo.TRAINPROCESS3);
			} else if (roleId == 30) { // 秘书
				trainPlan.setTrain_status(Consts.ProcessInfo.TRAINPROCESS3);
			} else if (roleId == 40) { // 科主任
				trainPlan.setTrain_status(Consts.ProcessInfo.TRAINPROCESS4);
			} else if (roleId == 50) { // 科教科
				trainPlan.setTrain_status(Consts.ProcessInfo.TRAINPROCESS5);
			}
		} else if (exam_result != null && exam_result.equals("-1")) {// 驳回
			trainPlan.setTrain_status(Consts.ProcessInfo.TRAINPROCESS1);
		}
		trainPlan.setQueryCondition("and train_status in (53,54,55,56,57)");
		Integer num1 = trainPlanDao.updateTrainStatus(trainPlan); // 改变状态
		// 更新OUTDEPT_RECORD表审核记录，填充审核轨迹
		OutdeptRecord outdeptRecord = new OutdeptRecord();
		outdeptRecord.setStu_auth_id(sutId);
		if (s_orga_id != null && !s_orga_id.equals("")) {
			outdeptRecord.setOrga_id(Integer.parseInt(s_orga_id));
		}
		if (roleId == 20 || roleId == 30) {
			outdeptRecord.setExam_role_id(20);
		} else if (roleId == 40) {
			outdeptRecord.setExam_role_id(40);
		} else if (roleId == 50) {
			outdeptRecord.setExam_role_id(50);
		}
		outdeptRecord.setState("Y");
		List<OutdeptRecord> orList = outdeptRecordDao
				.selectOrList(outdeptRecord);// 查询学生的出科审核轨迹
		int or_id = 0;
		if (orList != null && !orList.isEmpty()) {
			or_id = orList.get(0).getId();
		}
		OutdeptRecord or = new OutdeptRecord();
		or.setId(or_id);
		if (roleId == 30) {
			or.setExam_role_id(30);
		}
		or.setExam_auth_id(loginBean.getvUserDetailInfo().getAuth_id()); // 审核人id
		Timestamp d = new Timestamp(System.currentTimeMillis()); // 当前审核时间
		or.setExam_datetime(d);
		String EXAM_CONTENT = req.getParameter("EXAM_CONTENT"); // 审核意见
		or.setExam_content(EXAM_CONTENT);
		if (theory_sco != null && !theory_sco.equals("")) {
			or.setTheory_sco(Integer.parseInt(theory_sco));
		}
		if (skill_sco != null && !skill_sco.equals("")) {
			or.setSkill_sco(Integer.parseInt(skill_sco));
		}
		if (exam_result != null && !exam_result.equals("")) {
			or.setExam_result(Integer.parseInt(exam_result));
		}
		int num2 = outdeptRecordDao.update(or);
		// 给SCO_FORM_MAIN表的or_id字段添加数据
		int sfm_id = 0;
		ScoFormMain scoFormMain = new ScoFormMain();
		Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		scoFormMain.setCreate_auth_id(create_auth_id);
		scoFormMain.setUser_auth_id(sutId);
		if (s_orga_id != null && !s_orga_id.equals("")) {
			scoFormMain.setOrga_id(Integer.parseInt(s_orga_id));
		}
		if (form_id_str != null && !form_id_str.equals("")) {
			String[] form_id_strs = form_id_str.split("-");
			for (int i = 0; i < form_id_strs.length; i++) {
				scoFormMain.setForm_id(Integer.parseInt(form_id_strs[i]));
				ScoFormMain scoFormM = scoFormMainDao.getSFMId(scoFormMain);
				if (scoFormM != null) {
					sfm_id = scoFormM.getId();
					ScoFormMain sfm = new ScoFormMain();
					sfm.setId(sfm_id);
					sfm.setOr_id(or_id);
					scoFormMainDao.update(sfm);
				}
			}
		}
		if (num1 > 0 && num2 > 0) {// 发消息也应该根据审核轨迹
			if (exam_result != null && exam_result.equals("1")) {
				// 往消息发布表插入数据（MESSAGE_PUBLISH）
				MessagePublish mp = new MessagePublish();
				int mpId = messagePublishDao.getSequence();
				mp.setId(mpId);
				String title = "";
				mp.setContent(EXAM_CONTENT);
				mp.setSender_auth_id(create_auth_id);
				java.util.Date date = new java.util.Date();
				Timestamp create_time = new Timestamp((date.getTime()));
				mp.setSend_time(create_time);
				mp.setType_id(2);
				mp.setRelated_table("MESSAGE_RECEIVE");
				mp.setState("Y");
				// 往消息接收表插入数据（MESSAGE_RECEIVE）
				MessageReceive mr = new MessageReceive();
				mr.setProgress_state(0);
				mr.setMp_id(mpId);
				mr.setState("Y");
				List<ProcessInfo> processList = (List<ProcessInfo>) req
						.getSession().getServletContext()
						.getAttribute(Consts.SESSION_PROCESS_INFO);
				List<ProcessInfo> processSelList = new ArrayList<ProcessInfo>();
				for (ProcessInfo processInfo : processList) {
					if (processInfo.getType_code().equals("TRAINPROCESS")) {
						processSelList.add(processInfo);
					}
				}
				String train_status_str = "";
				for (ProcessInfo processSeInfo : processSelList) {
					train_status_str += processSeInfo.getId() + "-";
				}
				List<UserAuthority> userAuthorityList = new ArrayList<UserAuthority>();
				UserAuthority userAuthority = new UserAuthority();
				userAuthority.setState("Y");
				if (roleId == 20 || roleId == 30) { // 带教老师
					if (train_status_str
							.indexOf((Consts.ProcessInfo.TRAINPROCESS4) + "") != -1) {
						title = userAuthorityDao.selectOneNameByAuth(sutId)
								.getName()
								+ "【"
								+ orga_name
								+ "】"
								+ "发起了出科申请，请及时审批！";
						mp.setTitle(title);
						messagePublishDao.add(mp);
						if (s_orga_id != null && !s_orga_id.equals("")) {
							userAuthority.setOrga_id(Integer
									.parseInt(s_orga_id));
						}
						userAuthority.setRole_id(40);
						userAuthorityList = userAuthorityDao
								.selectList(userAuthority);
						for (UserAuthority userAuthorityGet : userAuthorityList) {
							int mrId = messageReceiveDao.getSequence();
							mr.setId(mrId);
							mr.setReceiver_auth_id(userAuthorityGet
									.getAuth_id());
							messageReceiveDao.add(mr);
						}
					} else if (train_status_str
							.indexOf((Consts.ProcessInfo.TRAINPROCESS5) + "") != -1) {
						title = userAuthorityDao.selectOneNameByAuth(sutId)
								.getName()
								+ "【"
								+ orga_name
								+ "】"
								+ "发起了出科申请，请及时审批！";
						mp.setTitle(title);
						messagePublishDao.add(mp);
						userAuthority.setRole_id(50);
						userAuthorityList = userAuthorityDao
								.selectList(userAuthority);
						for (UserAuthority userAuthorityGet : userAuthorityList) {
							int mrId = messageReceiveDao.getSequence();
							mr.setId(mrId);
							mr.setReceiver_auth_id(userAuthorityGet
									.getAuth_id());
							messageReceiveDao.add(mr);
						}
					} else {
						title = user_name + "【" + orga_name + "】"
								+ "批准了你的出科申请！";
						mp.setTitle(title);
						messagePublishDao.add(mp);
						int mrId = messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(sutId);
						messageReceiveDao.add(mr);
					}
				} else if (roleId == 40) {
					if (train_status_str
							.indexOf((Consts.ProcessInfo.TRAINPROCESS5) + "") != -1) {
						title = userAuthorityDao.selectOneNameByAuth(sutId)
								.getName()
								+ "【"
								+ orga_name
								+ "】"
								+ "发起了出科申请，请及时审批！";
						mp.setTitle(title);
						messagePublishDao.add(mp);
						userAuthority.setRole_id(50);
						userAuthorityList = userAuthorityDao
								.selectList(userAuthority);
						for (UserAuthority userAuthorityGet : userAuthorityList) {
							int mrId = messageReceiveDao.getSequence();
							mr.setId(mrId);
							mr.setReceiver_auth_id(userAuthorityGet
									.getAuth_id());
							messageReceiveDao.add(mr);
						}
					} else {
						title = user_name + "【" + orga_name + "】"
								+ "批准了你的出科申请！";
						mp.setTitle(title);
						messagePublishDao.add(mp);
						int mrId = messageReceiveDao.getSequence();
						mr.setId(mrId);
						mr.setReceiver_auth_id(sutId);
						messageReceiveDao.add(mr);
					}
				} else if (roleId == 50) {
					title = user_name + "【" + orga_name + "】" + "批准了你的出科申请！";
					mp.setTitle(title);
					messagePublishDao.add(mp);
					int mrId = messageReceiveDao.getSequence();
					mr.setId(mrId);
					mr.setReceiver_auth_id(sutId);
					messageReceiveDao.add(mr);
				}
			} else if (exam_result != null && exam_result.equals("-1")) {
				// 往消息发布表插入数据（MESSAGE_PUBLISH）
				MessagePublish mp = new MessagePublish();
				int mpId = messagePublishDao.getSequence();
				mp.setId(mpId);
				String title = user_name + "【" + orga_name + "】" + "驳回了你的出科申请！";
				mp.setTitle(title);
				mp.setContent(EXAM_CONTENT);
				mp.setSender_auth_id(create_auth_id);
				java.util.Date date = new java.util.Date();
				Timestamp create_time = new Timestamp((date.getTime()));
				mp.setSend_time(create_time);
				mp.setType_id(2);
				mp.setRelated_table("MESSAGE_RECEIVE");
				mp.setState("Y");
				messagePublishDao.add(mp);
				// 往消息接收表插入数据（MESSAGE_RECEIVE）
				MessageReceive mr = new MessageReceive();
				int mrId = messageReceiveDao.getSequence();
				mr.setId(mrId);
				mr.setProgress_state(0);
				mr.setMp_id(mpId);
				mr.setState("Y");
				mr.setReceiver_auth_id(sutId);
				messageReceiveDao.add(mr);
			}
			return StringUtil.returns(true, "操作成功！");
		} else {
			return StringUtil.returns(false, "操作失败！");
		}
	}

	/**
	 * 将时间上达到出科条件的学生 强制出科
	 * 
	 * @author xudk
	 * @date 2017年5月18日
	 */
	public void trainOutDept(ServletContext ctx) {
		System.out.println("--->强制出科开始");
		// 拿到满足条件的唯一auth_id
		TrainPlan trainPlan = new TrainPlan();
		List<TrainPlan> auth_idList = trainPlanDao.getAuth_id(trainPlan);
		// 更新当前train_status为58
		trainPlan.setTrain_status(58);
		trainPlanDao.updateTrain_status58(trainPlan);
		for (int i = 0; i < auth_idList.size(); i++) {
			TrainPlan trainPlan2 = new TrainPlan();
			trainPlan2.setStu_auth_id(auth_idList.get(i).getStu_auth_id());
			TrainPlan trainPlan3 = trainPlanDao.getTrain_status(trainPlan2);
			if (trainPlan3 != null) {
				TrainPlan _tp = new TrainPlan();
				if ((trainPlan3.getTeacher_auth_id() != null)
						&& !("".equals(trainPlan3.getTeacher_auth_id()))) {// 已经提前入过科
					_tp.setTrain_status(53);
				} else {
					_tp.setTrain_status(52);
				}
				_tp.setId(trainPlan3.getId());
				trainPlanDao.updateTrain_status52(_tp);
				if (StringUtil.isNotEmpty(trainPlan3.getTrain_dept_id())) {
					UserAuthority userAuthority = new UserAuthority();
					userAuthority.setAuth_id(trainPlan3.getStu_auth_id());
					userAuthority.setOrga_id(Integer.parseInt(trainPlan3
							.getTrain_dept_id()));
					userAuthorityDao.updateOrga_id(userAuthority);
				}
			}
		}
		System.out.println("--->成功出科学生数：" + auth_idList.size());

	}

	/***
	 * 科教科学生综合查询页面
	 * 
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年5月10日
	 */
	public Object orgaPageInfo(HttpServletRequest req) {
		String orga_name = req.getParameter("orga_name");
		// String hos_id=req.getParameter("hos_id");
		TrainPlan trainPlan = new TrainPlan();
		trainPlan.setOrga_name(orga_name);
		// 待入科
		String wtrainStatusStr = Consts.ProcessInfo.TRAINPROCESSD + "";
		// 轮转中
		String ztrainStatusStr = Consts.ProcessInfo.TRAINPROCESSIn + "";
		// 待出科
		String ytrainStatusStr = Consts.ProcessInfo.TRAINPROCESSOutBesidesFinsh
				+ "";
		// 在科
		String trainStatusStrZ = Consts.ProcessInfo.TRAINPROCESSInAll + "";
		String state = Consts.STATUS_Y;
		List<TrainPlan> stuList = null;
		if (wtrainStatusStr != "" && ztrainStatusStr != ""
				&& ytrainStatusStr != "" && state != "") {
			// 获取当前时间
			java.util.Date date = new java.util.Date();
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			Integer attend_date = Integer.parseInt(format.format(date));
			trainPlan.setAttend_date(attend_date);
			trainPlan.setWtrainStatusStr(wtrainStatusStr);
			trainPlan.setZtrainStatusStr(ztrainStatusStr);
			trainPlan.setYtrainStatusStr(ytrainStatusStr);
			trainPlan.setTrainStatusStrZ(trainStatusStrZ);
			trainPlan.setState(state);
			stuList = trainPlanDao.orgaPageInfo(trainPlan);
		}
		return stuList;
	}

	/**
	 * 根据教学秘书id查询学生信息
	 * 
	 * @param trainplan
	 * @return List<TrainPlan>
	 * @author guocc
	 * @date 2017年5月26日
	 */
	public List<TrainPlan> selectListBySecretaryAuthId(TrainPlan trainplan) {
		return trainPlanDao.selectListBySecretaryAuthId(trainplan);
	}

	/**
	 * 获取个人未考勤天数
	 * 
	 * @param stuAttendanceInfo
	 * @return int
	 * @author guocc
	 * @date 2017年4月21日
	 */
	public int selectNonAttendanceCountHalf(StuAttendanceInfo stuAttendanceInfo) {
		return trainPlanDao.selectNonAttendanceCountHalf(stuAttendanceInfo);
	}

	public List<TrainPlan> selectPlanList(TrainPlan trainplan) {
		return trainPlanDao.selectPlanList(trainplan);
	}

	public Object exportStuTrainPlan(HttpServletResponse response,
			HttpServletRequest req) {
		String result = "true";
		try {
			String fileName = "";
			StudentInfo student = new StudentInfo();
			student.setState("Y");
			fileName = "轮转排班.xls";
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型

			// 定义输出流，以便打开保存对话框_______________________end

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(fileName);
			sheet.setDefaultColumnWidth(12); // 默认列宽

			HSSFFont font = wb.createFont();
			font.setFontName("黑体");
			font.setFontHeightInPoints((short) 13);// 设置字体大小
			// font.setColor(HSSFColor.WHITE.index); // 字体颜色

			HSSFCellStyle headStyle = wb.createCellStyle(); // 头部样式
			headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
			headStyle.setWrapText(true);
			headStyle.setAlignment((short) 2);
			headStyle.setVerticalAlignment((short) 1);
			headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
			// headStyle.setFillForegroundColor(HSSFColor.TEAL.index);// 设置背景色
			// headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			headStyle.setFont(font);// 选择需要用到的字体格式

			HSSFCellStyle contentStyle = wb.createCellStyle(); // 内容样式
			contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
			contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
			contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
			contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
			contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
			contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

			student.setStu_status("2");
			List<StudentInfo> list = studentInfoDao.selectListStu(student);
			HSSFRow row1 = sheet.createRow((short) 0);
			row1.createCell(0).setCellStyle(headStyle);
			row1.getCell(0).setCellValue("   周次 学生  ");
			// 画线(由左上到右下的斜线)
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255,
					(short) 0, 0, (short) 0, 0);
			HSSFSimpleShape shape1 = patriarch.createSimpleShape(a);
			shape1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
			shape1.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);
			for (int i = 1; i < 52; i++) {
				row1.setHeight((short) (15.625 * 50));// 目的是想把行高设置成25px
				row1.createCell(i).setCellStyle(headStyle);
				row1.getCell(i).setCellValue("第" + i + "周");
			}
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					HSSFRow row = sheet.createRow(i + 1);
					row.setHeight((short) (15.625 * 40));// 目的是想把行高设置成25px
					row.createCell(0).setCellStyle(contentStyle);
					row.getCell(0).setCellValue(list.get(i).getStu_name());
					if (list.get(i).getStu_auth_id() != null) {
						TrainPlan plan = new TrainPlan();
						plan.setStu_auth_id(list.get(i).getStu_auth_id());
						plan.setState("Y");
						plan.setOrderCondition(" train_start_time");
						List<TrainPlan> tpList = trainPlanDao
								.selectPlanList(plan);
						if (tpList != null && tpList.size() > 0) {
							int num = 0;
							for (int j = 0; j < tpList.size(); j++) {
								int weeks = tpList.get(j).getDays() / 7;
								row.createCell(num + 1).setCellStyle(
										contentStyle);
								row.getCell(num + 1).setCellValue(
										tpList.get(j).getOrga_name());
								// System.out.println(tpList.get(j).getOrga_name()+":"+(num+1)+"~"+(num+weeks));
								CellRangeAddress cra = new CellRangeAddress(
										(i + 1), (i + 1), (num + 1),
										(num + weeks));
								sheet.addMergedRegion(cra);
								RegionUtil.setBorderBottom(1, cra, sheet, wb);
								RegionUtil.setBorderLeft(1, cra, sheet, wb);
								RegionUtil.setBorderRight(1, cra, sheet, wb);
								RegionUtil.setBorderTop(1, cra, sheet, wb);
								num = num + weeks;
							}
						}
					}
				}
			}
			wb.write(response.getOutputStream());
		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取提前入科的学生列表
	 * 
	 * @param trainPlan
	 * @return
	 * @author guocc
	 * @date 2017年7月3日
	 */
	public List<TrainPlan> getAdvanceStuInfo(TrainPlan trainPlan) {
		return trainPlanDao.getAdvanceStuInfo(trainPlan);
	}

	public TrainPlan getOrga_name(TrainPlan trainPlan) {
		return trainPlanDao.getOrga_name(trainPlan);
	}

	/**
	 * 查询讨论人和阐述人信息
	 * 
	 * @param trainPlan
	 * @return
	 * @author guocc
	 * @date 2017年9月4日
	 */
	public List<TrainPlan> selectDiscussantInfo(TrainPlan trainPlan) {
		return trainPlanDao.selectDiscussantInfo(trainPlan);
	}

	public List<TrainPlan> selectAllStuByTea(TrainPlan plan) {

		return trainPlanDao.selectAllStuByTea(plan);
	}

	public List<TrainPlan> selectListByHomeworkAuthId(TrainPlan plan) {
		// TODO Auto-generated method stub
		return trainPlanDao.selectListByHomeworkAuthId(plan);
	}

	public boolean saveHtmlData(HttpServletRequest req) {
		// 从内存中读取当前操作人
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		String content = req.getParameter("content");
		int stuAuthId = loginBean.getvUserDetailInfo().getAuth_id();
		// html表单存入表单记录表
		// 1、先判断是否有数据，先删除掉之前的数据
		int orgaId = loginBean.getvUserDetailInfo().getOrga_id();
		if (StringUtil.isNotEmpty(stuAuthId + "") && orgaId > 0) {
			ScoFormMain _sfm = new ScoFormMain();
			_sfm.setType_id(1);
			_sfm.setUser_auth_id(stuAuthId);
			_sfm.setOrga_id(orgaId);
			scoFormMainDao.delStuOldData(_sfm);
		}
		// 2、然后新增数据进去
		ScoFormMain sfm = new ScoFormMain();
		sfm.setId(scoFormMainDao.getSequence());
		sfm.setType_id(1);
		sfm.setForm_type(2);
		sfm.setUser_auth_id(stuAuthId);
		sfm.setOrga_id(loginBean.getvUserDetailInfo().getOrga_id());
		sfm.setHtml_content(content);
		sfm.setCreate_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
		sfm.setCreate_time(new java.sql.Timestamp(new java.util.Date()
				.getTime()));
		sfm.setState("Y");
		int f1 = scoFormMainDao.add(sfm);
		// 获取发送消息开关信息
		SysConfig sysConfig = new SysConfig();
		sysConfig.setConfig_code("message_user");
		sysConfig.setConfig_flag(1);
		List<SysConfig> list = sysConfigDao.selectList(sysConfig);
		int f4 = 0;
		if (list.size() > 0) {
			String[] data = null;
			if (list.get(0).getConfig_data().contains(";")) {
				data = list.get(0).getConfig_data().split(";");
			} else {
				data = new String[] { list.get(0).getConfig_data() };
			}
			for (int j = 0; j < data.length; j++) {
				if (data[j].equals("20")) {
					TrainPlan trainPlan = new TrainPlan();
					trainPlan.setStu_auth_id(stuAuthId);
					trainPlan.setTrain_dept_id(orgaId + "");
					trainPlan.setState("Y");
					trainPlan.setTrain_status(53);
					TrainPlan train = trainPlanDao.selectOneTrain(trainPlan);
					if (train != null) {
						f4 += sendMsgToStu(content, loginBean,
								train.getTeacher_auth_id() + "");
					}
				} else if (data[j].equals("30")) {
					UserAuthority userAuthority = new UserAuthority();
					userAuthority.setRole_id(30);
					userAuthority.setState("Y");
					userAuthority.setOrga_id(orgaId);
					List<UserAuthority> userList = userAuthorityDao
							.selectMessagePeo(userAuthority);
					for (int i = 0; i < userList.size(); i++) {
						f4 += sendMsgToStu(content, loginBean, userList.get(i)
								.getAuth_id() + "");
					}
				}
			}
		} else {
			UserAuthority userAuthority = new UserAuthority();
			userAuthority.setRole_id(30);
			userAuthority.setState("Y");
			userAuthority.setOrga_id(orgaId);
			List<UserAuthority> userList = userAuthorityDao
					.selectMessagePeo(userAuthority);
			for (int i = 0; i < userList.size(); i++) {
				f4 += sendMsgToStu(content, loginBean, userList.get(i)
						.getAuth_id() + "");
			}
		}
		// 判断
		if (f1 > 0 && f4 > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Object getTrainStudent(int page, int rows, TrainPlan trainPlan, HttpServletRequest req) throws Exception {
		JSONObject jobj = new JSONObject();
		String kind=req.getParameter("_kind");
		List<TrainPlan> list=new ArrayList<TrainPlan>();
		int totalCount=0;
		if(StringUtil.isNotEmpty(kind)){
			if("1".equals(kind)){
				list =trainPlanDao.getTrainStudent(new RowBounds((page - 1) * rows, page * rows),trainPlan);
				totalCount=	trainPlanDao.getTrainStudentCount(trainPlan);
			}else{
				LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
				Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
				TrainPlan plan = new TrainPlan();
				plan.setTrain_dept_id(orga_id.toString());
				plan.setTrain_status(Consts.ProcessInfo.TRAINPROCESSNull);
				List<TrainPlan> trainPlanList = trainPlanDao.getAdvanceStuInfo(trainPlan);
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
				for (TrainPlan trainPlanGet : trainPlanList) {
					if (DateUtil.daysBetween(sdf.parse(today.toString()), sdf.parse(trainPlanGet.getTrain_start_date().toString())) > 0 && DateUtil.daysBetween(sdf.parse(today.toString()), sdf.parse(trainPlanGet.getTrain_start_date().toString())) <= days_advanceAdmission) {
						TrainPlan tp1 = trainPlanDao.getOrga_name(trainPlanGet);//  当前科室的信息
						if (tp1 != null) {
							trainPlanGet.setOrga_name(tp1.getOrga_name());
						}
						trainPlanGet.setDays(DateUtil.daysBetween(sdf.parse(today.toString()), sdf.parse(trainPlanGet.getTrain_start_date().toString())));
						list.add(trainPlanGet);
					}
				}
				totalCount=	list.size();
			}
		
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", page);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", list);// 显示的具体数据，jsonarray格式。
		}
		return jobj;
	}

	public List<TrainPlan> selectListTrain(TrainPlan plan) {
		return trainPlanDao.selectListTrain(plan);
	}

}