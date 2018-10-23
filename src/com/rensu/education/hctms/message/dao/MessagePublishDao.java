package com.rensu.education.hctms.message.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.message.bean.MessagePublish;

import configuration.mapper.MessagePublishMapper;

@Repository("messagePublishDao")
public class MessagePublishDao extends BaseDao<MessagePublish> {
	
	Logger log = Logger.getLogger(MessagePublishDao.class);
	
	@Autowired
	private MessagePublishMapper<MessagePublish> messagePublishMapper;
	
	@Override
	public int add(MessagePublish messagePublish) {
		return messagePublishMapper.add(messagePublish);
	};
	
	@Override
	public int update(MessagePublish messagePublish) {
		return messagePublishMapper.update(messagePublish);
	};
	
	@Override
	public int delete(MessagePublish messagePublish) {
		return messagePublishMapper.delete(messagePublish);
	};
	
	@Override
	public MessagePublish selectOne(int id) {
		return messagePublishMapper.selectOne(id);
	};
	
	@Override
	public List<MessagePublish> selectList(MessagePublish messagePublish) {
		return messagePublishMapper.selectList(messagePublish);
	};
	
	@Override
	public List<MessagePublish> selectPage(RowBounds rowBounds, MessagePublish messagePublish) {
		return messagePublishMapper.selectPage(rowBounds, messagePublish);
	};
	
	@Override
	public int selectCount(MessagePublish messagePublish) {
		return messagePublishMapper.selectCount(messagePublish);
	};
	
	@Override
	public int getSequence() {
		return messagePublishMapper.getSequence();
	}

	public List<MessagePublish> selectPageAll(RowBounds rowBounds, MessagePublish messagePublish) {
		return messagePublishMapper.selectPageAll(rowBounds, messagePublish);
	};
	
}
