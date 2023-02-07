package com.stickpoint.devtools.common.template;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

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
    private String creatorId;
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

    @SuppressWarnings("unused")
    public String getFileName() {
        return fileName;
    }

    @SuppressWarnings("unused")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @SuppressWarnings("unused")
    public String getFileRelativePath() {
        return fileRelativePath;
    }

    @SuppressWarnings("unused")
    public void setFileRelativePath(String fileRelativePath) {
        this.fileRelativePath = fileRelativePath;
    }

    @SuppressWarnings("unused")
    public String getNameSpace() {
        return nameSpace;
    }

    @SuppressWarnings("unused")
    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    @SuppressWarnings("unused")
    public String getMetaData() {
        return metaData;
    }

    @SuppressWarnings("unused")
    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    @SuppressWarnings("unused")
    public String getCreateTime() {
        return createTime;
    }

    @SuppressWarnings("unused")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @SuppressWarnings("unused")
    public String getUpdateTime() {
        return updateTime;
    }

    @SuppressWarnings("unused")
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @SuppressWarnings("unused")
    public String getVersion() {
        return version;
    }

    @SuppressWarnings("unused")
    public void setVersion(String version) {
        this.version = version;
    }

    @SuppressWarnings("unused")
    public String getCreatorId() {
        return creatorId;
    }

    @SuppressWarnings("unused")
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @SuppressWarnings("unused")
    public String getCreatorNickName() {
        return creatorNickName;
    }

    @SuppressWarnings("unused")
    public void setCreatorNickName(String creatorNickName) {
        this.creatorNickName = creatorNickName;
    }

    @SuppressWarnings("unused")
    public String getSmallApplicationUrl() {
        return smallApplicationUrl;
    }

    @SuppressWarnings("unused")
    public void setSmallApplicationUrl(String smallApplicationUrl) {
        this.smallApplicationUrl = smallApplicationUrl;
    }

    @SuppressWarnings("unused")
    public String getBriefDescription() {
        return briefDescription;
    }

    @SuppressWarnings("unused")
    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    @SuppressWarnings("unused")
    public String getDetailedDescription() {
        return detailedDescription;
    }

    @SuppressWarnings("unused")
    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    @SuppressWarnings("unused")
    public String getShaOrHashCode() {
        return shaOrHashCode;
    }

    @SuppressWarnings("unused")
    public void setShaOrHashCode(String shaOrHashCode) {
        this.shaOrHashCode = shaOrHashCode;
    }

    @SuppressWarnings("unused")
    public String getAppKey() {
        return appKey;
    }

    @SuppressWarnings("unused")
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @SuppressWarnings("unused")
    public String getAppSecret() {
        return appSecret;
    }

    @SuppressWarnings("unused")
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @SuppressWarnings("unused")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    @SuppressWarnings("unused")
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @SuppressWarnings("unused")
    public String getUpdateUrl() {
        return updateUrl;
    }

    @SuppressWarnings("unused")
    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    @SuppressWarnings("unused")
    public int getFileSize() {
        return fileSize;
    }

    @SuppressWarnings("unused")
    public Integer fileSizeProperty() {
        return fileSize;
    }

    @SuppressWarnings("unused")
    public String getIconUrl() {
        return iconUrl;
    }

    @SuppressWarnings("unused")
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @SuppressWarnings("unused")
    public String getFxmlFilePath() {
        return fxmlFilePath;
    }

    @SuppressWarnings("unused")
    public void setFxmlFilePath(String fxmlFilePath) {
        this.fxmlFilePath = fxmlFilePath;
    }

    @SuppressWarnings("unused")
    public String[] getMark() {
        return mark;
    }

    @SuppressWarnings("unused")
    public void setMark(String[] mark) {
        this.mark = mark;
    }

    @SuppressWarnings("unused")
    public String getAppName() {
        return appName;
    }

    @SuppressWarnings("unused")
    public void setAppName(String appName) {
        this.appName = appName;
    }

    @SuppressWarnings("unused")
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "FxSmallApplicationTemplate{" +
                "fileName='" + fileName + '\'' +
                ", fileRelativePath='" + fileRelativePath + '\'' +
                ", appName='" + appName + '\'' +
                ", nameSpace='" + nameSpace + '\'' +
                ", metaData='" + metaData + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", version='" + version + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", creatorNickName='" + creatorNickName + '\'' +
                ", smallApplicationUrl='" + smallApplicationUrl + '\'' +
                ", briefDescription='" + briefDescription + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", shaOrHashCode='" + shaOrHashCode + '\'' +
                ", appKey='" + appKey + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", updateUrl='" + updateUrl + '\'' +
                ", fileSize=" + fileSize +
                ", iconUrl='" + iconUrl + '\'' +
                ", fxmlFilePath='" + fxmlFilePath + '\'' +
                ", mark='" + Arrays.toString(mark) + '\'' +
                '}';
    }
}
