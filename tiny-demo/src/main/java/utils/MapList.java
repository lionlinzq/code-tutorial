package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 支持按照Map里的某个key去判断list是否已经有了该map有了就不要加入
 * @author rongdi
 * @create 2017-05-17 14:45
 **/
public class MapList extends ArrayList {

    private Map<String,Map> keyMap = new HashMap<String,Map>();

    public boolean add(String key,Map<String,Object> data) {
        if(keyMap.containsKey(key)) {
            return false;
        } else {
            keyMap.put(key,data);
            return add(data);
        }
    }

    public Map<String,Object> get(String key) {
        return keyMap.get(key);
    }
}
