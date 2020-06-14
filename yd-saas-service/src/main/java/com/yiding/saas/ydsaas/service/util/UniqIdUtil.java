package com.yiding.saas.ydsaas.service.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhiyuan
 * @Depiction 序列util
 * @Date 2018/1/8
 */
public class UniqIdUtil {
    private static UniqIdUtil me = new UniqIdUtil();
    private String hostAddr;
    private final Random random = new SecureRandom();
    private final Logger log = LoggerFactory.getLogger(UniqIdUtil.class);
    private final UniqIdUtil.UniqTimer timer = new UniqIdUtil.UniqTimer();

    private UniqIdUtil() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            this.hostAddr = addr.getHostAddress();
        } catch (IOException var2) {
            this.log.error("[UniqID] Get HostAddr Error", var2);
            this.hostAddr = String.valueOf(System.currentTimeMillis());
        }

        if (null == this.hostAddr || this.hostAddr.trim().length() == 0 || "127.0.0.1".equals(this.hostAddr)) {
            this.hostAddr = String.valueOf(System.currentTimeMillis());
        }

        this.hostAddr = this.hostAddr.substring(this.hostAddr.length() - 2).replace(".", "0");
        if (this.log.isDebugEnabled()) {
            this.log.debug("[UniqID]hostAddr is:" + this.hostAddr);
        }

    }

    public static UniqIdUtil getInstance() {
        return me;
    }

    private String getUniqTime() {
        return this.timer.getCurrentTime();
    }

    public String getUniqID() {
        StringBuffer sb = new StringBuffer();
        String t = this.getUniqTime();
        sb.append(t);
        sb.append(this.hostAddr);
        sb.append(this.getUniqThreadCode(9));
        sb.append(this.random.nextInt(8999999) + 1000000);
        if (this.log.isDebugEnabled()) {
            this.log.debug("[UniqID.getUniqID]" + sb.toString());
        }

        return sb.toString();
    }

    private String get16BitUniqID(String supplierId) {
        StringBuffer sb = new StringBuffer();
        String t = this.getUniqTime();
        sb.append(t.substring(2));
        sb.append(supplierId.hashCode());
        if (this.log.isDebugEnabled()) {
            this.log.debug("[UniqID.getUniqID]" + sb.toString());
        }

        return sb.toString();
    }

    private String getUniqThreadCode(int length) {
        String threadCode = StringUtils.left(String.valueOf(Thread.currentThread().hashCode()), length);
        if (this.log.isDebugEnabled()) {
            this.log.debug("[UniqID.getUniqThreadCode]" + threadCode + "----length:" + threadCode.length());
        }

        return StringUtils.leftPad(threadCode, length, "0");
    }

    private static String timestamp2Datetimes(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date(timestamp));
    }

    private static String timestamp2Date(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date(timestamp));
    }

    public static void main(String[] args) {
        String uid = getInstance().get16BitUniqID("2088102146931393");
        System.out.println(uid);
        System.out.println(System.currentTimeMillis());
        System.out.println(uid.length());
        System.out.println("2088102146931393".hashCode());
        System.out.println(getInstance().getUniqID());
        System.out.println(getInstance().getUniqID().length());
    }

    private class UniqTimer {
        private final AtomicLong lastTime;

        private UniqTimer() {
            this.lastTime = new AtomicLong(System.currentTimeMillis());
        }

        public String getCurrentTime() {
            if (UniqIdUtil.this.log.isDebugEnabled()) {
                UniqIdUtil.this.log.debug("[UniqID.getUniqTime]" + UniqIdUtil.timestamp2Datetimes(this.lastTime.incrementAndGet()));
            }

            if (!UniqIdUtil.timestamp2Date(this.lastTime.incrementAndGet()).equals(UniqIdUtil.timestamp2Date(System.currentTimeMillis()))) {
                this.lastTime.set(System.currentTimeMillis() + (long) UniqIdUtil.this.random.nextInt(10000));
            }

            return UniqIdUtil.timestamp2Datetimes(this.lastTime.incrementAndGet());
        }
    }
}
