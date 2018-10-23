package com.rensu.education.hctms.teach.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.teach.bean.TaskDeal;
import com.rensu.education.hctms.teach.bean.TaskPublish;
import com.rensu.education.hctms.teach.dao.TaskDealDao;
import com.rensu.education.hctms.teach.dao.TaskPublishDao;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.dao.StudentInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("taskPublishService")
public class TaskPublishService extends BaseService<TaskPublish> {
	
	Logger log = Logger.getLogger(TaskPublishService.class);
	
	@Autowired
	protected TaskPublishDao taskPublishDao;
	@Autowired
	protected TaskDealDao taskDealDao;
    @Autowired
    protected StudentInfoDao studentInfoDao;
    @Autowired
    protected MessagePublishDao messagePublishDao; 
    @Autowired
    protected MessageReceiveDao messageReceiveDao;
    
    
	public Object sendHomeworkToStu(HttpServletRequest req) {
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer role_id=loginBean.getvUserDetailInfo().getAuth_id();
		Integer orga_id=loginBean.getvUserDetailInfo().getOrga_id();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		String user_name = loginBean.getvUserDetailInfo().getUser_name();
		String content=req.getParameter("content");
		String title=req.getParameter("title");
		String task_type_code=req.getParameter("task_type_code");
		String senderStu=req.getParameter("senderStu");
		String progress_state=req.getParameter("progress_state");
		String state=req.getParameter("state");
		org.json.JSONArray jsonArray = new org.json.JSONArray(senderStu); 
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp send_time = Timestamp.valueOf(time);
		TaskPublish taskPublish=new TaskPublish();
		taskPublish.setId(taskPublishDao.getSequence());
        taskPublish.setTitle(title);
        taskPublish.setContent(content);
        taskPublish.setOrga_id(orga_id.toString());
        taskPublish.setProgress_state(progress_state);
        taskPublish.setPublish_time(send_time);
        taskPublish.setPublisher_auth_id(role_id.toString());
        taskPublish.setTask_type_code(task_type_code);
        if(task_type_code.equals("1")){
        	taskPublish.setTask_type_name("课前");
        }else if(task_type_code.equals("2")){
        	taskPublish.setTask_type_name("课中");
        }else{
        	taskPublish.setTask_type_name("课后");
        }
        taskPublish.setState(state);
        int num1=taskPublishDao.add(taskPublish);
        TaskDeal taskDeal=new TaskDeal();
        int num2=0;
        for (int i = 0; i < jsonArray.length(); i++) {
			if(jsonArray.getJSONObject(i).getString("key")!=null&&!jsonArray.getJSONObject(i).getString("key").equals("")){
				taskDeal.setReceiver_auth_id(Integer.parseInt(jsonArray.getJSONObject(i).getString("key")));
			}
			taskDeal.setId(taskDealDao.getSequence());
	        taskDeal.setTp_id(taskPublish.getId());
	        taskDeal.setReceive_time(send_time);
	        taskDeal.setState(state);
	        taskDeal.setProgress_state("1");
	        num2=taskDealDao.add(taskDeal);
		}
        int mpId=0;
        String mTitle=user_name  + "【" + orga_name + "】" +  "发布了作业，请及时作答！";
        MessagePublish mp = new MessagePublish();
		mpId = messagePublishDao.getSequence();
		mp.setId(mpId);
		mp.setTitle(mTitle);
		mp.setSend_time(send_time);
		mp.setSender_auth_id(role_id);
		mp.setState("Y");
		mp.setType_id(2);
		mp.setContent(content);
		messagePublishDao.add(mp);
		MessageReceive mr = new MessageReceive();
		mr.setProgress_state(0);
		mr.setState("Y");
		mr.setMp_id(mpId);
		for (int i = 0; i < jsonArray.length(); i++) {
			if(jsonArray.getJSONObject(i).getString("key")!=null&&!jsonArray.getJSONObject(i).getString("key").equals("")){
				mr.setReceiver_auth_id(Integer.parseInt(jsonArray.getJSONObject(i).getString("key")));
				mr.setId(messageReceiveDao.getSequence());
				messageReceiveDao.add(mr);
			}
		}
        if(num1>0&&num2>0){
        	return  StringUtil.returns(true, "发布成功!");
        }else{
        	return  StringUtil.returns(false, "发布失败!");
        }
		
	}

	public Object getTaskPub(int page, int rows, HttpServletRequest req) {
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer publisher_auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		JSONObject jobj=new JSONObject();
		String start_date=req.getParameter("start_date");
		String end_date= req.getParameter("end_date");
		String state= req.getParameter("state");
		String progress_state=req.getParameter("progress_state");
		String task_type_code=req.getParameter("task_type_code");
		TaskPublish taskPublish=new TaskPublish();
		if (StringUtil.isNotEmpty(start_date)) {
			taskPublish.setStart_date(start_date+" 00:00:00");
		}
		if (StringUtil.isNotEmpty(end_date)) {
			taskPublish.setEnd_date(end_date+" 23:59:59");
		}
		if (StringUtil.isNotEmpty(progress_state)) {
			taskPublish.setProgress_state(progress_state);;
		}
		if (StringUtil.isNotEmpty(task_type_code)) {
			taskPublish.setTask_type_code(task_type_code);;
		}
		taskPublish.setOrderCondition("publish_time desc");
		taskPublish.setPublisher_auth_id(publisher_auth_id.toString());
		taskPublish.setState(state);
		List<TaskPublish> list=taskPublishDao.selectPageAll(new RowBounds((page-1)* rows,page* rows), taskPublish);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getProgress_state().equals("1")){
				list.get(i).setProgress_stateStr("<span style='color:#00FF00;'>已发布</span>");
			}else if(list.get(i).getProgress_state().equals("2")){
				list.get(i).setProgress_stateStr("<span style='color:#FF0000;'>已处理</span>");
			} else{
				list.get(i).setProgress_stateStr("<span style='color:#0000FF;'>完成</span>");
			}
		}
		int totalCount=taskPublishDao.selectCount(taskPublish);
		jobj.put("tatal",StringUtil.getMaxInt(totalCount, rows));//总页数
		jobj.put("page", page);//当前页码
		jobj.put("records", totalCount);//总的记录数
		jobj.put("rows", list);//显示的具体数据，jsonarray格式。	
		if(jobj!=null){
			String jsonStr = StringUtil.dnull(jobj.toString());
			jobj = JSONObject.fromObject(jsonStr);
		}   
		return StringUtil.dnull(jobj.toString()); 
	}

	public Object delTask(HttpServletRequest req) {
		Integer id=Integer.parseInt(req.getParameter("id"));
		String state=req.getParameter("state");
		TaskPublish taskPublish=new TaskPublish();
		List<TaskDeal> list=new ArrayList<TaskDeal>();
		TaskDeal taskDeal=new TaskDeal();
		if(id != null && !id.equals("")){
			taskPublish =taskPublishDao.selectOne(id);	
			taskDeal.setTp_id(taskPublish.getId());
			list=taskDealDao.selectList(taskDeal);
		}
		 if(state!=null&!state.equals("")){
			 taskPublish.setState(state); 
			 for(int i=0;i<list.size();i++){
				 list.get(i).setState("X");
				 taskDealDao.update(list.get(i));
			 }
		 }
		int num=0;
		num=taskPublishDao.update(taskPublish);
		if(num > 0){
				return StringUtil.returns(true, "操作成功!");
			}else{
				return StringUtil.returns(false, "操作失败！");
			}
	}

	public Object updateTask(HttpServletRequest req) {
		  Integer id=Integer.parseInt(req.getParameter("id"));
		  TaskPublish taskPublish=new TaskPublish();
		  if (id != null && !id.equals("")) {
		   taskPublish=taskPublishDao.selectOne(id);
		   TaskDeal taskDeal=new TaskDeal();
		   taskDeal.setTp_id(taskPublish.getId());;
		   List<TaskDeal> list=taskDealDao.selectList(taskDeal);
		   StudentInfo studentInfo=new StudentInfo();
		   StringBuilder sb = new StringBuilder();
			for(int i=0;i<list.size();i++){
				studentInfo=studentInfoDao.selectStuNameByAuthId(list.get(i).getReceiver_auth_id());
			            if (sb.length() > 0) {//该步即不会第一位有逗号，也防止最后一位拼接逗号！
			                sb.append(",");
			            }
			            sb.append(studentInfo.getStu_name());
			}
			taskPublish.setStuList(sb.toString());
		  }
		  return StringUtil.returns(true, taskPublish);
	} 

	
	public Object sendHomeworkEditToStu(HttpServletRequest req) {
		String id=req.getParameter("id");
		String content=req.getParameter("content");
		String title=req.getParameter("title");
		String task_type_code=req.getParameter("task_type_code");
		String senderStu=req.getParameter("senderStu");
		String progress_state=req.getParameter("progress_state");
		org.json.JSONArray jsonArray = new org.json.JSONArray(senderStu); 
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp send_time = Timestamp.valueOf(time);
		TaskPublish taskPublish=new TaskPublish();
		if(id!=null&&!id.equals("")){
			 taskPublish=taskPublishDao.selectOne(Integer.parseInt(id));	
		}
        taskPublish.setTitle(title);
        taskPublish.setContent(content);
        taskPublish.setProgress_state(progress_state);
        taskPublish.setPublish_time(send_time);
        taskPublish.setTask_type_code(task_type_code);
        if(task_type_code.equals("1")){
        	taskPublish.setTask_type_name("课前");
        }else if(task_type_code.equals("2")){
        	taskPublish.setTask_type_name("课中");
        }else{
        	taskPublish.setTask_type_name("课后");
        }
        int num1=taskPublishDao.update(taskPublish);
        TaskDeal taskDeal=new TaskDeal();
        taskDeal.setTp_id(taskPublish.getId());
        List<TaskDeal> list=taskDealDao.selectList(taskDeal);
        int num2=0;
        if(jsonArray.length()!=0){
        	for(int i=0;i<list.size();i++){
        		taskDealDao.delete(list.get(i));
        	}
        	for (int i = 0; i < jsonArray.length(); i++) {
        		if(jsonArray.getJSONObject(i).getString("key")!=null&&!jsonArray.getJSONObject(i).getString("key").equals("")){
    				taskDeal.setReceiver_auth_id(Integer.parseInt(jsonArray.getJSONObject(i).getString("key")));
    				taskDeal.setId(taskDealDao.getSequence());
    		        taskDeal.setTp_id(taskPublish.getId());
    		        taskDeal.setReceive_time(send_time);
    		        taskDeal.setProgress_state("1");
    		        taskDeal.setState("Y");
    		        num2=taskDealDao.add(taskDeal);
    			}
        	}
        } else{
        	for(int i=0;i<list.size();i++){
        		list.get(i).setReceive_time(send_time);
        		num2=taskDealDao.update(list.get(i));
        	}
        }
        
        if(num1>0&&num2>0){
        	return  StringUtil.returns(true, "修改成功!");
        }else{
        	return  StringUtil.returns(false, "修改失败!");
        }
		
	}

}
