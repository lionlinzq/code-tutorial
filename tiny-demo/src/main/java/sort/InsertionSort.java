package sort;

public class InsertionSort implements Sort {
    @Override
    public int[] Sort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int base = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > base) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = base;
        }
        return array;
    }
}
