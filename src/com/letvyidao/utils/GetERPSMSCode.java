package com.letvyidao.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.log4j.helpers.SyslogWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.letvyidao.inter.getUserApiToken;

public class GetERPSMSCode {

	
	public static String getSmsCode(String phonenumber){
		String code=null;
		GetERPSMSCode g=new GetERPSMSCode();
		
			String cookie=g.loginERP();
			System.out.println("cookie"+cookie);
			Document doc=null;
			try {
				doc =Jsoup.connect("https://testing.be.yongche.org/validatecode/")
				        .cookie("cookie", cookie) //设置cookie
				        .validateTLSCertificates(false)
				        .timeout(10000) //设置连接超时时间
				        .post();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //使用POST方法访问URL
			
			List list=new ArrayList<>();
			list=doc.getElementsByTag("tr");
			System.out.println(list.size());
			
//			for(int z=0;z<list.size();z++){
//				System.out.println("+++++"+list.get(z));
//			}
			
			for(int i=1;i<list.size();i++){
				String[] str=null;
				str=list.get(i).toString().split("<td>");
				String phone=str[1].replace("</td>", "").trim();
				code=str[2].replace("</td>", "").trim();
				
				if(phone.equals(phonenumber)){
					System.out.println(phone);
					System.out.println(code);
					break;
				}
			}

		return code;
	}
	
	public String loginERP(){
		//登录ERP测试环境
		Map<String,String> mapParams = new HashMap<String,String>();
		HashMap<String, String> headerParams = new HashMap<String, String>();
		String url="https://testing.sso.backend.yongche.org/auth/login";
		mapParams.put("app_id", "3");
		mapParams.put("cn", "E");
		mapParams.put("done", "https://testing.be.yongche.org/?done=/validatecode/");
		mapParams.put("login", "yaojing");
		mapParams.put("password", "Tf58228888");
		
		mapParams.put("cookies", "B=50qwkffubhu3tmti&1&s0.1b0403095");
		
	    Object rs= HttpUtils.getIntance().doSendPost(url, mapParams);
	    System.out.println("登录ERPresponse:"+rs.toString());
		Header [] headers =null; 
		headers=HttpUtils.getIntance().headers1;

		for(int j=0;j<headers.length;j++){
			System.out.println("登录ERP:header value:========"+headers[j]);
		}
		

		
	//	String cookie1="B=nve1smmfj69hn2t&1&ud;";
		String cookie1="";
		String cookie2="";
		String cookie3="";
		
		for(int i=0;i<headers.length;i++){
			if(headers[i].getValue().matches("B=.*")){				
				cookie1=headers[i].getValue().split(";")[0]+";";
			}else if(headers[i].getValue().matches("E_3=.*")){
				cookie2=headers[i].getValue().split(";")[0]+";";
			}else if(headers[i].getValue().matches("E_1=.*")){
				cookie3=headers[i].getValue().split(";")[0];
			}
		}
		String cookie=cookie1+cookie2+cookie3;

		return cookie;
		
	}
}
