//package com.letvyidao.utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.yaml.snakeyaml.Yaml;
//
//public class ReadYaml {
//
//	public void readYaml(){
//		 Yaml yaml = new Yaml();
//	      String filepath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\yd\\test\\yj\\login.yaml";
//	        File f = new File(filepath);
//	        try {
//	        	Map<?, ?> allParamsMap=new HashMap<>();
//				Map result= (Map) yaml.load(new FileInputStream(f));
//				System.out.println(result);
//				System.out.println(result.get("packageName"));
//				System.out.println(result.get("taskList").getClass().toString());
//							
//				ArrayList<?> alist=new ArrayList<Object>();
//				alist=(ArrayList<?>) result.get("taskList");
//				System.out.println("alist:"+alist);
//				System.out.println(alist.get(0).getClass().toString());
//				allParamsMap=(Map<?, ?>) alist.get(0);
//				System.out.println("paramsMap:"+allParamsMap.toString());
//				System.out.println(allParamsMap.get("headers").getClass());
//				
//				Map<?, ?> headerParamsMap=new HashMap<>();
//				Map<?,?> paramsMap=new HashMap<>();
//				
//				headerParamsMap=(Map<?, ?>) allParamsMap.get("headers");
//				System.out.println(headerParamsMap.get("cookie"));
//				System.out.println(headerParamsMap.get("User-Agent"));
//				
//				paramsMap=(Map<?, ?>) allParamsMap.get("params");
//				
//				System.out.println(paramsMap.toString());
//				
//				System.out.println(paramsMap.get("IMEI"));
//				System.out.println(paramsMap.get("userId"));
//				
//							
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
//}
