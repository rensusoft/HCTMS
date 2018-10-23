package com.rensu.education.hctms.teach.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.CathedraCondition;
import com.rensu.education.hctms.teach.bean.CathedraDetail;
import com.rensu.education.hctms.teach.bean.CathedraPlan;
import com.rensu.education.hctms.userauth.bean.StuClass;
import com.rensu.education.hctms.userauth.bean.StuType;

import configuration.mapper.CathedraPlanMapper;

@Repository("cathedraPlanDao")
public class CathedraPlanDao extends BaseDao<CathedraPlan> {
	
	Logger log = Logger.getLogger(CathedraPlanDao.class);
	
	@Autowired
	private CathedraPlanMapper<CathedraPlan> cathedraPlanMapper;
	
	@Override
	public int add(CathedraPlan cathedraPlan) {
		return cathedraPlanMapper.add(cathedraPlan);
	};
	
	@Override
	public int update(CathedraPlan cathedraPlan) {
		return cathedraPlanMapper.update(cathedraPlan);
	};
	
	@Override
	public int delete(CathedraPlan cathedraPlan) {
		return cathedraPlanMapper.delete(cathedraPlan);
	};
	
	@Override
	public CathedraPlan selectOne(int id) {
		return cathedraPlanMapper.selectOne(id);
	};
	
	@Override
	public List<CathedraPlan> selectList(CathedraPlan cathedraPlan) {
		return cathedraPlanMapper.selectList(cathedraPlan);
	};
	
	@Override
	public List<CathedraPlan> selectPage(RowBounds rowBounds, CathedraPlan cathedraPlan) {
		return cathedraPlanMapper.selectPage(rowBounds, cathedraPlan);
	};
	
	@Override
	public int selectCount(CathedraPlan cathedraPlan) {
		return cathedraPlanMapper.selectCount(cathedraPlan);
	};
	
	@Override
	public int getSequence() {
		return cathedraPlanMapper.getSequence();
	}
	/**
	 * 查询讲座编排
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月6日
	 */
	public List<CathedraPlan> selectCathedraPlan(CathedraPlan cathedraPlan){
		return cathedraPlanMapper.selectCathedraPlan(cathedraPlan);
	}
	//加载届次
	public List<StuClass> stuClassCheckbox(){
		return cathedraPlanMapper.stuClassCheckbox();
	};
	//加载学生类型
	public List<StuType> stuTypeCheckbox(){
		return cathedraPlanMapper.stuTypeCheckbox();
	};
	//加载条数
	public int countCathedraPlan(String dateTime){
		return cathedraPlanMapper.countCathedraPlan(dateTime);
	};
	//加载条数对应的date
	public Integer countCathedraPlanDate(String dateTime){
		return cathedraPlanMapper.countCathedraPlanDate(dateTime);
	};
	//查询一个讲座安排
	public String selectcathedraOnePlan(Integer id){
		return cathedraPlanMapper.selectcathedraOnePlan(id);
	};
	//查询左侧日期导航栏的日期
	public List<CathedraPlan> getCathDate(){
		return cathedraPlanMapper.getCathDate();
	};
	
	public List<CathedraPlan> selectCathedraNotice(CathedraPlan cathedraPlan){
		return cathedraPlanMapper.selectCathedraNotice(cathedraPlan);
	}
	
	public CathedraDetail getClassAndTypeByAuthId(Integer auth_id){
		return cathedraPlanMapper.getClassAndTypeByAuthId(auth_id);
	}

	public CathedraCondition selectCathedraCondition(
			CathedraCondition cathedraCondition) {
		
		return cathedraPlanMapper.selectCathedraCondition(cathedraCondition);
	}  
	
	public List<CathedraPlan> countCathedraNotice(CathedraPlan cathedraPlan){
		return cathedraPlanMapper.countCathedraNotice(cathedraPlan);
	};
}
