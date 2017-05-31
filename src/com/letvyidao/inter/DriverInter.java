package com.letvyidao.inter;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.letvyidao.BaseRequest;
import com.letvyidao.utils.HttpUtils;
import com.letvyidao.utils.ReadFileUtils;
import com.letvyidao.utils.ReadProperties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DriverInter extends BaseRequest{
public JSONObject jsonObj = null;
public Map<String,String> params = null;
public static Map<String,String> HeaderParams=new HashMap<String,String>();
public ReadFileUtils readFileUtils=new ReadFileUtils();
public ReadProperties readProperties=new ReadProperties();
public DriverInter d;
public static String version;
public static String imei;
public static String x_auth_mode;
public static String is_gzip;
public String reason;
public String page_num;
public String vehicle_number;
public String cellphone;
public String area_code;


@BeforeTest
public void initData(){
	//初始化HeaderParams
	String header= HttpUtils.getIntance().getAuthorizationString("Authorization_Driver.txt");
	HeaderParams=readFileUtils.getParameterFromFileAsMap("headerParams_Driver.txt");	
	HeaderParams.put("Authorization","OAuth "+header);
	//初始化接口参数
	String filename="DriverAppInterParams.properties";
	try {
		version=readProperties.getProp(filename,"version");
		imei=readProperties.getProp(filename,"imei");
		x_auth_mode=readProperties.getProp(filename,"x_auth_mode");
		is_gzip=readProperties.getProp(filename,"is_gzip");
		reason=readProperties.getProp(filename,"reason");
		page_num=readProperties.getProp(filename,"page_num");
		vehicle_number=readProperties.getProp(filename,"vehicle_number");
		cellphone=readProperties.getProp(filename,"cellphone");
		area_code=readProperties.getProp(filename,"area_code");

		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	
		
	params = new HashMap<String,String>();

	params.put("version", version);
	params.put("imei", imei);
	params.put("x_auth_mode", x_auth_mode);
	params.put("is_gzip", is_gzip);	
}

///new

@Test(description="getDriverAccountDetail 我的账户详情")
public void GetDriverAccountDetail(){
	String url = constant.appdriver_GetDriverAccountDetail;
	params.put("reason", reason);
	params.put("page_num", page_num);
		
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);	
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"/V2/Driver/GetDriverAccountDetai 我的账户详情接口报错，返回信息如下："+rs.toString());
    Assert.assertEquals(jsonObj.getJSONObject("msg").getInt("ret_code"),201,"/V2/Driver/GetDriverAccountDetai 我的账户详情接口报错，返回信息如下："+rs.toString());	
}




@Test(description="GetInBalanceList 结算中")
public void testGetInBalanceList(){
	String url = constant.appdriver_GetInBalanceList;
	params.put("page_num", "5");
	params.put("version", "80");
	Object  rs = HttpUtils.getIntance().doSendPost(url, params, HeaderParams);	
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());

	Assert.assertEquals(jsonObj.getInt("code"), 200,"/V4/Driver/GetInBalanceList结算中接口报错，返回信息如下："+rs.toString());
//    Assert.assertEquals(jsonObj.getJSONObject("msg").getInt("ret_code"),499,"/v4/Driver/SetCarType 获取司机可变更的车型列表接口报错，返回信息如下："+rs.toString());	
}


@Test(description="GetLowCarType 获取司机可变更的车型列表")
public void testGetLowCarType(){
	String url = constant.appdriver_GetLowCarType;
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);	
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"/V4/Driver/GetLowCarType 获取司机可变更的车型列表接口报错，返回信息如下："+rs.toString());
    Assert.assertTrue(jsonObj.getJSONObject("msg").getJSONArray("car_type_list").length() >= 0, "/V4/Driver/GetLowCarType 获取司机可变更的车型列表接口报错，返回信息如下：" + rs.toString());
}

    @Test(description = "GetDriverEvalute 好评率接口")
public void testGetDriverEvalute(){
	String url = constant.appdriver_GetDriverEvalute;
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);	
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"V1/Driver/GetDriverEvalute  好评率接口报错，返回信息如下："+rs.toString());
    Assert.assertEquals(jsonObj.getJSONObject("msg").getInt("good_comment_count"), 0,"V1/Driver/GetDriverEvalute  好评率接口报错,返回信息如下："+rs.toString());
}
@Test(description="WithdrawDriverCash 司机申请提现")
public void testWithdrawDriverCash(){
	String url = constant.appdriver_WithdrawDriverCash;
	params.put("amount", "1");
	Object rs = HttpUtils.getIntance().doSendPost(url, params, HeaderParams);	
	//{"msg":"true","code":200}
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"/Driver/WithdrawDriverCash 司机申请提现接口报错，返回信息如下："+rs.toString());
    Assert.assertEquals(jsonObj.getJSONObject("msg").getString("ret_msg"), "提现金额必须大于10","/Driver/WithdrawDriverCash 司机申请提现接口报错,返回信息如下："+rs.toString());
}
@Test(description="getDriverLevelDetailDay 司机日经验值明细")
public void testgetDriverLevelDetailDay(){
	String url = constant.appdriver_getDriverLevelDetailDay;
	params.put("date", "20161011");
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);	
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"V4/Driver/getDriverLevelDetailDay 司机日经验值明细接口报错，返回信息如下："+rs.toString());
	int sum = jsonObj.getJSONObject("msg").getInt("sum");
	JSONArray details = jsonObj.getJSONObject("msg").getJSONArray("detail");
	int actualSum = 0;
	for(int i=0;i<details.length();i++){
	   actualSum+= details.getJSONObject(i).getInt("score");
	}
	Assert.assertTrue(jsonObj.getJSONObject("msg").getString("sum").matches("[0-9]*"),"V4/Driver/getDriverLevelDetailDay 司机日经验值明细接口报错，返回信息如下："+rs.toString());
    Assert.assertEquals(actualSum, sum,"V4/Driver/getDriverLevelDetailDay 司机日经验值明细接口报错，返回信息如下："+rs.toString());
}
@Test(description="getDriverLevelDetailMonth 司机经验值明细")
public void testgetDriverLevelDetailMonth(){
	String url = constant.appdriver_getDriverLevelDetailMonth;
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);	
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"/V4/Driver/getDriverLevelDetailMonth 司机经验值明细接口报错，返回信息如下："+rs.toString());
	Assert.assertTrue(jsonObj.getJSONObject("msg").getString("sum").matches("[0-9]*"),"/V4/Driver/getDriverLevelDetailMonth 司机经验值明细接口报错，返回信息如下："+rs.toString());
}
@Test(description="GetDriverLevelInfo 司机等级")
public void testGetDriverLevelInfo(){
	String url = constant.appdriver_GetDriverLevelInfo;
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);	
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"/V4/Driver/GetDriverLevelInfo 司机等级接口报错，返回信息如下："+rs.toString());
	Assert.assertEquals(jsonObj.getJSONObject("msg").getInt("next_level"), jsonObj.getJSONObject("msg").getInt("driver_level")+1,"/V4/Driver/GetDriverLevelInfo 司机等级接口报错，返回信息如下："+rs.toString());
}
@Test(description="member 获取司机信息")
public void testMember(){
	String url = constant.appdriver_member;
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);	
	jsonObj = JSONObject.fromString(rs.toString());
//	String name = jsonObj.getJSONObject("msg").getString("name");
	System.out.println(jsonObj.toString());
	Assert.assertTrue(jsonObj.getInt("code") == 200 || jsonObj.getInt("code") == 403, "/driver/member 获取司机信息接口报错，返回信息如下："+rs.toString());
	Assert.assertEquals(jsonObj.getJSONObject("msg").getJSONObject("member_info").getString("login_name"),"易到测试11","/driver/member 获取司机信息接口报错，返回信息如下："+rs.toString());
}
@Test(description="GetDriverAccount 我的账户首页")
public void testGetDriverAccount(){
	String url = constant.appdriver_GetDriverAccount;
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	
	jsonObj = JSONObject.fromString(rs.toString());
	String name = jsonObj.getJSONObject("msg").getString("name");
	System.out.println(jsonObj.toString()+"====="+name);
	Assert.assertEquals(jsonObj.getInt("code"), 200,"v4/Driver/GetDriverAccount 我的账户首页接口报错，返回信息如下："+rs.toString());
	Assert.assertEquals(jsonObj.getJSONObject("msg").getString("name"),"易到测试11","v4/Driver/GetDriverAccount 我的账户首页接口报错，返回信息如下："+rs.toString());
}
@Test(description="Driver/GetContributionOrder 取得司机参与分相关订单信息")
public void testGetContributionOrder(){
	String url = constant.appdriver_GetContributionOrder;
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	System.out.println(rs.toString());
	jsonObj = JSONObject.fromString(rs.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 499,"Driver/GetContributionOrder 取得司机参与分相关订单信息接口报错，返回信息如下："+rs.toString());
	Assert.assertEquals(jsonObj.getString("msg"), "无法取得司机参与分相关的订单信息！","Driver/GetContributionOrder 取得司机参与分相关订单信息接口报错，返回信息如下："+rs.toString());
//	Assert.assertEquals(jsonObj.getJSONObject("msg").getJSONArray("list").length(), jsonObj.getJSONObject("msg").getInt("count"),"Driver/GetContributionOrder 取得司机参与分相关订单信息接口报错，返回信息如下："+rs.toString());
}
@Test(description="DriverGetDriverIncome 司机端获取历史收入的接口")
public void testGetDriverIncome(){
 	String url = constant.appdriver_GetDriverIncome;
 	params.put("type", "month");

	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	System.out.println(rs.toString());
	jsonObj = JSONObject.fromString(rs.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"DriverGetDriverIncome 司机端获取历史收入的接口返回值非200，接口返回内容如下："+rs.toString());
	Assert.assertTrue(StringUtils.isNotEmpty(jsonObj.getString("msg")),"DriverGetDriverIncome 司机端获取历史收入的接口报错，报错内容："+rs.toString());
    params.put("type", "month_history");    
	 rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	System.out.println(rs.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"DriverGetDriverIncome 司机端获取历史收入的接口返回值非200，接口返回内容如下："+rs.toString());
	params.put("type", "day");	
	rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	System.out.println(rs.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 200,"DriverGetDriverIncome 司机端获取历史收入的接口返回值非200，接口返回内容如下："+rs.toString());
}
@Test(description="GetDriverIncomeOrder 取得司机收入相关订单明细")
public void testGetDriverIncomeOrder(){
  	String url = constant.appdriver_GetDriverIncomeOrder;
	params.put("type", "month");
	params.put("timeflag", "2016-02");
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	System.out.println(rs.toString());
	jsonObj = JSONObject.fromString(rs.toString());
	Assert.assertEquals(jsonObj.getInt("code"), 404,"GetDriverIncomeOrder 取得司机收入相关订单明细接口报错，报错信息如下："+rs.toString());
	Assert.assertEquals(jsonObj.getString("msg"), "没有任何结果！","GetDriverIncomeOrder 取得司机收入相关订单明细接口报错，报错信息如下："+rs.toString());
}
@Test(description="/Order/GetItemOrder 获取订单详情")
public void testGetItemOrder(){
	String url = constant.appdriver_OrderGetItemOrder;
	params.put("order_id", "2005817779");
	params.put("out_coord_type", "baidu");
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());
	Assert.assertTrue((jsonObj.getInt("code")==200),"/Order/GetItemOrder接口返回码非200，接口返回信息："+rs.toString());
//	Assert.assertTrue(jsonObj.getJSONObject("msg").getString("today_income").matches("[0-9]*"),"/V5/Driver/GetIndex接口报错，接口返回信息："+rs.toString());
}
@Test(description="GetIndex 首页信息")
public void testGetIndex(){
	String url = constant.appdriver_DriverGetIndex;
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	System.out.println(rs.toString());
	jsonObj = JSONObject.fromString(rs.toString());
	Assert.assertTrue((jsonObj.getInt("code")==200),"/V5/Driver/GetIndex接口返回码非200，接口返回信息："+rs.toString());
	Assert.assertTrue(jsonObj.getJSONObject("msg").getString("today_income").matches("[0-9]*"),"/V5/Driver/GetIndex接口报错，接口返回信息："+rs.toString());
}
@Test(description="MemberStat 司机工作状态")
public void testMemberStat(){
	String url = constant.appdriver_DriverMemberStat;
	
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(jsonObj.toString());
	Assert.assertTrue((jsonObj.getInt("code")==200),"/Driver/MemberStat接口返回码非200，接口返回信息："+rs.toString());
	System.out.println(jsonObj.getString("msg"));
	Assert.assertTrue(jsonObj.getString("msg").matches("..nobusy.*"),"/Driver/MemberStat接口报错，接口返回信息："+rs.toString());
}
@Test(description="CurrentVersion 获取版本")
public void testCurrentVersion(){
	String url = constant.appdriver_CurrentVersion;
	params.put("device_type", "1");
		
	Object rs = HttpUtils.getIntance().doSendGet(url,params,HeaderParams);
	jsonObj = JSONObject.fromString(rs.toString());
	System.out.println(rs.toString());
	Assert.assertTrue((jsonObj.getInt("code")==200)||(jsonObj.getInt("code")==412),"/Global/CurrentVersion接口返回码非200、非412，接口返回信息："+rs.toString());
	Assert.assertTrue(jsonObj.getJSONObject("msg").getString("current_version").matches("[1-9][0-9]*"),"/Global/CurrentVersion接口报错，接口返回信息："+rs.toString());
}
@Test(description="CreateDriverPassword 获取验证码")
public void testDriverCreateDriverPassword(){
	String url = constant.appdriver_CreateDriverPassword;
	params.put("cellphone", cellphone);	
	params.put("vehicle_number",vehicle_number);	
	params.put("area_code", area_code);

	Object rs = HttpUtils.getIntance().doSendGet(url,params);
	jsonObj = JSONObject.fromString(rs.toString());
    System.out.println(rs.toString());
    Assert.assertEquals(jsonObj.getInt("code"), 200,"/Driver/CreateDriverPassword接口返回码非200，接口返回值如下:"+rs.toString());
}

@Test(description="v4/Driver/Appeal申诉内容提交   /v4/Driver/GetAppealContent 申诉机会次数获取 流程监控")
public void testAppealAndGetAppealContent(){
	d=new DriverInter();
	try{
		jsonObj = d.getAppealContent();
		String appealBefore = jsonObj.toString();
		System.out.println("appealBefore:"+appealBefore);
		Assert.assertEquals(jsonObj.getInt("code"), 200,"/v4/Driver/GetAppealContent接口返回状态码非200，返回内容如下："+appealBefore);
		int appeal_count = jsonObj.getJSONObject("msg").getInt("appeal_count");
		 jsonObj = d.appeal();
		 String appeal = jsonObj.toString();
		 System.out.println("appeal:"+appeal);
		 Assert.assertEquals(jsonObj.getInt("code"), 200,"/v4/Driver/Appeal申诉内容提交接口返回状态码非200  返回内容如下："+appeal);
		boolean appealRS = jsonObj.getJSONObject("msg").getBoolean("result");
		String ret_msg = "";
		if(!appealRS){
			ret_msg=jsonObj.getJSONObject("msg").getString("ret_msg");
		}	 
		jsonObj = d.getAppealContent();
		String appealAfter = jsonObj.toString();
		System.out.println(appealAfter);
		int appeal_count2 = jsonObj.getJSONObject("msg").getInt("appeal_count");;
		if(StringUtils.isNotEmpty(ret_msg)){
			ret_msg=interfaceUtils.decode(ret_msg);
		}
		if(appeal_count-appeal_count2==0){
			Assert.assertEquals(ret_msg, "你没有申诉机会了!","司机调用申诉接口后，申诉次数数量没有减少！,申诉前--申诉--申诉后返回内容如下：；"+appealBefore+","+appeal+","+appealAfter);
		}else if(appeal_count-appeal_count2==1){
		 Assert.assertEquals(appeal_count-appeal_count2, 1,"司机调用申诉接口后，申诉次数数量没有减少！,申诉前--申诉--申诉后返回内容如下：；"+appealBefore+","+appeal+","+appealAfter);	
		}	
	}catch(Exception e){
		e.printStackTrace();
		Assert.assertTrue(false,"申诉前--申诉--申诉后流程报错，报错内容："+e.getMessage());
	}
	
}


public JSONObject appeal() throws Exception{
	//new DriverInter().initData();
	String url = constant.appdriver_Appeal;	
	HashMap<String, String> appealParams = new HashMap<String, String>();
	appealParams.put("version", version);
	appealParams.put("imei", imei);
	appealParams.put("x_auth_mode", x_auth_mode);
	appealParams.put("is_gzip", is_gzip);	
	
	appealParams.put("appeal_content", "dj");
	appealParams.put("appeal_type", "1");
	
	Object rs = HttpUtils.getIntance().doSendPost(url,appealParams,HeaderParams);
	System.out.println(rs.toString());
	return JSONObject.fromString(rs.toString());

}
public JSONObject getAppealContent() throws Exception{
	
//	new DriverInter().initData();
	String url = constant.appdriver_GetAppealContent;
	HashMap<String, String> getAppealParams = new HashMap<String, String>();
	getAppealParams.put("imei", imei);
	getAppealParams.put("version",version);
	getAppealParams.put("x_auth_mode", "client_auth");
	getAppealParams.put("is_gzip", is_gzip);	

    System.out.println(url);
	Object rs= HttpUtils.getIntance().doSendGet(url,getAppealParams,HeaderParams);
	System.out.println("getAppealContent返回结果："+rs==null?(rs=null):rs.toString());
	return JSONObject.fromString(rs.toString());
}


}
