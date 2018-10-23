package com.rensu.education.hctms.message.service;

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
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("messagePublishService")
public class MessagePublishService extends BaseService<MessagePublish> {
	
	Logger log = Logger.getLogger(MessagePublishService.class);
	
	@Autowired
	protected MessagePublishDao messagePublishDao;
	
	@Autowired
	protected MessageReceiveDao messageReceiveDao;
	@Autowired
	protected UserAuthorityDao authorityDao;

	/**
	 * 根据当前用户权限id   查询当前用户发的信息
	 * @param page
	 * @param rows
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年2月27日
	 */
	public Object selectPageByAuthId(int page, int rows, HttpServletRequest req) {
		//从缓存中拿到用户的auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer auth_id=loginBean.getvUserDetailInfo().getAuth_id();   
		
		JSONObject jobj = new JSONObject();
		MessagePublish messagePublish=new MessagePublish();
		messagePublish.setSender_auth_id(auth_id);
		messagePublish.setState("Y");
		int totalCount = messagePublishDao.selectCount(messagePublish);
		messagePublish.setState("Y");
		messagePublish.setOrderCondition(" send_time desc ");
		List<MessagePublish> lists=messagePublishDao.selectPageAll(new RowBounds((page-1)* rows,page* rows), messagePublish);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));//总页数
		jobj.put("page", page);//当前页码
		jobj.put("records", totalCount);//总的记录数
		jobj.put("rows", lists);//显示的具体数据，jsonarray格式。	
		if(jobj!=null){
			String jsonStr = StringUtil.dnull(jobj.toString());
			jobj = JSONObject.fromObject(jsonStr);
		}
		return StringUtil.dnull(jobj.toString());  
	}

	public int updateState(HttpServletRequest req) {
		int id=Integer.parseInt(req.getParameter("id"));
		MessagePublish messagePublish=new MessagePublish();
		messagePublish.setId(id);
		messagePublish.setState("X");
		int i=messagePublishDao.update(messagePublish);
		MessageReceive messageReceive=new MessageReceive();
		messageReceive.setMp_id(id);
		messageReceive.setState("X");
		messageReceiveDao.updateState(messageReceive);
		return i;
	}

	
	

	
	public Object sendMsg(HttpServletRequest req) {
		// 从session中获取当前用户
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		int auth_id = loginBean.getvUserDetailInfo().getAuth_id();  //获取当前用户的权限id    发送人的id
		String content = req.getParameter("content");
		String title = req.getParameter("title");
		String rsender_auth_id = req.getParameter("sender_auth_id"); //接收人的id
		org.json.JSONArray jsonArray = new org.json.JSONArray(rsender_auth_id); 
		
		String typeId = req.getParameter("typeId"); //消息类型
		int mpId = 0;
		List<MessagePublish> list = new ArrayList<MessagePublish>();
		// 获取当前时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp send_time = Timestamp.valueOf(time);
		// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
		MessagePublish mp = new MessagePublish();
		if (content != "") {
			mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			mp.setTitle(title);
			mp.setContent(content);
			mp.setSend_time(send_time);
			mp.setSender_auth_id(auth_id);
			mp.setState("Y");
			mp.setType_id(Integer.parseInt(typeId));
			messagePublishDao.add(mp);                     //发布表
					
			MessageReceive mr = new MessageReceive();
			for (int i = 0; i < jsonArray.length(); i++) {
				mr.setReceiver_auth_id(Integer.parseInt(jsonArray.get(i).toString()));
				mr.setId(messageReceiveDao.getSequence());
				mr.setProgress_state(0);
				mr.setState("Y");
				mr.setMp_id(mpId);
				messageReceiveDao.add(mr);            //接收表
			}
			return StringUtil.returns(true, "发送成功！");
		} else
			return StringUtil.returns(false, "发送失败！");
	}

	public Object sendMsgByRoleId(HttpServletRequest req) {
		// 从session中获取当前用户
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		int auth_id = loginBean.getvUserDetailInfo().getAuth_id();  //获取当前用户的权限id    发送人的id
		String content = req.getParameter("content");
		String title = req.getParameter("title");
		String rsender_auth_id = req.getParameter("sender_auth_id"); //接收人的id
		org.json.JSONArray jsonArray = new org.json.JSONArray(rsender_auth_id); 
		
		String typeId = req.getParameter("typeId"); //消息类型
		int mpId = 0;
		List<MessagePublish> list = new ArrayList<MessagePublish>();
		// 获取当前时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp send_time = Timestamp.valueOf(time);
		// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
		MessagePublish mp = new MessagePublish();
		if (content != "") {
			mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			mp.setTitle(title);
			mp.setContent(content);
			mp.setSend_time(send_time);
			mp.setSender_auth_id(auth_id);
			mp.setState("Y");
			mp.setType_id(Integer.parseInt(typeId));
			messagePublishDao.add(mp);                     //发布表
					
			
			for (int i = 0; i < jsonArray.length(); i++) {
				List<MessageReceive> lists = new ArrayList<MessageReceive>();
				UserAuthority userAuthority=new UserAuthority();
				userAuthority.setRole_id(Integer.parseInt(jsonArray.get(i).toString()));
				List<UserAuthority> authors=authorityDao.selectList(userAuthority);  //得到拥有所有这类角色的人物
				if(authors.size()>0){
				for (int j = 0; j < authors.size(); j++) {
					MessageReceive mr =new MessageReceive();
					mr.setProgress_state(0);
					mr.setState("Y");
					mr.setMp_id(mpId);
					mr.setReceiver_auth_id(authors.get(j).getAuth_id());
					lists.add(mr);
				}
				messageReceiveDao.addMany(lists);            //接收表
			}else{
				UserAuthority userAuthority1=new UserAuthority();
				userAuthority1.setStu_type_id(Integer.parseInt(jsonArray.get(i).toString()));
				List<UserAuthority> authors1=authorityDao.selectList(userAuthority);  //得到拥有所有学生的人物
				if(authors1.size()>0){
				for (int j = 0; j < authors1.size(); j++) {
					MessageReceive mr =new MessageReceive();
					mr.setProgress_state(0);
					mr.setState("Y");
					mr.setMp_id(mpId);
					mr.setReceiver_auth_id(authors.get(j).getAuth_id());
					lists.add(mr);
				}
				messageReceiveDao.addMany(lists);  }
			}
				}
			return StringUtil.returns(true, "发送成功！");
		} else
			return StringUtil.returns(false, "发送失败！");
	}

	public MessageReceive getMyMessagebyId(HttpServletRequest req) {
		int id=Integer.parseInt(req.getParameter("id"));   //接收表的id
		MessageReceive messageReceive=new MessageReceive();   //接收表 对象
		messageReceive.setMp_id(id);
		List<MessageReceive> lists=messageReceiveDao.selectListAll(messageReceive);
		if(lists.size()>0){
		messageReceive.setSendTimeStr(lists.get(0).getSendTimeStr());
		messageReceive.setContent(lists.get(0).getContent());
		messageReceive.setTitle(lists.get(0).getTitle());
		String name="";
		for (int i = 0; i < lists.size(); i++) {
			if(i!=lists.size()-1){
			name+=lists.get(i).getReceiverName()+",";
			}else{
				name+=lists.get(i).getReceiverName()+";";
			}
		}
		messageReceive.setReceiverName(name);}
		else{
			
		}
		return messageReceive;
	}

	public Object sendMsgByOrga(HttpServletRequest req) {
		// 从session中获取当前用户
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		int auth_id = loginBean.getvUserDetailInfo().getAuth_id();  //获取当前用户的权限id    发送人的id
		String content = req.getParameter("content");
		String title = req.getParameter("title");
		String rsender_auth_id = req.getParameter("sender_auth_id"); //接收人的id
		org.json.JSONArray jsonArray = new org.json.JSONArray(rsender_auth_id); 
		
		String typeId = req.getParameter("typeId"); //消息类型
		int mpId = 0;
		List<MessagePublish> list = new ArrayList<MessagePublish>();
		// 获取当前时间
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		Timestamp send_time = Timestamp.valueOf(time);
		// 1、往消息发布表插入数据（MESSAGE_PUBLISH）
		MessagePublish mp = new MessagePublish();
		if (content != "") {
			mpId = messagePublishDao.getSequence();
			mp.setId(mpId);
			mp.setTitle(title);
			mp.setContent(content);
			mp.setSend_time(send_time);
			mp.setSender_auth_id(auth_id);
			mp.setState("Y");
			mp.setType_id(Integer.parseInt(typeId));
			messagePublishDao.add(mp);                     //发布表
					
			
			for (int i = 0; i < jsonArray.length(); i++) {   //  科室id  科室名     
				List<MessageReceive> lists = new ArrayList<MessageReceive>();
				UserAuthority userAuthority=new UserAuthority();
				userAuthority.setOrga_id(Integer.parseInt(jsonArray.get(i).toString()));
				List<UserAuthority> authors=authorityDao.selectList(userAuthority);  //得到拥有所有在这个科室的人物
				if(authors.size()>0){
				for (int j = 0; j < authors.size(); j++) {
					MessageReceive mr =new MessageReceive();
					mr.setProgress_state(0);
					mr.setState("Y");
					mr.setMp_id(mpId);
					mr.setReceiver_auth_id(authors.get(j).getAuth_id());
					lists.add(mr);
				}
				messageReceiveDao.addMany(lists);            //接收表
			}else{
				UserAuthority userAuthority1=new UserAuthority();
				userAuthority1.setStu_type_id(Integer.parseInt(jsonArray.get(i).toString()));
				List<UserAuthority> authors1=authorityDao.selectList(userAuthority);  //得到拥有所有学生的人物
				if(authors1.size()>0){
				for (int j = 0; j < authors1.size(); j++) {
					MessageReceive mr =new MessageReceive();
					mr.setProgress_state(0);
					mr.setState("Y");
					mr.setMp_id(mpId);
					mr.setReceiver_auth_id(authors.get(j).getAuth_id());
					lists.add(mr);
				}
				messageReceiveDao.addMany(lists);  }
			}
				}
			return StringUtil.returns(true, "发送成功！");
		} else
			return StringUtil.returns(false, "发送失败！");
	}
	
	
}
