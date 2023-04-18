package com.stickpoint.devtools.common.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author puye(0303)
 * @since 2023/2/17
 */


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

    public String getRuntimeJavaVersion() {
        return runtimeJavaVersion;
    }

    public void setRuntimeJavaVersion(String runtimeJavaVersion) {
        this.runtimeJavaVersion = runtimeJavaVersion;
    }

    public String getRuntimeJavaHomePath() {
        return runtimeJavaHomePath;
    }

    public void setRuntimeJavaHomePath(String runtimeJavaHomePath) {
        this.runtimeJavaHomePath = runtimeJavaHomePath;
    }

    public String getRuntimeJavaServerProvider() {
        return runtimeJavaServerProvider;
    }

    public void setRuntimeJavaServerProvider(String runtimeJavaServerProvider) {
        this.runtimeJavaServerProvider = runtimeJavaServerProvider;
    }

    public String getRuntimeSystemOsVersion() {
        return runtimeSystemOsVersion;
    }

    public void setRuntimeSystemOsVersion(String runtimeSystemOsVersion) {
        this.runtimeSystemOsVersion = runtimeSystemOsVersion;
    }

    public String getRuntimeSystemFramework() {
        return runtimeSystemFramework;
    }

    public void setRuntimeSystemFramework(String runtimeSystemFramework) {
        this.runtimeSystemFramework = runtimeSystemFramework;
    }

    public String getRuntimeCurrentDir() {
        return runtimeCurrentDir;
    }

    public void setRuntimeCurrentDir(String runtimeCurrentDir) {
        this.runtimeCurrentDir = runtimeCurrentDir;
    }
}
