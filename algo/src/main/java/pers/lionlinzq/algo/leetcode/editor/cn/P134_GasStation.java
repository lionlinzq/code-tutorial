/**
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: gas = [2,3,4], cost = [3,4,3]
 * 输出: -1
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * <p>
 * <p>
 * <p>
 * 提示:
 * <p>
 * <p>
 * gas.length == n
 * cost.length == n
 * 1 <= n <= 10⁵
 * 0 <= gas[i], cost[i] <= 10⁴
 * <p>
 * <p>
 * Related Topics 贪心 数组 👍 1725 👎 0
 */

package pers.lionlinzq.algo.leetcode.editor.cn;

/**
 * P134_加油站
 *
 * @author Lin
 * @date 2024-10-08 10:51:33
 */
public class P134_GasStation {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P134_GasStation().new Solution();
        int[] gas = {7,1,0,11,4};
        int[] cost = {5,9,1,2,5};
        System.out.println(solution.canCompleteCircuit(gas, cost));
    }

    // 力扣代码
// leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            int curSum = 0;  // 从起点开始的累计和
            int totalSum = 0;  // 总的累计和
            int start = 0;  // 当前的起点
            for(int i = 0; i < n; i++) {
                curSum += (gas[i] - cost[i]);
                totalSum += (gas[i] - cost[i]);
                if(curSum < 0) {
                    start = i + 1;  // 这一段都不行，更换起点
                    curSum = 0;
                }
            }
            if(totalSum >= 0)
                return start;
            return -1;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)


}
