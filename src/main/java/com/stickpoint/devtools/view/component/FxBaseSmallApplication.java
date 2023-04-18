package com.stickpoint.devtools.view.component;

import javafx.stage.Stage;

/**
 * description: FxBaseSmallApplication
 *
 * @ClassName : FxBaseSmallApplication
 * @Date 2023/1/6 10:14
 * @Author puye(0303)
 * @PackageName smallApplication.saPane
 */
public interface FxBaseSmallApplication {

    /**
     * 显示界面
     * @param stage 传入一个Stage 舞台对象
     */
    void show(Stage stage);

    /**
     * 销毁微应用
     * 销毁微应用注意事项
     * 销毁是指彻底从内存中移除数据，降低内存占用
     */
    @SuppressWarnings("unused")
    void destroy();

    /**
     * 关闭微应用
     * 关闭微应用并不会从内存中去移除数据，关闭仍旧会占用系统内存空间
     */
    @SuppressWarnings("unused")
    void close();

}
