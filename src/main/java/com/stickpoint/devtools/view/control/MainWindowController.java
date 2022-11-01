package com.stickpoint.devtools.view.control;

import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.view.page.MainWindowApplication;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Objects;

/**
 * @author puye(0303)
 */
public class MainWindowController {

    private static final Logger log = LoggerFactory.getLogger(MainWindowApplication.class);

    @FXML
    public RXAvatar userAvatar;

    public AnchorPane mainWindowHeaderPane;

    public BorderPane mainPane;

    public StackPane paneCenter;

    private double oldStageX;

    private double oldStageY;

    private double oldScreenX;

    private double oldScreenY;

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
        FXMLLoader fxmlLoader = SysCache.PAGE_MAP.get(PageEnums.FUNCTION_CENTER.getRouterId());
        Parent root = fxmlLoader.getRoot();
        root.prefHeight(420);
        root.minHeight(420);
        root.maxHeight(420);
        mainPane.setLeft(fxmlLoader.getRoot());
        FXMLLoader bottomLoader = SysCache.PAGE_MAP.get(PageEnums.BOTTOM_CENTER.getRouterId());
        mainPane.setBottom(bottomLoader.getRoot());
        FXMLLoader toolCenter = SysCache.PAGE_MAP.get(PageEnums.Content_CENTER.getRouterId());
        ScrollPane scrollPane = new ScrollPane(toolCenter.getRoot());
        paneCenter.getChildren().add(scrollPane);
        try {
            // 滑动pane容器设置样式
            scrollPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/scrollPane-common.css")).toURI().toString());
            // 界面初始化默认仪表盘在最前显示
            scrollPane.toFront();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void showUserInfoCard(MouseEvent mouseEvent) {

    }

    public void closeUserInfoCard(MouseEvent mouseEvent) {

    }

    /**
     * 获得当前stage
     * @return stage
     */
    private Stage getCurrentStage(){
       Parent rootNode =  SysCache.PAGE_MAP.get(PageEnums.MAIN_WINDOWS.getRouterId()).getRoot();
       return (Stage) rootNode.getScene().getWindow();
    }
}