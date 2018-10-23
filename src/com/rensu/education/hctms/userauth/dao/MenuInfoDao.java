package com.rensu.education.hctms.userauth.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.MenuInfo;

import configuration.mapper.MenuInfoMapper;

/**
 * 菜单dao
 * @date 2016年4月20日
 * @autor chen xiaoxiao
 */
@Repository("menuInfoDao")
public class MenuInfoDao extends BaseDao<MenuInfo> {
	
	Logger log = Logger.getLogger(MenuInfoDao.class);
	
	@Autowired
	private MenuInfoMapper<MenuInfo> menuInfoMapper;
	
	@Override
	public int add(MenuInfo menuInfo) {
		return menuInfoMapper.add(menuInfo);
	};
	
	@Override
	public int update(MenuInfo menuInfo) {
		return menuInfoMapper.update(menuInfo);
	};
	
	@Override
	public int delete(MenuInfo menuInfo) {
		return menuInfoMapper.delete(menuInfo);
	};
	
	@Override
	public MenuInfo selectOne(int id) {
		return menuInfoMapper.selectOne(id);
	};
	
	@Override
	public List<MenuInfo> selectList(MenuInfo menuInfo) {
		return menuInfoMapper.selectList(menuInfo);
	};
	
	@Override
	public List<MenuInfo> selectPage(RowBounds rowBounds, MenuInfo menuInfo) {
		return menuInfoMapper.selectPage(rowBounds, menuInfo);
	};
	
	@Override
	public int selectCount(MenuInfo menuInfo) {
		return menuInfoMapper.selectCount(menuInfo);
	};
	
	@Override
	public int getSequence() {
		return menuInfoMapper.getSequence();
	}
	
	/**
	 * 根据角色获取该角色对应的所有菜单。
	 * @param argMap
	 * @return
	 * @date 2016年4月26日
	 * @autor chen xiaoxiao
	 */
	public List<MenuInfo> getMenusByRoleId(Map<String, Object> argMap) {
		return menuInfoMapper.getMenusByRoleId(argMap);
	}
	
}
