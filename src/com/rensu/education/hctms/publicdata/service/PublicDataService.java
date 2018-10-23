package com.rensu.education.hctms.publicdata.service;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.publicdata.bean.PublicData;
import com.rensu.education.hctms.publicdata.dao.PublicDataDao;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("publicDataService")
public class PublicDataService extends BaseService<PublicData> {
	
	Logger log = Logger.getLogger(PublicDataService.class);
	
	@Autowired
	protected PublicDataDao publicDataDao;

	/***
	 * 新增或修改共享资料（id为空则新增）
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月6日
	 */
	public Object insterPublicData(HttpServletRequest req) {
		String type_code = req.getParameter("type_code");
		String id = req.getParameter("id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		String publisher_auth_id  = loginBean.getvUserDetailInfo().getAuth_id().toString();
		PublicData publicData = new PublicData();
		publicData.setType_code(type_code);
		publicData.setTitle(title);
		publicData.setContent(content);
		int num = 0;
		if(("").equals(id)||null==id){
			publicData.setId(publicDataDao.getSequence());
			publicData.setPublisher_auth_id(publisher_auth_id);
			publicData.setPublish_time(new java.sql.Timestamp(new java.util.Date().getTime()));
			publicData.setState("Y");
			num =publicDataDao.add(publicData);
		}else{
			publicData.setId(Integer.parseInt(id));
			num =publicDataDao.update(publicData);
		}
		if(num>0){
			return StringUtil.returns(true, "操作成功");
		}else{
			return StringUtil.returns(true, "操作异常");
		}
	}
     /***
      * 
      * @param pageIndex
      * @param rows
      * @param publicData
      * @return
      * @author hezx
      * @date 2017年1月9日
      */
	public JSONObject selectPage(int pageIndex, int rows,PublicData publicData) {
		JSONObject jobj = new JSONObject();
		List<PublicData> dataList = publicDataDao.selectPage(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				publicData);
		if (dataList != null && dataList.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < dataList.size(); i++) {
				if (dataList.get(i).getPublish_time()!= null) {
					dataList.get(i).setTime(
							sdf.format(dataList.get(i).getPublish_time()));
				}
			}
		}
		
		int totalCount = publicDataDao.selectCount(publicData);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}
	
	/***
	 *根据ID删除共享资料
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月9日
	 */
	public Object delPublicDate(HttpServletRequest req) {
		String id = req.getParameter("id");
		String state = req.getParameter("state");
		PublicData publicData = new PublicData();
		publicData.setId(Integer.parseInt(id));
		publicData.setState(state);
		int num =publicDataDao.update(publicData);
		if(num>0){
			return StringUtil.returns(true, "操作成功");
		}else{
			return StringUtil.returns(true, "操作异常");
		}
	}
	
	/**
	 * 根据ID查看资料详情
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月9日
	 */
	public Object selectOneById(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		String id = req.getParameter("id");
		PublicData publicData =publicDataDao.selectOneById(Integer.parseInt(id));
		mv.setViewName("web/publicdata/readPublicData");
		mv.addObject("publicData", publicData);
		return mv;
	}
	
	public Object selectPageOfOthers(int pageIndex, int rows, PublicData publicData) {
		  JSONObject jobj= new JSONObject();
		  List<PublicData> dataList=publicDataDao.selectPageOfOthers(
					new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
					publicData);
		  
		  if(dataList!=null&&dataList.size()>0){
			  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			  for(int i=0;i<dataList.size();i++){
				  if(dataList.get(i).getPublish_time()!=null){
					  dataList.get(i).setTime(sdf.format(dataList.get(i).getPublish_time()));
				  }
			  }
		  }
		int totalCount=publicDataDao.selectCountOfOthers(publicData);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}
	
	}
	

