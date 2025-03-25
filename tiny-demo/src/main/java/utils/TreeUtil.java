package utils;

import org.apache.commons.collections.MapUtils;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 树形工具
 * @author rongdi
 * @date 2017-9-5
 */
public class TreeUtil {

    public static void main(String[] args) {
        List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
        datas.add(new LinkedHashMap(){{put("id",1);put("name","name1");}});
        datas.add(new LinkedHashMap(){{put("id",2);put("pid",1);put("name","name2");}});
        datas.add(new LinkedHashMap(){{put("id",3);put("pid",1);put("name","name3");}});
        datas.add(new LinkedHashMap(){{put("id",4);put("pid",2);put("name","name4");}});
        datas.add(new LinkedHashMap(){{put("id",5);put("pid",2);put("name","name5");}});
        datas.add(new LinkedHashMap(){{put("id",6);put("name","name6");}});
        datas.add(new LinkedHashMap(){{put("id",7);put("pid",6);put("name","name7");}});
        datas.add(new LinkedHashMap(){{put("id",8);put("pid",7);put("name","name8");}});
        List<Map<String,Object>> result = TreeUtil.list2MultyTree(datas,"id","pid","pid",null,"children");
        JSONArray array = new JSONArray(result);
        System.out.println(array);
    }

    /**
     * 将列表数据转成树形结构
     * @param datas 列表数据
     * @param idStr 数据标示id的字段
     * @param pidStr 数据标示父id的字段
     * @param topKey 可以标示树顶层的字段key
     * @param topValue 可以标示树顶层的字段value
     * @param childrenKey 可以标示树顶层的字段value
     * @return
     */
    public static Map<String,Object> list2SingleTree(List<Map<String,Object>> datas,String idStr,String pidStr,String topKey,String topValue,String childrenKey) {
        try {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> data : datas) {
                String keyValue = MapUtils.getString(data,topKey);
                if(keyValue == null || keyValue.equals(topValue)) { //如果是顶层
                    map = new LinkedHashMap<String, Object>();
                    map.putAll(data);
                    groupChildrenTree(map, datas, idStr, pidStr,childrenKey);
                    result.add(map);
                }
            }
            return result.get(0);
        } catch(Exception e) {
            throw e;
        }
    }

    public static List<Map<String,Object>> list2MultyTree(List<Map<String,Object>> datas,String idStr,String pidStr,String topKey,String topValue,String childrenKey) {
        try {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> data : datas) {
                String keyValue = MapUtils.getString(data,topKey);
                if(keyValue == null || keyValue.equals(topValue)) { //如果是顶层
                    map = new LinkedHashMap<String, Object>();
                    map.putAll(data);
                    groupChildrenTree(map, datas, idStr, pidStr,childrenKey);
                    result.add(map);
                }
            }
            return result;
        } catch(Exception e) {
            throw e;
        }
    }

    private static void groupChildrenTree(Map<String, Object> map, List<Map<String, Object>> datas,String idStr,String pidStr,String childrenKey) {
        List<Map<String,Object>> childrenList = new ArrayList<Map<String,Object>>();
        Map<String, Object> childrenMap;
        for(Map<String,Object> data:datas) {
            String targetId = MapUtils.getString(map,idStr);
            String currPid = MapUtils.getString(data,pidStr);
            if(currPid == null) {
                continue;
            }
            if(currPid.equals(targetId)) {
                childrenMap = new LinkedHashMap<String, Object>();
                childrenMap.putAll(data);
                childrenList.add(childrenMap);
                groupChildrenTree(childrenMap,datas,idStr,pidStr,childrenKey);
            }
        }
        if(childrenList.size() > 0) {
            map.put(childrenKey, childrenList);
        }
    }

}
