/**
 * 给你一个由字符 'a'、'b'、'c' 组成的字符串 s 和一个非负整数 k 。每分钟，你可以选择取走 s 最左侧 还是 最右侧 的那个字符。
 * <p>
 * 你必须取走每种字符 至少 k 个，返回需要的 最少 分钟数；如果无法取到，则返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：s = "aabaaaacaabc", k = 2
 * 输出：8
 * 解释：
 * 从 s 的左侧取三个字符，现在共取到两个字符 'a' 、一个字符 'b' 。
 * 从 s 的右侧取五个字符，现在共取到四个字符 'a' 、两个字符 'b' 和两个字符 'c' 。
 * 共需要 3 + 5 = 8 分钟。
 * 可以证明需要的最少分钟数是 8 。
 * <p>
 * <p>
 * 示例 2：\
 * <p>
 * <p>
 * 输入：s = "a", k = 1
 * 输出：-1
 * 解释：无法取到一个字符 'b' 或者 'c'，所以返回 -1 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= s.length <= 10⁵
 * s 仅由字母 'a'、'b'、'c' 组成
 * 0 <= k <= s.length
 * <p>
 * <p>
 * Related Topics 哈希表 字符串 滑动窗口 👍 87 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * P2516_每种字符至少取 K 个
 *
 * @author Lin
 * @date 2024-09-27 17:45:50
 */
public class P2516_TakeKOfEachCharacterFromLeftAndRight {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2516_TakeKOfEachCharacterFromLeftAndRight().new Solution();

    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int takeCharacters(String s, int k) {
            int[] charCount = new int[3];
            for (char c : s.toCharArray()) {
                charCount[c - 'a']++;
            }
            if (charCount[0] < k || charCount[1] < k || charCount[2] < k) {
                return -1;
            }
            int maxLen = 0;
            int left = 0;
            for (int right = 0; right < s.length(); right++) {
                int index = s.charAt(right) - 'a';
                charCount[index]--;
                while (charCount[index] < k) {
                    charCount[s.charAt(left) - 'a']++;
                    left++;
                }
                maxLen = Math.max(maxLen, right - left + 1);
            }
            return s.length() - maxLen;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
