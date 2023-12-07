package org.wolflink.sharine.action;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author march
 * @since 2023/6/2 上午9:11
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return 是空字符串
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return 不是空字符串
     */
    public static boolean isPresent(String str) {
        return str != null && !str.trim().equals("");
    }

    /**
     * 检测邮箱是否合法
     *
     * @param email 用户名
     * @return 合法状态
     */
    public static boolean checkEmail(String email) {
        String rule = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式 编译正则表达式
        Pattern p = Pattern.compile(rule);
        //正则表达式的匹配器
        Matcher m = p.matcher(email);
        //进行正则匹配
        return m.matches();
    }


    /**
     * 生成6位随机验证码
     *
     * @return 验证码
     */
    public static String getRandomCode(int length) {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }


    /**
     * 检查字符串是否符合长度
     *
     * @param str 字符串
     * @param l   最小长度
     * @param r   最大长度
     * @return 是否符合
     */
    public static boolean isSpecifiedLength(String str, int l, int r) {
        return str != null && str.length() >= l && str.length() <= r;
    }

    public static boolean compile(String string, String stdOutput) {
        return string.equalsIgnoreCase(stdOutput);
    }
}
