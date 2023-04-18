package com.stickpoint.devtools.common.template;


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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileRelativePath() {
        return fileRelativePath;
    }

    public void setFileRelativePath(String fileRelativePath) {
        this.fileRelativePath = fileRelativePath;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorNickName() {
        return creatorNickName;
    }

    public void setCreatorNickName(String creatorNickName) {
        this.creatorNickName = creatorNickName;
    }

    public String getSmallApplicationUrl() {
        return smallApplicationUrl;
    }

    public void setSmallApplicationUrl(String smallApplicationUrl) {
        this.smallApplicationUrl = smallApplicationUrl;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getShaOrHashCode() {
        return shaOrHashCode;
    }

    public void setShaOrHashCode(String shaOrHashCode) {
        this.shaOrHashCode = shaOrHashCode;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getFxmlFilePath() {
        return fxmlFilePath;
    }

    public void setFxmlFilePath(String fxmlFilePath) {
        this.fxmlFilePath = fxmlFilePath;
    }

    public String[] getMark() {
        return mark;
    }

    public void setMark(String[] mark) {
        this.mark = mark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
