package com.sooncode.subassembly.pay.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

public class WeixinUtil {
    /**
     * 获取UUID
     * @return
     */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	 * 请求接口，已xml参数方式。
	 * @param urlStr 接口url地址
	 * @param xml xml格式的参数
	 * @return 接口返回的信息  异常时返回空字符串"".
	 */
	public static String post4xml(String urlStr, String xml){
		 
			try {
				URL url = new URL(urlStr);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();  
				connection.setDoOutput(true);  
				connection.setDoInput(true);  
				connection.setUseCaches(false);
				connection.setConnectTimeout(30000);
				connection.setReadTimeout(30000);
				connection.setRequestProperty("Content-type", "text/xml");
				connection.setRequestProperty("Charset", "UTF-8");
				connection.setRequestMethod("POST");  
				if (  xml != null) {  
					OutputStream outputStream = connection.getOutputStream();  
					outputStream.write(xml.getBytes());  
					outputStream.close();  
				}
				return getResponsMessage(connection);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}  
  
         
	}
	
	
	
	/**
	 * 获取签名  
	 * @param map 参加签名算法的字段
	 * @param apiKey API密钥
	 * @return
	 */
	public static String getSign(TreeMap<String,String> map,String apiKey){
		String string = "";
		for (Entry<String, String> en : map.entrySet()) {
			string = string + en.getKey() + "=" + en.getValue() + "&";
		}
		string = string + "key=" + apiKey;

		string = Md5.GetMD5Code(string).toUpperCase();
		return string;
	}
	
	private static String getResponsMessage(HttpsURLConnection connection){
		InputStream inputStream;
		try {
			inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
			String str = null;  
			StringBuffer buffer = new StringBuffer();  
			while ((str = bufferedReader.readLine()) != null) {  
				buffer.append(str);  
			}  
			bufferedReader.close();  
			inputStreamReader.close();  
			inputStream.close();  
			inputStream = null;  
			connection.disconnect();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}  
	}
	
	/**
     * token签名
     *
     * @param token
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String getSHA1(String token, String timestamp, String nonce) {
        try {
            String[] array = new String[] { token, timestamp, nonce};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
