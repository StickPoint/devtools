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
import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.ParseException;
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
    public void testWeather() throws IOException, ParseException {
        log.info("开始装载本地环境变量~");
        Properties properties = new Properties();
        properties.load(StickPointDevToolsApplication.class.getClassLoader().getResourceAsStream("local.properties"));
        properties.forEach((key,value)-> System.setProperty((String) key,(String) value));
        SysCache.SYS_PROP.putAll(new YamlUtil(System.getProperty(AppEnums.APP_REMOTE_YAML_PATH_KEY.getInfoValue())));
        IApplicationService applicationService = new ApplicationServiceImpl();
        List<WeatherInfoEntity> weatherInfo = applicationService.getWeatherInfo();
        weatherInfo.forEach(item-> log.info(String.valueOf(item)));
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

    @Test
    public void testWeatherInfo(){
        String requestUrl = "https://api.msn.cn/weatherfalcon/weather/overview?apikey=j5i4gDqHL6nGYwx5wi5kRhXjtf2c5qgFX9fzfk0TOo&activityId=B4437AEA-75EC-4737-B74F-C5A7D7302A48&ocid=msftweather&cm=zh-cn&user=m-17520DA586CB6D4239CA1F8582CB6C5A&units=C&appId=9e21380c-ff19-4c78-b4ea-19558e93a5d3&wrapodata=false&includemapsmetadata=true&nowcastingv2=true&usemscloudcover=true&cuthour=true&getCmaAlert=true&regioncategories=alert,content&feature=lifeday&includenowcasting=true&nowcastingapi=2&lifeDays=2&lifeModes=50&distanceinkm=0&regionDataCount=20&orderby=distance&days=10&pageOcid=prime-weather::weathertoday-peregrine&source=weather_csr&region=cn&market=zh-cn&locale=zh-cn&lat=30.15913963317871&lon=120.07327270507812";
        String resp = HttpUtils.getInstance().doMsWeatherInfoGet(requestUrl);
        UniversalDetector detector = new UniversalDetector(null);
        detector.handleData(resp.getBytes(),0,resp.getBytes().length);
        detector.dataEnd();
        String encode = detector.getDetectedCharset();
        log.info(encode);
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
