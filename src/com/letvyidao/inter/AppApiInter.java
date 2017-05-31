package com.letvyidao.inter;


import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.letvyidao.BaseRequest;
import com.letvyidao.utils.HttpUtils;

import net.sf.json.JSONObject;

public class AppApiInter extends BaseRequest{
	
	UserAppApiInter ua=new UserAppApiInter();
	public String orderAllId;
	public static String accessToken ;	

   @BeforeClass
	public void initOauth2Token(){
	//   constant.initHost();
	//   accessToken=getUserApiToken.initOauth2Token();
	//   orderAllId=ua.creatOrder("createorder.txt",accessToken);
	//   accessToken="93d866ae56a79083d94ba9ff9428dd61fef17bdb";
	   
	   accessToken="b30985205c4db6157fa7e9e2bb9562432a9cfa86";  //token会过期，需要动态获取
	   orderAllId="6383137577196620522";

	}
     
	@Test(description="/v7/order/all Get User Order List For 6.0",priority=1)
	public void testv7OrderAll(){
		HashMap<String, String> headMap = new HashMap<String, String>();
		Map<String,String> requestParam = new HashMap<>();
		String url= constant.ckd_orderAll;
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");

		requestParam.put("out_coord_type", "mars");

		Object rs = HttpUtils.getIntance().doSendGet(url,requestParam,headMap);
		System.out.println(rs.toString());
		jsonObj = JSONObject.fromString(rs.toString());
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());		
				
	}
	@Test(description="GET /v7/order Get Single Order Info",priority=2)
	public void testV7Order(){
		String url = constant.ckd_v7Order;
		Map<String,String> requestParam = new HashMap<>();
		HashMap<String, String> headMap = new HashMap<String, String>();
		requestParam.put("order_id", orderAllId);
		requestParam.put("out_coord_type", "mars");
	
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");
		Object rs = HttpUtils.getIntance().doSendGet(url,requestParam,headMap);
		jsonObj = JSONObject.fromString(rs.toString());
		System.out.println(rs.toString());
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
	}
	
	@Test(description="GET /v7/order/track Get Order Track",priority=3)
	public void testV7OrderTrack(){
		String url = constant.ckd_v7OrderTrack;
		Map<String,String> requestParam = new HashMap<>();
		requestParam.put("order_id", orderAllId);
		requestParam.put("out_coord_type", "mars");
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");
		Object rs = HttpUtils.getIntance().doSendGet(url,requestParam,headMap);
		jsonObj = JSONObject.fromString(rs.toString());
		System.out.println(rs.toString());
		//Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		Assert.assertEquals(true, jsonObj.getInt("ret_code")==403||jsonObj.getInt("ret_code")==200,rs.toString());
	}
	@Test(description="GET /v7/config/app Get App Config")
	public void testV7ConfigApp(){
		String url = constant.ckd_v7ConfigApp;
		Map<String,String> requestParam = new HashMap<>();
		requestParam.put("city", "bj");
	
		System.out.println(url);
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");
		Object rs = HttpUtils.getIntance().doSendGet(url, requestParam,headMap);
		jsonObj = JSONObject.fromString(rs.toString());
		System.out.println(rs.toString());
		Assert.assertEquals(jsonObj.getString("version").matches("[0-9]+")?"正确":jsonObj.getString("version"), "正确",rs.toString());
		
	}
	
	@Test(description="GET /v7/price/all Get All Price")
	public void testV7PriceAll(){
		String url = constant.ckd_v7PriceAll;
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");
		Object rs = HttpUtils.getIntance().doSendGetHeader(url,headMap);
		jsonObj = JSONObject.fromString(rs.toString());
		System.out.println(rs.toString());
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
	}
	
	@Test(description="GET /v7/map/location Get Nearby Cars")
	public void testV7MapLocation(){
		String url = constant.ckd_v7MapLocation+"?";
		Map<String,String> requestParam = new HashMap<>();
		requestParam.put("lat", "36.902");
		requestParam.put("lng", "100.152");
		requestParam.put("car_type_ids", "3");	
		requestParam.put("in_coord_type", "baidu");
		requestParam.put("out_coord_type", "baidu");
		url+=interfaceUtils.doGetParamsStr(requestParam);
		System.out.println(url);
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");
		Object rs = HttpUtils.getIntance().doSendGet(url,requestParam, headMap);
		jsonObj = JSONObject.fromString(rs.toString());
		System.out.println(rs.toString());
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
	//	Assert.assertEquals(jsonObj.getJSONObject("result").getInt("count")>0?"count>0":"count=0","count>0",rs.toString() );	
	}
	@Test(description="GET v7/map/locationsearch Get locationsearch keyword 国内")
	public void testV7MapLocationsearchKeyword(){
		String url = constant.ckd_v7MapLocationsearch+"?";
		Map<String,String> requestParam = new HashMap<>();
		requestParam.put("keywords", "中国技术交易大厦");
		requestParam.put("place_type", "1");
		requestParam.put("map_type", "2");	
		requestParam.put("in_coord_type", "baidu");
		requestParam.put("out_coord_type", "baidu");
		requestParam.put("is_inland", "1");
		url+=interfaceUtils.doGetParamsStr(requestParam);
		System.out.println(url);
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");
		Object rs = HttpUtils.getIntance().doSendGet(url, requestParam,headMap);
		jsonObj = JSONObject.fromString(rs.toString());
		System.out.println(rs.toString());
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		Assert.assertEquals(jsonObj.getJSONArray("results").length()>0?"length>0":"length=0", "length>0",rs.toString());
	}
	
	@Test(description="GET v7/map/locationsearch around 国内")
	public void testV7MapLocationsearchAround(){
		String url = constant.ckd_v7MapLocationsearch+"?";
		Map<String,String> requestParam = new HashMap<>();
		requestParam.put("place_type", "2");
		requestParam.put("map_type", "2");	
		requestParam.put("lat", "39.99016");
		requestParam.put("lng", "116.31409");
		requestParam.put("in_coord_type", "baidu");
		requestParam.put("out_coord_type", "baidu");
		requestParam.put("is_inland", "1");
		url+=interfaceUtils.doGetParamsStr(requestParam);
		System.out.println(url);
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");
		Object rs = HttpUtils.getIntance().doSendGet(url,requestParam, headMap);
		jsonObj = JSONObject.fromString(rs.toString());
		System.out.println(rs.toString());
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		Assert.assertEquals(jsonObj.getJSONArray("results").length()>0?"length>0":"length=0", "length>0",rs.toString());
	}
	
	@Test(description="GET v7/map/locationsuggest")
	public void testV7MapLocationsuggest(){
		String url = constant.ckd_v7MapLocationsuggest+"?";
		Map<String,String> requestParam = new HashMap<>();
		requestParam.put("keywords", "中国技术交易大厦");
		requestParam.put("map_type", "2");	
		requestParam.put("out_coord_type", "baidu");
		url+=interfaceUtils.doGetParamsStr(requestParam);
		System.out.println(url);
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");
		Object rs = HttpUtils.getIntance().doSendGet(url,requestParam, headMap);
		jsonObj = JSONObject.fromString(rs.toString());
		System.out.println(rs.toString());
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		Assert.assertEquals(jsonObj.getJSONArray("results").length()>0?"length>0":"length=0", "length>0",rs.toString());
	}
	
	@Test(description="GET v7/user/address/recommend V7获取上下车推荐地点")
	public void testV7AddressRecommend(){
		String url = constant.ckd_v7UserAddressRecomend+"?";
		Map<String,String> requestParam = new HashMap<>();
		requestParam.put("city", "bj");
		requestParam.put("address_type", "1");	
		requestParam.put("out_coord_type", "baidu");
		url+=interfaceUtils.doGetParamsStr(requestParam);
		System.out.println(url);
		HashMap<String, String> headMap = new HashMap<String, String>();
		headMap.put("Authorization", "Bearer "+accessToken);
		headMap.put("User-Agent", "aWeidao/7.1.3 (X900; Android 5.0.2)");
		Object rs = HttpUtils.getIntance().doSendGet(url,requestParam, headMap);
		jsonObj = JSONObject.fromString(rs.toString());
		System.out.println(rs.toString());
		Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		Assert.assertEquals(jsonObj.getJSONArray("result").length()>0?"length>0":"length=0", "length>0",rs.toString());
	}
}
