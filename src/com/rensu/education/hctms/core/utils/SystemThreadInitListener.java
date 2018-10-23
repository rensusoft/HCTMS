package com.rensu.education.hctms.core.utils;


import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.rensu.education.hctms.config.bean.SysConfig;
import com.rensu.education.hctms.teach.service.TrainPlanService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;

/**
 * 该类用于定义和启动线程
 * @author anz
 * @date 2015年8月12日
 *
 */
@Component
public class SystemThreadInitListener implements InitializingBean,
		ServletContextAware {
	
	@Autowired
	protected TrainPlanService trainPlanService;
	
	private ServletContext ctx = null;
	private static boolean destroyFlag = false;
	
	/*
	 * 讲座编排关联通知公告线程
	 */
	private class SystemThread extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			String runtime = "01:00:00";
			try {
				while (true) {
					@SuppressWarnings("unchecked")
					List<SysConfig> sysConfigList = (List<SysConfig>) ctx.getAttribute(Consts.SESSION_SYS_CONFIG);
					for(SysConfig sysConfig : sysConfigList){
						if(sysConfig.getConfig_code() != null && sysConfig.getConfig_code().equals("thread_start_time") && sysConfig.getConfig_flag() == 1){
							runtime = sysConfig.getConfig_data();
						}
					}
					boolean runflag = true;
//					SysConfig sc = new SysConfig();
//					sc.setConfig_code("THREAD_START_TIME_DAILY");
//					sc.setConfig_kind_code("COMMON_CONFIG");
//					sc = sysConfigService.selectList(sc).get(0);
//					if(!sc.getConfig_content().isEmpty()){
//						runtime = sc.getConfig_content().substring(0, 5);
//					}
					runtime = DateUtil.getCurrDateTime("yyyyMMdd") + runtime.replace(":", "");
//							+ "00";
//					System.out.println(runtime);
					long sleeplong = DateUtil.betweenTwoDayslong(runtime, DateUtil
							.getCurrDateTime());
					if (sleeplong < 0) {
						sleeplong = sleeplong + 24 * 60 * 60 * 1000;
					}
					System.out.println("--->距离线程开始运行时间：" + sleeplong + "毫秒");
					sleep(sleeplong);
					if (runflag && !destroyFlag) {
						System.out.println("--->线程开始运行时间："
								+ DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:dd"));
						try {
							/*以下为系统线程运行执行的方法*/
							//1、每天到达系统配置中的时间点后自动自行强制出科动作
							trainPlanService.trainOutDept(ctx);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
						sleep(1000);
						System.out.println("--->线程结束运行时间："
								+ DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:dd"));
					}
					if (destroyFlag) {
						System.out.println("--->线程销毁时间："
								+ DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:dd"));
						break;
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * 线程启动
	 */
	private void startSystemThread() {
		SystemThread st = new SystemThread();
		st.start();
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext ctx) {
		this.ctx = ctx;
		/*系统启动时调用的方法 开始*/
		/*系统启动时调用的方法 结束*/
		startSystemThread();
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

	}
	
}
