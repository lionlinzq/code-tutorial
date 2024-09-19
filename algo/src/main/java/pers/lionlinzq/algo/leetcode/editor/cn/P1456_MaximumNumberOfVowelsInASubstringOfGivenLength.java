/**
 * 给你字符串 s 和整数 k 。
 * <p>
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * <p>
 * 英文中的 元音字母 为（a, e, i, o, u）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abciiidef", k = 3
 * 输出：3
 * 解释：子字符串 "iii" 包含 3 个元音字母。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "aeiou", k = 2
 * 输出：2
 * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "rhythms", k = 4
 * 输出：0
 * 解释：字符串 s 中不含任何元音字母。
 * <p>
 * <p>
 * 示例 5：
 * <p>
 * 输入：s = "tryhard", k = 4
 * 输出：1
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 * 1 <= k <= s.length
 * <p>
 * <p>
 * Related Topics 字符串 滑动窗口 👍 114 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * P1456_定长子串中元音的最大数目
 *
 * @author Lin
 * @date 2024-09-11 16:10:35
 */
public class P1456_MaximumNumberOfVowelsInASubstringOfGivenLength {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P1456_MaximumNumberOfVowelsInASubstringOfGivenLength().new Solution();
        System.out.println(solution.maxVowels("ibpbhixfiouhdljnjfflpapptrxgcomvnb", 33));
        // System.out.println("1234567".substring(0,4));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 前缀和方法，效率高
        public int maxVowels(String s, int k) {
            char[] charArray = s.toCharArray();
            int[] count = new int[charArray.length + 1];
            for (int i = 1; i <= charArray.length; i++) {
                char c = charArray[i - 1];
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    count[i] = count[i - 1] + 1;
                } else {
                    count[i] = count[i - 1];
                }
            }
            int ans = 0;
            for (int i = k; i < count.length; i++) {
                ans = Math.max(ans,count[i] - count[i - k]);
            }
            return ans;
        }

        // 直接遍历数组
        public int maxVowels2(String s, int k) {
            if (s.length() <= k) {
                return count(s);
            }
            int lastCount = count(s.substring(0, k));
            int ans = lastCount;
            for (int i = 1; i <= s.length() - k; i++) {
                lastCount = lastCount - (valid(s.charAt(i - 1)) ? 1 : 0)
                        + (valid(s.charAt(i + k - 1)) ? 1 : 0);
                ans = Math.max(ans, lastCount);
            }
            return ans;
        }

        public int count(String str) {
            int count = 0;
            for (char c : str.toCharArray()) {
                if (valid(c)) {
                    count++;
                }
            }
            return count;
        }

        public boolean valid(char c) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }
            return false;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
