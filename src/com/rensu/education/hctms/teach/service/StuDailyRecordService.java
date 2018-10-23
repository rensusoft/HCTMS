package com.rensu.education.hctms.teach.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessagePublish;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessagePublishDao;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.teach.bean.StuDailyRecord;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuDailyRecordDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("stuDailyRecordService")
public class StuDailyRecordService extends BaseService<StuDailyRecord> {
	
	Logger log = Logger.getLogger(StuDailyRecordService.class);
	
	@Autowired
	protected StuDailyRecordDao stuDailyRecordDao;
	@Autowired
	protected MessageReceiveDao messageReceiveDao;
	@Autowired
	protected MessagePublishDao messagePublishDao;
	@Autowired
	protected TrainPlanDao trainPlanDao;
	/**
	 * 查询培训日志
	 * @param pageIndex
	 * @param rows
	 * @param stuDailyRecord
	 * @return JSONObject
	 * @author guocc
	 * @date 2017年2月28日
	 */
	public JSONObject selectDailyRecord(int pageIndex, int rows,StuDailyRecord stuDailyRecord) {
		JSONObject jobj = new JSONObject();
		List<StuDailyRecord> dataList = stuDailyRecordDao.selectDailyRecord(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				stuDailyRecord);
		int totalCount = stuDailyRecordDao.selectCount(stuDailyRecord);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}

	/**
	 * 查询待审阅的日志
	 * @param pageIndex
	 * @param rows
	 * @param stuDailyRecord
	 * @return JSONObject
	 * @author guocc
	 * @date 2017年6月7日
	 */
	public JSONObject selectReviewedDaily(int pageIndex, int rows,StuDailyRecord stuDailyRecord) {
		JSONObject jobj = new JSONObject();
		List<StuDailyRecord> dataList = stuDailyRecordDao.selectReviewedDaily(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				stuDailyRecord);
		int totalCount = dataList.size();
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}

	/**
	 * 查询培训日志
	 * @author zhengc
	 * @date 2017年5月26日
	 */
	public  ModelAndView selectDailyRecordById(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		String flag = req.getParameter("flag");
		Integer id = Integer.parseInt(req.getParameter("id")) ;
		StuDailyRecord stuDailyRecord = stuDailyRecordDao.selectDailyRecordById(id);
		if(stuDailyRecord.getType_id()==2){
			String arr=stuDailyRecord.getDuration();
			String time[]=arr.split("--");
			String week=time[0];   
			String timeBegin=time[1];
			String timeEnd=time[2];
			stuDailyRecord.setWeek(week);
			stuDailyRecord.setTimeBegin(timeBegin);
			stuDailyRecord.setTimeEnd(timeEnd);
		}
		stuDailyRecord.setFlag(flag);
		mv.setViewName("web/teach/look");
		mv.addObject("stuDailyRecord",stuDailyRecord);
		return mv;

	}

	/**
	 * 删除培训日志
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年2月28日
	 */
	public Object delDailyRecord(HttpServletRequest req){
		String id = req.getParameter("id");
		String create_auth_id = req.getParameter("create_auth_id");
		String state = req.getParameter("state");
		//
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		String user_name = loginBean.getvUserDetailInfo().getUser_name();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		if(id != null && !id.equals("") && state != null && !state.equals("")){
			StuDailyRecord stuDailyRecord = new StuDailyRecord();
			stuDailyRecord.setId(Integer.parseInt(id));
			stuDailyRecord.setState(state);
			int flag = stuDailyRecordDao.update(stuDailyRecord);
			if(flag > 0){
				if (state.equals("Y")) {//  审阅发消息
					//发送消息
					int mpId = 0;
					String title = user_name  + "【" + orga_name + "】" +  "审阅了你的日志！";
					// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
					MessagePublish mp = new MessagePublish();
					mpId = messagePublishDao.getSequence();
					mp.setId(mpId);
					mp.setTitle(title);
					mp.setContent(title);
					Timestamp create_time = new Timestamp((new Date()).getTime());
					mp.setSend_time(create_time);
					mp.setSender_auth_id(auth_id);
					mp.setState("Y");
					mp.setType_id(2);
					messagePublishDao.add(mp);                     //发布表
					
					MessageReceive mr = new MessageReceive();
					mr.setProgress_state(0);
					mr.setState("Y");
					mr.setMp_id(mpId);
					if (create_auth_id != null && !create_auth_id.equals("")) {
						mr.setReceiver_auth_id(Integer.parseInt(create_auth_id));
					}
					mr.setId(messageReceiveDao.getSequence());
					messageReceiveDao.add(mr);            //接收表
				}
					return StringUtil.returns(true, "操作成功!");
			}else{
				return StringUtil.returns(false, "操作失败!");
			}
		}else{
			return StringUtil.returns(false, "操作失败!");
		}
	}

	/**
	 * 老师批量审阅日志
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年6月7日
	 */
	public Object batchReview(HttpServletRequest req){
		String id_create_auth_id_arry = req.getParameter("id_create_auth_id_arry");
		String state = req.getParameter("state");
		//
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		String user_name = loginBean.getvUserDetailInfo().getUser_name();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		Integer auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		if(id_create_auth_id_arry != null && !id_create_auth_id_arry.equals("") && state != null && !state.equals("")){
			String[] id_create_auth_id_arrys = id_create_auth_id_arry.split(";");
			StuDailyRecord stuDailyRecord = new StuDailyRecord();
			stuDailyRecord.setState(state);
			for (int i = 0; i < id_create_auth_id_arrys.length; i++) {
				stuDailyRecord.setId(Integer.parseInt(id_create_auth_id_arrys[i].split("-")[0]));
				int flag = stuDailyRecordDao.update(stuDailyRecord);
				if(flag > 0){
					//发送消息
					int mpId = 0;
					String title = user_name  + "【" + orga_name + "】" +  "审阅了你的日志！";
					// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
					MessagePublish mp = new MessagePublish();
					mpId = messagePublishDao.getSequence();
					mp.setId(mpId);
					mp.setTitle(title);
					mp.setContent(title);
					Timestamp create_time = new Timestamp((new Date()).getTime());
					mp.setSend_time(create_time);
					mp.setSender_auth_id(auth_id);
					mp.setState("Y");
					mp.setType_id(2);
					messagePublishDao.add(mp);                     //发布表
					
					MessageReceive mr = new MessageReceive();
					mr.setProgress_state(0);
					mr.setState("Y");
					mr.setMp_id(mpId);
					mr.setReceiver_auth_id(Integer.parseInt(id_create_auth_id_arrys[i].split("-")[1]));
					mr.setId(messageReceiveDao.getSequence());
					messageReceiveDao.add(mr);            //接收表
				}else{
					return StringUtil.returns(false, "操作失败!");
				}
			}
			return StringUtil.returns(true, "操作成功!");
		}else{
			return StringUtil.returns(false, "操作失败!");
		}
	}

	/**
	 * 保存培训日志
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年2月29日
	 */
	public Object saveDaily(HttpServletRequest req){
		String flag = req.getParameter("flag");//区分增加和修改的标志
		String duration = req.getParameter("duration");//汇报时间
		String content = req.getParameter("content");
		String type_id = req.getParameter("type_id");
		StuDailyRecord stuDailyRecord = new StuDailyRecord();
		if(type_id != null && !type_id.equals("")){
			stuDailyRecord.setType_id(Integer.parseInt(type_id));
		}
		stuDailyRecord.setDuration(duration);
		stuDailyRecord.setContent(content);
		stuDailyRecord.setCreate_time(new Timestamp((new Date()).getTime()));//做修改时创建时间是否要改动？
		LoginBean loginBean = (LoginBean)req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		Integer orga_id = loginBean.getvUserDetailInfo().getOrga_id();
		String user_name = loginBean.getvUserDetailInfo().getUser_name();
		String orga_name = loginBean.getvUserDetailInfo().getOrga_name();
		stuDailyRecord.setCreate_auth_id(create_auth_id);
		stuDailyRecord.setState("N");//  待审阅
		Integer id = 0;//ID标识
		int num = 0;
		if(("").equals(flag)||null==flag){
			id = stuDailyRecordDao.getSequence();
			stuDailyRecord.setId(id);
			num = stuDailyRecordDao.add(stuDailyRecord);
			if (num > 0) {
				//发送消息
				int mpId = 0;
				String title = user_name  + "【" + orga_name + "】" +  "提交了日志！";
				// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
				MessagePublish mp = new MessagePublish();
				mpId = messagePublishDao.getSequence();
				mp.setId(mpId);
				mp.setTitle(title);
				mp.setContent(title);
				Timestamp create_time = new Timestamp((new Date()).getTime());
				mp.setSend_time(create_time);
				mp.setSender_auth_id(create_auth_id);
				mp.setState("Y");
				mp.setType_id(2);
				messagePublishDao.add(mp);                     //发布表
				
				MessageReceive mr = new MessageReceive();
				mr.setProgress_state(0);
				mr.setState("Y");
				mr.setMp_id(mpId);
				TrainPlan tp = new TrainPlan();
				tp.setStu_auth_id(create_auth_id);
				tp.setTrain_dept_id(orga_id.toString());
				tp.setState("Y");
				Integer teacher_auth_id = 0;
				if(trainPlanDao.selectList(tp) != null && !trainPlanDao.selectList(tp).isEmpty()) {
					teacher_auth_id = trainPlanDao.selectList(tp).get(0).getTeacher_auth_id();
				}
				mr.setReceiver_auth_id(teacher_auth_id);
				mr.setId(messageReceiveDao.getSequence());
				messageReceiveDao.add(mr);            //接收表
			}
		}else{
			id = Integer.parseInt(flag);
			stuDailyRecord.setId(id);
			num =stuDailyRecordDao.update(stuDailyRecord);
		}
		if(num < 0){
			return StringUtil.returns(false, "操作失败!");
		}
		return StringUtil.returns(true, "操作成功!");
	}
	
}
