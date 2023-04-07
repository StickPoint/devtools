package com.stickpoint.devtools.common.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author puye(0303)
 * @since 2023/2/3
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

}
