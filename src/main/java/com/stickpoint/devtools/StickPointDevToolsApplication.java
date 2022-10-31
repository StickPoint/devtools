package com.stickpoint.devtools;

import com.stickpoint.devtools.view.page.MainWindowApplication;

import javafx.application.Application;

import java.io.IOException;
import java.util.Properties;

/**
 * @author puye(0303)
 */
public class StickPointDevToolsApplication{
	
	/**
	 * 内网助手入口：启动内网助手
	 * @param args 调配java-jvm & java-env环境参数
	 */
	public static void main(String[] args) {
		// 配置Java-Env可变参数
		try {
			Properties properties = new Properties();
			properties.load(StickPointDevToolsApplication.class.getClassLoader().getResourceAsStream("local.properties"));
			properties.forEach((key,value)-> System.setProperty((String) key,(String) value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Application.launch(MainWindowApplication.class,args);
	}

}
