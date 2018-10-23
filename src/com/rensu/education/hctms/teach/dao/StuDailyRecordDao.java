package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.StuDailyRecord;

import configuration.mapper.StuDailyRecordMapper;

@Repository("stuDailyRecordDao")
public class StuDailyRecordDao extends BaseDao<StuDailyRecord> {
	
	Logger log = Logger.getLogger(StuDailyRecordDao.class);
	
	@Autowired
	private StuDailyRecordMapper<StuDailyRecord> stuDailyRecordMapper;
	
	@Override
	public int add(StuDailyRecord stuDailyRecord) {
		return stuDailyRecordMapper.add(stuDailyRecord);
	};
	
	@Override
	public int update(StuDailyRecord stuDailyRecord) {
		return stuDailyRecordMapper.update(stuDailyRecord);
	};
	
	@Override
	public int delete(StuDailyRecord stuDailyRecord) {
		return stuDailyRecordMapper.delete(stuDailyRecord);
	};
	
	@Override
	public StuDailyRecord selectOne(int id) {
		return stuDailyRecordMapper.selectOne(id);
	};
	
	@Override
	public List<StuDailyRecord> selectList(StuDailyRecord stuDailyRecord) {
		return stuDailyRecordMapper.selectList(stuDailyRecord);
	};
	
	@Override
	public List<StuDailyRecord> selectPage(RowBounds rowBounds, StuDailyRecord stuDailyRecord) {
		return stuDailyRecordMapper.selectPage(rowBounds, stuDailyRecord);
	};
	
	@Override
	public int selectCount(StuDailyRecord stuDailyRecord) {
		return stuDailyRecordMapper.selectCount(stuDailyRecord);
	};
	
	@Override
	public int getSequence() {
		return stuDailyRecordMapper.getSequence();
	}
	/**
	 * 查询培训日志
	 * @param stuDailyRecord
	 * @param rowBounds
	 * @return List<StuDailyRecord>
	 * @author guocc
	 * @date 2017年2月28日
	 */
	public List<StuDailyRecord> selectDailyRecord(RowBounds rowBounds, StuDailyRecord stuDailyRecord) {
		return stuDailyRecordMapper.selectDailyRecord(rowBounds, stuDailyRecord);
	}

	public List<StuDailyRecord> selectReviewedDaily(RowBounds rowBounds, StuDailyRecord stuDailyRecord) {
		return stuDailyRecordMapper.selectReviewedDaily(rowBounds, stuDailyRecord);
	}

	/**
    * 查询个人培训日志
    * @param id
    * @author zhengc
    * @date 2017年5月26日
    */
	public  StuDailyRecord selectDailyRecordById(Integer id){
		return stuDailyRecordMapper.selectDailyRecordById(id);
	}	
	
	public int updateAllStuDRState(StuDailyRecord stuDailyRecord) {
		return stuDailyRecordMapper.updateAllStuDRState(stuDailyRecord);
	}
	
}
