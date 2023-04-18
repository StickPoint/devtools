package com.stickpoint.devtools.common.entity;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * description: IpInfoEntity
 *
 * @ClassName : IpInfoEntity
 * @Date 2022/10/30 19:36
 * @Author puye(0303)
 * @PackageName com.stickpoint.devtools.common.entity
 */
public class IpInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5210461740866873238L;
    /**
     * ipv4地址
     */
    private String ipv4Address;
    /**
     * ipv6地址
     */
    private String ipv6Address;
    /**
     * ip地址组
     */
    private String[] ipArray;
    /**
     * 开放的端口
     */
    private Integer[] openPorts;
    /**
     * 主机名称
     */
    private String hostName;
    /**
     * 网关
     */
    private String gatewayAddress;
    /**
     * 子网掩码
     */
    private String subnetMask;

    private static final Builder BUILDER = new Builder();

    public IpInfoEntity(Builder builder) {
        this.ipv4Address=builder.getIpv4Address();
        this.hostName=builder.getHostName();
        this.gatewayAddress=builder.getGatewayAddress();
        this.ipArray= builder.getIpArray();
        this.ipv6Address= builder.getIpv6Address();
        this.openPorts= builder.getOpenPorts();
        this.subnetMask=builder.getSubnetMask();
    }

    public String getIpv4Address() {
        return ipv4Address;
    }

    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public String[] getIpArray() {
        return ipArray;
    }

    public void setIpArray(String[] ipArray) {
        this.ipArray = ipArray;
    }

    public Integer[] getOpenPorts() {
        return openPorts;
    }

    public void setOpenPorts(Integer[] openPorts) {
        this.openPorts = openPorts;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getGatewayAddress() {
        return gatewayAddress;
    }

    public void setGatewayAddress(String gatewayAddress) {
        this.gatewayAddress = gatewayAddress;
    }

    public String getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
    }

    public static class Builder{
        private String ipv4Address;

        private String ipv6Address;

        private String[] ipArray;

        private Integer[] openPorts;
        /**
         * 主机名称
         */
        private String hostName;
        /**
         * 网关
         */
        private String gatewayAddress;
        /**
         * 子网掩码
         */
        private String subnetMask;

        public String getIpv4Address() {
            return ipv4Address;
        }

        public Builder setIpv4Address(String ipv4Address) {
            this.ipv4Address = ipv4Address;
            return this;
        }

        public String getIpv6Address() {
            return ipv6Address;
        }

        public Builder setIpv6Address(String ipv6Address) {
            this.ipv6Address = ipv6Address;
            return this;
        }

        public String[] getIpArray() {
            return ipArray;
        }

        public Builder setIpArray(String[] ipArray) {
            this.ipArray = ipArray;
            return this;
        }

        public Integer[] getOpenPorts() {
            return openPorts;
        }

        public Builder setOpenPorts(Integer[] openPorts) {
            this.openPorts = openPorts;
            return this;
        }

        public String getHostName() {
            return hostName;
        }

        public Builder setHostName(String hostName) {
            this.hostName = hostName;
            return this;
        }

        public String getGatewayAddress() {
            return gatewayAddress;
        }

        public Builder setGatewayAddress(String gatewayAddress) {
            this.gatewayAddress = gatewayAddress;
            return this;
        }

        public String getSubnetMask() {
            return subnetMask;
        }

        public Builder setSubnetMask(String subnetMask) {
            this.subnetMask = subnetMask;
            return this;
        }

        public IpInfoEntity build(){
            return new IpInfoEntity(this);
        }
    }

    public static Builder builder(){
        return BUILDER;
    }

    @Override
    public int hashCode() {
        return hostName.hashCode() & ipv4Address.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.nonNull(obj)&&obj.getClass()==this.getClass()){
            return getHostName().equals(((IpInfoEntity) obj).getHostName())
                    &&ipv4Address.equals(((IpInfoEntity) obj).getIpv4Address());
        }
        return false;
    }
}
