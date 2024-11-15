// 给你一个 m x n 的二进制矩阵 grid 。
//
// 如果矩阵中一行或者一列从前往后与从后往前读是一样的，那么我们称这一行或者这一列是 回文 的。 
//
// 你可以将 grid 中任意格子的值 翻转 ，也就是将格子里的值从 0 变成 1 ，或者从 1 变成 0 。 
//
// 请你返回 最少 翻转次数，使得矩阵 要么 所有行是 回文的 ，要么所有列是 回文的 。 
//
// 
//
// 示例 1： 
//
// 
// 输入：grid = [[1,0,0],[0,0,0],[0,0,1]] 
// 
//
// 输出：2 
//
// 解释： 
//
// 
//
// 将高亮的格子翻转，得到所有行都是回文的。 
//
// 示例 2： 
//
// 
// 输入：grid = [[0,1],[0,1],[0,0]] 
// 
//
// 输出：1 
//
// 解释： 
//
// 
//
// 将高亮的格子翻转，得到所有列都是回文的。 
//
// 示例 3： 
//
// 
// 输入：grid = [[1],[0]] 
// 
//
// 输出：0 
//
// 解释： 
//
// 所有行已经是回文的。 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m * n <= 2 * 10⁵ 
// 0 <= grid[i][j] <= 1 
// 
//
// Related Topics 数组 双指针 矩阵 👍 14 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * 最少翻转次数使二进制矩阵回文 I
 *
 * @author Lin
 * @date 2024-11-15 10:20:35
 */
public class P3239_MinimumNumberOfFlipsToMakeBinaryGridPalindromicI {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P3239_MinimumNumberOfFlipsToMakeBinaryGridPalindromicI().new Solution();
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minFlips(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            if (m == 1 || n == 1) {
                return 0;
            }
            int ans = 0;
            for (int i = 0; i < m; i++) {
                int left = 0, right = n - 1;
                while (left < right) {
                    if (grid[i][left] != grid[i][right]) {
                        ans++;
                    }
                    left++;
                    right--;
                }
            }
            int ans2 = 0;
            for (int j = 0; j < n; j++) {
                int top = 0, bottom = m - 1;
                while (top < bottom) {
                    if (grid[top][j] != grid[bottom][j]) {
                        ans2++;
                    }
                    top++;
                    bottom--;
                }
            }
            return Math.min(ans, ans2);
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}
