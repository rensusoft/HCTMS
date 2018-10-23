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

import com.rensu.education.hctms.basicdata.bean.ExamineTypeContent;
import com.rensu.education.hctms.basicdata.dao.ExamineTypeContentDao;
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
import com.rensu.education.hctms.userauth.service.StudentInfoService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("taskDealService")
public class TaskDealService extends BaseService<TaskDeal> {
	
	Logger log = Logger.getLogger(TaskDealService.class);
	
	@Autowired
	protected TaskDealDao taskDealDao;
	@Autowired
	protected TaskPublishDao taskPublishDao;
    @Autowired
    protected StudentInfoService studentInfoService;
    @Autowired
    protected ExamineTypeContentDao examineTypeContentDao;
    @Autowired
    protected MessagePublishDao messagePublishDao; 
    @Autowired
    protected MessageReceiveDao messageReceiveDao;
    
    
	public Object getTaskPubStu(int page, int rows, HttpServletRequest req) {
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer stu_auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		JSONObject jobj =new JSONObject();
		String start_date=req.getParameter("start_date");
		String end_date= req.getParameter("end_date");
		String progress_state=req.getParameter("progress_state");
		String task_type_code=req.getParameter("task_type_code");
		String state= req.getParameter("state");
		TaskDeal taskDeal=new TaskDeal();
		if (StringUtil.isNotEmpty(start_date)) {
			taskDeal.setStart_date(start_date+" 00:00:00");
		}
		if (StringUtil.isNotEmpty(end_date)) {
			taskDeal.setEnd_date(end_date+" 23:59:59");
		}
		if (StringUtil.isNotEmpty(progress_state)) {
			taskDeal.setProgress_state(progress_state);;
		}
		if (StringUtil.isNotEmpty(task_type_code)) {
			taskDeal.setTask_type_code(task_type_code);;
		}
		taskDeal.setOrderCondition("publish_time desc");
		taskDeal.setState(state);
		taskDeal.setReceiver_auth_id(stu_auth_id);
		List<TaskDeal> list=taskDealDao.selectPageAllStu(new RowBounds((page-1)* rows,page* rows),taskDeal);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getProgress_state().equals("1")){
				list.get(i).setProgress_stateStr("<span style='color:#FF0000;'>未处理</span>");
			}else if(list.get(i).getProgress_state().equals("2")){
				list.get(i).setProgress_stateStr("<span style='color:#00FF00;'>已处理</span>");
			} else{
				list.get(i).setProgress_stateStr("<span style='color:#0000FF;'>已评分</span>");
			}
		}
		TaskDeal taskDeal2 =new TaskDeal();
		taskDeal2.setReceiver_auth_id(stu_auth_id);
		int totalCount=taskDealDao.selectCount(taskDeal2);
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

	public Object answerTask(HttpServletRequest req) {
		String id=req.getParameter("id");
		TaskPublish taskPublish=new TaskPublish();
		if(id!=null&&!id.equals("")){
			TaskDeal taskDeal=taskDealDao.selectOne(Integer.parseInt(id));
			 taskPublish=taskPublishDao.selectOne(taskDeal.getTp_id());
		}
		return StringUtil.returns(true, taskPublish);
	}

	public Object saveStuContent(HttpServletRequest req) {
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		String user_name = loginBean.getvUserDetailInfo().getUser_name();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		String id=req.getParameter("id");
		String content=req.getParameter("content");
		int num1=0;
		int num2=0;
        TaskDeal taskDeal=new TaskDeal();
        TaskPublish taskPublish=new TaskPublish();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp finish_time = Timestamp.valueOf(time);
        if(id!=null&&!id.equals("")){
        taskDeal=taskDealDao.selectOne(Integer.parseInt(id));
        if(content!=null&&!content.equals("")){
        	taskDeal.setContent(content);
        }
        taskDeal.setProgress_state("2");
		taskDeal.setFinish_time(finish_time);
		taskPublish=taskPublishDao.selectOne(taskDeal.getTp_id());
		taskPublish.setProgress_state("2");
		num1=taskDealDao.update(taskDeal);
		num2=taskPublishDao.update(taskPublish);
       
        int mpId = 0;
        String title = user_name  + "【" + orga_name + "】" +  "完成了作业解答，请及时审批！";
        MessagePublish mp = new MessagePublish();
        mpId = messagePublishDao.getSequence();
        mp.setTitle(title);
		mp.setId(mpId);
		mp.setContent(content);
		mp.setSend_time(finish_time);
		mp.setSender_auth_id(auth_id);
		mp.setState("Y");
		mp.setType_id(2);
		messagePublishDao.add(mp); 
		MessageReceive mr = new MessageReceive();
		mr.setProgress_state(0);
		mr.setState("Y");
		mr.setMp_id(mpId);
		mr.setReceiver_auth_id(Integer.parseInt(taskPublish.getPublisher_auth_id()));
		mr.setId(messageReceiveDao.getSequence());
		messageReceiveDao.add(mr);     
        }
        if(num1>0&&num2>0){
        	return  StringUtil.returns(true, "操作成功!");
        }else{
        	return  StringUtil.returns(false, "操作失败!");
        }
        
	}

	public Object getStuWithTask(HttpServletRequest req) {
		String id =req.getParameter("id");
		TaskPublish taskPublish=new TaskPublish();
		TaskDeal taskDeal=new TaskDeal();
		List<TaskDeal> taskDeals=new ArrayList<TaskDeal>();
		StudentInfo studentInfo=new StudentInfo();
		if(id!=null&&!id.equals("")){
			taskPublish=taskPublishDao.selectOne(Integer.parseInt(id));	
		    taskDeal.setTp_id(taskPublish.getId());
		    taskDeal.setProgress_state("2");
			taskDeals=taskDealDao.selectList(taskDeal);
			for(int i=0;i<taskDeals.size();i++){
				studentInfo=studentInfoService.selectStuNameByAuthId(taskDeals.get(i).getReceiver_auth_id());
				taskDeals.get(i).setStuName(studentInfo.getStu_name());
			}
		}
		return StringUtil.returns(true, taskDeals);
	}

	public Object getStuTaskById(HttpServletRequest req) {
		String id=req.getParameter("id");
		TaskPublish taskPublish=new TaskPublish();
		TaskDeal taskDeal=new TaskDeal();
		ExamineTypeContent examineTypeContent=new ExamineTypeContent();
		examineTypeContent.setType_id(2);    
		examineTypeContent.setOrderCondition("order_num asc");
		List<ExamineTypeContent> list=examineTypeContentDao.selectList(examineTypeContent);
		if(id!=null&&!id.equals("")){
			taskDeal=taskDealDao.selectOne(Integer.parseInt(id));
			taskPublish=taskPublishDao.selectOne(taskDeal.getTp_id());
			taskDeal.setContent_teach(taskPublish.getContent());
			for(int i=0;i<list.size();i++){
				taskDeal.setContents(list);
			}
			
		}
		return StringUtil.returns(true, taskDeal);
	}
}
