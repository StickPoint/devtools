package com.stickpoint.devtools.view.control;
import com.leewyatt.rxcontrols.controls.RXAvatar;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.view.page.MainWindowApplication;
import com.stickpoint.devtools.view.router.PageEnums;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * @author puye(0303)
 */
public class MainWindowController {

    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(MainWindowApplication.class);

    @FXML
    public RXAvatar userAvatar;

    public AnchorPane mainWindowHeaderPane;

    public BorderPane mainPane;

    public StackPane contentCenter;

    public Region systemSetCenter;

    private double oldStageX;

    private double oldStageY;

    private double oldScreenX;

    private double oldScreenY;

    private ContextMenu systemSetContext;

    @FXML
    public void initialize() {
        // 拖拽header拖动
        mainWindowHeaderPane.setOnMousePressed(mouseEvent -> {
            Stage primaryStage = getCurrentStage();
            oldStageX = primaryStage.getX();
            oldStageY = primaryStage.getY();
            oldScreenX = mouseEvent.getScreenX();
            oldScreenY = mouseEvent.getScreenY();
        });
        mainWindowHeaderPane.setOnMouseDragged(mouseEvent -> {
            Stage primaryStage = getCurrentStage();
            primaryStage.setX(mouseEvent.getScreenX() - oldScreenX + oldStageX);
            primaryStage.setY(mouseEvent.getScreenY() - oldScreenY + oldStageY);
        });
        mainPane.setLeft(initLeftPane());
        mainPane.setBottom(initBottomPane());
        // 将初始化界面生成的scrollPane装在进入缓存中去
        contentCenter.getChildren().addAll(SysCache.MENU_LIST);
        initCenterContent(contentCenter);
        SysCache.NODE_MAP.put(AppEnums.APPLICATION_MAIN_STACK_PANE.getInfoValue(),contentCenter);
        // 初始化系统菜单设置
        initSystemSetCenterContext();
    }

    /**
     * 初始化左侧侧边栏
     * @return 返回一个父级组件
     */
    private Parent initLeftPane(){
        // 首先加载左侧侧边栏菜单
        FXMLLoader fxmlLoader = SysCache.PAGE_MAP.get(PageEnums.LEFT_MENU_CENTER.getRouterId());
        Parent root = fxmlLoader.getRoot();
        root.prefHeight(420);
        root.minHeight(420);
        root.maxHeight(420);
        return root;
    }

    /**
     * 初始化的
     * @param stackPane stackPane instance
     */
    private void initCenterContent(StackPane stackPane){
        // 初始化的时候，系统情况面板是处于最上面的
        Node targetScrollPane = stackPane.getChildren()
                .filtered(node -> AppEnums.LEFT_PANE_MENU_ID_SYS_STATUS_PANE.getInfoValue().equals(node.getId()))
                .get(AppEnums.INDEX_ZERO.getNumberInfo());
        log.info("初始化的时候，系统情况面板是处于最上面的 ~ 已完成界面初始化加载");
        targetScrollPane.toFront();
    }

    /**
     * 初始化底部菜单面板
     * @return 返回一个底部面板
     */
    private Parent initBottomPane(){
        // 然后加载底部菜单
        FXMLLoader bottomLoader = SysCache.PAGE_MAP.get(PageEnums.BOTTOM_CENTER.getRouterId());
        return bottomLoader.getRoot();
    }

    public void showUserInfoCard() {

    }

    public void closeUserInfoCard() {

    }

    /**
     * 获得当前stage
     * @return stage
     */
    private Stage getCurrentStage(){
       Parent rootNode =  SysCache.PAGE_MAP.get(PageEnums.MAIN_WINDOWS.getRouterId()).getRoot();
       return (Stage) rootNode.getScene().getWindow();
    }



    /**
     * 先绑定后监听显示
     */
    private void initSystemSetCenterContext(){
        systemSetContext = new ContextMenu(new SeparatorMenuItem());
        FXMLLoader systemSetCenterLoader = new FXMLLoader(PageEnums.SYSTEM_SET_CENTER.getPageSource());
        Parent systemSetCenterRoot = null;
        try {
            systemSetCenterRoot = systemSetCenterLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        systemSetContext.getScene().setRoot(systemSetCenterRoot);
    }

    @FXML
    public void showSystemSetCenter() {
        Bounds bounds = systemSetCenter.localToScreen(systemSetCenter.getBoundsInLocal());
        systemSetContext.show(getStage(),bounds.getMaxX() - 120,bounds.getMaxY() + 10);
    }

    private Stage getStage(){
        return (Stage) mainPane.getScene().getWindow();
    }
}