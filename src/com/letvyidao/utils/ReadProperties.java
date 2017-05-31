package com.letvyidao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadProperties {
//	private  static Logger logger = LoggerFactory.getLogger(ReadProperties.class);
	
	public static void main(String[] args) throws FileNotFoundException {
		ReadProperties r=new ReadProperties();
		String filepath=System.getProperty("user.dir")+"/dataFiles/protest.properties";
		String filename="config.properties";
		
		//读取属性文件
//		System.out.println("读取属性文件driver-apiphone:"+r.getProp(filepath, "driver-apiphone"));
//		System.out.println("读取属性文件lbsmailer:"+r.getProp(filepath, "lbsmailer"));
		System.out.println("读取属性文件config.properties:"+r.getProp(filename, "BmapPlaceCommandErrorPercentage"));
		System.out.println("读取属性文件lbsmailer:"+r.getProp(filename, "userDispatchOrder"));
				
		//将properties值写入文件
		try {
			//r.setProp("driver-apiphone", "13910147603,15101537885,13911483068", filepath);
			r.setProp("driver-apiphone", "13910147603,15101537885,13911483068", filename);
			//r.setProp("driver-apiphone", "13,15,18", filepath);
			System.out.println("driver-apiphone写入成功");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	
//获取properties文件值的方法,输入文件路径和key,输出value 
	public String getProp(String filename,String key) throws FileNotFoundException{
		Properties Prop = new Properties();
		try {
			String filepath=System.getProperty("user.dir")+"/"+filename;
			
			FileInputStream file=new FileInputStream(filepath);		
			Prop.load(file);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		String rs=Prop.getProperty(key);
		return rs;		
	}

	//设置propertis文件值，并写入文件   输入数据key,value,filepath，直接写入文件，没有返回值
	public static void  setProp(String key,String value,String filename) throws FileNotFoundException{
		Properties props=null;
		String filepath=System.getProperty("user.dir")+"/dataFiles/"+filename;
		File file=new File(filepath);
		if(!file.exists()){
			try {
				file.createNewFile();
				System.out.println("creat success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		//先读取属性文件，赋值给props
		try {
			FileInputStream fis=new FileInputStream(filepath);
			props=new Properties();
			props.load(fis);
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//通过props写入key value至pros中
		props.setProperty(key, value);
		//将props属性写入文件
		FileOutputStream fos=new FileOutputStream(filepath);
		try {
			props.store(fos, "update value");
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
}
