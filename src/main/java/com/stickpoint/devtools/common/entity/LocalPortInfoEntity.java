package com.stickpoint.devtools.common.entity;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author puye(0303)
 * @since 2023/2/16
 */


public class LocalPortInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -8843281520015794648L;

    private int canBeUsedPortListCounts;

    private int alreadyInUsePortsCounts;

    private List<Integer> canBeUsedPortList;

    private List<Integer> alreadyInUsePorts;

    public int getCanBeUsedPortListCounts() {
        return canBeUsedPortListCounts;
    }

    public void setCanBeUsedPortListCounts(int canBeUsedPortListCounts) {
        this.canBeUsedPortListCounts = canBeUsedPortListCounts;
    }

    public int getAlreadyInUsePortsCounts() {
        return alreadyInUsePortsCounts;
    }

    public void setAlreadyInUsePortsCounts(int alreadyInUsePortsCounts) {
        this.alreadyInUsePortsCounts = alreadyInUsePortsCounts;
    }

    public List<Integer> getCanBeUsedPortList() {
        return canBeUsedPortList;
    }

    public void setCanBeUsedPortList(List<Integer> canBeUsedPortList) {
        this.canBeUsedPortList = canBeUsedPortList;
    }

    public List<Integer> getAlreadyInUsePorts() {
        return alreadyInUsePorts;
    }

    public void setAlreadyInUsePorts(List<Integer> alreadyInUsePorts) {
        this.alreadyInUsePorts = alreadyInUsePorts;
    }
}
