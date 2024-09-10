/**
 * 七个不同的符号代表罗马数字，其值如下：
 * <p>
 * <p>
 * <p>
 * <p>
 * 符号
 * 值
 * <p>
 * <p>
 * <p>
 * <p>
 * I
 * 1
 * <p>
 * <p>
 * V
 * 5
 * <p>
 * <p>
 * X
 * 10
 * <p>
 * <p>
 * L
 * 50
 * <p>
 * <p>
 * C
 * 100
 * <p>
 * <p>
 * D
 * 500
 * <p>
 * <p>
 * M
 * 1000
 * <p>
 * <p>
 * <p>
 * <p>
 * 罗马数字是通过添加从最高到最低的小数位值的转换而形成的。将小数位值转换为罗马数字有以下规则：
 * <p>
 * <p>
 * 如果该值不是以 4 或 9 开头，请选择可以从输入中减去的最大值的符号，将该符号附加到结果，减去其值，然后将其余部分转换为罗马数字。
 * 如果该值以 4 或 9 开头，使用 减法形式，表示从以下符号中减去一个符号，例如 4 是 5 (V) 减 1 (I): IV ，9 是 10 (X) 减 1
 * (I)：IX。仅使用以下减法形式：4 (IV)，9 (IX)，40 (XL)，90 (XC)，400 (CD) 和 900 (CM)。
 * 只有 10 的次方（I, X, C, M）最多可以连续附加 3 次以代表 10 的倍数。你不能多次附加 5 (V)，50 (L) 或 500 (D)。如果需要
 * 将符号附加4次，请使用 减法形式。
 * <p>
 * <p>
 * 给定一个整数，将其转换为罗马数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：num = 3749
 * <p>
 * <p>
 * 输出： "MMMDCCXLIX"
 * <p>
 * 解释：
 * <p>
 * <p>
 * 3000 = MMM 由于 1000 (M) + 1000 (M) + 1000 (M)
 * 700 = DCC 由于 500 (D) + 100 (C) + 100 (C)
 * 40 = XL 由于 50 (L) 减 10 (X)
 * 9 = IX 由于 10 (X) 减 1 (I)
 * 注意：49 不是 50 (L) 减 1 (I) 因为转换是基于小数位
 * <p>
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：num = 58
 * <p>
 * <p>
 * 输出："LVIII"
 * <p>
 * 解释：
 * <p>
 * <p>
 * 50 = L
 * 8 = VIII
 * <p>
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：num = 1994
 * <p>
 * <p>
 * 输出："MCMXCIV"
 * <p>
 * 解释：
 * <p>
 * <p>
 * 1000 = M
 * 900 = CM
 * 90 = XC
 * 4 = IV
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= num <= 3999
 * <p>
 * <p>
 * Related Topics 哈希表 数学 字符串 👍 1323 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * 整数转罗马数字
 *
 * @author Lin
 * @date 2024-09-05 18:04:15
 */
public class P12_IntegerToRoman {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P12_IntegerToRoman().new Solution();

    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        // I:1
        // V:5
        // X:10
        // L:50
        // C:100
        // D:500
        // M:1000
        public String intToRoman(int num) {
            StringBuffer roman = new StringBuffer();
            for (int i = 0; i < values.length; ++i) {
                int value = values[i];
                String symbol = symbols[i];
                while (num >= value) {
                    num -= value;
                    roman.append(symbol);
                }
                if (num == 0) {
                    break;
                }
            }
            return roman.toString();
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
