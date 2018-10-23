package com.rensu.education.hctms.util;

/**
 * 保存系统常量信息
 * @author Chen Xiaoxiao
 * @date 2016-04-25
 */
public class Consts {

	//用户登录成功后保存在session中的用户信息key
	public static final String SESSION_LOGIN_KEY = "LOGIN_INFO";
	//用户登录成功后保存在session中的菜单信息
	public static final String SESSION_MENU_LIST = "MENU_LIST";
	//用户登录成功后系统配置信息
	public static final String SESSION_SYSTEM_DICT = "SYSTEM_DICT";
	//用户登录成功后文书类型 doc_type_config
	public static final String SESSION_DOC_TYPE = "DOC_TYPE";
	public static final String SESSION_DOC_TYPE_ROOT = "TREE_ROOT";//文书类型顶级类型编码
	//科室信息，该科室是规培系统的科室，不是其他系统的科室信息。
	public static final String SESSION_TRAIN_DEPT = "TRAIN_DEPT";
	//诊断字典
	public static final String SESSION_DIAGNOSE_DICT_ZY = "DIAGNOSE_DICT_ZY";//中医
	public static final String SESSION_DIAGNOSE_DICT_XY = "DIAGNOSE_DICT_XY";//西医
	public static final String SESSION_DIAGNOSE_DICT_YZB = "DIAGNOSE_DICT_YZB";//院自备
	//药品字典
	public static final String SESSION_DRUG_DICT_ZY = "DRUG_DICT_ZY";
	public static final String SESSION_DRUG_DICT_XY = "DRUG_DICT_XY";
	public static final String SESSION_DRUG_DICT_ZCY = "DRUG_DICT_ZCY";
	//中医药方字典
	public static final String SESSION_TRADITIONAL_RECIPE_DICT = "TRADITIONAL_RECIPE_DICT";
	//检查字典
	public static final String SESSION_EXAM_DICT = "EXAM_DICT";
	//检验字典
	public static final String SESSION_LAB_DICT_TREE = "LAB_DICT_TREE";//树形集合
	public static final String SESSION_LAB_DICT_PY = "LAB_DICT_PY";//拼音码集合
	//手术字典
	public static final String SESSION_OPERATION_DICT = "OPERATION_DICT";
	//系统配置
	public static final String SESSION_SYS_CONFIG = "SYS_CONFIG";
	public static final String SESSION_SYS_CONFIG_SIMPLE = "SYS_CONFIG_SIMPLE";
	
	//全局成功失败返回文本
	public static final String res_success = "操作成功！";
	public static final String res_failure = "操作失败...";
	//全局成功失败返回状态
	public static final String res_success_flag = "1";
	public static final String res_failure_flag = "-1";
	
	public static final String SPLIT_FLAG = "@_@";
	public static final String SPLIT_FLAG_SEMI = ";";
	public static final String SPLIT_FLAG_COMMA = ",";
	public static final String SPLIT_FLAG_SHORT_STRING = "-";
	
	
	public static final String SYS_ENCLOSURE_TYPE_PUBLIC_DATA = "PUBLIC_DATA";//附件类型之公共资料
	public static final String SYS_ENCLOSURE_TYPE_CATHETRA_DATA = "CATHETRA_DATA";//附件类型之讲座记录资料
	
	//状态值。一般通用表中的状态值，Y-可用；X-删除。
	public static final String STATUS_Y = "Y";
	public static final String STATUS_X = "X";
	
	//操作
	public static final String OPT_ADD = "add";
	public static final String OPT_EDIT = "edit";
	public static final String OPT_DELETE = "delete";
	
	//医院名称
	public static final String HOSPITAL_NAME = "南京市中西医结合医院";
	
	//校区课程时间表
	public static final String SCHOOL_DISTRICT = "SCHOOL_DISTRICT";
	public static final String TIMETABLE = "timetable";
	
	//教学类型
	public static final String TEACH_TYPE = "TEACH_TYPE";
	//学期信息
	public static final String TERM_INFO = "TERM_INFO";
	
	//机构表学院列表
	public static final String ORGA_DEPT_MAP = "ORGA_DEPT_MAP";
	
	//入学年份列表
	public static final String START_YEAR_LIST = "START_YEAR_LIST";
	
	//roleInfo信息
	public static final String SESSION_ROLE_INFO = "SESSION_ROLE_INFO";

	//orgaInfo信息
	public static final String SESSION_ORGA_INFO = "SESSION_ORGA_INFO";
	
	//学生类型
	public static final String SESSION_STU_TYPE = "SESSION_STU_TYPE";

	//系统字典主表SYS_DICT_MAIN
	public static final String SESSION_SYS_DICT_MAIN = "SESSION_SYS_DICT_MAIN";
	//系统字典子表SYS_DICT_SUB
	public static final String SESSION_SYS_DICT_SUB = "SESSION_SYS_DICT_SUB";
	
	//流程配置表
	public static final String SESSION_PROCESS_INFO = "SESSION_PROCESS_INFO";
	
	//学生届次表
	public static final String STU_CLASS = "STU_CLASS";


	/**
	 * 系统配置表定义常量-开关编码
	 * @author Chen Xiaoxiao
	 * @date 2015年12月16日
	 */
	public class CSysConfig {
		public static final String SYS_PASSWORD = "SYS_PASSWORD";
		public static final String END_MEDICALCASE_BYCHANGETIME = "END_MEDICALCASE_BYCHANGETIME";
		public static final String ENABLE_MEDICALINSTRUMENTS = "ENABLE_MEDICALINSTRUMENTS";
		public static final String SCORE_STATISTICS_QUERY_PERCANTAGE = "SCORE_STATISTICS_QUERY_PERCANTAGE";
		public static final String ALERT_BEFORECLOSE_TIME = "ALERT_BEFORECLOSE_TIME";
		public static final String ENABLE_MARKMEDICALINSTRUMENTS = "ENABLE_MARKMEDICALINSTRUMENTS";
		public static final String ENABLE_CORRENTMEDICALINSTRUMENTS = "ENABLE_CORRENTMEDICALINSTRUMENTS";
		public static final String MAINPAGE_SHOWDATANUM = "MAINPAGE_SHOWDATANUM";
		public static final String TEACHER_WORKLOAD_STATISTICS_QUERY_PERCANTAGE = "TEACHER_WORKLOAD_STATISTICS_QUERY_PERCANTAGE";
		public static final String THREAD_START_TIME_DAILY = "THREAD_START_TIME_DAILY";
		public static final String TEACHER_WORK_QUERY_DAYS = "TEACHER_WORK_QUERY_DAYS";
	}
	
	/**
	 * 用户角色常量-角色编码
	 * @author Chen Xiaoxiao
	 * @date 2015年12月16日
	 */
	public class CRoleInfo {
		public static final String T_STU = "T_STU";  //学生
		public static final String T_TEACH = "T_TEACH";  //教师
		public static final String T_INSTRUCTOR = "T_INSTRUCTOR";  //辅导员
		public static final String T_MASTER = "T_MASTER";  //班主任
		public static final String T_COL_SEC = "T_COL_SEC";  //学院秘书
		public static final String T_RES_SEC = "T_RES_SEC";  //教研室主任
		public static final String T_EDUCATIONAL = "T_EDUCATIONAL";  //教务处
		public static final String T_SYSADMIN = "T_SYSADMIN";  //系统管理员
		
	}
	
	/**
	 * 各个表的名称--只有使用到的才定义
	 * @author Chen Xiaoxiao
	 * @date 2015年12月16日
	 */
	public class CTableName {
		public static final String TASK_DEAL = "TASK_DEAL";
		public static final String MEDICAL_CASE_MAIN = "MEDICAL_CASE_MAIN";
		public static final String DOC_TYPE_CONFIG = "DOC_TYPE_CONFIG";
		public static final String SCO_EVAL = "SCO_EVAL";
		public static final String EXP_LAB = "EXP_LAB";
		public static final String EXP_PROJECT = "EXP_PROJECT";
		public static final String EXP_STU_RESERVE = "EXP_STU_RESERVE";
		public static final String ROLE_INFO = "ROLE_INFO";
		public static final String USER_INFO = "USER_INFO";
		public static final String USER_AUTHORITY = "USER_AUTHORITY";
	}
	
	/**
	 * 文书类型-编码常量
	 * @author Chen Xiaoxiao
	 * @date 2015年12月16日
	 */
	public class CDocType {
		public static final String BLTL = "BLTL";//病例讨论
		public static final String BLTL_DISCUSS = "BLTL_DISCUSS";//病例讨论之学生讨论
		public static final String BLTL_EXPOUND = "BLTL_EXPOUND";//病例讨论之学生阐述
	}
	
	/**
	 * 系统字典常量
	 * @author Anz
	 * @date 2015年12月18日
	 */
	public class CSystemDictSub {
		public static final String SEX = "SEX";//性别
		public static final String NATION = "NATION";//民族
		public static final String SCHOOL = "SCHOOL";//学校
		public static final String EDU_LEVEL = "EDU_LEVEL";//文化水平
		public static final String COUNTRY = "COUNTRY";//国籍
		public static final String MAJOR = "MAJOR";//专业
		public static final String STU_POSITION = "STU_POSITION";//职务
		public static final String MAJOR_DIRECTION = "MAJOR_DIRECTION";//专业方向
		public static final String POLITICAL_STATUS = "POLITICAL_STATUS";//政治面貌
		public static final String PRAC_TYPE = "PRAC_TYPE";//执业类型
		public static final String STU_TYPE = "STU_TYPE";//规培生类型
	}
	
	/**
	 * 流程表流程ID
	 * @author Administrator
	 * @date 2017年04月22日
	 */
	public class ProcessInfo {
		public static final int TRAINPROCESSNull = 51; //未轮转
		public static final int TRAINPROCESS0 = 52; //入科报到
		public static final int TRAINPROCESS1 = 53; //轮转学习
		public static final int TRAINPROCESS2 = 54; //学生出科申请
		public static final int TRAINPROCESS3 = 55; //老师/教学秘书审核
		public static final int TRAINPROCESS4 = 56; //科主任审核
		public static final int TRAINPROCESS5 = 57; //科教科审核
		public static final int TRAINPROCESS6 = 58; //出科完成
		//
		public static final String TRAINPROCESSIn = "53"; //学生轮转中
		public static final String TRAINPROCESSOut = "54,55,56,57,58"; //学生待出科
		//
		public static final String TRAINPROCESSD = "52"; //待入科

		//已经发起出科审核 guocc 2017-04-24
		public static final String TRAINPROCESSChecking = "54,55,56,57,58"; //已经发起出科审核
		public static final String TRAINPROCESSInAll=  "53,54,55,56,57"; //所有已经入科的学生
		public static final String TRAINPROCESSAll="51,52,53,54,55,56,57,58" ;  //所有学生
		
		public static final String TRAINPROCESSOutBesidesFinsh = "54,55,56,57"; //学生待出科除了完成的
		public static final String TRAINPROCESZD = "52,53,54,55,56,57" ;  //所有待入，轮转中，待出科的学生

		//
		public static final int VACATENull = 64; //驳回申请
		public static final int VACATE0 = 61; //学生申请
		public static final int VACATE1 = 62; //带教老师审核
		public static final int VACATE2 = 63; //教学秘书审核
		public static final int VACATE3 = 65; //科教科审核
		public static final String TRAINPROCESSALLOUT="52,53,54,55,56,57,58";//所有除了未轮转的学生
	
		
		

		//轮转中
		public static final String TRAINPROCESSZ = "52,53,54,55,56,57";
	
		
		
	}
	
}
