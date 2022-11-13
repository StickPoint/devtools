package com.stickpoint.devtools.view.control;
import com.stickpoint.devtools.view.page.AboutPage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description: SystemSetCenterController
 *
 * @ClassName : SystemSetCenterController
 * @Date 2022/11/3 12:55
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.view.control
 */
public class SystemSetCenterController {

    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(SystemSetCenterController.class);

    /**
     * 这里引用了关于页面
     * 但是关于页面是通过Application的Init方法中加载的FXML文件
     * 因此在Init中需要将AboutPage的加载器放置在系统设置中心的前面加载
     * 又因为系统加载器是在首页的initialize中初始化加载的，因此我们需要
     * 在初始化加载之前对他进行load。
     */
    private Stage aboutStage;
    /**
     * 根节点，这个页面的根Node
     */
    public AnchorPane rootPane;
    /**
     * 退出系统按钮点击区域
     */
    public HBox exitSystem;

    /**
     * 初始化方法
     * 在这里，对关于页面进行了初始化操作，实际load操作实在Application中进行FXML文件装载的
     * 只有当FXML文件装在完成比，才能使用这个Root对象
     */
    @FXML
    public void initialize(){
        aboutStage = AboutPage.getAboutPage();
    }

    /**
     * 点击了退出系统
     */
    @FXML
    public void exitSystem() {
        Platform.exit();
    }

    public void showAboutPage() {
        Platform.runLater(()->{
            try {
                aboutStage.show();
            } catch (Exception e) {
                log.error("出现异常：",e);
            }
            // 监听当前窗口焦点事件
            aboutStage.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(observable.getValue())) {
                    log.info(oldValue.toString());
                    log.info(newValue.toString());
                    aboutStage.close();
                }
            });
        });
    }
}
