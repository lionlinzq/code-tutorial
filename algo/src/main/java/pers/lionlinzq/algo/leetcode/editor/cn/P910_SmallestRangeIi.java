// 给你一个整数数组 nums，和一个整数 k 。
//
// 对于每个下标 i（0 <= i < nums.length），将 nums[i] 变成 nums[i] + k 或 nums[i] - k 。 
//
// nums 的 分数 是 nums 中最大元素和最小元素的差值。 
//
// 在更改每个下标对应的值之后，返回 nums 的最小 分数 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
// 输入：nums = [1], k = 0
// 输出：0
// 解释：分数 = max(nums) - min(nums) = 1 - 1 = 0 。
// 
//
// 示例 2： 
//
// 
// 输入：nums = [0,10], k = 2
// 输出：6
// 解释：将数组变为 [2, 8] 。分数 = max(nums) - min(nums) = 8 - 2 = 6 。
// 
//
// 示例 3： 
//
// 
// 输入：nums = [1,3,6], k = 3
// 输出：3
// 解释：将数组变为 [4, 6, 3] 。分数 = max(nums) - min(nums) = 6 - 3 = 3 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// 0 <= nums[i] <= 10⁴ 
// 0 <= k <= 10⁴ 
// 
//
// Related Topics 贪心 数组 数学 排序 👍 211 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 最小差值 II
 *
 * @author Lin
 * @date 2024-10-21 09:54:16
 */
public class P910_SmallestRangeIi {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P910_SmallestRangeIi().new Solution();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(solution.smallestRangeII(nums, 2));
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int smallestRangeII(int[] nums, int k) {
            // 排序后,假设前i个数据+k,后面的数据-k
            // 最大值要么是nums[i-1]+k,要么是nums[n-1]-k
            // 最小值要么是nums[0]+k,要么是nums[i]-k
            Arrays.sort(nums);
            int n = nums.length;
            int ans = nums[n - 1] - nums[0];
            for (int i = 1; i < n; i++) {
                int mn = Math.min(nums[0] + k, nums[i] - k);
                int mx = Math.max(nums[i - 1] + k, nums[n - 1] - k);
                ans = Math.min(ans,mx - mn);
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}
