package org.gege.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	private PropertiesUtils(){}
	public static Properties properties = new Properties();//将sql.properties的值全部放里面
	static{
		try {
			properties.load(Utils.class.getClassLoader().getResourceAsStream("sql.properties"));
		} catch (IOException e) {
			System.out.println("读取配置文件出错");
		}
	}
	
	/**
	 * 根据key值读取指定文件的配置值
	 * @param path 路径
	 * @param key 
	 * @return 值
	 */
	public static String readProperty(String path,String key){
		try {
			Properties propert = new Properties();
			propert.load(Utils.class.getClassLoader().getResourceAsStream(path));
			return propert.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
