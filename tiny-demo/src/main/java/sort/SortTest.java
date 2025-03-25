package sort;

import java.util.Arrays;

public class SortTest {
    public static void main(String[] args) {
        int[] array = {1, 32, 12, 43, 123, 12, 4, 7, 82, 122, 312, 5, 7, 23, 2, 88};
        int[] array2 = {4,1,3,1,5,2};
        int[] array3 = {2,4,1,0,4,5};
        Sort sort = new BubbleSort();
        SelectionSort selectionSort = new SelectionSort();
        InsertionSort insertionSort = new InsertionSort();
        QuickSort quickSort = new QuickSort();
        HeapSort heapSort = new HeapSort();
        // System.out.println(Arrays.toString(insertionSort.Sort(array2)));
        System.out.println(Arrays.toString(quickSort.Sort(array)));
        System.out.println(Arrays.toString(heapSort.Sort(array)));

    }
}
