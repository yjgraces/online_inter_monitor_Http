package com.letvyidao;

import java.io.FileInputStream;
import java.util.Properties;

public class AlarmInfos {
private static Properties alerminfoProperty = null;
public static Properties getAlarmInfoProperty(){
	if(alerminfoProperty==null){
		alerminfoProperty = new Properties();
		try{
			String filepath = System.getProperty("user.dir")+"/dataFiles/mailphone.properties";
			FileInputStream fios = new FileInputStream(filepath);
			alerminfoProperty.load(fios);	
			fios.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	return alerminfoProperty;
}

}
