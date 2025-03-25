package pers.lionlinzq.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] array = new int[][]{{2, 4}, {0, 2}, {0, 4}};
        HashMap<Integer,Integer> map = new HashMap<>();
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] generateARandomArray(int length) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(length * 10);
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    public String convertPassword(String password) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= 'B' && c <= 'Z') {
                c = (char) (c - 1);
                c = Character.toLowerCase(c);
            } else if (c == 'A') {
                c = 'z';
            }
            if (c >= 'a' && c <= 'c') {
                stringBuilder.append(2);
            } else if (c >= 'd' && c <= 'f') {
                stringBuilder.append(3);
            } else if (c >= 'g' && c <= 'i') {
                stringBuilder.append(4);
            } else if (c >= 'j' && c <= 'l') {
                stringBuilder.append(5);
            } else if (c >= 'm' && c <= 'o') {
                stringBuilder.append(6);
            } else if (c >= 'p' && c <= 's') {
                stringBuilder.append(7);
            } else if (c >= 't' && c <= 'v') {
                stringBuilder.append(8);
            } else if (c >= 'w' && c <= 'z') {
                stringBuilder.append(9);
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}
