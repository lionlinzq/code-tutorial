/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * <p>
 * <p>
 * Related Topics 字典树 字符串 👍 3175 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * 最长公共前缀
 *
 * @author Lin
 * @date 2024-09-10 09:44:59
 */
public class P14_LongestCommonPrefix {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P14_LongestCommonPrefix().new Solution();
        System.out.println(solution.longestCommonPrefix(new String[]{"cir", "car"}));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            for (int i = 0; i < strs[0].length(); i++) {
                for (int j = 1; j < strs.length; j++) {
                    // 当前等待比较的字符串的长度比第一个字符串中等待比较的字符所在的位置要小 strs[j].length() == i
                    if (strs[j].length() == i || strs[0].charAt(i) != strs[j].charAt(i)) {
                        return strs[0].substring(0, i);
                    }
                }
            }
            return strs[0];
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
