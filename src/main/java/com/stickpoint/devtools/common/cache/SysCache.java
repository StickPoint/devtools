package com.stickpoint.devtools.common.cache;

import javafx.fxml.FXMLLoader;

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

    Map<String, FXMLLoader> PAGE_MAP = new ConcurrentHashMap<>(10);


}
