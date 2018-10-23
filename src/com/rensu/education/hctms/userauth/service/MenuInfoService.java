package com.rensu.education.hctms.userauth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.userauth.bean.MenuInfo;
import com.rensu.education.hctms.userauth.dao.MenuInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;

/**
 * 菜单
 * @date 2016年4月20日
 * @autor chen xiaoxiao
 */
@Service("menuInfoService")
public class MenuInfoService extends BaseService<MenuInfo> {
	
	Logger log = Logger.getLogger(MenuInfoService.class);
	
	@Autowired
	protected MenuInfoDao menuInfoDao;

	/**
	 * 根据角色获取该角色对应的所有菜单。
	 * @param argMap {orderCondition: xx, roleId: xx}
	 * @return
	 * @date 2016年4月26日
	 * @autor chen xiaoxiao
	 */
	public List<MenuInfo> getMenusByRoleId(Integer roleId) {
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("roleId", roleId);
		argMap.put("orderCondition", " ordinal asc ");
		argMap.put("state", Consts.STATUS_Y);
		List<MenuInfo> menuList = menuInfoDao.getMenusByRoleId(argMap);
		List<MenuInfo> supMenuList = new ArrayList<MenuInfo>();
		Map<Integer, List<MenuInfo>> menuMap = new HashMap<Integer, List<MenuInfo>>();
		//遍历出所有一级菜单，并将二级菜单放到map中。
		for (MenuInfo _menu : menuList) {
			if (_menu.getSup_id() == 0) { //一级菜单
				supMenuList.add(_menu);
			} else if (_menu.getSup_id() > 0) { //二级菜单
				Integer _supId = _menu.getSup_id();
				List<MenuInfo> _menuList = menuMap.get(_supId);
				if (_menuList == null) {
					_menuList = new ArrayList<MenuInfo>();
					menuMap.put(_supId, _menuList);
				}
				_menuList.add(_menu);
			}
		}
		//将一级菜单和二级菜单整合到一起
		for (MenuInfo _menu : supMenuList) {
			List<MenuInfo> _subList = menuMap.get(_menu.getMenu_id());
			if (_subList != null && _subList.size() > 0) {
				_menu.setSubMenuList(_subList);
			}
		}
		return supMenuList;
	}
	
	/**
	 * 菜单保存操作：新增和修改。
	 * @param menu
	 * @return
	 * @date 2016年5月6日
	 * @autor chen xiaoxiao
	 */
	public boolean menuSave(MenuInfo menu, String action) {
		int count = 0;
		if (Consts.OPT_ADD.equals(action)) {
			count = menuInfoDao.add(menu);
		} else if (Consts.OPT_EDIT.equals(action)) {
			count = menuInfoDao.update(menu);
		} else {
			count = -1;
		}
		
		return count > 0;
	}
	
	/**
	 * 获取树形结构的menu列表。
	 * 列表中只包含一级菜单，二级菜单列表分别在各自menu的submenulist属性中。
	 * @param menu
	 * @return
	 * @date 2016年5月12日
	 * @autor chen xiaoxiao
	 */
	public List<MenuInfo> treeMenuList(MenuInfo menu) {
		List<MenuInfo> menuList = menuInfoDao.selectList(menu);
		List<MenuInfo> supMenuList = new ArrayList<MenuInfo>();
		Map<Integer, List<MenuInfo>> menuMap = new HashMap<Integer, List<MenuInfo>>();
		//遍历出所有一级菜单，并将二级菜单放到map中。
		for (MenuInfo _menu : menuList) {
			if (_menu.getSup_id() == 0) { //一级菜单
				supMenuList.add(_menu);
			} else if (_menu.getSup_id() > 0) { //二级菜单
				Integer _supId = _menu.getSup_id();
				List<MenuInfo> _menuList = menuMap.get(_supId);
				if (_menuList == null) {
					_menuList = new ArrayList<MenuInfo>();
					menuMap.put(_supId, _menuList);
				}
				_menuList.add(_menu);
			}
		}
		//将一级菜单和二级菜单整合到一起
		for (MenuInfo _menu : supMenuList) {
			_menu.setSubMenuList(menuMap.get(_menu.getMenu_id()));
		}
		
		return supMenuList;
	}
	
	public Map<String,Object> getSupMenuList(HttpServletRequest req){
		MenuInfo menu = new MenuInfo();
		menu.setState(Consts.STATUS_Y);
		menu.setOrderCondition("ordinal");
		List<MenuInfo> menuList = menuInfoDao.selectList(menu);
		List<MenuInfo> supMenuList = new ArrayList<MenuInfo>();
		Map<Integer, List<MenuInfo>> menuMap = new HashMap<Integer, List<MenuInfo>>();
		//遍历出所有一级菜单，并将二级菜单放到map中。
		for (MenuInfo _menu : menuList) {
			if (_menu.getSup_id() == 0) { //一级菜单
				supMenuList.add(_menu);
			} else if (_menu.getSup_id() > 0) { //二级菜单
				Integer _supId = _menu.getSup_id();
				List<MenuInfo> _menuList = menuMap.get(_supId);
				if (_menuList == null) {
					_menuList = new ArrayList<MenuInfo>();
					menuMap.put(_supId, _menuList);
				}
				_menuList.add(_menu);
			}
		}
		//将一级菜单和二级菜单整合到一起
		for (MenuInfo _menu : supMenuList) {
			_menu.setSubMenuList(menuMap.get(_menu.getMenu_id()));
		}
		return StringUtil.returns(true, supMenuList);
	}
	
	
}

