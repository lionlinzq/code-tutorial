package pers.lionlinzq.algo.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtil {

    public static <F, T> List<T> convertList(List<F> list, Class<T> clazz){
        if(list == null){
            return new ArrayList<>();
        }
        List<T> tList = new ArrayList<>();
        for(F record : list){
            try {
                T t = clazz.newInstance();
                BeanUtils.copyProperties(record, t);
                tList.add(t);
            } catch (Exception e) {
                throw new RuntimeException("创建对象失败", e);
            }
        }
        return tList;
    }

    public static <F, T> T convert(F fRecord, Class<T> clazz){
        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(fRecord, t);
            return t;
        } catch (Exception e) {
            throw new RuntimeException("创建对象失败", e);
        }
    }
}
