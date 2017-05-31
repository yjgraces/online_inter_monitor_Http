package com.letvyidao.testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRetry {

	@Test(description="testRetry test")
	public void testRetry(){
		
		boolean flag=false;
		System.out.println("test111111111");

			//	boolean flag=false;
		Assert.assertEquals(flag, true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(description="testRetry1 test")
	public void testRetry1(){
		
		boolean flag=true;
		System.out.println("test22222222");

			//	boolean flag=false;
		Assert.assertEquals(flag, true);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
