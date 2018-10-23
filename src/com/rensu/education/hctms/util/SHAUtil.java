/**
 * @author Chen Xiaoxiao
 * @date 2015年12月9日
 */
package com.rensu.education.hctms.util;

import java.security.MessageDigest;

/**
 * SHA加密算法实现。
 * 内容来自网络，网址：http://www.blogjava.net/amigoxie/archive/2014/06/01/414299.html
 * @author Chen Xiaoxiao
 * @date 2015年12月9日
 * 
 */
public class SHAUtil {
	/***
	 * SHA加密 生成40位SHA码
	 * 
	 * @param 待加密字符串
	 * @return 返回40位SHA码
	 * @author chen xiaoxiao 
	 * @date 2015-12-09
	 */
	public static String encode(String inStr) throws Exception {
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


	public static void main(String[] s) {
		String str = "123";
		try {
//			System.out.println(encode(str));
			System.out.println(encode(MD5Util.encode(str)));
			
			System.out.println(new String("adcd7048512e64b48da55b027577886ee5a36350").length());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
