package com.stickpoint.devtools.service.impl;
import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.service.IApplicationService;
import java.net.InetAddress;

/**
 * description: ApplicationServiceImpl
 *
 * @ClassName : ApplicationServiceImpl
 * @Date 2022/10/30 19:48
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.service.impl
 */
public class ApplicationServiceImpl implements IApplicationService {

    /**
     * 获得本地网卡IP信息
     *
     * @return 返回一个IP信息实体
     */
    @Override
    public IpInfoEntity getLocalIpInfo() {
        String[] ret = null;
        String hostName = null;
        String ipAddress6 = null;
        String ipAddress4 = null;
        try {
            InetAddress address = InetAddress.getLocalHost();
            hostName = address.getHostName();
            ipAddress6 = address.getHostAddress();
            ipAddress4 = address.getHostAddress();
            if (hostName.length() > 0) {
                InetAddress[] addresses = InetAddress.getAllByName(hostName);
                if (addresses.length > 0) {
                    ret = new String[addresses.length];
                    for (int i = 0; i < addresses.length; i++) {
                        ret[i] = addresses[i].getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
            ret = null;
        }
        return IpInfoEntity.builder()
                .setIpv6Address(ipAddress6).setIpArray(ret)
                .setHostName(hostName)
                .setIpv4Address(ipAddress4).build();
    }

}
