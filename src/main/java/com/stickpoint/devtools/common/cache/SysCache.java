package com.stickpoint.devtools.common.cache;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: SysCache
 *
 * @ClassName : SysCache
 * @Date 2022/10/27 12:35
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.common.cache
 */
public interface SysCache {
    /**
     * 缓存页面
     */
    Map<String, FXMLLoader> PAGE_MAP = new ConcurrentHashMap<>(10);
    /**
     * 缓存节点
     * (1) 主页面的StackPane stackPane
     * (2) 系统托盘Tray tray
     * (3) 系统Stage stage
     */
    Map<String, Object> NODE_MAP = new ConcurrentHashMap<>(3);
    /**
     * 菜单map
     */
    List<Node> MENU_LIST = new ArrayList<>();

}
