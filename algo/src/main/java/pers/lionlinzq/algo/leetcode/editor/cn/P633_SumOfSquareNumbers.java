// 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a² + b² = c 。
//
// 
//
// 示例 1： 
//
// 
// 输入：c = 5
// 输出：true
// 解释：1 * 1 + 2 * 2 = 5
// 
//
// 示例 2： 
//
// 
// 输入：c = 3
// 输出：false
// 
//
// 
//
// 提示： 
//
// 
// 0 <= c <= 2³¹ - 1 
// 
//
// Related Topics 数学 双指针 二分查找 👍 474 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 平方数之和
 *
 * @author Lin
 * @date 2024-11-04 09:39:28
 */
public class P633_SumOfSquareNumbers {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P633_SumOfSquareNumbers().new Solution();
        solution.judgeSquareSum(2147482647);
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean judgeSquareSum(int c) {
            long left = 0,right = (long) Math.sqrt(c);
            while (left <= right){
                long sum = left * left + right * right;
                if (sum == c){
                    return true;
                }else if (sum < c){
                    left++;
                }else {
                    right--;
                }
            }
            return false;
        }

        public boolean judgeSquareSum2(int c) {
            HashSet<Long> set = new HashSet<>();
            for (long i = 0;  i * i <= c; i++) {
                set.add(i * i);
                if (set.contains(c - i * i)) {
                    return true;
                }

            }
            return false;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}
