package com.rensu.education.hctms.basicdata.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.MarksheetDetail;
import com.rensu.education.hctms.basicdata.bean.MarksheetMain;
import com.rensu.education.hctms.basicdata.bean.MarksheetSub;
import com.rensu.education.hctms.basicdata.dao.FormInfoDao;
import com.rensu.education.hctms.basicdata.dao.MarksheetDetailDao;
import com.rensu.education.hctms.basicdata.dao.MarksheetMainDao;
import com.rensu.education.hctms.basicdata.dao.MarksheetSubDao;


@Service("marksheetMainService")
public class MarksheetMainService extends BaseService<MarksheetMain> {
	
	Logger log = Logger.getLogger(MarksheetMainService.class);
	
	@Autowired
	protected MarksheetMainDao marksheetMainDao;
	@Autowired
	protected FormInfoDao formInfoDao;
	@Autowired
	protected MarksheetSubDao marksheetSubDao;
	@Autowired
	protected MarksheetDetailDao marksheetDetailDao;
	
	@SuppressWarnings("unchecked")
	public boolean addMarksheet(HttpServletRequest req){
		boolean flag = false;
		String data = req.getParameter("data");
		if(data!=null&&!data.equals("")){
			JSONObject jsonObject = JSONObject.fromObject(data);
			FormInfo form = (FormInfo) JSONObject.toBean(jsonObject, FormInfo.class);
			int num = marksheetMainDao.getSequence();
			MarksheetMain main = new MarksheetMain();
			main.setId(num);
			if(form.getName()!=null&&!form.getName().equals(""))
				main.setMm_name(form.getName().trim());
			if(form.getTotal_sconum()!=null&&!form.getTotal_sconum().equals(""))
				main.setTotal_sconum(Integer.parseInt(form.getTotal_sconum()));
			main.setValidity(form.getAvailability());
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			main.setCreate_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
			main.setState(form.getState());
			int mainresult = marksheetMainDao.add(main);	//新增MARKSHEET_MAIN
			form.setId(formInfoDao.getSequence());
			form.setRelated_id(num);
			formInfoDao.add(form);	//新增FORM_INFO
			JSONArray jsonArray = JSONArray.fromObject(form.getSubs());
			List<MarksheetSub> subList = (List<MarksheetSub>) JSONArray.toCollection(jsonArray, MarksheetSub.class);
			List<MarksheetSub> list = new ArrayList<MarksheetSub>();
			int subresult = 0;
			int detailresult=0;
			if(subList!=null&&subList.size()>0){
				int id=0;
				int index = 0;
				for(int i=0;i<subList.size();i++){
					MarksheetSub sub = subList.get(i);
					if(sub!=null){
						int ms_id = marksheetSubDao.getSequence();
						if(sub.getType_code()!=null&&sub.getType_code()==0){
							index = sub.getId();
							id = ms_id;
							sub.setId(ms_id);
							sub.setMm_id(num);
							list.add(sub);
						}
						if(sub.getType_code()!=null&&sub.getType_code()==1){
							if(sub.getMs_id()==index){
								sub.setId(ms_id);
								sub.setMm_id(num);
								sub.setMs_id(id);
								list.add(sub);
								JSONArray array = JSONArray.fromObject(sub.getDetails());
								List<MarksheetDetail> detailList = (List<MarksheetDetail>) JSONArray.toCollection(array, MarksheetDetail.class);
								List<MarksheetDetail> lists = new ArrayList<MarksheetDetail>();
								if(detailList!=null&&detailList.size()>0){
									for(int j=0;j<detailList.size();j++){
										MarksheetDetail detail = detailList.get(j);
										detail.setMs_id(ms_id);
										lists.add(detail);
									}
								}
								if(lists!=null&&lists.size()>0){
									detailresult = marksheetDetailDao.insertWithList(lists);	//新增MARKSHEET_DETAIL
								}
							}
						}
					}
				}
			}
			if(list!=null&&list.size()>0){
				subresult = marksheetSubDao.insertWithList(list);	//新增MARKSHEET_SUB
			}
			if(mainresult>0&&subresult>0&&detailresult>0){
				flag = true;
			}
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	public boolean updateMarksheet(HttpServletRequest req){
		boolean flag = false;
		String data = req.getParameter("data");
		if(data!=null&&!data.equals("")){
			JSONObject jsonObject = JSONObject.fromObject(data);
			FormInfo form = (FormInfo) JSONObject.toBean(jsonObject, FormInfo.class);
			int num = form.getRelated_id();
			if(num >0){
				MarksheetSub marksheetSub = new MarksheetSub();
				marksheetSub.setState("X");
				marksheetSub.setMm_id(num);
				marksheetSubDao.updateList(marksheetSub);
				MarksheetMain main = new MarksheetMain();
				main.setId(num);
				if(form.getName()!=null&&!form.getName().equals(""))
					main.setMm_name(form.getName().trim());
				if(form.getTotal_sconum()!=null&&!form.getTotal_sconum().equals(""))
					main.setTotal_sconum(Integer.parseInt(form.getTotal_sconum()));
				int mainresult = marksheetMainDao.update(main);	// 修改MARKSHEET_MAIN
				JSONArray jsonArray = JSONArray.fromObject(form.getSubs());
				List<MarksheetSub> subList = (List<MarksheetSub>) JSONArray.toCollection(jsonArray, MarksheetSub.class);
				List<MarksheetSub> list = new ArrayList<MarksheetSub>();
				int subresult = 0;
				int detailresult=0;
				if(subList!=null&&subList.size()>0){
					int id=0;
					int index = 0;
					for(int i=0;i<subList.size();i++){
						MarksheetSub sub = subList.get(i);
						if(sub!=null){
							int ms_id = marksheetSubDao.getSequence();
							if(sub.getType_code()!=null&&sub.getType_code()==0){
								index = sub.getId();
								id = ms_id;
								sub.setId(ms_id);
								sub.setMm_id(num);
								list.add(sub);
							}
							if(sub.getType_code()!=null&&sub.getType_code()==1){
								if(sub.getMs_id()==index){
									sub.setId(ms_id);
									sub.setMm_id(num);
									sub.setMs_id(id);
									list.add(sub);
									JSONArray array = JSONArray.fromObject(sub.getDetails());
									List<MarksheetDetail> detailList = (List<MarksheetDetail>) JSONArray.toCollection(array, MarksheetDetail.class);
									List<MarksheetDetail> lists = new ArrayList<MarksheetDetail>();
									if(detailList!=null&&detailList.size()>0){
										for(int j=0;j<detailList.size();j++){
											MarksheetDetail detail = detailList.get(j);
											detail.setMs_id(ms_id);
											lists.add(detail);
										}
									}
									if(lists!=null&&lists.size()>0){
										detailresult = marksheetDetailDao.insertWithList(lists);	//新增MARKSHEET_DETAIL
									}
								}
							}
						}
					}
				}
				if(list!=null&&list.size()>0){
					subresult = marksheetSubDao.insertWithList(list);	//新增MARKSHEET_SUB
				}
				if(mainresult>0&&subresult>0&&detailresult>0){
					flag = true;
				}
			}
		}
		return flag;
	}
}
