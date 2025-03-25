package utils.http;

import java.io.File;

/**
 * 操作系统工具类
 * @since: 2021/7/20
 * @author: hongdahao
*/
public class OsUtil {

    public static final int LINUX = 0;
    public static final int WINDOWS = 1;
    public static final int UNKNOWN = -1;


    /**
     * 判断操作系统类型
    */
    public static int getOsType(){
        if ("\\".equals(File.separator)) {
            return WINDOWS;
        } else if ("/".equals(File.separator)) {
            return LINUX;
        }else {
            return UNKNOWN;
        }
    }

}
