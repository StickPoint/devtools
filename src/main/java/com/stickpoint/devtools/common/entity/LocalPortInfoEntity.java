package com.stickpoint.devtools.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author puye(0303)
 * @since 2023/2/16
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LocalPortInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -8843281520015794648L;

    private int canBeUsedPortListCounts;

    private int alreadyInUsePortsCounts;

    private List<Integer> canBeUsedPortList;

    private List<Integer> alreadyInUsePorts;

}
