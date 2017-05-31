package com.letvyidao.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.letvyidao.inter.getUserApiToken;


public class JsoupParse {

	public void parseHtml(){

		GetERPSMSCode g=new GetERPSMSCode();
		String cookie=g.loginERP();
		Document doc=null;
		try {
			doc =Jsoup.connect("https://testing.be.yongche.org/validatecode/")
			        .cookie("cookie", cookie) //设置cookie
			        .validateTLSCertificates(false)
			        .timeout(3000) //设置连接超时时间
			        .post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //使用POST方法访问URL
		

		System.out.println(doc.getElementsByTag("tbody"));
		List list=new ArrayList<>();
		list=doc.getElementsByTag("tr");
		System.out.println(list.size());
		System.out.println(list.get(2));
		
		for(int i=1;i<list.size();i++){
			String[] str=null;
			str=list.get(i).toString().split("<td>");
			String phone=str[1].replace("</td>", "").trim();
			String code=str[2].replace("</td>", "").trim();
			
			if(phone.equals("16820170000")){
				System.out.println(phone);
				System.out.println(code);
				break;
			}
		}
		
		
	}
	
	
	public static void main(String[] args) {
		JsoupParse jp=new JsoupParse();
		jp.parseHtml();
	}
}
