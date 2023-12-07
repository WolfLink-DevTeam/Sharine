package org.wolflink.sharine.action;


import cn.dev33.satoken.secure.BCrypt;
import org.springframework.stereotype.Component;

/**
 * 加密工具
 *
 * @author march
 * @since 2023/6/2 下午5:37
 */
@Component
public class EncryptAction {

    /**
     * 加密
     *
     * @param str 明文
     * @return 密文
     */
    public String encode(String str) {
        return BCrypt.hashpw(str, BCrypt.gensalt());
    }

    /**
     * 匹配
     *
     * @param str  明文
     * @param pass 密文
     * @return 是否匹配
     */
    public boolean match(String str, String pass) {
        return BCrypt.checkpw(str, pass);
    }
}
