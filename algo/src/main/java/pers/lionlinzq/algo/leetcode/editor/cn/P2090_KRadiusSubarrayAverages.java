/**
 * 给你一个下标从 0 开始的数组 nums ，数组中有 n 个整数，另给你一个整数 k 。
 * <p>
 * 半径为 k 的子数组平均值 是指：nums 中一个以下标 i 为 中心 且 半径 为 k 的子数组中所有元素的平均值，即下标在 i - k 和 i + k 范
 * 围（含 i - k 和 i + k）内所有元素的平均值。如果在下标 i 前或后不足 k 个元素，那么 半径为 k 的子数组平均值 是 -1 。
 * <p>
 * 构建并返回一个长度为 n 的数组 avgs ，其中 avgs[i] 是以下标 i 为中心的子数组的 半径为 k 的子数组平均值 。
 * <p>
 * x 个元素的 平均值 是 x 个元素相加之和除以 x ，此时使用截断式 整数除法 ，即需要去掉结果的小数部分。
 * <p>
 * <p>
 * 例如，四个元素 2、3、1 和 5 的平均值是 (2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75，截断后得到 2 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * <p>
 * 输入：nums = [7,4,3,9,1,8,5,2,6], k = 3
 * 输出：[-1,-1,-1,5,4,4,-1,-1,-1]
 * 解释：
 * - avg[0]、avg[1] 和 avg[2] 是 -1 ，因为在这几个下标前的元素数量都不足 k 个。
 * - 中心为下标 3 且半径为 3 的子数组的元素总和是：7 + 4 + 3 + 9 + 1 + 8 + 5 = 37 。
 * 使用截断式 整数除法，avg[3] = 37 / 7 = 5 。
 * - 中心为下标 4 的子数组，avg[4] = (4 + 3 + 9 + 1 + 8 + 5 + 2) / 7 = 4 。
 * - 中心为下标 5 的子数组，avg[5] = (3 + 9 + 1 + 8 + 5 + 2 + 6) / 7 = 4 。
 * - avg[6]、avg[7] 和 avg[8] 是 -1 ，因为在这几个下标后的元素数量都不足 k 个。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [100000], k = 0
 * 输出：[100000]
 * 解释：
 * - 中心为下标 0 且半径 0 的子数组的元素总和是：100000 。
 * avg[0] = 100000 / 1 = 100000 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [8], k = 100000
 * 输出：[-1]
 * 解释：
 * - avg[0] 是 -1 ，因为在下标 0 前后的元素数量均不足 k 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == nums.length
 * 1 <= n <= 10⁵
 * 0 <= nums[i], k <= 10⁵
 * <p>
 * <p>
 * Related Topics 数组 滑动窗口 👍 38 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * P2090_半径为 k 的子数组平均值
 *
 * @author Lin
 * @date 2024-09-12 11:28:48
 */
public class P2090_KRadiusSubarrayAverages {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2090_KRadiusSubarrayAverages().new Solution();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.pow(10,10));
        System.out.println(Arrays.toString(solution.getAverages(new int[]{7, 4, 3, 9, 1, 8, 5, 2, 6}, 3)));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] getAverages(int[] nums, int k) {
            int[] averages = new int[nums.length];
            Arrays.fill(averages, -1);
            if (2 * k + 1 > nums.length) {
                return averages;
            }
            // 10e5 * 10e5 大于int最大
            long sum = 0;
            for (int i = 0; i < 2 * k + 1; i++) {
                sum += nums[i];
            }

            averages[k] = (int) (sum / (2 * k + 1));

            for (int n = k + 1; n < nums.length - k; n++) {
                sum = sum - nums[n - k - 1] + nums[n + k];
                averages[n] = (int) (sum / (2 * k + 1));
            }
            return averages;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
