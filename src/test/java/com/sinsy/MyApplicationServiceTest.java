package com.sinsy;

import com.stickpoint.devtools.common.entity.WeatherInfoEntity;
import com.stickpoint.devtools.service.IApplicationService;
import com.stickpoint.devtools.service.impl.ApplicationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * @author puye(0303)
 * @since 2023/4/11
 */
@Slf4j
public class MyApplicationServiceTest {

    @Test
    public void testWeather(){
        IApplicationService applicationService = new ApplicationServiceImpl();
        List<WeatherInfoEntity> weatherInfo = applicationService.getWeatherInfo("杭州市西湖区");
        weatherInfo.forEach(item->{
            log.info(String.valueOf(item));
        });
    }

    @Test
    public void testGetIpaddress(){
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = networkInterfaces.nextElement();
                if (!netInterface.isUp() || netInterface.isLoopback())
                    continue;
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    log.info(address.getHostAddress());
                    // 判断是否为本地或回路地址
                    if (address.isSiteLocalAddress() || address.isLoopbackAddress())
                        continue;
                    // 判断是否为 IPv6 地址，如果是则跳过
                    if (address.getHostAddress().indexOf(":") != -1)
                        continue;
                    // 找到公网 IP 地址
                    System.out.println("Public IP found: " + address.getHostAddress());
                    return;
                }
            }
            System.out.println("Public IP not found!");
        } catch (SocketException e) {
            System.out.println("Failed to get network interfaces: " + e.getMessage());
        }
    }


}
