package pers.lionlinzq.algo.leetcode.editor.cn;
// 桌子上有 n 个球，每个球的颜色不是黑色，就是白色。
//
// 给你一个长度为 n 、下标从 0 开始的二进制字符串 s，其中 1 和 0 分别代表黑色和白色的球。 
//
// 在每一步中，你可以选择两个相邻的球并交换它们。 
//
// 返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数」。 
//
// 
//
// 示例 1： 
//
// 
// 输入：s = "101"
// 输出：1
// 解释：我们可以按以下方式将所有黑色球移到右侧：
//- 交换 s[0] 和 s[1]，s = "011"。
// 最开始，1 没有都在右侧，需要至少 1 步将其移到右侧。
//
// 示例 2： 
//
// 
// 输入：s = "100"
// 输出：2
// 解释：我们可以按以下方式将所有黑色球移到右侧：
//- 交换 s[0] 和 s[1]，s = "010"。
//- 交换 s[1] 和 s[2]，s = "001"。
// 可以证明所需的最小步数为 2 。
// 
//
// 示例 3： 
//
// 
// 输入：s = "0111"
// 输出：0
// 解释：所有黑色球都已经在右侧。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n == s.length <= 10⁵ 
// s[i] 不是 '0'，就是 '1'。 
// 
//
// Related Topics 贪心 双指针 字符串 👍 26 👎 0


/**
 * 区分黑球与白球
 *
 * @author lin
 * @date 2024-06-06 11:11:18
 */
public class P2938_SeparateBlackAndWhiteBalls {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2938_SeparateBlackAndWhiteBalls().new Solution();
        System.out.println(solution.minimumSteps("111111111100100010"));
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 双指针
        public long minimumSteps(String s) {
            int len = s.length();
            int left = 0, right = len - 1;
            long ans = 0;
            while (left < right) {
                while (left < right && s.charAt(right) == '1') {
                    right--;
                }
                while (left < right && s.charAt(left) == '0') {
                    left++;
                }
                ans += right - left;
                right--;
                left++;
            }
            return ans;
        }

        // 贪心
        public long minimumSteps2(String s) {
            long res = 0;
            long oneCnt = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '1'){
                    oneCnt++;   // 统计每个0左侧的1个数
                }else{
                    res += oneCnt;  // 每个0左侧的1一定都是连续排在这个0左侧，相当于把0移动到这些1之前
                }
            }
            return res;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}