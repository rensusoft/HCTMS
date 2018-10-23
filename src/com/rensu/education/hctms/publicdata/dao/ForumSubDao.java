package com.rensu.education.hctms.publicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.publicdata.bean.ForumSub;

import configuration.mapper.ForumSubMapper;

@Repository("forumSubDao")
public class ForumSubDao extends BaseDao<ForumSub> {
	
	Logger log = Logger.getLogger(ForumSubDao.class);
	
	@Autowired
	private ForumSubMapper<ForumSub> forumSubMapper;
	
	@Override
	public int add(ForumSub forumSub) {
		return forumSubMapper.add(forumSub);
	};
	
	@Override
	public int update(ForumSub forumSub) {
		return forumSubMapper.update(forumSub);
	};
	
	@Override
	public int delete(ForumSub forumSub) {
		return forumSubMapper.delete(forumSub);
	};
	
	@Override
	public ForumSub selectOne(int id) {
		return forumSubMapper.selectOne(id);
	};
	
	@Override
	public List<ForumSub> selectList(ForumSub forumSub) {
		return forumSubMapper.selectList(forumSub);
	};
	
	@Override
	public List<ForumSub> selectPage(RowBounds rowBounds, ForumSub forumSub) {
		return forumSubMapper.selectPage(rowBounds, forumSub);
	};
	
	@Override
	public int selectCount(ForumSub forumSub) {
		return forumSubMapper.selectCount(forumSub);
	};
	
	@Override
	public int getSequence() {
		return forumSubMapper.getSequence();
	}
	
	/***
     * 根据ID查询论坛交流被引用的回帖的信息
     * @param forumSub
     * @return ForumSub
     * @author guocc
     * @date 2017年3月30日
     */
	public ForumSub selectForumSubById(ForumSub forumSub) {
		return forumSubMapper.selectForumSubById(forumSub);
	}
}
