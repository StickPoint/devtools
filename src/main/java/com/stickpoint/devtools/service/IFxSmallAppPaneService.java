package com.stickpoint.devtools.service;

import com.stickpoint.devtools.common.exception.SmallApplicationException;
import com.stickpoint.devtools.common.template.FxSmallApplicationTemplate;

/**
 * description: SmallAppPane
 *
 * @ClassName : SmallAppPane
 * @Date 2023/1/4 15:23
 * @Author puye(0303)
 * @PackageName smallApplication.saPane
 */
public interface IFxSmallAppPaneService {

   /**
    * jar文件加载预检
    * @param template fx微应用模板文件
    * @return 返回一个预检结果 true：预检正常；false：预检失败
    * @throws SmallApplicationException 抛出一个微应用异常
    */
   @SuppressWarnings("unused")
   Boolean checkApplication(FxSmallApplicationTemplate template) throws SmallApplicationException;

   /**
    * 启动微应用
    * @param template 微应用模板对象
    */
   @SuppressWarnings("unused")
   void runApplication(FxSmallApplicationTemplate template);

}
