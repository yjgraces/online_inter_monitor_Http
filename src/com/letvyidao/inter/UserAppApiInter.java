package com.letvyidao.inter;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import com.letvyidao.BaseRequest;
import com.letvyidao.utils.HttpUtils;
import com.letvyidao.utils.getSign;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserAppApiInter extends BaseRequest{

	public String time=(System.currentTimeMillis()/1000)+"";
	String alertmsg="";

/*
 * 接口
 */
	
	//接口名称：获取订单价格预估 /order/estimate
	public void orderEstimate(String paramFile,String accessToken){
		String url = constant.orderEstimate;
		
		
		Map<String,String> mapParams = new HashMap<>();		
		Map<String,String> headerParams = new HashMap<>();	
		mapParams=readFileUtils.getParameterFromFileAsMap(paramFile);
		mapParams.put("start_time", time);
		mapParams.put("nonce", time);
		
		
		String sign=getSign.getSignKey(mapParams);
		headerParams.put("Authorization", "Bearer "+accessToken);
		headerParams.put("User-Agent", "aWeidao/7.2.2 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);	
		Object rs = HttpUtils.getIntance().doSendGet(url,mapParams,headerParams);
		
		System.out.println("orderEstimate:"+rs.toString());
		jsonObj = JSONObject.fromString(rs.toString());
		alertmsg="/order/estimate 获取价格预估接口response："+rs.toString();
		System.out.println(alertmsg);
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
		
	}
	

	//接口名称： 创建订单 /order
	public String creatOrder(String paramFile,String accessToken){
		String url=constant.creatOrder;	
		System.out.println("creatOrder url============="+url);
		
		Map<String,String> mapParams = new HashMap<>();		
		Map<String,String> headerParams = new HashMap<>();
		mapParams=readFileUtils.getParameterFromFileAsMap(paramFile);
		mapParams.put("start_time", time);
		mapParams.put("nonce", time);		
		String sign=getSign.getSignKey(mapParams);	
		headerParams.put("Authorization", "Bearer "+accessToken);
		headerParams.put("User-Agent", "aWeidao/7.2.2 (X900; Android 5.0.2)");
		headerParams.put("sign", sign);
		Object rs = HttpUtils.getIntance().doSendPost(url,mapParams,headerParams);		
		alertmsg="创建订单 /order接口response："+rs.toString();
		System.out.println(alertmsg);
		jsonObj = JSONObject.fromString(rs.toString());
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
		String order_Id=jsonObj.getJSONObject("result").getJSONObject("orderinfo").getString("order_id");
		System.out.println("order_Id:"+order_Id);
		
		return order_Id;		
	}
	
	//订单状态  /order/status
	public int orderStatus(String accessToken,String order_Id){
		String url=constant.orderStatus;
		Map<String,String> mapParams = new HashMap<>();		
		Map<String,String> headerParams = new HashMap<>();
		int order_Status=0;
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
			
			Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
			order_Status=jsonObj.getJSONObject("result").getInt("status");
			System.out.println("订单状态 :order_Status="+order_Status);
			if (order_Status==4){	
				break;
			}	
			System.out.println(alertmsg);
		}
		return order_Status;		
	}
	
	//取消订单  /order/cancel
	public void orderCancel(String accessToken,String order_Id){
		String url=constant.orderCancel;
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
			System.out.println(alertmsg);
			Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
	
	}
	
	
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
	
}
