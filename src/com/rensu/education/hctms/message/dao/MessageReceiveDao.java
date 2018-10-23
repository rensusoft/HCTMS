package com.rensu.education.hctms.message.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.message.bean.MessageReceive;

import configuration.mapper.MessageReceiveMapper;

@Repository("messageReceiveDao")
public class MessageReceiveDao extends BaseDao<MessageReceive> {
	
	Logger log = Logger.getLogger(MessageReceiveDao.class);
	
	@Autowired
	private MessageReceiveMapper<MessageReceive> messageReceiveMapper;
	
	@Override
	public int add(MessageReceive messageReceive) {
		return messageReceiveMapper.add(messageReceive);
	};
	
	@Override
	public int update(MessageReceive messageReceive) {
		return messageReceiveMapper.update(messageReceive);
	};
	
	@Override
	public int delete(MessageReceive messageReceive) {
		return messageReceiveMapper.delete(messageReceive);
	};
	
	@Override
	public MessageReceive selectOne(int id) {
		return messageReceiveMapper.selectOne(id);
	};
	
	@Override
	public List<MessageReceive> selectList(MessageReceive messageReceive) {
		return messageReceiveMapper.selectList(messageReceive);
	};
	
	@Override
	public List<MessageReceive> selectPage(RowBounds rowBounds, MessageReceive messageReceive) {
		return messageReceiveMapper.selectPage(rowBounds, messageReceive);
	};
	
	@Override
	public int selectCount(MessageReceive messageReceive) {
		return messageReceiveMapper.selectCount(messageReceive);
	};
	
	@Override
	public int getSequence() {
		return messageReceiveMapper.getSequence();
	}

	public List<MessageReceive> selectPageAll(RowBounds rowBounds,
			MessageReceive messageReceive) {
		return messageReceiveMapper.selectPageAll(rowBounds, messageReceive);
	}

	public int selectCountByLei(MessageReceive messageReceive) {
		return messageReceiveMapper.selectCountByLei(messageReceive);
	}

	public int addMany(List<MessageReceive> lists) {
		return messageReceiveMapper.addMany(lists);
	}

	public List<MessageReceive> selectListAll(MessageReceive messageReceive) {
		return messageReceiveMapper.selectListAll(messageReceive);
	}

	public int updateState(MessageReceive messageReceive) {
		return messageReceiveMapper.updateState(messageReceive);
	}

	public List<MessageReceive> getTodo(MessageReceive messageReceive) {
		return messageReceiveMapper.getTodo(messageReceive);
	}
	
	/**
	 * 得到科教科首页的    消息块信息
	 * @param req
	 * @return
	 *@author guocc
	 *@date 2017年4月17日
	 */
	public List<MessageReceive> getNews(MessageReceive messageReceive) {
		return messageReceiveMapper.getNews(messageReceive);
	}

	public List<MessageReceive> selectNewsProgress_state0(
			MessageReceive messageReceive) {
		return messageReceiveMapper.selectNewsProgress_state0(messageReceive);
	}
	
	public int updateAllMsgStateByAuthId(MessageReceive messageReceive){
		return messageReceiveMapper.updateAllMsgStateByAuthId(messageReceive);
	}
	
}
