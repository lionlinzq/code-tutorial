package sort;


/**
 * 选择排序
 * 基本思想是每次从待排序的数据中选择最小（或最大）的元素，放到已排序序列的末尾，直到全部数据排序完成。
 *
 * @author lin
 */
public class SelectionSort implements Sort {

    @Override
    public int[] Sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int k = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            swap(array, i, k);
        }
        return array;
    }
}
