package sort;

public class HeapSort implements Sort {

    @Override
    public int[] Sort(int[] array) {
        int length = array.length;
        // 1. 构建大顶堆
        for (int i = length / 2 - 1; i >= 0; i--) {
            adjustHeap(array, length, i);
        }

        // 2. 交换堆顶元素与末尾元素，并调整堆
        for (int j = length - 1; j > 0; j--) {
            swap(array, 0, j); // 将堆顶元素与末尾元素进行交换
            adjustHeap(array, j, 0); // 重新对堆进行调整，注意堆的长度是 j
        }
        return array;
    }

    /**
     * 调整堆
     *
     * @param array  阵列
     * @param length 堆的长度
     * @param i      当前节点的索引
     */
    public void adjustHeap(int[] array, int length, int i) {
        int temp = array[i]; // 先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) { // 从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && array[k] < array[k + 1]) { // 如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (array[k] > temp) { // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp; // 将temp值放到最终的位置
    }
}
