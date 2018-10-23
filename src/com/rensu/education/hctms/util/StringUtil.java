package com.rensu.education.hctms.util;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;


public class StringUtil {

	static Logger log = null;
	static {
		log = Logger.getLogger(StringUtil.class);
	}
	
	
	/**
	 * 过滤null及空字符串
	 * @param s
	 * @return
	 */
	public static String dnull(String s) {
		return s == null ? "" : s.trim().replace("NULL", "\"\"").replace("null", "\"\"");
	}
	
	/**
	 * 判断字符串是否为空！
	 * 字符串为null或者为多个空格时均按空字符串处理。
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.trim().length() == 0;
	}
	
	/**
	 * 判断字符串是否非空
	 * @param s
	 * @return
	 * @date 2015年9月21日
	 * @autor chen xiaoxiao
	 */
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}
	
	/**
	 * 通用接口返回格式。
	 * @param success  true：接口调用成功；false：接口调用失败。
	 * @param result 返回结果，一般是提示信息。
	 * @return map
	 */
	public static Map<String, Object> returns(boolean success, Object result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);
		map.put("data", result);
		return map;
	}
	
	/**
	 * 通用接口返回格式-返回String类型
	 * @param success
	 * @param result
	 * @return
	 * @date 2015年10月19日
	 * @autor chen xiaoxiao
	 */
	public static String returnStr(boolean success, String result) {
		JSONObject job = new JSONObject();
		try {
			job.put("success", success);
			job.put("data", result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return job.toString();
	}
	
	/***
	 * 
	 * @param success
	 * @param result
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
        String res = uuid.toString().replace("-", "");
		return res;
	}
	
	/**
	 * 打印输出所有请求参数
	 * @param req
	 * @author chen xiaoxiao 
	 * @date 2016-04-20
	 */
	public static void printRequestParams(HttpServletRequest req) {
//		Map<Object, Object> paraMap = req.getParameterMap();
//		Iterator<Object> keyItor = paraMap.keySet().iterator();
//		StringBuffer sb = new StringBuffer();
//		while(keyItor.hasNext()) {
//			Object _key = keyItor.next();
//			if (_key instanceof String) {
//				sb.append("[" + (String)_key + ":" + req.getParameter((String)_key) + "]");
//			}
//		}
		
		Map<String, String[]> paraMap = req.getParameterMap();
		Iterator<String> keyItor = paraMap.keySet().iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("---req=");
		while(keyItor.hasNext()) {
			String _key = keyItor.next();
			sb.append("[" + _key + ":" + req.getParameter(_key) + "]");
		}
		log.info(sb.toString());
	}
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 * @date 2015年8月12日
	 * @autor chen xiaoxiao
	 */
	public static String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	/***
	 * 根据数字得到中文数字
	 * @param a
	 * @return
	 * @date 2015年12月14日
	 * @autor yuan b
	 */
	public static String getChinaNum(int a){
		String num = "";
		if(a==1){
			num = "一";
		}else if(a==2){
			num = "二";
		}else if(a==3){
			num = "三";
		}else if(a==4){
			num = "四";
		}else if(a==5){
			num = "五";
		}else if(a==6){
			num = "六";
		}else if(a==7){
			num = "七";
		}else if(a==8){
			num = "八";
		}else if(a==9){
			num = "九";
		}else if(a==10){
			num = "十";
		}else if(a==11){
			num = "十一";
		}else if(a==12){
			num = "十二";
		}else if(a==13){
			num = "十三";
		}else if(a==14){
			num = "十四";
		}else if(a==15){
			num = "十五";
		}else if(a==16){
			num = "十六";
		}else if(a==17){
			num = "十七";
		}else if(a==18){
			num = "十八";
		}else if(a==19){
			num = "十九";
		}else if(a==20){
			num = "二十";
		}
		return num;
	}
	
	/**
	 * 模糊查询-不区分大小写的情况下相等匹配。
	 * @param source
	 * @param condition
	 * @return
	 * @date 2016年2月23日
	 * @autor chen xiaoxiao
	 */
	public static boolean equalsContains(String source, String condition) {
		if (isEmpty(source) || isEmpty(condition)) {
			return false;
		}
		
		return source.toUpperCase().equals(condition.toUpperCase());
	}
	
	/**
	 * 模糊查询-查询条件连续匹配.
	 * 不区分大小写
	 * @param source
	 * @param condition
	 * @return
	 * @date 2016年2月23日
	 * @autor chen xiaoxiao
	 */
	public static boolean seriateContains(String source, String condition) {
		return fullContains(source, condition);
	}
	
	/**
	 * 模糊查询-查询条件全匹配.
	 * 不区分大小写
	 * @param source 被查字符串
	 * @param condition 查询条件字符串
	 * @return 
	 * @date 2016年1月22日
	 * @autor chen xiaoxiao
	 */
	public static boolean fullContains(String source, String condition) {
		if (isEmpty(source) || isEmpty(condition)) {
			return false;
		}
		
		return source.toUpperCase().contains(condition.toUpperCase());
	}
	
	/**
	 * 模糊查询-查询条件分散模糊匹配.
	 * 不区分大小写
	 * 比如字符串abcdefgh中搜索abd，返回结果为true
	 * @param source
	 * @param condition
	 * @return
	 * @date 2016年1月22日
	 * @autor chen xiaoxiao
	 */
	public static boolean dispersiveContains(String source, String condition) {
		if (isEmpty(source) || isEmpty(condition)) {
			return false;
		}
		
		char[] c = condition.toUpperCase().toCharArray();
		StringBuffer sb = new StringBuffer(".*");
		for (int i = 0; i < c.length; i++) {
			sb.append(c[i] + ".*");
		}
		
		boolean res = source.toUpperCase().matches(sb.toString());

//		System.out.println("[" + source + ", " + condition + "][" + sb.toString() + "][" + res + "]");
		
		return res;
	}
	
	/**
	 * 两数相除，获取最大的整数。
	 * @param a
	 * @param b
	 * @return
	 * @date 2016年5月9日
	 * @autor chen xiaoxiao
	 */
	public static int getMaxInt(int a , int b) {
		double d = (double)a/b;
		int i = a / b;
		if (d > i) {
			i++;
		}
		
		return i;
	}
	
	/**
	 * 比较两个字符是否相等。
	 * 当任意一方为null或双方都为null时，返回false。
	 * @param s1
	 * @param s2
	 * @return
	 * @date 2016年5月10日
	 * @autor chen xiaoxiao
	 */
	public static boolean equals(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}
		
		return s1.equals(s2);
	}
	
	/*** 
     * MD5加密 生成32位md5码 速度比较快 性能高 安全强度比sha1要低 
     *  
     * @param 待加密字符串 
     * @return 返回32位md5码 
     */  
    public static String md5EncodeSignature(String inStr) throws Exception {  
        MessageDigest md5 = null;  
        try {  
            md5 = MessageDigest.getInstance("MD5");  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
  
        byte[] byteArray = inStr.getBytes("UTF-8");  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++) {  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16) {  
                hexValue.append("0");  
            }  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
    }  
	
	/*** 
     * SHA加密 生成40位SHA码 加密速度比md5慢 性能比md5低 安全强度比md5高 
     *  
     * @param 待加密字符串 
     * @return 返回40位SHA码 
     */  
    public static String shaEncodeSignature(String inStr) throws Exception {  
        MessageDigest sha = null;  
        try {  
            sha = MessageDigest.getInstance("SHA");  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
  
        byte[] byteArray = inStr.getBytes("UTF-8");  
        byte[] md5Bytes = sha.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++) {  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16) {  
                hexValue.append("0");  
            }  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
    } 
	
	
}
