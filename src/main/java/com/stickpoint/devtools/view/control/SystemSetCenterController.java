package com.stickpoint.devtools.view.control;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * description: SystemSetCenterController
 *
 * @ClassName : SystemSetCenterController
 * @Date 2022/11/3 12:55
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.view.control
 */
public class SystemSetCenterController {

    public AnchorPane rootPane;
    public HBox exitSystem;

    /**
     * 点击了退出系统
     */
    @FXML
    public void exitSystem() {
        System.exit(0);
    }
}
