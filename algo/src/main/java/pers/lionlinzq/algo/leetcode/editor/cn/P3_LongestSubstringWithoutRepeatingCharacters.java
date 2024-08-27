/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 0 <= s.length <= 5 * 10⁴
 * s 由英文字母、数字、符号和空格组成
 * <p>
 * <p>
 * Related Topics 哈希表 字符串 滑动窗口 👍 10292 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * @author Lin
 * @date 2024-08-27 09:57:34
 */
public class P3_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P3_LongestSubstringWithoutRepeatingCharacters().new Solution();
		System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int ans = 0;
			// 记录无重复的字母的最远位置
			Map<Character,Integer> map = new HashMap<>();
			// 滑动窗口快慢指针
			int left = 0, right = 0;
			while (right < s.length()){
				if (map.containsKey(s.charAt(right))){
					left = Math.max(left, map.get(s.charAt(right)) + 1);
				}
				map.put(s.charAt(right), right);
				ans = Math.max(ans, right - left + 1);
				right++;
			}
			return ans;
        }

		// 实际上右指针一直都会走，采取for循环即可
		public int lengthOfLongestSubstring2(String s) {
			if (s.length()==0) return 0;
			HashMap<Character, Integer> map = new HashMap<Character, Integer>();
			int max = 0;
			int left = 0;
			for(int i = 0; i < s.length(); i ++){
				if(map.containsKey(s.charAt(i))){
					left = Math.max(left,map.get(s.charAt(i)) + 1);
				}
				map.put(s.charAt(i),i);
				max = Math.max(max,i-left+1);
			}
			return max;

		}

    }
// leetcode submit region end(Prohibit modification and deletion)


}
