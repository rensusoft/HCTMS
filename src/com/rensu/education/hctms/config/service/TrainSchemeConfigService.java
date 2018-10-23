package com.rensu.education.hctms.config.service;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.config.bean.TrainPlanConfig;
import com.rensu.education.hctms.config.bean.TrainSchemeConfig;
import com.rensu.education.hctms.config.dao.TrainPlanConfigDao;
import com.rensu.education.hctms.config.dao.TrainSchemeConfigDao;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.dao.OrgaInfoDao;
import com.rensu.education.hctms.userauth.service.StuTypeService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("trainSchemeConfigService")
public class TrainSchemeConfigService extends BaseService<TrainSchemeConfig> {
	
	Logger log = Logger.getLogger(TrainSchemeConfigService.class);
	
	@Autowired
	protected TrainSchemeConfigDao trainSchemeConfigDao;
	@Autowired
	protected StuTypeService stuTypeService;
	@Autowired
	protected TrainPlanConfigDao trainPlanConfigDao;
	@Autowired 
	protected OrgaInfoDao orgaInfoDao;
	
	public JSONObject selectPageByTiao(int pageIndex, int rows,
			TrainSchemeConfig t) {
		JSONObject jobj = new JSONObject();
		t.setState("Y");
		int totalCount = trainSchemeConfigDao.selectCount(t);
		t.setQueryCondition("order by t.id asc");
		List<TrainSchemeConfig> dataList = trainSchemeConfigDao.selectStuPage(new RowBounds((pageIndex - 1) * rows, pageIndex * rows), t);
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

	public  int addTrain(HttpServletRequest req) {
		String stuTypeCode=req.getParameter("stuTypeCode");
		String major=req.getParameter("major");
		String name=req.getParameter("name");
		String timelong=req.getParameter("timelong");
		TrainSchemeConfig trainSchemeConfig=new TrainSchemeConfig();
		trainSchemeConfig.setName(name);
		trainSchemeConfig.setTime_long(Integer.valueOf(timelong));
		trainSchemeConfig.setMajor(major);
		trainSchemeConfig.setStu_type_code(stuTypeCode);
		//得到序列
		int id=trainSchemeConfigDao.getSequence();
		trainSchemeConfig.setId(id);
		trainSchemeConfig.setAvailability("Y");
		trainSchemeConfig.setState("Y");
		int i=trainSchemeConfigDao.add(trainSchemeConfig);
		return i;
	}

	public int deleteTrainConfig(HttpServletRequest req) {
		String id=req.getParameter("id");
		TrainSchemeConfig trainSchemeConfig=new TrainSchemeConfig();
		trainSchemeConfig.setId(Integer.valueOf(id));
		int i=trainSchemeConfigDao.delete(trainSchemeConfig);
		return i;
	}

	public int updateAvail(HttpServletRequest req) {
		String id=req.getParameter("id");
		TrainSchemeConfig trainSchemeConfig=new TrainSchemeConfig();
		trainSchemeConfig.setId(Integer.valueOf(id));
		trainSchemeConfig.setAvailability("Y");
		int i =trainSchemeConfigDao.update(trainSchemeConfig);
		return i;
	}

	public int updateNoAvail(HttpServletRequest req) {
		String id=req.getParameter("id");
		TrainSchemeConfig trainSchemeConfig=new TrainSchemeConfig();
		trainSchemeConfig.setId(Integer.valueOf(id));
		trainSchemeConfig.setAvailability("X");
		int i =trainSchemeConfigDao.update(trainSchemeConfig);
		return i;
	}
	/***
	 * 同步科室
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月9日
	 */
	public  Object addTrainPlan(HttpServletRequest req) {
		String id=req.getParameter("id");
		TrainSchemeConfig trainSchemeConfig=trainSchemeConfigDao.selectOne(Integer.parseInt(id));
		String durdationUnit=req.getParameter("durationunit");
		@SuppressWarnings("unchecked")
		//得到所有的科室  从缓存中拿的
//		List<OrgaInfo> Orgalist = (List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
		OrgaInfo info = new OrgaInfo();
		info.setState("Y");
		List<OrgaInfo> Orgalist =orgaInfoDao.selectList(info);
		List<TrainPlanConfig> trainPlanConfigs=new ArrayList<TrainPlanConfig>();   //用来存放假数据的
		int i=0;
		for (int x=0;x<Orgalist.size();x++) {
			OrgaInfo orgaInfo = Orgalist.get(x);
			if(orgaInfo.getState().equals("Y")&&orgaInfo.getOrga_type()==1&&orgaInfo.getOrga_level()==1){
				TrainPlanConfig trainPlanConfig=new TrainPlanConfig();
				trainPlanConfig.setTsc_id(trainSchemeConfig.getId());
				trainPlanConfig.setStu_type_code(trainSchemeConfig.getStu_type_code());
				trainPlanConfig.setState("Y");	
				
				trainPlanConfig.setId(i);
				i++;
				if(durdationUnit.equals("D")){
					trainPlanConfig.setDuration_unit("天");
				}else if(durdationUnit.equals("W")){
					trainPlanConfig.setDuration_unit("周");
				}else{
					trainPlanConfig.setDuration_unit("月");
				}
				trainPlanConfig.setDuration(0);
				trainPlanConfig.setGroup_num("0");
				trainPlanConfig.setOrdinal(0);
				trainPlanConfig.setDept_id(orgaInfo.getOrga_id());
				trainPlanConfig.setOrga_name(orgaInfo.getOrga_name());
				trainPlanConfig.setLimit_num(orgaInfo.getLimit_num());
				trainPlanConfig.setReq_content(null);
				trainPlanConfig.setState("X");
				trainPlanConfigs.add(trainPlanConfig);
			}
		}
			
		return trainPlanConfigs;
	}
}
