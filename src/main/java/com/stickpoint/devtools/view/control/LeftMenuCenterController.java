package com.stickpoint.devtools.view.control;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.enums.AppEnums;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description: LeftMenuCenterController
 *
 * @ClassName : LeftMenuCenterController
 * @Date 2022/10/28 12:30
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.view.control
 */
public class LeftMenuCenterController {

    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(LeftMenuCenterController.class);

    public AnchorPane parentNode;
    /**
     * 系统状态监控面板
     */
    public Pane sysStatusPane;
    /**
     * 系统助手面板
     */
    public Pane systemPane;
    /**
     * 开发助手面板
     */
    public Pane developPane;
    /**
     * 运维助手面板
     */
    public Pane opPane;
    /**
     * 内网助手面板
     */
    public Pane webPane;
    /**
     * 节点缓存集合
     */
    private static final List<Node> NODE_LIST = new ArrayList<>();
    /**
     * 菜单Vbox
     */
    public VBox menuPane;

    @FXML
    public void initialize(){
        // 初始化时候点击一次
        changeBackgroundStyle();
        // 监听系统状态面板
        setOnListenSysStatusPaneClicked();
        // 监听开发助手面板点击事件
        setOnListenDevPaneClicked();
        // 监听运维助手点击事件
        setOnListenOpPaneClicked();
        // 监听内网助手面板点击事件
        setOnListenWebPaneClicked();
        // 监听系统助手面板点击事件
        setOnListenSysPaneClicked();
    }

    /**
     * 菜单切换Style效果
     * 按钮点击事件
     */
    @FXML
    public void changeBackgroundStyle() {
        ObservableList<Node> myMusicContainerChildren = menuPane.getChildren ();
        // 第一个菜单不参与高亮显示
        myMusicContainerChildren.forEach (node -> node.setOnMouseClicked (event -> {
            // 前面都是一直在获取元素 获取到了 直接调用API 原子动作分割
            setMenuPaneSelectedBackgroundStyle (node);
        }));
    }

    /**
     * 设置菜单选项选中时候的背景效果
     * @param node 节点
     */
    public void setMenuPaneSelectedBackgroundStyle(Node node){
        // 否则不是菜单按钮，那么直接点击就好了
        NODE_LIST.addAll (menuPane.getChildren ());
        // 外层节点
        node.setStyle ("-fx-background-color: rgba(243, 243, 243, 0.99);" +
                "    -fx-effect: dropshadow(three-pass-box, #D9D9D9, 5.0,0,0, 0);" +
                "    -fx-cursor: hand;");
        Pane currentNode = (Pane) node;
        // 获取菜单子节点-最左侧的垂直Pane设置它的背景色
        Node currentLittleNode = currentNode.getChildren ().get (2);
        currentLittleNode.setStyle (" -fx-background-color: #f34600;");
        // Label标签
        Node selectedTextNode = currentNode.getChildren().get(1);
        // 设置菜单文本选中颜色
        if(Objects.nonNull(selectedTextNode.getId())&& AppEnums.LEFT_PANE_MENU_TEXT_ID.getInfoValue().equals(selectedTextNode.getId())) {
            // 菜单文字选中颜色
            selectedTextNode.setStyle("-fx-text-fill: black");
        }
        boolean remove = NODE_LIST.remove (node);
        // 处理除了选中了的menuPane之外的Pane
        if (remove) {
            NODE_LIST.forEach (child->{
                Pane menuItemPane = (Pane) child;
                // 菜单Pane透明
                menuItemPane.setStyle ("-fx-background-color: transparent");
                menuItemPane.getChildren().forEach(itemNode->{
                    if (Objects.nonNull(itemNode.getId())&& AppEnums.MENU_PANE_SELECTION_LITTLE_ID.getInfoValue().equals(itemNode.getId())) {
                        // 菜单左边Pane透明
                        itemNode.setStyle ("-fx-background-color: transparent");
                    }
                    if(Objects.nonNull(itemNode.getId())&& AppEnums.LEFT_PANE_MENU_TEXT_ID.getInfoValue().equals(itemNode.getId())) {
                        // 菜单文字高亮
                        itemNode.setStyle("-fx-text-fill: #7f7f7f");
                    }
                });
            });
        }
        NODE_LIST.clear ();
    }

    /**
     * 打开开发助手面板
     */
    public void setOnListenDevPaneClicked() {
        developPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            StackPane contentCenter = (StackPane) SysCache.NODE_MAP.get(AppEnums.APPLICATION_MAIN_STACK_PANE.getInfoValue());
            Node targetScrollPane = contentCenter.getChildren()
                    .filtered(node -> AppEnums.LEFT_PANE_MENU_ID_DEV_PANE.getInfoValue().equals(node.getId()))
                    .get(AppEnums.INDEX_ZERO.getNumberInfo());
            targetScrollPane.toFront();
            log.info("当前点击了【菜单-开发助手面板】，已经切换到了开发助手面板");
        });
    }

    /**
     * 打开运维助手
     */
    public void setOnListenOpPaneClicked() {
        opPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            StackPane contentCenter = (StackPane) SysCache.NODE_MAP.get(AppEnums.APPLICATION_MAIN_STACK_PANE.getInfoValue());
            Node targetScrollPane = contentCenter.getChildren()
                    .filtered(node -> AppEnums.LEFT_PANE_MENU_ID_OP_PANE.getInfoValue().equals(node.getId()))
                    .get(AppEnums.INDEX_ZERO.getNumberInfo());
            targetScrollPane.toFront();
            log.info("当前点击了【菜单-运维助手面板】，已经切换到了运维助手面板");
        });
    }

    /**
     * 打开内网助手
     */
    public void setOnListenWebPaneClicked() {
        webPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            StackPane contentCenter = (StackPane) SysCache.NODE_MAP.get(AppEnums.APPLICATION_MAIN_STACK_PANE.getInfoValue());
            Node targetScrollPane = contentCenter.getChildren()
                    .filtered(node -> AppEnums.LEFT_PANE_MENU_ID_WEB_PANE.getInfoValue().equals(node.getId()))
                    .get(AppEnums.INDEX_ZERO.getNumberInfo());
            targetScrollPane.toFront();
            log.info("当前点击了【菜单-网络助手面板】，已经切换到了网络助手面板");
        });
    }

    /**
     * 打开系统助手
     */
    public void setOnListenSysPaneClicked() {
        systemPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            StackPane contentCenter = (StackPane) SysCache.NODE_MAP.get(AppEnums.APPLICATION_MAIN_STACK_PANE.getInfoValue());
            Node targetScrollPane = contentCenter.getChildren()
                    .filtered(node -> AppEnums.LEFT_PANE_MENU_ID_SYS_PANE.getInfoValue().equals(node.getId()))
                    .get(AppEnums.INDEX_ZERO.getNumberInfo());
            targetScrollPane.toFront();
            log.info("当前点击了【菜单-系统助手面板】，已经切换到了系统助手面板");
        });
    }

    /**
     * 打开系统监控面板
     */
    private void setOnListenSysStatusPaneClicked(){
        sysStatusPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            StackPane contentCenter = (StackPane) SysCache.NODE_MAP.get(AppEnums.APPLICATION_MAIN_STACK_PANE.getInfoValue());
            Node targetScrollPane = contentCenter.getChildren()
                    .filtered(node -> AppEnums.LEFT_PANE_MENU_ID_SYS_STATUS_PANE.getInfoValue().equals(node.getId()))
                    .get(AppEnums.INDEX_ZERO.getNumberInfo());
            targetScrollPane.toFront();
            log.info("当前点击了【菜单-系统监控助手面板】，已经切换到了系统监控面板");
        });
    }

}
