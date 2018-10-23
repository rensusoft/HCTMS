package com.rensu.education.hctms.userauth.bean;


import com.rensu.education.hctms.core.bean.BaseBean;

public class StudentInfo extends BaseBean {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;//   用户标识
	private String user_code;//   工号
	private String stu_name;//   姓名
	private String stu_sex;//   性别
	private Integer stu_age;//   年龄
	private String stu_school_code;//   所属院校ID
	private String stu_school_name;//   所属院校名称
	private String stu_num;//   学号
	private String stu_edu_level;//   文化水平
	private String stu_major_code;//   专业ID
	private String stu_major_name;//   专业名称
	private String stu_type;//   学生类型
	private String stu_type_name; 
	private String sup_doc_code;//   上级医师AuthId
	private String sup_doc_name;//   上级医师名称
	private String state;//   状态
	private java.sql.Timestamp create_time;//   创建时间
	private String creator;//   创建人
	private String remark;//   备注
	private String stu_birthday;//   出生日期
	private String stu_country;//   国籍
	private String stu_nationality;//   民族
	private String stu_native;//   籍贯
	private String stu_phone;//   手机号码
	private String stu_hk_address;//   户口地址
	private String stu_home_address;//   居住地址
	private Integer stu_class;//   届次
	private String political_status;//   政治面貌
	private String interesting_speciality;//   兴趣特长
	private String stu_position;//   职务
	private String stu_major_direction;//   专业方向
	private String tutor_workplace;//   导师单位
	private String tutor_name;//   导师姓名
	private String stu_edu_level_code;//   文化水平ID
	private String stu_status;  //学生状态
	private String data;  //学生状态
	private Integer tsc_id;//轮转方案ID
	

	private Integer stu_auth_id;
	private Integer gid;//   组别ID
	private String auth_ids;
	private Integer teacher_auth_id; //带教老师
	private Integer secretary_auth_id;//   教学秘书权限ID
	private Integer director_auth_id;//   科主任权限ID
	private Integer train_status;//   轮转状态
	private Integer train_end_date;//   轮转结束日期
	private String tea_name;//带教老师
	private Integer q_num;//缺勤天数
	private Integer over_day;//剩余天数
	private Integer outlineCom;//大纲吻合度
	private java.sql.Timestamp train_end_time;//大纲吻合度
	
	private Integer train_plan_id;
	private Integer train_dept_id;//科室ID

	private String group_no;
	
	private String orga_name;
	private String type_name; 
	private Integer user_id;
	private Integer auth_id;
	private Integer q_stuNum; //缺勤人数
	private Integer d_stuNum; //到勤人数
	private Integer hos_stu_num; //医院总人数
	private Integer attend_date; //缺勤日期
	private String date_str; 
	private Integer tea_num;
	private Integer wstu_num;
	private Integer zstu_num;
	private Integer ystu_num;
	private String ztrainStatusStr;
	private String attend_state;  //考勤状态
	private String  stuNum;
	private double que_count;// 缺勤天数
	
	private String attend_state_b;
	
	private Integer userId;
	
	private String tsc_name;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAttend_state_b() {
		return attend_state_b;
	}
	public void setAttend_state_b(String attend_state_b) {
		this.attend_state_b = attend_state_b;
	}
	private String stuName;
	
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public double getQue_count() {
		return que_count;
	}
	public void setQue_count(double que_count) {
		this.que_count = que_count;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(Integer auth_id) {
		this.auth_id = auth_id;
	}
	public String getOrga_name() {
		return orga_name;
	}
	public void setOrga_name(String orga_name) {
		this.orga_name = orga_name;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getStu_type_name() {
		return stu_type_name;
	}
	public void setStu_type_name(String stu_type_name) {
		this.stu_type_name = stu_type_name;
	}
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	public String getUser_code() {
	    return this.user_code;
	}
	public void setUser_code(String user_code) {
	    this.user_code=user_code;
	}
	public String getStu_name() {
	    return this.stu_name;
	}
	public void setStu_name(String stu_name) {
	    this.stu_name=stu_name;
	}
	public String getStu_sex() {
	    return this.stu_sex;
	}
	public void setStu_sex(String stu_sex) {
	    this.stu_sex=stu_sex;
	}
	public Integer getStu_age() {
	    return this.stu_age;
	}
	public void setStu_age(Integer stu_age) {
	    this.stu_age=stu_age;
	}
	public String getStu_school_code() {
	    return this.stu_school_code;
	}
	public void setStu_school_code(String stu_school_code) {
	    this.stu_school_code=stu_school_code;
	}
	public String getStu_school_name() {
	    return this.stu_school_name;
	}
	public void setStu_school_name(String stu_school_name) {
	    this.stu_school_name=stu_school_name;
	}
	public String getStu_num() {
	    return this.stu_num;
	}
	public void setStu_num(String stu_num) {
	    this.stu_num=stu_num;
	}
	public String getStu_edu_level() {
	    return this.stu_edu_level;
	}
	public void setStu_edu_level(String stu_edu_level) {
	    this.stu_edu_level=stu_edu_level;
	}
	public String getStu_major_code() {
	    return this.stu_major_code;
	}
	public void setStu_major_code(String stu_major_code) {
	    this.stu_major_code=stu_major_code;
	}
	public String getStu_major_name() {
	    return this.stu_major_name;
	}
	public void setStu_major_name(String stu_major_name) {
	    this.stu_major_name=stu_major_name;
	}
	public String getStu_type() {
	    return this.stu_type;
	}
	public void setStu_type(String stu_type) {
	    this.stu_type=stu_type;
	}
	public String getSup_doc_code() {
	    return this.sup_doc_code;
	}
	public void setSup_doc_code(String sup_doc_code) {
	    this.sup_doc_code=sup_doc_code;
	}
	public String getSup_doc_name() {
	    return this.sup_doc_name;
	}
	public void setSup_doc_name(String sup_doc_name) {
	    this.sup_doc_name=sup_doc_name;
	}
	public String getState() {
	    return this.state;
	}
	public void setState(String state) {
	    this.state=state;
	}
	public java.sql.Timestamp getCreate_time() {
	    return this.create_time;
	}
	public void setCreate_time(java.sql.Timestamp create_time) {
	    this.create_time=create_time;
	}
	public String getCreator() {
	    return this.creator;
	}
	public void setCreator(String creator) {
	    this.creator=creator;
	}
	public String getRemark() {
	    return this.remark;
	}
	public void setRemark(String remark) {
	    this.remark=remark;
	}
	public String getStu_birthday() {
	    return this.stu_birthday;
	}
	public void setStu_birthday(String stu_birthday) {
	    this.stu_birthday=stu_birthday;
	}
	public String getStu_country() {
	    return this.stu_country;
	}
	public void setStu_country(String stu_country) {
	    this.stu_country=stu_country;
	}
	public String getStu_nationality() {
	    return this.stu_nationality;
	}
	public void setStu_nationality(String stu_nationality) {
	    this.stu_nationality=stu_nationality;
	}
	public String getStu_native() {
	    return this.stu_native;
	}
	public void setStu_native(String stu_native) {
	    this.stu_native=stu_native;
	}
	public String getStu_phone() {
	    return this.stu_phone;
	}
	public void setStu_phone(String stu_phone) {
	    this.stu_phone=stu_phone;
	}
	public String getStu_hk_address() {
	    return this.stu_hk_address;
	}
	public void setStu_hk_address(String stu_hk_address) {
	    this.stu_hk_address=stu_hk_address;
	}
	public String getStu_home_address() {
	    return this.stu_home_address;
	}
	public void setStu_home_address(String stu_home_address) {
	    this.stu_home_address=stu_home_address;
	}
	public Integer getStu_class() {
	    return this.stu_class;
	}
	public void setStu_class(Integer stu_class) {
	    this.stu_class=stu_class;
	}
	public String getPolitical_status() {
	    return this.political_status;
	}
	public void setPolitical_status(String political_status) {
	    this.political_status=political_status;
	}
	public String getInteresting_speciality() {
	    return this.interesting_speciality;
	}
	public void setInteresting_speciality(String interesting_speciality) {
	    this.interesting_speciality=interesting_speciality;
	}
	public String getStu_position() {
	    return this.stu_position;
	}
	public void setStu_position(String stu_position) {
	    this.stu_position=stu_position;
	}
	public String getStu_major_direction() {
	    return this.stu_major_direction;
	}
	public void setStu_major_direction(String stu_major_direction) {
	    this.stu_major_direction=stu_major_direction;
	}
	public String getTutor_workplace() {
	    return this.tutor_workplace;
	}
	public void setTutor_workplace(String tutor_workplace) {
	    this.tutor_workplace=tutor_workplace;
	}
	public String getTutor_name() {
	    return this.tutor_name;
	}
	public void setTutor_name(String tutor_name) {
	    this.tutor_name=tutor_name;
	}
	public String getStu_edu_level_code() {
	    return this.stu_edu_level_code;
	}
	public void setStu_edu_level_code(String stu_edu_level_code) {
	    this.stu_edu_level_code=stu_edu_level_code;
	}
	public String getStu_status() {
		return stu_status;
	}
	public void setStu_status(String stu_status) {
		this.stu_status = stu_status;
	}
	public Integer getStu_auth_id() {
		return stu_auth_id;
	}
	public void setStu_auth_id(Integer stu_auth_id) {
		this.stu_auth_id = stu_auth_id;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getAuth_ids() {
		return auth_ids;
	}
	public void setAuth_ids(String auth_ids) {
		this.auth_ids = auth_ids;
	}
	
	public String getGroup_no() {
		return group_no;
	}
	public void setGroup_no(String group_no) {
		this.group_no = group_no;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getTeacher_auth_id() {
		return teacher_auth_id;
	}
	public void setTeacher_auth_id(Integer teacher_auth_id) {
		this.teacher_auth_id = teacher_auth_id;
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
	public Integer getTrain_status() {
		return train_status;
	}
	public void setTrain_status(Integer train_status) {
		this.train_status = train_status;
	}
	public Integer getTrain_end_date() {
		return train_end_date;
	}
	public void setTrain_end_date(Integer train_end_date) {
		this.train_end_date = train_end_date;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	public Integer getQ_num() {
		return q_num;
	}
	public void setQ_num(Integer q_num) {
		this.q_num = q_num;
	}
	public Integer getOver_day() {
		return over_day;
	}
	public void setOver_day(Integer over_day) {
		this.over_day = over_day;
	}
	public Integer getOutlineCom() {
		return outlineCom;
	}
	public void setOutlineCom(Integer outlineCom) {
		this.outlineCom = outlineCom;
	}
	public java.sql.Timestamp getTrain_end_time() {
		return train_end_time;
	}
	public void setTrain_end_time(java.sql.Timestamp train_end_time) {
		this.train_end_time = train_end_time;
	}
	public Integer getTrain_dept_id() {
		return train_dept_id;
	}
	public void setTrain_dept_id(Integer train_dept_id) {
		this.train_dept_id = train_dept_id;
	}
	public Integer getTrain_plan_id() {
		return train_plan_id;
	}
	public void setTrain_plan_id(Integer train_plan_id) {
		this.train_plan_id = train_plan_id;
	}
	public Integer getQ_stuNum() {
		return q_stuNum;
	}
	public void setQ_stuNum(Integer q_stuNum) {
		this.q_stuNum = q_stuNum;
	}
	public Integer getD_stuNum() {
		return d_stuNum;
	}
	public void setD_stuNum(Integer d_stuNum) {
		this.d_stuNum = d_stuNum;
	}
	public Integer getHos_stu_num() {
		return hos_stu_num;
	}
	public void setHos_stu_num(Integer hos_stu_num) {
		this.hos_stu_num = hos_stu_num;
	}
	public Integer getAttend_date() {
		return attend_date;
	}
	public void setAttend_date(Integer attend_date) {
		this.attend_date = attend_date;
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
	public String getDate_str() {
		return date_str;
	}
	public void setDate_str(String date_str) {
		this.date_str = date_str;
	}
	public String getZtrainStatusStr() {
		return ztrainStatusStr;
	}
	public void setZtrainStatusStr(String ztrainStatusStr) {
		this.ztrainStatusStr = ztrainStatusStr;
	}
	public String getAttend_state() {
		return attend_state;
	}
	public void setAttend_state(String attend_state) {
		this.attend_state = attend_state;
	}
	public Integer getTsc_id() {
		return tsc_id;
	}
	public void setTsc_id(Integer tsc_id) {
		this.tsc_id = tsc_id;
	}
	public String getTsc_name() {
		return tsc_name;
	}
	public void setTsc_name(String tsc_name) {
		this.tsc_name = tsc_name;
	}
	
	
}
