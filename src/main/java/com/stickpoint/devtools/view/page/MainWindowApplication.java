package com.stickpoint.devtools.view.page;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * @author puye(0303)
 */
public class MainWindowApplication extends Application {
    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(MainWindowApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = SysCache.PAGE_MAP.get(PageEnums.MAIN_WINDOWS.getRouterId());
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("鑫软助手");
        try {
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/img/logo.png")).toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.show();
        Platform.setImplicitExit(false);
        stage.setOnCloseRequest(event -> {
            if(stage.isIconified()){
                stage.setIconified(false);
            }
        });
        SysCache.NODE_MAP.put(AppEnums.APPLICATION_MAIN_STAGE.getInfoValue(),stage);
    }


    /**
     * 装载页面loader
     */
    @Override
	public void init() {
        FXMLLoader mainWindowLoader = new FXMLLoader(PageEnums.MAIN_WINDOWS.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.MAIN_WINDOWS.getRouterId(), mainWindowLoader);
        FXMLLoader leftMenuCenterLoader = new FXMLLoader(PageEnums.LEFT_MENU_CENTER.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.LEFT_MENU_CENTER.getRouterId(), leftMenuCenterLoader);
        FXMLLoader bottomCenterLoader = new FXMLLoader(PageEnums.BOTTOM_CENTER.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.BOTTOM_CENTER.getRouterId(), bottomCenterLoader);
        FXMLLoader systemStatusLoader = new FXMLLoader(PageEnums.SYSTEM_STATUS.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.SYSTEM_STATUS.getRouterId(), systemStatusLoader);
        FXMLLoader devAssistantLoader = new FXMLLoader(PageEnums.DEV_ASSISTANT.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.DEV_ASSISTANT.getRouterId(), devAssistantLoader);
        FXMLLoader sysAssistantLoader = new FXMLLoader(PageEnums.SYS_ASSISTANT.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.SYS_ASSISTANT.getRouterId(), sysAssistantLoader);
        FXMLLoader webAssistantLoader = new FXMLLoader(PageEnums.WEB_ASSISTANT.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.WEB_ASSISTANT.getRouterId(), webAssistantLoader);
        FXMLLoader optionAssistantLoader = new FXMLLoader(PageEnums.OP_ASSISTANT.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.OP_ASSISTANT.getRouterId(), optionAssistantLoader);
        FXMLLoader toastLoader = new FXMLLoader(PageEnums.COMPONENT_TOAST.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.COMPONENT_TOAST.getRouterId(), toastLoader);
        FXMLLoader aboutLoader = new FXMLLoader(PageEnums.ABOUT_PAGE.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.ABOUT_PAGE.getRouterId(), aboutLoader);
        FXMLLoader systemTrayLoader = new FXMLLoader(PageEnums.SYSTEM_TRAY.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.SYSTEM_TRAY.getRouterId(), systemTrayLoader);
        FXMLLoader weatherLoader = new FXMLLoader(PageEnums.SMALL_APP_WEATHER.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.SMALL_APP_WEATHER.getRouterId(), weatherLoader);
        log.info("装载所有页面加载器完毕"); log.info("开始加载各种页面");
        Parent sysStatusRootNode = null;
        Parent devAssistantRootNode = null;
        Parent sysAssistantRootNode = null;
        Parent webAssistantRootNode = null;
        Parent opAssistantRootNode = null;
        try {
            toastLoader.load();
            aboutLoader.load();
            systemTrayLoader.load();
            // bottom内部用到的pane需要在bottom之前加载
            bottomCenterLoader.load(); log.info("底部面板加载完毕~");
            leftMenuCenterLoader.load(); log.info("左侧菜单面板加载完毕~");
            sysStatusRootNode = systemStatusLoader.load(); log.info("系统面板页面加载完毕~");
            devAssistantRootNode = devAssistantLoader.load(); log.info("开发助手菜单页面加载完毕~");
            opAssistantRootNode = optionAssistantLoader.load(); log.info("运维助手面板页面装载完毕~");
            webAssistantRootNode = webAssistantLoader.load(); log.info("网络助手面板页面装载完毕~");
            sysAssistantRootNode = sysAssistantLoader.load(); log.info("系统助手面板页面装载完毕~");
        } catch (IOException e) {
            log.error("页面加载失败,出现了异常，异常信息如下：-- \n %s",e);
        }
        log.info("所有菜单面板加载完毕~");
        ScrollPane sysStatusScrollPane = createCommonScrollPaneRoot(sysStatusRootNode);
        ScrollPane devAssistantScrollPane = createCommonScrollPaneRoot(devAssistantRootNode);
        ScrollPane opAssistantScrollPane = createCommonScrollPaneRoot(opAssistantRootNode);
        ScrollPane webAssistantScrollPane = createCommonScrollPaneRoot(webAssistantRootNode);
        ScrollPane sysAssistantScrollPane = createCommonScrollPaneRoot(sysAssistantRootNode);
        SysCache.MENU_LIST.add(sysStatusScrollPane);
        SysCache.MENU_LIST.add(devAssistantScrollPane);
        SysCache.MENU_LIST.add(opAssistantScrollPane);
        SysCache.MENU_LIST.add(webAssistantScrollPane);
        SysCache.MENU_LIST.add(sysAssistantScrollPane);
        log.info("所有页面加载完毕，开始进入主页面~");
    }

    /**
     * 基于根节点构建滑动容器
     * @param rootNode 根节点
     * @return 返回一个滑动容器
     */
    private ScrollPane createCommonScrollPaneRoot(Parent rootNode){
        ScrollPane scrollPane = new ScrollPane(rootNode);
        scrollPane.setId(rootNode.getId());
        try {
            // 滑动pane容器设置样式
            scrollPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/scrollPane-common.css")).toURI().toString());
            // 界面初始化默认仪表盘在最前显示
            scrollPane.toFront();
            // 监听鼠标移入移出
            scrollPaneMouseEventStyleChange(scrollPane);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return scrollPane;
    }

    /**
     * 更改scrollPane的鼠标移入移出事件样式
     * @param scrollPane 滚动面板
     */
    private void scrollPaneMouseEventStyleChange(ScrollPane scrollPane){
        scrollPane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            log.info("当前鼠标移动策略：{}" , scrollPane.getVbarPolicy().name());
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        });
        scrollPane.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
            log.info("当前鼠标移动策略：{}", scrollPane.getVbarPolicy().name());
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        });
    }

    /**
     * 通过加载远程系统配置然后将远程配置加载进入内存中
     */
    private void initSystemProperties() {
        // TODO init systemProperties here ,while this application started
        // TODO load systemTools

    }

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.exit(-1);
	}
}