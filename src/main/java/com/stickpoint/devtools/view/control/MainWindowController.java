package com.stickpoint.devtools.view.control;

import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.entity.MenuItemEntity;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.view.page.MainWindowApplication;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Objects;

public class MainWindowController {

    private static final Logger log = LoggerFactory.getLogger(MainWindowApplication.class);

    @FXML
    public RXAvatar userAvatar;

    public AnchorPane mainWindowHeaderPane;

    public BorderPane mainPane;

    public ScrollPane scrollPane;
    /**
     * 当前时间
     */
    public Label currentTime;
    /**
     * ip地址
     */
    public Label ipAddress;

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
        //FXMLLoader toolCenter = SysCache.PAGE_MAP.get(PageEnums.TOOL_CENTER.getRouterId());
        //Parent root = toolCenter.getRoot();
        //if (Objects.isNull(scrollPane)) {
        //    log.info("1111");
        //}
        //scrollPane.setContent(root);
        FXMLLoader functionCenterLoader = SysCache.PAGE_MAP.get(PageEnums.FUNCTION_CENTER.getRouterId());
        Parent functionCenterRoot = functionCenterLoader.getRoot();
        ObservableMap<String, Object> namespace = functionCenterLoader.getNamespace();
        TreeView<MenuItemEntity> menuTree = (TreeView<MenuItemEntity>) namespace.get(AppEnums.FUNCTION_CENTER_MENU_PANE_FX_ID_INFO.getInfoValue());
        TreeItem<MenuItemEntity> rootNode = new TreeItem<>();
        for (int i = 0; i < 20; i++) {
            MenuItemEntity menuItemEntity = new MenuItemEntity();
            menuItemEntity.setIcon(new Region());
            menuItemEntity.setId(i);
            menuItemEntity.setName(new Label("测试"+i));
            TreeItem<MenuItemEntity> child = new TreeItem<>();
            rootNode.getChildren().add(child);
        }
        menuTree.setRoot(rootNode);
        mainPane.setLeft(functionCenterRoot);
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