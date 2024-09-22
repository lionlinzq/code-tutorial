package pers.lionlinzq.algo.leetcode.editor.cn;
/**
 * 小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 * <p>
 * 如果小镇法官真的存在，那么：
 * <p>
 * <p>
 * 小镇法官不会信任任何人。
 * 每个人（除了小镇法官）都信任这位小镇法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * <p>
 * <p>
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 * <p>
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 2, trust = [[1,2]]
 * 输出：2
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：n = 3, trust = [[1,3],[2,3]]
 * 输出：3
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：n = 3, trust = [[1,3],[2,3],[3,1]]
 * 输出：-1
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= n <= 1000
 * 0 <= trust.length <= 10⁴
 * trust[i].length == 2
 * trust 中的所有trust[i] = [ai, bi] 互不相同
 * ai != bi
 * 1 <= ai, bi <= n
 * <p>
 * <p>
 * Related Topics 图 数组 哈希表 👍 359 👎 0
 */

/**
 * P997_找到小镇的法官
 *
 * @author lzq
 */
public class P997_FindTheTownJudge {
    public static void main(String[] args) {
        Solution solution = new P997_FindTheTownJudge().new Solution();

    }

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findJudge(int n, int[][] trust) {
            int[] cnt = new int[n + 1];
            int[] cnt2 = new int[n + 1];
            for (int i = 0; i < trust.length; i++) {
                cnt[trust[i][1]]++;
                cnt2[trust[i][0]]++;
            }
            for (int i = 1; i <= n ; i++) {
                if (cnt[i] == (n - 1) && cnt2[i] == 0){
                    return i;
                }
            }
            return -1;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}
