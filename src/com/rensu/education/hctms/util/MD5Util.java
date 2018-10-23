/**
 * @author Chen Xiaoxiao
 * @date 2015年12月9日
 */
package com.rensu.education.hctms.util;

import java.security.MessageDigest;

/**
 * md5加密实现。
 * 算法来源于网络，网址：http://www.blogjava.net/amigoxie/archive/2014/06/01/414299.html
 * 
 * @author Chen Xiaoxiao
 * @date 2015年12月9日
 */
public class MD5Util {

	/**
	 * md5加密
	 * @param inStr 加密前明文
	 * @return 加密后32位密文
	 * @throws Exception
	 * @date 2015年12月9日
	 * @autor chen xiaoxiao
	 */
	public static String encode(String inStr) throws Exception {
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
	
	
	public static void main(String[] s) {
		String str = "nanjingrensu";
		try {
			System.out.println(encode(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
