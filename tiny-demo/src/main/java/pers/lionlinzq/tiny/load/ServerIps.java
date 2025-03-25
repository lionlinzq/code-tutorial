package pers.lionlinzq.tiny.load;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerIps {

    public static final Map<String, Integer> WEIGHT_LIST = new LinkedHashMap<String, Integer>();

    public static final List<String> LIST = Arrays.asList(
            "192.168.0.1",
            "192.168.0.2",
            "192.168.0.3",
            "192.168.0.4",
            "192.168.0.5"
    );

    static {
        WEIGHT_LIST.put("192.168.0.1", 1);
        WEIGHT_LIST.put("192.168.0.2", 2);
        WEIGHT_LIST.put("192.168.0.3", 3);
        WEIGHT_LIST.put("192.168.0.4", 4);
        WEIGHT_LIST.put("192.168.0.5", 5);
    }
}
