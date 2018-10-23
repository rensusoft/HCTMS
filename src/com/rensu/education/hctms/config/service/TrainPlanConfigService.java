package com.rensu.education.hctms.config.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.config.bean.TrainPlanConfig;
import com.rensu.education.hctms.config.bean.TrainSchemeConfig;
import com.rensu.education.hctms.config.dao.TrainPlanConfigDao;
import com.rensu.education.hctms.config.dao.TrainSchemeConfigDao;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.util.Consts;


@Service("trainPlanConfigService")
public class TrainPlanConfigService extends BaseService<TrainPlanConfig> {
	
	Logger log = Logger.getLogger(TrainPlanConfigService.class);
	
	@Autowired
	protected TrainPlanConfigDao trainPlanConfigDao;
	@Autowired
	protected TrainSchemeConfigDao trainSchemeConfigDao;
	
	/**
	 * 得到所有科室轮转计划安排
	 * @return
	 *@author huq
	 * @param req 
	 *@date 2017年2月6日
	 */
	public Object findRotaryDepartment(HttpServletRequest req) {
		String id=req.getParameter("id");
		TrainPlanConfig trainPlanConfig=new TrainPlanConfig();
		if(id!=null&&!id.equals("")){
			int  tsc_id=Integer.valueOf(id);
			trainPlanConfig.setTsc_id(tsc_id);
		}
		String duration_unit=req.getParameter("durationunit");
//		if(!duration_unit.equals("-1")){
//			trainPlanConfig.setDuration_unit(duration_unit);
//			trainPlanConfigDao.updatebyTsc_id(trainPlanConfig);
//		}			
		List<TrainPlanConfig> trainListConfig=trainPlanConfigDao.findRotaryDepartment(trainPlanConfig);
		for (TrainPlanConfig trainPlanConfig2 : trainListConfig) {
			if(trainPlanConfig2.getReq_content()==""||trainPlanConfig2.getReq_content()==null){
				trainPlanConfig2.setState("X");
			}
			if(duration_unit != null && duration_unit.equals("D")) {
				trainPlanConfig2.setDuration_unit("天");
			}else if(duration_unit != null && duration_unit.equals("W")) {
				trainPlanConfig2.setDuration_unit("周");
			}else if(duration_unit != null && duration_unit.equals("M")) {
				trainPlanConfig2.setDuration_unit("月");
			}
		}
		return trainListConfig;
	}


		public TrainPlanConfig findTranPlanConfigById(HttpServletRequest req) {
			String id=req.getParameter("id");
			TrainPlanConfig trainPlanConfig=trainPlanConfigDao.selectOne(Integer.parseInt(id));
			return trainPlanConfig;
		}

		public int addConfig(HttpServletRequest req) {
			//从缓存中  拿到所有科室
//			@SuppressWarnings("unchecked")
//			List<OrgaInfo> Orgalist = (List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
			String rotate=req.getParameter("rotate");
			org.json.JSONArray jsonArray = new org.json.JSONArray(rotate);  
			String _id=req.getParameter("_id");
			TrainPlanConfig trainPlanConfig1=new TrainPlanConfig();
			trainPlanConfig1.setTsc_id(Integer.parseInt(_id));
			trainPlanConfigDao.delete(trainPlanConfig1);     //删除以前所有数据
			
			List<TrainPlanConfig> list=new ArrayList<TrainPlanConfig>();  //添加新的数据
			for (int i = 0; i < jsonArray.length(); i++) {
				TrainPlanConfig trainPlanConfig=new TrainPlanConfig();
				trainPlanConfig.setDuration(Integer.parseInt((String) jsonArray.getJSONObject(i).get("duration")));
				trainPlanConfig.setOrdinal(Integer.parseInt((String) jsonArray.getJSONObject(i).get("ordinal")));
				trainPlanConfig.setGroup_num((String) jsonArray.getJSONObject(i).get("group_num"));
				trainPlanConfig.setLimit_num(Integer.parseInt((String) jsonArray.getJSONObject(i).get("limit_num")));
//				String orga_name=(String) jsonArray.getJSONObject(i).get("orga_name");  //得到当前科室名
//				for (OrgaInfo orgaInfo : Orgalist) {
//					if(orga_name.equals(orgaInfo.getOrga_name())){
//						trainPlanConfig.setDept_id(orgaInfo.getOrga_id());
//					}
//				}
				trainPlanConfig.setDept_id(Integer.parseInt((String) jsonArray.getJSONObject(i).get("dept_id")));
				TrainSchemeConfig trainSchemeConfig=trainSchemeConfigDao.selectOne(Integer.parseInt(_id));  //得到当前专业的信息
				trainPlanConfig.setStu_type_code(trainSchemeConfig.getStu_type_code());  //适合学生的类型
				trainPlanConfig.setState("Y");
				trainPlanConfig.setTsc_id(Integer.parseInt(_id));
				String duration_unit=(String) jsonArray.getJSONObject(i).get("duration_unit");  
				if(duration_unit != null && duration_unit.equals("天")) {
					trainPlanConfig.setDuration_unit("D");
				}else if(duration_unit != null && duration_unit.equals("周")) {
					trainPlanConfig.setDuration_unit("W");
				}else if(duration_unit != null && duration_unit.equals("月")) {
					trainPlanConfig.setDuration_unit("M");
				}
//				trainPlanConfig.setDuration_unit("M");
				list.add(trainPlanConfig);
			}
			int i=trainPlanConfigDao.insertMany(list);
			return i;
		}

		
		
		
		
		
		
		
		public int findTrainConfig(HttpServletRequest req) {
			String id=req.getParameter("id");
			TrainPlanConfig trainPlanConfig=new TrainPlanConfig();
			trainPlanConfig.setTsc_id(Integer.parseInt(id));
			int i=trainPlanConfigDao.selectCount(trainPlanConfig);
			return i;
		}

		public String getReqContent(HttpServletRequest req) {
			String id=req.getParameter("id");
			TrainPlanConfig trainPlanConfig=new TrainPlanConfig();
			trainPlanConfig.setId(Integer.parseInt(id));
			int i =trainPlanConfigDao.selectCount(trainPlanConfig);
			if(i>0){
			TrainPlanConfig trainPlanConfig2=trainPlanConfigDao.selectOne(Integer.parseInt(id));
			if(trainPlanConfig2.getReq_content()==""||trainPlanConfig2.getReq_content()==null){
				return "-1";
			}else{
				return trainPlanConfig2.getReq_content();
			}
			}else{
				return "-1";
			}
			
		}

		public int updateReqContent(HttpServletRequest req) {
			String id=req.getParameter("id");
			String req_content=req.getParameter("content");
			TrainPlanConfig trainPlanConfig=new TrainPlanConfig();
			trainPlanConfig.setId(Integer.parseInt(id));
			trainPlanConfig.setReq_content(req_content);
			int i =trainPlanConfigDao.update(trainPlanConfig);
			return i;
		}

}
