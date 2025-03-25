package utils;


/**
 * @author liangzhi
 * @description 字符串工具类
 */
public class StringUtil {
    private StringUtil() {
    }

    /**
     * 判断字符串不为null，且不为空字符串
     *
     * @param value
     * @return
     */
    public static boolean hasLength(String value) {
        return value != null && !"".equals(value.trim());
    }

    public static boolean isNotNull(Integer value) {
        return value != null;
    }
}

