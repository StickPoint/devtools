package com.stickpoint.devtools.common.entity;


import java.io.Serial;
import java.io.Serializable;

/**
 * description: JavaAppInfoEntity
 *
 * @ClassName : JavaAppInfoEntity
 * @Date 2022/11/23 15:07
 * @Author puye(0303)
 * @PackageName systemp
 */


public class JavaAppInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -992932052039575531L;

    private String name;

    private Integer runningPort;

    private String applicationNameSpace;

    private String jvmParams;

    private String mainMethodParameters;

    private Integer pid;

    private String[] runtimeInfo;

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

    public String[] getRuntimeInfo() {
        return runtimeInfo;
    }

    public void setRuntimeInfo(String[] runtimeInfo) {
        this.runtimeInfo = runtimeInfo;
    }
}
