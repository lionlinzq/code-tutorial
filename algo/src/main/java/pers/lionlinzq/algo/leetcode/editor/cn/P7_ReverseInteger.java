/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2³¹, 231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：x = 123
 * 输出：321
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：x = -123
 * 输出：-321
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：x = 120
 * 输出：21
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * <p>
 * 输入：x = 0
 * 输出：0
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * -2³¹ <= x <= 2³¹ - 1
 * <p>
 * <p>
 * Related Topics 数学 👍 4016 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * 整数反转
 * @author Lin
 * @date 2024-09-03 17:44:13
 */
public class P7_ReverseInteger {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P7_ReverseInteger().new Solution();
        System.out.println(solution.reverse(-2147483412));
        System.out.println(2147483412);
        System.out.println("2143847412");
        // System.out.println(Math.pow(2,31));
        // System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse(int x) {
            int ans = 0;
            while (x != 0) {
                if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                int remainder = x % 10;
                ans = ans * 10 + remainder;
                x = x / 10;
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
