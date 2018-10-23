package com.rensu.education.hctms.basicdata.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.basicdata.bean.HtmlTemplate;

import configuration.mapper.HtmlTemplateMapper;

@Repository("htmlTemplateDao")
public class HtmlTemplateDao extends BaseDao<HtmlTemplate> {
	
	Logger log = Logger.getLogger(HtmlTemplateDao.class);
	
	@Autowired
	private HtmlTemplateMapper<HtmlTemplate> htmlTemplateMapper;
	
	@Override
	public int add(HtmlTemplate htmlTemplate) {
		return htmlTemplateMapper.add(htmlTemplate);
	};
	
	@Override
	public int update(HtmlTemplate htmlTemplate) {
		return htmlTemplateMapper.update(htmlTemplate);
	};
	
	@Override
	public int delete(HtmlTemplate htmlTemplate) {
		return htmlTemplateMapper.delete(htmlTemplate);
	};
	
	@Override
	public HtmlTemplate selectOne(int id) {
		return htmlTemplateMapper.selectOne(id);
	};
	
	@Override
	public List<HtmlTemplate> selectList(HtmlTemplate htmlTemplate) {
		return htmlTemplateMapper.selectList(htmlTemplate);
	};
	
	@Override
	public List<HtmlTemplate> selectPage(RowBounds rowBounds, HtmlTemplate htmlTemplate) {
		return htmlTemplateMapper.selectPage(rowBounds, htmlTemplate);
	};
	
	@Override
	public int selectCount(HtmlTemplate htmlTemplate) {
		return htmlTemplateMapper.selectCount(htmlTemplate);
	};
	
	@Override
	public int getSequence() {
		return htmlTemplateMapper.getSequence();
	}
	
}
