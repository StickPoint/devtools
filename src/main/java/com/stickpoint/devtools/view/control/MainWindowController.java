package com.stickpoint.devtools.view.control;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * @author puye(0303)
 */
public class MainWindowController {

    private static final Logger log = LoggerFactory.getLogger(MainWindowController.class);

    @FXML
    public RXAvatar userAvatar;

    public AnchorPane mainWindowHeaderPane;

    public BorderPane mainPane;

    public StackPane contentCenter;

    public Region systemSetCenter;

    private double oldStageX;

    private double oldStageY;

    private double oldScreenX;

    private double oldScreenY;

    private ContextMenu systemSetContext;

    @FXML
    public void initialize() {
        // 拖拽header拖动
        mainWindowHeaderPane.setOnMousePressed(mouseEvent -> {
            Stage primaryStage = getCurrentStage();
            oldStageX = primaryStage.getX();
            oldStageY = primaryStage.getY();
            oldScreenX = mouseEvent.getScreenX();
            oldScreenY = mouseEvent.getScreenY();
        });
        mainWindowHeaderPane.setOnMouseDragged(mouseEvent -> {
            Stage primaryStage = getCurrentStage();
            primaryStage.setX(mouseEvent.getScreenX() - oldScreenX + oldStageX);
            primaryStage.setY(mouseEvent.getScreenY() - oldScreenY + oldStageY);
        });
        // 首先加载左侧侧边栏菜单
        FXMLLoader fxmlLoader = SysCache.PAGE_MAP.get(PageEnums.FUNCTION_CENTER.getRouterId());
        Parent root = fxmlLoader.getRoot();
        root.prefHeight(420);
        root.minHeight(420);
        root.maxHeight(420);
        mainPane.setLeft(fxmlLoader.getRoot());
        // 然后加载底部菜单
        FXMLLoader bottomLoader = SysCache.PAGE_MAP.get(PageEnums.BOTTOM_CENTER.getRouterId());
        mainPane.setBottom(bottomLoader.getRoot());
        // 然后加载系统面板
        FXMLLoader systemStatus = SysCache.PAGE_MAP.get(PageEnums.SYSTEM_STATUS.getRouterId());
        ScrollPane scrollPane = new ScrollPane(systemStatus.getRoot());
        contentCenter.getChildren().add(scrollPane);
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
        // 将初始化界面生成的scrollPane装在进入缓存中去
        SysCache.NODE_MAP.put("scrollPane",scrollPane);
        // 初始化系统菜单
        initSystemSetCenterContext();
    }

    public void showUserInfoCard() {

    }

    public void closeUserInfoCard() {

    }

    /**
     * 获得当前stage
     * @return stage
     */
    private Stage getCurrentStage(){
       Parent rootNode =  SysCache.PAGE_MAP.get(PageEnums.MAIN_WINDOWS.getRouterId()).getRoot();
       return (Stage) rootNode.getScene().getWindow();
    }

    /**
     * 更改scrollPane的鼠标移入移出事件样式
     * @param scrollPane 滚动面板
     */
    private void scrollPaneMouseEventStyleChange(ScrollPane scrollPane){
        scrollPane.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            log.info("当前鼠标移动策略：" + scrollPane.getVbarPolicy().name());
            scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);

        });
        scrollPane.addEventHandler(MouseEvent.MOUSE_ENTERED,event -> {
            log.info("当前鼠标移动策略：" + scrollPane.getVbarPolicy().name());
            scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        });
    }

    /**
     * 先绑定后监听显示
     */
    private void initSystemSetCenterContext(){
        systemSetContext = new ContextMenu(new SeparatorMenuItem());
        FXMLLoader systemSetCenterLoader = new FXMLLoader(PageEnums.SYSTEM_SET_CENTER.getPageSource());
        Parent systemSetCenterRoot = null;
        try {
            systemSetCenterRoot = systemSetCenterLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        systemSetContext.getScene().setRoot(systemSetCenterRoot);
    }

    @FXML
    public void showSystemSetCenter() {
        Bounds bounds = systemSetCenter.localToScreen(systemSetCenter.getBoundsInLocal());
        systemSetContext.show(getStage(),bounds.getMaxX() - 120,bounds.getMaxY() + 10);
    }

    private Stage getStage(){
        return (Stage) mainPane.getScene().getWindow();
    }
}