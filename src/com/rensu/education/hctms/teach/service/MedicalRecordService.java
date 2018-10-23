package com.rensu.education.hctms.teach.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.config.bean.TrainPlanBefore;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.teach.bean.MedicalAdviceMain;
import com.rensu.education.hctms.teach.bean.MedicalAdviceSub;
import com.rensu.education.hctms.teach.bean.MedicalDiagnose;
import com.rensu.education.hctms.teach.bean.MedicalRecord;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.bean.StuDailyRecord;
import com.rensu.education.hctms.teach.dao.MedicalAdviceMainDao;
import com.rensu.education.hctms.teach.dao.MedicalAdviceSubDao;
import com.rensu.education.hctms.teach.dao.MedicalDiagnoseDao;
import com.rensu.education.hctms.teach.dao.MedicalRecordDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;

@Service("medicalRecordService")
public class MedicalRecordService extends BaseService<MedicalRecord> {
	
	Logger log = Logger.getLogger(MedicalRecordService.class);
	
	@Autowired
	protected MedicalRecordDao medicalRecordDao;
    @Autowired
    protected TrainPlanDao trainPlanDao; 
    
	@Autowired
	protected MedicalDiagnoseDao medicalDiagnoseDao;
	@Autowired
	protected MedicalAdviceMainDao medicalAdviceMainDao;
	@Autowired
	protected MedicalAdviceSubDao medicalAdviceSubDao;
	
	/**
	 * 查询医疗文书
	 * @param pageIndex
	 * @param rows
	 * @param medicalRecord
	 * @return
	 * @author guocc
	 * @date 2017年8月18日
	 */
	public JSONObject selectMedicalDocument(int pageIndex, int rows,MedicalRecord medicalRecord) {
		JSONObject jobj = new JSONObject();
		List<MedicalRecord> dataList = medicalRecordDao.selectMedicalDocument(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				medicalRecord);
		for (MedicalRecord mr : dataList) {
			if (mr.getCorrect_status() != null && mr.getCorrect_status().equals("Y")) {
				mr.setCorrect_status_str("<span style='color:#00FF00;'>" + mr.getCorrect_status_str() + "</sapn>");
			}else if (mr.getCorrect_status() != null && mr.getCorrect_status().equals("N")) {
				mr.setCorrect_status_str("<span style='color:#FF8800;'>" + mr.getCorrect_status_str() + "</sapn>");
			}
		}
		int totalCount = medicalRecordDao.selectMedicalDocumentCount(medicalRecord);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}

	public Object saveProgressNote(HttpServletRequest req) {
	  LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
	  Integer stu_auth_id=loginBean.getvUserDetailInfo().getAuth_id();
	  String type_id=req.getParameter("type_id");
	  String mr_name=req.getParameter("mr_name");
	  String p_name=req.getParameter("p_name");
	  String p_sex=req.getParameter("p_sex");
	  String p_age=req.getParameter("p_age");
	  String p_deptname=req.getParameter("p_deptname");
	  String p_bednum=req.getParameter("p_bednum");
	  String p_pid=req.getParameter("p_pid");
	  String content=req.getParameter("content");
	  Timestamp create_time= new Timestamp(System.currentTimeMillis());
	  MedicalRecord medicalRecord=new MedicalRecord();
	  if(type_id!=null&&!type_id.equals("")){
		  medicalRecord.setType_id(Integer.parseInt(type_id));
	  }
	  if(mr_name!=null&&!mr_name.equals("")){
		  medicalRecord.setMr_name(mr_name);
	  }
	  if(p_name!=null&&!p_name.equals("")){
		  medicalRecord.setP_name(p_name);
	  }
    if(p_sex!=null&&!p_sex.equals("")){
  	  medicalRecord.setP_sex(p_sex);
    }
	  if(p_age!=null&&!p_age.equals("")){
		  medicalRecord.setP_age(p_age);
	  }
	  if(p_deptname!=null&&!p_deptname.equals("")){
		  medicalRecord.setP_deptname(p_deptname);
	  }
	  if(p_bednum!=null&&!p_bednum.equals("")){
		  medicalRecord.setP_bednum(p_bednum);
	  }
	  if(p_pid!=null&&!p_pid.equals("")){
		  medicalRecord.setP_pid(p_pid);
	  }
	  if(content!=null&&!content.equals("")){
		  medicalRecord.setContent(content);
	  }
	  medicalRecord.setPk_code( java.util.UUID.randomUUID().toString());
	  medicalRecord.setStu_auth_id(stu_auth_id);
	  medicalRecord.setCreate_time(create_time);
	  medicalRecord.setCorrect_status("N");
	  medicalRecord.setState("Y");
	  medicalRecord.setId(medicalRecordDao.getSequence());
	  int num=medicalRecordDao.add(medicalRecord);
	  if(num > 0){
			return StringUtil.returns(true, "操作成功");
		}else{
			return StringUtil.returns(true, "操作异常");
		}
	  }
	
	/**
	 * 保存医疗文书（除医嘱外）
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月18日
	 */
	public Object saveMedicalRecord(HttpServletRequest req) {
		//
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		//
		String action = req.getParameter("action");
		String id = req.getParameter("id");
		String type_id=req.getParameter("type_id");
		String mr_name=req.getParameter("mr_name");
		String p_name=req.getParameter("p_name");
		String p_sex=req.getParameter("p_sex");
		String p_age=req.getParameter("p_age");
		String p_deptname=req.getParameter("p_deptname");
		String p_bednum=req.getParameter("p_bednum");
		String p_pid=req.getParameter("p_pid");
		String content=req.getParameter("content");
		int num = 0;
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setMr_name(mr_name);
		medicalRecord.setP_name(p_name);
		medicalRecord.setP_sex(p_sex);
		medicalRecord.setP_age(p_age);
		medicalRecord.setP_deptname(p_deptname);
		medicalRecord.setP_bednum(p_bednum);
		medicalRecord.setP_pid(p_pid);
		medicalRecord.setContent(content);
		if (action != null && action.equals("add")) {
			int mr_id = medicalRecordDao.getSequence();
			medicalRecord.setId(mr_id);
			medicalRecord.setPk_code(java.util.UUID.randomUUID().toString());
			if(type_id != null && !type_id.equals("")){
				medicalRecord.setType_id(Integer.parseInt(type_id));
			}
			medicalRecord.setStu_auth_id(stu_auth_id);
			Timestamp create_time= new Timestamp(System.currentTimeMillis());
			medicalRecord.setCreate_time(create_time);
			medicalRecord.setCorrect_status("N");
			medicalRecord.setState("Y");
			num = medicalRecordDao.add(medicalRecord);
			//入院记录--MEDICAL_DIAGNOSE
			if (type_id!=null && type_id.equals("1")){
				String rowData=req.getParameter("rowData");
				if (rowData != null && !rowData.equals("[]")) {
					JSONArray jsonArray = JSONArray.fromObject(rowData);
					List<MedicalDiagnose> list = JSONArray.toList(jsonArray, MedicalDiagnose.class);
					MedicalDiagnose medicalDiagnose = new MedicalDiagnose();
					medicalDiagnose.setState("Y");
					medicalDiagnose.setMr_id(mr_id);
					for (MedicalDiagnose md : list) {
						medicalDiagnose.setId(medicalDiagnoseDao.getSequence());
						medicalDiagnose.setDiag_name(md.getDiag_name());
						medicalDiagnose.setIcd_code(md.getIcd_code());
						medicalDiagnoseDao.add(medicalDiagnose);
					}
				}
			}
		}else if (action != null && action.equals("edit")){
			if (id != null && !id.equals("")) {
				medicalRecord.setId(Integer.parseInt(id));
			}
			num = medicalRecordDao.update(medicalRecord);
			//入院记录--MEDICAL_DIAGNOSE
			if (type_id != null && type_id.equals("1")){
				String rowData=req.getParameter("rowData");
				if (rowData != null && !rowData.equals("[]")) {
					//
					MedicalDiagnose md = new MedicalDiagnose();
					if (id != null && !id.equals("")) {
						md.setMr_id(Integer.parseInt(id));
					}
					md.setState("X");
					medicalDiagnoseDao.updateByMrId(md);
					//
					JSONArray jsonArray = JSONArray.fromObject(rowData);
					List<MedicalDiagnose> list = JSONArray.toList(jsonArray, MedicalDiagnose.class);
					MedicalDiagnose medicalDiagnose = new MedicalDiagnose();
					medicalDiagnose.setState("Y");
					if (id != null && !id.equals("")) {
						medicalDiagnose.setMr_id(Integer.parseInt(id));
					}
					for (MedicalDiagnose mdGet : list) {
						medicalDiagnose.setId(medicalDiagnoseDao.getSequence());
						medicalDiagnose.setDiag_name(mdGet.getDiag_name());
						medicalDiagnose.setIcd_code(mdGet.getIcd_code());
						medicalDiagnoseDao.add(medicalDiagnose);
					}
				}
			}
		}
		if(num > 0){
			return StringUtil.returns(true, "操作成功...");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}

	public Object selectMedDocByStu(int pageIndex, int rows, HttpServletRequest req) {
		JSONObject jobj = new JSONObject();
		String start_date=req.getParameter("start_date");
		String end_date= req.getParameter("end_date");
		String state= req.getParameter("state");
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer teacher_auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		MedicalRecord medicalRecord=new MedicalRecord();
		medicalRecord.setState(state);
		medicalRecord.setTeacher_auth_id(teacher_auth_id);
		if (StringUtil.isNotEmpty(start_date)) {
			medicalRecord.setStart_date(start_date+" 00:00:00");
		}
		if (StringUtil.isNotEmpty(end_date)) {
			medicalRecord.setEnd_date(end_date+" 23:59:59");
		}
		medicalRecord.setOrderCondition("t.create_time desc");
		List<MedicalRecord> medicalRecords=medicalRecordDao.selectMedicalDocumentTea(medicalRecord);
		for (MedicalRecord mr : medicalRecords) {
			if (mr.getCorrect_status() != null && mr.getCorrect_status().equals("Y")) {
				mr.setCorrect_status_str("<span style='color:#00FF00;'>" + mr.getCorrect_status_str() + "</sapn>");
			}else if (mr.getCorrect_status() != null && mr.getCorrect_status().equals("N")) {
				mr.setCorrect_status_str("<span style='color:#FF8800;'>" + mr.getCorrect_status_str() + "</sapn>");
			}
		}
		int totalCount=medicalRecordDao.selectMedicalDocumentCountTea(medicalRecord);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));//总页数
		jobj.put("page", pageIndex);//当前页码
		jobj.put("records", totalCount);//总的记录数
		jobj.put("rows",medicalRecords);//显示的具体数据，jsonarray格式。
		return jobj;
	}

	/**
	 * 删除医疗文书
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月22日
	 */
	public Object delMedicalDocument(HttpServletRequest req) {
		//
		String id = req.getParameter("id");
		String type_id = req.getParameter("type_id");
		String state = req.getParameter("state");
		int num = 0;
		if (type_id != null && (type_id.equals("1") || type_id.equals("2") || type_id.equals("3"))) {
			MedicalRecord medicalRecord = new MedicalRecord();
			if (id != null && !id.equals("")) {
				medicalRecord.setId(Integer.parseInt(id));
			}
			medicalRecord.setState(state);
			num = medicalRecordDao.update(medicalRecord);
			if (type_id.equals("1")) {//  入院记录--MEDICAL_DIAGNOSE
				MedicalDiagnose md = new MedicalDiagnose();
				if (id != null && !id.equals("")) {
					md.setMr_id(Integer.parseInt(id));
				}
				md.setState(state);
				medicalDiagnoseDao.updateByMrId(md);
			}
		}else if (type_id != null) {
			MedicalAdviceMain medicalAdviceMain = new MedicalAdviceMain();
			if (id != null && !id.equals("")) {
				medicalAdviceMain.setId(Integer.parseInt(id));
			}
			medicalAdviceMain.setState(state);
			num = medicalAdviceMainDao.update(medicalAdviceMain);
			//医嘱子表--MEDICAL_ADVICE_SUB
			MedicalAdviceSub medicalAdviceSub = new MedicalAdviceSub();
			if (id != null && !id.equals("")) {
				medicalAdviceSub.setMam_id(Integer.parseInt(id));
			}
			medicalAdviceSub.setState(state);
			medicalAdviceSubDao.updateByMamId(medicalAdviceSub);
		}
		if (num > 0) {
			return StringUtil.returns(true, "操作成功...");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
	
	/**
	 * 根据id查询医疗文书
	 * @param id
	 * @return
	 * @author guocc
	 * @date 2017年8月23日
	 */
	public MedicalRecord selectMedicalRecordById(int id) {
		return medicalRecordDao.selectMedicalRecordById(id);
	}

	public Object saveMedicalRecordTea(HttpServletRequest req) {
		//
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer tea_auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		String tea_name=loginBean.getvUserDetailInfo().getUser_name();
		//
		String action = req.getParameter("action");
		String id = req.getParameter("id");
		String type_id=req.getParameter("type_id");
		String mr_name=req.getParameter("mr_name");
		String p_name=req.getParameter("p_name");
		String p_sex=req.getParameter("p_sex");
		String p_age=req.getParameter("p_age");
		String p_deptname=req.getParameter("p_deptname");
		String p_bednum=req.getParameter("p_bednum");
		String p_pid=req.getParameter("p_pid");
		String content=req.getParameter("content");
		int num = 0;
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setMr_name(mr_name);
		medicalRecord.setP_name(p_name);
		medicalRecord.setP_sex(p_sex);
		medicalRecord.setP_age(p_age);
		medicalRecord.setP_deptname(p_deptname);
		medicalRecord.setP_bednum(p_bednum);
		medicalRecord.setP_pid(p_pid);
		medicalRecord.setContent(content);
		
		MedicalRecord medicalRecord2=medicalRecordDao.selectOne(Integer.parseInt(id));//通过Id查询学生记录
		MedicalRecord medicalRecord3= new MedicalRecord();
		medicalRecord3.setPk_code(medicalRecord2.getPk_code());
		List<MedicalRecord> list1=medicalRecordDao.selectList(medicalRecord3);        //通过PK_Code查询老师批改的记录
		for(int i=0;i<list1.size();i++){
			if(list1.get(i).getCorrect_status()==""||list1.get(i).getCorrect_status()==null){ //老师批改记录中审核状态为空
				MedicalRecord medicalRecord4= new MedicalRecord();                
				medicalRecord4.setId(list1.get(i).getId());                          //获得老师第一次批改记录ID，
				medicalRecordDao.delete(medicalRecord4);                            //删除老师第一次批改的记录
			    if(list1.get(i).getType_id()==1){
			    	MedicalDiagnose medicalDiagnose=new MedicalDiagnose();
			    	medicalDiagnose.setMr_id(list1.get(i).getId());
			    	List<MedicalDiagnose> medicalDiagnose2=medicalDiagnoseDao.selectList(medicalDiagnose);
			    	for(int j=0;j<medicalDiagnose2.size();j++){
			    		medicalDiagnose.setId(medicalDiagnose2.get(j).getId());
			    		medicalDiagnoseDao.delete(medicalDiagnose);
			    	}
			    }
			}
		}
		if (action != null && action.equals("add")) {
			int mr_id = medicalRecordDao.getSequence();
			medicalRecord.setId(mr_id);
			medicalRecord.setPk_code(medicalRecord2.getPk_code());
			if(type_id != null && !type_id.equals("")){
				medicalRecord.setType_id(Integer.parseInt(type_id));
			}
			medicalRecord.setStu_auth_id(medicalRecord2.getStu_auth_id());
			Timestamp create_time= new Timestamp(System.currentTimeMillis());
			medicalRecord.setCreate_time(create_time);
			medicalRecord.setCorrect_time(create_time);
			medicalRecord.setState("Y");
			medicalRecord2.setCorrect_status("Y");
			medicalRecord2.setCorrect_auth_id(tea_auth_id);
			medicalRecord2.setCorrect_name(tea_name);
			medicalRecord2.setCorrect_time(create_time);
		    medicalRecordDao.update(medicalRecord2);
			num = medicalRecordDao.add(medicalRecord);
			//入院记录--MEDICAL_DIAGNOSE
			if (type_id!=null && type_id.equals("1")){
				String rowData=req.getParameter("rowData");
				if (rowData != null && !rowData.equals("[]")) {
					JSONArray jsonArray = JSONArray.fromObject(rowData);
					List<MedicalDiagnose> list = JSONArray.toList(jsonArray, MedicalDiagnose.class);
					MedicalDiagnose medicalDiagnose = new MedicalDiagnose();
					medicalDiagnose.setState("Y");
					medicalDiagnose.setMr_id(mr_id);
					for (MedicalDiagnose md : list) {
						medicalDiagnose.setId(medicalDiagnoseDao.getSequence());
						medicalDiagnose.setDiag_name(md.getDiag_name());
						medicalDiagnose.setIcd_code(md.getIcd_code());
						medicalDiagnoseDao.add(medicalDiagnose);
					}
				}
			}
		}
		if(num > 0){
			return StringUtil.returns(true, "操作成功...");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	
	}
	
	/***
	 * 表格对比
	 * @param typeId
	 * @param oldList
	 * @param newList
	 * @return
	 * @author guocc
	 * @date 2017年9月5日
	 */
	public String getTableCompareContent(int typeId, List<?> oldList, List<?> newList){
		String tableCompareContent = "<table style='width:100%' border=\"1\" cellspacing=\"0\">";
		if (typeId == 1) {
			tableCompareContent += "<tr>"+
					"<th width=\"160\"style=\"font-weight:bold;text-align:center;\">诊断内容</th>"+
					"<th width=\"120\"style=\"font-weight:bold;text-align:center;\">ICD码</th>"+
					"</tr>";
			for(int i=0;oldList!=null&&i<oldList.size();i++){
				MedicalDiagnose medicalDiagnose = (MedicalDiagnose)oldList.get(i);
				String diagName = medicalDiagnose.getDiag_name();
				boolean flag = false;
				for(int j=0;newList!=null&&j<newList.size();j++){
					MedicalDiagnose _medicalDiagnose = (MedicalDiagnose)newList.get(j);
					if(diagName.equals(_medicalDiagnose.getDiag_name())){
						tableCompareContent += "<tr>"+
							   "<td>"+ this.getNotNullHtml(_medicalDiagnose.getDiag_name()) + "</td>"+
							   "<td>"+ this.getNotNullHtml(_medicalDiagnose.getIcd_code()) + "</td>"+
							   "</tr>";
						flag = true;
						break;
					}
				}
				if(!flag){
					tableCompareContent += "<tr>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalDiagnose.getDiag_name())  + "</strike></td>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalDiagnose.getIcd_code()) + "</strike></td>"+
						   "</tr>";
				}
			}
			//最后剩下的诊断为新增
			for(int i=0;newList!=null&&i<newList.size();i++){
				MedicalDiagnose _medicalDiagnose = (MedicalDiagnose)newList.get(i);
				String diagName = _medicalDiagnose.getDiag_name();
				boolean flag = false;
				for(int j=0;oldList!=null&&j<oldList.size();j++){
					MedicalDiagnose medicalDiagnose = (MedicalDiagnose)oldList.get(j);
					if(diagName.equals(medicalDiagnose.getDiag_name())){
						flag = true;
						break;
					}
				}
				if(!flag){
					tableCompareContent += "<tr>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalDiagnose.getDiag_name()) + "</td>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalDiagnose.getIcd_code()) + "</td>"+
						   "</tr>";
				}
			}
		}else if (typeId == 4) {
			tableCompareContent += "<tr>"+
					"<th width=\"160\" style=\"font-weight:bold;text-align:center;\">药品名称</th>"+
					"<th width=\"120\" style=\"font-weight:bold;text-align:center;\">药品规格</th>"+
					"<th width=\"50\" style=\"font-weight:bold;text-align:center;\">剂量</th>"+
					"<th width=\"50\" style=\"font-weight:bold;text-align:center;\">剂量单位</th>"+
					"<th width=\"50\" style=\"font-weight:bold;text-align:center;\">用药途径</th>"+
					"<th width=\"50\" style=\"font-weight:bold;text-align:center;\">用药频率</th>"+
					"<th width=\"50\" style=\"font-weight:bold;text-align:center;\">总量</th>"+
					"<th width=\"50\" style=\"font-weight:bold;text-align:center;\">总量单位</th>"+
					"</tr>";
			for(int i=0;oldList!=null&&i<oldList.size();i++){
				MedicalAdviceSub medicalAdviceSub = (MedicalAdviceSub)oldList.get(i);
				String adviceName = medicalAdviceSub.getAdvice_name();
				boolean flag = false;
				for(int j=0;newList!=null&&j<newList.size();j++){
					MedicalAdviceSub _medicalAdviceSub = (MedicalAdviceSub)newList.get(j);
					if(adviceName.equals(_medicalAdviceSub.getAdvice_name())){
						tableCompareContent += "<tr>"+
							   "<td>"+ this.getNotNullHtml(_medicalAdviceSub.getAdvice_name()) + "</td>"+
							   "<td>"+ this.getNotNullHtml(_medicalAdviceSub.getAdvice_spec()) + "</td>"+
							   "<td>"+ this.getNotNullHtml(_medicalAdviceSub.getAdvice_dose().toString()) + "</td>"+
							   "<td>"+ this.getNotNullHtml(_medicalAdviceSub.getDose_unit_code_str()) + "</td>"+
							   "<td>"+ this.getNotNullHtml(_medicalAdviceSub.getPath_code_str()) + "</td>"+
							   "<td>"+ this.getNotNullHtml(_medicalAdviceSub.getFrequency_code_str()) + "</td>"+
							   "<td>"+ this.getNotNullHtml(_medicalAdviceSub.getTotal_dose().toString()) + "</td>"+
							   "<td>"+ this.getNotNullHtml(_medicalAdviceSub.getTotal_dose_unit_code_str()) + "</td>"+
							   "</tr>";
						flag = true;
						break;
					}
				}
				if(!flag){
					tableCompareContent += "<tr>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalAdviceSub.getAdvice_name())  + "</strike></td>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalAdviceSub.getAdvice_spec()) + "</strike></td>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalAdviceSub.getAdvice_dose().toString()) + "</strike></td>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalAdviceSub.getDose_unit_code_str()) + "</strike></td>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalAdviceSub.getPath_code_str()) + "</strike></td>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalAdviceSub.getFrequency_code_str()) + "</strike></td>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalAdviceSub.getTotal_dose().toString()) + "</strike></td>"+
						   "<td style=\"color:#0000FF;\"><strike>"+ this.getNotNullHtml(medicalAdviceSub.getTotal_dose_unit_code_str()) + "</strike></td>"+
						   "</tr>";
				}
			}
			//最后剩下的诊断为新增
			for(int i=0;newList!=null&&i<newList.size();i++){
				MedicalAdviceSub _medicalAdviceSub = (MedicalAdviceSub)newList.get(i);
				String adviceName = _medicalAdviceSub.getAdvice_name();
				boolean flag = false;
				for(int j=0;oldList!=null&&j<oldList.size();j++){
					MedicalAdviceSub medicalAdviceSub = (MedicalAdviceSub)oldList.get(j);
					if(adviceName.equals(medicalAdviceSub.getAdvice_name())){
						flag = true;
						break;
					}
				}
				if(!flag){
					tableCompareContent += "<tr>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalAdviceSub.getAdvice_name()) + "</td>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalAdviceSub.getAdvice_spec()) + "</td>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalAdviceSub.getAdvice_dose().toString()) + "</td>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalAdviceSub.getDose_unit_code_str()) + "</td>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalAdviceSub.getPath_code_str()) + "</td>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalAdviceSub.getFrequency_code_str()) + "</td>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalAdviceSub.getTotal_dose().toString()) + "</td>"+
						   "<td style=\"color:#66CD00;\">"+ this.getNotNullHtml(_medicalAdviceSub.getTotal_dose_unit_code_str()) + "</td>"+
						   "</tr>";
				}
			}
		}
		tableCompareContent += "</table>";
		return tableCompareContent;
	}
	
	public String getNotNullHtml(String s){
		String res = "";
		if(s==null){
			res = "";
		}else{
			res = s;
		}
		return res;
	}
}