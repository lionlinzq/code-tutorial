/**
 * 给你一个整数数组 nums 和一个整数 k 。请你从 nums 中满足下述条件的全部子数组中找出最大子数组和：
 * <p>
 * <p>
 * 子数组的长度是 k，且
 * 子数组中的所有元素 各不相同 。
 * <p>
 * <p>
 * 返回满足题面要求的最大子数组和。如果不存在子数组满足这些条件，返回 0 。
 * <p>
 * 子数组 是数组中一段连续非空的元素序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,4,2,9,9,9], k = 3
 * 输出：15
 * 解释：nums 中长度为 3 的子数组是：
 * - [1,5,4] 满足全部条件，和为 10 。
 * - [5,4,2] 满足全部条件，和为 11 。
 * - [4,2,9] 满足全部条件，和为 15 。
 * - [2,9,9] 不满足全部条件，因为元素 9 出现重复。
 * - [9,9,9] 不满足全部条件，因为元素 9 出现重复。
 * 因为 15 是满足全部条件的所有子数组中的最大子数组和，所以返回 15 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [4,4,4], k = 3
 * 输出：0
 * 解释：nums 中长度为 3 的子数组是：
 * - [4,4,4] 不满足全部条件，因为元素 4 出现重复。
 * 因为不存在满足全部条件的子数组，所以返回 0 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= k <= nums.length <= 10⁵
 * 1 <= nums[i] <= 10⁵
 * <p>
 * <p>
 * Related Topics 数组 哈希表 滑动窗口 👍 54 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;

/**
 * P2461_长度为 K 子数组中的最大和
 *
 * @author Lin
 * @date 2024-09-14 14:30:33
 */
public class P2461_MaximumSumOfDistinctSubarraysWithLengthK {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2461_MaximumSumOfDistinctSubarraysWithLengthK().new Solution();
        System.out.println(solution.maximumSubarraySum(new int[]{1, 5, 4, 2, 9, 9, 9}, 3));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long maximumSubarraySum(int[] array, int k) {
            long ans = 0, curSum = 0;
            int count = 0;
            int startIndex = 0;
            HashMap<Integer, Integer> map = new HashMap<>(k);
            for (int i = 0; i < array.length; i++) {
                if (map.containsKey(array[i])) {
                    int lastIndex = map.get(array[i]);
                    while (startIndex <= lastIndex) {
                        map.remove(array[startIndex]);
                        curSum -= array[startIndex];
                        count--;
                        startIndex++;
                    }
                    count++;
                    curSum += array[i];
                    map.put(array[i], i);
                } else {
                    count++;
                    curSum += array[i];
                    map.put(array[i], i);
                    if (count == k) {
                        ans = Math.max(ans, curSum);
                        map.remove(array[i - k + 1]);
                        startIndex = i - k + 2;
                        count--;
                        curSum -= array[i - k + 1];
                    }
                }

            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
