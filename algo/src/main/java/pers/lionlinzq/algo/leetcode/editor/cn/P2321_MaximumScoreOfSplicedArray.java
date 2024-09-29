/**
 * 给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度都是 n 。
 * <p>
 * 你可以选择两个整数 left 和 right ，其中 0 <= left <= right < n ，接着 交换 两个子数组 nums1[left...
 * right] 和 nums2[left...right] 。
 * <p>
 * <p>
 * 例如，设 nums1 = [1,2,3,4,5] 和 nums2 = [11,12,13,14,15] ，整数选择 left = 1 和 right = 2，
 * 那么 nums1 会变为 [1,12,13,4,5] 而 nums2 会变为 [11,2,3,14,15] 。
 * <p>
 * <p>
 * 你可以选择执行上述操作 一次 或不执行任何操作。
 * <p>
 * 数组的 分数 取 sum(nums1) 和 sum(nums2) 中的最大值，其中 sum(arr) 是数组 arr 中所有元素之和。
 * <p>
 * 返回 可能的最大分数 。
 * <p>
 * 子数组 是数组中连续的一个元素序列。arr[left...right] 表示子数组包含 nums 中下标 left 和 right 之间的元素（含 下标
 * left 和 right 对应元素）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums1 = [60,60,60], nums2 = [10,90,10]
 * 输出：210
 * 解释：选择 left = 1 和 right = 1 ，得到 nums1 = [60,90,60] 和 nums2 = [10,60,10] 。
 * 分数为 max(sum(nums1), sum(nums2)) = max(210, 80) = 210 。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums1 = [20,40,20,70,30], nums2 = [50,20,50,40,20]
 * 输出：220
 * 解释：选择 left = 3 和 right = 4 ，得到 nums1 = [20,40,20,40,20] 和 nums2 = [50,20,50,70,3
 * 0] 。
 * 分数为 max(sum(nums1), sum(nums2)) = max(140, 220) = 220 。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums1 = [7,11,13], nums2 = [1,1,1]
 * 输出：31
 * 解释：选择不交换任何子数组。
 * 分数为 max(sum(nums1), sum(nums2)) = max(31, 3) = 31 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == nums1.length == nums2.length
 * 1 <= n <= 10⁵
 * 1 <= nums1[i], nums2[i] <= 10⁴
 * <p>
 * <p>
 * Related Topics 数组 动态规划 👍 60 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P2321_拼接数组的最大分数
 *
 * @author Lin
 * @date 2024-09-25 10:30:26
 */
public class P2321_MaximumScoreOfSplicedArray {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2321_MaximumScoreOfSplicedArray().new Solution();
        int[] array1 = new int[]{7, 11, 13};
        int[] array2 = new int[]{1, 1, 1};
        System.out.println(solution.maximumsSplicedArray(array1, array2));
    }

    /**
     * 思路：
     * 1.计算出两个数组的差分数组（假设使用num1[i]-num2[i]得出）
     * 2.sum1加上最小的连续子数组和，sum2加上最大的连续子数组和
     * 3.比较步骤2产生的两个数
     */

// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int maximumsSplicedArray(int[] nums1, int[] nums2) {
            return Math.max(solve(nums1,nums2),solve(nums2,nums1));
        }

        int solve(int[] nums1, int[] nums2) {
            int maxSum = 0;
            int sum = 0;
            int diffSum = 0;
            for (int i = 0; i < nums1.length; i++) {
                int diff = nums2[i] - nums1[i];
                // 加起来还是大于0都不可以放弃这个增加值
                diffSum = Math.max(0, diffSum + diff);

                // 一直遇到的最大的累计增加值
                maxSum = Math.max(maxSum,diffSum);
                sum += nums1[i];
            }
            return sum + maxSum;
        }

        public int maximumsSplicedArray2(int[] nums1, int[] nums2) {
            int len = nums1.length;
            int sum1 = 0, sum2 = 0;
            int[] diff = new int[len];
            for (int i = 0; i < len; i++) {
                diff[i] = nums1[i] - nums2[i];
                sum1 += nums1[i];
                sum2 += nums2[i];
            }
            int f1 = 0, f2 = 0;
            int mx = Integer.MIN_VALUE, mn = Integer.MAX_VALUE;
            for (int i = 0; i < diff.length; i++) {
                f1 = Math.max(f1, 0) + diff[i];
                mx = Math.max(f1, mx);
                f2 = Math.min(f2, 0) + diff[i];
                mn = Math.min(f2, mn);
            }
            int res1 = sum1 - f2, res2 = sum2 + mx;
            return Math.max(res1, res2);
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
