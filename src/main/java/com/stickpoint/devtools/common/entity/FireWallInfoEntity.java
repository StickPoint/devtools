package com.stickpoint.devtools.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author puye(0303)
 * @since 2023/2/16
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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



}
