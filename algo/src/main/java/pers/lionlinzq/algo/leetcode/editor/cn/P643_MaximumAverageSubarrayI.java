/**
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * <p>
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 任何误差小于 10⁻⁵ 的答案都将被视为正确答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == nums.length
 * 1 <= k <= n <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * <p>
 * <p>
 * Related Topics 数组 滑动窗口 👍 353 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P643_子数组最大平均数 I
 *
 * @author Lin
 * @date 2024-09-12 10:43:58
 */
public class P643_MaximumAverageSubarrayI {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P643_MaximumAverageSubarrayI().new Solution();
        System.out.println((double) -1 / 1);
        System.out.println(solution.findMaxAverage(new int[]{-1}, 1));
        System.out.println(Double.MIN_VALUE < 0.0);
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 滑动窗口
        public double findMaxAverage(int[] nums, int k) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }
            int maxSum = sum;
            for (int n = k; n < nums.length; n++) {
                sum = sum - nums[n - k] + nums[n];
                maxSum = Math.max(maxSum, sum);
            }
            return 1.0 * maxSum / k;
        }


        // 前缀和
        public double findMaxAverage2(int[] nums, int k) {
            int[] count = new int[nums.length + 1];
            for (int i = 1; i <= nums.length; i++) {
                count[i] = count[i - 1] + nums[i - 1];
            }
            double ans = -Double.MAX_VALUE;
            for (int i = k; i < count.length; i++) {
                ans = Math.max(ans, (double) (count[i] - count[i - k]) / k);
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
