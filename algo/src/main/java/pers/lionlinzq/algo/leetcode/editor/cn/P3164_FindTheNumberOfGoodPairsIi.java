/**
 * 给你两个整数数组 nums1 和 nums2，长度分别为 n 和 m。同时给你一个正整数 k。
 * <p>
 * 如果 nums1[i] 可以被 nums2[j] * k 整除，则称数对 (i, j) 为 优质数对（0 <= i <= n - 1, 0 <= j <=
 * m - 1）。
 * <p>
 * 返回 优质数对 的总数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums1 = [1,3,4], nums2 = [1,3,4], k = 1
 * <p>
 * <p>
 * 输出：5
 * <p>
 * 解释：
 * <p>
 * 5个优质数对分别是 (0, 0), (1, 0), (1, 1), (2, 0), 和 (2, 2)。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums1 = [1,2,4,12], nums2 = [2,4], k = 3
 * <p>
 * <p>
 * 输出：2
 * <p>
 * 解释：
 * <p>
 * 2个优质数对分别是 (3, 0) 和 (3, 1)。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= n, m <= 10⁵
 * 1 <= nums1[i], nums2[j] <= 10⁶
 * 1 <= k <= 10³
 * <p>
 * <p>
 * Related Topics 数组 哈希表 👍 17 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * P3164_优质数对的总数 II
 *
 * @author Lin
 * @date 2024-10-10 11:00:16
 */
public class P3164_FindTheNumberOfGoodPairsIi {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P3164_FindTheNumberOfGoodPairsIi().new Solution();

    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         *  a / (b * k) = a / k / b
         */
        public long numberOfPairs(int[] nums1, int[] nums2, int k) {
            Map<Integer, Integer> cnt = new HashMap<>(nums1.length);

            for (int x : nums1) {
                if (x % k != 0) {
                    continue;
                }
                x = x / k;
                // 取因子,因子最大就是平方根
                for (int i = 1; i * i <= x; i++) {
                    if (x % i != 0) {
                        continue;
                    }
                    cnt.merge(i, 1, Integer::sum);
                    if (i * i < x) { // 16可以是4*4,需要避免重复取多一个4这种情况
                        cnt.merge(x / i, 1, Integer::sum);
                    }
                }
            }

            long ans = 0;
            for (int b : nums2) {
                ans += cnt.getOrDefault(b, 0);
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
