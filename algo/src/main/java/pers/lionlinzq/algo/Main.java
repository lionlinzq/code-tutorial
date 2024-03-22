package pers.lionlinzq.algo;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array = generateARandomArray(10);

    }

    public void swap(int[] array,int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] generateARandomArray(int length){
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++){
            array[i] = random.nextInt(length * 10);
        }
        System.out.println(Arrays.toString(array));
        return array;
    }
}
