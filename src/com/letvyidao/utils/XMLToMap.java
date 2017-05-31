package com.letvyidao.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;  
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

 
import org.dom4j.Document;  
import org.dom4j.DocumentException;  
  
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;



import net.sf.json.JSONObject;  
  
/** 
 * 将XML解析成MAP  
 * @author YaoJing
 * @version 
 * @param XmlFilename xml文件名，文件默认地址在dataFiles文件夹下
 */  
public class XMLToMap {  
	
	//main方法测试
	 public static void main(String[] args) throws UnsupportedEncodingException, DocumentException {

	    	//XML转 Map
		 	XMLToMap x=new XMLToMap();
	    	
	    	Map<String,String> map1=x.XmlToMap("dispatch.xml");
	    	///service_order_id="6355984982750657836"
	    	map1.put("service_order_id", "6355984982750657836");
	    	
	    	//Map转JSON格式
	    	JSONObject jsonObject = JSONObject.fromMap(map1);
	    	String rs=jsonObject.toString();
	    	System.out.println("maptojson:"+rs);
	       	
	    	//Json转Map
	    	Map<String,String>outputmap=x.JsonToMap(jsonObject);
	    	x.PrintMap(outputmap);
	    	
	  }
	    
	    //JSON转Map
	    public Map<String,String> JsonToMap(JSONObject jsonObject){
	    	
	    	@SuppressWarnings("unchecked")
			Iterator<String> nameIt=jsonObject.keys();
	    	String name;
	    	Map<String,String> outMap=new HashMap<String,String>();
	    	while(nameIt.hasNext()){
	    		name=nameIt.next();
	    		outMap.put(name, jsonObject.getString(name));
	    	}
	    	
			return outMap;
	    	
	    }
	    
	    //打印Map
	    public void PrintMap(Map<String,String> map){
	    	System.out.println("打印map");
	    	for(Object obj : map.keySet()){
	    		Object value = map.get(obj );
	    		System.out.println(obj+":"+value);     
	    	}
	    }
	    
	    @SuppressWarnings("unchecked")
		public Map<String,String> XmlToMap(String filename){
	    	String filepath=System.getProperty("user.dir")+"/dataFiles/"+filename;
	    	XMLToMap x=new XMLToMap();
	    	SAXReader xmlReader = new SAXReader();
	    	Document doc=null;
	    	try {
	    		doc = xmlReader.read(filepath);
	    	} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	}	
	    	Map<String,String> map=(Map<String,String>)x.xml2map(doc.getRootElement());
	    	return map;       	   	   	
	    }

	    @SuppressWarnings("unchecked")
		public  Object xml2map(Element element) {
	        System.out.println(element);
	        Map<String, Object> map = new HashMap<String, Object>();
	        List<Element> elements = element.elements();
	        if (elements.size() == 0) {
	            map.put(element.getName(), element.getText());
	            if (!element.isRootElement()) {
	                return element.getText();
	            }
	        } else if (elements.size() == 1) {
	            map.put(elements.get(0).getName(), xml2map(elements.get(0)));
	        } else if (elements.size() > 1) {
	            // 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
	            // 构造一个map用来去重
	            Map<String, Element> tempMap = new HashMap<String, Element>();
	            for (Element ele : elements) {
	                tempMap.put(ele.getName(), ele);
	            }
	            Set<String> keySet = tempMap.keySet();
	            for (String string : keySet) {
	                Namespace namespace = tempMap.get(string).getNamespace();
	                List<Element> elements2 = element.elements(new QName(string, namespace));
	                // 如果同名的数目大于1则表示要构建list
	                if (elements2.size() > 1) {
	                    List<Object> list = new ArrayList<Object>();
	                    for (Element ele : elements2) {
	                        list.add(xml2map(ele));
	                    }
	                    map.put(string, list);
	                } else {
	                    // 同名的数量不大于1则直接递归去
	                    map.put(string, xml2map(elements2.get(0)));
	                }
	            }
	        }

	        return map;
	    }
	
}  