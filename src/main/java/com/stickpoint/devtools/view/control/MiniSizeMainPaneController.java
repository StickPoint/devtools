package com.stickpoint.devtools.view.control;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.view.component.MyTray;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Objects;

/**
 * @author puye(0303)
 * @since 2023/4/27
 */
public class MiniSizeMainPaneController {

    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(MiniSizeMainPaneController.class);
    /**
     * 搜索输入框
     */
    public TextField searchInput;
    /**
     * 提示文本框
     */
    public Label promoteText;

    public AnchorPane rootPane;

    /**
     * 初始化
     */
    @FXML
    public void initialize(){
        promoteText.setDisable(true);
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > oldValue.length()) {
                log.info(newValue);
                promoteText.setStyle("-fx-text-fill: transparent");
            }
            if (Objects.isNull(searchInput.getText())||searchInput.getText().length()==0) {
                promoteText.setStyle("-fx-text-fill: #00000078");
            }
        });
        searchInput.setOnInputMethodTextChanged(event -> {
            String committed = event.getCommitted();
            if (Objects.nonNull(committed)) {
                searchInput.setText(searchInput.getText() + committed);
                searchInput.positionCaret(searchInput.getText().length());
            }
        });
        initInnerComponent();
    }

    private void initBackToGround(){
        searchInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

            }
        });
    }

    private void initInnerComponent() {
        FXMLLoader sysTrayLoader = SysCache.PAGE_MAP.get(PageEnums.SYSTEM_TRAY.getRouterId());
        Region region = sysTrayLoader.getRoot();
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/min.png"));
        Platform.runLater(() -> {
            MyTray myTray = new MyTray(image, AppEnums.APPLICATION_NAME.getInfoValue(),region);
            SysCache.NODE_MAP.put(AppEnums.APPLICATION_TRAY.getInfoValue(), myTray);
        });
    }



}


