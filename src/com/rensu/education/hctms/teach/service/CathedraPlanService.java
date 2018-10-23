package com.rensu.education.hctms.teach.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.teach.bean.CathedraCondition;
import com.rensu.education.hctms.teach.bean.CathedraDetail;
import com.rensu.education.hctms.teach.bean.CathedraPlan;
import com.rensu.education.hctms.teach.bean.CathedraPlanCount;
import com.rensu.education.hctms.teach.dao.CathedraPlanDao;
import com.rensu.education.hctms.userauth.bean.StuClass;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("cathedraPlanService")
public class CathedraPlanService extends BaseService<CathedraPlan> {
	
	Logger log = Logger.getLogger(CathedraPlanService.class);
	
	@Autowired
	protected CathedraPlanDao cathedraPlanDao;
	
	/**
	 * 查询讲座编排
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月6日
	 */
	public List<CathedraPlan> selectCathedraPlan(CathedraPlan cathedraPlan){
		return cathedraPlanDao.selectCathedraPlan(cathedraPlan);
	}
	//加载届次
	public List<StuClass> stuClassCheckbox(){
		return cathedraPlanDao.stuClassCheckbox();
	};
	//加载学生类型
	public List<StuType> stuTypeCheckbox(){
		return cathedraPlanDao.stuTypeCheckbox();
	};
	//加载条数
	public Integer countCathedraPlan(String dateTime){
		return cathedraPlanDao.countCathedraPlan(dateTime);
	};
	//加载条数对应的date
	public Integer countCathedraPlanDate(String dateTime){
		return cathedraPlanDao.countCathedraPlanDate(dateTime);
	};
	//查询一个讲座安排
	public String selectcathedraOnePlan(Integer id){
		return cathedraPlanDao.selectcathedraOnePlan(id);
	};
	//查询左侧日期导航栏的日期
	public List<CathedraPlan> getCathDate(){
		return cathedraPlanDao.getCathDate();
	};

	public List<CathedraPlan> selectCathedraNotice(HttpServletRequest req){
		//获取页面的参数
		String timeStart =req.getParameter("timeStart");
		String timeEnd =req.getParameter("timeEnd");
		String status = req.getParameter("status");
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		CathedraDetail cathedraDetail = cathedraPlanDao.getClassAndTypeByAuthId(auth_id); //获取届次和类型
		CathedraPlan cathedraPlan = new CathedraPlan();
		cathedraPlan.setTimeStart(Timestamp.valueOf(timeStart));
		cathedraPlan.setTimeEnd(Timestamp.valueOf(timeEnd));
		cathedraPlan.setStatus(status);
		
		List<CathedraPlan> listCathedraPlanGet = cathedraPlanDao.selectCathedraNotice(cathedraPlan);
		CathedraCondition cathedraCondition = new CathedraCondition();
		List<CathedraPlan> listCathedraPlan = new ArrayList<CathedraPlan>();
		for(CathedraPlan cathPlan : listCathedraPlanGet){
			int condition_type = 1;//条件类型
			cathedraCondition.setCondition_type(condition_type);
			cathedraCondition.setCp_id(cathPlan.getId());
			CathedraCondition cathedraConditionGet = cathedraPlanDao.selectCathedraCondition(cathedraCondition);
			String[] stuClass = cathedraConditionGet.getCondition_value().split(",");
			for(int i = 0; i < stuClass.length; i++){
				if(cathedraDetail != null && cathedraDetail.getStu_class() != null && stuClass[i].equals(cathedraDetail.getStu_class())){  //判断届次
					condition_type = 2;
					cathedraCondition.setCondition_type(condition_type);
					cathedraConditionGet = cathedraPlanDao.selectCathedraCondition(cathedraCondition);
					String[] stuType = cathedraConditionGet.getCondition_value().split(",");
					for(int j = 0; j < stuType.length; j++){
						if(cathedraDetail.getType_name() != null && stuType[j].equals(cathedraDetail.getType_name())){  //判断类型
							listCathedraPlan.add(cathPlan);
						}
					}
				}
			}
		}
		return listCathedraPlan;
	}
	
	@ResponseBody
	@RequestMapping(value="/countCathedraNotice")
	public Object countCathedraNotice(HttpServletRequest req){
		String date_time_str = req.getParameter("date");
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		CathedraDetail cathedraDetail = cathedraPlanDao.getClassAndTypeByAuthId(auth_id); //获取届次和学生类型
		CathedraPlan cathedraPlan = new CathedraPlan();
		//Integer hos_id = loginBean.getvUserDetailInfo().getHos_id();
		//cathedraPlan.setHos_id(hos_id);
		List<CathedraPlanCount> list = new ArrayList<CathedraPlanCount>();
		CathedraCondition cathedraCondition = new CathedraCondition();
		String[] date_times = date_time_str.split(" ");
		for(int i = 0; i < date_times.length; i++){
			int count = 0;
			CathedraPlanCount cathedraPlanCount = new CathedraPlanCount();//new 放里面和外面有区别
			cathedraPlan.setCath_date(Integer.parseInt(date_times[i].replace("-", "")));
			List<CathedraPlan> listCathedraPlanGet = cathedraPlanDao.countCathedraNotice(cathedraPlan);//根据月份查询讲座
			for(CathedraPlan cathPlan : listCathedraPlanGet){
				int condition_type = 1;//条件类型
				cathedraCondition.setCondition_type(condition_type);
				cathedraCondition.setCp_id(cathPlan.getId());
				CathedraCondition cathedraConditionGet = cathedraPlanDao.selectCathedraCondition(cathedraCondition);//根据月份查询讲座条件
				String[] stuClass = cathedraConditionGet.getCondition_value().split(",");
				for(int j = 0; j < stuClass.length; j++){
					if(cathedraDetail != null && cathedraDetail.getStu_class() != null && stuClass[j].equals(cathedraDetail.getStu_class())){  //判断届次
						condition_type = 2;
						cathedraCondition.setCondition_type(condition_type);
						cathedraConditionGet = cathedraPlanDao.selectCathedraCondition(cathedraCondition);
						String[] stuType = cathedraConditionGet.getCondition_value().split(",");
						for(int k = 0; k < stuType.length; k++){
							if(cathedraDetail.getType_name() != null && stuType[k].equals(cathedraDetail.getType_name())){  //判断类型
								count++;
							}
						}
					}
				}
			}
			Integer cath_date = Integer.parseInt((date_times[i].replace("-","")));
			cathedraPlanCount.setCath_date(cath_date);
			cathedraPlanCount.setCount(count);
		    list.add(cathedraPlanCount);
		}
		return StringUtil.returns(true, list);
	}
	public Integer countCathedraPlan(CathedraPlan cathedraPlan) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
