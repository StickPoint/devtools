package com.stickpoint.devtools.view.page;

import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author puye(0303)
 * @since 2023/4/27
 */
public class MiniSizeMainPanePage extends Application {

    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(MiniSizeMainPanePage.class);

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MiniSizeMainPanePage.class.getResource("/fxml/miniSizeMainPane.fxml"));
        AnchorPane rootNode = fxmlLoader.load();
        Scene scene = new Scene(rootNode, rootNode.getPrefWidth(), rootNode.getPrefHeight());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
        log.info("搜索页面显示成功~");
    }




}
