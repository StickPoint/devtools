package com.sinsy;

import org.junit.Test;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * description: MyTest
 *
 * @ClassName : MyTest
 * @Date 2022/10/30 19:40
 * @Author puye(0303)
 * @PackageName com.sinsy
 */
public class MyTest {

    @Test
    public void test01() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(Arrays.toString(localHost.getAddress()));
            System.out.println(localHost.getHostAddress());
            System.out.println(localHost.getHostName());
            System.out.println(localHost.getCanonicalHostName());
            //    -------------------------------------------------
            System.out.println("-------------------------------------------------");
            InetAddress ip4 = Inet4Address.getLocalHost();
            InetAddress ip6 = Inet6Address.getLocalHost();
            System.out.println(Arrays.toString(ip6.getAddress()));
            System.out.println(ip6.getHostAddress());
            System.out.println(ip6.getHostName());
            System.out.println(ip6.getCanonicalHostName());
            System.out.println("-------------------------------------------------");
            System.out.println(Arrays.toString(ip4.getAddress()));
            System.out.println(ip4.getHostAddress());
            System.out.println(ip4.getHostName());
            System.out.println(ip4.getCanonicalHostName());
            System.out.println("-------------------------------------------------");
            System.out.println(Arrays.toString(getAllLocalHostIP()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static String[] getAllLocalHostIP() {
        String[] ret = null;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostName = addr.getHostName();
            if (hostName.length() > 0) {
                InetAddress[] addrs = InetAddress.getAllByName(hostName);
                if (addrs.length > 0) {
                    ret = new String[addrs.length];
                    for (int i = 0; i < addrs.length; i++) {
                        ret[i] = addrs[i].getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
            ret = null;
        }
        return ret;
    }
}
