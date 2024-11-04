//给你一个二进制数组 nums 。 
//
// 你可以对数组执行以下操作 任意 次（也可以 0 次）： 
//
// 
// 选择数组中 任意连续 3 个元素，并将它们 全部反转 。 
// 
//
// 反转 一个元素指的是将它的值从 0 变 1 ，或者从 1 变 0 。 
//
// 请你返回将 nums 中所有元素变为 1 的 最少 操作次数。如果无法全部变成 1 ，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
// 输入：nums = [0,1,1,1,0,0] 
// 
//
// 输出：3 
//
// 解释： 我们可以执行以下操作： 
//
// 
// 选择下标为 0 ，1 和 2 的元素并反转，得到 nums = [1,0,0,1,0,0] 。 
// 选择下标为 1 ，2 和 3 的元素并反转，得到 nums = [1,1,1,0,0,0] 。 
// 选择下标为 3 ，4 和 5 的元素并反转，得到 nums = [1,1,1,1,1,1] 。 
// 
//
// 示例 2： 
//
// 
// 输入：nums = [0,1,1,1] 
// 
//
// 输出：-1 
//
// 解释： 无法将所有元素都变为 1 。 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10⁵ 
// 0 <= nums[i] <= 1 
// 
//
// Related Topics 位运算 队列 数组 前缀和 滑动窗口 👍 35 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * 使二进制数组全部等于 1 的最少操作次数 I
 * @author Lin
 * @date 2024-10-18 18:02:20
 */
public class P3191_MinimumOperationsToMakeBinaryArrayElementsEqualToOneI{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new P3191_MinimumOperationsToMakeBinaryArrayElementsEqualToOneI().new Solution();
	 }
	 
    //力扣代码
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int minOperations(int[] nums) {
            int ans = 0;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    if (i > n - 3) {
                        return -1;
                    }
                    nums[i] ^= 1;
                    nums[i + 1] ^= 1;
                    nums[i + 2] ^= 1;
                    ans++;
                }
            }
            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}