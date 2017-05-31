package com.letvyidao.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;


import org.apache.log4j.Logger;

public class InitYamlFile {
	public static Logger log = Logger.getLogger(ReadYaml.class);

	/*
	 * 通过FilePath和testName来读取TestData
	 */
	public TestData initYamlData(String filePath, String testName) {
		TestData testData = new TestData();
		ArrayList<TestData> testDataList = ReadYaml.readParamsYamlMap(filePath);
		boolean flag = false;
		try {
			for (int i = 0; i < testDataList.size(); i++) {
				if (testName.equals(testDataList.get(i).getTestName().toString().trim())) {
					testData = testDataList.get(i);
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
			if (!flag) {
				log.info("Yaml配置文件中未找到对应的testName，请检查");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testData;
	}

	public TestData initYamlData(ArrayList<TestData> testDataList, String testName) {
		TestData testData = new TestData();
		// ArrayList<TestData> testDataList =
		// ReadYaml.readParamsYamlMap(filePath);
		boolean flag = false;
		try {
			for (int i = 0; i < testDataList.size(); i++) {
				if (testName.equals(testDataList.get(i).getTestName().toString().trim())) {
					testData = testDataList.get(i);
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
			if (!flag) {
				log.info("Yaml配置文件中未找到对应的testName，请检查");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testData;
	}

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir") + "\\resources\\testdata\\pages\\login.yaml";
		InitYamlFile dr = new InitYamlFile();
		// dr.initYamlData(filePath,"testLogin");

		// TestData td=dr.initYamlData(filePath,"uploadPhoto");
		TestData td = dr.initYamlData(filePath, "testLogin");

		System.out.println(td.getUrl());
		System.out.println(td.getParamsList().get(0).toString());
		// System.out.println(td.getFileMap().toString());
	}

}
