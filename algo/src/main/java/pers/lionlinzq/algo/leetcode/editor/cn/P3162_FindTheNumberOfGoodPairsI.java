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
 * 1 <= n, m <= 50
 * 1 <= nums1[i], nums2[j] <= 50
 * 1 <= k <= 50
 * <p>
 * <p>
 * Related Topics 数组 哈希表 👍 11 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P3162_优质数对的总数 I
 * @author Lin
 * @date 2024-10-10 10:45:26
 */
public class P3162_FindTheNumberOfGoodPairsI {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P3162_FindTheNumberOfGoodPairsI().new Solution();

    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
		// a % b == 0, 记作 b整除a ,或者是 a能够被b整除
        public int numberOfPairs(int[] nums1, int[] nums2, int k) {
            int ans = 0;
            for (int i = 0; i < nums1.length; i++) {
                for (int j = 0; j < nums2.length; j++) {
                    if (nums1[i] % (nums2[j] * k) == 0) {
                        ans++;
                    }
                }
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
