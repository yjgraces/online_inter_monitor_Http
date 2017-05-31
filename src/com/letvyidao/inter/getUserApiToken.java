package com.letvyidao.inter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

import com.letvyidao.Constant;
import com.letvyidao.utils.GetERPSMSCode;
import com.letvyidao.utils.HttpUtils;
import com.letvyidao.utils.getSign;
import net.sf.json.JSONObject;

public class getUserApiToken{
	public static JSONObject jsonObj=new JSONObject();
	public static Constant constant=new Constant();
	
	//获取司机token
	
	public static String initOauth2Token(){
		HashMap<String, String> mapParams = new HashMap<String, String>();
		mapParams.put("grant_type", "password");
		mapParams.put("username", "13391951392");
		mapParams.put("password", "qqqqqq");
		mapParams.put("device_token", "DuKAyFATsjcrIG6+GtnSFwYzmnOyXgpSd+h+jPiIit0S+zaK28Ie+oR7e72qGRnOc2BYGjR+UNanXoDWv4YCfkVg");
		mapParams.put("uuid", "861795031479572");
		mapParams.put("macaddress", "02:00:00:00:00:00");
		mapParams.put("imei", "861795031479572");
		mapParams.put("vdtype", "pwd");
		mapParams.put("brand_id", "SM-G9300");
		mapParams.put("serialno", "898600c00116f0016156");
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Authorization", "Basic YzU0NmU2YTlmYTI3N2UxMzk1NWYxMGUwYjBkZDljYjM6YmU1NDVmM2NlMzZlYThmYmI1ODYxMmQ3MmM0MjIyZGU=");   //登录接口认证用的BASIC空格 +" " 
		headMap.put("User-Agent", "aWeidao/7.2.0(C106;Android 6.0.1)");
		headMap.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
		Object resObject = HttpUtils.getIntance().doSendPost(constant.OAUTH2_TOKEN,mapParams, headMap);
	    jsonObj = JSONObject.fromString(resObject.toString());
	    String accessToken=jsonObj.getString("access_token");
	    System.out.println(resObject.toString());
	    return accessToken;
	    
	}
	
	//refresh token在线上机器才可以测试
	//user_Id="13508593", device_Id="24745914050575260"  cell_Phone="13391951392"  //乘客端线上测试账号，刷新token方式如下
	//user_Id="43276378",device_Id="1442181",cell_Phone="16871051234"
	public String userRefreshToken(String user_Id,String device_Id,String cell_Phone){
		String url="http://auth.lan.yongche.com/Oauth2/setAccessToken";
		String access_token = "";
		Map<String,String> param=new HashMap<String,String>();
		param.put("client_id", "319bed0e5b94a54e35d42a4310b23227");
		param.put("client_secret", "eb79957c9f59507798bf40a6dac12595");
		param.put("grant_type", "password");
		param.put("scope", "user");
		param.put("user_id", user_Id);
		param.put("device_id", device_Id);
		param.put("app_id", "1");
		
		try{			
			Object rs=HttpUtils.getIntance().doSendGet(url, param);
			jsonObj = JSONObject.fromString(rs.toString());
			access_token = jsonObj.getJSONObject("result").getJSONObject("access_token").getString("token");	
			System.out.println(access_token+","+user_Id+","+cell_Phone);
			
		}catch(Exception e){
	    	e.printStackTrace();
	    	System.out.println(user_Id+","+device_Id+","+cell_Phone+"登录失败");
	    }		
		return access_token;						
	}	
	
	// 新接口
	//@Test
	public void getToken(){
	//public static String getToken(){
		
		String url=constant.oauth2_token;
		HashMap<String, String> mapParams = new HashMap<String, String>();
		HashMap<String, String> headerParams = new HashMap<String, String>();
				
		mapParams.put("grant_type", "password");
		mapParams.put("username", "13391951392");
		mapParams.put("password", "qqqqqq");
		mapParams.put("uuid", "353222063475100");
		mapParams.put("imsi", "460012005313008");
		mapParams.put("macaddress", "02:00:00:00:00:00");
		mapParams.put("bluetooth_id", "02:00:00:00:00:00");
		mapParams.put("imei", "353222063475100");
		mapParams.put("vdtype", "pwd");
		mapParams.put("brand_id", "SM-G9300");
		mapParams.put("serialno", "02:00:00:00:00:00");
		mapParams.put("nonce",(System.currentTimeMillis()/1000)+"");
					
		String sign=getSign.getSignKey(mapParams);
	//	headerParams.put("Authorization", "Bearer a376788e639d6e062e9612c8a5029dc532e72367");	
		headerParams.put("Authorization", "Bearer 68baa1fd79024bf01329e98c201340405f5f2a18");		

		headerParams.put("User-Agent", "aWeidao/7.2.0(C106;Android 6.0.1)");
		headerParams.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		headerParams.put("sign",sign);
		
		Object rs = HttpUtils.getIntance().doSendPost(url,mapParams,headerParams);
		System.out.println("========"+rs.toString());
	    jsonObj = JSONObject.fromString(rs.toString());
	   // String accessToken=jsonObj.getString("access_token");
	   // System.out.println(rs.toString()+"======="+accessToken);
	//    return accessToken;
	    

	    
	}
	
	//测试环境
	public void getToken_testevn(UserInfo user){
			
		String url="https://testing-client-agent.yongche.org:443/user/token";

		HashMap<String, String> mapParams1 = new HashMap<String, String>();
		HashMap<String, String> headerParams1 = new HashMap<String, String>();	

		getUserApiToken get2=new getUserApiToken();
		

		String macAddr=user.getMacAddr();

		String imei=user.getImei();
		String device_id=user.getDevice_id();
	//	String macAddr="F1:11:11:FF:88:F1";
	//	String imei="353222069990001";
	//	String device_id="880242";
	//	String deviceToken="DuVRZUsGvkPRtcpnCM5tcdmyqsGn0+qe/geLZDz1LMnTqsSTQXjYnJtmbaeeh6UJ";
    //	String username="16830180001";
		String deviceToken=user.getDeviceToken();
	
		String username=user.getUsername();
	

		String loginToken=get2.getLoginToken(imei);
		get2.regcode(username,loginToken);
	    		
		String password=GetERPSMSCode.getSmsCode(username);
		
		mapParams1.put("grant_type", "password");
		mapParams1.put("imei", imei);
		mapParams1.put("bluetooth_id", macAddr);
		mapParams1.put("macaddress", macAddr);
		mapParams1.put("uuid", imei);
		mapParams1.put("serialno", macAddr);
		mapParams1.put("brand_id", "SM-G9300");
		mapParams1.put("nonce",(System.currentTimeMillis()/1000)+"");
		mapParams1.put("deviceToken", deviceToken);
	//	mapParams1.put("vdtype", "pwd");
	//	mapParams1.put("username", "16836123123");
	//	mapParams1.put("password", "123456");
		mapParams1.put("username", username);
		mapParams1.put("password", password);
		mapParams1.put("vdtype", "np");		
		mapParams1.put("devicesID", device_id); //device 表获取  和，device token对应
		
		String sign1=getSign.getSignKey(mapParams1);
		headerParams1.put("Authorization", "Bearer "+loginToken);	
		headerParams1.put("User-Agent", "aWeidao/7.3.3 (X900; Android 5.0.2)");
		headerParams1.put("sign", sign1);
		
		Object rs1 = HttpUtils.getIntance().doSendPost(url,mapParams1,headerParams1);
		jsonObj = JSONObject.fromString(rs1.toString());
	//	String actoken=jsonObj.getJSONObject("result").getString("access_token");
		System.out.println("++++++++"+rs1.toString());
	//	System.out.println("access_token:"+actoken);
			
			
		}
	
	
	public String getLoginToken(String imei){
		
		String url="https://testing-client-agent.yongche.org:443/user/token";
		HashMap<String, String> mapParams = new HashMap<String, String>();
		HashMap<String, String> headerParams = new HashMap<String, String>();	
	
		mapParams.put("grant_type", "client_credentials");
		mapParams.put("imei", imei);
		mapParams.put("bluetooth_id", "02:00:00:00:00:00");
		mapParams.put("macaddress", "02:00:00:00:00:00");
		mapParams.put("uuid", imei);
		mapParams.put("serialno", "02:00:00:00:00:00");
		mapParams.put("brand_id", "SM-G9300");
		mapParams.put("nonce",(System.currentTimeMillis()/1000)+"");
		String sign=getSign.getSignKey(mapParams);
		headerParams.put("Authorization", "Basic dGVzdDp0ZXN0");	
		headerParams.put("User-Agent", "aWeidao/7.3.3 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);
		
		Object rs = HttpUtils.getIntance().doSendPost(url,mapParams,headerParams);
		jsonObj = JSONObject.fromString(rs.toString());
	//	System.out.println("获取登录token:"+rs.toString());
		String loginToken=jsonObj.getJSONObject("result").getString("access_token");
		System.out.println("获取登录token:"+loginToken);
		return loginToken;
		
	}
	
	public void regcode(String cellphone,String loginToken){
		
		//获取验证码	
		String url="https://testing-client-agent.yongche.org:443/user/captcha/mobile";
		HashMap<String, String> mapParams = new HashMap<String, String>();
		HashMap<String, String> headerParams = new HashMap<String, String>();	
		
		mapParams.put("cellphone", cellphone);
		mapParams.put("type", "login");
		mapParams.put("nonce",(System.currentTimeMillis()/1000)+"");
		
		String sign=getSign.getSignKey(mapParams);
		headerParams.put("Authorization", "Bearer "+loginToken);	
		headerParams.put("User-Agent", "aWeidao/7.3.3 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);
		
		Object rs = HttpUtils.getIntance().doSendPost(url,mapParams,headerParams);
		System.out.println("发送短信验证码：==="+rs.toString());
		
	}
	
	
	public static void main(String[] args) {
		getUserApiToken g=new getUserApiToken();
		GetUserinfoList getuserinfo=new GetUserinfoList();
		ArrayList<UserInfo> List=new ArrayList<UserInfo>();
		List=getuserinfo.getUserList();
		for(int m=0;m<List.size();m++){
			UserInfo user=List.get(m);
			g.getToken_testevn(user);
		}
		
		
//		
//		String imei="353222069990006";
//		String username="16830180006";
//		
//	    String loginToken=g.getLoginToken(imei);
//		g.regcode(username,loginToken);
//		String vercode=GetERPSMSCode.getSmsCode(username);
		 
	//	g.getToken_testevn(user);
		
	}
}
