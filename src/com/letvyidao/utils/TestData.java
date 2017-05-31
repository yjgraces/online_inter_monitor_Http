package com.letvyidao.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {

	private String packageName;
	private String host;
	private String uri;
	private String methodType;
	private String id;
	private String testName;
	private Map<String,String> headerParamsMap;
	private Map<String,String> paramsMap;
	private ArrayList<?> paramsList;
	private Map<String,String> fileMap;
	private String url;



	public Map<String, String> getFileMap() {
		if(fileMap==null){
			System.out.println("fileMap为空，请检查配置文件");
		}
		
		return fileMap;
	}
	public void setFileMap(Map<String, String> fileMap) {
		this.fileMap = fileMap;
	}
	public Map<String, String> getParamsMap() {
		return paramsMap;
	}
	public void setParamsMap(Map<String, String> paramsMap) {
		this.paramsMap = paramsMap;
	}
	public String getUrl() {
		url=host+uri;
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Map<String, String> getHeaderParamsMap() {
		return headerParamsMap;
	}
	public void setHeaderParamsMap(Map<String, String> headerParamsMap) {
		this.headerParamsMap = headerParamsMap;
	}

	public ArrayList<?> getParamsList() {
		
		return paramsList;
	}
	public void setParamsList(ArrayList<?> paramsList) {
		this.paramsList = paramsList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
