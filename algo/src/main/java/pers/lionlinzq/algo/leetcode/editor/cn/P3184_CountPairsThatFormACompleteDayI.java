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
// 1 <= hous.length <= 100
// 1 <= hours[i] <= 10⁹ 
// 
//
// Related Topics 数组 哈希表 计数 👍 24 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 构成整天的下标对数目 I
 *
 * @author Lin
 * @date 2024-10-22 17:03:30
 */
public class P3184_CountPairsThatFormACompleteDayI {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P3184_CountPairsThatFormACompleteDayI().new Solution();
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countCompleteDayPairs(int[] hours) {
            Arrays.sort(hours);
            int ans = 0;
            int[] count = new int[25];
            int n = hours.length;
            for (int i = 0; i < n; i++) {
                int temp = hours[i] % 24; // 余数，可以和别人一起贡献加起来的
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}
