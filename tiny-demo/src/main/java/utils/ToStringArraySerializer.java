package utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Long集合转成String集合的序列化类
 * @author rongdi
 * @create 2017-02-27 11:24
 **/
public class ToStringArraySerializer extends JsonSerializer<List<Long>> {

    @Override
    public void serialize(List<Long> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        List<String> strs = new ArrayList<String>();
        for(Long v:value) {
            strs.add(String.valueOf(v));
        }
        gen.writeObject(strs);

    }

}
