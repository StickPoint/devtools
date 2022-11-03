package com.stickpoint.devtools.view.router;


import com.stickpoint.devtools.view.control.*;

import java.net.URL;

/**
 * @author fntp
 * @version v0.0.1
 * @date 2022/9/11
 * @description
 */
public enum PageEnums {
    /**
     * 首页页面
     */
    MAIN_WINDOWS(MainWindowController.class, MainWindowController.class.getResource("/fxml/mainWindow.fxml"),"mainWindow"),
	/**
	 * 系统设置菜单
	 */
	SYSTEM_SET_CENTER(SystemSetCenterController.class,SystemSetCenterController.class.getResource("/fxml/systemSetCenter.fxml"),"systemSetCenter"),
	/**
	 * 菜单栏页面
	 */
    FUNCTION_CENTER(FunctionCenterController.class, FunctionCenterController.class.getResource("/fxml/functionCenter.fxml"),"functionCenter"),
	/**
	 * 主面板页面
	 */
	SYSTEM_STATUS(SystemStatusController.class, SystemStatusController.class.getResource("/fxml/systemStatus.fxml"),"systemStatus"),
	/**
	 * 开发助手
	 */
	DEV_ASSISTANT(DevAssistantController.class,DevAssistantController.class.getResource("/fxml/devAssistant.fxml"),"devAssistant"),
	/**
	 * 底部页面
	 */
	BOTTOM_CENTER(BottomCenterController.class,BottomCenterController.class.getResource("/fxml/bottomCenter.fxml") , "bottomCenter");
    /**
     * 页面名称
     */
    private final Class<?> pageType;
    /**
     * 页面路由
     */
    private final URL pageSource;
    /**
     * 页面全名称
     */
    private final String routerId;
    
	/**
	 * 
	 * 构建构造方法
	 * @param pageType 页面类型，以页面的class来区分
	 * @param pageSource 页面数据源URL，classpath下的文件
	 * @param routerId 路由id，字符串，作为组件路由的关键key
	 */
    PageEnums(Class<?> pageType, URL pageSource,String routerId){
        this.pageType = pageType;
        this.pageSource = pageSource;
        this.routerId = routerId;
    }
    
	/**
	 * 
	 * @title: getPageType 
	 * @description:
	 * @return Class<?>
	 */
    public Class<?> getPageType() {
        return pageType;
    }
    
	/**
	 * 
	 * @title: getPageSource 
	 * @description:
	 * @return URL
	 */
    public URL getPageSource() {
        return pageSource;
    }
	/**
	 * 
	 * @title: getRouterId 方法名：getRouterId
	 * @description: 此方法就是为了返回路由Id，页面的路由中，Id是唯一的。
	 * @return String 返回的是一个字符串，也就是枚举对象的路由Id
	 */
    public String getRouterId() {
        return routerId;
    }
}
