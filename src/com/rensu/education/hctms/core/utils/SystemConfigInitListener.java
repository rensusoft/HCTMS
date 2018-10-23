/**
 * 
 */
package com.rensu.education.hctms.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.rensu.education.hctms.basicdata.service.ProcessInfoService;
import com.rensu.education.hctms.config.service.SysConfigService;
import com.rensu.education.hctms.config.service.SysDictMainService;
import com.rensu.education.hctms.config.service.SysDictSubService;
import com.rensu.education.hctms.teach.controller.TeachWebController;
import com.rensu.education.hctms.teach.service.TrainPlanService;
import com.rensu.education.hctms.userauth.service.OrgaInfoService;
import com.rensu.education.hctms.userauth.service.RoleInfoService;
import com.rensu.education.hctms.userauth.service.StuClassService;
import com.rensu.education.hctms.userauth.service.StuTypeService;


/**
 * 该类用于初始化系统配置信息等
 * @author anz
 * @date 2016年6月1日
 *
 */
@Component
public class SystemConfigInitListener implements InitializingBean,
		ServletContextAware {

	Logger log = Logger.getLogger(TeachWebController.class);
	
	@Autowired
	private RoleInfoService roleInfoService;	
	@Autowired
	private OrgaInfoService orgaInfoService;
	@Autowired
	private SysDictSubService sysDictSubService;
	@Autowired
	private SysDictMainService sysDictMainService;
	@Autowired
	private StuTypeService stuTypeService;
	@Autowired
	private ProcessInfoService processInfoService;
	@Autowired
	private StuClassService stuClassService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private TrainPlanService trainPlanService;
	
	private ServletContext ctx = null;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext ctx) {
		this.ctx = ctx;
		
		
//		//1、读取项目根目录下的文件，读取加密数据
//		String qrStr = "-1";
//		String path = this.getClass().getClassLoader().getResource("").getPath(); 
//		Properties prop = new Properties(); 
//		try{
//			//读取属性文件a.properties
//			InputStream in = new BufferedInputStream (new FileInputStream(path+"rensuKey.properties"));
//			prop.load(in);     ///加载属性列表
//			Iterator<String> it=prop.stringPropertyNames().iterator();
//			while(it.hasNext()){
//				qrStr = it.next();
//			}
//			in.close();
//		}catch(FileNotFoundException e){
//			log.error("----未找到许可文件！-----");
//			System.out.println("----未找到许可文件！-----");
//			e.printStackTrace();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		//2.进行加密处理
//		String rtn = "";
//		try {
//			//2、读取计算机机器码进行比较  
//            String cpuId = SystemConfigInitListener.getCPUSerial().trim();
//            String macId = SystemConfigInitListener.getLocalMac("127.0.0.1").replace("-", "").replace(":", "").trim();
//            //拆分
//            List<String> listCpu = new ArrayList<String>();
//            listCpu.add(cpuId.substring(0, 4));
//            listCpu.add(cpuId.substring(4, 8));
//            listCpu.add(cpuId.substring(8, 12));
//            listCpu.add(cpuId.substring(12, 16));
//            
//            List<String> listMac = new ArrayList<String>();
//            listMac.add(macId.substring(0, 1));
//            listMac.add(macId.substring(1, 6));
//            listMac.add(macId.substring(6, 7));
//            listMac.add(macId.substring(9, 12));
//            
//            String keyStr = listMac.get(2) + listCpu.get(3) + listMac.get(1) + listCpu.get(0)
//                    	  + listMac.get(0) + listCpu.get(1) + listMac.get(3) + listCpu.get(2);
//            
//            //组成秘钥（项目+机器码）
//            Mademd5 md5 = new Mademd5();
//            rtn = md5.toMd5("HCTMS_" + keyStr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		//进行KEY文件验证
//		if(StringUtil.equals(rtn,qrStr)){
//			log.info("----Key验证成功！");
			//角色ROLE_INFO信息 和 学生TYPE
			SystemConfigInitHandler.initRoleInfo(ctx, roleInfoService,stuTypeService);
					
			//初始化system_dict_main 和 system_dict_sub表信息
			SystemConfigInitHandler.initSysDict(ctx,sysDictMainService, sysDictSubService);
			
			//初始化流程配置表信息
			SystemConfigInitHandler.initProcessInfo(ctx,processInfoService);
			
			//学生届次表
			SystemConfigInitHandler.initStuClass(ctx,stuClassService);
			
			//机构列表
			SystemConfigInitHandler.initOrgaList(ctx,orgaInfoService);
			
			//系统开关配置信息
			SystemConfigInitHandler.initSysConfig(ctx,sysConfigService);
			
//		}else{
//			log.info("----Key验证失败.....");
//		}

	
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {

	}
	
	
	/**
     * 获取CPU序列号
     * 
     * @return
     */
    private static String getCPUSerial() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
 
            // + "    exit for  \r\n" + "Next";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        if (result.trim().length() < 1 || result == null) {
            result = "无CPU_ID被读取";
        }
        return result.trim();
    }
    
    /**
     * 根据IP地址获取mac地址
     * @param ipAddress 127.0.0.1
     * @return
     * @throws SocketException
     * @throws UnknownHostException
     */
    public static String getLocalMac(String ipAddress) throws SocketException,
        UnknownHostException {
      // TODO Auto-generated method stub
      String str = "";
      String macAddress = "";
      final String LOOPBACK_ADDRESS = "127.0.0.1";
      // 如果为127.0.0.1,则获取本地MAC地址。
      if (LOOPBACK_ADDRESS.equals(ipAddress)) {
        InetAddress inetAddress = InetAddress.getLocalHost();
        // 貌似此方法需要JDK1.6。
        byte[] mac = NetworkInterface.getByInetAddress(inetAddress)
            .getHardwareAddress();
        // 下面代码是把mac地址拼装成String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
          if (i != 0) {
            sb.append("-");
          }
          // mac[i] & 0xFF 是为了把byte转化为正整数
          String s = Integer.toHexString(mac[i] & 0xFF);
          sb.append(s.length() == 1 ? 0 + s : s);
        }
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        macAddress = sb.toString().trim().toUpperCase();
        return macAddress;
      } else {
        // 获取非本地IP的MAC地址
        try {
          System.out.println(ipAddress);
          Process p = Runtime.getRuntime()
              .exec("nbtstat -A " + ipAddress);
          System.out.println("===process=="+p);
          InputStreamReader ir = new InputStreamReader(p.getInputStream());
            
          BufferedReader br = new BufferedReader(ir);
          
          while ((str = br.readLine()) != null) {
            if(str.indexOf("MAC")>1){
              macAddress = str.substring(str.indexOf("MAC")+9, str.length());
              macAddress = macAddress.trim();
              System.out.println("macAddress:" + macAddress);
              break;
            }
          }
          p.destroy();
          br.close();
          ir.close();
        } catch (IOException ex) {
        }
        return macAddress;
      }
    }
	
}
