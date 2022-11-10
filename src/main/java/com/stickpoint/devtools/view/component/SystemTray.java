package com.stickpoint.devtools.view.component;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author fearless
 * @version v1.0
 * @description:
 * @date 2021/12/30 下午6:23
 */
public class SystemTray extends TrayIcon {
    /**
     * 系统托盘实现
     * @param stage 传入一个stage
     * @param pane 传入一个stackPane
     * @param image 传入一个image图
     * @param tooltip 传入一个菜单
     * @param systemIcon 传入一个菜单图标
     */
    public SystemTray(Stage stage,StackPane pane,Image image, String tooltip, Region systemIcon) {
        super(image, tooltip);
        stage.setScene(new Scene(pane));
        //去掉面板的标题栏
        stage.initStyle(StageStyle.TRANSPARENT);
        //监听面板焦点
        stage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue)) {
                //如果失去焦点就隐藏面板
                stage.hide();
            }
        });
        //设置系统托盘图标为自适应
        this.setImageAutoSize(true);
        //添加组件到面板中
        pane.getChildren().add(systemIcon);
        //设置面板的宽高
        stage.setWidth(systemIcon.getPrefWidth());
        stage.setHeight(systemIcon.getPrefHeight());
        //添加鼠标事件
        this.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            //getButton() 1左键 2中键 3右键
            if (e.getButton() == 3) {
                //在JavaFx的主线程中调用javaFx组件的方法
                Platform.runLater(() -> {
                    //设置dialog的显示位置
                    stage.setX(e.getX() - (stage.getWidth() / 2));
                    stage.setY(e.getY() - stage.getHeight());
                    //设置为顶层，否则在windows系统中会被底部任务栏遮挡
                    stage.setAlwaysOnTop(true);
                    //展示/隐藏
                    if (!stage.isShowing()) {
                        stage.show();
                    } else {
                        stage.hide();
        }});}}});
    }
}