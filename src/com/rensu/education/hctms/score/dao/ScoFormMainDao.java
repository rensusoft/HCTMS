package com.rensu.education.hctms.score.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.MarksheetDetail;
import com.rensu.education.hctms.basicdata.bean.MarksheetSub;
import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.log.bean.OutdeptRecord;
import com.rensu.education.hctms.score.bean.ScoFormMain;

import configuration.mapper.ScoFormMainMapper;

@Repository("scoFormMainDao")
public class ScoFormMainDao extends BaseDao<ScoFormMain> {
	
	Logger log = Logger.getLogger(ScoFormMainDao.class);
	
	@Autowired
	private ScoFormMainMapper<ScoFormMain> scoFormMainMapper;
	
	@Override
	public int add(ScoFormMain scoFormMain) {
		return scoFormMainMapper.add(scoFormMain);
	};
	
	@Override
	public int update(ScoFormMain scoFormMain) {
		return scoFormMainMapper.update(scoFormMain);
	};
	
	@Override
	public int delete(ScoFormMain scoFormMain) {
		return scoFormMainMapper.delete(scoFormMain);
	};
	
	@Override
	public ScoFormMain selectOne(int id) {
		return scoFormMainMapper.selectOne(id);
	};
	
	@Override
	public List<ScoFormMain> selectList(ScoFormMain scoFormMain) {
		return scoFormMainMapper.selectList(scoFormMain);
	};
	
	@Override
	public List<ScoFormMain> selectPage(RowBounds rowBounds, ScoFormMain scoFormMain) {
		return scoFormMainMapper.selectPage(rowBounds, scoFormMain);
	};
	
	@Override
	public int selectCount(ScoFormMain scoFormMain) {
		return scoFormMainMapper.selectCount(scoFormMain);
	};
	
	@Override
	public int getSequence() {
		return scoFormMainMapper.getSequence();
	};
	
	public int delStuOldData(ScoFormMain scoFormMain){
		return scoFormMainMapper.delStuOldData(scoFormMain);
	}
	
	/**
	 * 根据form_id查询这条数据的id标识
	 * @param scoFormMain
	 * @return ScoFormMain
	 * @author guocc
	 * @date 2017年5月4日
	 */
	public ScoFormMain querySFMId(ScoFormMain scoFormMain) {
		return scoFormMainMapper.querySFMId(scoFormMain);
	}  
	
	/**
	 * 根据form_id查询评分表单子项的主项
	 * @param formInfo
	 * @return List<MarksheetSub>
	 * @author guocc
	 * @date 2017年5月4日
	 */
	public List<MarksheetSub> findMarksheetSubM(FormInfo formInfo) {
		return scoFormMainMapper.findMarksheetSubM(formInfo);
	}  
	
	/**
	 * 根据form_id查询评分表单子项
	 * @param id
	 * @return List<MarksheetDetail>
	 * @author guocc
	 * @date 2017年5月4日
	 */
	public List<MarksheetDetail> findMarksheetDetail(Integer id) {
		return scoFormMainMapper.findMarksheetDetail(id);
	}  
	
	/**
	 * 根据主项查询评分表单项的子项
	 * @param id
	 * @return List<MarksheetSub>
	 * @author guocc
	 * @date 2017年5月4日
	 */
	public List<MarksheetSub> findMarksheetSubS(Integer id) {
		return scoFormMainMapper.findMarksheetSubS(id);
	}  
	
	/**
	 * 根据form_id查询这条数据的id标识
	 * @param scoFormMain
	 * @return ScoFormMain
	 * @author guocc
	 * @date 2017年5月8日
	 */
	public ScoFormMain getSFMId(ScoFormMain scoFormMain) {
		return scoFormMainMapper.getSFMId(scoFormMain);
	}

	/**
	 * 根据pub_num查询这条数据的or_id
	 * @param outdeptRecord
	 * @return List<ScoFormMain>
	 * @author guocc
	 * @date 2017年5月18日
	 */
	public List<ScoFormMain> getOrIdByPubNum(OutdeptRecord outdeptRecord) {
		return scoFormMainMapper.getOrIdByPubNum(outdeptRecord);
	}

	/**
	 * 根据or_id查询表单数据
	 * @param scoFormMain
	 * @return ScoFormMain
	 * @author guocc
	 * @date 2017年5月18日
	 */
	public ScoFormMain selectByOrId(ScoFormMain scoFormMain) {
		return scoFormMainMapper.selectByOrId(scoFormMain);
	}
	
	public int updateState(ScoFormMain scoFormMain) {
		return scoFormMainMapper.updateState(scoFormMain);
	}
}
