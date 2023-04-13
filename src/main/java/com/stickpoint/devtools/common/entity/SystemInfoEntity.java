package com.stickpoint.devtools.common.entity;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author puye(0303)
 * @since 2023/2/3
 */


public class SystemInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2728529885622403860L;
    /**
     * Java服务进程详情
     */
    private List<JavaAppInfoEntity> javaAppInfoEntity;
    /**
     * 本机端口详情
     */
    private LocalPortInfoEntity localPortInfoEntity;
    /**
     * 本机网关详情
     */
    private String gatewayInfo;
    /**
     * 内网IP详情
     */
    private List<String> otherIpaddressInfo;
    /**
     * 系统详情面板
     */
    private SystemDetailInfoEntity systemDetailInfoEntity;
    /**
     * 防火墙信息
     */
    private FireWallInfoEntity fireWallInfo;

    public List<JavaAppInfoEntity> getJavaAppInfoEntity() {
        return javaAppInfoEntity;
    }

    public void setJavaAppInfoEntity(List<JavaAppInfoEntity> javaAppInfoEntity) {
        this.javaAppInfoEntity = javaAppInfoEntity;
    }

    public LocalPortInfoEntity getLocalPortInfoEntity() {
        return localPortInfoEntity;
    }

    public void setLocalPortInfoEntity(LocalPortInfoEntity localPortInfoEntity) {
        this.localPortInfoEntity = localPortInfoEntity;
    }

    public String getGatewayInfo() {
        return gatewayInfo;
    }

    public void setGatewayInfo(String gatewayInfo) {
        this.gatewayInfo = gatewayInfo;
    }

    public List<String> getOtherIpaddressInfo() {
        return otherIpaddressInfo;
    }

    public void setOtherIpaddressInfo(List<String> otherIpaddressInfo) {
        this.otherIpaddressInfo = otherIpaddressInfo;
    }

    public SystemDetailInfoEntity getSystemDetailInfoEntity() {
        return systemDetailInfoEntity;
    }

    public void setSystemDetailInfoEntity(SystemDetailInfoEntity systemDetailInfoEntity) {
        this.systemDetailInfoEntity = systemDetailInfoEntity;
    }

    public FireWallInfoEntity getFireWallInfo() {
        return fireWallInfo;
    }

    public void setFireWallInfo(FireWallInfoEntity fireWallInfo) {
        this.fireWallInfo = fireWallInfo;
    }
}
