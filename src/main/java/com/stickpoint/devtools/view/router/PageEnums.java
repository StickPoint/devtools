package com.stickpoint.devtools.view.router;


import com.stickpoint.devtools.view.component.ToastDialog;
import com.stickpoint.devtools.view.control.BottomCenterController;
import com.stickpoint.devtools.view.control.DevAssistantController;
import com.stickpoint.devtools.view.control.LeftMenuCenterController;
import com.stickpoint.devtools.view.control.MainWindowController;
import com.stickpoint.devtools.view.control.OptionAssistantController;
import com.stickpoint.devtools.view.control.SaTranslateController;
import com.stickpoint.devtools.view.control.SysAssistantController;
import com.stickpoint.devtools.view.control.SystemSetCenterController;
import com.stickpoint.devtools.view.control.SystemStatusController;
import com.stickpoint.devtools.view.control.SystemTrayController;
import com.stickpoint.devtools.view.control.WebAssistantController;
import com.stickpoint.devtools.view.page.AboutPage;

import java.net.URL;

/**
 * @author fntp
 * @version v0.0.1
 * @date 2022/9/11
 * @description 页面路由中心
 */
public enum PageEnums {
	/**
     * 首页页面
     */
    MAIN_WINDOWS(MainWindowController.class, MainWindowController.class.getResource("/fxml/mainWindow.fxml"),"mainWindow"),
	/**
	 * 系统组件--吐司弹窗
	 */
	COMPONENT_TOAST(ToastDialog.class, ToastDialog.class.getResource("/fxml/toast.fxml"),"toast"),
	/**
	 * 系统设置菜单
	 */
	SYSTEM_SET_CENTER(SystemSetCenterController.class,SystemSetCenterController.class.getResource("/fxml/systemSetCenter.fxml"),"systemSetCenter"),
	/**
	 * 翻译程序页面
	 */
	SMALL_APP_TRANSLATION(SaTranslateController.class,SaTranslateController.class.getResource("/fxml/saTranslate.fxml"),"translation"),
	/**
	 * 菜单栏页面
	 */
    LEFT_MENU_CENTER(LeftMenuCenterController.class, LeftMenuCenterController.class.getResource("/fxml/leftMenuCenter.fxml"),"leftMenuCenter"),
	/**
	 * 主面板页面
	 */
	SYSTEM_STATUS(SystemStatusController.class, SystemStatusController.class.getResource("/fxml/systemStatus.fxml"),"systemStatus"),
	/**
	 * 开发助手
	 */
	DEV_ASSISTANT(DevAssistantController.class,DevAssistantController.class.getResource("/fxml/devAssistant.fxml"),"devAssistant"),
	/**
	 * 系统助手
	 */
	SYS_ASSISTANT(SysAssistantController.class,SysAssistantController.class.getResource("/fxml/sysAssistant.fxml"),"sysAssistant"),
	/**
	 * 运维助手
	 */
	OP_ASSISTANT(OptionAssistantController.class,OptionAssistantController.class.getResource("/fxml/opAssistant.fxml"),"opAssistant"),
	/**
	 * 网络助手
	 */
	WEB_ASSISTANT(WebAssistantController.class,WebAssistantController.class.getResource("/fxml/webAssistant.fxml"),"webAssistant"),
	/**
	 * 关于页面
	 */
	ABOUT_PAGE(AboutPage.class,AboutPage.class.getResource("/fxml/about.fxml"),"about"),
	/**
	 * 天气页面
	 */
	SMALL_APP_WEATHER(WebAssistantController.class,WebAssistantController.class.getResource("/fxml/saWeather.fxml"),"weather"),
	/**
	 * 系统托盘
	 */
	SYSTEM_TRAY(SystemTrayController.class, SystemTrayController.class.getResource("/fxml/systemTray.fxml"),"systemTray"),
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
	 * @description: 数据类型
	 * @return Class<?>
	 */
    public Class<?> getPageType() {
        return pageType;
    }
    
	/**
	 * 
	 * @title: getPageSource 
	 * @description: 页面内容路由
	 * @return URL 路由地址
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
