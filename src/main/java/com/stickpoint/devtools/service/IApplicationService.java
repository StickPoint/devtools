package com.stickpoint.devtools.service;

import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.common.entity.SystemInfoEntity;
import com.stickpoint.devtools.common.entity.VersionEntity;
import com.stickpoint.devtools.common.entity.WeatherInfoEntity;

import java.io.IOException;
import java.text.ParseException;
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
     * @return 返回七日内天气信息
     * @exception ParseException 解析异常
     */
    List<WeatherInfoEntity> getWeatherInfo() throws ParseException;

    /**
     * 检查软件版本更新
     * @return 返回一个版本信息
     */
    VersionEntity checkVersionForUpdate();

    /**
     * 检查系统状态
     * @return 返回一个系统面板信息
     * @throws IOException 抛出IO异常
     */
    SystemInfoEntity checkSystemInfo() throws IOException;

}
