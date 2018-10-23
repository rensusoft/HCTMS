package com.rensu.education.hctms.basicdata.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;






import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.OutdeptRecord;
import com.rensu.education.hctms.log.dao.OutdeptRecordDao;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.score.bean.ScoFormMain;
import com.rensu.education.hctms.score.dao.ScoFormMainDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.TfcStutypeformRela;
import com.rensu.education.hctms.basicdata.dao.FormInfoDao;
import com.rensu.education.hctms.basicdata.dao.TfcStutypeformRelaDao;


@Service("tfcStutypeformRelaService")
public class TfcStutypeformRelaService extends BaseService<TfcStutypeformRela> {
	
	Logger log = Logger.getLogger(TfcStutypeformRelaService.class);
	
	@Autowired
	protected TfcStutypeformRelaDao tfcStutypeformRelaDao;

	@Autowired
	protected ScoFormMainDao scoFormMainDao;
	@Autowired
	protected OutdeptRecordDao outdeptRecordDao;
	@Autowired
	protected FormInfoDao formInfoDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;

	
	
	/**
	 * 得到出科审核页面的表单配置列表
	 * @param tfcStutypeformRela
	 * @return List<TfcStutypeformRela>
	 * @author guocc
	 * @date 2017年4月25日
	 */
	public List<TfcStutypeformRela> getFormList(TfcStutypeformRela tfcStutypeformRela) {
		return tfcStutypeformRelaDao.getFormList(tfcStutypeformRela);
	}

	/**
	 * 出科审核流程初始化
	 * @param req
	 * @return Map<String, Object>
	 * @author guocc
	 * @date 2017年4月25日
	 */
	public Map<String, Object> getProcessInfor(HttpServletRequest req) {
		String pub_num = req.getParameter("pub_num");
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		String stuTypeId = req.getParameter("stuTypeId");
		//得到当前角色
		LoginBean loginBean=(LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
//		Integer stuTypeId=loginBean.getvUserDetailInfo().getStu_type_id();   //学生类型
		Integer stuId=loginBean.getvUserDetailInfo().getAuth_id();    //学生
		Integer stu_type_id = loginBean.getvUserDetailInfo().getStu_type_id();
		//当前科室
		Integer orgaId=loginBean.getvUserDetailInfo().getOrga_id();
			
//		TfcStutypeformRela tfcStutypeformRela=new TfcStutypeformRela();     //查询条件	
//		tfcStutypeformRela.setState("Y");
//		tfcStutypeformRela.setStu_type_id(stuTypeId);   //学生类型
//		tfcStutypeformRela.setOrga_id(orgaId);   //科室id
		Map<String, Object> map=new HashMap<String, Object>();
//		tfcStutypeformRela.setRole_id("10"); 
//		List<TfcStutypeformRela> listStu=tfcStutypeformRelaDao.getTfcList(tfcStutypeformRela);
//		map.put("listStu", listStu);
//		tfcStutypeformRela.setRole_id("20;30");    //教师    秘书
//		List<TfcStutypeformRela> listTea=tfcStutypeformRelaDao.getTfcList(tfcStutypeformRela);
//		map.put("listTea", listTea);
//		tfcStutypeformRela.setRole_id("40");		//科主任
//		List<TfcStutypeformRela> listScea=tfcStutypeformRelaDao.getTfcList(tfcStutypeformRela);
//		map.put("listScea", listScea);
//		tfcStutypeformRela.setRole_id("50");		//科教科
//		List<TfcStutypeformRela> listKe=tfcStutypeformRelaDao.getTfcList(tfcStutypeformRela);
//		map.put("listKe", listKe);
		
		//获取当前角色id
//		Integer roleId= loginBean.getvUserDetailInfo().getRole_id();
//		if(roleId==20||roleId==30){
//			map.put("lists", listTea);
//		}else if(roleId==40){
//			map.put("lists", listScea);
//		}else if(roleId==50){
//			map.put("lists", listKe);
//		}
		
//		map.put("success", true);
		
		
		OutdeptRecord outdeptRecord=new OutdeptRecord();
		if (pub_num != null && !pub_num.equals("")) {
			outdeptRecord.setPub_num(Integer.parseInt(pub_num));
		}
		outdeptRecord.setState("Y");
		if (s_orga_id != null && !s_orga_id.equals("")) {
			outdeptRecord.setOrga_id(Integer.parseInt(s_orga_id));
		}else{
			outdeptRecord.setOrga_id(orgaId);
		}
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			outdeptRecord.setStu_auth_id(Integer.parseInt(s_user_auth_id));
		}else{
			outdeptRecord.setStu_auth_id(stuId);
		}
		
		outdeptRecord.setOrderCondition(" o.exam_datetime");
		List<OutdeptRecord> outlists=outdeptRecordDao.selectFlowList(outdeptRecord);
		List<ScoFormMain> scoFormMainList = new ArrayList<ScoFormMain>();
		List<ScoFormMain> sfmList = new ArrayList<ScoFormMain>();
		int flag = -1;//  老师教秘审核记录处显示分数标识（1：不显示 / -1：显示）
		TfcStutypeformRela tfcStutypeformRela = new TfcStutypeformRela();
		tfcStutypeformRela.setState("Y");
		if (stuTypeId != null && !stuTypeId.equals("")) {
			tfcStutypeformRela.setStu_type_id(Integer.parseInt(stuTypeId));
		}else{
			tfcStutypeformRela.setStu_type_id(stu_type_id);
		}
		tfcStutypeformRela.setRole_id("20;30");
		if (s_orga_id != null && !s_orga_id.equals("")) {
			tfcStutypeformRela.setOrga_id(Integer.parseInt(s_orga_id));
		}else{
			tfcStutypeformRela.setOrga_id(orgaId);
		}
		for (OutdeptRecord oRecord : outlists) {
			ScoFormMain scoFormMain = new ScoFormMain();
			scoFormMain.setOr_id(oRecord.getId());
			scoFormMainList = scoFormMainDao.selectList(scoFormMain);
			//写的太死！！！
			if (oRecord.getExam_role_id() == 10) {
				map.put("listStu", scoFormMainList);
			}else if (oRecord.getExam_role_id() == 20 || oRecord.getExam_role_id() == 30) {
				if (oRecord.getExam_result() != null && (oRecord.getExam_result() == 0 || oRecord.getExam_result() == -1)) {
					flag = 1;
				}
				for (ScoFormMain sfm : scoFormMainList) {
					tfcStutypeformRela.setForm_id(sfm.getForm_id());
					List<TfcStutypeformRela> tfcStutypeformRelaList = tfcStutypeformRelaDao.selectList(tfcStutypeformRela);
					if (tfcStutypeformRelaList != null && !tfcStutypeformRelaList.isEmpty()) {
						if (tfcStutypeformRelaList.get(0).getVisual_flag() != null && tfcStutypeformRelaList.get(0).getVisual_flag() == 1) {
							sfmList.add(sfm);
						}
					}
				}
				map.put("listTea", sfmList);
			}else if (oRecord.getExam_role_id() == 40) {
				map.put("listScea", scoFormMainList);
			}else if (oRecord.getExam_role_id() == 50) {
				map.put("listKe", scoFormMainList);
			}
		}
		map.put("outlists", outlists);
		map.put("flag", flag);

		return map;
	}


	public Map<String, Object> getStuInfor(HttpServletRequest req) {	
	String pub_num = req.getParameter("pub_num");
	String s_orga_id = req.getParameter("s_orga_id");
	String stu_type_id = req.getParameter("stuTypeId");
//	Integer stuTypeId=Integer.parseInt(req.getParameter("stuTypeId"));    //学生类型
	Integer stuId=Integer.parseInt(req.getParameter("stuId"));    //学生
	//得到当前角色
	LoginBean loginBean=(LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
	//当前科室
//	Integer orgaId=loginBean.getvUserDetailInfo().getOrga_id();
		
//	TfcStutypeformRela tfcStutypeformRela=new TfcStutypeformRela();     //查询条件	
//	tfcStutypeformRela.setState("Y");
//	tfcStutypeformRela.setStu_type_id(stuTypeId);   //学生类型
//	tfcStutypeformRela.setOrga_id(orgaId);   //科室id
	Map<String, Object> map=new HashMap<String, Object>();
//	tfcStutypeformRela.setRole_id("10"); 
//	List<TfcStutypeformRela> listStu=tfcStutypeformRelaDao.getTfcList(tfcStutypeformRela);
//	map.put("listStu", listStu);
//	tfcStutypeformRela.setRole_id("20;30");    //教师    秘书
//	List<TfcStutypeformRela> listTea=tfcStutypeformRelaDao.getTfcList(tfcStutypeformRela);
//	map.put("listTea", listTea);
//	tfcStutypeformRela.setRole_id("40");		//科主任
//	List<TfcStutypeformRela> listScea=tfcStutypeformRelaDao.getTfcList(tfcStutypeformRela);
//	map.put("listScea", listScea);
//	tfcStutypeformRela.setRole_id("50");		//科教科
//	List<TfcStutypeformRela> listKe=tfcStutypeformRelaDao.getTfcList(tfcStutypeformRela);
//	map.put("listKe", listKe);
	
	//获取当前角色id
//	Integer roleId= loginBean.getvUserDetailInfo().getRole_id();
//	if(roleId==20||roleId==30){
//		map.put("lists", listTea);
//	}else if(roleId==40){
//		map.put("lists", listScea);
//	}else if(roleId==50){
//		map.put("lists", listKe);
//	}
//	
//	map.put("success", true);
	
	
	OutdeptRecord outdeptRecord=new OutdeptRecord();
	if (pub_num != null && !pub_num.equals("")) {
		outdeptRecord.setPub_num(Integer.parseInt(pub_num));
	}
	outdeptRecord.setState("Y");
	outdeptRecord.setStu_auth_id(stuId);
	if (s_orga_id != null && !s_orga_id.equals("")) {
		outdeptRecord.setOrga_id(Integer.parseInt(s_orga_id));
	}
	outdeptRecord.setOrderCondition(" o.exam_datetime");
	List<OutdeptRecord> outlists=outdeptRecordDao.selectFlowList(outdeptRecord);
	List<ScoFormMain> scoFormMainList = new ArrayList<ScoFormMain>();
	List<ScoFormMain> sfmList = new ArrayList<ScoFormMain>();
	int flag = -1;//  老师教秘审核记录处显示分数标识（1：不显示 / -1：显示）
	TfcStutypeformRela tfcStutypeformRela = new TfcStutypeformRela();
	tfcStutypeformRela.setState("Y");
	tfcStutypeformRela.setStu_type_id(Integer.parseInt(stu_type_id));
	tfcStutypeformRela.setRole_id("10");
	tfcStutypeformRela.setOrga_id(Integer.parseInt(s_orga_id));
	for (OutdeptRecord oRecord : outlists) {
		ScoFormMain scoFormMain = new ScoFormMain();
		scoFormMain.setOr_id(oRecord.getId());
		scoFormMainList = scoFormMainDao.selectList(scoFormMain);
		if (oRecord.getExam_role_id() == 10) {
			for (ScoFormMain sfm : scoFormMainList) {
				tfcStutypeformRela.setForm_id(sfm.getForm_id());
				List<TfcStutypeformRela> tfcStutypeformRelaList = tfcStutypeformRelaDao.selectList(tfcStutypeformRela);
				if (tfcStutypeformRelaList != null && !tfcStutypeformRelaList.isEmpty()) {
					if (tfcStutypeformRelaList.get(0).getVisual_flag() != null && tfcStutypeformRelaList.get(0).getVisual_flag() == 1) {
						sfmList.add(sfm);
					}
				}
			}
			map.put("listStu", sfmList);
		}else if (oRecord.getExam_role_id() == 20 || oRecord.getExam_role_id() == 30) {
			if (oRecord.getExam_result() != null && (oRecord.getExam_result() == 0 || oRecord.getExam_result() == -1)) {
				flag = 1;
			}
			map.put("listTea", scoFormMainList);
		}else if (oRecord.getExam_role_id() == 40) {
			map.put("listScea", scoFormMainList);
		}else if (oRecord.getExam_role_id() == 50) {
			map.put("listKe", scoFormMainList);
		}
	}
	map.put("outlists", outlists);
	map.put("flag", flag);

	return map;
	}  


	
	/**
	 * 查询表单名
	 * 根据
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年4月10日
	 */
	public List<FormInfo> selectTSR(HttpServletRequest req) {
		Integer ftc_id=Integer.parseInt(req.getParameter("ftc_id"));//  教学模板配置id
		Integer stuTypeId=Integer.parseInt(req.getParameter("stuTypeId"));//学生类型 Id
		String orga_id=req.getParameter("orga_id");  //科室id
		String roleId=req.getParameter("roleId");
		TfcStutypeformRela tfcStutypeformRela=new TfcStutypeformRela();
		tfcStutypeformRela.setTfc_id(ftc_id);
		tfcStutypeformRela.setState("Y");
		tfcStutypeformRela.setStu_type_id(stuTypeId);
		if(orga_id!=""&&orga_id!=null){  //不是统一绑定
			tfcStutypeformRela.setOrga_id(Integer.parseInt(orga_id));
			tfcStutypeformRela.setRole_id(roleId);
			List<FormInfo> lists=new ArrayList<FormInfo>();
			List<TfcStutypeformRela> tfcStutypeformRela2=tfcStutypeformRelaDao.selectList(tfcStutypeformRela);
			if(tfcStutypeformRela2!=null){
				for (int i = 0; i < tfcStutypeformRela2.size(); i++) {
					FormInfo formInfo=new FormInfo();
					formInfo.setId(tfcStutypeformRela2.get(i).getForm_id());
					formInfo.setState("Y");
					FormInfo form = formInfoDao.getForm(new RowBounds(0, 1),formInfo).get(0);
					form.setVisual_flag(tfcStutypeformRela2.get(i).getVisual_flag());
					lists.add(form);				
				}
			}
			return lists;
		}else{    //是统一绑定
			return null;
		}
		
	}
	
	/**
	 * 修改或者添加新的表单  （出科）
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年4月10日
	 */
	public int updateTSR(HttpServletRequest req) {
		Integer ftc_id=Integer.parseInt(req.getParameter("ftc_id"));//  教学模板配置id
		Integer stuTypeId=Integer.parseInt(req.getParameter("stuTypeId"));//学生类型 Id
		String orga_id=req.getParameter("orga_id");  //科室id
		String roleId=req.getParameter("roleId");
		TfcStutypeformRela tfcStutypeformRela=new TfcStutypeformRela();
		tfcStutypeformRela.setTfc_id(ftc_id);
		tfcStutypeformRela.setState("X");
		tfcStutypeformRela.setStu_type_id(stuTypeId);
		if(orga_id!=""&&orga_id!=null){  //不是统一绑定
		tfcStutypeformRela.setOrga_id(Integer.parseInt(orga_id));
		tfcStutypeformRela.setRole_id(roleId);
	    tfcStutypeformRelaDao.deleteAllByCon(tfcStutypeformRela);
		String formIds=req.getParameter("form_id");
		String[] formIDS=formIds.split(";");
		int i=0;
		for (int m = 0; m < formIDS.length; m++) {
			if (roleId != null && (roleId.equals("10") || roleId.equals("20;30"))) {
				String[] formIDVisual = formIDS[m].split("_");
				Integer formId=Integer.parseInt(formIDVisual[0]);   //表单id
				Integer visual_flag=Integer.parseInt(formIDVisual[1]);   //表单id
				//新增
				tfcStutypeformRela.setForm_id(formId);
				tfcStutypeformRela.setVisual_flag(visual_flag);
				tfcStutypeformRela.setState("Y");
				tfcStutypeformRela.setId(tfcStutypeformRelaDao.getSequence());
				i=tfcStutypeformRelaDao.add(tfcStutypeformRela);
			}else{
				Integer formId=Integer.parseInt(formIDS[m]);   //表单id
				//新增
				tfcStutypeformRela.setForm_id(formId);
				tfcStutypeformRela.setState("Y");
				tfcStutypeformRela.setId(tfcStutypeformRelaDao.getSequence());
				i=tfcStutypeformRelaDao.add(tfcStutypeformRela);
			}
			   
		}
		 return i;		
		}else{                   //统一绑定   修改或者新增			
			//查找所有科室
			//从缓存中  拿到所有科室
			@SuppressWarnings("unchecked")
			List<OrgaInfo> Orgalist = (List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
			List<OrgaInfo> OrgaLists=new ArrayList<OrgaInfo>();
			for (int i = 0; i < Orgalist.size(); i++) {//  移到规培系统时，两个库的ORGA_INFO表数据不一样，此处做了相应的修改
				if(Orgalist.get(i).getOrga_type()==1&&Orgalist.get(i).getState().equals("Y")){//  &&(Orgalist.get(i).getOrga_level()==0||Orgalist.get(i).getOrga_level()==2)
					OrgaLists.add(Orgalist.get(i));
				}
			}
			int t=0;
			for (int i = 0; i < OrgaLists.size(); i++) {
				String rotate=req.getParameter("check_val");   //角色名称
				org.json.JSONArray jsonArray = new org.json.JSONArray(rotate);
				for (int j = 0; j < jsonArray.length(); j++) {
					tfcStutypeformRela.setRole_id(jsonArray.get(j).toString());//得到单个角色
					tfcStutypeformRela.setOrga_id(OrgaLists.get(i).getOrga_id()); //这个科室
					tfcStutypeformRelaDao.deleteAllByCon(tfcStutypeformRela);        //删除相关数据
					String formIds=req.getParameter("form_id");
					String[] formIDS=formIds.split(";");      //新增
					for (int k = 0; k < formIDS.length; k++) {
						tfcStutypeformRela.setForm_id(Integer.parseInt(formIDS[k]));
						tfcStutypeformRela.setId(tfcStutypeformRelaDao.getSequence());
						tfcStutypeformRela.setState("Y");
					   t=tfcStutypeformRelaDao.add(tfcStutypeformRela);
					}	
					}
				}
			return t;
		}
	}

}
