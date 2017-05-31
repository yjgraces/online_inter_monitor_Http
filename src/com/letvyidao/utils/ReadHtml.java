package com.letvyidao.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadHtml {

	static String tempString = "";
	static StringBuffer sb = new StringBuffer();
	static MailTest sendMail = new MailTest(true);
	
	
    /**
     * 读取html文件
     *
     * @param fileName
     * 		  Html文件名称，默认路径在test-output文件夹中
     */
    public static String readFileByLines(String fileName) {
       
        String filepath=System.getProperty("user.dir")+"/test-output/"+fileName;
        File file = new File(filepath);
        BufferedReader reader = null;
        try {
        	// System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	sb.append(tempString);
            }                        
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return sb.toString();
    }    
    

    /**
     * 通过邮件发送html正文
     * @param filename
     * 			Html文件名称，默认路径在test-output文件夹中
     * @param mailer
     * 			收件人邮箱
     */
    public static void sendHtmlContent(String filename,String mailer){
    	String message=ReadHtml.readFileByLines(filename);
    	//System.out.println(message);    	
    	String content = "[接口测试结果]";
    	sendMail.doSendHtmlEmail(content, message, mailer, null);
    	
    }
    
    
    public static void main(String[] args) {
    	    	
    	String filename="emailable-report.html";
    	String mailer="yaojing@yongche.com";    	
    	ReadHtml.sendHtmlContent(filename, mailer);
    	
	}
}
