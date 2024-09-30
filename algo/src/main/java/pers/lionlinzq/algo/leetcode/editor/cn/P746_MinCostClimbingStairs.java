/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * <p>
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * <p>
 * 请你计算并返回达到楼梯顶部的最低花费。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 * <p>
 * <p>
 * Related Topics 数组 动态规划 👍 1534 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P746_使用最小花费爬楼梯
 *
 * @author Lin
 * @date 2024-09-29 17:27:28
 */
public class P746_MinCostClimbingStairs {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P746_MinCostClimbingStairs().new Solution();

    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * f(n) = min: f(n - 1) + [n-1],f(n - 2) + [n-2]
         *
         * @param cost
         * @return int
         */
        public int minCostClimbingStairs(int[] cost) {
            int cur = 0,prev = 0;
            // 楼梯到最后要跳出去才算到楼顶
            for (int i = 2; i <= cost.length; i++) {
                int next = Math.min(cur + cost[i - 1], prev + cost[i - 2]);
                prev = cur;
                cur = next;
            }
            return cur;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
