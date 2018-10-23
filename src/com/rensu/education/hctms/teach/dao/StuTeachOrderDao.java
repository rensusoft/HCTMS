package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.StuTeachOrder;

import configuration.mapper.StuTeachOrderMapper;

@Repository("stuTeachOrderDao")
public class StuTeachOrderDao extends BaseDao<StuTeachOrder> {
	
	Logger log = Logger.getLogger(StuTeachOrderDao.class);
	
	@Autowired
	private StuTeachOrderMapper<StuTeachOrder> stuTeachOrderMapper;
	
	@Override
	public int add(StuTeachOrder stuTeachOrder) {
		return stuTeachOrderMapper.add(stuTeachOrder);
	};
	
	@Override
	public int update(StuTeachOrder stuTeachOrder) {
		return stuTeachOrderMapper.update(stuTeachOrder);
	};
	
	public int updateFinnish_num(StuTeachOrder stuTeachOrder) {
		return stuTeachOrderMapper.updateFinnish_num(stuTeachOrder);
	};
	
	@Override
	public int delete(StuTeachOrder stuTeachOrder) {
		return stuTeachOrderMapper.delete(stuTeachOrder);
	};
	
	@Override
	public StuTeachOrder selectOne(int id) {
		return stuTeachOrderMapper.selectOne(id);
	};
	
	@Override
	public List<StuTeachOrder> selectList(StuTeachOrder stuTeachOrder) {
		return stuTeachOrderMapper.selectList(stuTeachOrder);
	};
	
	@Override
	public List<StuTeachOrder> selectPage(RowBounds rowBounds, StuTeachOrder stuTeachOrder) {
		return stuTeachOrderMapper.selectPage(rowBounds, stuTeachOrder);
	};
	
	@Override
	public int selectCount(StuTeachOrder stuTeachOrder) {
		return stuTeachOrderMapper.selectCount(stuTeachOrder);
	};
	
	@Override
	public int getSequence() {
		return stuTeachOrderMapper.getSequence();
	}

	public StuTeachOrder selectReq_content(int id) {
		return stuTeachOrderMapper.selectReq_content(id);
	}
	public int insertList(List<StuTeachOrder> list) {
		return stuTeachOrderMapper.insertList(list);
	};
}
