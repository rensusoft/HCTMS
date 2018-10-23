package com.rensu.education.hctms.message.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.message.bean.MessageReceive;
import com.rensu.education.hctms.message.dao.MessageReceiveDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("messageReceiveService")
public class MessageReceiveService extends BaseService<MessageReceive> {
	
	Logger log = Logger.getLogger(MessageReceiveService.class);
	
	@Autowired
	protected MessageReceiveDao messageReceiveDao;

	public Object getAllNewsByAuthId(int page, int rows, HttpServletRequest req) {
		//从缓存中拿到用户的auth_id
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer auth_id=loginBean.getvUserDetailInfo().getAuth_id();
		
		JSONObject jobj = new JSONObject();
		MessageReceive messageReceive=new MessageReceive();
		messageReceive.setReceiver_auth_id(auth_id);
		String propress_state=req.getParameter("progress_state");
		messageReceive.setState("Y");
		if(propress_state!=""&&propress_state!=null){
		messageReceive.setProgress_state(Integer.parseInt(propress_state));
		}
		int totalCount = messageReceiveDao.selectCount(messageReceive);
		
		String type_id=req.getParameter("type_id");
		if(type_id!=""&&type_id!=null){
			int typeId=Integer.parseInt(type_id);
			messageReceive.setType_id(typeId);
		}
		messageReceive.setState("Y");
		messageReceive.setOrderCondition(" p.send_time desc");
		List<MessageReceive> lists=messageReceiveDao.selectPageAll(new RowBounds((page-1)* rows,page* rows), messageReceive);
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

	public MessageReceive getMessagebyId(HttpServletRequest req) {
		int id=Integer.parseInt(req.getParameter("id"));   //接收表的id
		MessageReceive messageReceive1=new MessageReceive();
		messageReceive1.setId(id);
		List<MessageReceive> list=messageReceiveDao.selectPageAll(new RowBounds(0,1), messageReceive1);
		for (int i = 0; i <list.size(); i++) {
			messageReceive1=list.get(0);
			messageReceive1.setProgress_state(1);   //改变状态  已经查看过
			messageReceiveDao.update(messageReceive1);
		}
		return messageReceive1;
	}


	public int selectCountByLei(MessageReceive messageReceive) {	
		return messageReceiveDao.selectCountByLei(messageReceive);
	}

	/**
	 * 得到未读消息
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月17日
	 */
	public Object getTodo(HttpServletRequest req) {
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer authId=loginBean.getvUserDetailInfo().getAuth_id();  //得到当前用户的权限的id
		
		MessageReceive messageReceive=new MessageReceive();
		messageReceive.setProgress_state(0);    //未查看的信息
		messageReceive.setState("Y");
		messageReceive.setReceiver_auth_id(authId);
		messageReceive.setOrderCondition(" sendTimeStr  desc");		 
		List<MessageReceive> lists=messageReceiveDao.getTodo(messageReceive);
		return StringUtil.returns(true,lists);
	}
	
	/**
	 * 得到科教科首页的    消息块信息
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年4月17日
	 */
	public Object getNews(HttpServletRequest req) {
		LoginBean loginBean=(LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer authId=loginBean.getvUserDetailInfo().getAuth_id();  //得到当前用户的权限的id
		
		MessageReceive messageReceive=new MessageReceive();
		messageReceive.setProgress_state(0);    //未查看的信息
		messageReceive.setState("Y");
		messageReceive.setReceiver_auth_id(authId);
		messageReceive.setOrderCondition(" sendTimeStr  desc");		 
		List<MessageReceive> listMessageReceive=messageReceiveDao.getNews(messageReceive);
		return StringUtil.returns(true,listMessageReceive);
	}

	/**
	 * 消息中心全部已读操作
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年7月10日
	 */
	public Object readAllNews(HttpServletRequest req) {
			//从缓存中拿到用户的auth_id
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Integer auth_id=loginBean.getvUserDetailInfo().getAuth_id();
			MessageReceive messageReceive=new MessageReceive();
			messageReceive.setReceiver_auth_id(auth_id);
			messageReceive.setState("Y");
			messageReceive.setProgress_state(1);
			int num = messageReceiveDao.updateAllMsgStateByAuthId(messageReceive);
			if(num > 0){
				return StringUtil.returns(true, "操作成功...");
			}else{
				return StringUtil.returns(false, "操作失败！");
			}
	}

}
