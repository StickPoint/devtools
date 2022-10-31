package com.stickpoint.devtools.service;

import com.stickpoint.devtools.common.entity.IpInfoEntity;

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

}
