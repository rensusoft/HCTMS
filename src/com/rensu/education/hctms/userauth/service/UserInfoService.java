package com.rensu.education.hctms.userauth.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rensu.education.hctms.config.bean.SysConfig;
import com.rensu.education.hctms.config.bean.TrainPlanBefore;
import com.rensu.education.hctms.config.bean.TrainPlanConfig;
import com.rensu.education.hctms.config.bean.TrainSchemeConfig;
import com.rensu.education.hctms.config.dao.TrainPlanBeforeDao;
import com.rensu.education.hctms.config.dao.TrainPlanConfigDao;
import com.rensu.education.hctms.config.dao.TrainSchemeConfigDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.RoleInfo;
import com.rensu.education.hctms.userauth.bean.StaffInfo;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.bean.VUserDetailInfo;
import com.rensu.education.hctms.userauth.dao.OrgaInfoDao;
import com.rensu.education.hctms.userauth.dao.RoleInfoDao;
import com.rensu.education.hctms.userauth.dao.StaffInfoDao;
import com.rensu.education.hctms.userauth.dao.StuTypeDao;
import com.rensu.education.hctms.userauth.dao.StudentInfoDao;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.userauth.dao.UserInfoDao;
import com.rensu.education.hctms.userauth.dao.VUserDetailInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.ImportExcel;
import com.rensu.education.hctms.util.JxlUtil;
import com.rensu.education.hctms.util.StringUtil;


@Service("userInfoService")
public class UserInfoService extends BaseService<UserInfo> {
	
	Logger log = Logger.getLogger(UserInfoService.class);
	
	@Autowired
	protected UserInfoDao userInfoDao;
	@Autowired
	protected StaffInfoDao staffInfoDao;
	@Autowired
	protected StudentInfoDao studentInfoDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	@Autowired
	protected VUserDetailInfoDao vUserDetailInfoDao;
	@Autowired
	protected RoleInfoDao roleInfoDao;
	@Autowired
	private StuTypeService stuTypeService;
	@Autowired
	private TrainSchemeConfigDao trainSchemeConfigDao;
    @Autowired
    private StuTypeDao stuTypeDao;
    @Autowired
    private OrgaInfoDao orgaInfoDao;
    @Autowired
    private  TrainPlanConfigDao trainPlanConfigDao;
    @Autowired
    private TrainPlanBeforeDao trainPlanBeforeDao;
    
   
	private String msg = "";

	//用来记载已存在的工号
	String existcodes = "";
	
	/***
	 * 根据用户名密码判断是否存在用户
	 * @param pramMap
	 * @return
	 * @author yuanb
	 * @date 2016年6月6日
	 */
	public List<UserInfo> loginByCodePsd(Map<String,Object> pramMap){
		return userInfoDao.loginByCodePsd(pramMap);
	}

     /**
      * 查看所有用户信息
      * @return
      *@author huq
      *@date 2016年12月29日
      */
	public List<UserInfo> getAllUserInfo() {
		return userInfoDao.getAllUserInfo();
	}

	
	/**
	 * 删除用户基本信息同事删除用户权限信息
	 *用户删除操作。 
	 * @param userId
	 * @param userName
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年1月3日
	 */
	public boolean userDel(int userId, String userName, HttpServletRequest req) {
		int res = 0;
		//删除用户基本信息
		UserInfo user = new UserInfo();
		user.setUser_id(userId);
		user.setState("X");
		res = userInfoDao.update(user);
		if (res <= 0) {
			return false;
		}
		//删除用户权限信息
        UserAuthority userAuth=new UserAuthority();
		userAuth.setUser_id(userId);
		List<UserAuthority> userAuthList = userAuthorityDao.selectList(userAuth);
		if (userAuthList != null) {
			UserAuthority delUserAuth = new UserAuthority();
			delUserAuth.setState(Consts.STATUS_X);
			for (UserAuthority temUserAuth : userAuthList) {
				delUserAuth.setAuth_id(temUserAuth.getAuth_id());
				userAuthorityDao.update(delUserAuth);
			}
		}
		UserInfo user1 = userInfoDao.selectOne(userId);
		//删除学生表信息
		if(studentInfoDao.selectOneByUserCode(user1.getUser_code())!=null){
			StudentInfo stu =studentInfoDao.selectOneByUserCode(user1.getUser_code());	
			stu.setState("X");
			studentInfoDao.update(stu);	
		}
		//删除教职工表信息
		if(staffInfoDao.selectOneByUserCode(user1.getUser_code())!=null){
		StaffInfo sta =staffInfoDao.selectOneByUserCode(user1.getUser_code());	
		sta.setState("X");
		staffInfoDao.update(sta);
		}
		return true;
	}
	
	
	/**
	 * 添加用户信息
	 * @param action
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年1月3日
	 */
	public int userSave(String action, HttpServletRequest req){
		String user_code=req.getParameter("userCode");		
		String user_name=req.getParameter("userName");
		String mobile=req.getParameter("mobile");
		UserInfo userInfo=new UserInfo();
		userInfo.setUser_code(user_code);
		int aa=userInfoDao.selectKeYonCount(userInfo);
		if(aa>0){
			aa=-1;
			return aa;
		}
		Timestamp d = new Timestamp(System.currentTimeMillis());
		//添加个人详细信息
		String  identity=req.getParameter("identity");
		userInfo.setCreate_time(d);
		//guocc 修改于2017-4-7  读系统配置数据设置用户密码
		String user_password = "123";
		//得到系统配置的密码 （从系统内存中拿）
		List<SysConfig> sysConfigList = (List<SysConfig>)req.getSession().getServletContext().getAttribute(Consts.SESSION_SYS_CONFIG);
		for(SysConfig sysConfig : sysConfigList){
			if(sysConfig.getConfig_code() != null && sysConfig.getConfig_code().equals("default_password") && sysConfig.getConfig_flag() == 1){
				user_password = sysConfig.getConfig_data();
			}
		}
		try {
			userInfo.setUser_password(StringUtil.shaEncodeSignature(StringUtil.md5EncodeSignature(user_password)));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		userInfo.setUser_name(user_name);
		userInfo.setMobile(mobile);
		userInfo.setState("Y");
		int b=userInfoDao.getSequence();
		userInfo.setUser_id(b);
		int i=userInfoDao.add(userInfo);
//		UserAuthority authority=new UserAuthority();
//		int ab=userAuthorityDao.getSequence();
//		authority.setAuth_id(ab);
//		authority.setUser_id(b);
//		authority.setState("Y");
//		authority.setCreate_time(d);
//		int gk=userAuthorityDao.add(authority);
		int identityId=0;
		//如果是学生
		if(identity.equals("S")){
			String a=req.getParameter("stu_school_name");
			String b1=req.getParameter("stu_sex");
			String c=req.getParameter("stu_num");
			String d1=req.getParameter("stu_edu_level");
			String e=req.getParameter("stu_major_name");
			String f=req.getParameter("stu_type");
			String g=req.getParameter("sup_doc_name");
			String h=req.getParameter("stu_status");
			String i1=req.getParameter("stu_birthday");
			String j=req.getParameter("stu_country");
			String k=req.getParameter("stu_nationality");
			String l=req.getParameter("stu_native");
			String m=req.getParameter("remark");
			String n=req.getParameter("stu_hk_address");
			String o=req.getParameter("stu_class");
			String p=req.getParameter("political_status");
			String q=req.getParameter("interesting_speciality");
			String r=req.getParameter("stu_position");
			String s=req.getParameter("stu_major_direction");
			String t=req.getParameter("tutor_workplace");
			String u=req.getParameter("tutor_name");
			String v=req.getParameter("creator");
			String w=req.getParameter("stu_age");
			String x=req.getParameter("stu_home_address");
			StudentInfo student=new StudentInfo();
			student.setCreate_time(d);			
			int studentId=studentInfoDao.getSequence();
	        if(!w.equals(null)&&!w.equals("")){
	        	student.setStu_age(Integer.parseInt(w));        	
	        }
			student.setId(studentId);
			student.setStu_sex(b1);
			student.setStu_school_name(a);
			student.setStu_num(c);
			student.setStu_edu_level(d1);
			student.setStu_major_name(e);
			student.setStu_type(f);
			student.setSup_doc_name(g);
			student.setStu_status(h);
			student.setState("Y");
			student.setStu_birthday(i1);
			student.setStu_country(j);
			student.setStu_nationality(k);
			student.setStu_native(l);
			student.setRemark(m);
			student.setStu_hk_address(n);
			student.setStu_home_address(x);
			if(!o.equals(null)&&!o.equals("")){
				student.setStu_class(Integer.parseInt(o));						
			}
			student.setPolitical_status(p);
			student.setInteresting_speciality(q);
			student.setStu_position(r);
			student.setStu_major_direction(s);
			student.setTutor_workplace(t);
			student.setTutor_name(u);
			student.setCreator(v);
			student.setUser_code(user_code);
			student.setStu_name(user_name);
			student.setStu_phone(mobile);

			identityId=studentInfoDao.add(student);
			
			//添加学生角色  ID 10
			UserAuthority userAuthority=new UserAuthority();
			int auth_id=userAuthorityDao.getSequence();
			userAuthority.setAuth_id(auth_id);
			userAuthority.setRole_id(10);
			userAuthority.setUser_id(b);
			userAuthority.setState("Y");
			//添加学生类型
			userAuthority.setStu_type_id(Integer.parseInt(req.getParameter("stu_type_id")));
			userAuthorityDao.add(userAuthority);			
		}else if(identity.equals("T")){//如果是老师
			String a=req.getParameter("age");
			String b1=req.getParameter("birth");
			String c=req.getParameter("staff_native");
			String d1=req.getParameter("birth_place");
			String e=req.getParameter("professional_title");
			String f=req.getParameter("position");
			String g=req.getParameter("politicak_state");
			String h=req.getParameter("edu_level");
			
			StaffInfo staff=new StaffInfo();
			int id=staffInfoDao.getSequence();
			staff.setId(id);
			staff.setUser_code(user_code);
			staff.setName(user_name);
			staff.setAge(a);
			staff.setBirth(b1);
			staff.setStaff_native(c);
			staff.setBirth_place(d1);
			staff.setProfessional_title(e);
			staff.setPosition(f);
			staff.setPolitical_state(g);
			staff.setEdu_level(h);
			staff.setState("Y");
			staff.setCreate_time(d);
			
			identityId=staffInfoDao.add(staff);
		}
		return i;
	}


	public JSONObject selectById(UserInfo td) {
		JSONObject jobj = new JSONObject();
		List<VUserDetailInfo> dataList = userInfoDao.selectBytpId(td);
		if (dataList != null && dataList.size() > 0) {
			for (int i = 0; i < dataList.size(); i++) {
				if (dataList.get(i).getState().equals("Y")) {
					dataList.get(i).setState("可用");
				}else{
					dataList.get(i).setState("删除");
				}
			}
		}
		jobj.put("rows", dataList);//显示的具体数据，jsonarray格式。
		if(jobj!=null){
			String jsonStr = StringUtil.dnull(jobj.toString());
			jobj = JSONObject.fromObject(jsonStr);
		}
		
		return jobj;
	}
	public JSONObject selectPageByMoHu(int pageIndex, int rows, UserInfo user) {
		JSONObject jobj = new JSONObject();
		int totalCount = userInfoDao.selectCount(user);
		List<UserInfo> dataList = userInfoDao.selectPageByMoHu(new RowBounds((pageIndex - 1) * rows, pageIndex * rows), user);
		if (dataList != null && dataList.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < dataList.size(); i++) {
				if (dataList.get(i).getCreate_time() != null) {
					dataList.get(i).setCreate_time_str(
							sdf.format(dataList.get(i).getCreate_time()));
				}
				if (dataList.get(i).getState().equals("Y")) {
					dataList.get(i).setState("可用");
				}else{
					dataList.get(i).setState("删除");
				}
			}
		}
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));//总页数
		jobj.put("page", pageIndex);//当前页码
		jobj.put("records", totalCount);//总的记录数
		jobj.put("rows", dataList);//显示的具体数据，jsonarray格式。
		if(jobj!=null){
			String jsonStr = StringUtil.dnull(jobj.toString());
			jobj = JSONObject.fromObject(jsonStr);
		}
		
		return jobj;
	}
	/**
	 * 用户信息修改
	 * @param req
	 * @param userId
	 * @return
	 *@author huq
	 *@date 2017年1月9日
	 */
	public int userUpdate(HttpServletRequest req, String userId) {
		UserInfo userInfo=new UserInfo();
		int k=0;
		userInfo.setUser_id(Integer.parseInt(userId));
		userInfo.setUser_name(req.getParameter("userName"));
		userInfo.setUser_password(req.getParameter("password"));
		userInfo.setMobile(req.getParameter("mobile"));
		userInfo.setState("Y");
		k=userInfoDao.update(userInfo);
		
		//修改个人详细信息
		String  identity=req.getParameter("identity");
		int identityId=0;
		//如果是学生
		if(identity.equals("S")){
			String a=req.getParameter("stu_school_name");
			String b=req.getParameter("stu_sex");
			String c=req.getParameter("stu_num");
			String d1=req.getParameter("stu_edu_level");
			String e=req.getParameter("stu_major_name");
			String f=req.getParameter("stu_type");
			String g=req.getParameter("sup_doc_name");
			String h=req.getParameter("stu_status");
			String i=req.getParameter("stu_birthday");
			String j=req.getParameter("stu_country");
			String k1=req.getParameter("stu_nationality");
			String l=req.getParameter("stu_native");
			String m=req.getParameter("remark");
			String n=req.getParameter("stu_hk_address");
			String o=req.getParameter("stu_class");
			String p=req.getParameter("political_status");
			String q=req.getParameter("interesting_speciality");
			String r=req.getParameter("stu_position");
			String s=req.getParameter("stu_major_direction");
			String t=req.getParameter("tutor_workplace");
			String u=req.getParameter("tutor_name");
			String v=req.getParameter("creator");
			StudentInfo student=new StudentInfo();
			if(req.getParameter("studentId")!=null&&req.getParameter("studentId")!=""){
				int studentId=Integer.parseInt(req.getParameter("studentId"));		
				student.setId(studentId);		
			}
			student.setStu_sex(b);
			student.setStu_school_name(a);
			student.setStu_num(c);
			student.setStu_edu_level(d1);
			student.setStu_major_name(e);
			student.setStu_type(f);
			student.setSup_doc_name(g);
			student.setStu_status(h);
			student.setState("Y");
			student.setStu_birthday(i);
			student.setStu_country(j);
			student.setStu_nationality(k1);
			student.setStu_native(l);
			student.setRemark(m);
			student.setStu_hk_address(n);
			if(o!=null&&o!=""){
				student.setStu_class(Integer.parseInt(o));	
			}
			
			student.setPolitical_status(p);
			student.setInteresting_speciality(q);
			student.setStu_position(r);
			student.setStu_major_direction(s);
			student.setTutor_workplace(t);
			student.setTutor_name(u);
			student.setCreator(v);
			student.setUser_code(req.getParameter("userCode"));
			student.setStu_name(req.getParameter("userName"));
			student.setStu_phone(req.getParameter("mobile"));
			
			identityId=studentInfoDao.update(student);
			
			//学生角色修改
			UserAuthority userAuthority=new UserAuthority();
			userAuthority.setUser_id(Integer.parseInt(userId));
			List<UserAuthority> lists=userAuthorityDao.selectList(userAuthority);
			for (UserAuthority userAuthority2 : lists) {
				userAuthority=userAuthority2;
			}
			userAuthority.setStu_type_id(Integer.parseInt(req.getParameter("stu_type_id")));
			userAuthorityDao.update(userAuthority);
			
		}else {//如果是老师
			String a=req.getParameter("age");
			String b=req.getParameter("birth");
			String c=req.getParameter("staff_native");
			String d1=req.getParameter("birth_place");
			String e=req.getParameter("professional_title");
			String f=req.getParameter("position");
			String g=req.getParameter("political_state");
			String h=req.getParameter("edu_level");
			
			StaffInfo staff=new StaffInfo();
			int id=Integer.parseInt(req.getParameter("staffid"));
			staff.setId(id);
			staff.setUser_code(req.getParameter("userCode"));
			staff.setName(req.getParameter("userName"));
			staff.setAge(a);
			staff.setBirth(b);
			staff.setStaff_native(c);
			staff.setBirth_place(d1);
			staff.setProfessional_title(e);
			staff.setPosition(f);
			staff.setPolitical_state(g);
			staff.setEdu_level(h);
			staff.setState("Y");
			
			identityId=staffInfoDao.update(staff);
		}
		return k;
	}
	
	public void downUserInfo(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String title="学生用户信息批量导入模板";
		int index = 0;
		JxlUtil jxl = new JxlUtil(title+".xls");		
		jxl.buildSheet(title, 0);
		jxl.addHeaderCell("姓名", 0, index++);
		jxl.addHeaderCell("性别", 0, index++);
		jxl.addHeaderCell("年龄", 0, index++);
		jxl.addHeaderCell("出生日期", 0, index++);
		jxl.addHeaderCell("手机号码", 0, index++);
		jxl.addHeaderCell("国籍", 0, index++);		
		jxl.addHeaderCell("民族", 0, index++);
		jxl.addHeaderCell("籍贯", 0, index++);
		jxl.addHeaderCell("户口地", 0, index++);
		jxl.addHeaderCell("居住地", 0, index++);
		jxl.addHeaderCell("政治面貌", 0, index++);
		jxl.addHeaderCell("兴趣特长", 0, index++);
		jxl.addHeaderCell("所属院校名称", 0, index++);
		jxl.addHeaderCell("学号", 0, index++);
		jxl.addHeaderCell("届次", 0, index++);
		jxl.addHeaderCell("学生状态", 0, index++);
		jxl.addHeaderCell("职务", 0, index++);
		jxl.addHeaderCell("文化水平", 0, index++);		
		jxl.addHeaderCell("专业名称", 0, index++);
		jxl.addHeaderCell("专业方向", 0, index++);
		jxl.addHeaderCell("规培生类型", 0, index++);
		jxl.addHeaderCell("上级医师名称", 0, index++);
		jxl.addHeaderCell("导师姓名", 0, index++);
		jxl.addHeaderCell("导师单位", 0, index++);
		jxl.addHeaderCell("学生工号", 0, index++);
		jxl.addHeaderCell("学生类型", 0, index++);
		jxl.addHeaderCell("轮转方案ID", 0, index++);
		
		StuType stu=new StuType();
		List<StuType> trainDeptList=stuTypeService.selectList(stu);
		jxl.buildSheet("学生类型代码字典", 1);
		jxl.addHeaderCell("学生类型ID代码", 0, 0);	
		jxl.addHeaderCell("学生类型名称", 0, 1);
		for(int i = 0;trainDeptList != null && i < trainDeptList.size();i++){
			StuType  td = trainDeptList.get(i);
				jxl.addDataCell(td.getId().toString(), i+1, 0);
				jxl.addDataCell(td.getType_name(), i+1, 1);
		}
		
		TrainSchemeConfig config= new TrainSchemeConfig();
		config.setAvailability("Y");
		config.setState("Y");
		config.setOrderCondition("id asc ");
		List<TrainSchemeConfig> configs =trainSchemeConfigDao.selectList(config);
		//轮转方案代码
		jxl.buildSheet("轮转方案代码字典", 2);
		jxl.addHeaderCell("轮转方案ID代码", 0, 0);	
		jxl.addHeaderCell("轮转方案名称", 0, 1);
		for(int i = 0;configs != null && i < configs.size();i++){
			TrainSchemeConfig  td = configs.get(i);
				jxl.addDataCell(td.getId().toString(), i+1, 0);
				jxl.addDataCell(td.getName(), i+1, 1);
			
		}
		
		java.io.File file = jxl.getFileExcel();
		JxlUtil.downLoadExcel(file, req, res);
	}

	public String uploadTeachingTask(InputStream in, String filename) {
		return uploadTeachingTask(in, filename,"","","");
	}
	
	public String uploadTeachingTask(InputStream in, String filename,String state,String stuClassId,String stuClass) {
		List<StudentInfo> list = null;

		try {
			list = getExcelData(in, filename,state,stuClassId,stuClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list != null && list.size() > 0) {
			int num = studentInfoDao.insertWithList(list);
			// int num = teachingTaskInfoDao.add(list.get(0));
			if (num > 0) {
				msg = "true";
			}
		}
		if(!existcodes.equals("null")&&!existcodes.equals("")){
			msg=existcodes;
		}
		return msg;
	}
	
	
	/**
	 * 读取excel文档
	 * 
	 * @param fileUrl
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	public List<StudentInfo> getExcelData(InputStream in, String fileName,String state,String stuClassId,String stuClass) throws Exception {
		msg = "";
		existcodes="";
		Workbook wb = ImportExcel.openWorkbook(in, fileName);
		if (wb == null) {
			msg = "导入文件" + fileName + "扩展名异常，请确认文件格式！";
			log.info(msg);
			return null;
		}
		List<StudentInfo> list = new ArrayList<StudentInfo>();
		Sheet sheet = (Sheet) wb.getSheetAt(0);
		Row row = null;
		int totalRows = sheet.getPhysicalNumberOfRows();
		for (int r = 1; r < totalRows; r++) {
			StudentInfo student = new StudentInfo();
			UserInfo userinfo=new UserInfo();
			UserAuthority roleinfo=new UserAuthority();
			row = sheet.getRow(r);
			// if(row.getPhysicalNumberOfCells()!=13){
			// msg = "导入文件"+fileUrl+"第"+r+"行缺少数据，请检查excel并重新导入";
			// log.info(msg);
			// return null;
			// }
			HSSFCell cell = null;
			if (row.getCell(0) != null) {
				student.setStu_name(ImportExcel.getCellValue(row.getCell(0)));
				userinfo.setUser_name(ImportExcel.getCellValue(row.getCell(0)));
			}else{
				break;
			}
			if (row.getCell(1) != null) {
				student.setStu_sex(ImportExcel.getCellValue(row.getCell(1)));;
			}
			if (row.getCell(2) != null) {
				cell=(HSSFCell) row.getCell(2);
				if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
					existcodes += "第" + (r+1) + "行年龄格式错误，应为数字;";
					continue;
				}
				double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(2)));
				int b=(int)a;
				student.setStu_age(b);	
			}
			if (row.getCell(3) != null) {
				cell=(HSSFCell) row.getCell(3);
				//设置字段类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				student.setStu_birthday(cell.toString());
			}
			if (row.getCell(4) != null) {
				cell=(HSSFCell) row.getCell(4);
				//设置字段类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				student.setStu_phone(cell.toString());
				userinfo.setMobile(cell.toString());
					}
			if (row.getCell(5) != null) {
				student.setStu_country(ImportExcel.getCellValue(row.getCell(5)));
			}
			if (row.getCell(6) != null) {
				student.setStu_nationality(ImportExcel.getCellValue(row.getCell(6)));
			}
			if (row.getCell(7) != null) {
				student.setStu_native(ImportExcel.getCellValue(row.getCell(7)));
			}
			if (row.getCell(8) != null) {
				student.setStu_hk_address(ImportExcel.getCellValue(row.getCell(8)));
			}
			if (row.getCell(9) != null) {
				student.setStu_home_address(ImportExcel.getCellValue(row.getCell(9)));
			}
			if (row.getCell(10) != null) {
				student.setPolitical_status(ImportExcel.getCellValue(row.getCell(10)));
			}
			if (row.getCell(11) != null) {
				student.setInteresting_speciality(ImportExcel.getCellValue(row.getCell(11)));
			}
			if (row.getCell(12) != null) {
				student.setStu_school_name(ImportExcel.getCellValue(row.getCell(12)));
			}
			if (row.getCell(13) != null) {
				cell=(HSSFCell) row.getCell(13);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				student.setStu_num(cell.toString());
			}
			if(stuClassId!=null&&!stuClassId.equals("")){
				student.setStu_class(Integer.parseInt(stuClassId));
			}else if (row.getCell(14) != null) {
				cell=(HSSFCell) row.getCell(14);
				if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
					existcodes += "第" + (r+1) + "行届次格式错误,应为数字;";
					continue;
				}
				double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(14)));
				int b=(int)a;
				student.setStu_class(b);
			}
			if (row.getCell(15) != null) {
				student.setStu_status(ImportExcel.getCellValue(row.getCell(15)));
			}
			if (row.getCell(16) != null) {
				student.setStu_position(ImportExcel.getCellValue(row.getCell(16)));
					}
			if (row.getCell(17) != null) {
				student.setStu_edu_level(ImportExcel.getCellValue(row.getCell(17)));
			}
			if (row.getCell(18) != null) {
				student.setStu_major_name(ImportExcel.getCellValue(row.getCell(18)));
			}
			if (row.getCell(19) != null) {
				student.setStu_major_direction(ImportExcel.getCellValue(row.getCell(19)));
			}
			if (row.getCell(20) != null) {
				student.setStu_type(ImportExcel.getCellValue(row.getCell(20)));
			}
			if (row.getCell(21) != null) {
				student.setSup_doc_name(ImportExcel.getCellValue(row.getCell(21)));
			}
			if (row.getCell(22) != null) {
				student.setTutor_name(ImportExcel.getCellValue(row.getCell(22)));
			}
			if (row.getCell(23) != null) {
				student.setTutor_workplace(ImportExcel.getCellValue(row.getCell(23)));
			}
			if (row.getCell(24) != null) {//工号 存在
				cell=(HSSFCell) row.getCell(24);
				//设置字段类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				student.setUser_code(cell.toString());
				userinfo.setUser_code(cell.toString());
				UserInfo user=new UserInfo();
				user.setUser_code(cell.toString());
				int _i=0;
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getUser_code().equals(user.getUser_code())){
						existcodes += "第" + (r+1) + "行工号为【" + cell.toString() + "】的记录重复;";
						_i=1;
						break;
					}
					};
				if(_i==1){
					continue;
				}
				if(userInfoDao.selectKeYonCount(user)>0){
					existcodes += "第" + (r+1) + "行工号为【" + cell.toString() + "】的记录重复;";
					continue;
				}	
			}else{
				existcodes += "第" + (r+1) + "行工号为【空】不能添加;";
				continue;
			}
			
			if (row.getCell(25) != null) {
				cell=(HSSFCell) row.getCell(25);
				if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
					existcodes += "第" + (r+1) + "学生类型ID格式错误,应为数字;";
					continue;
				}
				Integer typeId = (int)Double.parseDouble(ImportExcel.getCellValue(row.getCell(25)));
				StuType stuType= new StuType();
				stuType.setId(typeId);
				stuType.setState("Y");
				int count= stuTypeDao.selectCount(stuType);
				if(count<=0){
					existcodes += "第" + (r+1) + "行学生类型ID不存在;";
					continue;
				}
				student.setStu_type(typeId.toString());
				roleinfo.setStu_type_id(typeId);
			}else{
				existcodes += "第" + (r+1) + "行学生类型为【空】不能添加;";
				continue;
			}
			
			if (row.getCell(26) != null) {
				cell=(HSSFCell) row.getCell(26);
				if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
					existcodes += "第" + (r+1) + "行轮转方案ID格式错误,应为数字;";
					continue;
				}
				int tscId = (int)Double.parseDouble(ImportExcel.getCellValue(row.getCell(26)));
				TrainSchemeConfig config=trainSchemeConfigDao.selectOne(tscId);
				if (config == null){
					existcodes += "第" + (r+1) + "行轮转方案不存在不能添加;";
					continue;
				}
				student.setTsc_id(tscId);
			}
			int user_id=userInfoDao.getSequence();
			Timestamp d = new Timestamp(System.currentTimeMillis());
			userinfo.setCreate_time(d);
			if(state!=null&&!state.equals("")){
				userinfo.setState(state);
			}else{
				userinfo.setState("Y");
			}
			userinfo.setUser_id(user_id);
			userinfo.setUser_password(StringUtil. shaEncodeSignature(StringUtil.md5EncodeSignature("123")));
			userInfoDao.add(userinfo);
			int auth_id=userAuthorityDao.getSequence();
			roleinfo.setRole_id(10);
			roleinfo.setAuth_id(auth_id);
			roleinfo.setCreate_time(d);
			roleinfo.setUser_id(user_id);
			if(state!=null&&!state.equals("")){
				roleinfo.setState(state);
			}else{
				roleinfo.setState("Y");
			}
			userAuthorityDao.add(roleinfo);
			if(state!=null&&!state.equals("")){
				student.setState(state);
			}else{
				student.setState("Y");
			}
			student.setCreate_time(d);
			list.add(student);
		}
		log.info("existcodes-------"+existcodes);
		return list;
	}
	/**
	 *导出教职工模板
	 * @param req
	 * @param res
	 * @throws Exception
	 *@author huq
	 *@date 2017年1月16日
	 */
	public void downTeacherUserInfo(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String title="教职工用户信息批量导入模板";
		int index = 0;
		JxlUtil jxl = new JxlUtil(title+".xls");		
		jxl.buildSheet(title, 0);
		jxl.addHeaderCell("姓名", 0, index++);
		jxl.addHeaderCell("年龄", 0, index++);
		jxl.addHeaderCell("出生日期", 0, index++);
		jxl.addHeaderCell("手机号码", 0, index++);
		jxl.addHeaderCell("籍贯", 0, index++);
		jxl.addHeaderCell("出生地", 0, index++);
		jxl.addHeaderCell("职称", 0, index++);
		jxl.addHeaderCell("职位", 0, index++);
		jxl.addHeaderCell("政治面貌", 0, index++);
		jxl.addHeaderCell("文化水平", 0, index++);
		jxl.addHeaderCell("教职工工号", 0, index++);
		jxl.addHeaderCell("角色ID", 0, index++);
		jxl.addHeaderCell("科室ID", 0, index++);
		
		
		@SuppressWarnings("unchecked")
		List<RoleInfo> roleInfoList = 
				(List<RoleInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ROLE_INFO);
		
		//用户角色
		jxl.buildSheet("角色代码字典", 1);
		jxl.addHeaderCell("角色ID", 0, 0);	
		jxl.addHeaderCell("角色名称", 0, 1);
		int j=0;
		for(int i = 0;roleInfoList != null && i < roleInfoList.size();i++){
			RoleInfo td = roleInfoList.get(i);
			if(td.getRole_id()!=10){
				j++;
				jxl.addDataCell(td.getRole_id().toString(), j, 0);
				jxl.addDataCell(td.getRole_name(), j, 1);
			}else{
				continue;
			}
		}
		
		@SuppressWarnings("unchecked")
		List<OrgaInfo> orgaInfo = 
				(List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
		
//		用户角色
		jxl.buildSheet("科室代码字典", 2);
		jxl.addHeaderCell("科室ID", 0, 0);	
		jxl.addHeaderCell("科室名称", 0, 1);
		for(int i = 0;orgaInfo != null && i < orgaInfo.size();i++){
			OrgaInfo td = orgaInfo.get(i);
				jxl.addDataCell(td.getOrga_id().toString(), i+1, 0);
				jxl.addDataCell(td.getOrga_name(), i+1, 1);
		}
		java.io.File file = jxl.getFileExcel();
		JxlUtil.downLoadExcel(file, req, res);
	}
	/**
	 * 上传教职工模板
	 * @param in
	 * @param filename
	 * @return
	 *@author huq
	 *@date 2017年1月16日
	 */
	public String uploadTeacherTeachingTask(InputStream in, String filename) {
		List<StaffInfo> list = null;

		try {
			list = getTeacherExcelData(in, filename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list != null && list.size() > 0) {
			int num = staffInfoDao.insertWithList(list);
			// int num = teachingTaskInfoDao.add(list.get(0));
			if (num > 0) {
				msg = "true";
			}
		}
		if(!existcodes.equals("null")&&!existcodes.equals("")){
			msg=existcodes;
		}
		return msg;
	}
	
	/**
	 * 读取教职工excel文档
	 * 
	 * @param fileUrl
	 *            文件路径
	 * @return
	 * @throws Exception
	 */
	public List<StaffInfo> getTeacherExcelData(InputStream in, String fileName) throws Exception {
		msg = "";
		existcodes="";
		Workbook wb = ImportExcel.openWorkbook(in, fileName);
		if (wb == null) {
			msg = "导入文件" + fileName + "扩展名异常，请确认文件格式！";
			log.info(msg);
			return null;
		}
		List<StaffInfo> list = new ArrayList<StaffInfo>();
		Sheet sheet = (Sheet) wb.getSheetAt(0);
		Row row = null;
		int totalRows = sheet.getPhysicalNumberOfRows();
		for (int r = 1; r < totalRows; r++) {
			StaffInfo teacher = new StaffInfo();
			UserInfo userinfo=new UserInfo();
			UserAuthority roleinfo=new UserAuthority();
			row = sheet.getRow(r);
			// if(row.getPhysicalNumberOfCells()!=13){
			// msg = "导入文件"+fileUrl+"第"+r+"行缺少数据，请检查excel并重新导入";
			// log.info(msg);
			// return null;
			// }
			HSSFCell cell = null;
			if (row.getCell(0) != null) {
				teacher.setName(ImportExcel.getCellValue(row.getCell(0)));
				userinfo.setUser_name(ImportExcel.getCellValue(row.getCell(0)));
			}else{
				break;
			}
			if (row.getCell(1) != null) {
				cell=(HSSFCell) row.getCell(1);
				if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
					existcodes += "第" + (r+1) + "行年龄格式错误,应为数字;";
					continue;
				}
				double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(1)));
				int b=(int)a;
				teacher.setAge(String.valueOf(b));;
			}
			if (row.getCell(3) != null) {
				cell=(HSSFCell) row.getCell(3);
				//设置字段类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				teacher.setBirth(cell.toString());
			}
			if (row.getCell(3) != null) {
				cell=(HSSFCell) row.getCell(3);
				//设置字段类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				userinfo.setMobile(cell.toString());
			}
			if (row.getCell(4) != null) {
				teacher.setStaff_native(ImportExcel.getCellValue(row.getCell(4)));
					}
			if (row.getCell(5) != null) {
				teacher.setBirth_place(ImportExcel.getCellValue(row.getCell(5)));
			}
			if (row.getCell(6) != null) {
				teacher.setProfessional_title(ImportExcel.getCellValue(row.getCell(6)));
			}
			if (row.getCell(7) != null) {
				teacher.setPosition(ImportExcel.getCellValue(row.getCell(7)));
			}
			if (row.getCell(8) != null) {
				teacher.setPolitical_state(ImportExcel.getCellValue(row.getCell(8)));
			}
			if (row.getCell(9) != null) {
				teacher.setEdu_level(ImportExcel.getCellValue(row.getCell(9)));
			}
			if (row.getCell(10) != null) {
				cell=(HSSFCell) row.getCell(10);
				//设置字段类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				teacher.setUser_code(cell.toString());
				userinfo.setUser_code(cell.toString());
				UserInfo userInfo = new UserInfo();
				userInfo.setUser_code(cell.toString());
				if(userInfoDao.selectKeYonCount(userInfo)>0){
					existcodes += "第" + (r+1) + "行工号为【" + cell.toString() + "】的记录重复;";
					continue;
				};
			}else{
				existcodes += "第" + (r+1) + "行工号为【空】不能添加;";
				continue;
			}
			if (row.getCell(11) != null) {
				cell=(HSSFCell) row.getCell(11);
				if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
					existcodes += "第" + (r+1) + "行角色id格式错误，应为数字;";
					continue;
				}
				double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(11)));
				int b=(int)a;
				roleinfo.setRole_id(b);
			}
			if (row.getCell(12) != null) {
				cell=(HSSFCell) row.getCell(12);
				if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
					existcodes += "第" + (r+1) + "行科室id格式错误，应为数字;";
					continue;
				}
				double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(12)));
				int b=(int)a;
				roleinfo.setOrga_id(b);
			}
			int user_id=userInfoDao.getSequence();
			Timestamp d = new Timestamp(System.currentTimeMillis());
			userinfo.setCreate_time(d);
			userinfo.setState("Y");
			userinfo.setUser_id(user_id);
			userinfo.setUser_password(StringUtil. shaEncodeSignature(StringUtil.md5EncodeSignature("123")));
			userInfoDao.add(userinfo);
			int auth_id=userAuthorityDao.getSequence();
			roleinfo.setAuth_id(auth_id);
			roleinfo.setCreate_time(d);
			roleinfo.setUser_id(user_id);
			roleinfo.setState("Y");
			userAuthorityDao.add(roleinfo);
			teacher.setState("Y");
			teacher.setCreate_time(d);
			list.add(teacher);
		}
		log.info("existcodes-------"+existcodes);
		return list;
	}

	public List<UserInfo> findAllUserName(UserInfo userInfo) {
		return userInfoDao.findAllUserName(userInfo);
	}
	//guocc模糊查询
	public List<UserInfo> findAllUserNameAndId(UserInfo userInfo) {
		return userInfoDao.findAllUserNameAndId(userInfo);
	}
	
	public String uploadStuInfo(HttpServletRequest req) throws IOException, FileUploadException {
		String msg="";
		//1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
		List<FileItem> list = upload.parseRequest(req);
		if(list!=null&&list.size()>0){
//			StudentInfo studentInfo = new StudentInfo();
//			studentInfo.setState("X");
//			studentInfoDao.updateStateList(studentInfo);
			String filename = list.get(0).getName();
			filename = filename.substring(filename.lastIndexOf("\\")+1);
			InputStream in = list.get(0).getInputStream();
			String state = req.getParameter("state");
			String stuClass = req.getParameter("stuClass");
			String stuClassId = req.getParameter("stuClassId");
			msg = uploadTeachingTask(in,filename,state,stuClassId,stuClass);
		}
		return msg;
	}
	
	/***
	 * 批量上传学生头像信息
	 * @author yuanb
	 * @throws IOException 
	 * @date 2017年4月1日
	 */
	public String uploadHeadImgs(MultipartFile headImgs,HttpServletRequest req) throws IOException{
		String msg = "";
		ServletContext sc = req.getSession().getServletContext();
        String dir = sc.getRealPath("/_uploadImgs/_userHeadImgs");//附件存放服务器的路径
        System.out.println(dir);
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = headImgs.getOriginalFilename();
        String realname = filename.substring(0, filename.indexOf("."));
        //防止文件被覆盖，以纳秒生成文件
//      Long _l = System.nanoTime();
        String _extfilename = filename.substring(filename.indexOf("."));
//      filename = _l+_extfilename;
//      String uploadPath = req.getContextPath()+""+dir+"/"+realname;
        
        //1、根据realname（学号）去学生表中查找是否存在此人，有就把这个文件名或路径存在学生对应的user_info表的头像路径字段里
        StudentInfo stu = new StudentInfo();
        stu.setStu_num(realname);
        stu.setState("Y");
        List<StudentInfo> stuList = studentInfoDao.selectList(stu);
        if(stuList!=null&&stuList.size()>0){
        	String userCode = stuList.get(0).getUser_code();
        	UserInfo user = new UserInfo();
        	user.setUser_code(userCode);
        	user.setImg_path("_uploadImgs/_userHeadImgs/"+realname+_extfilename);
        	if(userInfoDao.updateByUserCode(user)>0){
        		//2、先把所有头像图片上传服务器
                try {
                    FileUtils.writeByteArrayToFile(new File(dir, realname+_extfilename), headImgs.getBytes());
                    msg = "上传成功！";
                }catch (Exception e) {
                    e.printStackTrace();
            		msg = "上传失败 ...";
                }
        	}
        }
		return msg;
	}
	/**
	 * 得到某个科室下的带教老师人数
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月30日
	 */
	public Map<String, Object> getTeaCount(HttpServletRequest req) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("success", true);
		LoginBean loginBean=(LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer orga_id=loginBean.getvUserDetailInfo().getOrga_id();
		UserAuthority userAuthority=new UserAuthority();
		userAuthority.setOrga_id(orga_id);
		userAuthority.setState("Y");
		userAuthority.setRole_id(20);   //带教老师
		int i =userAuthorityDao.selectCount(userAuthority);
		map.put("data",i);		
		List<UserAuthority> lists=userAuthorityDao.selectTeacherList(userAuthority);
		map.put("teacherList", lists);
		return map;
	}
	
	public void downUserTrainPlan(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String title="学生轮转排班信息批量导入模板";
		int index = 0;
		JxlUtil jxl = new JxlUtil(title+".xls");		
		jxl.buildSheet(title, 0);
		jxl.addHeaderCell("工号", 0, index++);
		jxl.addHeaderCell("姓名", 0, index++);
	    String stuClass = req.getParameter("stuClass");
	    Integer tscId=null;
	     if(StringUtil.isNotEmpty(stuClass)){
	    	 StudentInfo student = new StudentInfo();
			 student.setStu_class(Integer.parseInt(stuClass));
			 student.setQueryCondition("and t.state!='X' and t.state !='Y'");
			 student.setOrderCondition("id asc");
			 List<StudentInfo> list = studentInfoDao.selectStudentList(student); 
	         for (int k = 0; list != null && k < list.size(); k++) {
	        	 tscId=list.get(0).getTsc_id();
	    		StudentInfo or = list.get(k);
	    		jxl.addDataCell(or.getUser_code().toString(), k + 1, 0);
	    		jxl.addDataCell(or.getStu_name(), k + 1, 1);
	    		}
	     }
	     if(tscId!=null) {
	     TrainPlanConfig trainPlanConfig=new TrainPlanConfig();
	     trainPlanConfig.setTsc_id(tscId);
	     trainPlanConfig.setState("Y");
	     List<TrainPlanConfig> configs=new ArrayList<TrainPlanConfig>();
	     configs=trainPlanConfigDao.selectOrgaByTscId(trainPlanConfig);
		 jxl.buildSheet("科室代码字典", 1);
		 jxl.addHeaderCell("科室ID代码", 0, 0);	
		 jxl.addHeaderCell("科室名称", 0, 1);
		 for(int i = 0;configs != null && i < configs.size();i++){
			TrainPlanConfig  td = configs.get(i);
				jxl.addDataCell(td.getDept_id().toString(), i+1, 0);
				jxl.addDataCell(td.getOrga_name(), i+1, 1);
		 }
	     }
		 java.io.File file = jxl.getFileExcel();
		 JxlUtil.downLoadExcel(file, req, res);
	}
	
	public String uploadStuTrainPlanInfo(HttpServletRequest req, String stuClass) throws IOException, FileUploadException {
		String msg="";
		//1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
		List<FileItem> list = upload.parseRequest(req);
		if(list!=null&&list.size()>0){
//			StudentInfo studentInfo = new StudentInfo();
//			studentInfo.setState("X");
//			studentInfoDao.updateStateList(studentInfo);
			String filename = list.get(0).getName();
			filename = filename.substring(filename.lastIndexOf("\\")+1);
			InputStream in = list.get(0).getInputStream();
			msg = uploadTrainPlanInfo(in,filename,stuClass);
		}
		return msg;
	}
	
	public String uploadTrainPlanInfo(InputStream in, String filename, String stuClass) {
		List<TrainPlanBefore> list = null;
		try {
			list = getTrainPlanExcelData(in, filename,stuClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list != null && list.size() > 0) {
			for(int i=0;i<list.size();i++){
				TrainPlanBefore trainPlanBefore= new TrainPlanBefore();
				trainPlanBefore.setStu_auth_id(list.get(i).getStu_auth_id());
				trainPlanBeforeDao.deleteList(trainPlanBefore);
			}
			int num = trainPlanBeforeDao.insertWithList(list);
			// int num = teachingTaskInfoDao.add(list.get(0));
			if (num > 0) {
				msg = "true";
			}
		}
		if(!existcodes.equals("null")&&!existcodes.equals("")){
			msg=existcodes;
		}
		return msg;
	}
	
	public List<TrainPlanBefore> getTrainPlanExcelData(InputStream in, String fileName, String stuClass) throws Exception {
		msg = "";
		existcodes="";
		Workbook wb = ImportExcel.openWorkbook(in, fileName);
		if (wb == null) {
			msg = "导入文件" + fileName + "扩展名异常，请确认文件格式！";
			log.info(msg);
			return null;
		}
		List<TrainPlanBefore> list=new ArrayList<TrainPlanBefore>();
		Sheet sheet = (Sheet) wb.getSheetAt(0);
		Row row = null;
		String string=null;
		int totalRows = sheet.getPhysicalNumberOfRows();
		List<String> students= new ArrayList<String>();
		for (int r = 1; r < totalRows; r++) {
			int month=1;
			Integer endDay=Integer.parseInt(stuClass);
			Integer authId=null;
			row = sheet.getRow(r);
			// if(row.getPhysicalNumberOfCells()!=13){
			// msg = "导入文件"+fileUrl+"第"+r+"行缺少数据，请检查excel并重新导入";
			// log.info(msg);
			// return null;
			// }
			HSSFCell cell = null;
			if (row.getCell(0) != null) {
				string=ImportExcel.getCellValue(row.getCell(0));
				if(StringUtil.isNotEmpty(string)) {
				students.add(ImportExcel.getCellValue(row.getCell(0)));
				UserInfo info =new UserInfo();
				info.setUser_code(ImportExcel.getCellValue(row.getCell(0)));
				info=userInfoDao.selecAuthIdBy(info);
				authId=info.getAuth_id();
				}
			}else{
				existcodes += "第" + (r+1) + "行学生不存在;";
				break;
			}
			if (row.getCell(2) != null) {
				if(StringUtil.isNotEmpty(string)) {
				string=ImportExcel.getCellValue(row.getCell(2));
				cell=(HSSFCell) row.getCell(2);
				if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
					existcodes += "第" + (r+1) + "行科室ID格式错误，应为数字;";
					break;
				}
				double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(2)));
				int b=(int)a;
				TrainPlanConfig config= new TrainPlanConfig();
				config.setDept_id(b);
				config.setUser_code(ImportExcel.getCellValue(row.getCell(0)));
				config=trainPlanConfigDao.getDeptId(config);
				if(config==null||config.equals("")){
					existcodes += "第" + (r+1) + "行科室ID,轮转计划中不存在;";
					break;
				}
				if(row.getCell(3)==null&&StringUtil.isNotEmpty(string)) {
					string=ImportExcel.getCellValue(row.getCell(3));
					if(StringUtil.isNotEmpty(string)) {
					TrainPlanBefore trainPlanBefore = new TrainPlanBefore();
					trainPlanBefore.setStu_auth_id(authId);
					trainPlanBefore.setTrain_dept_id(b);
					trainPlanBefore.setTrain_start_date(endDay);
					trainPlanBefore.setTrain_start_time(getDateTime(String.valueOf(endDay)+"000000"));
					endDay=Integer.parseInt(addMonth(endDay, month));
					trainPlanBefore.setTrain_end_date(endDay);
					trainPlanBefore.setTrain_end_time(getDateTime(String.valueOf(endDay)+"235959"));
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				    String newDate=null;
				    try {
				        Date parse = format.parse(endDay.toString());
				        Calendar calendar = Calendar.getInstance();
				        calendar.setTime(parse);
				        calendar.add(Calendar.DAY_OF_MONTH, 1);
				        newDate = format.format(calendar.getTime());
				    } catch (ParseException e) {
				        e.printStackTrace();
				    }
				    endDay=Integer.parseInt(newDate);
					month=1;
					trainPlanBefore.setState("Y");
					trainPlanBefore.setConfig_id(config.getId());
					list.add(trainPlanBefore);
				}
				}
				}
			}
			for(int i=3;i<50;i++) {
				 string=ImportExcel.getCellValue(row.getCell(i));
				if (row.getCell(i) != null&&StringUtil.isNotEmpty(string)) {
					cell=(HSSFCell) row.getCell(i);
					if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
						existcodes += "第" + (r+1) + "行科室ID格式错误，应为数字;";
						break;
					}
					double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(i)));
					double aBefore=Double.parseDouble(ImportExcel.getCellValue(row.getCell(i-1)));
					int b=(int)a;
					TrainPlanConfig config= new TrainPlanConfig();
					config.setDept_id(b);
					config.setUser_code(ImportExcel.getCellValue(row.getCell(0)));
					config=trainPlanConfigDao.getDeptId(config);
					if(config==null||config.equals("")){
						existcodes += "第" + (r+1) + "行科室ID,轮转计划中不存在;";
						break;
					}
					if(a==aBefore) {
						month++;
					}else{
						double aPrev=Double.parseDouble(ImportExcel.getCellValue(row.getCell(i-1)));
						int bPrev=(int)aPrev;
						TrainPlanConfig plConfig= new TrainPlanConfig();
						plConfig.setDept_id(bPrev);
						plConfig.setUser_code(ImportExcel.getCellValue(row.getCell(0)));
						plConfig=trainPlanConfigDao.getDeptId(plConfig);
						
						TrainPlanBefore trainPlanBefore = new TrainPlanBefore();
						trainPlanBefore.setStu_auth_id(authId);
						trainPlanBefore.setTrain_dept_id((int)aBefore);
						trainPlanBefore.setTrain_start_date(endDay);
						trainPlanBefore.setTrain_start_time(getDateTime(String.valueOf(endDay)+"000000"));
						endDay=Integer.parseInt(addMonth(endDay, month));
						trainPlanBefore.setTrain_end_date(endDay);
						trainPlanBefore.setTrain_end_time(getDateTime(String.valueOf(endDay)+"235959"));
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
					    String newDate=null;
					    try {
					        Date parse = format.parse(endDay.toString());
					        Calendar calendar = Calendar.getInstance();
					        calendar.setTime(parse);
					        calendar.add(Calendar.DAY_OF_MONTH, 1);
					        newDate = format.format(calendar.getTime());
					    } catch (ParseException e) {
					        e.printStackTrace();
					    }
					    endDay=Integer.parseInt(newDate);
						month=1;
						trainPlanBefore.setState("Y");
						trainPlanBefore.setConfig_id(plConfig.getId());
						list.add(trainPlanBefore);
					}
				}else {
					double aPrev=Double.parseDouble(ImportExcel.getCellValue(row.getCell(i-1)));
					int bPrev=(int)aPrev;
					TrainPlanConfig config= new TrainPlanConfig();
					config.setDept_id(bPrev);
					config.setUser_code(ImportExcel.getCellValue(row.getCell(0)));
					config=trainPlanConfigDao.getDeptId(config);
					TrainPlanBefore trainPlanBefore = new TrainPlanBefore();
					trainPlanBefore.setStu_auth_id(authId);
					trainPlanBefore.setTrain_dept_id(bPrev);
					trainPlanBefore.setTrain_start_date(endDay);
					trainPlanBefore.setTrain_start_time(getDateTime(String.valueOf(endDay)+"000000"));
					endDay=Integer.parseInt(addMonth(endDay, month));
					trainPlanBefore.setTrain_end_date(endDay);
					trainPlanBefore.setTrain_end_time(getDateTime(String.valueOf(endDay)+"235959"));
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				    String newDate=null;
				    try {
				        Date parse = format.parse(endDay.toString());
				        Calendar calendar = Calendar.getInstance();
				        calendar.setTime(parse);
				        calendar.add(Calendar.DAY_OF_MONTH, 1);
				        newDate = format.format(calendar.getTime());
				    } catch (ParseException e) {
				        e.printStackTrace();
				    }
				    endDay=Integer.parseInt(newDate);
					month=1;
					trainPlanBefore.setState("Y");
					trainPlanBefore.setConfig_id(config.getId());
					list.add(trainPlanBefore);
				
					break;
				}
			}
		}
		log.info("existcodes-------"+existcodes);
		//将预编排过的学生的状态设为M，待生成轮转计划状态
		 List<String> result = new ArrayList<String>(students.size());
		    for (String str : students) {
		        if (!result.contains(str)) {
		            result.add(str);
		        }
		    }
		    students.clear();
		    students.addAll(result);
		for (int i = 0; i < students.size(); i++) {
			StudentInfo studentInfo = new StudentInfo();
	        studentInfo.setState("M");
	        studentInfo.setUser_code(students.get(i));
	        studentInfoDao.updateStuInfo(studentInfo);
	        studentInfoDao.updateUserInfo(studentInfo);
	        studentInfoDao.updateUserAuth(studentInfo);
		}
		return list;
	}
	
	public static String addMonth(Integer date,int month){
	    String nowDate = null;
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	    try {
	        Date parse = format.parse(date.toString());
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(parse);
	        calendar.add(Calendar.MONTH, month);
	        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
	        nowDate = format.format(calendar.getTime());
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return nowDate;
	}
	
	private Timestamp getDateTime(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date datetime = new Date();
		try {
			datetime = new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = dateFormat.format(datetime);//时间存储为字符串
		return Timestamp.valueOf(str);//转换时间字符串为Timestamp
	}

	public List<UserInfo> selectTeaList(UserInfo info) {
		return userInfoDao.selectTeaList(info);
	}
}
