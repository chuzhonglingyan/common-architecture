package com.yuntian.architecture.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 * @Auther: yuntian
 * @Date: 2020/2/8 0008 15:10
 * @Description:
 */
public class Md5Util {

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    public static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return Hex.encodeHexString(bs);
        } catch (Exception e) {
            return null;
        }
    }
}
