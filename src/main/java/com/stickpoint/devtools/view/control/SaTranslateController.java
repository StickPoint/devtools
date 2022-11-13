package com.stickpoint.devtools.view.control;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: SaTranslateController
 *
 * @ClassName : SaTranslateController
 * @Date 2022/11/9 9:47
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.view.control
 */
public class SaTranslateController {
    /**
     * 译文原文切换按钮
     */
    public HBox textViewContentTap;
    /**
     * 初始化原文内容
     */
    public VBox originalContent;
    /**
     * 翻译之后的结果内容
     */
    public VBox resultContent;
    public Pane originPane;
    public Pane resultPane;
    public TextArea inputContent;
    /**
     * 文本框内的数据
     */
    private Map<Integer,String> contentMap;

    /**
     * 组件初始化方法
     */
    @FXML
    public void initialize(){
        contentMap = new ConcurrentHashMap<>(2);
        contentMap.put(0,"这是原文...............%%%%%%%%%%%%%...............................................");
        contentMap.put(1,"这是译文................@@@@@@@@@@@................................................");
        initTextViewContentTap();
    }

    /**
     * 初始化监听文本内容区域的按钮点击展示
     */
    private void initTextViewContentTap() {
        originalContent.setOnMouseClicked(event -> {
            originPane.setStyle("-fx-background-color: red;");
            resultPane.setStyle("-fx-background-color: transparent;");
            inputContent.setText(contentMap.get(0));
        });
        resultContent.setOnMouseClicked(event -> {
            resultPane.setStyle("-fx-background-color: red;");
            originPane.setStyle("-fx-background-color: transparent;");
            inputContent.setText(contentMap.get(1));
        });
    }
}
