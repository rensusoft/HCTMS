package com.rensu.education.hctms.config.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.core.utils.SystemConfigInitListener;
import com.rensu.education.hctms.config.bean.SysConfig;
import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.SysConfigDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("sysConfigService")
public class SysConfigService extends BaseService<SysConfig> {
	
	Logger log = Logger.getLogger(SysConfigService.class);
	
	@Autowired
	protected SysConfigDao sysConfigDao;
	@Autowired
	private SystemConfigInitListener systemConfigInitListener;
	
	/***
	 * 查询系统开关配置
	 * @param req
	 * @return 
	 * @author guocc
	 * @date 2017年4月5日
	 */
	public Object selectSysConfig(HttpServletRequest req) {
		//获取页面的参数
		String availability = req.getParameter("availability");
		String config_type = req.getParameter("config_type");
		SysConfig sysConfig = new SysConfig();
		if(availability != null && !availability.equals("")){
			sysConfig.setAvailability(Integer.parseInt(availability));
		}
		if(config_type != null && !config_type.equals("")){
			sysConfig.setConfig_type(Integer.parseInt(config_type));
		}
		List<SysConfig> sysConfigList = sysConfigDao.selectSysConfig(sysConfig);
		return StringUtil.returns(true, sysConfigList);
	}
	
	/***
	 * 保存系统开关配置 
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年4月6日
	 */
	public Object saveSysConfig(HttpServletRequest req){
		//获取页面的参数
		String id = req.getParameter("id");
		String config_flag = req.getParameter("config_flag");
		String config_data = req.getParameter("config_data");
		String config_content = req.getParameter("config_content");
		SysConfig sysConfig = new SysConfig();
		if(id != null && !id.equals("")){
			sysConfig.setId(Integer.parseInt(id));;
		}
		if(config_flag != null && !config_flag.equals("")){
			sysConfig.setConfig_flag(Integer.parseInt(config_flag));
		}
		sysConfig.setConfig_data(config_data);
		sysConfig.setConfig_content(config_content);
		int num = sysConfigDao.update(sysConfig);
		if(num > 0){
			systemConfigInitListener.setServletContext(req.getSession().getServletContext());
			return StringUtil.returns(true, "操作成功！");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}

}
