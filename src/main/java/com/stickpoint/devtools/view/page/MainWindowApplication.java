package com.stickpoint.devtools.view.page;

import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author puye(0303)
 */
public class MainWindowApplication extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainWindowApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = SysCache.PAGE_MAP.get(PageEnums.MAIN_WINDOWS.getRouterId());
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * 装载页面loader
     */
    @Override
	public void init() {
        FXMLLoader mainWindowLoader = new FXMLLoader(PageEnums.MAIN_WINDOWS.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.MAIN_WINDOWS.getRouterId(), mainWindowLoader);
        FXMLLoader toolCenterLoader = new FXMLLoader(PageEnums.Content_CENTER.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.Content_CENTER.getRouterId(), toolCenterLoader);
        FXMLLoader functionCenterLoader = new FXMLLoader(PageEnums.FUNCTION_CENTER.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.FUNCTION_CENTER.getRouterId(), functionCenterLoader);
        FXMLLoader bottomCenterLoader = new FXMLLoader(PageEnums.BOTTOM_CENTER.getPageSource());
        SysCache.PAGE_MAP.put(PageEnums.BOTTOM_CENTER.getRouterId(), bottomCenterLoader);
        log.info("装载所有页面加载器完毕");
        log.info("开始加载各种页面");
        try {
            toolCenterLoader.load();
            log.info("助手中心页面加载完毕~");
            functionCenterLoader.load();
            log.info("菜单面板加载完毕~");
            bottomCenterLoader.load();
            log.info("底部面板加载完毕~");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("页面加载失败！");
        }
        log.info("所有页面加载完毕，开始进入主页面~");
    }



	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.exit(-1);
	}
}