package com.sinsy;

import com.stickpoint.devtools.StickPointDevToolsApplication;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.entity.WeatherInfoEntity;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.common.utils.HttpUtils;
import com.stickpoint.devtools.common.utils.YamlUtil;
import com.stickpoint.devtools.service.IApplicationService;
import com.stickpoint.devtools.service.impl.ApplicationServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 * @author puye(0303)
 * @since 2023/4/11
 */
public class MyApplicationServiceTest {

    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(MyApplicationServiceTest.class);

    @Test
    public void testWeather() throws IOException {
        log.info("开始装载本地环境变量~");
        Properties properties = new Properties();
        properties.load(StickPointDevToolsApplication.class.getClassLoader().getResourceAsStream("local.properties"));
        properties.forEach((key,value)-> System.setProperty((String) key,(String) value));
        SysCache.SYS_PROP.putAll(new YamlUtil(System.getProperty(AppEnums.APP_REMOTE_YAML_PATH_KEY.getInfoValue())));
        IApplicationService applicationService = new ApplicationServiceImpl();
        List<WeatherInfoEntity> weatherInfo = applicationService.getWeatherInfo();
        weatherInfo.forEach(item->{
            log.info(String.valueOf(item));
        });
    }

    @Test
    public void testHttp() throws IOException {
        log.info("开始装载本地环境变量~");
        Properties properties = new Properties();
        properties.load(StickPointDevToolsApplication.class.getClassLoader().getResourceAsStream("local.properties"));
        properties.forEach((key,value)-> System.setProperty((String) key,(String) value));
        SysCache.SYS_PROP.putAll(new YamlUtil(System.getProperty(AppEnums.APP_REMOTE_YAML_PATH_KEY.getInfoValue())));
        String addressUrl = SysCache.SYS_PROP.get("system.weather.address");
        String addressInfo = HttpUtils.getInstance().doMsAddressGet(addressUrl);
        log.info(addressInfo);
    }

    /**
     * 测试获取iP地址
     */
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
                    if (address.getHostAddress().contains(":"))
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
