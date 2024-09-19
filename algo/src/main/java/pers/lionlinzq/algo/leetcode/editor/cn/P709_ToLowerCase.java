/**
给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。 

 

 示例 1： 

 
输入：s = "Hello"
输出："hello"
 

 示例 2： 

 
输入：s = "here"
输出："here"
 

 示例 3： 

 
输入：s = "LOVELY"
输出："lovely"
 

 

 提示： 

 
 1 <= s.length <= 100 
 s 由 ASCII 字符集中的可打印字符组成 
 

 Related Topics 字符串 👍 244 👎 0

*/

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P709_转换成小写字母
 * @author Lin
 * @date 2024-09-11 15:27:03
 */
public class P709_ToLowerCase{
	public static void main(String[] args) {
	 	//测试代码
	 	Solution solution = new P709_ToLowerCase().new Solution();

	}
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String toLowerCase(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			if (ch >= 65 && ch <= 90) {
				// 代替与32的加法
				ch |= 32;
			}
			sb.append(ch);
		}
		return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}
