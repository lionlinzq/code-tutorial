package pers.lionlinzq.algo.leetcode.editor.cn;
/**
字母序连续字符串 是由字母表中连续字母组成的字符串。换句话说，字符串 "abcdefghijklmnopqrstuvwxyz" 的任意子字符串都是 字母序连续字
符串 。 

 
 例如，"abc" 是一个字母序连续字符串，而 "acb" 和 "za" 不是。 
 

 给你一个仅由小写英文字母组成的字符串 s ，返回其 最长 的 字母序连续子字符串 的长度。 

 

 示例 1： 

 输入：s = "abacaba"
输出：2
解释：共有 4 个不同的字母序连续子字符串 "a"、"b"、"c" 和 "ab" 。
"ab" 是最长的字母序连续子字符串。
 

 示例 2： 

 输入：s = "abcde"
输出：5
解释："abcde" 是最长的字母序连续子字符串。
 

 

 提示： 

 
 1 <= s.length <= 10⁵ 
 s 由小写英文字母组成 
 

 Related Topics 字符串 👍 36 👎 0

*/
/**
 * P2414_最长的字母序连续子字符串的长度
 *
 * @author lzq
 */
public class P2414_LengthOfTheLongestAlphabeticalContinuousSubstring{
    public static void main(String[] args) {
        Solution solution = new P2414_LengthOfTheLongestAlphabeticalContinuousSubstring().new Solution();
        System.out.println(solution.longestContinuousSubstring("abacaba"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestContinuousSubstring(String s) {
        if (s.length() < 2){
            return s.length();
        }
        int ans = 1;
        int curMax = 1;
        for (int i = 1; i < s.length(); i++) {
            char newChar = s.charAt(i);
            char oldChar = s.charAt(i - 1);
            if (newChar - oldChar == 1){
                curMax++;
            }else {
                curMax = 1;
            }
            ans = Math.max(ans, curMax);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
