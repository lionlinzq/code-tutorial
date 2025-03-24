package pers.lionlinzq.algo.utils;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeUtil {

    public static <T> Tree<T> buildTree(T first, List<T> children, Function<T, String> getKey, Function<T, String> getParentKey) {
        return buildTree(first, children, getKey, getParentKey, false);
    }

    public static <T> Tree<T> buildTree(T first, List<T> children, Function<T, String> getKey, Function<T, String> getParentKey, boolean ignoreCheckParentNodeNotExists) {

        Map<String, Node<T>> nodeMap = new LinkedHashMap<>();
        Node<T> firstNode = null;
        if(first != null){
            String parentKey = getKey.apply(first);
            Assert.hasLength(parentKey, "节点key不能为空");
            firstNode = new Node<>(first);
            nodeMap.put(parentKey, firstNode);
        }
        for (T child : children) {
            Assert.hasLength(getKey.apply(child), "节点key不能为空");
            Assert.isTrue(nodeMap.put(getKey.apply(child), new Node<>(child)) == null, "节点" + getKey.apply(child) + "重复");
        }
        for(Node<T> node : nodeMap.values()){
            String parentKey = getParentKey.apply(node.getData());
            if(StringUtils.isNotEmpty(parentKey)){
                Node<T> parentNode = nodeMap.get(parentKey);
                if(!ignoreCheckParentNodeNotExists){
                    Assert.notNull(parentNode, "节点" + getKey.apply(node.getData()) + "的父节点" + getParentKey.apply(node.getData()) + "不存在");
                }
                if(parentNode != null){
                    parentNode.addChild(node);
                    node.setParent(parentNode);
                }
            }else if(firstNode != null && node != firstNode){
                firstNode.addChild(node);
                node.setParent(firstNode);
            }

        }
        return new Tree(nodeMap.values());
    }

    /**
     * 转树结构
     * @Author: lijuntao
     * @Date: 2023/8/21
     **/
    public static <T extends TreeData> List<TreeData> toTreeData(T treeData, List<T> dataListList, boolean ignoreCheckParentNodeNotExists){
        Tree<T> tree = TreeUtil.buildTree(treeData, dataListList, T::getKey, T::getParentKey, ignoreCheckParentNodeNotExists);
        tree.forEachConsumerParentAndChild((p, c) -> {
            List<TreeData> children = p.getChildren();
            children = children == null? new ArrayList<>(): children;
            children.add(c);
            p.setChildren(children);
        });
        return tree.getRoots().stream().map(Node::getData).collect(Collectors.toList());
    }

    public static class Tree<T> {

        private Collection<Node<T>> nodeList;

        public Tree(Collection<Node<T>> nodeList) {
            this.nodeList = nodeList;
        }



        public List<Node<T>> getRoots() {
            List<Node<T>> roots = new ArrayList<>();
            if(nodeList != null){
                for (Node<T> node : nodeList) {
                    if (node.getParent() == null) {
                        roots.add(node);
                    }
                }
            }
            return roots;
        }

        public List<T> getDataList(){
            if(nodeList != null){
                return nodeList.stream().map(Node::getData).collect(Collectors.toList());
            }
            return new ArrayList<>();
        }

        public void forEachConsumerParentAndChild(BiConsumer<T, T> consumer){
            List<Node<T>> roots = getRoots();
            for (Node<T> root : roots) {
                recursive(root, consumer);
            }
        }

        private void recursive(Node<T> node, BiConsumer<T, T> consumer){
            if(node != null){
                consumer.accept(Optional.ofNullable(node.getParent()).map(Node::getData).orElse(null), node.getData());
                List<Node<T>> children = node.getChildren();
                if(children != null){
                    for (Node<T> child : children) {
                        recursive(child, consumer);
                    }
                }
            }
        }

    }

    public static interface TreeData {
        String getKey();
        String getParentKey();
        List<TreeData> getChildren();
        void setChildren(List<TreeData> children);
    }

    @Data
    public static class Node<T>{
        private Node<T> parent;
        private T data;
        private List<Node<T>> children;

        public Node() {
        }

        public Node(@NotNull T data) {
            if(data == null){
                throw new RuntimeException("data can not be null");
            }
            this.data = data;
        }

        public void addChild(Node<T> node) {
            if(children == null){
                children = new ArrayList<>();
            }
            children.add(node);
        }
    }
}
