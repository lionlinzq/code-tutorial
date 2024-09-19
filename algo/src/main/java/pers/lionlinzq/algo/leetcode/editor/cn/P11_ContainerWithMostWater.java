/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == height.length
 * 2 <= n <= 10⁵
 * 0 <= height[i] <= 10⁴
 * <p>
 * <p>
 * Related Topics 贪心 数组 双指针 👍 5088 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import javax.sound.midi.Soundbank;

/**
 * 盛最多水的容器
 *
 * @author Lin
 * @date 2024-09-04 15:20:52
 */
public class P11_ContainerWithMostWater {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P11_ContainerWithMostWater().new Solution();
        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            int left = 0, right = height.length - 1;
            int ans = 0;
            while (left < right) {
                int min = Math.min(height[left], height[right]);
                ans = Math.max(ans, (right - left) * min);
                if (min == height[left]) {
                    left++;
                } else {
                    right--;
                }
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
