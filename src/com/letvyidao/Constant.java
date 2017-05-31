package com.letvyidao;

import java.io.FileNotFoundException;

import com.letvyidao.utils.ReadProperties;

public class Constant {
	
//	public static boolean online=true; // true--调用OnlineHost.properties
////	public boolean online=false;  // flase--调用OnlineHost.properties
//	public static String filename;
//	
//	public static ReadProperties readProperties=new ReadProperties();
//		
//
//	
//	public static String hostName;
//	public static String userApiHost; 
//	public static String categryHost;
//	public static String rebateHost;
//	public static String driverApiHost;
//	
//	public void initHost(){
//		
//		if(online){
//			filename="OnlineHost.properties";
//		}else{
//			filename="OfflineHost.properties";
//		}
//				
//		try {
//			hostName=readProperties.getProp(filename,"hostName");
//			userApiHost=readProperties.getProp(filename,"userApiHost");
//			categryHost=readProperties.getProp(filename,"categryHost");
//			rebateHost=readProperties.getProp(filename,"rebateHost");
//			driverApiHost=readProperties.getProp(filename,"driverApiHost");
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("参数读取错误，请检查");
//			
//		}	
//		
//		System.out.println(rebateHost);
//		System.out.println("userApiHost:========"+userApiHost);
//		
//		//creatOrder= userApiHost+"/order";
//	}
//	public static String creatOrder;
		
	public String hostName="https://appapi.yongche.com";
	public String userApiHost="https://ycagent.yongche.com"; 
	public String categryHost="http://opserver.yongche.org";
	public String rebateHost="https://h5.yongche.com";
	public String driverApiHost="http://driver-api.yongche.com";
	
	
//	public static String hostName="http://yongche-app-api2.yongche.org";//线下
//	public String hostName="https://appapi.yongche.com";  //线 上
	public String OAUTH2_TOKEN=hostName+"/oauth2/token.php";     
	public String ckd_orderAll = hostName+"/v7/order/all";
	public String ckd_v7Order = hostName+"/v7/order";
	public String ckd_v7OrderTrack = hostName+"/v7/order/track";
	public String ckd_v7ConfigApp = hostName+"/v7/config/app";
	public String ckd_v7PriceAll = hostName+"/v7/price/all";
	public String ckd_v7MapLocation = hostName+"/v7/map/location";
	public String ckd_v7MapLocationsearch = hostName+"/v7/map/locationsearch";
	public String ckd_v7MapLocationsuggest = hostName+"/v7/map/locationsuggest";
	public String ckd_v7UserAddressRecomend = hostName+"/v7/user/address/recommend";
	
		
	//LBS 接口地址
	private static String lbsprifix_around = "http://172.17.254.3:9978";
	private static String lbsprifix_assist = "http://172.17.254.18:9977";
    private static String lbsprifix_rgeo = "http://172.17.254.19:9979";
    private static String lbsprifix_estimate = "http://172.17.254.17:9976";
    
    private static String lbs_healthcheck1="http://172.17.254.17";
    private static String lbs_healthcheck2="http://172.17.254.18";
    private static String lbs_healthcheck3="http://172.17.254.19";
   
    public String lbs_around=lbsprifix_around+"/api/v1/around";
    public String lbs_place=lbsprifix_assist+"/api/v1/place";
    public String lbs_suggest=lbsprifix_assist+"/api/v1/suggest";
    public String lbs_poiPassback=lbsprifix_assist+"/api/v1/poi/passback";
   
    public String lbs_rgeo=lbsprifix_rgeo+"/api/v1/rgeo/rgeo";    
    public String lbs_estimateOrder=lbsprifix_estimate+"/api/v1/estimate/dispatch/order";
    public String lbs_estimateDriver=lbsprifix_estimate+"/api/v1/estimate/dispatch/driver";
    public String lbs_estimatebilling=lbsprifix_estimate+"/api/v1/estimate/billing";
    public String lbs_estimateDriverClient=lbsprifix_estimate+"/api/v1/estimate/driver/client";
    
    //LBS 高德    
    public String lbs_metrics1=lbs_healthcheck1+"/healthcheck/metrics";
    public String lbs_metrics2=lbs_healthcheck2+"/healthcheck/metrics";
    public String lbs_metrics3=lbs_healthcheck3+"/healthcheck/metrics";
            
	//策略工具线上地址
	//private static String categryHost="http://opserver.yongche.org";

    public String categry_findActivityInf = categryHost+"/findActivityInf.json?cityCode=bj&driverId=1";
    public String categry_findActivityRewards = categryHost+"/findActivityRewards.json?driverId=0&cityCode=bj&monitorStatus=1";
    public String categry_findActivityDetail = categryHost+"/findActivityDetail.json?driverId=0&cityCode=bj&activityId=0";
    public String categry_findHistoryActivityRewards = categryHost+"/findHistoryActivityRewards.json?driverId=0&cityCode=bj&page=0&size=5";
    public String categry_findRewardDetail = categryHost+"/findRewardDetail.json?driverId=0&cityCode=bj&activityId=0";
    public String categry_cacheURL = categryHost+"/findActivityRewards.json?driverId=0&cityCode=bj&monitorStatus=1";
    
    public String categry_dbURL ="http://172.17.60.11:8080/act/query/byCity.do?citys=bj";
    public String categry_isAlive="http://opsettlement.yongche.org/isAlive";  
    //司机端
    
    //private static String driverApiHost = "http://driver-api.yongche.com";    
	public String appdriver_GetAppealContent = driverApiHost+"/v4/Driver/GetAppealContent";
	public String appdriver_Appeal = driverApiHost+"/v4/Driver/Appeal";
	public String V6CreateAuthCode = driverApiHost+"/V6/CarMaster/CreateAuthCode";
	public String V6Login= driverApiHost+"/V6/CarMaster/Login";
	public String appdriver_VerifyCooperaStatus= driverApiHost+"/Driver/VerifyCooperaStatus";
	public String appdriver_accessToken=driverApiHost+"/oauth/accessToken";
	public String appdriver_CreateDriverPassword=driverApiHost+"/Driver/CreateDriverPassword";
	public String appdriver_CurrentVersion=driverApiHost+"/Global/CurrentVersion";
	public String appdriver_DriverMemberStat=driverApiHost+"/Driver/MemberStat";
	public String appdriver_DriverUnbind=driverApiHost+"/Driver/Unbind";
	public String appdriver_DriverGetIndex=driverApiHost+"/V5/Driver/GetIndex";
	public String appdriver_OrderGetItemOrder=driverApiHost+"/Order/GetItemOrder";
	public String appdriver_GetDriverIncomeOrder=driverApiHost+"/Driver/GetDriverIncomeOrder";
	public String appdriver_GetDriverIncome=driverApiHost+"/Driver/GetDriverIncome";
	public String appdriver_GetContributionOrder=driverApiHost+"/Driver/GetContributionOrder";
	public String appdriver_GetDriverAccount=driverApiHost+"/v4/Driver/GetDriverAccount";
	public String appdriver_member=driverApiHost+"/driver/member";
	public String appdriver_GetDriverLevelInfo=driverApiHost+"/V4/Driver/GetDriverLevelInfo";
	public String appdriver_getDriverLevelDetailMonth=driverApiHost+"/V4/Driver/getDriverLevelDetailMonth";
	public String appdriver_getDriverLevelDetailDay=driverApiHost+"/V4/Driver/getDriverLevelDetailDay";
	public String appdriver_WithdrawDriverCash=driverApiHost+"/Driver/WithdrawDriverCash";
	public String appdriver_GetDriverEvalute=driverApiHost+"/V1/Driver/GetDriverEvalute";
	public String appdriver_GetLowCarType=driverApiHost+"/V4/Driver/GetLowCarType";
	public String appdriver_SetCarType=driverApiHost+"/v4/Driver/SetCarType";
	public String appdriver_GetInBalanceList=driverApiHost+"/V4/Driver/GetInBalanceList";
	public String appdriver_GetDriverAccountDetail=driverApiHost+"/V2/Driver/GetDriverAccountDetail";
	
	
	//private static String  rebateHost = "https://h5.yongche.com";
	//充返
    public String api_getRebateIndex=rebateHost+"/ajax/Rechargerebate/getRebateIndex";
    public String api_getActiveInfo=rebateHost+"/ajax/Rechargerebate/getActiveInfo";
    public String api_getUserAmout=rebateHost+"/ajax/Rechargerebate/getUserAmout";
    public String api_completeInfo=rebateHost+"/ajax/Rechargerebate/completeInfo";
    public String api_complete=rebateHost+"/ajax/Rechargerebate/complete";
	    
//	UserAppApi 新接口
//	public String userApiHost="https://ycagent.yongche.com";  //线 上	
	
	public String oauth2_token=userApiHost+"/user/token";	
	public String orderEstimate=userApiHost+"/order/estimate";
	public String creatOrder= userApiHost+"/order";
	public String orderStatus=userApiHost+"/order/status";
	public String orderCancel=userApiHost+"/order/cancel";
	public String acceptcar=userApiHost+"/order/acceptcar";
	public String decisiondriver=userApiHost+"/order/decisiondriver";
	
	

	

}


