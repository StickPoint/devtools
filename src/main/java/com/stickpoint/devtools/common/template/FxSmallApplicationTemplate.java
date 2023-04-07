package com.stickpoint.devtools.common.template;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * description: SmallApplicationTemplate
 *
 * @ClassName : SmallApplicationTemplate
 * @Date 2023/1/4 15:41
 * @Author puye(0303)
 * @PackageName smallApplication.saPane
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FxSmallApplicationTemplate implements Serializable {

    @Serial
    private static final long serialVersionUID = -5652298938352540167L;
    /**
     * 数据id
     */
    private long id;
    /**
     * 微应用文件名称，这个属性需要在文件通过后台上传之后，自动获取到然后将hash文件名称作为该文件名
     * Md5-hash计算方式： hashCode(显示名称 + 上传时间 + 作者)
     */
    private String fileName;
    /**
     * 微应用相对路径 相对于主体程序的路径
     */
    private String fileRelativePath;
    /**
     * 微应用显示的名称
     */
    private String appName;
    /**
     * 微应用的命名空间，可不填
     */
    private String nameSpace;
    /**
     * 微应用元数据
     */
    private String metaData;
    /**
     * 微应用创建时间
     */
    private String createTime;
    /**
     * 微应用更新时间
     */
    private String updateTime;
    /**
     * 微应用版本 版本号需要严格按照以下格式
     * eg： v{年份}-{月份}-{日}-{版本标识符号}
     * 关于版本标识符：β α 或者是单字母，单数字等，禁止使用其他标点符号，特殊符号仅支持 α 与 β
     */
    private String version;
    /**
     * 微应用创建者的id 后台用户id
     */
    private long creatorId;
    /**
     * 微应用创建者昵称
     */
    private String creatorNickName;
    /**
     * 微应用内网下载地址：
     * 内网部署需要填写内网地址，
     */
    private String smallApplicationUrl;
    /**
     * 微应用简介,200字以内
     */
    private String briefDescription;
    /**
     * 微应用 支持markdown文本
     */
    private String detailedDescription;
    /**
     * 软件hashCode-Md5校验码
     * 格式：hashCode(作者+微应用的显示名称)
     */
    private String shaOrHashCode;
    /**
     * appKey 微应用 的key
     */
    private String appKey;
    /**
     * 微应用 秘钥 更新软件的时候需要，秘钥在软件上传并且通过审核之后会自动返回给上传者
     */
    private String appSecret;
    /**
     * 微应用的公网下载地址
     */
    private String downloadUrl;
    /**
     * 微应用更新地址
     */
    private String updateUrl;
    /**
     * 微应用软件大小，计算的是长度 fileLength
     */
    private Integer fileSize;
    /**
     * 微应用的软件图标地址，一般写公网地址
     */
    private String iconUrl;
    /**
     * fxml文件的相对路径位置
     * 这个字段是扩展字段，一般不使用
     */
    private String fxmlFilePath;
    /**
     * 微应用标签集合
     */
    private String[] mark;
    /**
     * 状态
     * 0--正常上架中
     * 1--上传完毕，审核中
     * 2--上传失败
     * 3--审核未通过
     * 4--重新审核
     * 5--审核通过
     * 6--已下架
     */
    private int status;
}
