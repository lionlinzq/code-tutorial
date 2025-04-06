package pers.lionlinzq.excel.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    public static String toJSONString(Object obj) {
        return JSONObject.toJSONString(obj);
    }


    public static <T> T parseObject(String context, Class<T> clazz) {
        return parseObject(context, clazz, false);
    }

    public static <T> T parseObject(String context, Class<T> clazz, boolean ignoreError) {
        try {
            return JSONObject.parseObject(context, clazz);
        } catch (Exception e) {
            if (!ignoreError) {
                throw e;
            }else{
                log.error("parseObject error", e);
                return null;
            }
        }
    }
}
