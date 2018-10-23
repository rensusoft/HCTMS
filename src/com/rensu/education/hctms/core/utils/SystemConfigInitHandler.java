/**
 * 
 */
package com.rensu.education.hctms.core.utils;


import java.util.List;

import javax.servlet.ServletContext;

import com.rensu.education.hctms.teach.service.TrainPlanService;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.StuClass;
import com.rensu.education.hctms.userauth.bean.StuType;
import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.basicdata.service.ProcessInfoService;
import com.rensu.education.hctms.config.bean.SysConfig;
import com.rensu.education.hctms.config.bean.SysDictMain;
import com.rensu.education.hctms.config.bean.SysDictSub;
import com.rensu.education.hctms.config.service.SysConfigService;
import com.rensu.education.hctms.config.service.SysDictMainService;
import com.rensu.education.hctms.config.service.SysDictSubService;
import com.rensu.education.hctms.userauth.bean.RoleInfo;
import com.rensu.education.hctms.userauth.service.OrgaInfoService;
import com.rensu.education.hctms.userauth.service.RoleInfoService;
import com.rensu.education.hctms.userauth.service.StuClassService;
import com.rensu.education.hctms.userauth.service.StuTypeService;
import com.rensu.education.hctms.util.Consts;


/**
 * 系统初始话具体实现类
 * @author hezx
 * @date 2017年1月10日
 *
 */
public class SystemConfigInitHandler {
	
	/***
	 * 向内存中写入系统开关sysConfig信息
	 * @param ctx
	 * @param sysConfigService
	 * @author guocc
	 * @date 2017年4月6日
	 */
	public static void initSysConfig(ServletContext ctx, SysConfigService sysConfigService) {
		try {
			//向内存中写入系统开关sysConfig信息
			SysConfig sysConfig = new SysConfig();
			sysConfig.setAvailability(1);
			List<SysConfig> sysConfigList = sysConfigService.selectList(sysConfig);
			if (ctx.getAttribute(Consts.SESSION_SYS_CONFIG) != null) {
				ctx.removeAttribute(Consts.SESSION_SYS_CONFIG);
			}
			ctx.setAttribute(Consts.SESSION_SYS_CONFIG, sysConfigList);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	/***
	 * 向内存中写入roleInfo信息 和  学生type
	 * @param ctx
	 * @param roleInfoService
	 * @author hezx
	 * @date 2017年1月10日
	 */
	public static void initRoleInfo(ServletContext ctx,	RoleInfoService roleInfoService,StuTypeService stuTypeService) {
		try {
			//向内存中写入roleInfo信息
			RoleInfo r = new RoleInfo();
			r.setState("Y");
			List<RoleInfo> roleInfoList = roleInfoService.selectList(r);
			if (ctx.getAttribute(Consts.SESSION_ROLE_INFO) != null) {
				ctx.removeAttribute(Consts.SESSION_ROLE_INFO);
			}
			// 学生type
			StuType stu = new StuType();
			stu.setState("Y");
			List<StuType> stuTypeList = stuTypeService.selectList(stu);
			if (ctx.getAttribute(Consts.SESSION_STU_TYPE) != null) {
				ctx.removeAttribute(Consts.SESSION_STU_TYPE);
			}
			ctx.setAttribute(Consts.SESSION_ROLE_INFO, roleInfoList);
			ctx.setAttribute(Consts.SESSION_STU_TYPE, stuTypeList);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
/**
 * 向内存中写入科室信息
 * @param ctx
 * @param orgaInfoService
 *@author huq
 *@date 2017年1月16日
 */
	public static void initOrgaList(ServletContext ctx,OrgaInfoService orgaInfoService) {
		try {
			List<OrgaInfo> orgaInfoList = orgaInfoService.selectList(new OrgaInfo());
			ctx.setAttribute(Consts.SESSION_ORGA_INFO, orgaInfoList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 初始化 system_dict_sub表信息,system_dict_main表数据暂未加载
	 * 数据加载后放在application对象中，key值为：Consts.SESSION_SYSTEM_DICT
	 */
	public static void initSysDict(ServletContext ctx, SysDictMainService sysDictMainService,SysDictSubService sysDictSubService) {
		List<SysDictSub> dictSubList = sysDictSubService.selectList(new SysDictSub());
		List<SysDictMain> dictMainList = sysDictMainService.selectList(new SysDictMain());
		if (ctx.getAttribute(Consts.SESSION_SYS_DICT_SUB) != null) {
			ctx.removeAttribute(Consts.SESSION_SYS_DICT_SUB);
		}
		if (ctx.getAttribute(Consts.SESSION_SYS_DICT_MAIN) != null) {
			ctx.removeAttribute(Consts.SESSION_SYS_DICT_MAIN);
		}
		ctx.setAttribute(Consts.SESSION_SYS_DICT_SUB, dictSubList);
		ctx.setAttribute(Consts.SESSION_SYS_DICT_MAIN, dictMainList);
	}
	/***
	 * 初始化流程配置表信息
	 * @param ctx
	 * @param processInfoService
	 * @author hezx
	 * @date 2017年2月6日
	 */
	public static void initProcessInfo(ServletContext ctx,ProcessInfoService processInfoService) {
		ProcessInfo processInfo	= new ProcessInfo();
		processInfo.setState("Y");
		List<ProcessInfo> processInfoList = processInfoService.selectList(processInfo);
		if (ctx.getAttribute(Consts.SESSION_PROCESS_INFO) != null) {
			ctx.removeAttribute(Consts.SESSION_PROCESS_INFO);
		}
		ctx.setAttribute(Consts.SESSION_PROCESS_INFO, processInfoList);
	}
	/***
	 * 初始化学生届次表
	 * @param ctx
	 * @param stuClassService
	 * @author hezx
	 * @date 2017年3月6日
	 */
	public static void initStuClass(ServletContext ctx,StuClassService stuClassService) {
		StuClass stuClass	= new StuClass();
		stuClass.setAvailable("Y");
		stuClass.setOrderCondition(" stu_class,id asc");
		List<StuClass> stuClassList = stuClassService.selectList(stuClass);
		if (ctx.getAttribute(Consts.STU_CLASS) != null) {
			ctx.removeAttribute(Consts.STU_CLASS);
		}
		ctx.setAttribute(Consts.STU_CLASS, stuClassList);
	}
	
	
	

}
