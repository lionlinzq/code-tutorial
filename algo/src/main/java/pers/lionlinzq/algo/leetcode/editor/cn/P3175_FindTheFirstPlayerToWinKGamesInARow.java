// 有 n 位玩家在进行比赛，玩家编号依次为 0 到 n - 1 。
//
// 给你一个长度为 n 的整数数组 skills 和一个 正 整数 k ，其中 skills[i] 是第 i 位玩家的技能等级。skills 中所有整数 互不
// 相同 。
//
// 所有玩家从编号 0 到 n - 1 排成一列。 
//
// 比赛进行方式如下： 
//
// 
// 队列中最前面两名玩家进行一场比赛，技能等级 更高 的玩家胜出。 
// 比赛后，获胜者保持在队列的开头，而失败者排到队列的末尾。 
// 
//
// 这个比赛的赢家是 第一位连续 赢下 k 场比赛的玩家。 
//
// 请你返回这个比赛的赢家编号。 
//
// 
//
// 示例 1： 
//
// 
// 输入：skills = [4,2,6,3,9], k = 2 
// 
//
// 输出：2 
//
// 解释： 
//
// 一开始，队列里的玩家为 [0,1,2,3,4] 。比赛过程如下： 
//
// 
// 玩家 0 和 1 进行一场比赛，玩家 0 的技能等级高于玩家 1 ，玩家 0 胜出，队列变为 [0,2,3,4,1] 。 
// 玩家 0 和 2 进行一场比赛，玩家 2 的技能等级高于玩家 0 ，玩家 2 胜出，队列变为 [2,3,4,1,0] 。 
// 玩家 2 和 3 进行一场比赛，玩家 2 的技能等级高于玩家 3 ，玩家 2 胜出，队列变为 [2,4,1,0,3] 。 
// 
//
// 玩家 2 连续赢了 k = 2 场比赛，所以赢家是玩家 2 。 
//
// 示例 2： 
//
// 
// 输入：skills = [2,5,4], k = 3 
// 
//
// 输出：1 
//
// 解释： 
//
// 一开始，队列里的玩家为 [0,1,2] 。比赛过程如下： 
//
// 
// 玩家 0 和 1 进行一场比赛，玩家 1 的技能等级高于玩家 0 ，玩家 1 胜出，队列变为 [1,2,0] 。 
// 玩家 1 和 2 进行一场比赛，玩家 1 的技能等级高于玩家 2 ，玩家 1 胜出，队列变为 [1,0,2] 。 
// 玩家 1 和 0 进行一场比赛，玩家 1 的技能等级高于玩家 0 ，玩家 1 胜出，队列变为 [1,2,0] 。 
// 
//
// 玩家 1 连续赢了 k = 3 场比赛，所以赢家是玩家 1 。 
//
// 
//
// 提示： 
//
// 
// n == skills.length 
// 2 <= n <= 10⁵ 
// 1 <= k <= 10⁹ 
// 1 <= skills[i] <= 10⁶ 
// skills 中的整数互不相同。 
// 
//
// Related Topics 数组 模拟 👍 33 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * 找到连续赢 K 场比赛的第一位玩家
 *
 * @author Lin
 * @date 2024-10-24 16:54:05
 */
public class P3175_FindTheFirstPlayerToWinKGamesInARow {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P3175_FindTheFirstPlayerToWinKGamesInARow().new Solution();
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findWinningPlayer(int[] skills, int k) {
            int maxI = 0;
            int win = 0;
            // 提示：当遍历完一次还没有人赢k次，所有的人都不可能比最大的人先赢到k次了
            for (int i = 1; i < skills.length && win < k; i++) {
                if (skills[i] > skills[maxI]) { // 打擂台，发现新的最大值
                    maxI = i;
                    win = 0;
                }
                win++; // 获胜回合 +1
            }
            // 如果 k 很大，那么 maxI 就是 skills 最大值的下标，毕竟最大值会一直赢下去
            return maxI;
        }

        /**
         * 模拟
         */
        public int findWinningPlayer2(int[] skills, int k) {
            int n = skills.length;
            int cnt = 0;
            int i = 0, last_i = 0;

            while (i < n) {
                int j = i + 1;
                while (j < n && skills[j] < skills[i] && cnt < k) {
                    j++;
                    cnt++;
                }
                if (cnt == k) {
                    return i;
                }
                cnt = 1;
                last_i = i;
                i = j;
            }
            return last_i;
        }

    }
// leetcode submit region end(Prohibit modification and deletion)

}
