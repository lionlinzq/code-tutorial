package pers.lionlinzq.excel.utils;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ColUtil {

    public static <T> List<T> nullToEmpty(List<T> list){
        if(list == null){
            return new ArrayList<>(0);
        }
        return list;
    }

    public static <T> Collection<T> nullToEmpty(Collection<T> list){
        if(list == null){
            return new ArrayList<>(0);
        }
        return list;
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer){
        if(list == null){
            return;
        }
        list.forEach(consumer);
    }

    public static <T> boolean isNotEmpty(Collection<T> collect) {
        return collect != null && collect.size() != 0;
    }

    public static <T> boolean isEmpty(Collection<T> collect) {
        return collect == null || collect.isEmpty();
    }


    public static <E, K> Map<K, E> toMap(Collection<E> list, Function<E, K> getKey){
        return toMap(list, getKey, t -> t);
    }

    public static <E, K, V> Map<K, V> toMap(Collection<E> list, Function<E, K> getKey, Function<E, V> getValue){
        Map<K, V> map = new LinkedHashMap<K, V>();
        nullToEmpty(list).forEach(t -> map.put(getKey.apply(t), getValue.apply(t)));
        return map;
    }

    public static <E, K> Map<K, List<E>> group(Collection<E> list, Function<E, K> getKey){
        return group(list, getKey, t -> t);
    }

    public static <E, K, V> Map<K, List<V>> group(Collection<E> list, Function<E, K> getKey, Function<E, V> getValue){
        Map<K, List<V>> map = new LinkedHashMap<K, List<V>>();
        nullToEmpty(list).forEach(t -> {
            K key = getKey.apply(t);
            List<V> childList = map.get(key);
            if(childList == null){
                childList = new ArrayList<V>();
                map.put(key, childList);
            }
            childList.add(getValue.apply(t));
        });
        return map;
    }

    public static <K, V> Map<K, List<V>> addToGroup(Map<K, List<V>> group, K key, V value){
        List<V> childList = group.get(key);
        if(childList == null){
            childList = new ArrayList<V>();
            group.put(key, childList);
        }
        childList.add(value);
        return group;
    }

    public static <T, K> Set<K> toSet(Collection<T> tenantModuleDTOS, Function<T, K> fun) {
        return tenantModuleDTOS == null? new HashSet<K>(): tenantModuleDTOS.stream().map(fun).collect(Collectors.toSet());
    }

    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }

    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    public static int size(List<?> rows) {
        return rows == null? 0: rows.size();
    }
}
