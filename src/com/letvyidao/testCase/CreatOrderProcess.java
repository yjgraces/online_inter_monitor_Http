package com.letvyidao.testCase;



import com.letvyidao.BaseRequest;
import com.letvyidao.inter.UserAppApiInter;
import com.letvyidao.inter.getUserApiToken;
import com.letvyidao.utils.HttpUtils;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreatOrderProcess extends BaseRequest {
	public static String accessToken;
	public static String order_Id;
	public static String driver_Id;
	public String time;
	UserAppApiInter ua=new UserAppApiInter();
	
	
	/* testcase	
	 * 1.乘客端预估价格--创建订单--获取订单状态--取消	
	 * 2.乘客端预估价格--创建订单--司机选车--用户选司机--取消
	 */
		@BeforeClass
	//	@Test
		public void initData(){			
		//accessToken=getUserApiToken.initOauth2Token();	
			accessToken="c9da39bc60d07f4c3217ec5723c83b8ab69e958c";
		//	accessToken="fd24076af925a228989566aec1062f3efb96f215";

		}

		@Test(description="AppApi全流程验证：获取价格预估-创建系统决策订单-获取订单状态（派单成功）-取消订单")
		public void sysDispatchOrder(){
			//String order_Id;
			ua.orderEstimate("getestimatedcost.txt",accessToken);
//			order_Id=ua.creatOrder("createorder.txt",accessToken);
//			ua.orderStatus(accessToken,order_Id);	
//			ua.orderCancel(accessToken,order_Id);						
		}
		
		@Test(description="AppApi全流程验证：获取价格预估-创建用户决策订单-获取司机列表(获取成功)-选择司机-取消订单")
		public void userDispatchOrder(){
			ua.orderEstimate("getestimatedcost.txt",accessToken);
			order_Id=ua.creatOrder("createUserDispatchOrder.txt",accessToken);
			driver_Id=ua.acceptCar(accessToken,order_Id);
			ua.decisionDriver(accessToken,order_Id,driver_Id);
			ua.orderCancel(accessToken,order_Id);
		}
		
		
		@Test
		public void test(){
			HashMap<String,String> map=new HashMap<String,String>();

			
			String url="http://106.75.95.113/yibot/query";
			
				map.put("account", "acct123");
				map.put("cid", "123");
				map.put("ip", "120.24.19.73");
				map.put("pubkey", "VNNJ52PDeoVA3PvIYjvvMoq5FFWZBSgH2RADfS9UjW0");
				map.put("question","我要打车");
				map.put("sessionId", "sid123");
				map.put("source", "test");
				//ip=120.24.19.73&account=acct123&sessionId=sid123&source=test
				
			//	String sign=GetYibotSign.getSign(map);	
			//	System.out.println("sign:======="+sign);
				
				String sign="f075f131151f82e675ff69e5a397048b";
				map.put("sign", sign);
				//map.put("pubkey", "VNNJ52PDeoVA3PvIYjvvMoq5FFWZBSgH2RADfS9UjW0");
					
				Object rs = HttpUtils.getIntance().doSendGet(url,map);	
				
				System.out.println("response:===="+rs.toString());
		}
}
