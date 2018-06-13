package org.gege.utils;

import java.io.IOException;
import java.util.Properties;

public class Utils {
	/**
	 * 根据键读取属性文件的值
	 * @param properityFileName 配置文件名称
	 * @param key 配置文件的键
	 * @return  配置文件的键的值
	 */
	public static String readProperity(String key){
		return readProperity("application.properties",key);
	}
	/**
	 * 根据键读取属性文件的值
	 * @param properityFileName 配置文件名称
	 * @param key 配置文件的键
	 * @return  配置文件的键的值
	 */
	public static String readProperity(String properityFileName,String key){
		Properties p = new Properties();
		try {
			p.load(Utils.class.getClassLoader().getResourceAsStream(properityFileName));
			return p.getProperty(key);
		} catch (IOException e) {
			throw new RuntimeException("配置文件不存在");
		}
	}
}
