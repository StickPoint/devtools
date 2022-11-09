package com.stickpoint.devtools.service.impl;
import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.service.IApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

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
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

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
        List<Inet4Address> list = getLocalIp4AddressFromNetworkInterface();
        list.forEach(item->{
            log.info(item.getHostName()+"--"+item.getHostAddress()+"---"+ Arrays.toString(item.getAddress()));
        });
        return IpInfoEntity.builder()
                .setIpv6Address(ipAddress6).setIpArray(ret)
                .setHostName(hostName)
                .setIpv4Address(ipAddress4).build();
    }

    /**
     * 获取本机所有网卡信息   得到所有IPv4信息
     * @return 返回一个网卡信息
     */
    public static List<Inet4Address> getLocalIp4AddressFromNetworkInterface()  {
        List<Inet4Address> addresses = new ArrayList<>(10);
        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(networkInterfaces)) {
            return Collections.emptyList();
        }
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            log.info("网卡接口名称：{}",networkInterface.getName());
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (inetAddress instanceof Inet4Address address) {
                    addresses.add(address);
                }
            }
        }
        return addresses;
    }

}
