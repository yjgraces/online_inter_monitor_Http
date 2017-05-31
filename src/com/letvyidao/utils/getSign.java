package com.letvyidao.utils;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class getSign {
	static InterfaceUtils interfaceUtils= new InterfaceUtils();		
	   /**
	    * 1.得到Nonce
	    * 2.key=value 按ascll码排序 
	    * 3.加nonce 加key=1bW4KASQPawc2G8DNBtIJmr35SzRcBCb
	    * 4.md5上页面串
	    * @throws NoSuchAlgorithmException 
	    */

		String key="1bW4KASQPawc2G8DNBtIJmr35SzRcBCb";	
		public static String md5Encypt(String encyptString){  //md5加密
			String md5String="";
			try {
				 MessageDigest  md = MessageDigest.getInstance("MD5");
				 md.update(encyptString.getBytes("utf-8"));
				  byte b[] = md.digest();
				  StringBuffer buf = new StringBuffer("");
				 int i=0; 
				  for (int offset = 0; offset < b.length; offset++) {
					  i = b[offset];
					  if (i < 0) 
						  i += 256;
					  if (i < 16)
						  buf.append("0");
					  buf.append(Integer.toHexString(i)); 
					  }
				  md5String=buf.toString().toUpperCase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  md5String;
		}
		 
		   
		 public static String encyptString(String paraString){    //param排序 加Nonce
			 String encyptStr="";
			 if(paraString.toLowerCase().contains("nonce")){
				 encyptStr=paraString;
			 }else{
				 encyptStr=paraString+"&nonce="+((System.currentTimeMillis()/1000)+"");
			 }			   
			 encyptStr=getSortParams(encyptStr);		
			 return encyptStr;
		 }
		 
	 
		 public static String getSortParams(String params) {  //param排序 
		        String result = "";
		        ArrayList<String> paramList = new ArrayList<>();	       
		            String[] paramArray = params.split("&");
		            if (paramArray.length > 0) {
		                for (int i = 0; i < paramArray.length; i++) {
		                    String param = paramArray[i];
		                    if (param.contains("=")) {
		                        String value = getElementFromArray(param.split("="), 1);	                       
		                            paramList.add(param);	                     
		                }
		                Collections.sort(paramList);
		            }

		            StringBuilder builder = new StringBuilder();
		            for (int i = 0; i < paramList.size(); i++) {
		                builder.append(paramList.get(i) + "&");
		            }
		            result = builder.toString();
		            if (paramList.size() > 0){
		                result = result.substring(0, result.length() - 1);   
		                }         
		            }
		        return result;
		    }
		 
		   public static <T> T getElementFromArray(T[] array, int index) {
		        if (isArrayEmpty(array)) {
		            return null;
		        }

		        if (index < 0 || index >= array.length) {
		            return null;
		        }

		        return array[index];
		    }
		   public static <T> boolean isArrayEmpty(T[] array) {
		        return array == null || array.length == 0;
		    }
		

	//获取 signkey	   
		   public static String getSignKey(Map<String,String> mapParams){
			   String paraString=interfaceUtils.doGetParamsStr(mapParams);
			   paraString=getSign.encyptString(paraString)+"&key=1bW4KASQPawc2G8DNBtIJmr35SzRcBCb";			   
			  String signkey= getSign.md5Encypt(paraString);
			return signkey;
			   			   
		   }
		   
		   public static String getSignPostParameter(Map<String,String> mapParams){
			   
			   String p=HttpUtils.getIntance().getPostParameter(mapParams);
			   String signParameter=getSign.encyptString(p);
			   
			return signParameter;
			   
		   }
	
}
