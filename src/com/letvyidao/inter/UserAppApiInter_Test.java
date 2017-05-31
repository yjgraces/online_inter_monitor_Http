package com.letvyidao.inter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.letvyidao.BaseRequest;
import com.letvyidao.utils.HttpUtils;
import com.letvyidao.utils.ReadProperties;
import com.letvyidao.utils.getSign;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.print.resources.serviceui;

public class UserAppApiInter_Test extends BaseRequest{
	public static Logger log=Logger.getLogger(UserAppApiInter_Test.class);
	public String time=(System.currentTimeMillis()/1000)+"";
	String alertmsg="";
	public static String testSleepTime="";
	public static String userListFile="";
	public static String driverListFile="";

/*
 * 接口
 */
	
	//接口名称：获取订单价格预估 /order/estimate
	public void orderEstimate(String paramFile,String accessToken,String cellPhone){
				
		String url="https://testing-client-agent.yongche.org:443/order/estimate";
		Map<String,String> mapParams = new HashMap<>();		
		Map<String,String> headerParams = new HashMap<>();	
		mapParams=readFileUtils.getParameterFromFileAsMap(paramFile);
		mapParams.put("start_time", time);
		mapParams.put("nonce", time);
		mapParams.put("city", "bj");
		mapParams.put("passenger_phone",cellPhone);
		
		String sign=getSign.getSignKey(mapParams);
		headerParams.put("Authorization", "Bearer "+accessToken);
		headerParams.put("User-Agent", "aWeidao/7.2.2 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);	
		Object rs = HttpUtils.getIntance().doSendGet(url,mapParams,headerParams);
		
		jsonObj = JSONObject.fromString(rs.toString());
		alertmsg="/order/estimate 获取价格预估接口response："+rs.toString();
		log.info(alertmsg);
		
		
	}
	

	//接口名称： 创建订单 /order
	public String creatOrder(String paramFile,String accessToken,String cellPhone){
		
		String url="https://testing-client-agent.yongche.org:443/order";
		String order_Id="";
		String bidding_id="";
		String sign="";
		
		
		Map<String,String> mapParams = new HashMap<>();		
		Map<String,String> headerParams = new HashMap<>();
		mapParams=readFileUtils.getParameterFromFileAsMap(paramFile);
		mapParams.put("start_time", time);
		mapParams.put("nonce", time);
		mapParams.put("passenger_phone",cellPhone);
		
		sign=getSign.getSignKey(mapParams);	
		headerParams.put("Authorization", "Bearer "+accessToken);
		headerParams.put("User-Agent", "aWeidao/7.2.2 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);
		
		Object rs = HttpUtils.getIntance().doSendPost(url,mapParams,headerParams);		
		alertmsg="创建订单 /order接口response："+rs.toString();
	//	System.out.println(alertmsg);
		jsonObj = JSONObject.fromString(rs.toString());
		int ret_code=jsonObj.getInt("ret_code");
		if(ret_code==200){
			order_Id=jsonObj.getJSONObject("result").getJSONObject("orderinfo").getString("order_id");
		//	System.out.println("order_Id:"+order_Id);
		}else if(ret_code==540){
			bidding_id=jsonObj.getJSONObject("result").getJSONObject("bidding").getString("bidding_id");
		//	System.out.println("bidding_id:"+bidding_id);
			mapParams.put("bidding_id",bidding_id);
			sign=getSign.getSignKey(mapParams);
			headerParams.put("sign", sign);
			rs = HttpUtils.getIntance().doSendPost(url,mapParams,headerParams);
		//	log.info("加价后创建订单结果："+rs.toString());
			jsonObj = JSONObject.fromString(rs.toString());
			ret_code=jsonObj.getInt("ret_code");
			if (ret_code==200){
				order_Id=jsonObj.getJSONObject("result").getJSONObject("orderinfo").getString("order_id");
				log.info("创建订单成功，order_Id:"+order_Id);
			}
			
		}else{
			log.error("创建订单失败！ret_code:"+ret_code);
		}
		
		return order_Id;		
	}
	
	//订单状态  /order/status
	public int orderStatus(String accessToken,String order_Id){
		boolean driverflag=false;
		UserAppApiInter_Test test=new UserAppApiInter_Test();
		String url="https://testing-client-agent.yongche.org:443/order/status";
		Map<String,String> mapParams = new HashMap<>();		
		Map<String,String> headerParams = new HashMap<>();
		int order_Status=0;
		String driver_id="";
		mapParams.put("order_id", order_Id);
		mapParams.put("nonce", time);		
		String sign=getSign.getSignKey(mapParams);	
		headerParams.put("Authorization", "Bearer "+accessToken);
		headerParams.put("User-Agent", "aWeidao/7.2.2 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);
	
		for(int i=0;i<10;i++){	
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object rs = HttpUtils.getIntance().doSendGet(url,mapParams,headerParams);
			jsonObj = JSONObject.fromString(rs.toString());
			alertmsg="订单状态  /order/status接口response:"+rs.toString();			
			log.info(alertmsg);
			int ret_code=jsonObj.getInt("ret_code");
			if (ret_code==200){
				order_Status=jsonObj.getJSONObject("result").getInt("status");
				driver_id=jsonObj.getJSONObject("result").getString("driver_id");
				
				//log.info("接单司机driver_id："+driver_id);
				if (order_Status==4){
					driverflag=test.compareDriverId(driver_id);
					if(driverflag){
						log.info("司机接单成功,接单司机在司机列表范围！ Order_id"+order_Id+"Order Status"+order_Status);
					}else{
						log.error("接单司机不在本程序司机列表范围,请确认司机是否在订单附近，Order_id:"+order_Id
								+"driverId:"+driver_id);
					}
					break;
				}	
				
			}
		}
		if(order_Status!=4){
			log.error("司机未接单,请检查司机接单程序是否正常！ Order_id:"+order_Id+" Order Status:"+order_Status);
		}
		return order_Status;		
	}
	
	//取消订单  /order/cancel
	public void orderCancel(String accessToken,String order_Id){
	//	String url=constant.orderCancel;
		String url="https://testing-client-agent.yongche.org:443/order/cancel";
		Map<String,String> mapParams = new HashMap<>();		
		Map<String,String> headerParams = new HashMap<>();

		mapParams.put("order_id", order_Id);
		mapParams.put("nonce", time);
		//mapParams.put("user_confirmed", "0");
		
		String sign=getSign.getSignKey(mapParams);	
		headerParams.put("Authorization", "Bearer "+accessToken);
		headerParams.put("User-Agent", "aWeidao/7.2.2 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);
		Object rs = HttpUtils.getIntance().doSendPost(url,mapParams,headerParams);
		jsonObj = JSONObject.fromString(rs.toString());
		alertmsg="/order/cancle 取消订单："+rs.toString();
		log.info(alertmsg);
	
	}
	
	//线上接口，暂未改造====
	//获取接受订单的司机列表 /order/acceptcar
	public String acceptCar(String accessToken,String order_Id){
		String url=constant.acceptcar;
		String driver_Id="";
		boolean acceptRS = false;
		Map<String,String> mapParams = new HashMap<>();		
		Map<String,String> headerParams = new HashMap<>();
		JSONArray car_list = null;
		Object rs;
		mapParams.put("order_id", order_Id);
		mapParams.put("nonce", time);
		
		String sign=getSign.getSignKey(mapParams);	
		headerParams.put("Authorization", "Bearer "+accessToken);
		headerParams.put("User-Agent", "aWeidao/7.2.2 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);
		
		for(int i=0;i<10;i++){
						
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = HttpUtils.getIntance().doSendGet(url,mapParams,headerParams);	
			alertmsg="获取接受订单的司机列表 /order/acceptcar接口response:"+rs.toString();
			System.out.println(alertmsg);
			jsonObj = JSONObject.fromString(rs.toString());
			int ret_code=jsonObj.getInt("ret_code");
			//System.out.println("ret_code:"+ret_code);
					
			if (ret_code==498){
				System.out.println("获取司机列表失败,订单号为："+order_Id);
				System.out.println("请求response:"+rs.toString());
				break;
			}else if(ret_code==200){
				int car_num=jsonObj.getJSONObject("result").getJSONArray("car_list").length();
				System.out.println("car_num"+car_num);
				if(car_num>0){
					car_list= jsonObj.getJSONObject("result").getJSONArray("car_list");
					driver_Id=car_list.getJSONObject(0).getString("driver_id");
					acceptRS=true;
					break;
				}
			}
			Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
			Assert.assertTrue(acceptRS,"获取接单司机失败，错误信息："+rs.toString()+" 订单id:"+order_Id);

		}
			
			System.out.println("driver_Id:"+driver_Id);
           // String car_id = car_list.getJSONObject(0).getString("car_id");

            return driver_Id;
		
	}
	
	//线上接口，暂未改造====
	// 选司机 /order/decisiondriver
	public void decisionDriver(String accessToken,String order_Id,String driver_Id){
		String url=constant.decisiondriver;
		Map<String,String> mapParams = new HashMap<>();		
		Map<String,String> headerParams = new HashMap<>();

		mapParams.put("order_id", order_Id);
		mapParams.put("driver_id", driver_Id);
		mapParams.put("nonce", time);
		mapParams.put("coupon_member_id", "0");
		mapParams.put("third_party_coupon", "0");
		
		String sign=getSign.getSignKey(mapParams);	
		headerParams.put("Authorization", "Bearer "+accessToken);
		headerParams.put("User-Agent", "aWeidao/7.2.2 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Object rs = HttpUtils.getIntance().doSendPost(url,mapParams,headerParams);
			jsonObj = JSONObject.fromString(rs.toString());
			alertmsg="选司机 /order/decisiondriver接口response:"+rs.toString();
			System.out.println(alertmsg);
			Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
	
	}
	
	 public static void main(String[] args) {
		 
		 UserAppApiInter_Test test=new UserAppApiInter_Test();
		 ArrayList<ReUserInfo> List2=new ArrayList<ReUserInfo>();
		 test.init();
		 List2=GetUserinfoList.getReUserList();
		 int x=1;
		 while(true){
			 
			 //遍历refreshtokenuserlist.dat中的用户，循环创建订单
			 
			 for(int i=0;i<List2.size();i++){
				 try {
					log.info("第"+x+"轮，第"+(i+1)+"次创建订单"); 
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 ReUserInfo user=List2.get(i);
				 test.createSysOrder(user); 
			 }
			 x++;
			 try {
				 int sleepTime=Integer.parseInt(testSleepTime);
				 Thread.sleep(sleepTime);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		 }
	 		
	}
	 
	 public void createSysOrder(ReUserInfo user){
		 RefreshToken_test r=new RefreshToken_test();
		 String cell_Phone=user.getCell_Phone();
		 String device_Id=user.getDevice_Id();
		 String user_Id=user.getUser_Id();
		 String accessToken= r.userRefreshToken(user_Id, device_Id, cell_Phone);

		 String paramFile="orderestimated_testevn.txt";
		 String paramFile2="createorder_testevn.txt";

		 UserAppApiInter_Test u=new UserAppApiInter_Test();
		 u.orderEstimate(paramFile,accessToken,cell_Phone);
		 String order_Id=u.creatOrder(paramFile2, accessToken, cell_Phone);
		 u.orderStatus(accessToken, order_Id);
		 u.orderCancel(accessToken, order_Id); 
	 }
	 
	 public boolean compareDriverId(String driver_Id){
		 boolean flag=false;
		 ArrayList<String> driverList=new ArrayList<String>();
		 driverList=GetUserinfoList.driverList();
		 System.out.println(driverList.size());
		 for(int i=0;i<driverList.size();i++){
			 if(driverList.get(i).trim().equals(driver_Id)){
				 flag=true; 
				 break;
			 }else{
				 flag=false;
			 }
			 
		 }
		return flag;
	 }
	 
	 public void init(){
		 ReadProperties r=new ReadProperties();
		 String filename="config.properties";

		 try {
			 testSleepTime=r.getProp(filename, "testSleepTime");
			 userListFile=r.getProp(filename, "userListFile");
			 driverListFile=r.getProp(filename, "driverListFile");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 
	 }
}
