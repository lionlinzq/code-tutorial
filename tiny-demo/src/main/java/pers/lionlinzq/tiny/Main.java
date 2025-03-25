package pers.lionlinzq.tiny;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        int[] array1 = new int[]{1,11,9,3};
        int[] array2 = new int[]{7,5,13,15};
        int[] res = new int[8];
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(1,1);
        treeMap.put(3,3);
        treeMap.put(2,2);
        System.out.println(treeMap);
    }
}
