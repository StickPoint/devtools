package com.stickpoint.devtools;
import com.stickpoint.devtools.view.page.MainWindowApplication;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Properties;

/**
 * @author puye(0303)
 */
public class StickPointDevToolsApplication{

	/**
	 * 系统日志
	 */
	private static final Logger log = LoggerFactory.getLogger(StickPointDevToolsApplication.class);
	
	/**
	 * 内网助手入口：启动内网助手
	 * @param args 调配java-jvm & java-env环境参数 jvm-maxSize jvm-minSize jvm-stackSize
	 * 单一主线程Application
	 * 加载配置文件之后，立马执行应用启动操作
	 */
	public static void main(String[] args) {
		// 配置Java-Env可变参数
		log.info("StickPoint-Devtools-Initialize has been started...");
		try {
			log.info("开始装载本地环境变量~");
			Properties properties = new Properties();
			properties.load(StickPointDevToolsApplication.class.getClassLoader().getResourceAsStream("local.properties"));
			properties.forEach((key,value)-> System.setProperty((String) key,(String) value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Application.launch(MainWindowApplication.class,args);
	}

}
