package com.rensu.education.hctms.score.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.MarksheetDetail;
import com.rensu.education.hctms.basicdata.bean.MarksheetSub;
import com.rensu.education.hctms.basicdata.dao.FormInfoDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.log.bean.OutdeptRecord;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.score.bean.ScoFormDetail;
import com.rensu.education.hctms.score.bean.ScoFormMain;
import com.rensu.education.hctms.score.bean.ScoFormSub;
import com.rensu.education.hctms.score.dao.ScoFormDetailDao;
import com.rensu.education.hctms.score.dao.ScoFormMainDao;
import com.rensu.education.hctms.score.dao.ScoFormSubDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("scoFormMainService")
public class ScoFormMainService extends BaseService<ScoFormMain> {
	
	Logger log = Logger.getLogger(ScoFormMainService.class);
	
	@Autowired
	protected ScoFormMainDao scoFormMainDao;    
	@Autowired
	protected ScoFormDetailDao scoFormDetailDao;  
	@Autowired
	protected FormInfoDao formInfoDao;  
	@Autowired
	protected ScoFormSubDao scoFormSubDao;
	
	public Map<String,Object> getIndeptEducInfo(HttpServletRequest req){
		String stu_auth_id = req.getParameter("stu_auth_id");
		String dept_id = req.getParameter("dept_id");
		String type_id = req.getParameter("type_id");
		if(StringUtil.isEmpty(stu_auth_id)||StringUtil.isEmpty(dept_id)
				||StringUtil.isEmpty(type_id)){
			return StringUtil.returns(false, "基础参数数据出错...");
		}
		ScoFormMain sfm = new ScoFormMain();
		sfm.setUser_auth_id(Integer.parseInt(stu_auth_id));
		sfm.setOrga_id(Integer.parseInt(dept_id));
		sfm.setType_id(Integer.parseInt(type_id));
		List<ScoFormMain> sfmList = scoFormMainDao.selectList(sfm);
		if(sfmList!=null&&sfmList.size()>0){
			String htmlContent = sfmList.get(0).getHtml_content();
			return StringUtil.returns(true, htmlContent);
		}
		return StringUtil.returns(false, "未查询到相关数据...");
	}
	
	/**
	 * 表单配置  提交表单内容
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月27日
	 */
	public boolean stuIndept(HttpServletRequest req){
		boolean fl = false;
		String dataType = req.getParameter("dataType");
		if("html".equals(dataType)){ //html表单提交
			fl = this.saveHtmlData(req);
		}else if ("mark".equals(dataType)) { //评分表单提交
			fl = this.saveMarkSheetData(req);
		}
		return fl;
	}
	/**
	 * 保存普通表单数据
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月27日
	 */
	public boolean saveHtmlData(HttpServletRequest req){
		String content = req.getParameter("content");
		String form_name = req.getParameter("form_name");
		String form_id = req.getParameter("form_id");
		String flag = req.getParameter("flag");
		String id = req.getParameter("id");
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		//从内存中读取当前操作人
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		ScoFormMain sfm = new ScoFormMain();
		int f1 = 0;
		if (flag != null && flag.equals("1")) {
			if (id != null && !id.equals("")) {
				sfm.setId(Integer.parseInt(id));
				sfm.setHtml_content(content);
				f1 = scoFormMainDao.update(sfm);
				if (f1 > 0) {
					return true;
				}else{
					return false;
				}
			}
		}
		sfm.setId(scoFormMainDao.getSequence());
		sfm.setType_id(2); 
		sfm.setForm_type(2);
		sfm.setForm_name(form_name);
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			sfm.setUser_auth_id(Integer.parseInt(s_user_auth_id));//  老师复用，要从前台取数据
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			sfm.setOrga_id(Integer.parseInt(s_orga_id));//  老师复用，要从前台取数据
		}
		sfm.setHtml_content(content);
		sfm.setCreate_auth_id(create_auth_id);
		sfm.setCreate_time(new java.sql.Timestamp(new java.util.Date().getTime()));
		sfm.setState("Y");
		if (form_id != null && !form_id.equals("")) {
			sfm.setForm_id(Integer.parseInt(form_id));
		}
		int f2 = scoFormMainDao.add(sfm);
		if (f2 > 0) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 保存评分表单数据
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年5月2日
	 */
	public boolean saveMarkSheetData(HttpServletRequest req) {
		String form_id = req.getParameter("form_id");
		String form_name = req.getParameter("form_name");
		String form_sco = req.getParameter("form_sco");
		String get_sco = req.getParameter("get_sco");
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		String flag = req.getParameter("flag");
		String sfm_id_str = req.getParameter("sfm_id");
		String id_score_str_array = req.getParameter("id_score_str_array");
		org.json.JSONArray jsonArray = new org.json.JSONArray(id_score_str_array);
		//从内存中读取当前操作人
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer stuAuthId = loginBean.getvUserDetailInfo().getAuth_id();
		int f1 = 0;
		if (flag != null && flag.equals("1")) {// 修改数据
			ScoFormMain scoFormMain = new ScoFormMain();
			if (form_id != null && !form_id.equals("")) {
				scoFormMain.setForm_id(Integer.parseInt(form_id));
			}
			if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
				scoFormMain.setUser_auth_id(Integer.parseInt(s_user_auth_id));//  老师复用，要从前台取数据
			}
			if (s_orga_id != null && !s_orga_id.equals("")) {
				scoFormMain.setOrga_id(Integer.parseInt(s_orga_id));//  老师复用，要从前台取数据
			}
			Integer sfm_id = 0;
			if (sfm_id_str != null && !sfm_id_str.equals("")) {
				sfm_id = Integer.parseInt(sfm_id_str);
			}
//			Integer sfm_id = scoFormMainDao.querySFMId(scoFormMain).getId();
			ScoFormMain sfm = new ScoFormMain();
			sfm.setId(sfm_id);
			if (get_sco != null && !get_sco.equals("")) {
				sfm.setGet_sco(Integer.parseInt(get_sco));
			}
			int num1 = scoFormMainDao.update(sfm);//  修改SCO_FORM_MAIN表得分数
			int num2 = 0;
			for (int i = 0; i < jsonArray.length(); i++) {
				ScoFormSub scoFormSub = new ScoFormSub();
				scoFormSub.setId(Integer.parseInt(jsonArray.get(i).toString().substring(0, jsonArray.get(i).toString().indexOf("-"))));
				scoFormSub.setSco_num(Integer.parseInt(jsonArray.get(i).toString().substring(jsonArray.get(i).toString().indexOf("-") + 1)));
				num2 = scoFormSubDao.update(scoFormSub);
			}
			if (num1 > 0 && num2 > 0) {
				return true;
			}else{
				return false;
			}
		}else if (flag != null && flag.equals("-1")) {//  插入数据
			//往SCO_FORM_MAIN表里写数据
			ScoFormMain sfm = new ScoFormMain();
			int sfm_id = scoFormMainDao.getSequence();
			sfm.setId(sfm_id);
			sfm.setType_id(2); 
			sfm.setForm_type(1);
			sfm.setForm_name(form_name);
			if (form_sco != null && !form_sco.equals("")) {
				sfm.setForm_sco(Integer.parseInt(form_sco));
			}
			if (get_sco != null && !get_sco.equals("")) {
				sfm.setGet_sco(Integer.parseInt(get_sco));
			}
			if (form_id != null && !form_id.equals("")) {
				sfm.setForm_id(Integer.parseInt(form_id));
			}
			if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
				sfm.setUser_auth_id(Integer.parseInt(s_user_auth_id));//  老师复用，要从前台取数据
			}
			if (s_orga_id != null && !s_orga_id.equals("")) {
				sfm.setOrga_id(Integer.parseInt(s_orga_id));//  老师复用，要从前台取数据
			}
			sfm.setCreate_auth_id(stuAuthId);
			sfm.setCreate_time(new java.sql.Timestamp(new java.util.Date().getTime()));
			sfm.setState("Y");
			f1 = scoFormMainDao.add(sfm);
			//往SCO_FORM_SUB表和SCO_FORM_DETAIL表里写数据
			int m1 = 0;
			int m2 = 0;
			int m3 = 0;
			boolean result = true;
			FormInfo formInfo = new FormInfo();
			if (form_id != null && !form_id.equals("")) {
				formInfo.setId(Integer.parseInt(form_id));
			}
			List<MarksheetSub> marksheetSubMList = scoFormMainDao.findMarksheetSubM(formInfo);//  查询主项
			for (MarksheetSub marksheetSubM : marksheetSubMList) {
				ScoFormSub scoFormSubM = new ScoFormSub();
				int sfs_id = scoFormSubDao.getSequence();
				scoFormSubM.setId(sfs_id);
				scoFormSubM.setSfm_id(sfm_id);
				scoFormSubM.setType_code(marksheetSubM.getType_code());//  0
				scoFormSubM.setSort_num(marksheetSubM.getSort_num());
				scoFormSubM.setItem_type_code(marksheetSubM.getItem_type_code());
				scoFormSubM.setText(marksheetSubM.getText());
				scoFormSubM.setTitle(marksheetSubM.getTitle());
				scoFormSubM.setState("Y");
				m1 = scoFormSubDao.add(scoFormSubM);//  给SCO_FORM_SUB表的主项写数据
				List<MarksheetSub> marksheetSubSList = scoFormMainDao.findMarksheetSubS(marksheetSubM.getId());//  根据主项查子项
				for (MarksheetSub marksheetSubS : marksheetSubSList) {
					ScoFormSub scoFormSubS = new ScoFormSub();
					int sfs_id_detail = scoFormSubDao.getSequence();
					scoFormSubS.setId(sfs_id_detail);
					scoFormSubS.setSfm_id(sfm_id);
					scoFormSubS.setType_code(marksheetSubS.getType_code());//  1
					scoFormSubS.setSort_num(marksheetSubS.getSort_num());
					scoFormSubS.setItem_type_code(marksheetSubS.getItem_type_code());
					scoFormSubS.setText(marksheetSubS.getText());
					scoFormSubS.setTitle(marksheetSubS.getTitle());
					for (int i = 0; i < jsonArray.length(); i++) {
						if (marksheetSubS.getId() == Integer.parseInt(jsonArray.get(i).toString().substring(0, jsonArray.get(i).toString().indexOf("-")))) {
							scoFormSubS.setSco_num(Integer.parseInt(jsonArray.get(i).toString().substring(jsonArray.get(i).toString().indexOf("-") + 1)));
						}
					}
					scoFormSubS.setState("Y");
					scoFormSubS.setSfs_id(sfs_id);
					m2 = scoFormSubDao.add(scoFormSubS);//  给SCO_FORM_SUB表的子项写数据
					List<MarksheetDetail> marksheetDetailList = scoFormMainDao.findMarksheetDetail(marksheetSubS.getId());//  根据子项查mark_sheet_detail表的数据
					for (MarksheetDetail marksheetDetail : marksheetDetailList) {
						ScoFormDetail scoFormDetail = new ScoFormDetail();
						scoFormDetail.setId(scoFormDetailDao.getSequence());
						scoFormDetail.setSfs_id(sfs_id_detail);
						scoFormDetail.setText(marksheetDetail.getText());
						scoFormDetail.setSco_num(marksheetDetail.getSco_num());
//				scoFormDetail.setResult_val(result_val);
						scoFormDetail.setState("Y");
						m3 = scoFormDetailDao.add(scoFormDetail);//  往SCO_FORM_DETAIL表写数据
					}
				}
			}
		}
		if (f1 > 0) {
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 普通表单的内容展示
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月28日
	 */
	public Object showHtmlForm(HttpServletRequest req) {
		String form_id = req.getParameter("form_id");
		String s_user_auth_id = req.getParameter("s_user_auth_id");
		String s_orga_id = req.getParameter("s_orga_id");
		String create_auth_id_str = req.getParameter("create_auth_id");
		String sfm_id = req.getParameter("sfm_id");
		ScoFormMain scoFormMain = new ScoFormMain();
		if (create_auth_id_str != null && create_auth_id_str.equals("-100")) {//  列表处触发请求
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
			scoFormMain.setCreate_auth_id(create_auth_id);
		}else if(create_auth_id_str != null && !create_auth_id_str.equals("-100")) {//  审核流程记录处触发请求
			scoFormMain.setCreate_auth_id(Integer.parseInt(create_auth_id_str));
		}
//		scoFormMain.setForm_name(t_name);//  根据form_name查是有问题的！！！
		if (form_id != null && !form_id.equals("")) {
			scoFormMain.setForm_id(Integer.parseInt(form_id));
		}
		if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
			scoFormMain.setUser_auth_id(Integer.parseInt(s_user_auth_id));//  老师复用，要从前台取数据
		}
		if (s_orga_id != null && !s_orga_id.equals("")) {
			scoFormMain.setOrga_id(Integer.parseInt(s_orga_id));//  老师复用，要从前台取数据
		}
		if (sfm_id != null && !sfm_id.equals("")) {
			scoFormMain.setId(Integer.parseInt(sfm_id));
		}
		FormInfo formInfoGet = formInfoDao.getTableContentFromSFM(scoFormMain);
		return StringUtil.returns(true, formInfoGet);
	}
	
	
	/**
	 * 根据form_id查询这条数据的id标识
	 * @param scoFormMain
	 * @return ScoFormMain
	 * @author guocc
	 * @date 2017年5月4日
	 */
	public ScoFormMain querySFMId(ScoFormMain scoFormMain) {
		return scoFormMainDao.querySFMId(scoFormMain);
	}
	/**
	 * 根据pub_num查询这条数据的or_id
	 * @param outdeptRecord
	 * @return List<ScoFormMain>
	 * @author guocc
	 * @date 2017年5月18日
	 */
	public List<ScoFormMain> getOrIdByPubNum(OutdeptRecord outdeptRecord) {
		return scoFormMainDao.getOrIdByPubNum(outdeptRecord);
	}
	/**
	 * 根据or_id查询表单数据
	 * @param scoFormMain
	 * @return ScoFormMain
	 * @author guocc
	 * @date 2017年5月18日
	 */
	public ScoFormMain selectByOrId(ScoFormMain scoFormMain) {
		return scoFormMainDao.selectByOrId(scoFormMain);
	}

	/**
	 * 删除无效表单数据
	 * @param scoFormMain
	 * @return
	 * @author guocc
	 * @date 2017年9月1日
	 */
	public int updateState(ScoFormMain scoFormMain) {
		return scoFormMainDao.updateState(scoFormMain);
	}
	
}
