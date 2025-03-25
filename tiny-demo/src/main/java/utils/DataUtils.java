package utils;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rongdi
 * @create 2017-02-27 15:33
 **/
public class DataUtils {

    public static Long[] strs2Longs(String[] strs) {
        if(strs == null || strs.length == 0) return new Long[]{};
        Long[] result = new Long[strs.length];
        for(int i = 0;i < strs.length;i++) {
            result[i] = Long.valueOf(strs[i]);
        }
        return result;
    }

    public static List<Long> strs2Longs(List<String> listStr) {
        if(ListUtil.isBlank(listStr)) return Lists.newArrayList();
        return listStr.stream().map(s -> Long.valueOf(s)).collect(Collectors.toList());
    }

}
