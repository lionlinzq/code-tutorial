/**
给你一个字符串 s，找到 s 中最长的 回文 子串。 

 

 示例 1： 

 
输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
 

 示例 2： 

 
输入：s = "cbbd"
输出："bb"
 

 

 提示： 

 
 1 <= s.length <= 1000 
 s 仅由数字和英文字母组成 
 

 Related Topics 双指针 字符串 动态规划 👍 7309 👎 0

*/

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * 最长回文子串
 * @author Lin
 * @date 2024-08-27 17:36:08
 */
public class P5_LongestPalindromicSubstring{
	public static void main(String[] args) {
	 	//测试代码
	 	Solution solution = new P5_LongestPalindromicSubstring().new Solution();

	}
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
		int len = s.length();
		if (len < 2) {
			return s;
		}

		int maxLen = 1;
		int begin = 0;
		// dp[i][j] 表示 s[i..j] 是否是回文串
		boolean[][] dp = new boolean[len][len];
		// 初始化：所有长度为 1 的子串都是回文串
		for (int i = 0; i < len; i++) {
			dp[i][i] = true;
		}

		char[] charArray = s.toCharArray();
		// 递推开始
		// 先枚举子串长度
		for (int L = 2; L <= len; L++) {
			// 枚举左边界，左边界的上限设置可以宽松一些
			for (int i = 0; i < len; i++) {
				// 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
				int j = L + i - 1;
				// 如果右边界越界，就可以退出当前循环
				if (j >= len) {
					break;
				}

				if (charArray[i] != charArray[j]) {
					dp[i][j] = false;
				} else {
					if (j - i < 3) {
						dp[i][j] = true;
					} else {
						dp[i][j] = dp[i + 1][j - 1];
					}
				}

				// 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
				if (dp[i][j] && j - i + 1 > maxLen) {
					maxLen = j - i + 1;
					begin = i;
				}
			}
		}
		return s.substring(begin, begin + maxLen);
    }

}
//leetcode submit region end(Prohibit modification and deletion)


}
