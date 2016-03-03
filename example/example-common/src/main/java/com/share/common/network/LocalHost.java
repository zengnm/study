package com.share.common.network;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 本机的网络信息
 */
public class LocalHost {

    private final static Log log = LogFactory.getLog(LocalHost.class);

    private static NetworkInterface localNetworkInterface;
    private static String hostName;


    static {
        try {

            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (!networkInterface.getName().equals("lo")) {
                    localNetworkInterface = networkInterface;
                    break;
                }
            }
        } catch (SocketException e) {
            log.error("init LocalHost error!", e);
        }

        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.error("init hostname error!", e);
        }

    }

    /**
     * 取本机的网卡接口
     * @return
     */
    public static NetworkInterface getLocalNetworkInterface() {
        return localNetworkInterface;
    }

    /**
     * 取本机的机器名称
     * @return
     */
    public static String getMachineName() {
        return hostName;
    }
}

