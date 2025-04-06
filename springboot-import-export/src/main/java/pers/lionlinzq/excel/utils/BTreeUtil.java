package pers.lionlinzq.excel.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BTreeUtil {
    private static final Logger logger = LoggerFactory.getLogger(BTreeUtil.class);
    public  static <T extends BTree<T>> T buildTree(final T root, List<T> grantFunctions) {
        if (CollectionUtils.isEmpty(grantFunctions)) {
            return (T) root;
        }
        Map<String, T> map = new LinkedHashMap<>();
        map.put(root.getValue(), root);
        Iterator<T> iterator = grantFunctions.iterator();
        while (iterator.hasNext()) {
            T item = (T) iterator.next();
            if (item.getParentValue() == "null") {
                item.setParentValue(root.getValue());
            }
            map.put(item.getValue(), item);
        }
        map.forEach((key, value) -> {
            String parentId = value.getParentValue();
            if (parentId != null) {
                T parent = map.get(parentId);
                if (map.get(parentId) != null) {
                    parent.add(value);
                }
            }
        });
        return root;
    }

    public static <T extends BTree<T>> void sortTree(BTree<T> root, Function<T,Integer> keyExtractor){
        if(Objects.isNull(root)||CollectionUtils.isEmpty(root.getChildren())){
            return;
        }
        List<T> children = root.getChildren();
        children = children.stream().sorted(Comparator.comparing(keyExtractor)).collect(Collectors.toList());
        root.setChildren(children);
        for(T child:root.getChildren()){
            sortTree(child,keyExtractor);
        }
    }

    public static <T extends BTree<T>> T preOrder(T root, Collection<T> collection){ //得到所有不重复节点
        if (root ==null){
            return null;
        }
        if(root.getChildren()==null){
            return root;
        }
        root.getChildren().forEach(child->{
            T grand = preOrder((T) child,collection);
            collection.add(grand);
        });
        return root;
    }

    public static <T extends BTree<T>> int getChildrenSize(BTree<T> node){
        int count = 1;
        if(node ==null){
            return 0;
        }
        if(node.getChildren()==null){
            return 1;
        }
        for(T child:node.getChildren()){
            count = count + getChildrenSize(child);
        }
        return count;
    }
    public static <T extends BTree<T>> Collection<String>  recursionGetParent(Collection<T> grantFunctions, List<String> sign,Collection<String> recursionUpList){
        //得到所有不重复节点
        if (!CollectionUtils.isEmpty(grantFunctions)&& !CollectionUtils.isEmpty(sign)){
            Iterator<T> iterator = grantFunctions.iterator();
            HashMap<String, T> recursionUpMap = new HashMap<>(); //因为子节点显示依赖父节点，所以要向上递归
            //将查询到的所有节点放入到map列表中
            while (iterator.hasNext()) {
                T grantFunction = (T) iterator.next();
                recursionUpMap.put(grantFunction.getValue(), grantFunction);
            }
            for (String grantFuntionCode:sign) {
                BTreeUtil.recursionUp(grantFuntionCode, recursionUpList, recursionUpMap);
            }
        } else {
            logger.info("传递的节点为空");
        }
        return recursionUpList;
    }
    public static <T extends BTree<T>> void recursionUp(String grantFunction, Collection<String> recursionUpList, HashMap<String, T> recursionUpMap){
        T grantFunctionObject = recursionUpMap.get(grantFunction);
        if(grantFunctionObject==null){
            return;
        }
        if(grantFunctionObject.getParentValue()==null){
            recursionUpList.add(grantFunctionObject.getValue());
            return;
        }else{
            recursionUp(grantFunctionObject.getParentValue(),recursionUpList,recursionUpMap);
            recursionUpList.add(grantFunction);
        }
    }
}
