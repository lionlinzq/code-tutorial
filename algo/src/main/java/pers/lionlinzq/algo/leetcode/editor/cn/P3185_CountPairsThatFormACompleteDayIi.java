// 给你一个整数数组 hours，表示以 小时 为单位的时间，返回一个整数，表示满足 i < j 且 hours[i] + hours[j] 构成 整天 的下标
// 对 i, j 的数目。
//
// 整天 定义为时间持续时间是 24 小时的 整数倍 。 
//
// 例如，1 天是 24 小时，2 天是 48 小时，3 天是 72 小时，以此类推。 
//
// 
//
// 示例 1： 
//
// 
// 输入： hours = [12,12,30,24,24] 
// 
//
// 输出： 2 
//
// 解释： 
//
// 构成整天的下标对分别是 (0, 1) 和 (3, 4)。 
//
// 示例 2： 
//
// 
// 输入： hours = [72,48,24,3] 
// 
//
// 输出： 3 
//
// 解释： 
//
// 构成整天的下标对分别是 (0, 1)、(0, 2) 和 (1, 2)。 
//
// 
//
// 提示： 
//
// 
// 1 <= hours.length <= 5 * 10⁵ 
// 1 <= hours[i] <= 10⁹ 
// 
//
// Related Topics 数组 哈希表 计数 👍 22 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.Map;

/**
 * 构成整天的下标对数目 II
 *
 * @author Lin
 * @date 2024-10-23 11:11:33
 */
public class P3185_CountPairsThatFormACompleteDayIi {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P3185_CountPairsThatFormACompleteDayIi().new Solution();
        int[] hours = {72,48,24,3};
        System.out.println(solution.countCompleteDayPairs(hours));
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long countCompleteDayPairs(int[] hours) {
            long ans = 0;
            int[] count = new int[25];
            for (int hour : hours) {
                int remainder = hour % 24;
                // 余数为0
                if (remainder == 0) {
                    if (count[24] != 0) {
                        ans += count[24];
                    }
                    count[24]++;
                } else {
                    if (count[24 - remainder] != 0) {
                        ans += count[24 - remainder];
                    }
                }
                count[remainder]++;
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}
