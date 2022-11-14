package com.stickpoint.devtools.service.impl;

import com.stickpoint.devtools.service.IDevAssistantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.StandardCharsets;

/**
 * description: DevAssistantServiceImpl
 *
 * @ClassName : DevAssistantServiceImpl
 * @Date 2022/11/8 12:52
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.service.impl
 */
public class DevAssistantServiceImpl implements IDevAssistantService {

    /**
     * 系统日志
     */
    private static final Logger log = LoggerFactory.getLogger(DevAssistantServiceImpl.class);

    /**
     * 字符串转十六进制数据，结果以字符串形式返回
     *
     * @param stringData 传入一个字符串数据
     * @return 返回一个十六进制数据
     */
    @Override
    public String strDataTransformToHexCode(String stringData) {
        char[] chars = stringData.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        byte[] stringDataBytes = stringData.getBytes();
        int bit;
        for (byte stringDataByte : stringDataBytes) {
            bit = (stringDataByte & 0x0f0) >> 4;
            stringBuilder.append(chars[bit]);
            bit = stringDataByte & 0x0f;
            stringBuilder.append(chars[bit]);
        }
        log.info("执行了字符串转十六进制方法，转换完成~");
        return stringBuilder.toString().trim();
    }

    /**
     * 十六进制转为字符串
     *
     * @param hexData 十六进制数据
     * @return 返回一个字符串数据
     */
    @Override
    public String hexDataTransformToStrData(String hexData) {
        char[] hexDataCharArray = hexData.toCharArray();
        byte[] bytes = new byte[hexData.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = hexData.indexOf(hexDataCharArray[2 * i]) * 16;
            n += hexData.indexOf(hexDataCharArray[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        // 最终将字符串以UTF-8的形式进行编码返回
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * unicode编码数据转为字符串
     *
     * @param unicodeData unicode编码字符串
     * @return 返回一个中文字符串
     */
    @Override
    public String unicodeDataTransformToStrData(String unicodeData) {

        return null;
    }

    /**
     * 字符串中文数据转为unicode编码
     *
     * @param stringData 传入一个中文字符串
     * @return 返回一个unicode编码后的字符串
     */
    @Override
    public String strDataTransformToUnicodeData(String stringData) {
        char[] utfBytes = stringData.toCharArray();
        StringBuilder unicodeBytes = new StringBuilder();
        for (char utfByte : utfBytes) {
            String hexB = Integer.toHexString(utfByte);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes.append("\\u").append(hexB);
        }
        return unicodeBytes.toString();
    }


}
