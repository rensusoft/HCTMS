package com.rensu.education.hctms.teach.service;


import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.teach.bean.MedicalCaseDiscuss;
import com.rensu.education.hctms.teach.bean.MedicalCaseMain;
import com.rensu.education.hctms.teach.bean.StuActiveData;
import com.rensu.education.hctms.teach.bean.MedicalCaseMember;
import com.rensu.education.hctms.teach.bean.StuVacateInfo;
import com.rensu.education.hctms.teach.dao.MedicalCaseDiscussDao;
import com.rensu.education.hctms.teach.dao.MedicalCaseMainDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.teach.dao.MedicalCaseMemberDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;



@Service("medicalCaseMainService")
public class MedicalCaseMainService extends BaseService<MedicalCaseMain> {
	
	Logger log = Logger.getLogger(MedicalCaseMainService.class);
	
	@Autowired
	protected MedicalCaseMainDao medicalCaseMainDao;
	@Autowired
	protected MedicalCaseMemberDao medicalCaseMemberDao;
	@Autowired
	protected MedicalCaseDiscussDao medicalCaseDiscussDao;
	
	/**
	 * 发布病例讨论
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月4日
	 */
	public Object releaseMedicalCaseDiscuss(HttpServletRequest req) {
		String in_patient_info = req.getParameter("in_patient_info");
		String exponent_auth_id = req.getParameter("exponent_auth_id");
		String hope_finish_time = req.getParameter("hope_finish_time");
		String exponent_content = req.getParameter("exponent_content");
		String stu_auth_ids = req.getParameter("stu_auth_ids");
		if (in_patient_info != null && !in_patient_info.equals("") && exponent_auth_id != null && !exponent_auth_id.equals("") &&
				hope_finish_time != null && !hope_finish_time.equals("") && exponent_content != null && !exponent_content.equals("") &&
				stu_auth_ids != null && !stu_auth_ids.equals("")) {
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Integer creator_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
			//MEDICAL_CASE_MAIN
			MedicalCaseMain medicalCaseMain = new MedicalCaseMain();
			int mcm_id = medicalCaseMainDao.getSequence();
			medicalCaseMain.setId(mcm_id);
			medicalCaseMain.setIn_patient_info(in_patient_info);
			medicalCaseMain.setContent(exponent_content);
			medicalCaseMain.setExponent_auth_id(Integer.parseInt(exponent_auth_id));
			Timestamp create_time = new Timestamp(new Date().getTime());
			medicalCaseMain.setCreate_time(create_time);
			medicalCaseMain.setCreator_auth_id(creator_auth_id);
			medicalCaseMain.setStatus("1");//  新建讨论
			medicalCaseMain.setState("Y");
			int num = medicalCaseMainDao.add(medicalCaseMain);
			//MEDICAL_CASE_MEMBER
			MedicalCaseMember medicalCaseMember = new MedicalCaseMember();
			medicalCaseMember.setMcm_id(mcm_id);
			medicalCaseMember.setState("Y");
			if (stu_auth_ids.indexOf(exponent_auth_id) == -1) {//  阐述人一定是讨论人
				medicalCaseMember.setId(medicalCaseMemberDao.getSequence());
				medicalCaseMember.setStu_auth_id(Integer.parseInt(exponent_auth_id));
				medicalCaseMemberDao.add(medicalCaseMember);
			}
			String[] stu_auth_id_array = stu_auth_ids.split(",");
			for (int i = 0; i < stu_auth_id_array.length; i++) {
				medicalCaseMember.setId(medicalCaseMemberDao.getSequence());
				medicalCaseMember.setStu_auth_id(Integer.parseInt(stu_auth_id_array[i]));
				medicalCaseMemberDao.add(medicalCaseMember);
			}
			if (num > 0) {
				return StringUtil.returns(true, "操作成功...");
			}else{
				return StringUtil.returns(false, "操作失败！");
			}
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
	
	/**
	 * 查询病例讨论列表
	 * @param pageIndex
	 * @param rows
	 * @param medicalCaseMain
	 * @return
	 * @author guocc
	 * @date 2017年9月5日
	 */
	public JSONObject selectMedicalCaseDiscuss(int pageIndex, int rows, MedicalCaseMain medicalCaseMain) {
		JSONObject jobj = new JSONObject();
		List<MedicalCaseMain> dataList = medicalCaseMainDao.selectMedicalCaseDiscuss(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				medicalCaseMain);
		int totalCount = medicalCaseMainDao.selectMCDCount(medicalCaseMain);
		for (MedicalCaseMain mcm : dataList) {
			if (mcm.getStatus().equals("1")) {
				mcm.setStatus_str("<span style='color:#FF0000;'>" + mcm.getStatus_str() + "</sapn>");
			}else if (mcm.getStatus().equals("2")) {
				mcm.setStatus_str("<span style='color:#0000FF;'>" + mcm.getStatus_str() + "</sapn>");
			}else if (mcm.getStatus().equals("3")) {
				mcm.setStatus_str("<span style='color:#FF00FF;'>" + mcm.getStatus_str() + "</sapn>");
			}else if (mcm.getStatus().equals("4")) {
				mcm.setStatus_str("<span style='color:#00FF00;'>" + mcm.getStatus_str() + "</sapn>");
			}
		}
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}


	public JSONObject selectPageDiscuss(int pageIndex, int rows,MedicalCaseMain medicalCaseMain, HttpServletRequest req) {
	//	String in_patient_info=req.getParameter("in_patient_info");
		String in_patient_info="";
		try {
			in_patient_info = new String(req.getParameter("in_patient_info").getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String beginTime=req.getParameter("beginTime");
		String endTime=req.getParameter("endTime");
		String status=req.getParameter("status");
		//从缓存中得到stu_auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		medicalCaseMain.setStatus(status);
		if(StringUtil.isNotEmpty(beginTime)){
			medicalCaseMain.setBeginTime(beginTime+" 00:00:00");
		}
		if(StringUtil.isNotEmpty(endTime)){
			medicalCaseMain.setEndTime(endTime+" 23:59:59");
		}
		medicalCaseMain.setIn_patient_info(in_patient_info);
		medicalCaseMain.setDiscuss_auth_id(stu_auth_id);
		medicalCaseMain.setState("Y");
		medicalCaseMain.setOrderCondition("t.create_time desc");
		JSONObject jobj = new JSONObject();
		int totalCount = medicalCaseMainDao.selectCountDiscuss(medicalCaseMain);
		List<MedicalCaseMain> dataList = medicalCaseMainDao.selectPageDiscuss(new RowBounds((pageIndex - 1) * rows, pageIndex * rows), medicalCaseMain);
		for (MedicalCaseMain mcm : dataList) {
			if (mcm.getStatus().equals("1")) {
				mcm.setDiscussStatus("<span style='color:#FF0000;'>" + mcm.getDiscussStatus() + "</sapn>");
			}else if (mcm.getStatus().equals("2")) {
				mcm.setDiscussStatus("<span style='color:#0000FF;'>" + mcm.getDiscussStatus() + "</sapn>");
			}else if (mcm.getStatus().equals("3")) {
				mcm.setDiscussStatus("<span style='color:#FF00FF;'>" + mcm.getDiscussStatus() + "</sapn>");
			}else if (mcm.getStatus().equals("4")) {
				mcm.setDiscussStatus("<span style='color:#00FF00;'>" + mcm.getDiscussStatus() + "</sapn>");
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
	 * 发表评论
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月5日
	 */
	public Object submitDiscuss(HttpServletRequest req) {
		//获取页面参数
		String id = req.getParameter("id");
		String discuss_content = req.getParameter("discuss_content");
		if (id != null && !id.equals("") && discuss_content != null && !discuss_content.equals("")) {
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Integer discuss_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
			//更新MEDICAL_CASE_MAIN表状态
			MedicalCaseMain mcm = new MedicalCaseMain();
			mcm.setId(Integer.parseInt(id));
			mcm.setStatus("3");//  讨论状态
//			MedicalCaseMain medicalCaseMain = medicalCaseMainDao.selectOne(Integer.parseInt(id));
			Timestamp time = new Timestamp(new Date().getTime());
//			if (medicalCaseMain.getStart_time() == null) {
//				mcm.setStart_time(time);
//			}
			int num1 = medicalCaseMainDao.update(mcm);
			//给MEDICAL_CASE_DISCUSS表添加讨论记录
			MedicalCaseDiscuss medicalCaseDiscuss = new MedicalCaseDiscuss();
			medicalCaseDiscuss.setId(medicalCaseDiscussDao.getSequence());
			medicalCaseDiscuss.setMcm_id(Integer.parseInt(id));
			medicalCaseDiscuss.setContent(discuss_content);
			medicalCaseDiscuss.setDiscuss_auth_id(discuss_auth_id);
			medicalCaseDiscuss.setDiscuss_time(time);
			medicalCaseDiscuss.setState("Y");
			int num2 = medicalCaseDiscussDao.add(medicalCaseDiscuss);
			if (num1 > 0 && num2 > 0) {
				return StringUtil.returns(true, "操作成功...");
			}else{
				return StringUtil.returns(false, "操作失败！");
			}
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
	
	/**
	 * 获取阐述人和评论人
	 * @param medicalCaseMain
	 * @return
	 * @author guocc
	 * @date 2017年9月5日
	 */
	public List<MedicalCaseMain> getDiscussants(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainDao.getDiscussants(medicalCaseMain);
	}
	
	/**
	 * 讨论记录流程显示
	 * @param medicalCaseMain
	 * @return
	 * @author guocc
	 * @date 2017年9月5日
	 */
	public List<MedicalCaseMain> getDiscussRecords(MedicalCaseMain medicalCaseMain) {
		return medicalCaseMainDao.getDiscussRecords(medicalCaseMain);
	}

}
