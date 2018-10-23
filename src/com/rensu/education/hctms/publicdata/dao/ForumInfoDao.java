package com.rensu.education.hctms.publicdata.dao;

import java.util.List;


import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.publicdata.bean.ForumInfo;
import com.rensu.education.hctms.publicdata.bean.ForumSub;

import configuration.mapper.ForumInfoMapper;

@Repository("forumInfoDao")
public class ForumInfoDao extends BaseDao<ForumInfo> {
	
	Logger log = Logger.getLogger(ForumInfoDao.class);
	
	@Autowired
	private ForumInfoMapper<ForumInfo> forumInfoMapper;
	
	@Override
	public int add(ForumInfo forumInfo) {
		return forumInfoMapper.add(forumInfo);
	};
	
	@Override
	public int update(ForumInfo forumInfo) {
		return forumInfoMapper.update(forumInfo);
	};
	
	@Override
	public int delete(ForumInfo forumInfo) {
		return forumInfoMapper.delete(forumInfo);
	};
	
	@Override
	public ForumInfo selectOne(int id) {
		return forumInfoMapper.selectOne(id);
	};
	
	@Override
	public List<ForumInfo> selectList(ForumInfo forumInfo) {
		return forumInfoMapper.selectList(forumInfo);
	};
	
	@Override
	public List<ForumInfo> selectPage(RowBounds rowBounds, ForumInfo forumInfo) {
		return forumInfoMapper.selectPage(rowBounds, forumInfo);
	};
	
	@Override
	public int selectCount(ForumInfo forumInfo) {
		return forumInfoMapper.selectCount(forumInfo);
	};
	
	@Override
	public int getSequence() {
		return forumInfoMapper.getSequence();
	}
	
	/***
     * 查询论坛交流信息列表
     * @param forumInfo
     * @return List<ForumInfo>
     * @author guocc
     * @date 2017年3月27日
     */
	public List<ForumInfo> selectForumsList(ForumInfo forumInfo) {
		return forumInfoMapper.selectForumsList(forumInfo);
	}
	
	/***
     * 查询论坛跟帖回复总条数
     * @param forumInfo
     * @return ForumInfo
     * @author guocc
     * @date 2017年3月29日
     */
	public ForumInfo selectReplyCount(ForumInfo forumInfo) {
		return forumInfoMapper.selectReplyCount(forumInfo);
	}
	
	/***
     * 根据ID查询论坛交流发帖信息
     * @param forumInfo
     * @return ForumInfo
     * @author guocc
     * @date 2017年3月29日
     */
	public ForumInfo selectForumsById(ForumInfo forumInfo) {
		return forumInfoMapper.selectForumsById(forumInfo);
	}
	
	/***
	 * 根据ID查询论坛交流回帖信息
	 * @param forumInfo
	 * @return List<ForumSub>
	 * @author guocc
	 * @date 2017年3月30日
	 */
	public List<ForumSub> selectReplyInfo(ForumInfo forumInfo) {
		List<ForumSub> forumSubList = forumInfoMapper.selectReplyInfo(forumInfo);
		return forumSubList;
	}  
	
	/***
	 * 根据ID查询论坛交流回帖引用信息
	 * @param forumSub
	 * @return ForumSub
	 * @author guocc
	 * @date 2017年3月30日
	 */
	public ForumSub selectCiteInfo(ForumSub forumSub) {
		return forumInfoMapper.selectCiteInfo(forumSub);
	}
	
}
