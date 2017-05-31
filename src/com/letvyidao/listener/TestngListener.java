package com.letvyidao.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.letvyidao.utils.AlarmRecordUtil;
import com.letvyidao.utils.MailTest;
import com.letvyidao.utils.PSFClient;

public class TestngListener extends TestListenerAdapter{
	private  static Logger logger = LoggerFactory.getLogger(TestngListener.class); 
    @Override
    public void onTestFailure(ITestResult tr) {
    	// TODO Auto-generated method stub
    	super.onTestFailure(tr);
    	System.out.println("onTestFailure  fail");
    }
	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
	
//		logger.info(tr.getName() + " Skipped");
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		try{
			System.out.println("success");
			AlarmRecordUtil.alarmRecord(tr.getName(),2);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		logger.info(tr.getTestClass().getName()+"."+tr.getName() + " Success");
	}

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info(tr.getName() + " Start");
	}

	@Override
	public void onFinish(ITestContext testContext) {

		super.onFinish(testContext);

	}
}
