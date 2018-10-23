package com.rensu.education.hctms.log.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.log.bean.OutdeptRecord;

import configuration.mapper.OutdeptRecordMapper;

@Repository("outdeptRecordDao")
public class OutdeptRecordDao extends BaseDao<OutdeptRecord> {
	
	Logger log = Logger.getLogger(OutdeptRecordDao.class);
	
	@Autowired
	private OutdeptRecordMapper<OutdeptRecord> outdeptRecordMapper;
	
	@Override
	public int add(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.add(outdeptRecord);
	};
	
	@Override
	public int update(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.update(outdeptRecord);
	};
	
	@Override
	public int delete(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.delete(outdeptRecord);
	};
	
	@Override
	public OutdeptRecord selectOne(int id) {
		return outdeptRecordMapper.selectOne(id);
	};
	
	@Override
	public List<OutdeptRecord> selectList(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.selectList(outdeptRecord);
	};
	
	@Override
	public List<OutdeptRecord> selectPage(RowBounds rowBounds, OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.selectPage(rowBounds, outdeptRecord);
	};
	
	@Override
	public int selectCount(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.selectCount(outdeptRecord);
	};
	
	@Override
	public int getSequence() {
		return outdeptRecordMapper.getSequence();
	}

	public List<OutdeptRecord> selectFlowList(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.selectFlowList(outdeptRecord);
	}
	/**
	 * 查询学生在本科室的最后一条出科审批记录是否为重新发起审核状态0
	 * @param outdeptRecord
	 * @return OutdeptRecord
	 * @author guocc
	 * @date 2017年5月15日
	 */
	public OutdeptRecord selectExamResult(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.selectExamResult(outdeptRecord);
	}
	/**
	 * 获取出科审核记录的组次
	 * @param outdeptRecord
	 * @return List<OutdeptRecord>
	 * @author guocc
	 * @date 2017年5月15日
	 */
	public List<OutdeptRecord> getGroups(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.getGroups(outdeptRecord);
	}
	/**
	 * 获取在本科室学生最后一次发起出科的组次
	 * @param outdeptRecord
	 * @return OutdeptRecord
	 * @author guocc
	 * @date 2017年5月12日
	 */
	public OutdeptRecord getLastPubNum(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.getLastPubNum(outdeptRecord);
	}

	/**
	 * 获取学生在本科室当前的出科组次
	 * @param outdeptRecord
	 * @return OutdeptRecord
	 * @author guocc
	 * @date 2017年5月12日
	 */
	public OutdeptRecord getPubNum(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.getPubNum(outdeptRecord);
	}  
	
	/**
	 * 查询学生的出科审核轨迹
	 * @param outdeptRecord
	 * @return
	 * @author guocc
	 * @date 2017年8月30日
	 */
	public List<OutdeptRecord> selectOrList(OutdeptRecord outdeptRecord) {
		return outdeptRecordMapper.selectOrList(outdeptRecord);
	}
}