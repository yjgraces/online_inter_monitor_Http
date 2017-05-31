package com.letvyidao.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;

public class GetERPPassword {

	public static void main(String[] args) {
		GetERPPassword g=new GetERPPassword();
		System.out.println(g.getERPLoginPassord("16811122233"));
	}
	
	public String getERPLoginPassord(String cellphone){
		Map<String,String> mapParams = new HashMap<>();
		mapParams.put("app_id", "3");
		mapParams.put("cn", "E");
		mapParams.put("done", "https://platform.yongche.com/");
//		mapParams.put("login", "wangbingwei");
//		mapParams.put("password", "Password01!");
		
		mapParams.put("login", "yaojing");
		mapParams.put("password", "Tf58228888");
	     Object rs =HttpUtils.getIntance().doSendPost("https://sso.yongche.com/auth/login", mapParams);
		System.out.println("/auth/login:"+rs.toString());
		Header [] headers = HttpUtils.getIntance().headers1;
		Map<String,String> erpheaderParams = new HashMap<>();
		String cookie ="";
		String cookie1="";
		String cookie2="";
		for(int i=0;i<headers.length;i++){
			if(headers[i].getValue().matches("B=.*")){				
				cookie1=headers[i].getValue().split(";")[0]+";";
			}else if(headers[i].getValue().matches("E_3=.*")){
				cookie2=headers[i].getValue().split(";")[0];
			}
		}
		cookie=cookie1+cookie2;
		erpheaderParams.put("Upgrade-Insecure-Requests", "1");
		System.out.println("cookie:"+cookie);
		erpheaderParams.put("Cookie", cookie);
		rs = HttpUtils.getIntance().doSendGet("https://platform.yongche.com/driver/test?p="+cellphone, erpheaderParams);
		String password = rs.toString();
		
		String password0 = password.split(":")[1].trim();
		System.out.println("password0:"+password0.split(" ")[0].trim());
		
		System.out.println("driver/test:"+rs.toString());
		int start = password.indexOf(":");
		int endindex = password.indexOf("imei");
		System.out.println("start:"+start+",endindex:"+endindex);
		if(endindex==-1){
			password = password.split(":")[1].trim();
		}else{
			password = password.substring(start+1, endindex).trim();
		}
		System.out.println("password:"+password);
	    return password;
	}
}
