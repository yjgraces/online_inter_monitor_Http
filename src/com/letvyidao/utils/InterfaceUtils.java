package com.letvyidao.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.letvyidao.BaseRequest;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class InterfaceUtils{
private  static Logger logger = LoggerFactory.getLogger(InterfaceUtils.class); 	
private JSONObject jsonObj = null;
public  String doGetParamsStr(Map<String, String> params) {
	StringBuffer sb = new StringBuffer();
	for (Map.Entry<String, String> entry : params.entrySet()) {
		if (!"".equals(entry.getValue()) && !"null".equals(entry.getValue())) {
			sb.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
	}
	return sb.substring(0, sb.length()-1);
}
public String  decode(String decodeStr){
	String decoders =  URLDecoder.decode(decodeStr);
	return decoders;
}

public void wait(int millsecond){
	try{
		Thread.sleep(millsecond);
	}catch(Exception e){
		e.printStackTrace();
	}
}

public String  encode(String encodeStr){
	String encoders =  URLEncoder.encode(encodeStr);
	return encoders;
}

//
//public Map<String,String> createAnOrder(){
//	 JSONObject jsonObj = null;
//	 Map<String,String> orderParams = null;
//	 String orderId = "";
//	try{
//		//测试环境
//		 //  String [] uids = {"13025137","13025135","13025134"};
//		 //  String [] phones={"16809340982","16820161007","16899000094"};
//		//线上环境，uid和phones
//		    String [] uids = {"43276378","43277106","43277118"};
//		    String [] phones={"16871051234","16871051235","16871051236"};
//				JSONArray car_list = null;
//			     boolean acceptRS = false;
//			     int carNumbr = 0;
//				for(int i=0;i<3;i++){
//					HttpUtils.getIntance().request.data="";
//					 String startTime = (System.currentTimeMillis()/1000+600)+"";
//					 HttpUtils.getIntance().request.service_uri = "state/createOrder?user_id="+uids[i]+"&corporate_id=0&passenger_phone="+phones[i]+"&passenger_name=testautuopassager&passenger_number=1&city=hlbe&product_type_id=1&fixed_product_id=0&car_type_id=3&car_type_ids=3&source=20000001&expect_start_time="+(System.currentTimeMillis()/1000+600)+"&in_coord_type=baidu&expect_end_latitude=36.9021&expect_end_longitude=100.1521&expect_start_latitude=36.9022&expect_start_longitude=100.1522&start_position=testaddr&start_address=testaddr&end_position=testaddr&end_address=testaddr&flight_number=0&is_asap=1&app_version=iWeidao/6.2.5 D/877035&media_id=1&sms=passenger&time_span=0&has_custom_decision=1&is_need_manual_dispatch=0&is_auto_dispatch=1&estimate_price=0&device_id=0&corporate_dept_id=0&estimate_price=100.0&estimate_info=D123,T3700&flag=2&create_order_longitude=36.9022&create_order_latitude=36.9022&ip=10.1.7.202&order_port=60428&dispatch_type=2&time_length=1800";
//					 String response =HttpUtils.getIntance().doPSFRequest("order");
//				    System.out.println("----"+response);
//				    jsonObj = JSONObject.fromString(response);		    
//				   orderId = jsonObj.getJSONObject("result").getString("service_order_id");		    
//				   HttpUtils.getIntance().request.data = "";
//				    String params = "order_id="+orderId+"&out_coord_type=baidu&filter_driver_ids=0&count=5";
//				    System.out.println("orderid:"+params);
//				    //获取接单司机列表
//				    HttpUtils.getIntance().request.service_uri = "Dispatch/getAcceptCars?"+params;
//					 for(int j=0;j<10;j++){
//						 Thread.sleep(3000); 
//						 response =HttpUtils.getIntance().doPSFRequest("dispatch");
//					     System.out.println(response.toString());
//					     jsonObj = JSONObject.fromString(response);
//					     if(jsonObj.getInt("ret_code")==498){
//					    	 break;
//					     }else if(jsonObj.getInt("ret_code")==200){
//					    	 carNumbr = jsonObj.getJSONArray("car_list").length();
//						     System.out.println("carNumbr"+carNumbr);
//					    	 if(carNumbr>0){
//					    		 car_list = jsonObj.getJSONArray("car_list");
//					    		 orderParams = new HashMap<String,String>();
//					    		 orderParams.put("order_id", orderId);
//					    		 String car_id = car_list.getJSONObject(0).getString("car_id");;
//								 String driverId= car_list.getJSONObject(0).getString("driver_id");
//								 orderParams.put("driverId", driverId);
//								 orderParams.put("car_id", car_id);
//								 orderParams.put("startTime", startTime);
//								 System.out.println("order_id:"+orderId+",--car_id:"+car_id);
//								 HttpUtils.getIntance().request.data = "";
//								String param="service_order_id="+orderId+"&driver_id="+driverId+"&coupon_member_id=0&third_party_coupon=0";
//							    System.out.println("userDecision:"+param); 
//							    HttpUtils.getIntance().request.service_uri = "Dispatch/userDecision?"+param;	
//							    HttpUtils.getIntance().doPSFRequest("dispatch");		
//							     acceptRS=true;
//						    	 break;	 
//					    	 }
//					     }
//					 }	
//					 //如果司机抢单成功，则跳出循环，否则则再次创建订单，重复创建订单3次还抢单抢单失败则报警
//					 if(acceptRS){
//						 break;
//					 }
//			}
//   }catch(Exception e){
//	   e.printStackTrace();
//   }
//	return orderParams;
// }

@Test(description="创建用户决策订单")
public  Map<String,String> createAnOrder(){
	Map<String,String> userOrderParams= new HashMap<String,String>();
	
	String orderId = "";
	String errorMsg = "";
	String driverId="";
	String carId="";
	String startTime = (System.currentTimeMillis()/1000+600)+"";
	try{
       //测试环境, uids需要找一个用户端的数据和手机号，因为害怕和我们的账号冲突，所以就不给你了
//		String [] uids = {"13025137","13025135","13025134"};
//		String [] phones={"16809340982","16820161007","16899000094"};
//线上环境16871051234（43276378）、16871051235（43277106）、16871051236（43277118）
		String [] uids = {"43276378","43277106","43277118"};
		String [] phones={"16871051234","16871051235","16871051236"};
		JSONArray car_list = null;
	
	     boolean acceptRS = false;
	     int carNumbr = 0;
		for(int i=0;i<1;i++){
			HttpUtils.getIntance().request.data="";
			HttpUtils.getIntance().request.service_uri = "state/createOrder?user_id="+uids[i]+"&corporate_id=0&passenger_phone="+phones[i]+"&passenger_name=testpassager&passenger_number=1&city=hlbe&product_type_id=1&fixed_product_id=0&car_type_id=3&car_type_ids=3&source=20000001&expect_start_time="+(System.currentTimeMillis()/1000+600)+"&in_coord_type=baidu&expect_end_latitude=36.9021&expect_end_longitude=100.1521&expect_start_latitude=36.9022&expect_start_longitude=100.1522&start_position=testaddr&start_address=testaddr&end_position=testaddr&end_address=testaddr&flight_number=0&is_asap=1&app_version=iWeidao/6.2.5 D/877035&media_id=1&sms=passenger&time_span=0&has_custom_decision=1&is_need_manual_dispatch=0&is_auto_dispatch=1&estimate_price=0&device_id=0&corporate_dept_id=0&estimate_price=100.0&estimate_info=D123,T3700&flag=2&create_order_longitude=36.9022&create_order_latitude=36.9022&ip=10.1.7.202&order_port=60428&dispatch_type=2&time_length=1800";
			String response = HttpUtils.getIntance().doPSFRequest("order");
		    System.out.println("createOrder返回结果:"+response+"request.uri:"+HttpUtils.getIntance().request.service_uri);
		    jsonObj = JSONObject.fromString(response);	
		    orderId = jsonObj.getJSONObject("result").getString("service_order_id");
		    System.out.println("订单ID:"+orderId);
		    //获取接单司机信息
		    HttpUtils.getIntance().request.data = "";
		    String params = "order_id="+orderId+"&out_coord_type=baidu&filter_driver_ids=0&count=5";
		    HttpUtils.getIntance().request.service_uri = "Dispatch/getAcceptCars?"+params;
			 for(int j=0;j<10;j++){
				 Thread.sleep(3000); 
				  response = HttpUtils.getIntance().doPSFRequest("dispatch");   
                  System.out.println("Dispatch/getAcceptCars返回信息:"+response+"request.uri请求:"+HttpUtils.getIntance().request.service_uri);
			     jsonObj = JSONObject.fromString(response);
			     if(jsonObj.getInt("ret_code")==498){
			    	 errorMsg=response ;
			    	 System.out.println("Dispatch/getAcceptCars: 498  获取司机端列表失败， response:"+response+"----service_order_id:"+orderId);
			    	 break;
			     }else if(jsonObj.getInt("ret_code")==200){
			    	 carNumbr = jsonObj.getJSONArray("car_list").length();
			    	 System.out.println("[testOrder_qlcCancel]  info 获取司机端列表成功，response:"+response+"----service_order_id:"+orderId);
			    	 System.out.println(" info 获取司机端列表成功，response:"+response+"----service_order_id:"+orderId);
			    	 if(carNumbr>0){
			    		 car_list = jsonObj.getJSONArray("car_list");
			    		 carId=car_list.getJSONObject(0).getString("car_id");
			    		 driverId=car_list.getJSONObject(0).getString("driver_id");
			    		 
				    	 acceptRS=true;
				    	 break;	 
			    	 }
			     }
			 }	
			 //如果司机抢单成功，则跳出循环，否则则再次创建订单，重复创建订单3次还抢单抢单失败则报警
			 if(acceptRS){
				 break;
			 }
	}
		//userDecision派单
		if(acceptRS){			
//			 car_id = car_list.getJSONObject(0).getString("car_id");
//			 driverId=car_list.getJSONObject(0).getString("driver_id");
			
			 HttpUtils.getIntance().request.data = "";
			String param="service_order_id="+orderId+"&driver_id="+driverId+"&coupon_member_id=0&third_party_coupon=0";		  
			HttpUtils.getIntance().request.service_uri = "Dispatch/userDecision?"+param;	
		     String response = HttpUtils.getIntance().doPSFRequest("dispatch");
		     logger.info("Dispatch/userDecision返回信息:"+response+"request.uri请求信息:"+HttpUtils.getIntance().request.service_uri);
		     jsonObj = JSONObject.fromString(response);
		     Assert.assertEquals(jsonObj.getInt("ret_code"), 200,"用户决策接口失败,接口返回信息："+response+" 订单id:"+orderId);
//		 //决策成功，取消订单    不同订单状态的含义???
//		     HttpUtils.getIntance().request.data ="";
//		     param="order_id="+orderId+"&return_min=0"+driverId+"&reason_id=1&extension=testcancel&user_confirmed";
//		     HttpUtils.getIntance().request.service_uri = "state/cancel?"+param;	
//		     response = HttpUtils.getIntance().doPSFRequest("order");
//		     logger.info("state/cancel返回信息:"+response+"request.uri请求信息:"+HttpUtils.getIntance().request.service_uri);
//		     jsonObj = JSONObject.fromString(response);
//		     Assert.assertEquals(jsonObj.getInt("ret_code"), 200,"取消订单接口失败,接口返回信息:"+response+" 订单id:"+orderId);
		}
		Assert.assertTrue(acceptRS,"派单流程失败，错误信息："+errorMsg+" 创建订单id:"+orderId);
	}catch(Exception e){
		e.printStackTrace();
		Assert.assertTrue(false,"方法异常，异常信息"+e.getMessage());		
	
}
	//用户决策订单ID


	System.out.println("用户决策订单CarId:"+carId);
	System.out.println("用户决策订单StartTime:"+startTime);
	
	userOrderParams.put("order_Id", orderId);
	userOrderParams.put("driver_Id", driverId);
	userOrderParams.put("car_Id", carId);
	userOrderParams.put("startTime", startTime);
	
	return userOrderParams;
}

public String currentTime(){
	Date d=new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	System.out.println(df.format(d));
	return df.format(d);
}

//public static void main(String[] args) {
//	InterfaceUtils i=new InterfaceUtils();
//	i.currentTime();
//	
//}



}
