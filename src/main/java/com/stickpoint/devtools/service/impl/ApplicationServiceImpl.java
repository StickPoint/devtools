package com.stickpoint.devtools.service.impl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.common.entity.WeatherInfoEntity;
import com.stickpoint.devtools.common.utils.HttpUtils;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
     * Http请求对象
     */
    private static final HttpUtils HTTP = HttpUtils.getInstance();

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
        list.forEach(item-> log.info("{}---{}---{}",item.getHostName(),item.getHostAddress(), Arrays.toString(item.getAddress())));
        return IpInfoEntity.builder()
                .setIpv6Address(ipAddress6).setIpArray(ret)
                .setHostName(hostName)
                .setIpv4Address(ipAddress4).build();
    }

    /**
     * 根据地域位置信息，获得当地天气
     *
     * @param addressStr 传入一个地域信息，比如：中大银座
     * @return 返回七日内天气信息
     */
    @Override
    public List<WeatherInfoEntity> getWeatherInfo(String addressStr) {
        // 首先根据传入的字符串查询经纬度信息
        Map<String,String> requestParamMap = new LinkedHashMap<>();
        requestParamMap.put("output","json");
        requestParamMap.put("ak","ItmA0TLauLZVEUnNQEqknQAHLPhIYYtl");
        requestParamMap.put("address",addressStr);
        String responseJson = HTTP.doNormalGet("https://api.map.baidu.com/geocoder/v2/", requestParamMap);
        log.info(responseJson);
        JsonElement jsonElement = JsonParser.parseString(responseJson);
        JsonElement location = jsonElement.getAsJsonObject().get("result").getAsJsonObject().get("location");
        long lng = 0;
        long lat = 0;
        if (location.isJsonObject()) {
            lng = location.getAsJsonObject().get("lng").getAsLong();
            lat = location.getAsJsonObject().get("lat").getAsLong();
        }
        Map<String,Object> weatherParamMap = new LinkedHashMap<>();
        weatherParamMap.put("target","http%3A//autodev.openspeech.cn/csp/api/v2.1/weather%3FopenId%3Daiuicus%26clientType%3Dandroid%26sign%3Dandroid%26city%3D%25E4%25B8%258A%25E6%25B5%25B7%26latitude%3D39.902895%26longitude%3D116.427915%26needMoreData%3Dtrue%26pageNo%3D1%26pageSize%3D7");
        weatherParamMap.put("openId","aiuicus");
        weatherParamMap.put("clientType","android");
        weatherParamMap.put("sign","android");
        weatherParamMap.put("latitude",lat);
        weatherParamMap.put("longitude",lng);
        weatherParamMap.put("needMoreData",true);
        weatherParamMap.put("pageNo",1);
        weatherParamMap.put("pageSize",7);
        String result = HTTP.doNormalGet("https://autodev.openspeech.cn/csp/api/v2.1/weather", weatherParamMap);
        log.info(result);
        JsonElement resultJson = JsonParser.parseString(result);
        if (resultJson.isJsonObject()) {
            JsonArray dataList = resultJson.getAsJsonObject().get("data").getAsJsonObject().get("list").getAsJsonArray();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            List<WeatherInfoEntity> infoList = gson.fromJson(dataList, new TypeToken<List<WeatherInfoEntity>>(){}.getType());
            if (Objects.nonNull(infoList)) {
                return infoList;
            }
        }
        return null;
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
