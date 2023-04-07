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
 * @since 2023/2/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class SystemDetailInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1859503590861224465L;
    /**
     * Java运行时环境版本
     */
    private String runtimeJavaVersion;
    /**
     * Java安装目录:
     */
    private String runtimeJavaHomePath;
    /**
     * Java 运行时环境供应商
     */
    private String runtimeJavaServerProvider;
    /**
     * 操作系统的版本以及名称
     */
    private String runtimeSystemOsVersion;
    /**
     * 操作系统的架构
     */
    private String runtimeSystemFramework;
    /**
     * 用户的当前工作目录
     */
    private String runtimeCurrentDir;
}
