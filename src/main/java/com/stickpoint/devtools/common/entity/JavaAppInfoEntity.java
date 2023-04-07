package com.stickpoint.devtools.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
}
