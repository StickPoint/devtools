package com.stickpoint.devtools.service.impl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import com.stickpoint.devtools.common.cache.SysCache;
import com.stickpoint.devtools.common.entity.FireWallInfoEntity;
import com.stickpoint.devtools.common.entity.IpInfoEntity;
import com.stickpoint.devtools.common.entity.JavaAppInfoEntity;
import com.stickpoint.devtools.common.entity.LocalPortInfoEntity;
import com.stickpoint.devtools.common.entity.SystemDetailInfoEntity;
import com.stickpoint.devtools.common.entity.SystemInfoEntity;
import com.stickpoint.devtools.common.entity.VersionEntity;
import com.stickpoint.devtools.common.entity.WeatherInfoEntity;
import com.stickpoint.devtools.common.enums.AppEnums;
import com.stickpoint.devtools.common.enums.DevToolsCodeEnums;
import com.stickpoint.devtools.common.exception.DevToolsException;
import com.stickpoint.devtools.common.utils.HttpUtils;
import com.stickpoint.devtools.common.utils.ThreadUtil;

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
     * @exception ParseException 解析异常
     * @return 返回七日内天气信息
     */
    @Override
    public List<WeatherInfoEntity> getWeatherInfo() throws ParseException {
        // 首先根据传入的字符串查询经纬度信息
        String addressUrl = SysCache.SYS_PROP.get("system.weather.address");
        if (Objects.isNull(addressUrl)) {
            // 如果地址url是空的话 抛出异常
            throw new DevToolsException(DevToolsCodeEnums.ERROR_PROP_URL_ERROR);
        }
        // 拿到请求的json响应
        String responseBody = HttpUtils.getInstance().doMsAddressGet(addressUrl);
        JsonElement respArray = JsonParser.parseString(responseBody);
        JsonObject addressJson = respArray.getAsJsonArray().get(0).getAsJsonObject();
        // 首先根据传入的字符串查询经纬度信息
        String infoUrl = SysCache.SYS_PROP.get("system.weather.info");
        if (Objects.isNull(infoUrl)) {
            // 如果地址url是空的话 抛出异常
            throw new DevToolsException(DevToolsCodeEnums.ERROR_PROP_URL_ERROR);
        }
        String replace = infoUrl.replace("paramLat", addressJson.get("longitude").getAsString());
        String finalUrl =  replace.replace("paramLng",addressJson.get("latitude").getAsString());
        log.info(finalUrl);
        String result = HTTP.doMsWeatherInfoGet(finalUrl);
        JsonElement resultJson = JsonParser.parseString(result);
        if (resultJson.isJsonObject()) {
            JsonObject sourceRoot = resultJson.getAsJsonObject().get("responses")
                    .getAsJsonArray().get(0).getAsJsonObject();
            JsonArray dataList = sourceRoot.getAsJsonObject()
                    .get("weather").getAsJsonArray()
                    .get(0).getAsJsonObject()
                    .get("forecast").getAsJsonObject()
                    .get("days").getAsJsonArray();
            JsonObject current = resultJson.getAsJsonObject().get("responses")
                    .getAsJsonArray().get(0).getAsJsonObject()
                    .get("weather").getAsJsonArray()
                    .get(0).getAsJsonObject().get("current").getAsJsonObject();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            List<WeatherInfoEntity> featureDays = new ArrayList<>();
            WeatherInfoEntity today = gson.fromJson(current, WeatherInfoEntity.class);
            JsonObject locationJson = sourceRoot.get("source").getAsJsonObject().get("location").getAsJsonObject();
            String stateCode = locationJson.get("StateCode").getAsString();
            String name = locationJson.get("Name").getAsString();
            today.setAddress(stateCode+" · "+name);
            today.setDayOfMonth(LocalDateTime.ofInstant(new Date().toInstant(),ZoneId.systemDefault()).getDayOfMonth());
            featureDays.add(today);
            for (int i = 1; i <= 7; i++) {
                WeatherInfoEntity featureDay = new WeatherInfoEntity();
                JsonObject dailyJson = dataList.get(i).getAsJsonObject().get("daily").getAsJsonObject();
                String cap = dailyJson.get("day").getAsJsonObject().get("cap").getAsString();
                featureDay.setCap(cap);
                String validTime = dailyJson.get("valid").getAsString();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                Date date = format.parse(validTime);
                featureDay.setValid(date);
                LocalDateTime todayTime = LocalDateTime.ofInstant(featureDay.getValid().toInstant(), ZoneId.systemDefault());
                featureDay.setDayOfMonth(todayTime.getDayOfMonth());
                featureDays.add(featureDay);
            }
            return featureDays;
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
        SystemInfoEntity systemInfoEntity = new SystemInfoEntity();
        systemInfoEntity.setSystemDetailInfoEntity(getLocalSystemDetailInfo());
        systemInfoEntity.setOtherIpaddressInfo(getLocalOtherIpaddress());
        systemInfoEntity.setGatewayInfo(getGatewayIpaddressInfo());
        systemInfoEntity.setJavaAppInfoEntity(getLocalJavaApplicationsInfo());
        systemInfoEntity.setLocalPortInfoEntity(getSystemPortsInfo(ThreadUtil.getPool()));
        systemInfoEntity.setFireWallInfo(getLocalFirewallInfo());
        return systemInfoEntity;
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
        LocalPortInfoEntity localPortInfoEntity = new LocalPortInfoEntity();
        localPortInfoEntity.setCanBeUsedPortList(canBeUsedPortList);
        localPortInfoEntity.setAlreadyInUsePorts(alreadyInUsedPortList);
        localPortInfoEntity.setCanBeUsedPortListCounts(canBeUsedPortList.size());
        localPortInfoEntity.setAlreadyInUsePortsCounts(alreadyInUsedPortList.size());
        return localPortInfoEntity;
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
        FireWallInfoEntity fireWallInfoEntity = new FireWallInfoEntity();
        Process jps = runtime.exec("netsh advfirewall show allprofiles state");
        if (Objects.nonNull(jps)) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(jps.getInputStream(),StandardCharsets.UTF_8))){
                String lineMes;
                AtomicInteger times = new AtomicInteger(1);
                while ((lineMes = bufferedReader.readLine()) != null) {
                    if (lineMes.contains("状态")||lineMes.contains("status")) {
                        switch (times.getAndAdd(1)) {
                            case 1 -> fireWallInfoEntity.setSiteNetworkStatus(lineMes.substring(lineMes.indexOf("状态")));
                            case 2 -> fireWallInfoEntity.setProfessionalNetworkStatus(lineMes.substring(lineMes.indexOf("状态")));
                            default -> fireWallInfoEntity.setPublicNetworkStatus(lineMes.substring(lineMes.indexOf("状态")));
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
        return fireWallInfoEntity;
    }

    /**
     * 返回一个系统信息对象
     * @return 获取本地系统信息
     */
    @SuppressWarnings("unused")
    private static SystemDetailInfoEntity getLocalSystemDetailInfo(){
        SystemDetailInfoEntity systemDetailInfoEntity = new SystemDetailInfoEntity();
        systemDetailInfoEntity.setRuntimeSystemFramework(System.getProperty("os.arch"));
        systemDetailInfoEntity.setRuntimeSystemOsVersion(System.getProperty("os.name").concat(System.getProperty("os.version")));
        systemDetailInfoEntity.setRuntimeCurrentDir(System.getProperty("user.dir"));
        systemDetailInfoEntity.setRuntimeJavaVersion(System.getProperty("java.version"));
        systemDetailInfoEntity.setRuntimeJavaServerProvider(System.getProperty("java.vendor"));
        systemDetailInfoEntity.setRuntimeJavaHomePath(System.getProperty("java.home"));
        return systemDetailInfoEntity;
    }

}
