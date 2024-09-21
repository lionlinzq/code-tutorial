package pers.lionlinzq.algo.leetcode.editor.cn;
/**
有一个无向的 星型 图，由 n 个编号从 1 到 n 的节点组成。星型图有一个 中心 节点，并且恰有 n - 1 条边将中心节点与其他每个节点连接起来。 

 给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间存在一条边。请你找出并返回 edges 所表
示星型图的中心节点。 

 

 示例 1： 
 
 
输入：edges = [[1,2],[2,3],[4,2]]
输出：2
解释：如上图所示，节点 2 与其他每个节点都相连，所以节点 2 是中心节点。
 

 示例 2： 

 
输入：edges = [[1,2],[5,1],[1,3],[1,4]]
输出：1
 

 

 提示： 

 
 3 <= n <= 10⁵ 
 edges.length == n - 1 
 edges[i].length == 2 
 1 <= ui, vi <= n 
 ui != vi 
 题目数据给出的 edges 表示一个有效的星型图 
 

 Related Topics 图 👍 89 👎 0

*/
/**
 * P1791_找出星型图的中心节点
 *
 * @author lzq
 */
public class P1791_FindCenterOfStarGraph{
    public static void main(String[] args) {
        Solution solution = new P1791_FindCenterOfStarGraph().new Solution();
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findCenter(int[][] edges) {
        int x = edges[0][0];
        int y = edges[0][1];

        int z = edges[1][0];
        int c = edges[1][1];

        return (x == z || x == c) ? x : y;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
