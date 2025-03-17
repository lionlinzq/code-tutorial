package sort;

/**
 * 快速排序
 * 1.选定一个基准值
 * 2.
 *
 * @author lin
 */
public class QuickSort implements Sort {
    @Override
    public int[] Sort(int[] array) {
        Sort(array, 0, array.length - 1);
        return array;
    }

    public void Sort(int[] array,int left, int right) {
        if (left >= right){
            return;
        }
        int pivot = partition(array,left, right);
        Sort(array, left, pivot - 1);
        Sort(array, pivot + 1, right);
    }

    /**
     * 分区
     *
     * @param arrays 阵列
     * @param left   左
     * @param right  右侧
     * @return int
     */
    public int partition(int[] arrays, int left, int right) {
        int base = arrays[left];
        int i = left, j = right;
        while (i < j) {
            while (i < j && arrays[j] >= base) {
                j--;
            }
            while (i < j && arrays[i] <= base) {
                i++;
            }
            swap(arrays, i, j);
        }
        swap(arrays, left, i);
        return i;
    }
}
