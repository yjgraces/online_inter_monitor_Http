package com.letvyidao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class ReadYaml {
	public static Logger log = Logger.getLogger(ReadYaml.class);

	@SuppressWarnings("unchecked")

	// params 是以list形式保存
	public static ArrayList<TestData> readParamsYamlList(String filePath) {
		Yaml yaml = new Yaml();
		TestData testData;
		ArrayList<TestData> testDataList = new ArrayList<TestData>();
		try {
			Object rs = yaml.load(new FileInputStream(new File(filePath)));

			if (!(rs.equals(null))) {
				System.out.println("Start init Yaml File");
				log.info("Start init Yaml File");
				Map<?, ?> result = (Map<?, ?>) rs;
				Map<?, ?> taskMap = new HashMap<>();
				ArrayList<?> taskList = new ArrayList<>();
				Map<String, String> headerParamsMap = new HashMap<>();
				ArrayList<?> paramsList = new ArrayList<>();
				Map<String, String> fileMap = new HashMap<String, String>();

				for (Map.Entry<?, ?> entry : result.entrySet()) {

					if (((String) entry.getKey()).equalsIgnoreCase("taskList")) {
						taskList = (ArrayList<?>) entry.getValue();
						if (taskList.size() > -1) {
							for (int i = 0; i < taskList.size(); i++) {
								taskMap = (Map<?, ?>) taskList.get(i);
								testData = new TestData();
								for (Map.Entry<?, ?> entryTaskList : taskMap.entrySet()) {

									// 获取packageName,host,port
									testData.setPackageName(result.get("packageName").toString().trim());
									testData.setHost(result.get("host").toString().trim());

									// 获取tasklist详细参数
									if (((String) entryTaskList.getKey()).equalsIgnoreCase("headers")) {
										headerParamsMap = (Map<String, String>) entryTaskList.getValue();
										testData.setHeaderParamsMap(headerParamsMap);
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("paramsList")) {
										paramsList = (ArrayList<?>) entryTaskList.getValue();
										testData.setParamsList(paramsList);
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("filePath")) {
										fileMap = (Map<String, String>) entryTaskList.getValue();
										testData.setFileMap(fileMap);
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("id")) {
										testData.setId(entryTaskList.getValue().toString().trim());
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("testName")) {
										testData.setTestName(entryTaskList.getValue().toString().trim());
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("methodType")) {
										testData.setMethodType(entryTaskList.getValue().toString().trim());
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("uri")) {
										testData.setUri(entryTaskList.getValue().toString().trim());
									} else {

									}
								}
								testDataList.add(testData);
							}
						} else {
							// System.out.println("taskList为空");
						}

					} else {
						// System.out.println("init Yaml File finished");
					}
				}

			} else {
				log.info("Test Yaml config File is null,please check!");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testDataList;
	}

	/*
	 * 读取yaml文件到TestDataList  demo:login2.yaml
	 */

	@SuppressWarnings("unchecked")
	public static ArrayList<TestData> readParamsYamlMap(String filePath) {
		Yaml yaml = new Yaml();
		TestData testData;
		ArrayList<TestData> testDataList = new ArrayList<TestData>();
		try {
			Object rs = yaml.load(new FileInputStream(new File(filePath)));

			if (!(rs.equals(null))) {
				System.out.println("Start init Yaml File");
				log.info("Start init Yaml File");
				Map<?, ?> result = (Map<?, ?>) rs;
				Map<?, ?> taskMap = new HashMap<>();
				ArrayList<?> taskList = new ArrayList<>();
				Map<String, String> headerParamsMap = new HashMap<String, String>();
				Map<String, String> paramsMap = new HashMap<String, String>();

				Map<String, String> fileMap = new HashMap<String, String>();

				for (Map.Entry<?, ?> entry : result.entrySet()) {
					if (((String) entry.getKey()).equalsIgnoreCase("taskList")) {
						taskList = (ArrayList<?>) entry.getValue();
						if (taskList.size() > -1) {
							for (int i = 0; i < taskList.size(); i++) {
								taskMap = (Map<?, ?>) taskList.get(i);
								testData = new TestData();
								for (Map.Entry<?, ?> entryTaskList : taskMap.entrySet()) {

									// 获取packageName,host,port
									testData.setPackageName(result.get("packageName").toString().trim());
									testData.setHost(result.get("host").toString().trim());

									// 获取tasklist详细参数
									if (((String) entryTaskList.getKey()).equalsIgnoreCase("headers")) {
										Map<String, String> tempHeaderMap = new HashMap<String, String>();
										tempHeaderMap = (Map<String, String>) entryTaskList.getValue();
										for (Map.Entry<String, String> entryTemp1 : tempHeaderMap.entrySet()) {
											headerParamsMap.put(entryTemp1.getKey().toString().trim(),
													entryTemp1.getValue().toString().trim());
										}

										testData.setHeaderParamsMap(headerParamsMap);
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("params")) {
										Map<String, String> tempMap = new HashMap<String, String>();
										tempMap = (Map<String, String>) entryTaskList.getValue();

										for (Map.Entry<?, ?> entryTemp : tempMap.entrySet()) {
											paramsMap.put(entryTemp.getKey().toString().trim(),
													entryTemp.getValue().toString().trim());
										}

										// paramsMap = (Map<String, String>)
										// entryTaskList.getValue();
										testData.setParamsMap(paramsMap);
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("filePath")) {
										fileMap = (Map<String, String>) entryTaskList.getValue();
										testData.setFileMap(fileMap);
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("id")) {
										testData.setId(entryTaskList.getValue().toString().trim());
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("testName")) {
										testData.setTestName(entryTaskList.getValue().toString().trim());
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("methodType")) {
										testData.setMethodType(entryTaskList.getValue().toString().trim());
									} else if (((String) entryTaskList.getKey()).equalsIgnoreCase("uri")) {
										testData.setUri(entryTaskList.getValue().toString().trim());
									} else {

									}
								}
								testDataList.add(testData);
							}
						} else {
							// System.out.println("taskList为空");
						}

					} else {
						// System.out.println("init Yaml File finished");
					}
				}

			} else {
				log.info("Test Yaml config File is null,please check!");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testDataList;
	}
	
}
