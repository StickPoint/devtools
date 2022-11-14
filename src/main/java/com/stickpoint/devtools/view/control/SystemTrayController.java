package com.stickpoint.devtools.view.control;

import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.enums.AppEnums;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * description: SystemTrayController
 *
 * @ClassName : SystemTrayController
 * @Date 2022/11/14 18:22
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.view.control
 */
public class SystemTrayController {

    @FXML
    public HBox openOriginalPage;

    @FXML
    public void initialize(){
        initOpenOriginalPage();
    }

    /**
     * 初始化监听-> 还原页面显示的按钮
     */
    private void initOpenOriginalPage() {
        openOriginalPage.setOnMouseClicked(event -> {
            Stage mainStage = (Stage) SysCache.NODE_MAP.get(AppEnums.APPLICATION_MAIN_STAGE.getInfoValue());
            mainStage.show();
        });
    }

}
