package com.stickpoint.devtools.service;

import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.common.entity.SystemInfoEntity;
import com.stickpoint.devtools.common.entity.VersionEntity;
import com.stickpoint.devtools.common.entity.WeatherInfoEntity;

import java.util.List;

/**
 * description: ApplicationService
 *
 * @ClassName : ApplicationService
 * @Date 2022/10/30 19:34
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.service
 */
public interface IApplicationService {

    /**
     * 获得本地网卡IP信息
     * @return 返回一个IP信息实体
     */
    IpInfoEntity getLocalIpInfo();

    /**
     * 根据地域位置信息，获得当地天气
     * @param addressStr 传入一个地域信息，比如：中大银座
     * @return 返回七日内天气信息
     */
    List<WeatherInfoEntity> getWeatherInfo(String addressStr);

    /**
     * 检查软件版本更新
     * @return 返回一个版本信息
     */
    VersionEntity checkVersionForUpdate();

    /**
     * 检查系统状态
     * @return
     */
    SystemInfoEntity checkSystemInfo();

}
