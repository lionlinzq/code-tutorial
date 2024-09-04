/**
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * <p>
 * 空格：读入字符串并丢弃无用的前导空格（" "）
 * 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
 * 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
 * 舍入：如果整数数超过 32 位有符号整数范围 [−2³¹, 231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2³¹ 的整数应该被
 * 舍入为 −2³¹ ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
 * <p>
 * <p>
 * 返回整数作为最终结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：s = "42"
 * <p>
 * <p>
 * 输出：42
 * <p>
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * <p>
 * <p>
 * 带下划线线的字符是所读的内容，插入符号是当前读入位置。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："42"（读入 "42"）
 * ^
 * <p>
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：s = " -042"
 * <p>
 * <p>
 * 输出：-42
 * <p>
 * 解释：
 * <p>
 * <p>
 * 第 1 步："   -042"（读入前导空格，但忽视掉）
 * ^
 * 第 2 步："   -042"（读入 '-' 字符，所以结果应该是负数）
 * ^
 * 第 3 步："   -042"（读入 "042"，在结果中忽略前导零）
 * ^
 * <p>
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：s = "1337c0d3"
 * <p>
 * <p>
 * 输出：1337
 * <p>
 * 解释：
 * <p>
 * <p>
 * 第 1 步："1337c0d3"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："1337c0d3"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："1337c0d3"（读入 "1337"；由于下一个字符不是一个数字，所以读入停止）
 * ^
 * <p>
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * <p>
 * 输入：s = "0-1"
 * <p>
 * <p>
 * 输出：0
 * <p>
 * 解释：
 * <p>
 * <p>
 * 第 1 步："0-1" (当前没有读入字符，因为没有前导空格)
 * ^
 * 第 2 步："0-1" (当前没有读入字符，因为这里不存在 '-' 或者 '+')
 * ^
 * 第 3 步："0-1" (读入 "0"；由于下一个字符不是一个数字，所以读入停止)
 * ^
 * <p>
 * <p>
 * <p>
 * 示例 5：
 * <p>
 * <p>
 * 输入：s = "words and 987"
 * <p>
 * <p>
 * 输出：0
 * <p>
 * 解释：
 * <p>
 * 读取在第一个非数字字符“w”处停止。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 * <p>
 * <p>
 * Related Topics 字符串 👍 1846 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import javax.xml.stream.FactoryConfigurationError;

/**
 * 字符串转换整数 (atoi)
 *
 * @author Lin
 * @date 2024-09-04 10:32:57
 */
public class P8_StringToIntegerAtoi {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P8_StringToIntegerAtoi().new Solution();
        System.out.println(solution.myAtoi("2147483648"));
        System.out.println(Math.pow(2,31) - 1);
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int myAtoi(String s) {
            int ans = 0;
            boolean hasNumber = false;
            boolean negative = false;
            boolean hadSymbol = false;
            for (int i = 0; i < s.length(); i++) {
                char character = s.charAt(i);
                if (character == ' ' && !hasNumber && !hadSymbol) {
                    continue;
                } else if (character >= '0' && character <= '9') {
                    hasNumber = true;
                    int number = character - '0';
                    if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && number > 8)) {
                        return Integer.MIN_VALUE;
                    }
                    if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && number > 7 )) {
                        return Integer.MAX_VALUE;
                    }
                    if (negative) {
                        ans = ans * (10) - number;
                    } else {
                        ans = ans * (10) + number;
                    }
                } else if (character == '-' && !hasNumber && !hadSymbol) {
                    // 直接读取到正负号，还没有读取到数字
                    negative = true;
                    hadSymbol = true;
                } else if (character == '+' && !hasNumber && !hadSymbol) {
                    negative = false;
                    hadSymbol = true;
                } else {
                    return ans;
                }
            }
            return ans;
        }

    }
// leetcode submit region end(Prohibit modification and deletion)


}
