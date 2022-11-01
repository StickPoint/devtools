package com.stickpoint.devtools.view.control;
import com.stickpoint.devtools.common.enums.AppEnums;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * description: FunctionCenterController
 *
 * @ClassName : FunctionCenterController
 * @Date 2022/10/28 12:30
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.view.control
 */
public class FunctionCenterController {

    public AnchorPane parentNode;
    public Pane statusPane;
    public Pane systemPane;
    public Pane developPane;
    public Pane opPane;
    public Pane innerNetPane;
    /**
     * 节点缓存集合
     */
    private static final List<Node> NODE_LIST = new ArrayList<>();
    public VBox menuPane;

    @FXML
    public void initialize(){
        // 初始化时候点击一次
        changeBackgroundStyle();
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
        // 获取菜单子节点
        Node currentLittleNode = currentNode.getChildren ().get (2);
        currentLittleNode.setStyle (" -fx-background-color: #b7b7b7;");
        Node selectedTextNode = currentNode.getChildren().get(1);
        // 设置菜单文本高亮
        if(Objects.nonNull(selectedTextNode.getId())&& AppEnums.LEFT_PANE_MENU_TEXT_ID.getInfoValue().equals(selectedTextNode.getId())) {
            // 菜单文字高亮
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

}
