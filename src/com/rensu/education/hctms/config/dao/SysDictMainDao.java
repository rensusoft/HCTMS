package com.rensu.education.hctms.config.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.config.bean.SysDictMain;

import configuration.mapper.SysDictMainMapper;

@Repository("sysDictMainDao")
public class SysDictMainDao extends BaseDao<SysDictMain> {
	
	Logger log = Logger.getLogger(SysDictMainDao.class);
	
	@Autowired
	private SysDictMainMapper<SysDictMain> sysDictMainMapper;
	
	@Override
	public int add(SysDictMain sysDictMain) {
		return sysDictMainMapper.add(sysDictMain);
	};
	
	@Override
	public int update(SysDictMain sysDictMain) {
		return sysDictMainMapper.update(sysDictMain);
	};
	
	@Override
	public int delete(SysDictMain sysDictMain) {
		return sysDictMainMapper.delete(sysDictMain);
	};
	
	@Override
	public SysDictMain selectOne(int id) {
		return sysDictMainMapper.selectOne(id);
	};
	
	@Override
	public List<SysDictMain> selectList(SysDictMain sysDictMain) {
		return sysDictMainMapper.selectList(sysDictMain);
	};
	
	@Override
	public List<SysDictMain> selectPage(RowBounds rowBounds, SysDictMain sysDictMain) {
		return sysDictMainMapper.selectPage(rowBounds, sysDictMain);
	};
	
	@Override
	public int selectCount(SysDictMain sysDictMain) {
		return sysDictMainMapper.selectCount(sysDictMain);
	};
	
	@Override
	public int getSequence() {
		return sysDictMainMapper.getSequence();
	}
	
	public SysDictMain selectByitem_code(String item_code) {
		return sysDictMainMapper.selectByitem_code(item_code);
	};
}
