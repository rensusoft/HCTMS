package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.score.bean.ScoFormMain;
import com.rensu.education.hctms.basicdata.bean.FormInfo;

import configuration.mapper.FormInfoMapper;

@Repository("formInfoDao")
public class FormInfoDao extends BaseDao<FormInfo> {
	
	Logger log = Logger.getLogger(FormInfoDao.class);
	
	@Autowired
	private FormInfoMapper<FormInfo> formInfoMapper;
	
	@Override
	public int add(FormInfo formInfo) {
		return formInfoMapper.add(formInfo);
	};
	
	@Override
	public int update(FormInfo formInfo) {
		return formInfoMapper.update(formInfo);
	};
	
	@Override
	public int delete(FormInfo formInfo) {
		return formInfoMapper.delete(formInfo);
	};
	
	@Override
	public FormInfo selectOne(int id) {
		return formInfoMapper.selectOne(id);
	};
	
	@Override
	public List<FormInfo> selectList(FormInfo formInfo) {
		return formInfoMapper.selectList(formInfo);
	};
	
	@Override
	public List<FormInfo> selectPage(RowBounds rowBounds, FormInfo formInfo) {
		return formInfoMapper.selectPage(rowBounds, formInfo);
	};
	
	@Override
	public int selectCount(FormInfo formInfo) {
		return formInfoMapper.selectCount(formInfo);
	};
	
	@Override
	public int getSequence() {
		return formInfoMapper.getSequence();
	}

	public List<FormInfo> selectFormInfo(FormInfo formInfo) {
		return formInfoMapper.selectFormInfo(formInfo);
	}

	public List<FormInfo> formRelation(FormInfo formInfo) {
		return formInfoMapper.formRelation(formInfo);
	}

	public List<FormInfo> getForm(RowBounds rowBounds, FormInfo formInfo) {
		return formInfoMapper.getForm(rowBounds,formInfo);
	}
	
	public FormInfo getTableContentFromSFM(ScoFormMain scoFormMain) {
		return formInfoMapper.getTableContentFromSFM(scoFormMain);
	}
	
	/**
	 * 从SCO_FORM_MAIN表获取分数和总分
	 * @param scoFormMain
	 * @return FormInfo
	 * @author guocc
	 * @date 2017年5月5日
	 */
	public FormInfo selectOneFromSFM(ScoFormMain scoFormMain) {
		return formInfoMapper.selectOneFromSFM(scoFormMain);
	}

	public FormInfo getTableContent(FormInfo formInfo) {
		
		return formInfoMapper.getTableContent(formInfo);
	}
	
}
