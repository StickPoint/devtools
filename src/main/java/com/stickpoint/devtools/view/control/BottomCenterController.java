package com.stickpoint.devtools.view.control;

import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.service.IApplicationService;
import com.stickpoint.devtools.service.impl.ApplicationServiceImpl;
import com.stickpoint.devtools.view.component.MyTray;
import com.stickpoint.devtools.view.component.ToastDialog;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.io.IOException;
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
     * 底部Pane
     */
    public AnchorPane bottomPane;
    /**
     * 屏幕截图快照
     */
    public Region snapshot;
    /**
     * 最小化
     */
    public Region minimize;
    /**
     * 金点子；提示
     */
    public Region tips;
    /**
     * 天气
     */
    public Region weather;
    /**
     * 翻译页面
     */
    private ContextMenu translationMenu;
    /**
     * 天气页面
     */
    private ContextMenu weatherMenu;
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
    /**
     * 翻译
     */
    public Region translate;

    @FXML
    public void initialize(){
        IpInfoEntity localIpInfo = applicationService.getLocalIpInfo();
        ipAddress.setText(AppEnums.INFO_CURRENT_IP.getInfoValue().concat(localIpInfo.getIpv4Address()));
        initCurrentTime(infoLabel);
        initTranslation();
        initWeather();
        initMinSizeAddListener();
        initInnerComponent();
    }

    private void initInnerComponent() {
        FXMLLoader sysTrayLoader = SysCache.PAGE_MAP.get(PageEnums.SYSTEM_TRAY.getRouterId());
        Region region = sysTrayLoader.getRoot();
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/min.png"));
        Platform.runLater(() -> {
            MyTray myTray = new MyTray(image,AppEnums.APPLICATION_NAME.getInfoValue(),region);
            SysCache.NODE_MAP.put(AppEnums.APPLICATION_TRAY.getInfoValue(), myTray);
        });
    }

    /**
     * 初始化加载当前时间
     * 注意这里有一个细节问题 yyyy-MM-dd HH:mm:ss
     * 当渲染时间格式是：hh的时候，渲染的时间是12小时制度
     * 只有当时间部分是：HH的时候，才是24小时制
     *
     * Current time of initialization load
     * Note that there is a detail problem yyyy MM dd HH: mm: ss
     * When the rendering time format is: hh, the rendering time is 12 hours
     * Only when the time part is HH, it is 24-hour
     * @param timeLabel 时间便条
     */
    private void initCurrentTime(Label timeLabel){
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        EventHandler<ActionEvent> eventHandler = e-> timeLabel.setText(dateTimeFormat.format(System.currentTimeMillis()));
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
    }

    /**
     * 复制本机IP信息
     * Copy native IP information
     */
    @FXML
    public void copyIpInfo() {
        // 要想复制IP信息进入系统剪切板，首先需要去看看ip信息有没有
        // To copy the IP information into the system clipboard, you need to check whether the IP information is available
        if (Objects.nonNull(ipAddress.getText())) {
            // 获得系统剪切板 Obtain the system shear plate
            Clipboard clipboard = Clipboard.getSystemClipboard();
            // 然后执行复制粘贴数据操作 Then copy and paste the data
            ClipboardContent content = new ClipboardContent();
            content.putString(ipAddress.getText().replace(AppEnums.INFO_CURRENT_IP.getInfoValue(),""));
            log.info("当前机器IP信息已经写入系统剪切板了吗？写入的最终状态是：{}", clipboard.setContent(content));
            TOAST_DIALOG_CONTROLLER.showToast(AppEnums.TOAST_INFO.getNumberInfo(), ipAddress,"信息已复制~");
        }
    }

    /**
     * 初始化翻译页面
     */
    public void initTranslation(){
        translationMenu = new ContextMenu(new SeparatorMenuItem());
        FXMLLoader translationLoader = new FXMLLoader(PageEnums.SMALL_APP_TRANSLATION.getPageSource());
        Parent rootNode = null;
        try {
             rootNode = translationLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        translationMenu.getScene().setRoot(rootNode);
    }

    public void initWeather() {
        weatherMenu = new ContextMenu(new SeparatorMenuItem());
        FXMLLoader weatherLoader = new FXMLLoader(PageEnums.SMALL_APP_WEATHER.getPageSource());
        Parent rootNode = null;
        try {
            rootNode = weatherLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        weatherMenu.getScene().setRoot(rootNode);
    }

    @FXML
    public void showTranslate() {
        Bounds bounds = translate.localToScreen(translate.getBoundsInLocal());
        translationMenu.show(getStage(),bounds.getMaxX()-250,bounds.getMaxY()-330);
    }

    @FXML
    public void showWeather() {
        Bounds bounds = weather.localToScreen(weather.getBoundsInLocal());
        weatherMenu.show(getStage(),bounds.getMaxX()-80,bounds.getMaxY()-144);
    }

    private Stage getStage(){
        return (Stage) bottomPane.getScene().getWindow();
    }

    private void initMinSizeAddListener() {
        minimize.setOnMouseClicked(event -> {
            SystemTray systemTray = SystemTray.getSystemTray();
            MyTray myTray = (MyTray) SysCache.NODE_MAP.get(AppEnums.APPLICATION_TRAY.getInfoValue());
            try {
               systemTray.remove(myTray);
               systemTray.add(myTray);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            // 最后将主页面隐藏 Finally, hide the main page
            Stage mainStage = (Stage) SysCache.NODE_MAP.get(AppEnums.APPLICATION_MAIN_STAGE.getInfoValue());
            mainStage.close();
        });
    }

}
