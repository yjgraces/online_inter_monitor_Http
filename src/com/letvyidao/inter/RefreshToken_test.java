package com.letvyidao.inter;

import java.util.HashMap;
import java.util.Map;

import com.letvyidao.utils.HttpUtils;

import net.sf.json.JSONObject;

public class RefreshToken_test {

	public static JSONObject jsonObj=new JSONObject();
	public String userRefreshToken(String user_Id,String device_Id,String cell_Phone){
	//	String url="http://auth.lan.yongche.com/Oauth2/setAccessToken";
	//	String url="http://test.testing.auth.yongche.org/?module=Oauth2&test#setAccessToken";
		String url="http://testing.auth.yongche.org/Oauth2/setAccessToken";
		String access_token = "";
		Map<String,String> param=new HashMap<String,String>();
		
//		param.put("client_id", "319bed0e5b94a54e35d42a4310b23227");
//		param.put("client_secret", "eb79957c9f59507798bf40a6dac12595");
		
		param.put("client_id", "test");
		param.put("client_secret", "test");
		param.put("grant_type", "password");
		param.put("scope", "user");
		param.put("user_id", user_Id);
		param.put("device_id", device_Id);
		param.put("app_id", "1");
		
		try{			
			Object rs=HttpUtils.getIntance().doSendGet(url, param);
		//	System.out.println("refreshtoken response:"+rs.toString());
			jsonObj = JSONObject.fromString(rs.toString());
			access_token = jsonObj.getJSONObject("result").getJSONObject("access_token").getString("token");	
			System.out.println(access_token+","+user_Id+","+cell_Phone);
			
		}catch(Exception e){
	    	e.printStackTrace();
	    	System.out.println(user_Id+","+device_Id+","+cell_Phone+"登录失败");
	    }		
		return access_token;						
	}
	
	public static void main(String[] args) {
		RefreshToken_test r=new RefreshToken_test();
		String user_Id="26036633";
		String device_Id="25048893167717181";
		String cell_Phone="16830180096";
		r.userRefreshToken(user_Id, device_Id, cell_Phone);
	}
}
