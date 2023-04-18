package com.stickpoint.devtools.common.entity;


import java.io.Serial;
import java.io.Serializable;

/**
 * @author puye(0303)
 * @since 2023/2/16
 */
@SuppressWarnings("unused")
public class JpsInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7559761689793229440L;
    /**
     * 名字
     */
    private String name;
    /**
     * 运行在哪个端口
     */
    private Integer runningPort;
    /**
     * 应用命名空间
     */
    private String applicationNameSpace;
    /**
     * 应用运行时候的JVM参数
     */
    private String jvmParams;
    /**
     * 应用运行的时候主方法的参数
     */
    private String mainMethodParameters;
    /**
     * Java应用进程的进程id
     */
    private Integer pid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRunningPort() {
        return runningPort;
    }

    public void setRunningPort(Integer runningPort) {
        this.runningPort = runningPort;
    }

    public String getApplicationNameSpace() {
        return applicationNameSpace;
    }

    public void setApplicationNameSpace(String applicationNameSpace) {
        this.applicationNameSpace = applicationNameSpace;
    }

    public String getJvmParams() {
        return jvmParams;
    }

    public void setJvmParams(String jvmParams) {
        this.jvmParams = jvmParams;
    }

    public String getMainMethodParameters() {
        return mainMethodParameters;
    }

    public void setMainMethodParameters(String mainMethodParameters) {
        this.mainMethodParameters = mainMethodParameters;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
