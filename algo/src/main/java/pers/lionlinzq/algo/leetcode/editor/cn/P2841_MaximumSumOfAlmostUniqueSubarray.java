package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 给你一个整数数组 nums 和两个正整数 m 和 k 。
 * <p>
 * 请你返回 nums 中长度为 k 的 几乎唯一 子数组的 最大和 ，如果不存在几乎唯一子数组，请你返回 0 。
 * <p>
 * 如果 nums 的一个子数组有至少 m 个互不相同的元素，我们称它是 几乎唯一 子数组。
 * <p>
 * 子数组指的是一个数组中一段连续 非空 的元素序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [2,6,7,3,1,7], m = 3, k = 4
 * 输出：18
 * 解释：总共有 3 个长度为 k = 4 的几乎唯一子数组。分别为 [2, 6, 7, 3] ，[6, 7, 3, 1] 和 [7, 3, 1, 7] 。这些子数
 * 组中，和最大的是 [2, 6, 7, 3] ，和为 18 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [5,9,9,2,4,5,4], m = 1, k = 3
 * 输出：23
 * 解释：总共有 5 个长度为 k = 3 的几乎唯一子数组。分别为 [5, 9, 9] ，[9, 9, 2] ，[9, 2, 4] ，[2, 4, 5] 和 [4
 * , 5, 4] 。这些子数组中，和最大的是 [5, 9, 9] ，和为 23 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [1,2,1,2,1,2,1], m = 3, k = 3
 * 输出：0
 * 解释：输入数组中不存在长度为 k = 3 的子数组含有至少  m = 3 个互不相同元素的子数组。所以不存在几乎唯一子数组，最大和为 0 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 2 * 10⁴
 * 1 <= m <= k <= nums.length
 * 1 <= nums[i] <= 10⁹
 * <p>
 * <p>
 * Related Topics 数组 哈希表 滑动窗口 👍 26 👎 0
 * <p>
 * package pers.lionlinzq.algo.leetcode.editor.cn;
 * <p>
 * import java.util.*;
 * <p>
 * /**
 * P2841_几乎唯一子数组的最大和
 *
 * @author Lin
 * @date 2024-09-13 17:25:51
 */
public class P2841_MaximumSumOfAlmostUniqueSubarray {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2841_MaximumSumOfAlmostUniqueSubarray().new Solution();
        // System.out.println(solution.maxSum(Arrays.asList(1), 1, 1));
        var map = new HashMap<Integer, Integer>();
        map.put(1, 2);
        map.merge(1, 2, Integer::compare);
        System.out.println(map);
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long maxSum(List<Integer> nums, int m, int k) {
            long maxSum = 0, curSum = 0;
            int[] array = nums.stream().mapToInt(i -> i).toArray();
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < k - 1; i++) {
                curSum += array[i];
                map.merge(array[i], 1, Integer::sum);
            }

            for (int i = k - 1; i < array.length; i++) {
                curSum += array[i];
                map.merge(array[i], 1, Integer::sum);

                if (map.size() >= m) {
                    maxSum = Math.max(maxSum, curSum);
                }
                curSum -= array[i - k + 1];
                if (map.merge(array[i - k + 1], -1, Integer::sum) == 0) {
                    map.remove(array[i - k + 1]);
                }
            }
            return maxSum;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
