package utils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author rongdi
 */
public class ListUtil {

    /**
     * Null-safe check if the specified collection is empty.
     * <p>
     * Null returns true.
     *
     * @param list the collection to check, may be null
     * @return true if empty or null
     */
    public static <T> boolean isBlank(List<T> list) {
        return (list == null || list.isEmpty());
    }

    /**
     * Null-safe check if the specified collection is not empty.
     * <p>
     * Null returns false.
     *
     * @param list the collection to check, may be null
     * @return true if non-null and non-empty
     */
    public static <T> boolean isNotBlank(List<T> list) {
        return !isBlank(list);
    }

    public static <T> List<T> cloneList(List<T> list) {
        List<T> temp = new ArrayList<T>();
        if (list == null) {
            return temp;
        }
        temp.addAll(list);
        return temp;
    }

    public static <T> void clearList(List<T> list) {
        if (list != null) {
            list.clear();
        }
    }

    public static <T> List<List<T>> splitList(List<T> list, int maxSize) {
        List<List<T>> temp = new ArrayList<List<T>>();
        if (list.size() == 0 || maxSize <= 0) {
            return temp;
        }
        int i = 0;
        while (i < list.size()) {
            int toIndex = (i + maxSize);
            if (toIndex > list.size()) {
                toIndex = toIndex - maxSize + (list.size() - i);
            }
            temp.add(new ArrayList<T>(list.subList(i, toIndex)));
            i += maxSize;
        }
        return temp;
    }

    public static <T> List<T> map2List(Collection<T> vals) {
        List<T> ts = new ArrayList<T>();
        if (vals == null) {
            return ts;
        }
        Iterator<T> it = vals.iterator();
        while (it.hasNext()) {
            ts.add(it.next());
        }
        return ts;
    }

    public static List<Integer> arr2List(int[] ids) {
        List<Integer> idsList = new ArrayList<Integer>();
        for (int id : ids) {
            idsList.add(id);
        }
        return idsList;
    }

    public static List<String> arr2List(String[] strs) {
        List<String> list = new ArrayList<String>();
        for (String str : strs) {
            list.add(str);
        }
        return list;
    }

    public static List<Integer> arr2List(int id) {
        List<Integer> idsList = new ArrayList<Integer>(1);
        idsList.add(id);
        return idsList;
    }

    public static List<Long> str2Long(List<String> ids) {
        if (ListUtil.isBlank(ids)) {
            return Collections.emptyList();
        }
        List<Long> list = new ArrayList<>();
        for (String id : ids) {
            list.add(Long.valueOf(id));
        }
        return list;
    }


    public static Map<Long, Map<String, Object>> list2Map(List<Map<String, Object>> objectList, String key) {
        Map<Long, Map<String, Object>> dataMap = new HashMap<Long, Map<String, Object>>();
        if (ListUtil.isBlank(objectList)) {
            return dataMap;
        }
        objectList.forEach(map -> {
            Long keycode = MapUtils.getLong(map, key);
            dataMap.put(keycode, map);
        });
        return dataMap;
    }


    //将list转成map
    public static  Map<String, Map<String, Object>> list2MapStr(List<Map<String, Object>> objectList, String key) {
        Map<String, Map<String, Object>> dataMap = new HashMap<String, Map<String, Object>>();
        if (ListUtil.isBlank(objectList)) {
            return dataMap;
        }
        objectList.forEach(map -> {
            String keycode =  MapUtils.getString(map, key);
            if(keycode != null && !dataMap.containsKey(keycode)) {
                dataMap.put(keycode, map);
            }
        });
        return dataMap;
    }

    public static void transMap2Bean(Map<String, Object> map, Object obj) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (map.containsKey(key)) {
                Object value = map.get(key);
                // 得到property对应的setter方法
                Method setter = property.getWriteMethod();
                setter.invoke(obj, value);
            }
        }

    }

    /**
     * 按照指定key值为依据合并产品和租户数据，使用租户覆盖产品数据
     *
     * @param productObjectList
     * @param tencentObjectList
     * @param key
     */
    public static void mergeProductAndTenantList(List<Map<String, Object>> productObjectList, List<Map<String, Object>> tencentObjectList, String key) {
        if (productObjectList != null) {
            Map<Long, Map<String, Object>> productObjectMap = ListUtil.list2Map(productObjectList, key);
            Map<Long, Map<String, Object>> tencentObjectMap = ListUtil.list2Map(tencentObjectList, key);
            productObjectMap.forEach((objectcode, obj) -> {
                Map<String, Object> tenantData = (Map) tencentObjectMap.get(objectcode);
                obj.put("marktype", 1);
                if (tenantData != null) {
                    tenantData.put("marktype",3);
                    obj.putAll(tenantData);
                }
            });
            tencentObjectMap.forEach((objectcode, obj) -> {
                if (!productObjectMap.containsKey(objectcode)) {
                    obj.put("marktype", 2);
                    productObjectList.add(obj);
                }
            });
        }
    }

    //按照指定key值为依据合并产品和租户数据，使用租户覆盖产品数据 不变更marktype值
    public static void mergeProductAndTenantListEx(List<Map<String, Object>> productObjectList, List<Map<String, Object>> tencentObjectList, String key) {
        if (productObjectList != null) {
            Map<Long, Map<String, Object>> productObjectMap = ListUtil.list2Map(productObjectList, key);
            Map<Long, Map<String, Object>> tencentObjectMap = ListUtil.list2Map(tencentObjectList, key);
            productObjectMap.forEach((objectcode, obj) -> {
                Map<String, Object> tenantData = (Map) tencentObjectMap.get(objectcode);
                //obj.put("marktype", 1);
                if (tenantData != null) {
                    //tenantData.put("marktype",3);
                    obj.putAll(tenantData);
                }
            });
            tencentObjectMap.forEach((objectcode, obj) -> {
                if (!productObjectMap.containsKey(objectcode)) {
                    //obj.put("marktype", 2);
                    productObjectList.add(obj);
                }
            });
        }
    }

    /**
     * List数据，按 link 连接符连接，转成字符串
     * @param list   [a,b]
     * @param link  连接符  如  ,
     * @return  a,b
     */
    public static String joinToString(List<String> list, String link) {
        return joinToString(list, link, "", "");
    }

    /**
     * List数据，按link 和 prefixAndSuffix 来转成字符串
     * @param list  [a,b]
     * @param link  连接符 如 ,
     * @param prefixAndSuffix '
     * @return  'a','b'
     */
    public static String joinToString(List<String> list, String link, String prefixAndSuffix) {
        return joinToString(list, link, prefixAndSuffix, prefixAndSuffix);
    }

    /**
     * List数据，按link,prefix,suffix 来转成字符串
     * @param list  [a,b]
     * @param link 连接符 如 ,
     * @param prefix $
     * @param suffix  !
     * @return  $a!,$b!
     */
    public static String joinToString(List<String> list, String link, String prefix, String suffix ) {

        StringBuilder sb = new StringBuilder();

        if(list != null && list.size() > 0) {
            for(String s : list) {
                sb.append(String.format("%s%s%s%s", prefix, s, suffix, link));
            }
        }

        if(sb.length() > 0 && link != null && !link.isEmpty()) {
            String res = sb.substring(0, sb.length() - link.length());
            return res;
        }

        return sb.toString();
    }
    /**
     * 把字符串按特定符号分割成集合
     * @param data
     * @param link  分割符号
     * @return
     */
    public static <T> List<T> joinToList(String data,String link,Class<T> clazz){
        List<T> list = new ArrayList<T>();
        if(StringUtils.isEmpty(data))
            return list;
        String[] temp = data.split(link);
        for (String obj : temp) {
            if(clazz.getName().equals("java.lang.Integer")){
                list.add((T)Integer.valueOf(obj));
            }else if(clazz.getName().equals("java.lang.Double")){
                list.add((T)Double.valueOf(obj));
            }else if(clazz.getName().equals("java.lang.Float")){
                list.add((T)Float.valueOf(obj));
            }else if(clazz.getName().equals("java.lang.Long")){
                list.add((T)Long.valueOf(obj));
            }else{
                list.add((T)obj);
            }
        }
        return list;
    }
}
