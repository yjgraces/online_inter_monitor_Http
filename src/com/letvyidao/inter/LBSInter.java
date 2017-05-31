package com.letvyidao.inter;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.letvyidao.BaseRequest;
import com.letvyidao.utils.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LBSInter extends BaseRequest{
public static Logger logger = LoggerFactory.getLogger(LBSInter.class);
public String alertmsg="";


	@Test(description="乘客端上车点名称推荐 /api/v1/around")
	public void testV1Around(){
	   String url = constant.lbs_around+"?";
	   Map<String,String> requestParams = new HashMap<String,String>();
	   requestParams.put("lat", "36.9022");
	   requestParams.put("lng", "100.1522");
	   requestParams.put("in_coord_type", "mars");
	   requestParams.put("out_coord_type", "baidu");
	   requestParams.put("car_type_ids", "3");
	   requestParams.put("area_km", "10");
	   requestParams.put("max_show_near_car_num", "3");
	  String getParamsStr = interfaceUtils.doGetParamsStr(requestParams);
	   url += getParamsStr;
	   System.out.println("v1Arount请求参数"+url);
	   Object rs = HttpUtils.getIntance().doSendGet(url);
	   System.out.println("v1Arount返回参数"+rs.toString());
	   jsonObj = JSONObject.fromString(rs.toString());
	   
	   System.out.println("ret_code:"+jsonObj.get("ret_code"));
	   System.out.println("ret_msg:"+jsonObj.getString("ret_msg"));
	   System.out.println("count:"+jsonObj.getJSONObject("result").getInt("count"));
	   
	   Assert.assertEquals(jsonObj.getInt("ret_code"), 200,"乘客端上车点名称推荐 接口：/api/v1/around返回码 非200，返回结果如下："+rs.toString());
	   Assert.assertEquals(jsonObj.getString("ret_msg"), "success","乘客端上车点名称推荐 接口：/api/v1/around异常，返回结果如下："+rs.toString());
	   Assert.assertTrue(jsonObj.getJSONObject("result").getInt("count")>=0,"乘客端上车点名称推荐 接口：/api/v1/around异常，返回结果如下："+rs.toString());
	   
	//   Assert.assertEquals(jsonObj.getString("status"), "ok","乘客端上车点名称推荐 接口：/api/v1/around异常，返回结果如下："+rs.toString());
	//   Assert.assertTrue(jsonObj.getJSONObject("results").getInt("count")>=0,"乘客端上车点名称推荐 接口：/api/v1/around异常，返回结果如下："+rs.toString());
	}
	@Test(description="乘客端上下车点搜索  /api/v1/place")
	public void testV1Place(){
		String url = constant.lbs_place+"?";
		 Map<String,String> requestParams = new HashMap<String,String>();
		 requestParams.put("lat", "39.98791");
		   requestParams.put("lng", "116.31286");
		   requestParams.put("city", "北京市");
		   requestParams.put("map_type", "2");
		   requestParams.put("in_coord_type", "mars");
		   requestParams.put("out_coord_type", "mars");
		   requestParams.put("place_type", "2");
		   String getParamsStr = interfaceUtils.doGetParamsStr(requestParams);
		   System.out.println("params:"+getParamsStr);
		   url += getParamsStr;
		   System.out.println("lsb_place请求参数："+url);
		   Object rs = HttpUtils.getIntance().doSendGet(url);
		   jsonObj = JSONObject.fromString(rs.toString());
		   System.out.println(rs.toString());
		   Assert.assertEquals(jsonObj.getInt("ret_code"), 200," /api/v1/place接口返回值非200，接口返回值如下："+rs.toString());
		   Assert.assertEquals(jsonObj.getString("status"), "ok","/api/v1/place异常，返回结果如下："+rs.toString());
		   Assert.assertTrue(jsonObj.getJSONArray("results").length()>=1,"/api/v1/place异常，返回结果如下："+rs.toString());
		   String poi_id = jsonObj.getJSONArray("results").getJSONObject(0).getString("poi_id");
		   String name = jsonObj.getJSONArray("results").getJSONObject(0).getString("name");
		   System.out.println("name:"+name);
		   Assert.assertTrue(poi_id.matches("b_.*")&&name.matches("北京大学"),"/api/v1/place异常，返回结果如下："+rs.toString());
	}
	
	@Test(description="乘客端上下车点搜索  /api/v1/place")
	public void testV1PlaceKeyWorld(){
		String url = constant.lbs_place+"?";
		 Map<String,String> requestParams = new HashMap<String,String>();
		 requestParams.put("keywords", "银科大厦");
		   requestParams.put("city", "北京市");
		   requestParams.put("map_type", "1");
		   requestParams.put("in_coord_type", "mars");
		   requestParams.put("out_coord_type", "mars");
		   requestParams.put("place_type", "1");
		   String getParamsStr = interfaceUtils.doGetParamsStr(requestParams);
		   url += getParamsStr;
		   System.out.println("testV1PlaceKeyWorld请求参数"+url);
		   Object rs = HttpUtils.getIntance().doSendGet(url);
		   jsonObj = JSONObject.fromString(rs.toString());
		   System.out.println(rs.toString());
		   Assert.assertEquals(jsonObj.getInt("ret_code"), 200," /api/v1/place接口返回值非200，接口返回值如下："+rs.toString());
		   Assert.assertEquals(jsonObj.getString("status"), "ok","/api/v1/place异常，返回结果如下："+rs.toString());
		   Assert.assertTrue(jsonObj.getJSONArray("results").length()>=1,"/api/v1/place异常，返回结果如下："+rs.toString());
		   String poi_id = jsonObj.getJSONArray("results").getJSONObject(0).getString("poi_id");
		   String name = jsonObj.getJSONArray("results").getJSONObject(0).getString("name");
		   Assert.assertTrue(poi_id.matches("b_.*")&&name.matches("银科大厦"),"/api/v1/place异常，返回结果如下："+rs.toString());
	}
	@Test(description="乘客端上下车点搜索  /api/v1/suggest")
	public void testV1Suggest(){
		String url = constant.lbs_suggest+"?";
		 Map<String,String> requestParams = new HashMap<String,String>();
		 requestParams.put("lat", "39.98791");
		   requestParams.put("lng", "116.31286");
		   requestParams.put("city", "北京市");
		   requestParams.put("map_type", "2");
		   requestParams.put("out_coord_type", "world");
		   requestParams.put("keywords", "银科");
		   String getParamsStr = interfaceUtils.doGetParamsStr(requestParams);
		   System.out.println(getParamsStr);
		   url += getParamsStr;
		   Object rs = HttpUtils.getIntance().doSendGet(url);
		   jsonObj = JSONObject.fromString(rs.toString());
		   System.out.println(rs.toString());
		   Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		   Assert.assertEquals(jsonObj.getString("status"), "ok",rs.toString());
		   Assert.assertTrue(jsonObj.getJSONArray("results").length()>=1,rs.toString());
		   String poi_id = jsonObj.getJSONArray("results").getJSONObject(0).getString("poi_id");
		   String name = jsonObj.getJSONArray("results").getJSONObject(0).getString("name");
		   System.out.println("name:"+name);
		   Assert.assertTrue(poi_id.matches("b_.*")&&name.matches("银科大厦"),"poi_id格式检查：matches'b_.*'失败"+rs.toString());
	}
	
	@Test(description="乘客端上下车点搜索 /api/v1/rgeo/rgeo")
	public void testV1Rgeo(){
		String url = constant.lbs_rgeo+"?";
		 Map<String,String> requestParams = new HashMap<String,String>();
		 requestParams.put("lat", "39.98791");
		   requestParams.put("lng", "116.31286");
		   requestParams.put("coord_type", "mars");
		   String getParamsStr = interfaceUtils.doGetParamsStr(requestParams);
		   System.out.println(getParamsStr);
		   url += getParamsStr;
		   Object rs = HttpUtils.getIntance().doSendGet(url);
		   jsonObj = JSONObject.fromString(rs.toString());
		   System.out.println(rs.toString());
		   Assert.assertEquals(jsonObj.getInt("code"), 200,rs.toString());
		   Assert.assertEquals(jsonObj.getString("status"), "ok",rs.toString());
		   Assert.assertEquals(jsonObj.getJSONObject("results").getString("aoi_name"),"北京大学",rs.toString());
	
	}
	@Test(description="派单距离预估  /api/v1/estimate/dispatch/order")
	public void testV1EstimateOrder(){
		String url = constant.lbs_estimateOrder;
		 jsonObj = new JSONObject();
		 jsonObj.put("service_order_id", "1");
		 jsonObj.put("origin", "{\"lat\":39.98791,\"lng\":116.31286 }");
		 jsonObj.put("destination", "{ \"lat\": 40.07968,\"lng\": 116.43490 }");
		 jsonObj.put("in_coord_type", "world");
		 System.out.println("params:"+jsonObj.toString());
		
		   Object rs = HttpUtils.getIntance().doSendPost(url, jsonObj.toString());
		 //  Object rs = HttpUtils.getIntance().doSendPost("http://10.0.11.239:9977/api/v1/estimate/dispatch/order", jsonObj.toString());
		   System.out.println("Lbs_estimateOrder"+rs.toString());
		   jsonObj = JSONObject.fromString(rs.toString());
		  
		   Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		   Assert.assertEquals(jsonObj.getString("status"), "ok",rs.toString());
		   Assert.assertTrue(jsonObj.getJSONArray("results").getJSONObject(0).getInt("route_distance")>18000,rs.toString());
	}
	@Test(description="司机预估距离补偿  /api/v1/estimate/dispatch/driver")
	public void testV1EstimateDriver(){
		String url = constant.lbs_estimateDriver;
		jsonObj = new JSONObject();
		 jsonObj.put("service_order_id", "1");
		 JSONArray originalArr = new JSONArray();
		//116.307524,39.984207中国技术交易大厦
		 originalArr.put(JSONObject.fromString("{ \"driver_id\":2,\"lat\":39.984207,\"lng\":116.307524}"));
		 //银科大厦39.9879110000,116.3128650000
		 originalArr.put(JSONObject.fromString("{ \"driver_id\":3,\"lat\":39.98791,\"lng\":116.31286 }"));
		 jsonObj.put("origins", originalArr.toString());
		 jsonObj.put("destination", "{ \"lat\": 40.068871,\"lng\": 116.423146}");
		 jsonObj.put("in_coord_type", "world");
		 System.out.println("params:"+jsonObj.toString());
		   Object rs = HttpUtils.getIntance().doSendPost(url, jsonObj.toString());
		   jsonObj = JSONObject.fromString(rs.toString());
		   System.out.println(rs.toString());
		   Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		   Assert.assertEquals(jsonObj.getString("status"), "ok",rs.toString());
		   Assert.assertTrue(jsonObj.getJSONArray("results").getJSONObject(0).getInt("route_distance")>15000,rs.toString());
	}
	@Test(description="计费距离时间预估  /api/v1/estimate/billing")
	public void testV1EstimateBillingNoCache(){
		String url = constant.lbs_estimatebilling;
		jsonObj = new JSONObject();
		jsonObj.put("service_order_id", "1");
		jsonObj.put("origin", "{\"lat\":39.984207,\"lng\":116.307524}");
		jsonObj.put("destination", "{\"lat\": 40.068871,\"lng\": 116.423146}");
		jsonObj.put("in_coord_type", "world");
		 System.out.println(jsonObj.toString());
		 Object rs = HttpUtils.getIntance().doSendPost(url, jsonObj.toString());
		 System.out.println(rs.toString());
		 jsonObj = JSONObject.fromString(rs.toString());
		
	//	jsonObj.put("use_cache", "1");
	//	jsonObj.put("cache_expires", "1");
		 Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		 Assert.assertTrue(jsonObj.getString("status").equals("ok")||jsonObj.getString("status").equals("degrade") ,rs.toString());
		 Assert.assertTrue(jsonObj.getJSONArray("results").getJSONObject(0).getInt("route_duration")>600,rs.toString());
	}
	@Test(description="计费距离时间预估(有缓存情况）  /api/v1/estimate/billing")
	public void testV1EstimateBillingCache(){
		String url = constant.lbs_estimatebilling;
		jsonObj = new JSONObject();
		jsonObj.put("service_order_id", "1");
		jsonObj.put("origin", "{\"lat\":39.984207,\"lng\":116.307524}");
		jsonObj.put("destination", "{\"lat\": 40.068871,\"lng\": 116.423146}");
		jsonObj.put("in_coord_type", "world");
		jsonObj.put("use_cache", "true");
		jsonObj.put("cache_expires", "3600");
		System.out.println(jsonObj.toString());
		 Object rs = HttpUtils.getIntance().doSendPost(url, jsonObj.toString());
		 System.out.println(rs.toString());
		 jsonObj = JSONObject.fromString(rs.toString());
		 Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		// Assert.assertEquals(jsonObj.getString("status"), "ok",rs.toString());
		 Assert.assertTrue(jsonObj.getString("status").equals("ok")||jsonObj.getString("status").equals("degrade") ,rs.toString());
		 Assert.assertTrue(jsonObj.getJSONArray("results").getJSONObject(0).getInt("route_duration")>600,rs.toString());
	}
	@Test(description="端上司机到达距离时间预估  /api/v1/estimate/driver/client")
	public void testV1EstimateDriverClient(){
	  	String url = constant.lbs_estimateDriverClient;
		jsonObj = new JSONObject();
		jsonObj.put("service_order_id", "1");
		jsonObj.put("origin", "{\"lat\":39.984207,\"lng\":116.307524}");
		jsonObj.put("destination", "{\"lat\": 40.068871,\"lng\": 116.423146}");
		jsonObj.put("in_coord_type", "world");
		 System.out.println(jsonObj.toString());
		 Object rs = HttpUtils.getIntance().doSendPost(url, jsonObj.toString());
		 System.out.println(rs.toString());
		 jsonObj = JSONObject.fromString(rs.toString());
		 Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		 Assert.assertEquals(jsonObj.getString("status"), "ok",rs.toString());
		 Assert.assertTrue(jsonObj.getJSONArray("results").getJSONObject(0).getInt("route_distance")>15000,rs.toString());
	}
	@Test(description="回传乘客选择的poi  /api/v1/poi/passback")
	public void testV1PoiPassback(){
		String url = constant.lbs_poiPassback+"?";	
		Map<String,String> requestParams = new HashMap<String,String>();
		 requestParams.put("poi_id", "b_9ee7cc789c89490e16c90544");
		   requestParams.put("provider", "suggesion");
		   requestParams.put("index", "3");
		   requestParams.put("name", "银科大厦");
		   requestParams.put("lat", "39.98056492037877");
		   requestParams.put("lng", "116.30027487535614");	   
		   requestParams.put("coord_type", "world");
		   requestParams.put("so_word", "银科");
		   requestParams.put("so_city", "北京");
		   requestParams.put("so_type", "1");
		   System.out.println(interfaceUtils.doGetParamsStr(requestParams));
		   url += interfaceUtils.doGetParamsStr(requestParams);
		 Object rs = HttpUtils.getIntance().doSendGet(url);
		 System.out.println(rs.toString());
		 jsonObj = JSONObject.fromString(rs.toString());
		 Assert.assertEquals(jsonObj.getInt("ret_code"), 200,rs.toString());
		 Assert.assertEquals(jsonObj.getString("status"), "ok",rs.toString());
	   }

/*
 * LBS 高德接口监控 20170117
 */
//
//@Test(description="预估高德错误比例:instance=AmapEstimateCommand&name=errorPercentage&type=HystrixCommand")
//	public void AmapEstimateErrorPercentage(){
//	Map<String,String> mapParams=new HashMap<String,String>();
//	    String url = constant.lbs_metrics1;
//	    
//	    mapParams.put("instance", "AmapEstimateCommand");
//	    mapParams.put("name", "errorPercentage");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="预估高德错误比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	    Assert.assertTrue(jsonObj.getInt("value")<10,alertmsg);
//	}
//
//@Test(description="预估百度错误比例:instance=BmapEstimateCommand&name=errorPercentage&type=HystrixCommand")
//public void BmapEstimateErrorPercentage(){
//    String url = constant.lbs_metrics1;
//    Map<String,String> mapParams=new HashMap<String,String>();
//    
//    mapParams.put("instance", "BmapEstimateCommand");
//    mapParams.put("name", "errorPercentage");
//    mapParams.put("type","HystrixCommand");
//
//    
//    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//    jsonObj = JSONObject.fromString(rs.toString());      
//    alertmsg="预估百度错误比例:接口response:"+rs.toString();
//    System.out.println(alertmsg);
//    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//    Assert.assertTrue(jsonObj.getInt("value")<10,alertmsg);
//
//}
//
//@Test(description="预估高德降级失败比例:instance=AmapEstimateCommand&name=rollingCountFallbackFailure&type=HystrixCommand")
//public void AmapEstimaterRollingCountFallbackFailure(){
//    String url = constant.lbs_metrics1;
//    Map<String,String> mapParams=new HashMap<String,String>();
//    mapParams.put("instance", "AmapEstimateCommand");
//    mapParams.put("name", "rollingCountFallbackFailure");
//    mapParams.put("type","HystrixCommand");
//    
//    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//    jsonObj = JSONObject.fromString(rs.toString());      
//    alertmsg="预估高德降级失败比例:接口response:"+rs.toString();
//    System.out.println(alertmsg);
//    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//    Assert.assertTrue(jsonObj.getInt("value")<100,alertmsg);
//}
//
//
//	@Test(description="预估百度降级失败比例:instance=BmapEstimateCommand&name=rollingCountFallbackFailure&type=HystrixCommand")
//	public void BmapEstimateRollingCountFallbackFailure(){
//	    String url = constant.lbs_metrics1;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "BmapEstimateCommand");
//	    mapParams.put("name", "rollingCountFallbackFailure");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="预估百度降级失败比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	    Assert.assertTrue(jsonObj.getInt("value")<100,alertmsg);
//	       
//	}
//	
////=====
//	
//	@Test(description="搜索高德错误比例:instance=AmapPlaceCommand&name=errorPercentage&type=HystrixCommand")
//	public void AmapPlaceCommandErrorPercentage(){
//	Map<String,String> mapParams=new HashMap<String,String>();
//	    String url = constant.lbs_metrics2;
//	    
//	    mapParams.put("instance", "AmapPlaceCommand");
//	    mapParams.put("name", "errorPercentage");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="搜索高德错误比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	   // Assert.assertTrue(jsonObj.getInt("value")<10,alertmsg);
//	}
//
//	@Test(description="搜索百度错误比例:instance=BmapPlaceCommand&name=errorPercentage&type=HystrixCommand")
//	public void BmapPlaceCommandErrorPercentage(){
//	    String url = constant.lbs_metrics2;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    
//	    mapParams.put("instance", "BmapPlaceCommand");
//	    mapParams.put("name", "errorPercentage");
//	    mapParams.put("type","HystrixCommand");
//		    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="搜索百度错误比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	    Assert.assertTrue(jsonObj.getInt("value")<10,alertmsg);
//	
//	}
//
//	@Test(description="搜索高德错误比例:instance=AmapSuggestCommand&name=errorPercentage&type=HystrixCommand")
//	public void AmapSuggestCommandErrorPercentage(){
//	    String url = constant.lbs_metrics2;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "AmapSuggestCommand");
//	    mapParams.put("name", "errorPercentage");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="搜索高德错误比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	 //   Assert.assertTrue(jsonObj.getInt("value")<10,alertmsg);
//	}
//
//
//	@Test(description="搜索百度错误比例:instance=BmapSuggestCommand&name=errorPercentage&type=HystrixCommand")
//	public void BmapSuggestCommandErrorPercentage(){
//	    String url = constant.lbs_metrics2;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "BmapSuggestCommand");
//	    mapParams.put("name", "errorPercentage");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="搜索百度错误比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	    Assert.assertTrue(jsonObj.getInt("value")<10,alertmsg);
//	       
//	}
//	
//	//降级
//
//	@Test(description="搜索高德降级失败比例:instance=AmapPlaceCommand&name=rollingCountFallbackFailure&type=HystrixCommand")
//	public void AmapPlaceCommandRollingCountFallbackFailure(){
//	    String url = constant.lbs_metrics2;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "AmapPlaceCommand");
//	    mapParams.put("name", "rollingCountFallbackFailure");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="搜索高德降级失败比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	//    Assert.assertTrue(jsonObj.getInt("value")<100,alertmsg);
//	       
//	}
//	@Test(description="搜索百度降级失败比例:instance=BmapPlaceCommand&name=rollingCountFallbackFailure&type=HystrixCommand")
//	public void BmapPlaceCommandRollingCountFallbackFailure(){
//	    String url = constant.lbs_metrics2;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "BmapPlaceCommand");
//	    mapParams.put("name", "rollingCountFallbackFailure");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="搜索百度降级失败比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	    Assert.assertTrue(jsonObj.getInt("value")<100,alertmsg);
//	       
//	}
//	@Test(description="搜索高德降级失败比例:instance=AmapSuggestCommand&name=rollingCountFallbackFailure&type=HystrixCommand")
//	public void AmapSuggestCommandRollingCountFallbackFailure(){
//	    String url = constant.lbs_metrics2;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "AmapSuggestCommand");
//	    mapParams.put("name", "rollingCountFallbackFailure");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="搜索高德降级失败比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	  //  Assert.assertTrue(jsonObj.getInt("value")<100,alertmsg);
//	       
//	}
//	
//	@Test(description="搜索百度降级失败比例:instance=BmapSuggestCommand&name=rollingCountFallbackFailure&type=HystrixCommand")
//	public void BmapSuggestCommandRollingCountFallbackFailure(){
//	    String url = constant.lbs_metrics2;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "BmapSuggestCommand");
//	    mapParams.put("name", "rollingCountFallbackFailure");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="搜索百度降级失败比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("ret_code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	    Assert.assertTrue(jsonObj.getInt("value")<100,alertmsg);
//	       
//	}
//	
//	//逆地理解析
//	
//	@Test(description="逆地理高德错误比例:instance=AmapRgeoCommand&name=errorPercentage&type=HystrixCommand")
//	public void AmapRgeoCommandErrorPercentage(){
//	    String url = constant.lbs_metrics3;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "AmapRgeoCommand");
//	    mapParams.put("name", "errorPercentage");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="逆地理高德错误比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	    Assert.assertTrue(jsonObj.getInt("value")<10,alertmsg);
//	}
//	  
//	@Test(description="逆地理百度错误比例:instance=BmapRgeoCommand&name=errorPercentage&type=HystrixCommand")
//	public void BmapRgeoCommandErrorPercentage(){
//	    String url = constant.lbs_metrics3;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "BmapRgeoCommand");
//	    mapParams.put("name", "errorPercentage");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="逆地理百度错误比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	 //   Assert.assertTrue(jsonObj.getInt("value")<10,alertmsg);
//	}
//	
//	
//	@Test(description="逆地理高德降级失败比例:instance=AmapRgeoCommand&name=rollingCountFallbackFailure&type=HystrixCommand")
//	public void AmapRgeoCommandRollingCountFallbackFailure(){
//	    String url = constant.lbs_metrics3;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "AmapRgeoCommand");
//	    mapParams.put("name", "rollingCountFallbackFailure");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="逆地理高德降级失败比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	    Assert.assertTrue(jsonObj.getInt("value")<100,alertmsg);
//	       
//	}
//	
//	@Test(description="逆地理百度降级失败比例:instance=BmapRgeoCommand&name=rollingCountFallbackFailure&type=HystrixCommand")
//	public void BmapRgeoCommandRollingCountFallbackFailure(){
//	    String url = constant.lbs_metrics3;
//	    Map<String,String> mapParams=new HashMap<String,String>();
//	    mapParams.put("instance", "BmapRgeoCommand");
//	    mapParams.put("name", "rollingCountFallbackFailure");
//	    mapParams.put("type","HystrixCommand");
//	    
//	    Object rs = HttpUtils.getIntance().doSendGetMap(url,mapParams);
//	    jsonObj = JSONObject.fromString(rs.toString());      
//	    alertmsg="逆地理百度降级失败比例:接口response:"+rs.toString();
//	    System.out.println(alertmsg);
//	    Assert.assertEquals(jsonObj.getInt("code"), 200,alertmsg);
//	    Assert.assertEquals(jsonObj.get("status"), "ok",alertmsg);
//	//    Assert.assertTrue(jsonObj.getInt("value")<100,alertmsg);
//	       
//	}
	
}
