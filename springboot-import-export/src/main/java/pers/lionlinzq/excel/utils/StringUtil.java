package pers.lionlinzq.excel.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 字符串工具类
 */
@Slf4j
public class StringUtil {

    /**
     * 子字符切割,保留后面层数
     *
     * @param str         待切割函数
     * @param cuttingMark 切割标记
     * @param reservedNum 保留层数
     * @return {@link String}
     */
    public static String subStringLastAt(String str, String cuttingMark, int reservedNum) {
        if (str == null || cuttingMark == null || reservedNum <= 0) {
            return "";
        }

        int lastSlashIndex = str.lastIndexOf(cuttingMark);
        if (lastSlashIndex == -1) {
            return str;
        }

        int count = 1;
        while (lastSlashIndex != -1 && count < reservedNum) {
            lastSlashIndex = str.lastIndexOf(cuttingMark, lastSlashIndex - 1);
            count++;
        }

        return str.substring(lastSlashIndex + 1);
    }

    public static void main(String[] args) {
        String str = "a/b/c/d";
        String s = subStringLastAt(str, "z", 1);
        System.out.println(s);
    }
}
