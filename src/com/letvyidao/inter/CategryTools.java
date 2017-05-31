package com.letvyidao.inter;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.letvyidao.BaseRequest;
import com.letvyidao.utils.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CategryTools extends BaseRequest{
private  static Logger logger = LoggerFactory.getLogger(CategryTools.class); 

@Test(description="/findActivityInf.json接口")
public void testfindActivityInf(){
	String url = constant.categry_findActivityInf;
	Object rs = HttpUtils.getIntance().doSendGet(url);
	logger.info(rs.toString());
    jsonObj = JSONObject.fromString(rs.toString());    
    Assert.assertEquals(jsonObj.getString("message"), "请求成功",rs.toString());
    Assert.assertEquals(jsonObj.getString("status"), "1",rs.toString());
    Assert.assertEquals(StringUtils.isEmpty(jsonObj.getJSONObject("result").getString("activityUrl"))?"activityUrl空":"activityUrl非空", "activityUrl非空",rs.toString());
    Assert.assertEquals(StringUtils.isEmpty(jsonObj.getJSONObject("result").getString("activityStatus"))?"activityStatus空":"activityStatus", "activityStatus",rs.toString());
}
@Test(description="/findActivityRewards.json接口")
public void testfindActivityRewards(){
	String url = constant.categry_findActivityRewards;
	Object rs = HttpUtils.getIntance().doSendGet(url);
    jsonObj = JSONObject.fromString(rs.toString());
    logger.info(rs.toString());
    Assert.assertEquals(jsonObj.getString("message"), "请求成功",rs.toString());
    Assert.assertEquals(jsonObj.getString("status"), "1",rs.toString());
    JSONArray activities = jsonObj.getJSONObject("result").getJSONArray("activities");
    Assert.assertEquals(activities.length()==0?"activityes.length==0":"activityes.length>0","activityes.length>0",rs.toString());
    Assert.assertEquals(StringUtils.isEmpty(jsonObj.getJSONObject("result").getString("monthReward"))?"monthReward 空":"monthReward非空", "monthReward非空",rs.toString());
    Assert.assertEquals(StringUtils.isEmpty(jsonObj.getJSONObject("result").getString("rank"))?"rank 空":"rank非空", "rank非空",rs.toString());
    Assert.assertEquals(StringUtils.isEmpty(jsonObj.getJSONObject("result").getString("yesterdayReward"))?"yesterdayReward 空":"yesterdayReward非空","yesterdayReward非空",rs.toString());
    Assert.assertEquals(StringUtils.isEmpty(jsonObj.getJSONObject("result").getString("monthReward"))?"monthReward 空":"monthReward非空", "monthReward非空",rs.toString());
}
@Test(description="/findActivityDetail.json接口")
public void testfindActivityDetail(){
	String url = constant.categry_findActivityDetail;
	Object rs = HttpUtils.getIntance().doSendGet(url);
    jsonObj = JSONObject.fromString(rs.toString());
 //   System.out.println(rs.toString());
    Assert.assertEquals(jsonObj.getString("message"), "请求成功",rs.toString());
    Assert.assertEquals(jsonObj.getString("status"), "1",url+"----返回结果："+rs.toString());
    Assert.assertEquals(jsonObj.getJSONObject("result").getJSONObject("baseInfo")!=null, true,url+"----返回结果："+rs.toString());
}
@Test(description="/findHistoryActivityRewards接口")
public void testfindHistoryActivityRewards(){
	String url = constant.categry_findHistoryActivityRewards;
	Object rs = HttpUtils.getIntance().doSendGet(url);
    jsonObj = JSONObject.fromString(rs.toString());
 //   System.out.println(rs.toString());
    Assert.assertEquals(jsonObj.getString("message"), "请求成功",rs.toString());
    Assert.assertEquals(jsonObj.getString("status"), "1",url+"----返回结果："+rs.toString());
    Assert.assertEquals(jsonObj.getJSONObject("result").getInt("recordCount")>=1?"recordCount>=1":"recordCount空", "recordCount>=1",url+"----返回结果："+rs.toString());
    String id = jsonObj.getJSONObject("result").getJSONArray("historyRewards").getJSONObject(0).getJSONArray("activityRewards").getJSONObject(0).getString("id");
    Assert.assertEquals(id.matches("[0-9]*")?"id非空":"id为空", "id非空",url+"----返回结果："+rs.toString());
}
@Test(description="/findRewardDetail.json接口")
public void testfindRewardDetail(){
	String url = constant.categry_findRewardDetail;
	Object rs = HttpUtils.getIntance().doSendGet(url);
    jsonObj = JSONObject.fromString(rs.toString());
 //   System.out.println(rs.toString());
    Assert.assertEquals(jsonObj.getString("message"), "请求成功",rs.toString());
    Assert.assertEquals(jsonObj.getString("status"), "1",url+"----返回结果："+rs.toString());
    Assert.assertEquals(jsonObj.getJSONObject("result").getJSONObject("baseInfo")!=null, true,url+"期待baseInfo不为空----返回结果："+rs.toString());
}
@Test(description="http://opsettlement.yongche.org/isAlive接口")
public void testIsAlive(){
	String url = constant.categry_isAlive;
	Object rs = HttpUtils.getIntance().doSendGet(url);
    jsonObj = JSONObject.fromString(rs.toString());
 //   System.out.println(rs.toString());
    Assert.assertEquals(jsonObj.getInt("code"), 0,rs.toString());
    Assert.assertEquals(jsonObj.getString("msg"), "服务正常！",url+"----返回结果："+rs.toString());
    Assert.assertEquals(jsonObj.getBoolean("success"), true,url+"期待success:true----返回结果："+rs.toString());
}
@Test(description="策略工具缓存数据库信息比对:数据库数据和缓存数据")
public void testCacheNoDiff(){
	String dburl = constant.categry_dbURL;
	String cacheURL = constant.categry_cacheURL;
	Object dbrs = HttpUtils.getIntance().doSendGet(dburl);
	Object cachers = HttpUtils.getIntance().doSendGet(cacheURL);
	JSONObject dbJson =  JSONObject.fromString(dbrs.toString());
    JSONObject casheJson = JSONObject.fromString(cachers.toString());
    StringBuffer sb = new StringBuffer("数据库中存在，但是缓存中没有的数据：");
    JSONArray activities = casheJson.getJSONObject("result").getJSONArray("activities");
    Iterator dbkeys = dbJson.keys();
    int [] activityIds = new int [activities.length()];
    for(int i=0;i<activities.length();i++){
    	activityIds[i] = activities.getJSONObject(i).getInt("id");
    }
    StringBuffer dbids = new StringBuffer();
    while(dbkeys.hasNext()){  
    	dbids.append(dbkeys.next()+"&");
    }
    String [] dbidsArr = dbids.substring(0,dbids.length()-1).split("&");
    for(int i=0;i<dbidsArr.length;i++){
    	 for(int j=0;j<activityIds.length;j++){
    		 if(activityIds[j]== Integer.parseInt(dbidsArr[i])){
    			 break;
    		 }
    		 if(j==activityIds.length){
    			 sb.append(dbidsArr[i]+",");
    		 }
    	 }
    }
  Assert.assertEquals(sb.toString(), "数据库中存在，但是缓存中没有的数据：",sb.toString());
}
}
