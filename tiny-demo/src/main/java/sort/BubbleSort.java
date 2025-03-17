package sort;

public class BubbleSort implements Sort {

    @Override
    public int[] Sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[i]) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }
}
