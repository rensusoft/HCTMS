package com.rensu.education.hctms.teach.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.TrainTeachOrderDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.teach.bean.StuActiveData;
import com.rensu.education.hctms.teach.bean.StuTeachOrder;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuActiveDataDao;
import com.rensu.education.hctms.teach.dao.StuTeachOrderDao;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("stuActiveDataService")
public class StuActiveDataService extends BaseService<StuActiveData> {
	
	Logger log = Logger.getLogger(StuActiveDataService.class);
	
	@Autowired
	protected StuActiveDataDao stuActiveDataDao;
	@Autowired
	protected MessagePublishDao messagePublishDao;
	@Autowired
	protected MessageReceiveDao messageReceiveDao;
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	@Autowired
	protected StuTeachOrderDao stuTeachOrderDao;
	@Autowired
	protected TrainTeachOrderDao trainTeachOrderDao;

	public JSONObject selectPageEntering(int pageIndex, int rows, StuActiveData t, HttpServletRequest req) {

		String examineState=req.getParameter("examineState");
		String startTime=req.getParameter("startTime");
		String endTime=req.getParameter("endTime");
		String indistinct=req.getParameter("text");
		
		if(StringUtil.isNotEmpty(startTime)){
			t.setStartTime(startTime+"00:00:00");
		}
		if(StringUtil.isNotEmpty(endTime)){
			t.setEndTime(endTime+"23:59:59");
		}
		if(StringUtil.isEmpty(indistinct)==false){
			t.setIndistinct(indistinct);
		}
		
		if(Integer.parseInt(examineState)!=-2){
			t.setExamine_state(Integer.parseInt(examineState));
		}
		
		
		//从缓存中得到stu_auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer stu_dept_id=loginBean.getvUserDetailInfo().getOrga_id();
		t.setDept_id(stu_dept_id);
		t.setStu_auth_id(stu_auth_id);
		t.setState("Y");
		JSONObject jobj = new JSONObject();
		int totalCount = stuActiveDataDao.selectCount(t);
		t.setOrderCondition("t.create_time desc");
		List<StuActiveData> dataList = stuActiveDataDao.selectPageEntering(new RowBounds((pageIndex - 1) * rows, pageIndex * rows), t);
		
		for (int i = 0; i < dataList.size(); i++) {
			if(dataList.get(i).getmOrder_name()==""||dataList.get(i).getmOrder_name()==null){				
			}else{
				dataList.get(i).setOrder_name(dataList.get(i).getmOrder_name()+"   ["+dataList.get(i).getOrder_name()+"]");
			}
		}
		for (int i = 0; i < dataList.size(); i++) {
			if(dataList.get(i).getExamineState() != null && dataList.get(i).getExamineState().equals("审核未通过")||dataList.get(i).getExamineState().equals("审核通过")){
				dataList.get(i).setExamineState("<a style='cursor:pointer' onclick='viewReason(" + dataList.get(i).getId() + ");'>" + dataList.get(i).getExamineState() + "</a>");
			}
		}
		
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

	public  int saveStuActiveData(HttpServletRequest req) {
		String content=req.getParameter("content");
		String id=req.getParameter("id");      
		String sad_id=req.getParameter("sad_id");      
		String action=req.getParameter("action");      
		//从缓存中得到stu_auth_id,和科室
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id = loginBean.getvUserDetailInfo().getAuth_id();  //学生的id
		Integer stu_dept_id=loginBean.getvUserDetailInfo().getOrga_id();   //学生的科室
		int i = 0;
		if (action != null && action.equals(Consts.OPT_ADD)) {
			StuActiveData stuActiveData=new StuActiveData();
			stuActiveData.setContent(content);
			stuActiveData.setSto_id(Integer.parseInt(id));
			stuActiveData.setStu_auth_id(stu_auth_id);
			stuActiveData.setDept_id(stu_dept_id);
			stuActiveData.setExamine_state(0);
			stuActiveData.setState("Y");
			Timestamp d = new Timestamp(System.currentTimeMillis());  //获取当时系统时间轴
			stuActiveData.setCreate_time(d);
			stuActiveData.setId(stuActiveDataDao.getSequence());
			i=stuActiveDataDao.add(stuActiveData);
			//
			StuTeachOrder stuTeachOrder = new StuTeachOrder();
			stuTeachOrder.setTto_id(Integer.parseInt(id));
			stuTeachOrder.setStu_auth_id(stu_auth_id);
			stuTeachOrder.setDept_id(stu_dept_id);
			stuTeachOrder.setState("Y");
			List<StuTeachOrder> stuTeachOrderList = stuTeachOrderDao.selectList(stuTeachOrder);
			if (stuTeachOrderList != null && !stuTeachOrderList.isEmpty()) {
				
			}else{
				TrainTeachOrder trainTeachOrder = trainTeachOrderDao.selectOne(Integer.parseInt(id));
				if (trainTeachOrder.getSup_id() != null) {
					TrainTeachOrder trainTeachOrderSup = trainTeachOrderDao.selectOne(trainTeachOrder.getSup_id());
					//父项，判断父项是否已存在！！！
					StuTeachOrder stuTeachOrderSup = new StuTeachOrder();
					stuTeachOrderSup.setTto_id(trainTeachOrderSup.getId());
					stuTeachOrderSup.setStu_auth_id(stu_auth_id);
					stuTeachOrderSup.setDept_id(stu_dept_id);
					stuTeachOrderSup.setState("Y");
					List<StuTeachOrder> stuTeachOrderSupList = stuTeachOrderDao.selectList(stuTeachOrderSup);
					if (stuTeachOrderSupList != null && !stuTeachOrderSupList.isEmpty()) {
						//子项
						StuTeachOrder sto = new StuTeachOrder();
						sto.setId(stuTeachOrderDao.getSequence());
						sto.setTto_id(Integer.parseInt(id));
						sto.setDept_id(stu_dept_id);
						sto.setStu_auth_id(stu_auth_id);
						sto.setOrder_name(trainTeachOrder.getOrder_name());
						sto.setType_id(trainTeachOrder.getType_id());
						sto.setSup_id(stuTeachOrderSupList.get(0).getId());
						sto.setSort_num(trainTeachOrder.getSort_num());
						sto.setOrder_num(trainTeachOrder.getOrder_num());
						sto.setOrder_num_unit(trainTeachOrder.getOrder_num_unit());
						sto.setState("Y");
						sto.setData_type_id(trainTeachOrder.getData_type_id());
						stuTeachOrderDao.add(sto);
					}else{
						StuTeachOrder stoSup = new StuTeachOrder();
						Integer sup_id = stuTeachOrderDao.getSequence();
						stoSup.setId(sup_id);
						stoSup.setTto_id(trainTeachOrderSup.getId());
						stoSup.setDept_id(stu_dept_id);
						stoSup.setStu_auth_id(stu_auth_id);
						stoSup.setOrder_name(trainTeachOrderSup.getOrder_name());
						stoSup.setType_id(trainTeachOrderSup.getType_id());
						stoSup.setSup_id(trainTeachOrderSup.getSup_id());
						stoSup.setSort_num(trainTeachOrderSup.getSort_num());
						stoSup.setOrder_num(trainTeachOrderSup.getOrder_num());
						stoSup.setOrder_num_unit(trainTeachOrderSup.getOrder_num_unit());
						stoSup.setState("Y");
						stoSup.setData_type_id(trainTeachOrderSup.getData_type_id());
						stuTeachOrderDao.add(stoSup);
						//子项
						StuTeachOrder sto = new StuTeachOrder();
						sto.setId(stuTeachOrderDao.getSequence());
						sto.setTto_id(Integer.parseInt(id));
						sto.setDept_id(stu_dept_id);
						sto.setStu_auth_id(stu_auth_id);
						sto.setOrder_name(trainTeachOrder.getOrder_name());
						sto.setType_id(trainTeachOrder.getType_id());
						sto.setSup_id(sup_id);
						sto.setSort_num(trainTeachOrder.getSort_num());
						sto.setOrder_num(trainTeachOrder.getOrder_num());
						sto.setOrder_num_unit(trainTeachOrder.getOrder_num_unit());
						sto.setState("Y");
						sto.setData_type_id(trainTeachOrder.getData_type_id());
						stuTeachOrderDao.add(sto);
					}
				}else{
					StuTeachOrder sto = new StuTeachOrder();
					sto.setId(stuTeachOrderDao.getSequence());
					sto.setTto_id(Integer.parseInt(id));
					sto.setDept_id(stu_dept_id);
					sto.setStu_auth_id(stu_auth_id);
					sto.setOrder_name(trainTeachOrder.getOrder_name());
					sto.setType_id(trainTeachOrder.getType_id());
					sto.setSup_id(trainTeachOrder.getSup_id());
					sto.setSort_num(trainTeachOrder.getSort_num());
					sto.setOrder_num(trainTeachOrder.getOrder_num());
					sto.setOrder_num_unit(trainTeachOrder.getOrder_num_unit());
					sto.setState("Y");
					sto.setData_type_id(trainTeachOrder.getData_type_id());
					stuTeachOrderDao.add(sto);
				}
			}
		}else if (action != null && action.equals(Consts.OPT_EDIT)) {
			StuActiveData stuActiveData=new StuActiveData();
			stuActiveData.setId(Integer.parseInt(sad_id));
			stuActiveData.setContent(content);
			i=stuActiveDataDao.update(stuActiveData);
		}
		return i;
	}

	public List<StuActiveData> selectShenHeCountByOA(StuActiveData stuActiveData) {
			return stuActiveDataDao.selectShenHeCountByOA(stuActiveData);
	}
	
	public int auditThrough(HttpServletRequest req) {
		String content = req.getParameter("content");
		Integer id=Integer.parseInt(req.getParameter("id")); //要审核的id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		int orgaId=loginBean.getvUserDetailInfo().getOrga_id();   //当前科室id
		if (id != null && content != null && !content.equals("")) {
			//将数据审核的意见写入到STU_ACTIVE_DATA表中
			StuActiveData stuActiveD = new StuActiveData();
			stuActiveD.setId(id);
			stuActiveD.setExamine_text(content);
			stuActiveDataDao.update(stuActiveD);
		}
		String  orgaName="";
		//从缓存中拿到当前科室名字；
		@SuppressWarnings("unchecked")
		List<OrgaInfo> Orgalist = (List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
		for (int i = 0; i < Orgalist.size(); i++) {
			if(Orgalist.get(i).getOrga_id()==orgaId){
				orgaName=Orgalist.get(i).getOrga_name();
			}
		}
		int authId=loginBean.getvUserDetailInfo().getAuth_id();   //审核人的id
		UserAuthority userAuthority2=userAuthorityDao.selectOneNameByAuth(authId);
		StuActiveData stuActiveData=new StuActiveData();
		stuActiveData.setId(id);   //审核  id
		List<StuActiveData> lists=stuActiveDataDao.getStuInput(stuActiveData);  //得到审核 的数据
		String orderName ="";
		for (int i = 0; i < lists.size(); i++) {
			if(lists.get(i).getmOrder_name()!=null&&lists.get(i).getmOrder_name()!=""){
				orderName=lists.get(i).getmOrder_name()+"["+lists.get(i).getOrder_name()+"]";
			}else{
				orderName=lists.get(i).getOrder_name();
			}
		}
		
		@SuppressWarnings("unused")
		StuActiveData stuActiveData2=stuActiveDataDao.selectOne(id);
		Integer stuAuthId=stuActiveData2.getStu_auth_id(); //得到学生id  给学生发系统消息
		// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
		MessagePublish mp = new MessagePublish();
		int mpId = messagePublishDao.getSequence();
		mp.setId(mpId);
		mp.setTitle(userAuthority2.getName()  + "【" + orgaName + "】" +  "通过了你的录入信息！");
		mp.setContent("您的【"+orderName+"】录入信息已通过！");
		// 获取当前时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp send_time = Timestamp.valueOf(time);
		mp.setSend_time(send_time);
		mp.setSender_auth_id(authId);
		mp.setState("Y");
		mp.setType_id(2);
		messagePublishDao.add(mp);                     //发布表	
		MessageReceive mr = new MessageReceive();
		mr.setReceiver_auth_id(stuAuthId);
		mr.setId(messageReceiveDao.getSequence());
		mr.setProgress_state(0);
		mr.setState("Y");
		mr.setMp_id(mpId);
		messageReceiveDao.add(mr);            //接收表
		
		stuActiveData.setExamine_authid(authId);
		stuActiveData.setExamine_state(1);
		
		int i = stuActiveDataDao.update(stuActiveData);
		//
		StuActiveData  stuActiveData1=new StuActiveData();
		stuActiveData1.setDept_id(orgaId);
		stuActiveData1.setStu_auth_id(stuAuthId);
		//查询此同学此刻科室下，所有审核通过的数据
		List<StuActiveData> listSAD=stuActiveDataDao.selectShenHeCountByOA(stuActiveData1);   
		
		//根据数据改变当前学生大纲表中的完成数量
		for (int j = 0; j < listSAD.size(); j++) {
			StuTeachOrder stuTeachOrder=new StuTeachOrder();
			stuTeachOrder.setTto_id(listSAD.get(j).getSto_id());  //获取 大纲表中的id
			stuTeachOrder.setStu_auth_id(stuAuthId);
			stuTeachOrder.setDept_id(orgaId);
			stuTeachOrder.setState("Y");
			stuTeachOrder.setFinnish_num(listSAD.get(j).getCount());   //完成的个数
			stuTeachOrderDao.updateFinnish_num(stuTeachOrder);  //改变总数
		}
		return i;
	}

	public int auditNotApproved(HttpServletRequest req) {
		String content = req.getParameter("content");
		Integer id=Integer.parseInt(req.getParameter("id")); 
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		int orgaId=loginBean.getvUserDetailInfo().getOrga_id();   //当前科室id
		//将数据审核的意见写入到STU_ACTIVE_DATA表中
				StuActiveData stuActiveD = new StuActiveData();
				stuActiveD.setId(id);
				stuActiveD.setExamine_text(content);
				stuActiveDataDao.update(stuActiveD);
		String  orgaName="";
		//从缓存中拿到当前科室名字；
		@SuppressWarnings("unchecked")
		List<OrgaInfo> Orgalist = (List<OrgaInfo>) req.getSession().getServletContext().getAttribute(Consts.SESSION_ORGA_INFO);
		for (int i = 0; i < Orgalist.size(); i++) {
			if(Orgalist.get(i).getOrga_id()==orgaId){
				orgaName=Orgalist.get(i).getOrga_name();
			}
		}
		int authId=loginBean.getvUserDetailInfo().getAuth_id();   //审核人的id

		UserAuthority userAuthority2=userAuthorityDao.selectOneNameByAuth(authId);
		StuActiveData stuActiveData=new StuActiveData();		
		stuActiveData.setId(id);  //审核id
		
		List<StuActiveData> lists=stuActiveDataDao.getStuInput(stuActiveData);  //得到审核 的数据
		String orderName ="";
		for (int i = 0; i < lists.size(); i++) {
			if(lists.get(i).getmOrder_name()!=null&&lists.get(i).getmOrder_name()!=""){
				orderName=lists.get(i).getmOrder_name()+"["+lists.get(i).getOrder_name()+"]";
			}else{
				orderName=lists.get(i).getOrder_name();
			}
		}
		
		@SuppressWarnings("unused")
		StuActiveData stuActiveData2=stuActiveDataDao.selectOne(id);
		Integer stuAuthId=stuActiveData2.getStu_auth_id(); //得到学生id  给学生发系统消息
		// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
		MessagePublish mp = new MessagePublish();
		int mpId = messagePublishDao.getSequence();
		mp.setId(mpId);
		mp.setTitle(userAuthority2.getName()  + "【" + orgaName + "】" +  "未通过你的录入信息！");
		mp.setContent("您的【"+orderName+"】录入信息未通过！");
		// 获取当前时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp send_time = Timestamp.valueOf(time);
		mp.setSend_time(send_time);
		mp.setSender_auth_id(authId);
		mp.setState("Y");
		mp.setType_id(2);
		messagePublishDao.add(mp);                     //发布表	
		MessageReceive mr = new MessageReceive();
		mr.setReceiver_auth_id(stuAuthId);
		mr.setId(messageReceiveDao.getSequence());
		mr.setProgress_state(0);
		mr.setState("Y");
		mr.setMp_id(mpId);
		messageReceiveDao.add(mr);            //接收表
		
		stuActiveData.setExamine_authid(authId);
		stuActiveData.setExamine_state(-1);
		
		int i = stuActiveDataDao.update(stuActiveData);
		return i;
	}

		/**
	 * 教学秘书下的待审核数据
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月28日
	 */
	public int getAudit(HttpServletRequest req) {
		//从缓存中得到secre_auth_id
				LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
				Integer secre_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
				Integer orgaId=loginBean.getvUserDetailInfo().getOrga_id();
				StuActiveData stuActiveData=new StuActiveData();
				stuActiveData.setDept_id(orgaId);
				stuActiveData.setState("Y");
				stuActiveData.setExamine_state(0);
				
				int i =stuActiveDataDao.selectCount(stuActiveData);
				
		return i;
	}

	/**
	 * 根据id查询审批评价
	 * @param stuActiveData
	 * @return
	 * @author guocc
	 * @date 2017年6月1日
	 */
	public StuActiveData getExamineTextById(StuActiveData stuActiveData) {
		return stuActiveDataDao.getExamineTextById(stuActiveData);
	}
	
	/**
	 * 删除录入信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年8月16日
	 */
	public int delStuActiveData(HttpServletRequest req) {
		String id = req.getParameter("id");
		String state = req.getParameter("state");
		int num = 0;
		if (id != null && !id.equals("") && state != null && !state.equals("")) {
			StuActiveData stuActiveData = new StuActiveData();
			stuActiveData.setId(Integer.parseInt(id));
			stuActiveData.setState(state);
			num = stuActiveDataDao.update(stuActiveData);
		}else{
			num = -1;
		}
		return num;
	}
	
	/**
	 * 查询所有数据录入情况  （相应学生的）
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年8月17日
	 */
	public JSONObject findStuActiveData(int pageIndex, int rows, HttpServletRequest req) {
		//
		String stu_auth_id=req.getParameter("stu_auth_id");
		String dept_id=req.getParameter("dept_id");
		String data_type_id=req.getParameter("data_type_id");
		StuActiveData t = new StuActiveData();
		if (stu_auth_id != null && !stu_auth_id.equals("")) {
			t.setStu_auth_id(Integer.parseInt(stu_auth_id));
		}
		if (dept_id != null && !dept_id.equals("")) {
			t.setDept_id(Integer.parseInt(dept_id));
		}
		if (data_type_id != null && !data_type_id.equals("")) {
			t.setData_type_id(Integer.parseInt(data_type_id));
		}
		JSONObject jobj = new JSONObject();
		int totalCount = stuActiveDataDao.selectStuActiveDataCount(t);
		t.setOrderCondition("t.create_time desc");
		List<StuActiveData> dataList = stuActiveDataDao.selectStuActiveData(new RowBounds((pageIndex - 1) * rows, pageIndex * rows), t);
		for (int i = 0; i < dataList.size(); i++) {
			if(dataList.get(i).getmOrder_name()==""||dataList.get(i).getmOrder_name()==null){				
			}else{
				dataList.get(i).setOrder_name(dataList.get(i).getmOrder_name()+"   ["+dataList.get(i).getOrder_name()+"]");
			}
		}
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));//总页数
		jobj.put("page", pageIndex);//当前页码
		jobj.put("records", totalCount);//总的记录数
		jobj.put("rows", dataList);//显示的具体数据，jsonarray格式。
		return jobj;
	}
}
