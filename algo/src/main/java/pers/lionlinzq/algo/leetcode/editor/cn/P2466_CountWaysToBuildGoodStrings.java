/**
 * 给你整数 zero ，one ，low 和 high ，我们从空字符串开始构造一个字符串，每一步执行下面操作中的一种：
 * <p>
 * <p>
 * 将 '0' 在字符串末尾添加 zero 次。
 * 将 '1' 在字符串末尾添加 one 次。
 * <p>
 * <p>
 * 以上操作可以执行任意次。
 * <p>
 * 如果通过以上过程得到一个 长度 在 low 和 high 之间（包含上下边界）的字符串，那么这个字符串我们称为 好 字符串。
 * <p>
 * 请你返回满足以上要求的 不同 好字符串数目。由于答案可能很大，请将结果对 10⁹ + 7 取余 后返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：low = 3, high = 3, zero = 1, one = 1
 * 输出：8
 * 解释：
 * 一个可能的好字符串是 "011" 。
 * 可以这样构造得到："" -> "0" -> "01" -> "011" 。
 * 从 "000" 到 "111" 之间所有的二进制字符串都是好字符串。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：low = 2, high = 3, zero = 1, one = 2
 * 输出：5
 * 解释：好字符串为 "00" ，"11" ，"000" ，"110" 和 "011" 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= low <= high <= 10⁵
 * 1 <= zero, one <= low
 * <p>
 * <p>
 * Related Topics 动态规划 👍 88 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P2466_统计构造好字符串的方案数
 * @author Lin
 * @date 2024-10-09 16:36:22
 */
public class P2466_CountWaysToBuildGoodStrings {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2466_CountWaysToBuildGoodStrings().new Solution();
        System.out.println(solution.countGoodStrings(3, 3, 1, 1));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * (a+b) mod m=((a mod m)+(b mod m))mod m
         * (a⋅b) mod m=((a mod m)⋅(b mod m))mod m
         */
        public int countGoodStrings(int low, int high, int zero, int one) {
            final int MOD = 1_000_000_007;
            int[] dp = new int[high + 1];
            int ans = 0;
            dp[0] = 1; // 取空字符串，即什么都不做，也是一种方案
            for (int i = 1; i <= high; i++) {
                if (i >= zero && i >= one) {
                    dp[i] = (dp[i - zero] + dp[i - one]) % MOD;
                } else if (i >= zero) {
                    dp[i] = dp[i - zero];
                } else if (i >= one) {
                    dp[i] = dp[i - one];
                }
                if (i >= low) {
                    ans = (ans + dp[i]) % MOD;
                }
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
