package com.stickpoint.devtools.view.control;

import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.service.IApplicationService;
import com.stickpoint.devtools.service.impl.ApplicationServiceImpl;
import com.stickpoint.devtools.view.component.ToastDialog;
import com.stickpoint.devtools.view.page.MainWindowApplication;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * @BelongsProject: devtools
 * @BelongsPackage: com.stickpoint.devtools.view.control
 * @Author: fntp
 * @CreateTime: 2022-10-29  10:40
 * @Description: TODO
 * @Version: 1.0
 */
public class BottomCenterController {
    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(BottomCenterController.class);

    private static final IApplicationService applicationService = new ApplicationServiceImpl();

    private static final ToastDialog TOAST_DIALOG_CONTROLLER = new ToastDialog();
    /**
     * 当前时间
     */
    public Label infoLabel;
    /**
     * 当前IP地址
     */
    public Label ipAddress;
    /**
     * IP信息HBox 点击此处复制IP
     */
    public HBox ipInfoHBox;

    @FXML
    public void initialize(){
        IpInfoEntity localIpInfo = applicationService.getLocalIpInfo();
        ipAddress.setText(AppEnums.INFO_CURRENT_IP.getInfoValue().concat(localIpInfo.getIpv4Address()));
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

    /**
     * 复制本机IP信息
     */
    @FXML
    public void copyIpInfo() {
        // 要想复制IP信息进入系统剪切板，首先需要去看看ip信息有没有
        if (Objects.nonNull(ipAddress.getText())) {
            // 获得系统剪切板
            Clipboard clipboard = Clipboard.getSystemClipboard();
            // 然后执行复制粘贴数据操作
            ClipboardContent content = new ClipboardContent();
            content.putString(ipAddress.getText().replace(AppEnums.INFO_CURRENT_IP.getInfoValue(),""));
            log.info("当前机器IP信息已经写入系统剪切板了吗？写入的最终状态是：{}", clipboard.setContent(content));
            TOAST_DIALOG_CONTROLLER.showToast(AppEnums.TOAST_WARNING.getNumberInfo(), ipAddress,"信息已复制~");
        }
    }
}
