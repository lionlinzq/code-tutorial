/**
 * 给你一个正整数数组 nums 。
 * <p>
 * <p>
 * 元素和 是 nums 中的所有元素相加求和。
 * 数字和 是 nums 中每一个元素的每一数位（重复数位需多次求和）相加求和。
 * <p>
 * <p>
 * 返回 元素和 与 数字和 的绝对差。
 * <p>
 * 注意：两个整数 x 和 y 的绝对差定义为 |x - y| 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [1,15,6,3]
 * 输出：9
 * 解释：
 * nums 的元素和是 1 + 15 + 6 + 3 = 25 。
 * nums 的数字和是 1 + 1 + 5 + 6 + 3 = 16 。
 * 元素和与数字和的绝对差是 |25 - 16| = 9 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 解释：
 * nums 的元素和是 1 + 2 + 3 + 4 = 10 。
 * nums 的数字和是 1 + 2 + 3 + 4 = 10 。
 * 元素和与数字和的绝对差是 |10 - 10| = 0 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 2000
 * 1 <= nums[i] <= 2000
 * <p>
 * <p>
 * Related Topics 数组 数学 👍 25 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P2535_数组元素和与数字和的绝对差
 * @author Lin
 * @date 2024-09-26 10:40:31
 */
public class P2535_DifferenceBetweenElementSumAndDigitSumOfAnArray {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2535_DifferenceBetweenElementSumAndDigitSumOfAnArray().new Solution();

    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int differenceOfSum(int[] nums) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                while (nums[i] != 0) {
                    int temp = nums[i] % 10;
                    sum -= temp;
                    nums[i] = nums[i] / 10;
                }
            }
			return sum;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
