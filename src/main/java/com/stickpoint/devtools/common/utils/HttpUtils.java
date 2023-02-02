package com.stickpoint.devtools.common.utils;

import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpCall;
import com.ejlchina.okhttps.HttpResult;
import com.ejlchina.okhttps.OkHttps;
import com.stickpoint.devtools.common.enums.AppEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.Objects;


/**
 * @BelongsProject: ddmusic
 * @BelongsPackage: com.stickpoint.ddmusic.common.utils
 * @Author: fntp
 * @CreateTime: 2022-11-02  21:58
 * @Description: TODO
 * @Version: 1.0
 */
public class HttpUtils {
    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * 系统Http请求工具类实例
     */
    private static HttpUtils instance;
    /**
     * 系统Http请求对象
     */
    private static final HTTP HTTP = OkHttps.getHttp();

    /**
     * 私有化构造，启用单例模式
     */
    private HttpUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获得系统HttpUtils对象实例
     * @return 返回一个HttpUtils请求对象
     */
    public static HttpUtils getInstance() {
        if (Objects.isNull(instance)) {
            synchronized (HttpUtils.class) {
                log.info("初始化加载HttpIUtils工具类对象");
                instance = new HttpUtils();
            }
        }
        return instance;
    }

    /**
     * 进行基础的常见的Get请求
     * @param requestUrl 请求地址
     * @param requestParams 请求参数
     * @return 返回一个请求的最终json响应字符串
     */
    public String doNormalGet(String requestUrl, Map<String, ?> requestParams){
        HttpCall httpCall = HTTP.async(requestUrl) .addUrlPara(requestParams).get();
        HttpResult httpResult = httpCall.getResult();
        return getResponse(httpResult);
    }

    /**
     * 封装内部的返回基本响应的方法
     * @param httpResult httpResult 请求结果对象
     * @return 返回一个相应的字符串内容
     */
    private String getResponse(HttpResult httpResult) {
        String responseStr = null;
        if (AppEnums.APP_NETWORK_STATUS_OK.getNumberInfo() == httpResult.getStatus()) {
            responseStr = httpResult.getBody().toString();
            httpResult.getBody().close();
        }
        return responseStr;
    }

    public String doAbsoluteGet(String requestUrl) {
        HttpCall httpCall = HTTP.async(requestUrl).get();
        HttpResult result = httpCall.getResult();
        return getResponse(result);
    }

}