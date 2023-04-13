package com.stickpoint.devtools.common.entity;



import java.io.Serial;
import java.io.Serializable;

/**
 * @author puye(0303)
 * @since 2023/2/2
 */


public class VersionEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3226821710919224918L;

    /**
     * 版本id
     * 版本id 组成为 hash{文件个数} + 版本发布日期时间戳-13位 + {八位字母+数字组合随机}
     */
    private String versionId;
    /**
     * 版本号码 v0.0.1.20230203.a
     */
    private String version;
    /**
     * 版本法布时间
     */
    private String publishTime;
    /**
     * 新版本软件大小 单位 MB
     */
    private String softwareSize;
    /**
     * 版本描述
     */
    private String desc;
    /**
     * 新版本下载地址
     */
    private String downloadUrl;
    /**
     * 备用下载地址
     */
    private String downloadUrlBak;

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getSoftwareSize() {
        return softwareSize;
    }

    public void setSoftwareSize(String softwareSize) {
        this.softwareSize = softwareSize;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrlBak() {
        return downloadUrlBak;
    }

    public void setDownloadUrlBak(String downloadUrlBak) {
        this.downloadUrlBak = downloadUrlBak;
    }
}
