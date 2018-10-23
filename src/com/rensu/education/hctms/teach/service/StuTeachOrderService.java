package com.rensu.education.hctms.teach.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.TrainTeachOrderDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.teach.bean.StuTeachOrder;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuTeachOrderDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.dao.StuTypeDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("stuTeachOrderService")
public class StuTeachOrderService extends BaseService<StuTeachOrder> {
	
	Logger log = Logger.getLogger(StuTeachOrderService.class);
	
	@Autowired
	protected StuTeachOrderDao stuTeachOrderDao;
	@Autowired
	protected TrainPlanDao trainPlanDao;
	@Autowired
	protected StuTypeDao stuTypeDao;
	@Autowired
	protected TrainTeachOrderDao trainTeachOrderDao;
	
	
	/***
	 * 查询轮转过程记录详情
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月22日
	 */
	public Object selectRotateProcessRight(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		//从页面获取数据
		String id = req.getParameter("id");
		String train_dept_id = req.getParameter("train_dept_id");
		String type = req.getParameter("type");
		String stu_auth = req.getParameter("stu_auth");
		//从缓存中得到stu_auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		//加载trainPlan的数据
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		TrainPlan trainPlan=null;
		List<TrainTeachOrder> trainTeachOrderList=new ArrayList<TrainTeachOrder>();
		if(id !=""&&!id.equals("null")&&id!=null){
			 trainPlan=trainPlanDao.selectOneById(Integer.parseInt(id));
		}
		if(trainPlan!=null&&trainPlan.getTpc_id()!=null){
		TrainTeachOrder trainTeachOrder= new TrainTeachOrder();
		trainTeachOrder.setTpc_id(trainPlan.getTpc_id());
		trainTeachOrder.setState("Y");
		trainTeachOrderList=trainTeachOrderDao.selectList(trainTeachOrder);
		}
		for(int i=0;i<trainTeachOrderList.size();i++){
			int tto=trainTeachOrderList.get(i).getId();
			//根据 stu_auth_id 和 科室ID加载 train_teach_order的数据
			StuTeachOrder stuTeachOrder = new StuTeachOrder();
			if(!train_dept_id.equals("")&&train_dept_id!=null&&!train_dept_id.equals("null")){
				stuTeachOrder.setDept_id(Integer.parseInt(train_dept_id));
			}
			if(stu_auth!=null&&!stu_auth.equals("")&&!stu_auth.equals("null")){
				stuTeachOrder.setStu_auth_id(Integer.parseInt(stu_auth));
			}else{
				stuTeachOrder.setStu_auth_id(stu_auth_id);
			}
			stuTeachOrder.setTto_id(tto);
			List<StuTeachOrder>  stuTeachOrderList=  stuTeachOrderDao.selectList(stuTeachOrder);
		    if(stuTeachOrderList.size()>0&&stuTeachOrderList.get(0).getFinnish_num()!=null){
				   trainTeachOrderList.get(i).setFinnish_num(stuTeachOrderList.get(0).getFinnish_num());
	    	}else{
	    		 trainTeachOrderList.get(i).setFinnish_num(0);
	    	}
		}
		List<TrainTeachOrder> supMenuList = new ArrayList<TrainTeachOrder>();
		Map<Integer, List<TrainTeachOrder>> menuMap = new HashMap<Integer, List<TrainTeachOrder>>();
		//遍历出所有一级菜单，并将二级菜单放到map中。计算完成率
				for (TrainTeachOrder _menu : trainTeachOrderList) {
					//计算完成率
					if(_menu.getOrder_num()!=null&&_menu.getOrder_num()!=0){
						if(_menu.getFinnish_num()==null){
							_menu.setFinnish_num(0);
						}
						float completion_rate=(float)_menu.getFinnish_num()/(float)_menu.getOrder_num();
						if((int)(completion_rate*100)>100){
							_menu.setCompletion_rate(100);
						}else{
							_menu.setCompletion_rate((int)(completion_rate*100));
						}
					}else{
						_menu.setCompletion_rate(0);
					}
					//遍历出所有一级菜单，并将二级菜单放到map中。
					if (_menu.getType_id() == 1||_menu.getType_id() == 0) { //一级菜单
						supMenuList.add(_menu);
					} else if (_menu.getType_id() == 2) { //二级菜单
						Integer _sup_id = _menu.getSup_id();
						List<TrainTeachOrder> _menuList = menuMap.get(_sup_id);
						if (_menuList == null) {
							_menuList = new ArrayList<TrainTeachOrder>();
							menuMap.put(_sup_id, _menuList);
						}
						_menuList.add(_menu);
					}
				}
				//将一级菜单和二级菜单整合到一起
				for (TrainTeachOrder _menu : supMenuList) {
					List<TrainTeachOrder> _subList = menuMap.get(_menu.getId());
					if (_subList != null && _subList.size() > 0) {
						_menu.setSubMenuList(_subList);
					}
				}
		if(type!=null&&type!=""){
			if(type.equals("onlyFit"))
		    mv.setViewName("web/userauth/outlineConformity"); //大纲吻合度页面
		}else{
			mv.setViewName("web/teach/rotateProcessRight");
		}
		mv.addObject("trainPlan",trainPlan);
		mv.addObject("supMenuList",supMenuList);
		return mv;
	}

	/***
	 * 查询轮转信息规范
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月22日
	 */
	public Object selectReq_content(HttpServletRequest req) {
		String tpc_id = req.getParameter("tpc_id");
		StuTeachOrder stuTeachOrder=null;
		if(StringUtil.isNotEmpty(tpc_id)){
			stuTeachOrder = stuTeachOrderDao.selectReq_content(Integer.parseInt(tpc_id));
		}
		return stuTeachOrder;
	}
	
}
