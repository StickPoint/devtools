package com.stickpoint.devtools.service;

/**
 * description: IDevAssistantService
 *
 * @ClassName : IDevAssistantService
 * @Date 2022/11/8 12:50
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.service
 */
public interface IDevAssistantService {

    /**
     * 字符串转十六进制数据，结果以字符串形式返回
     * @param stringData 传入一个字符串数据
     * @return 返回一个十六进制数据
     */
    String strDataTransformToHexCode(String stringData);

    /**
     * 十六进制转为字符串
     * @param hexData 十六进制数据
     * @return 返回一个字符串数据
     */
    String hexDataTransformToStrData(String hexData);

    /**
     * unicode编码数据转为字符串
     * @param unicodeData unicode编码字符串
     * @return 返回一个中文字符串
     */
    String unicodeDataTransformToStrData(String unicodeData);

    /**
     * 字符串中文数据转为unicode编码
     * @param stringData 传入一个中文字符串
     * @return 返回一个unicode编码后的字符串
     */
    String strDataTransformToUnicodeData(String stringData);
}
