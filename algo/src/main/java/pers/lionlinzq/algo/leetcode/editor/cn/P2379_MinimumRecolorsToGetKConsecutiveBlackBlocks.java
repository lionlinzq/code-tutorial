/**
 * 给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。字符 'W' 和
 * 'B' 分别表示白色和黑色。
 * <p>
 * 给你一个整数 k ，表示想要 连续 黑色块的数目。
 * <p>
 * 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。
 * <p>
 * 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：blocks = "WBBWWBBWBW", k = 7
 * 输出：3
 * 解释：
 * 一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。
 * 得到 blocks = "BBBBBBBWBW" 。
 * 可以证明无法用少于 3 次操作得到 7 个连续的黑块。
 * 所以我们返回 3 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：blocks = "WBWBBBW", k = 2
 * 输出：0
 * 解释：
 * 不需要任何操作，因为已经有 2 个连续的黑块。
 * 所以我们返回 0 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == blocks.length
 * 1 <= n <= 100
 * blocks[i] 要么是 'W' ，要么是 'B' 。
 * 1 <= k <= n
 * <p>
 * <p>
 * Related Topics 字符串 滑动窗口 👍 135 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P2379_得到 K 个黑块的最少涂色次数
 *
 * @author Lin
 * @date 2024-09-13 15:55:31
 */
public class P2379_MinimumRecolorsToGetKConsecutiveBlackBlocks {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2379_MinimumRecolorsToGetKConsecutiveBlackBlocks().new Solution();
        System.out.println(solution.minimumRecolors("BWBBWWBBBWBWWWBWWBBWBWBBWBB", 11));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumRecolors(String blocks, int k) {
            int curMax = 0;
            for (int i = 0; i < k; i++) {
                if (blocks.charAt(i) == 'B') {
                    curMax++;
                }
            }
            int curSum = curMax;
            for (int i = k; i < blocks.length(); i++) {
                curSum = curSum - (blocks.charAt(i - k) == 'B' ? 1 : 0) + (blocks.charAt(i) == 'B' ? 1 : 0);
                curMax = Math.max(curMax, curSum);
            }
            return Math.max(k - curMax, 0);
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
