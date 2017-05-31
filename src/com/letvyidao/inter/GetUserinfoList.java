package com.letvyidao.inter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.letvyidao.utils.ReadProperties;

public class GetUserinfoList {

	static ArrayList<UserInfo> userList = new ArrayList<UserInfo>();
	static UserInfo userInfo;
	static ArrayList<ReUserInfo> reUserList = new ArrayList<ReUserInfo>();
	static ReUserInfo reUserInfo;
	static ArrayList<DriverInfo> driverList = new ArrayList<DriverInfo>();
	static DriverInfo driverInfo;
	
	public static ArrayList<UserInfo> getUserList(){

		String filepath=System.getProperty("user.dir")+"/userLoginList.dat";

		System.out.println(System.getProperty("user.dir"));
		File file = new File(filepath);

		BufferedReader breader =null;
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			breader = new BufferedReader(reader);
			
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = "";
		try {
			while ((line = breader.readLine()) != null) {
				userInfo = new UserInfo();				
			String[] values = line.split(",");
			userInfo.setImei(values[0]);
			userInfo.setUsername(values[1]);
			userInfo.setDevice_id(values[2]);	
			userInfo.setDeviceToken(values[3]);
			userInfo.setMacAddr(values[4]);
			
			userList.add(userInfo);
						
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
		
	}
	
	
	public static ArrayList<ReUserInfo> getReUserList(){
		ReadProperties r=new ReadProperties();
		String userListFile="";
		try {
			userListFile = r.getProp("config.properties", "userListFile").trim();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		String filepath=System.getProperty("user.dir")+"/"+userListFile;

		System.out.println(System.getProperty("user.dir"));
		File file = new File(filepath);

		BufferedReader breader =null;
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			breader = new BufferedReader(reader);
			
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = "";
		try {
			while ((line = breader.readLine()) != null) {
				reUserInfo = new ReUserInfo();				
			String[] values = line.split(",");
			reUserInfo.setDevice_Id(values[0]);
			reUserInfo.setUser_Id(values[1]);
			reUserInfo.setCell_Phone(values[2]);	

			
			reUserList.add(reUserInfo);
						
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reUserList;
		
	}
	
	
	public static ArrayList<DriverInfo> getDriverList(){
		ReadProperties r=new ReadProperties();
		String driverListFile="";
		try {
			driverListFile = r.getProp("config.properties", "driverListFile");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 
		String filepath=System.getProperty("user.dir")+"/"+driverListFile;

		System.out.println(System.getProperty("user.dir"));
		File file = new File(filepath);

		BufferedReader breader =null;
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			breader = new BufferedReader(reader);
			
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = "";
		try {
			while ((line = breader.readLine()) != null) {
				driverInfo = new DriverInfo();				
			String[] values = line.split(",");
			driverInfo.setDriver_Id(values[0]);
			driverInfo.setCell_Phone(values[1]);
			driverInfo.setImei(values[2]);	

			
			driverList.add(driverInfo);
						
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driverList;
		
	}
	public static void main(String[] args) {
		ArrayList<UserInfo> List=new ArrayList<UserInfo>();
		ArrayList<ReUserInfo> List2=new ArrayList<ReUserInfo>();
		ArrayList<DriverInfo> List3=new ArrayList<DriverInfo>();
		ArrayList<String> List4=new ArrayList<String>();
		
		
		List=getUserList();
		List2=getReUserList();
		List3=getDriverList();
//		System.out.println(List3.size());
//		System.out.println(List3.get(1).getDriver_Id());
		
//		for(int m=0;m<List3.size();m++){
//			List4.add(List3.get(m).getDriver_Id());
//		}
//		System.out.println(List4.size());
//		System.out.println(List4.get(2));
		
		System.out.println(driverList().size());
		System.out.println(driverList().get(7));
		
//		System.out.println(List.size());
//		System.out.println(List.get(1).getMacAddr());
//		
//		System.out.println(List2.size());
//		System.out.println(List2.get(2).getCell_Phone());
	}
	
	public static ArrayList<String> driverList(){
		ArrayList<DriverInfo> List3=new ArrayList<DriverInfo>();
		ArrayList<String> drlist=new ArrayList<String>();
		List3=getDriverList();
		for(int m=0;m<List3.size();m++){
			drlist.add(List3.get(m).getDriver_Id());
		}
		return drlist;
	}
}
