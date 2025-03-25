package pers.lionlinzq.tiny.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 按照访问和插入顺序排序，最近使用的排在前面
 *
 * @author lzq
 */
public class LruByLinkHashMapV2 extends LinkedHashMap<Integer, Integer> {

    private final int capacity;

    public LruByLinkHashMapV2(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    public Integer get(Object key) {
        return super.getOrDefault(key, -1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LruByLinkHashMapV2 lruByLinkHashMap = new LruByLinkHashMapV2(3);
        lruByLinkHashMap.put(1,1);
        lruByLinkHashMap.put(2,2);
        lruByLinkHashMap.put(3,3);
        System.out.println(lruByLinkHashMap);

        lruByLinkHashMap.get(2);
        lruByLinkHashMap.get(3);
        lruByLinkHashMap.get(1);
        lruByLinkHashMap.put(4,4);

        System.out.println(lruByLinkHashMap);
    }
}
