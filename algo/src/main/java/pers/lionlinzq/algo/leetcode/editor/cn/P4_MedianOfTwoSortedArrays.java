/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10⁶ <= nums1[i], nums2[i] <= 10⁶
 * <p>
 * <p>
 * Related Topics 数组 二分查找 分治 👍 7206 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.time.OffsetDateTime;

/**
 * 寻找两个正序数组的中位数
 *
 * @author Lin
 * @date 2024-08-27 11:42:23
 */
public class P4_MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P4_MedianOfTwoSortedArrays().new Solution();
        System.out.println(3 / 2);
        // System.out.println(solution.binarySearch(new int[]{1, 2, 3, 4, 5, 7, 8, 10}, 8));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // 确保 nums1 是较短的数组
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int m = nums1.length;
            int n = nums2.length;
            int left = 0, right = m;
            int totalLeft = (m + n + 1) / 2;

            while (left < right) {
                int i = left + (right - left) / 2;
                int j = totalLeft - i;

                // nums1[i]太小，说明i要右移
                if (nums1[i] < nums2[j - 1]) {
                    left = i + 1;
                } else {
                    right = i;
                }
            }

            // 最终确定分割线的位置
            int i = left;
            int j = totalLeft - i;

            // 处理边界情况，取出左右两边的最大值和最小值
            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 如果总长度是奇数，返回左边最大值
            if ((m + n) % 2 == 1) {
                return Math.max(nums1LeftMax, nums2LeftMax);
            } else {
                // 如果总长度是偶数，返回中位数
                return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
            }
        }

        // 合并数组解法 O(m+n)
        public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;
            int totalLength = m + n;

            // 中位数的两个位置索引，适用于奇偶数情况
            // 奇数：5,中位数是第3个元素, 5/2=2,(5-1)/2=2均符合条件,偶数6的话就要第3，4个元素
            int medianIndex1 = (totalLength - 1) / 2;
            int medianIndex2 = totalLength / 2;

            // 双指针遍历数组
            int i = 0, j = 0;
            int median1 = 0, median2 = 0;

            // 合并数组直到达到 medianIndex2
            for (int count = 0; count <= medianIndex2; count++) {
                int value;
                if (i < m) {
                    if (j >= n || nums1[i] <= nums2[j]) {
                        value = nums1[i];
                        i++;
                    } else {
                        value = nums2[j];
                        j++;
                    }
                } else {
                    value = nums2[j];
                    j++;
                }
                // 记录中位数位置的元素
                if (count == medianIndex1) {
                    median1 = value;
                }
                if (count == medianIndex2) {
                    median2 = value;
                }
            }


            // 如果总长度是奇数，median1 和 median2 相等，只返回其中一个
            // 如果是偶数，返回两个中位数的平均值
            return totalLength % 2 == 0 ? (median1 + median2) / 2.0 : median2;
        }

        public int binarySearch(int[] array, int target) {
            int left = 0, right = array.length - 1;
            while (left <= right) {
                int middle = left + (right - left) / 2;
                if (array[middle] < target) {
                    left = middle + 1;
                } else if (array[middle] > target) {
                    right = middle - 1;
                } else {
                    return middle;
                }
            }
            return -1;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
