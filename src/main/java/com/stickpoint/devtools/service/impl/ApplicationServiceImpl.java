package com.stickpoint.devtools.service.impl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.stickpoint.devtools.common.entity.FireWallInfoEntity;
import com.stickpoint.devtools.common.entity.FireWallInfoEntity.FireWallInfoEntityBuilder;
import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.common.entity.JavaAppInfoEntity;
import com.stickpoint.devtools.common.entity.LocalPortInfoEntity;
import com.stickpoint.devtools.common.entity.SystemDetailInfoEntity;
import com.stickpoint.devtools.common.entity.SystemInfoEntity;
import com.stickpoint.devtools.common.entity.VersionEntity;
import com.stickpoint.devtools.common.entity.WeatherInfoEntity;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.common.utils.HttpUtils;
import com.stickpoint.devtools.common.utils.ThreadUtil;
import com.stickpoint.devtools.service.IApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

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
                infoList.forEach(item->{
                    LocalDateTime todayTime = LocalDateTime.ofInstant(item.getDate().toInstant(), ZoneId.systemDefault());
                    item.setDayOfMonth(todayTime.getDayOfMonth());
                });
                return infoList;
            }
        }
        return Collections.emptyList();
    }

    /**
     * 检查软件版本更新
     *
     * @return 返回一个版本信息
     */
    @Override
    public VersionEntity checkVersionForUpdate() {
        // 第一步 获得远程配置信息

        // 第二步 请求获得版本信息

        // 第三步 比较版本差异，决定是否进入版本更新

        // 第四步 版本更新：1、差异增量更新；2、覆盖全量更新；

        // 第四步 启动提示UI 引导用户进入软件更新程序

        return null;
    }

    /**
     * 检查系统状态
     *
     * @return 返回系统状态信息
     */
    @Override
    public SystemInfoEntity checkSystemInfo() throws IOException {
        return SystemInfoEntity.builder()
                .systemDetailInfoEntity(getLocalSystemDetailInfo())
                .otherIpaddressInfo(getLocalOtherIpaddress())
                .gatewayInfo(getGatewayIpaddressInfo())
                .javaAppInfoEntity(getLocalJavaApplicationsInfo())
                .localPortInfoEntity(getSystemPortsInfo(ThreadUtil.getPool()))
                .fireWallInfo(getLocalFirewallInfo())
                .build();
    }

    /**
     * 获取本机所有网卡信息   得到所有IPv4信息
     * @return 返回一个网卡信息
     */
    private static List<Inet4Address> getLocalIp4AddressFromNetworkInterface()  {
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

    /**
     * 获得DHCP服务器（网关地址）地址
     * 目前只实现了Windows平台的功能
     * TODO 后续考虑增加Linux平台与Mac平台的命令
     * @return 返回一个网关地址
     */
    @SuppressWarnings("unused")
    private static String getGatewayIpaddressInfo() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process jps = runtime.exec("systeminfo");
        if (Objects.nonNull(jps)) {
            List<String> systemApplications = new ArrayList<>();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(jps.getInputStream(), Charset.forName("GBK")))){
                String lineMes;
                while ((lineMes = bufferedReader.readLine()) != null) {
                    systemApplications.add(lineMes);
                }
                if (jps.waitFor() != 0 && jps.exitValue() == 1) {
                        log.error("命令执行失败!");
                }
            }catch (InterruptedException e){
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
            String dhcpInfo = null;
            if (!systemApplications.isEmpty()) {
                dhcpInfo = systemApplications.stream().filter(s1 -> s1.contains("DHCP 服务器") || s1.contains("DHCP Server")).findFirst().orElse(null);
            }
            if (Objects.nonNull(dhcpInfo)) {
                return dhcpInfo.substring(dhcpInfo.indexOf(":"));
            }
            return null;
        }
        return null;
    }

    /**
     * java获取本机端口详情
     * @param pool 线程池
     * @return 返回一个开放端口与使用端口列表Map
     */
    @SuppressWarnings("unused")
    private static LocalPortInfoEntity getSystemPortsInfo(ExecutorService pool) {
        List<Integer> alreadyInUsedPortList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(65535);
        List<Integer> canBeUsedPortList = new ArrayList<>();
        for (int port = 1; port <= AppEnums.APP_NETWORK_POSTS_NUMBER.getNumberInfo(); port++) {
            int finalPort = port;
            pool.execute(() -> {
                try(Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress("localhost", finalPort));
                    alreadyInUsedPortList.add(finalPort);
                } catch (Exception ignored) {
                    canBeUsedPortList.add(finalPort);
                }
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        alreadyInUsedPortList.sort(Integer::compareTo);
        canBeUsedPortList.sort(Integer::compareTo);
        return LocalPortInfoEntity.builder()
                .canBeUsedPortList(canBeUsedPortList)
                .alreadyInUsePorts(alreadyInUsedPortList)
                .alreadyInUsePortsCounts(alreadyInUsedPortList.size())
                .canBeUsedPortListCounts(canBeUsedPortList.size())
                .build();
    }

    /**
     * 获得本机局域网内其他IP地址
     * @return 返回同一网段内的其他IP地址集合
     */
    @SuppressWarnings("unused")
    private static List<String> getLocalOtherIpaddress(){
        List<String> finalIpaddressList = new ArrayList<>(Collections.emptyList());
        try {
            String localIp=InetAddress.getLocalHost().getHostAddress();
            String[] net=localIp.split("\\.");
            int j=Integer.parseInt(net[2]);
            for(int i = 1; i< AppEnums.COMMON_NUMBER_256.getNumberInfo(); i++){
                Runtime.getRuntime().exec("ping -w 2 -n 1 192.168."+j+"."+i).destroy();
            }
            Process p=Runtime.getRuntime().exec("arp -a");
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            log.info("Log this for sysInfo:{}",br.readLine());
            log.info("Log this for sysInfo:{}",br.readLine());
            log.info("Log this for sysInfo:{}",br.readLine());
            String temp;
            while(((temp=br.readLine())!=null&&!temp.isEmpty())){
                String ipInfo = temp.trim().split(" ")[0];
                if (ipInfo.startsWith("1")) {
                    finalIpaddressList.add(ipInfo);
                }
            }
            p.destroy();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalIpaddressList;
    }

    /**
     * 获得本机java应用集合
     * @return 返回一个本机java应用详情集合
     */
    @SuppressWarnings("unused")
    public static List<JavaAppInfoEntity> getLocalJavaApplicationsInfo() throws IOException {
        List<JavaAppInfoEntity> appList = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        Process jps = runtime.exec("jps");
        if (Objects.nonNull(jps)) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(jps.getInputStream(), StandardCharsets.UTF_8))){
                String lineMes;
                while ((lineMes = bufferedReader.readLine()) != null) {
                    JavaAppInfoEntity entity = new JavaAppInfoEntity();
                    String[] results = lineMes.split(" ");
                    if (results.length > 1) {
                        entity.setPid(Integer.parseInt(results[0]));
                        entity.setName(results[1]);
                    } else {
                        entity.setPid(Integer.parseInt(results[0]));
                    }
                    entity.setRuntimeInfo(results);
                    appList.add(entity);
                }
                if (jps.waitFor() != 0) {
                    if (jps.exitValue() == 1) {
                        log.info("JPS命令执行失败!");
                    }
                } else {
                    log.info("JSP正常结束");
                }
            }catch (InterruptedException e){
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
            return appList;
        }
        return Collections.emptyList();
    }

    /**
     * 本地加载防火墙策略
     * @return 返回一个防火墙信息
     * @throws IOException 抛出io异常
     */
    @SuppressWarnings("unused")
    public static FireWallInfoEntity getLocalFirewallInfo() throws IOException{
        Runtime runtime = Runtime.getRuntime();
        FireWallInfoEntityBuilder builder = FireWallInfoEntity.builder();
        Process jps = runtime.exec("netsh advfirewall show allprofiles state");
        if (Objects.nonNull(jps)) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(jps.getInputStream(),StandardCharsets.UTF_8))){
                String lineMes;
                AtomicInteger times = new AtomicInteger(1);
                while ((lineMes = bufferedReader.readLine()) != null) {
                    if (lineMes.contains("状态")||lineMes.contains("status")) {
                        switch (times.getAndAdd(1)) {
                            case 1 -> builder.siteNetworkStatus(lineMes.substring(lineMes.indexOf("状态")));
                            case 2 -> builder.professionalNetworkStatus(lineMes.substring(lineMes.indexOf("状态")));
                            default -> builder.publicNetworkStatus(lineMes.substring(lineMes.indexOf("状态")));
                        }
                    }
                }
                if (jps.waitFor() != 0 || jps.exitValue() == 1) {
                    log.error("命令执行失败!");
                }
            } catch ( InterruptedException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        return builder.build();
    }

    /**
     * 返回一个系统信息对象
     * @return 获取本地系统信息
     */
    @SuppressWarnings("unused")
    private static SystemDetailInfoEntity getLocalSystemDetailInfo(){
       return SystemDetailInfoEntity.builder()
               .runtimeSystemFramework(System.getProperty("os.arch"))
               .runtimeSystemOsVersion(System.getProperty("os.name").concat(System.getProperty("os.version")))
               .runtimeCurrentDir(System.getProperty("user.dir"))
               .runtimeJavaVersion(System.getProperty("java.version"))
               .runtimeJavaServerProvider(System.getProperty("java.vendor"))
               .runtimeJavaHomePath(System.getProperty("java.home"))
               .build();
    }

}
