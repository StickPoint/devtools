package com.stickpoint.devtools.service.impl;

import com.stickpoint.devtools.StickPointDevToolsApplication;
import com.stickpoint.devtools.common.exception.SmallApplicationException;
import com.stickpoint.devtools.service.IFxSmallAppPaneService;
import com.stickpoint.devtools.view.component.FxBaseSmallApplication;
import com.stickpoint.devtools.view.component.FxSmallApplicationTemplate;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

/**
 * description: SmallAppPaneImpl
 *
 * @ClassName : SmallAppPaneImpl
 * @Date 2023/1/4 16:24
 * @Author puye(0303)
 * @PackageName smallApplication.saPane
 */
public class FxSmallAppPaneServiceImpl implements IFxSmallAppPaneService {

    /**
     * jar文件加载预检
     *
     * @return 返回一个预检结果 true：预检正常；false：预检失败
     * @throws SmallApplicationException 抛出一个微应用异常
     */
    @Override
    public Boolean checkApplication(FxSmallApplicationTemplate template) throws SmallApplicationException {
        // 微应用启动预检第一步 检查文件大小是否正常
        try {
            URL url = Objects.requireNonNull(StickPointDevToolsApplication.class.getResource(template.getFileRelativePath())).toURI().toURL();
            URL[] urls = new URL[]{ url };
            URLClassLoader loader = new URLClassLoader(urls);
            Class<?> targetClass = loader.loadClass(template.getNameSpace());
            // 如果检测不到fxml文件，那么直接抛出异常
            String relativePath = "jar:file:"+Objects.requireNonNull(targetClass.getResource("/")).getPath()+template.getFileRelativePath()+"!"+template.getFxmlFilePath();
            URL resource = new URL(relativePath);
            InputStream inputStream = resource.openStream();
            return !Objects.isNull(inputStream);
        } catch (URISyntaxException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 启动微应用
     *
     * @param template 微应用模板对象
     */
    @Override
    public void runApplication(FxSmallApplicationTemplate template) {
        try {
            URL url = Objects.requireNonNull(StickPointDevToolsApplication.class.getResource(template.getFileRelativePath())).toURI().toURL();
            URL[] urls = new URL[]{ url };
            URLClassLoader loader = new URLClassLoader(urls);
            Class<?> targetClass = loader.loadClass(template.getNameSpace());
            FxBaseSmallApplication application = (FxBaseSmallApplication) targetClass.getDeclaredConstructor().newInstance();
            Platform.startup(() -> {
                Stage stage = new Stage();
                stage.setTitle(template.getAppName());
                application.show(stage);
            });
        } catch (MalformedURLException | URISyntaxException | ClassNotFoundException | NoSuchMethodException
                | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
