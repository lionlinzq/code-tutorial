/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组 是数组中的一个连续部分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * <p>
 * Related Topics 数组 分治 动态规划 👍 6793 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P53_最大子数组和
 *
 * @author Lin
 * @date 2024-09-27 09:18:51
 */
public class P53_MaximumSubarray {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P53_MaximumSubarray().new Solution();

    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
            int pre = 0;
            int maxAns = nums[0];
            for (int x : nums) {
                pre = Math.max(x, pre + x);  // 加上这个数字变成了负数
                maxAns = Math.max(maxAns, pre);
            }
            return maxAns;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
