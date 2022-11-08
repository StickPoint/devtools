package com.stickpoint.devtools.view.control;

import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.service.IApplicationService;
import com.stickpoint.devtools.service.impl.ApplicationServiceImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @BelongsProject: devtools
 * @BelongsPackage: com.stickpoint.devtools.view.control
 * @Author: fntp
 * @CreateTime: 2022-10-29  10:40
 * @Description: TODO
 * @Version: 1.0
 */
public class BottomCenterController {

    private static final IApplicationService applicationService = new ApplicationServiceImpl();
    /**
     * 当前时间
     */
    public Label infoLabel;
    /**
     * 当前IP地址
     */
    public Label ipAddress;

    @FXML
    public void initialize(){
        IpInfoEntity localIpInfo = applicationService.getLocalIpInfo();
        ipAddress.setText("当前IP：".concat(localIpInfo.getIpv4Address()));
        initCurrentTime(infoLabel);
    }

    /**
     * 初始化加载当前时间
     * @param timeLabel 时间便条
     */
    private void initCurrentTime(Label timeLabel){
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        EventHandler<ActionEvent> eventHandler = e-> timeLabel.setText(dateTimeFormat.format(System.currentTimeMillis()));
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }
}
