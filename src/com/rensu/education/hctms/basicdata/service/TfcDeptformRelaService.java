package com.rensu.education.hctms.basicdata.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.score.bean.ScoFormMain;
import com.rensu.education.hctms.score.dao.ScoFormMainDao;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.basicdata.bean.TfcDeptformRela;
import com.rensu.education.hctms.basicdata.dao.TfcDeptformRelaDao;


@Service("tfcDeptformRelaService")
public class TfcDeptformRelaService extends BaseService<TfcDeptformRela> {
	
	Logger log = Logger.getLogger(TfcDeptformRelaService.class);
	
	@Autowired
	protected TfcDeptformRelaDao tfcDeptformRelaDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	@Autowired
	protected ScoFormMainDao scoFormMainDao;

	public int addTDR(HttpServletRequest req) {
		Integer ftc_id=Integer.parseInt(req.getParameter("ftc_id"));
		String stuId=req.getParameter("stu_id");
		Integer formId=Integer.parseInt(req.getParameter("formId"));
		Integer orga_id=Integer.parseInt(req.getParameter("orga_id"));
		TfcDeptformRela tfcDeptformRela=new TfcDeptformRela();
		tfcDeptformRela.setForm_id(formId);
		tfcDeptformRela.setTfc_id(ftc_id);		
		tfcDeptformRela.setDept_id(orga_id);
		tfcDeptformRela.setState("Y");
		int i=0;
		if(stuId!=""&&stuId!=null){
		Integer stu_id=Integer.parseInt(stuId);
		tfcDeptformRela.setStu_type_id(stu_id);
		TfcDeptformRela tfDeptformRela1=tfcDeptformRelaDao.selectOneTfc(tfcDeptformRela);
		if(tfDeptformRela1!=null){  //就是存在
			tfcDeptformRela.setId(tfDeptformRela1.getId());
			i=tfcDeptformRelaDao.update(tfcDeptformRela);
		}else{
		tfcDeptformRela.setId(tfcDeptformRelaDao.getSequence());
	    i=tfcDeptformRelaDao.add(tfcDeptformRela);	//添加
		}
		}else{
			//从缓存中 拿到所有的学生类型
			@SuppressWarnings("unchecked")
			List<StuType> stuTypeList=(List<StuType>) req.getSession().getServletContext().getAttribute(Consts.SESSION_STU_TYPE);
			for (int j = 0; j < stuTypeList.size(); j++) {
				tfcDeptformRela.setStu_type_id(stuTypeList.get(j).getId());				
				TfcDeptformRela tfDeptformRela1=tfcDeptformRelaDao.selectOneTfc(tfcDeptformRela);
				if(tfDeptformRela1!=null){  //就是存在
					tfcDeptformRela.setId(tfDeptformRela1.getId());
					i=tfcDeptformRelaDao.update(tfcDeptformRela);
				}else{
				tfcDeptformRela.setId(tfcDeptformRelaDao.getSequence());
			    i=tfcDeptformRelaDao.add(tfcDeptformRela);	//添加
				}
			}
		}
		return i;
	}

	public TfcDeptformRela getStuFormId(HttpServletRequest req) {
		//权限id
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stuAuthId=loginBean.getvUserDetailInfo().getAuth_id();
		
		//从缓存中得到当前科室id
		Integer orgaId = loginBean.getvUserDetailInfo().getOrga_id();
		ScoFormMain scoFormMain = new ScoFormMain();
		scoFormMain.setUser_auth_id(stuAuthId);
		scoFormMain.setOrga_id(orgaId);
		scoFormMain.setState("Y");
		scoFormMain.setType_id(1);
		List<ScoFormMain> list=scoFormMainDao.selectList(scoFormMain);
		TfcDeptformRela tfcDeptformRela1=null;
		if(list.size()==0){
		UserAuthority userAuthority=userAuthorityDao.selectOne(stuAuthId);
		TfcDeptformRela tfcDeptformRela=new TfcDeptformRela();
		tfcDeptformRela.setDept_id(orgaId);
		tfcDeptformRela.setStu_type_id(userAuthority.getStu_type_id());
		tfcDeptformRela.setTfc_id(1);
		tfcDeptformRela1=tfcDeptformRelaDao.selectOneTfc(tfcDeptformRela);
		}
			return tfcDeptformRela1;
	}
}
