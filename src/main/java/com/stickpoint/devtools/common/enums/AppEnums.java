package com.stickpoint.devtools.common.enums;

/**
 * @Author SunChengXin_0303
 * @ClassName AppEnums.Class
 * @PackageName com.stickpoint.devtools.common.enums
 * @jdk_version 17
 * @Date 2022年10月28日 13:13
 * @since 1.5
 */
public enum AppEnums {

    FUNCTION_CENTER_MENU_PANE_FX_ID_INFO("菜单面板的menuTree的fx-id","menuTree");

    /**
     * fx应用内部infoName-key
     */
    private final String infoName;
    /**
     * fx应用内部infoValue-值
     */
    private final String infoValue;

    AppEnums(String infoName,String infoValue){
        this.infoName = infoName;
        this.infoValue=infoValue;
    }

    /**
     *
     * @title: getRouterId 方法名：getRouterId
     * @description: 此方法就是为了返回路由Id，页面的路由中，Id是唯一的。
     * @return String 返回的是一个字符串，也就是枚举对象的路由Id
     */
    public String getInfoName() {
        return infoName;
    }

    /**
     *
     * @title: getRouterId 方法名：getRouterId
     * @description: 此方法就是为了返回路由Id，页面的路由中，Id是唯一的。
     * @return String 返回的是一个字符串，也就是枚举对象的路由Id
     */
    public String getInfoValue() {
        return infoValue;
    }
}
