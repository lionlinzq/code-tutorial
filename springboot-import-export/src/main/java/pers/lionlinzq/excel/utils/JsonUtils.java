package pers.lionlinzq.excel.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //mapper.registerModules(new JavaTimeModule()); // 解决 LocalDateTime 的序列化
    }


    /**
     * 将对象序列化成json字符串
     *
     * @param obj 待序列化的对象
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public static String serialize(Object obj){
        return mapper.writeValueAsString(obj);
    }

    /**
     * 将json字符串反序列化成对象
     * @param src 待反序列化的json字符串
     * @param t   反序列化成为的对象的class类型
     * @return
     */
    @SneakyThrows
    public static <T> T deserialize(String src, Class<T> t){
        if (src == null) {
            return null;
        }
        if("{}".equals(src.trim())) {
            return null;
        }
        return mapper.readValue(src, t);
    }

}
