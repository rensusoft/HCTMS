package com.rensu.education.hctms.teach.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.teach.bean.MedicalAdviceMain;
import com.rensu.education.hctms.teach.bean.MedicalAdviceSub;
import com.rensu.education.hctms.teach.bean.MedicalDiagnose;
import com.rensu.education.hctms.teach.bean.MedicalRecord;
import com.rensu.education.hctms.teach.dao.MedicalAdviceMainDao;
import com.rensu.education.hctms.teach.dao.MedicalAdviceSubDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("medicalAdviceMainService")
public class MedicalAdviceMainService extends BaseService<MedicalAdviceMain> {
	
	Logger log = Logger.getLogger(MedicalAdviceMainService.class);
	
	@Autowired
	protected MedicalAdviceMainDao medicalAdviceMainDao;
	@Autowired
	protected MedicalAdviceSubDao medicalAdviceSubDao;
	
	/**
	 * 保存医疗文书--医嘱
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月18日
	 */
	public Object saveMedicalAdvice(HttpServletRequest req) {
		//
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		//
		String action=req.getParameter("action");
		String id=req.getParameter("id");
		String p_name=req.getParameter("p_name");
		String p_sex=req.getParameter("p_sex");
		String p_age=req.getParameter("p_age");
		String p_deptname=req.getParameter("p_deptname");
		String p_bednum=req.getParameter("p_bednum");
		String p_pid=req.getParameter("p_pid");
		int num = 0;
		MedicalAdviceMain medicalAdviceMain = new MedicalAdviceMain();
		medicalAdviceMain.setP_name(p_name);
		medicalAdviceMain.setP_sex(p_sex);
		medicalAdviceMain.setP_age(p_age);
		medicalAdviceMain.setP_deptname(p_deptname);
		medicalAdviceMain.setP_bednum(p_bednum);
		medicalAdviceMain.setP_pid(p_pid);
		if (action != null && action.equals("add")) {
			int mam_id = medicalAdviceMainDao.getSequence();
			medicalAdviceMain.setId(mam_id);
			medicalAdviceMain.setPk_code(java.util.UUID.randomUUID().toString());
			medicalAdviceMain.setStu_auth_id(stu_auth_id);
			Timestamp create_time= new Timestamp(System.currentTimeMillis());
			medicalAdviceMain.setCreate_time(create_time);
			medicalAdviceMain.setCorrect_status("N");
			medicalAdviceMain.setState("Y");
			num = medicalAdviceMainDao.add(medicalAdviceMain);
			//子表MEDICAL_ADVICE_SUB
			String rowData=req.getParameter("rowData");
			if (rowData != null && !rowData.equals("")) {
				JSONArray jsonArray = JSONArray.fromObject(rowData);
				List<MedicalAdviceSub> list = JSONArray.toList(jsonArray, MedicalAdviceSub.class);
				MedicalAdviceSub medicalAdviceSub = new MedicalAdviceSub();
				medicalAdviceSub.setMam_id(mam_id);
				medicalAdviceSub.setState("Y");
				for (MedicalAdviceSub mas : list) {
					medicalAdviceSub.setId(medicalAdviceSubDao.getSequence());
					medicalAdviceSub.setAdvice_name(mas.getAdvice_name());
					medicalAdviceSub.setAdvice_spec(mas.getAdvice_spec());
					medicalAdviceSub.setAdvice_dose(mas.getAdvice_dose());
					medicalAdviceSub.setDose_unit_code(mas.getDose_unit_code());
					medicalAdviceSub.setPath_code(mas.getPath_code());
					medicalAdviceSub.setFrequency_code(mas.getFrequency_code());
					medicalAdviceSub.setTotal_dose(mas.getTotal_dose());
					medicalAdviceSub.setTotal_dose_unit_code(mas.getTotal_dose_unit_code());
					medicalAdviceSubDao.add(medicalAdviceSub);
				}
			}
		}else if (action != null && action.equals("edit")) {
			if (id != null && !id.equals("")) {
				medicalAdviceMain.setId(Integer.parseInt(id));
			}
			num = medicalAdviceMainDao.update(medicalAdviceMain);
			//子表MEDICAL_ADVICE_SUB
			String rowData=req.getParameter("rowData");
			if (rowData != null && !rowData.equals("")) {
				MedicalAdviceSub mas = new MedicalAdviceSub();
				if (id != null && !id.equals("")) {
					mas.setMam_id(Integer.parseInt(id));
				}
				mas.setState("X");
				medicalAdviceSubDao.updateByMamId(mas);
				//
				JSONArray jsonArray = JSONArray.fromObject(rowData);
				List<MedicalAdviceSub> list = JSONArray.toList(jsonArray, MedicalAdviceSub.class);
				MedicalAdviceSub medicalAdviceSub = new MedicalAdviceSub();
				if (id != null && !id.equals("")) {
					medicalAdviceSub.setMam_id(Integer.parseInt(id));
				}
				medicalAdviceSub.setState("Y");
				for (MedicalAdviceSub masGet : list) {
					medicalAdviceSub.setId(medicalAdviceSubDao.getSequence());
					medicalAdviceSub.setAdvice_name(masGet.getAdvice_name());
					medicalAdviceSub.setAdvice_spec(masGet.getAdvice_spec());
					medicalAdviceSub.setAdvice_dose(masGet.getAdvice_dose());
					medicalAdviceSub.setDose_unit_code(masGet.getDose_unit_code());
					medicalAdviceSub.setPath_code(masGet.getPath_code());
					medicalAdviceSub.setFrequency_code(masGet.getFrequency_code());
					medicalAdviceSub.setTotal_dose(masGet.getTotal_dose());
					medicalAdviceSub.setTotal_dose_unit_code(masGet.getTotal_dose_unit_code());
					medicalAdviceSubDao.add(medicalAdviceSub);
				}
			}
		}
		if (num > 0) {
			return StringUtil.returns(true, "操作成功...");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}

	/**
	 * 根据id查询医嘱
	 * @param id
	 * @return
	 * @author guocc
	 * @date 2017年8月24日
	 */
	public MedicalAdviceMain selectMedicalAdviceMainById(int id) {
		return medicalAdviceMainDao.selectMedicalAdviceMainById(id);
	}
	
	public Object saveMedicalAdviceTea(HttpServletRequest req) {
		//
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer teach_auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		//
		String action=req.getParameter("action");
		String id=req.getParameter("id");
		String p_name=req.getParameter("p_name");
		String p_sex=req.getParameter("p_sex");
		String p_age=req.getParameter("p_age");
		String p_deptname=req.getParameter("p_deptname");
		String p_bednum=req.getParameter("p_bednum");
		String p_pid=req.getParameter("p_pid");
		int num = 0;
		MedicalAdviceMain medicalAdviceMain = new MedicalAdviceMain();
		medicalAdviceMain.setP_name(p_name);
		medicalAdviceMain.setP_sex(p_sex);
		medicalAdviceMain.setP_age(p_age);
		medicalAdviceMain.setP_deptname(p_deptname);
		medicalAdviceMain.setP_bednum(p_bednum);
		medicalAdviceMain.setP_pid(p_pid);
		medicalAdviceMain.setCorrect_auth_id(teach_auth_id);
		MedicalAdviceMain medicalAdviceMain2=medicalAdviceMainDao.selectOne(Integer.parseInt(id));
		medicalAdviceMain.setStu_auth_id(medicalAdviceMain2.getStu_auth_id());
		medicalAdviceMain.setState("Y");
		MedicalAdviceMain medicalAdviceMain3=new MedicalAdviceMain();
		medicalAdviceMain3.setPk_code(medicalAdviceMain2.getPk_code());
		List<MedicalAdviceMain> list1=medicalAdviceMainDao.selectList(medicalAdviceMain3);
		for(int i=0;i<list1.size();i++){
			if(list1.get(i).getCorrect_status()==""||list1.get(i).getCorrect_status()==null){ //老师批改记录中审核状态为空
				MedicalAdviceMain medicalAdviceMain4=new MedicalAdviceMain();                
				medicalAdviceMain4.setId(list1.get(i).getId());                          //获得老师第一次批改记录ID，
				medicalAdviceMainDao.delete(medicalAdviceMain4);                            //删除老师第一次批改的记录
			    	MedicalAdviceSub medicalAdviceSub=new MedicalAdviceSub();
			    	medicalAdviceSub.setMam_id(list1.get(i).getId());
			    	List<MedicalAdviceSub> medicalAdviceSub2=medicalAdviceSubDao.selectList(medicalAdviceSub);
			    	for(int j=0;j<medicalAdviceSub2.size();j++){
			    		medicalAdviceSub.setId(medicalAdviceSub2.get(j).getId());
			    		medicalAdviceSubDao.delete(medicalAdviceSub);
			    }
			}
		}
		if (action != null && action.equals("add")) {
			int mam_id = medicalAdviceMainDao.getSequence();
			medicalAdviceMain.setId(mam_id);
			medicalAdviceMain.setPk_code(medicalAdviceMain2.getPk_code());
			Timestamp create_time= new Timestamp(System.currentTimeMillis());
			medicalAdviceMain.setCreate_time(create_time);
			medicalAdviceMain2.setCorrect_status("Y");
			medicalAdviceMain2.setCorrect_auth_id(teach_auth_id);
			medicalAdviceMain2.setCorrect_time(create_time);
			medicalAdviceMainDao.update(medicalAdviceMain2);
			num = medicalAdviceMainDao.add(medicalAdviceMain);
			//子表MEDICAL_ADVICE_SUB
			String rowData=req.getParameter("rowData");
			if (rowData != null && !rowData.equals("")) {
				JSONArray jsonArray = JSONArray.fromObject(rowData);
				List<MedicalAdviceSub> list = JSONArray.toList(jsonArray, MedicalAdviceSub.class);
				MedicalAdviceSub medicalAdviceSub = new MedicalAdviceSub();
				medicalAdviceSub.setMam_id(mam_id);
				medicalAdviceSub.setState("Y");
				for (MedicalAdviceSub mas : list) {
					medicalAdviceSub.setId(medicalAdviceSubDao.getSequence());
					medicalAdviceSub.setAdvice_name(mas.getAdvice_name());
					medicalAdviceSub.setAdvice_spec(mas.getAdvice_spec());
					medicalAdviceSub.setAdvice_dose(mas.getAdvice_dose());
					medicalAdviceSub.setDose_unit_code(mas.getDose_unit_code());
					medicalAdviceSub.setPath_code(mas.getPath_code());
					medicalAdviceSub.setFrequency_code(mas.getFrequency_code());
					medicalAdviceSub.setTotal_dose(mas.getTotal_dose());
					medicalAdviceSub.setTotal_dose_unit_code(mas.getTotal_dose_unit_code());
					medicalAdviceSubDao.add(medicalAdviceSub);
				}
			}
		}
		if (num > 0) {
			return StringUtil.returns(true, "操作成功...");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
}