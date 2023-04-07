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
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

}
