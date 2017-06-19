package com.sooncode.subassembly.util;

import java.util.UUID;

public class GenerateUUIDUtil {
	
	/**
	 * 生成主键id.
	 * 
	 * @return uuid
	 */
	public static String createUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").toUpperCase();
	}
	/**
	* @Title: createIdForNumber 
	* @Description: 生成主键ID  LONG型 
	* @param @return    设定文件 
	* @return Long    返回类型 
	* @throws
	 */
	public static Long createIdForNumber(){
		return System.currentTimeMillis();
	}
	

}
