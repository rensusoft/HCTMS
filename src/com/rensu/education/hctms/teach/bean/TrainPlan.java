package com.rensu.education.hctms.teach.bean;

import com.rensu.education.hctms.core.bean.BaseBean;

public class TrainPlan extends BaseBean {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;//   标识
	private Integer stu_auth_id;//   学生权限ID
	private String train_dept_id;//   轮转科室ID
	private Integer train_start_date;//   轮转开始日期
	private Integer train_end_date;//   轮转结束日期
	private java.sql.Timestamp train_start_time;//   轮转开始时间
	private java.sql.Timestamp train_end_time;//   轮转结束时间
	private String train_start_str;//   轮转开始时间
	private String train_end_str;//   轮转结束时间
	private Integer teacher_auth_id;//   带教老师权限ID
	private java.sql.Timestamp dept_receive_date;//   科室报到时间
	private String dept_receive_str;//   科室报到时间
	private Integer train_status;//   轮转状态（到`查看；默认空）
	private String state;//   状态
	private String orga_name;  //科室名字
	private Integer director_auth_id;//   科主任权限ID
	private Integer secretary_auth_id;//   教学秘书权限ID
	private String teach_name;//   带教老师
	private String director_name;//   科主任
	private String secretary_name;//  教学秘书
	private String proc_name;//  流程名称
	private Integer tpc_id;//  流程名称
	private Integer finnish_num;//  完成数量
	private Integer order_num;//  要求数量
	private Integer completion_rate;//  完成的百分比
	private String req_content;//   TRAIN_PLAN_CONFIG中req_content（急诊科轮转信息规范）
	private String name;//   学生姓名 
	private String userCode;  
	private String typeName; //学生类型名字
	private String sex ;// 学生性别
	private String stu_age_str;// 学生年龄
	private String deptReceiveStr;//科室报到时间
	private Integer examine_state;//审核状态
	private Integer lastDay;  //轮转剩余天数
	private String train_end_strTime;//   科室报到时间
	private Integer counts;//总数
	private String auth_ids;//总数
	private double completion_type;// 学生类型的百分比
	
	private String trainStatus;//   轮转状态（多个）
	private Integer stuTypeId;    //学生类型id
	private String mobile; //手机号
	private String stu_major_name;//专业名称
	
	private Integer todoCount;//入科报到人数
	private Integer doingCount;//轮转学习人数
	private Integer didCount;//出科完成人数 
	private Integer orga_id;
	
	private String proc_num_str;//   获取轮转状态的流程序号
	private String train_status_str;//  轮转状态（轮转中/待出科）  
	
	private java.sql.Timestamp attend_time;//   当天考勤时间
	private String attend_state_str;//   考勤状况 
	private Integer attend_date;//当天考勤日期 
	
	private Integer daysAwayFromTrainEndTime;//今天距离出科的天数  
	
	private String train_status_arry;//   轮转状态（轮转中/待出科） 
	
	private Integer stu_type_id;//   学生类型id 
	private Integer q_num;
	private Integer d_num;
	private Integer tea_num;
	private Integer wstu_num;
	private Integer zstu_num;
	private Integer ystu_num;
	private String stu_num;


	//未轮转状态
	private	String wtrainStatusStr;
	//轮转中状态
	private	String ztrainStatusStr;
	//待出科状态
	private	String ytrainStatusStr; 
	//在科状态
	private	String trainStatusStrZ; 
	
	private Integer theory_sco;
	private Integer skill_sco;
	
	private Integer days;
	private Integer config_id;
	private Integer tsc_id;
	
	private String duration;
	private String duration_unit;
	
	private String stuClassName;  //学生届次
	
	
	public String getStu_age_str() {
		return stu_age_str;
	}
	public void setStu_age_str(String stu_age_str) {
		this.stu_age_str = stu_age_str;
	}
	public String getStu_major_name() {
		return stu_major_name;
	}
	public void setStu_major_name(String stu_major_name) {
		this.stu_major_name = stu_major_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDuration_unit() {
		return duration_unit;
	}
	public void setDuration_unit(String duration_unit) {
		this.duration_unit = duration_unit;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getConfig_id() {
		return config_id;
	}
	public void setConfig_id(Integer config_id) {
		this.config_id = config_id;
	}
	public Integer getTsc_id() {
		return tsc_id;
	}
	public void setTsc_id(Integer tsc_id) {
		this.tsc_id = tsc_id;
	}
	public Integer getTheory_sco() {
		return theory_sco;
	}
	public void setTheory_sco(Integer theory_sco) {
		this.theory_sco = theory_sco;
	}
	public Integer getSkill_sco() {
		return skill_sco;
	}
	public void setSkill_sco(Integer skill_sco) {
		this.skill_sco = skill_sco;
	}
	public String getStu_num() {
		return stu_num;
	}
	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}
	public Integer getStu_type_id() {
		return stu_type_id;
	}
	public void setStu_type_id(Integer stu_type_id) {
		this.stu_type_id = stu_type_id;
	}
	public String getTrain_status_arry() {
		return train_status_arry;
	}
	public void setTrain_status_arry(String train_status_arry) {
		this.train_status_arry = train_status_arry;
	}
	public Integer getDaysAwayFromTrainEndTime() {
		return daysAwayFromTrainEndTime;
	}
	public void setDaysAwayFromTrainEndTime(Integer daysAwayFromTrainEndTime) {
		this.daysAwayFromTrainEndTime = daysAwayFromTrainEndTime;
	}
	public Integer getAttend_date() {
		return attend_date;
	}
	public void setAttend_date(Integer attend_date) {
		this.attend_date = attend_date;
	}
	public String getAttend_state_str() {
		return attend_state_str;
	}
	public void setAttend_state_str(String attend_state_str) {
		this.attend_state_str = attend_state_str;
	}
	public java.sql.Timestamp getAttend_time() {
		return attend_time;
	}
	public void setAttend_time(java.sql.Timestamp attend_time) {
		this.attend_time = attend_time;
	}
	public String getTrain_status_str() {
		return train_status_str;
	}
	public void setTrain_status_str(String train_status_str) {
		this.train_status_str = train_status_str;
	}
	public String getProc_num_str() {
		return proc_num_str;
	}
	public void setProc_num_str(String proc_num_str) {
		this.proc_num_str = proc_num_str;
	}
	
	private java.sql.Timestamp newDate;    //系统当前时间
	
	public Integer getTodoCount() {
		return todoCount;
	}
	public void setTodoCount(Integer todoCount) {
		this.todoCount = todoCount;
	}
	public Integer getDoingCount() {
		return doingCount;
	}
	public void setDoingCount(Integer doingCount) {
		this.doingCount = doingCount;
	}
	public Integer getDidCount() {
		return didCount;
	}
	public void setDidCount(Integer didCount) {
		this.didCount = didCount;
	}

	public Integer getTpc_id() {
		return tpc_id;
	}
	public void setTpc_id(Integer tpc_id) {
		this.tpc_id = tpc_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStu_auth_id() {
		return stu_auth_id;
	}
	public void setStu_auth_id(Integer stu_auth_id) {
		this.stu_auth_id = stu_auth_id;
	}
	public String getTrain_dept_id() {
		return train_dept_id;
	}
	public void setTrain_dept_id(String train_dept_id) {
		this.train_dept_id = train_dept_id;
	}
	public Integer getTrain_start_date() {
		return train_start_date;
	}
	public void setTrain_start_date(Integer train_start_date) {
		this.train_start_date = train_start_date;
	}
	public Integer getTrain_end_date() {
		return train_end_date;
	}
	public void setTrain_end_date(Integer train_end_date) {
		this.train_end_date = train_end_date;
	}
	public java.sql.Timestamp getTrain_start_time() {
		return train_start_time;
	}
	public void setTrain_start_time(java.sql.Timestamp train_start_time) {
		this.train_start_time = train_start_time;
	}
	public java.sql.Timestamp getTrain_end_time() {
		return train_end_time;
	}
	public void setTrain_end_time(java.sql.Timestamp train_end_time) {
		this.train_end_time = train_end_time;
	}
	public Integer getTeacher_auth_id() {
		return teacher_auth_id;
	}
	public void setTeacher_auth_id(Integer teacher_auth_id) {
		this.teacher_auth_id = teacher_auth_id;
	}
	public java.sql.Timestamp getDept_receive_date() {
		return dept_receive_date;
	}
	public void setDept_receive_date(java.sql.Timestamp dept_receive_date) {
		this.dept_receive_date = dept_receive_date;
	}
	public Integer getTrain_status() {
		return train_status;
	}
	public void setTrain_status(Integer train_status) {
		this.train_status = train_status;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public Integer getDirector_auth_id() {
		return director_auth_id;
	}
	public void setDirector_auth_id(Integer director_auth_id) {
		this.director_auth_id = director_auth_id;
	}
	public Integer getSecretary_auth_id() {
		return secretary_auth_id;
	}
	public void setSecretary_auth_id(Integer secretary_auth_id) {
		this.secretary_auth_id = secretary_auth_id;
	}
	public String getTeach_name() {
		return teach_name;
	}
	public void setTeach_name(String teach_name) {
		this.teach_name = teach_name;
	}
	public String getDirector_name() {
		return director_name;
	}
	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}
	public String getSecretary_name() {
		return secretary_name;
	}
	public void setSecretary_name(String secretary_name) {
		this.secretary_name = secretary_name;
	}
	public String getProc_name() {
		return proc_name;
	}
	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}
	public String getTrain_start_str() {
		return train_start_str;
	}
	public void setTrain_start_str(String train_start_str) {
		this.train_start_str = train_start_str;
	}
	public String getTrain_end_str() {
		return train_end_str;
	}
	public void setTrain_end_str(String train_end_str) {
		this.train_end_str = train_end_str;
	}
	public String getDept_receive_str() {
		return dept_receive_str;
	}
	public void setDept_receive_str(String dept_receive_str) {
		this.dept_receive_str = dept_receive_str;
	}
	public Integer getFinnish_num() {
		return finnish_num;
	}
	public void setFinnish_num(Integer finnish_num) {
		this.finnish_num = finnish_num;
	}
	public Integer getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}
	public String getReq_content() {
		return req_content;
	}
	public void setReq_content(String req_content) {
		this.req_content = req_content;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDeptReceiveStr() {
		return deptReceiveStr;
	}
	public void setDeptReceiveStr(String deptReceiveStr) {
		this.deptReceiveStr = deptReceiveStr;
	}
	public Integer getExamine_state() {
		return examine_state;
	}
	public void setExamine_state(Integer examine_state) {
		this.examine_state = examine_state;
	}
	public Integer getLastDay() {
		return lastDay;
	}
	public void setLastDay(Integer lastDay) {
		this.lastDay = lastDay;
	}
	public String getTrain_end_strTime() {
		return train_end_strTime;
	}
	public void setTrain_end_strTime(String train_end_strTime) {
		this.train_end_strTime = train_end_strTime;
	}
	
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public Integer getCompletion_rate() {
		return completion_rate;
	}
	public void setCompletion_rate(Integer completion_rate) {
		this.completion_rate = completion_rate;
	}
	public String getAuth_ids() {
		return auth_ids;
	}
	public void setAuth_ids(String auth_ids) {
		this.auth_ids = auth_ids;
	}
	public double getCompletion_type() {
		return completion_type;
	}
	public void setCompletion_type(double completion_type) {
		this.completion_type = completion_type;
	}
	public String getTrainStatus() {
		return trainStatus;
	}
	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}
	public Integer getStuTypeId() {
		return stuTypeId;
	}
	public void setStuTypeId(Integer stuTypeId) {
		this.stuTypeId = stuTypeId;
	}
	public java.sql.Timestamp getNewDate() {
		return newDate;
	}
	public void setNewDate(java.sql.Timestamp newDate) {
		this.newDate = newDate;
	}
	public String getWtrainStatusStr() {
		return wtrainStatusStr;
	}
	public void setWtrainStatusStr(String wtrainStatusStr) {
		this.wtrainStatusStr = wtrainStatusStr;
	}
	public String getZtrainStatusStr() {
		return ztrainStatusStr;
	}
	public void setZtrainStatusStr(String ztrainStatusStr) {
		this.ztrainStatusStr = ztrainStatusStr;
	}
	public String getYtrainStatusStr() {
		return ytrainStatusStr;
	}
	public void setYtrainStatusStr(String ytrainStatusStr) {
		this.ytrainStatusStr = ytrainStatusStr;
	}
	public Integer getQ_num() {
		return q_num;
	}
	public void setQ_num(Integer q_num) {
		this.q_num = q_num;
	}
	public Integer getD_num() {
		return d_num;
	}
	public void setD_num(Integer d_num) {
		this.d_num = d_num;
	}
	public Integer getTea_num() {
		return tea_num;
	}
	public void setTea_num(Integer tea_num) {
		this.tea_num = tea_num;
	}
	public Integer getWstu_num() {
		return wstu_num;
	}
	public void setWstu_num(Integer wstu_num) {
		this.wstu_num = wstu_num;
	}
	public Integer getZstu_num() {
		return zstu_num;
	}
	public void setZstu_num(Integer zstu_num) {
		this.zstu_num = zstu_num;
	}
	public Integer getYstu_num() {
		return ystu_num;
	}
	public void setYstu_num(Integer ystu_num) {
		this.ystu_num = ystu_num;
	}
	public String getTrainStatusStrZ() {
		return trainStatusStrZ;
	}
	public void setTrainStatusStrZ(String trainStatusStrZ) {
		this.trainStatusStrZ = trainStatusStrZ;
	}
	public Integer getOrga_id() {
		return orga_id;
	}
	public void setOrga_id(Integer orga_id) {
		this.orga_id = orga_id;
	}
	public String getStuClassName() {
		return stuClassName;
	}
	public void setStuClassName(String stuClassName) {
		this.stuClassName = stuClassName;
	}
	
}
