package com.stickpoint.devtools.view.component;
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

    private String fileName;

    private String fileRelativePath;

    private String appName;

    private String nameSpace;

    private String metaData;

    private String createTime;

    private String updateTime;

    private String version;

    private String creatorId;

    private String creatorNickName;

    private String smallApplicationUrl;

    private String briefDescription;

    private String detailedDescription;

    private String shaOrHashCode;

    private String appKey;

    private String appSecret;

    private String downloadUrl;

    private String updateUrl;

    private Integer fileSize;

    private String iconUrl;

    private String fxmlFilePath;

    private String mark;

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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
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

    public int getFileSize() {
        return fileSize;
    }

    public Integer fileSizeProperty() {
        return fileSize;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
}
