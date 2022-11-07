package com.stickpoint.devtools.common.cache;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
     */
    Map<String, Node> NODE_MAP = new ConcurrentHashMap<>(1);
    /**
     * 菜单map
     */
    List<Node> MENU_LIST = new ArrayList<>();

}
