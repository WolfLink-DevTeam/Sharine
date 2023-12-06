package org.wolflink.sharine.utils;


import cn.dev33.satoken.secure.BCrypt;

/**
 * 加密工具
 *
 * @author march
 * @since 2023/6/2 下午5:37
 */
public class EncryptionUtil {

    /**
     * 加密
     *
     * @param str 明文
     * @return 密文
     */
    public static String encode(String str) {
        return BCrypt.hashpw(str, BCrypt.gensalt());
    }

    /**
     * 匹配
     *
     * @param str  明文
     * @param pass 密文
     * @return 是否匹配
     */
    public static boolean match(String str, String pass) {
        return BCrypt.checkpw(str, pass);
    }
}
