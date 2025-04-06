package pers.lionlinzq.excel.utils;

import pers.lionlinzq.algo.base.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * 树工具类
 *
 * @author lin
 * @date 2024/03/20
 */
public class TreeUtils {

    /**
     * 数组构建树节点
     *
     * @param array 阵列
     * @return {@link TreeNode}
     */
    public static TreeNode ArrayBuildTreeNode(int[] array){
        return createTreeHelper(array, 0);
    }

    /**
     * 创建树辅助对象
     *
     * @param array 阵列
     * @param index 指数
     * @return {@link TreeNode}
     */
    public static TreeNode createTreeHelper(int[] array, int index){
        if (index >= array.length){
            return null;
        }
        TreeNode cur = new TreeNode(array[index]);
        cur.left = createTreeHelper(array, 2 * index + 1);
        cur.right = createTreeHelper(array, 2 * index + 2);
        return cur;
    }

    /**
     * 获取树高
     *
     * @param treeNode 树节点
     * @return int 树的高度
     */
    public static int getHeightOfTree(TreeNode treeNode){
        return getHeightOfTreeHelper(treeNode);
    }

    /**
     * 获取树高度辅助方法
     *
     * @param treeNode 树节点
     * @return int
     */
    public static int getHeightOfTreeHelper(TreeNode treeNode){
        if (treeNode == null){
            return 0;
        }
        return 1 + Math.max(getHeightOfTreeHelper(treeNode.left),getHeightOfTreeHelper(treeNode.right));
    }

    /**
     * 分层遍历
     *
     * @param treeNode 树节点
     */
    public static void hierarchicalTraversal(TreeNode treeNode){

    }

    public static void printTree(TreeNode treeNode){
        int heightOfTree = getHeightOfTree(treeNode);
        ArrayList<String> trees = new ArrayList<>();
        TreeNode cur = treeNode;
        for (int i = heightOfTree - 1; i >= 0; i--) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(String.valueOf(' ').repeat(2 * i));
            stringBuffer.append(cur.val);
            trees.add(stringBuffer.toString());
        }
        for (String s: trees){
            System.out.println(s);
        }
    }

    /**
     * 树还原为数组
     *
     * @param treeNode 树节点
     * @return {@link int[]}
     */
    public static List<Integer> Tree2Array(TreeNode treeNode){
        if (treeNode == null){
            return new ArrayList<>();
        }

        List<Integer> resList = new ArrayList<>();
        helper(treeNode, resList);
        System.out.println(resList);
        return resList;
    }

    private static void helper(TreeNode treeNode, List<Integer> resList) {
        if (treeNode != null){
            resList.add(treeNode.val);
            helper(treeNode.left, resList);
            helper(treeNode.right, resList);
        }
    }


    public static void main(String[] args) {
        TreeNode treeNode = TreeUtils.ArrayBuildTreeNode(new int[]{1, 2, 3, 4, 5,6,7,8});
        System.out.println(getHeightOfTree(treeNode));
        Tree2Array(treeNode);
    }

}
