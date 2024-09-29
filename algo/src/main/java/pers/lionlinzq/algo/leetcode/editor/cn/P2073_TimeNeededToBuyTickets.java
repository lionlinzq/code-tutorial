/**
 * 有 n 个人前来排队买票，其中第 0 人站在队伍 最前方 ，第 (n - 1) 人站在队伍 最后方 。
 * <p>
 * 给你一个下标从 0 开始的整数数组 tickets ，数组长度为 n ，其中第 i 人想要购买的票数为 tickets[i] 。
 * <p>
 * 每个人买票都需要用掉 恰好 1 秒 。一个人 一次只能买一张票 ，如果需要购买更多票，他必须走到 队尾 重新排队（瞬间 发生，不计时间）。如果一个人没有剩下需
 * 要买的票，那他将会 离开 队伍。
 * <p>
 * 返回位于位置 k（下标从 0 开始）的人完成买票需要的时间（以秒为单位）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：tickets = [2,3,2], k = 2
 * <p>
 * <p>
 * 输出：6
 * <p>
 * 解释：
 * <p>
 * <p>
 * 队伍一开始为 [2,3,2]，第 k 个人以下划线标识。
 * 在最前面的人买完票后，队伍在第 1 秒变成 [3,2,1]。
 * 继续这个过程，队伍在第 2 秒变为[2,1,2]。
 * 继续这个过程，队伍在第 3 秒变为[1,2,1]。
 * 继续这个过程，队伍在第 4 秒变为[2,1]。
 * 继续这个过程，队伍在第 5 秒变为[1,1]。
 * 继续这个过程，队伍在第 6 秒变为[1]。第 k 个人完成买票，所以返回 6。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：tickets = [5,1,1,1], k = 0
 * <p>
 * <p>
 * 输出：8
 * <p>
 * 解释：
 * <p>
 * <p>
 * 队伍一开始为 [5,1,1,1]，第 k 个人以下划线标识。
 * 在最前面的人买完票后，队伍在第 1 秒变成 [1,1,1,4]。
 * 继续这个过程 3 秒，队伍在第 4 秒变为[4]。
 * 继续这个过程 4 秒，队伍在第 8 秒变为[]。第 k 个人完成买票，所以返回 8。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == tickets.length
 * 1 <= n <= 100
 * 1 <= tickets[i] <= 100
 * 0 <= k < n
 * <p>
 * <p>
 * Related Topics 队列 数组 模拟 👍 62 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P2073_买票需要的时间
 * @author Lin
 * @date 2024-09-29 16:55:34
 */
public class P2073_TimeNeededToBuyTickets {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P2073_TimeNeededToBuyTickets().new Solution();

    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int timeRequiredToBuy(int[] tickets, int k) {
            int ans = 0;
            for (int i = 0; i < tickets.length; i++) {
                if (i <= k) {
                    ans += Math.min(tickets[i], tickets[k]);
                } else {
                    ans += Math.min(tickets[i], tickets[k] - 1);
                }
            }
            return ans;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
