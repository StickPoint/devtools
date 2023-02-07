package com.stickpoint.devtools.common.entity;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author puye(0303)
 * @since 2023/2/3
 */
public class SystemInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2728529885622403860L;
    /**
     * 系统已经开放的端口
     */
    private int[] openedPorts;
    /**
     * 当前防火墙协议状态
     */
    private String firewallPolicy;
    /**
     * java服务情况
     */
    private String javaServerInfo;
    /**
     * 安全软件接入情况
     */
    private String securitySoftwareInfo;
    /**
     * 本地网络集群情况
     */
    private String localNetGroup;

}
