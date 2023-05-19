package com.thy.backend.parent.framework.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取日志IP
 *
 * @author root
 * @version 1.0
 */
@Slf4j
public class IpAddressConverter extends ClassicConverter {
    private static String WEB_IP;

    static {
        try {
            WEB_IP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取日志Ip异常", e);
            WEB_IP = "unknown";
        }
    }

    @Override
    public String convert(ILoggingEvent event) {
        return WEB_IP;
    }

}
