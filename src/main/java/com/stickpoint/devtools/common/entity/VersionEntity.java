package com.stickpoint.devtools.common.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author puye(0303)
 * @since 2023/2/2
 */
public class VersionEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3226821710919224918L;

    /**
     * 版本id
     * 版本id 组成为 hash{文件个数} + 版本发布日期时间戳-13位 + {八位字母+数字组合随机}
     */
    private String versionId;

    private String aaa;

}
