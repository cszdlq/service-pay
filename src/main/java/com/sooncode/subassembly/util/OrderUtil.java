package com.sooncode.subassembly.util;
 
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtil {
	
	private static int orderIndex = 0;
	private static Date thisDate = new Date();
	/**
	 * 生成订单号
	 * 
	 * @param preFixString
	 * @return
	 */
	public static String getOrderNumber(String preFixString) {

		
		Date nextDate = new Date();
		
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String currTime = outFormat.format(thisDate);
		 
		if (orderIndex > 0  && orderIndex < 999999) {//999999
			
			String thisDateString = new SimpleDateFormat("yyyyMMddHHmmss").format(thisDate);
			String nextDateString = new SimpleDateFormat("yyyyMMddHHmmss").format(thisDate);
			boolean minitesIsSame = ( thisDateString.equals(nextDateString) );
			
			if(minitesIsSame){
				orderIndex =  orderIndex += 1 ;
			}else{
				orderIndex = 1;
				thisDate = nextDate;
			}
			 
			 
		} else {
			orderIndex = 1;
			thisDate = nextDate;
		}
		String indexString = String.format("%s%06d", currTime, orderIndex);
		String orderNumberString = preFixString + indexString;
		return orderNumberString;
	}
	
	 
	
}
