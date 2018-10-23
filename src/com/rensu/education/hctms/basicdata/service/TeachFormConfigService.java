package com.rensu.education.hctms.basicdata.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.RoleInfo;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.TeachFormConfig;
import com.rensu.education.hctms.basicdata.bean.TfcDeptformRela;
import com.rensu.education.hctms.basicdata.bean.TfcStutypeformRela;
import com.rensu.education.hctms.basicdata.dao.FormInfoDao;
import com.rensu.education.hctms.basicdata.dao.TeachFormConfigDao;
import com.rensu.education.hctms.basicdata.dao.TfcDeptformRelaDao;
import com.rensu.education.hctms.basicdata.dao.TfcStutypeformRelaDao;


@Service("teachFormConfigService")
public class TeachFormConfigService extends BaseService<TeachFormConfig> {
	
	Logger log = Logger.getLogger(TeachFormConfigService.class);
	
	@Autowired
	protected TeachFormConfigDao teachFormConfigDao;
	@Autowired
	protected FormInfoDao formInfoDao;
	@Autowired
	protected TfcDeptformRelaDao tfcDeptformRelaDao;
	@Autowired
	protected TfcStutypeformRelaDao tfcStutypeformRelaDao;

//	public Map<String, Object> getOrgaListAndStuType(HttpServletRequest req) {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("success", true);
//		//从缓存中  拿到所有科室
//		@SuppressWarnings("unchecked")
//		List<OrgaInfo> Orgalist = (List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
//		List<OrgaInfo> OrgaLists=new ArrayList<OrgaInfo>();
//		for (int i = 0; i < Orgalist.size(); i++) {
//			if(Orgalist.get(i).getOrga_type()==1&&Orgalist.get(i).getState().equals("Y")){
//				OrgaLists.add(Orgalist.get(i));
//			}
//		}
//		map.put("OrgaLists", OrgaLists);
//		
//		//从缓存中 拿到所有的学生类型
//		@SuppressWarnings("unchecked")
//		List<StuType> stuTypeList=(List<StuType>) req.getSession().getServletContext().getAttribute(Consts.SESSION_STU_TYPE);
//		map.put("stuTypeList", stuTypeList);
//		
//		//拿到TFC_DEPTFORM_RELA的数据
//		List<TfcDeptformRela>  lists=tfcDeptformRelaDao.getTfcList();
//		map.put("tfcLists", lists);
//		return map;
//	}
	
	public Map<String, Object> getOrgaListAndStuType(HttpServletRequest req) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("success", true);
		//从缓存中  拿到所有科室
		@SuppressWarnings("unchecked")
		List<OrgaInfo> Orgalist = (List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
		List<OrgaInfo> OrgaLists=new ArrayList<OrgaInfo>();
		for (int i = 0; i < Orgalist.size(); i++) {
			if(Orgalist.get(i).getOrga_type()==1&&Orgalist.get(i).getState().equals("Y")){//  移到规培系统时，两个库的ORGA_INFO表数据不一样，此处做了相应的修改
				OrgaLists.add(Orgalist.get(i));
			}
		}
		map.put("OrgaLists", OrgaLists);
		
		//从缓存中 拿到所有的学生类型
		@SuppressWarnings("unchecked")
		List<StuType> stuTypeList=(List<StuType>) req.getSession().getServletContext().getAttribute(Consts.SESSION_STU_TYPE);
		map.put("stuTypeList", stuTypeList);
		
		//拿到TFC_DEPTFORM_RELA的数据
		List<TfcDeptformRela>  lists=tfcDeptformRelaDao.getTfcList();
		map.put("tfcLists", lists);
		
		
		//从缓存中拿到角色表内容
		@SuppressWarnings("unchecked")
		List<RoleInfo> roleAll=(List<RoleInfo>)req.getSession().getServletContext().getAttribute(Consts.SESSION_ROLE_INFO);
		List<RoleInfo> roleList=new ArrayList<RoleInfo>();
		for (int i = 0; i < roleAll.size(); i++) {
			if(roleAll.get(i).getRole_id()!=100&&roleAll.get(i).getRole_id()!=99&&roleAll.get(i).getRole_id()!=10){//   移到规培系统时，两个库的ROLE_INFO表数据不一样，此处做了相应的修改
				roleList.add(roleAll.get(i));
			}
		}
		map.put("roleList", roleList);
		
		//从出科表中 TFC_STUTYPEFORM_RELA    拿数据
		TfcStutypeformRela tfcStutypeformRela=new TfcStutypeformRela();
		tfcStutypeformRela.setState("Y");
		List<TfcStutypeformRela> lists_tsr=tfcStutypeformRelaDao.getTfcList(tfcStutypeformRela);
		map.put("listsTsr", lists_tsr);
		return map;
	}

	public JSONObject getForm(int page, int rows, HttpServletRequest req){
		JSONObject jobj = new JSONObject();
		FormInfo formInfo=new FormInfo();
		String name=req.getParameter("name");
		String type_id=req.getParameter("type_id");
		if(name!=""&&name!=null){
			formInfo.setName(name);
		}
		if(type_id!=""&&type_id!=null){
			formInfo.setType_id(Integer.parseInt(type_id));
		}
		formInfo.setState("Y");
		int totalCount = formInfoDao.selectCount(formInfo);
		List<FormInfo> dataList = formInfoDao.getForm(
				new RowBounds((page - 1) * rows, page * rows),
				formInfo);
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("page", page);// 当前页码
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		if(jobj!=null){
			String jsonStr = StringUtil.dnull(jobj.toString());
			jobj = JSONObject.fromObject(jsonStr);
		}
		
		return jobj;
	}

}
