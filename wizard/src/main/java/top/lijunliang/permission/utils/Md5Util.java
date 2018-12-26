package top.lijunliang.permission.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;

/**
 * MD5通用类
 *
 * @author 浩令天下
 * @since 2017.04.15
 * @version 1.0.0_1
 *
 */
public class Md5Util {

    /**
     *
     * @param password 密码
     * @param key 密匙
     * @param method 加密方法
     * @param frequency 加密次数
     * @return
     */
    public static String encryption(String password, String method, String key, int frequency)
    {
        return new SimpleHash(method, password, ByteSource.Util.bytes(key), frequency).toHex();
    }
}
