package top.lijunliang.permission.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;

/**
 * MD5通用类
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

    public static void main(String[] args) {
        System.out.println(encryption("root", "md5", "123456", 2));
    }
}
