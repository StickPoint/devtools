package com.stickpoint.devtools.common.entity;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author puye(0303)
 * @since 2023/2/16
 */

public class FireWallInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -7888365924003210076L;
    /**
     * 局域网络状态
     * 局域网内网配置文件状态
     */
    private String siteNetworkStatus;
    /**
     * 专用配置文件状态
     * 专有网络状态
     */
    private String professionalNetworkStatus;
    /**
     * 公用配置文件
     * 公用网络
     */
    private String publicNetworkStatus;

    public String getSiteNetworkStatus() {
        return siteNetworkStatus;
    }

    public void setSiteNetworkStatus(String siteNetworkStatus) {
        this.siteNetworkStatus = siteNetworkStatus;
    }

    public String getProfessionalNetworkStatus() {
        return professionalNetworkStatus;
    }

    public void setProfessionalNetworkStatus(String professionalNetworkStatus) {
        this.professionalNetworkStatus = professionalNetworkStatus;
    }

    public String getPublicNetworkStatus() {
        return publicNetworkStatus;
    }

    public void setPublicNetworkStatus(String publicNetworkStatus) {
        this.publicNetworkStatus = publicNetworkStatus;
    }
}
