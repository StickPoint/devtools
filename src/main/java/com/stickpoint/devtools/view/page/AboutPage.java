package com.stickpoint.devtools.view.page;

import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

/**
 * @BelongsProject: devtools
 * @BelongsPackage: com.stickpoint.devtools.view.page
 * @Author: fntp
 * @CreateTime: 2022-11-11  22:04
 * @Description: TODO
 * @Version: 1.0
 */
public class AboutPage extends Stage {

    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(AboutPage.class);

    private static final FXMLLoader ABOUT_LOADER;

    private static final Scene SCENE;

    private double oldStageX;

    private double oldStageY;

    private double oldScreenX;

    private double oldScreenY;

    static {
        ABOUT_LOADER = SysCache.PAGE_MAP.get(PageEnums.ABOUT_PAGE.getRouterId());
        SCENE = new Scene(ABOUT_LOADER.getRoot());
    }
    private static final AboutPage PAGE = new AboutPage();

    /**
     * Creates a new instance of decorated {@code Stage}.
     *
     * @throws IllegalStateException if this constructor is called on a thread
     *                               other than the JavaFX Application Thread.
     */
    private AboutPage() {
        // 初始化舞台样式
        initStyle(StageStyle.TRANSPARENT);
        if (Objects.isNull(getScene())) {
            setScene(SCENE);
        }
        Parent rootNode = ABOUT_LOADER.getRoot();
        // 监听拖动
        rootNode.setOnMousePressed(mouseEvent -> {
            oldStageX = this.getX();
            oldStageY = this.getY();
            oldScreenX = mouseEvent.getScreenX();
            oldScreenY = mouseEvent.getScreenY();
        });
        // 监听拖动
        rootNode.setOnMouseDragged(mouseEvent -> {
            this.setX(mouseEvent.getScreenX() - oldScreenX + oldStageX);
            this.setY(mouseEvent.getScreenY() - oldScreenY + oldStageY);
        });
    }

    /**
     * 获得关于页面
     * @return 返回一个关于Stage
     */
    public static Stage getAboutPage(){
        log.info("获得关于页面");
        return PAGE;
    }
}
