package com.letvyidao.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.letvyidao.AlarmInfos;
import com.letvyidao.utils.AlarmRecordUtil;
import com.letvyidao.utils.MailTest;

public class TestRetryAnalyzer implements IRetryAnalyzer {
	private  static Logger logger = LoggerFactory.getLogger(TestRetryAnalyzer.class); 
    private static MailTest sendMail = new MailTest(true);
    private static Properties alermInfoProerty = AlarmInfos.getAlarmInfoProperty();

    public String content = "[线上易到接口监控报警邮件]";
    public String content2 = "[线上易到接口监控报警短信]";
	

	public  int failTimes=0;
	public int currentTry = 1;
	
	
	
	
	@Override
    public synchronized boolean retry(ITestResult result) {
		
		String maxRetriesStr = result.getTestContext().getSuite().getParameter("maxRetries"); 
		int m_maxRetries = Integer.parseInt(maxRetriesStr);
		
	//	int m_maxRetries=3;
		System.out.println("result getMethodName打印:"+result.getMethod().getMethodName());
		System.out.println("yaojing 打印m_maxRetries:"+m_maxRetries);

//  	  if (currentTry < m_maxRetries){
//
//	       System.out.println(" FAILED, " + "Retrying " + currentTry + " time");
//	       currentTry++;	
//	       return true;
//       }else{
//    	   System.out.println("currentTry:===="+currentTry);
//    	   System.out.println("i am the end");
//      	   return false;
//       }
	
		
	    try {
  		 if ( result.isSuccess() ){
  			 AlarmRecordUtil.setAlarmRecordProperties(result.getName(),0);
  			 return false;
  	        }else{  	        
  	         failTimes=AlarmRecordUtil.getAlarmRecordProperties(result.getName());
  	         logger.info(result.getName()+"----failTime:"+failTimes);
  	     	 AlarmRecordUtil.setAlarmRecordProperties(result.getName(), failTimes+1);
			 if(failTimes==0){
				 currentTry =0;
			 }
			// if( failTimes>1&& failTimes<7){  
              if( failTimes>1&& failTimes<4){  
            	  System.out.println("失败，发送报警信息--yaojing,failtime="+failTimes);
             	  sendsms(result);
                  return false;
              }
              if(failTimes>=4){
             // if(failTimes>=7){
            	  System.out.println("error but on message");
            	 
            	  return false;
              }
        	  if (currentTry < m_maxRetries){
	               ++currentTry;	
	               Thread.sleep(10000);
	              System.out.println(" FAILED, " + "Retrying " + currentTry + " time");
	              return true;
	          }
  	        }    
        }catch(Exception e){
        	e.printStackTrace();
        }
		return false;

	}
	
//同一发送短信和邮件方式	
//	private void sendsms(ITestResult result){
//		try{
//			Throwable throwable = result.getThrowable();
//			  String methodName = " 报错方法："+result.getTestClass().getName()+"."+result.getName();
//			  String message =methodName+".用例监控接口："+result.getMethod().getDescription()+"  接口返回内容及调用信息："+ throwable.getMessage().replaceAll("\"", "'");
//			  logger.error(message);
//			  //String [] phones = {"13910147603,15101537885,13911483068"};
//			  String [] phones = {"13910147603"};
//			  HttpUtils.getIntance().request.service_uri="event/sendWarning";
//			   for(int i=0;i<phones.length;i++){
//				   HttpUtils.getIntance().request.data="{\"CELLPHONE\": \""+phones[i]+"\",\"CONTENT\": \""+(content+message)+"\",\"FLAG\": 14,\"__NO_ASSEMBLE\": 1,\"__EVENT_ID__\": 46}";
//			  String response = HttpUtils.getIntance().doPSFRequest("atm2");
//			   }
//			  sendMail.doSendHtmlEmail(content, message, "yaojing@yongche.com", null);
//		}catch(Exception e){
//			e.printStackTrace();
//		} 
//	}
	
	
	private void sendsms(ITestResult result){
		try{
			Date d=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String Ttime=df.format(d);
			Throwable throwable = result.getThrowable();
			String className = result.getTestClass().getName();
			  String methodName = " 报错方法名称："+className+"."+result.getName();
			  String message ="测试时间："+Ttime+" <br>"+
					 methodName+" <br>"+"用例监控接口："+result.getMethod().getDescription()+" <br> 接口返回内容及调用信息："+ throwable.getMessage().replaceAll("\"", "'");
			  String message2 ="测试时间："+Ttime+"##"+
						 methodName+" ##"+"用例监控接口："+result.getMethod().getDescription()+" ##> 接口返回内容及调用信息："+ throwable.getMessage().replaceAll("\"", "'");
			  
			  String phones = "";
			  String mailer = "";
			  if(className.matches(".*\\.AppApi.*")){
				phones = alermInfoProerty.getProperty("appapiphone");
				mailer = alermInfoProerty.getProperty("appapimailer");
			  }else if (className.matches(".*\\.Dispatch.*")){
				  phones = alermInfoProerty.getProperty("dispathphone");
				  mailer = alermInfoProerty.getProperty("dispathmailer");  
			  } else if( className.matches(".*\\.DriverInter.*")){
				  phones = alermInfoProerty.getProperty("driver-apiphone");
				  mailer = alermInfoProerty.getProperty("driver-apimailer");  
			  } else if( className.matches(".*\\.DriverApi.*")){
				  phones = alermInfoProerty.getProperty("driver-apiphone");
				  mailer = alermInfoProerty.getProperty("driver-apimailer"); 
			  }else if (className.matches(".*\\.LBS.*")){
				  phones = alermInfoProerty.getProperty("lbsphone");
				  mailer = alermInfoProerty.getProperty("lbsmailer");    
			  }else if (className.matches(".*\\.Order.*")){
				  phones = alermInfoProerty.getProperty("orderphone");
				  mailer = alermInfoProerty.getProperty("ordermailer");  
			  }else if (className.matches(".*\\.TestRetry.*")){
				  phones = alermInfoProerty.getProperty("testphone");
				  mailer = alermInfoProerty.getProperty("testmailer"); 
			  }else if (className.matches(".*\\.Merchant.*")){
				  phones = alermInfoProerty.getProperty("merchantphone");
				  mailer = alermInfoProerty.getProperty("merchantmailer"); 
			  }else if (className.matches(".*\\.Cate.*")){
				  phones = alermInfoProerty.getProperty("catephone");
				  mailer = alermInfoProerty.getProperty("catemailer");  
			 }
			 
			//  sendMail.doSendSMS(phones, content2+message2);
			  sendMail.doSendHtmlEmail(content, message, mailer, null);
			  
		}catch(Exception e){
			e.printStackTrace();
		} 
		
	}
	
}
