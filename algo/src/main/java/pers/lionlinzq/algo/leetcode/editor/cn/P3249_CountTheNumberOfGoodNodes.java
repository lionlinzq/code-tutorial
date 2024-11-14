// 现有一棵 无向 树，树中包含 n 个节点，按从 0 到 n - 1 标记。树的根节点是节点 0 。给你一个长度为 n - 1 的二维整数数组 edges，其
// 中 edges[i] = [ai, bi] 表示树中节点 ai 与节点 bi 之间存在一条边。
//
// 如果一个节点的所有子节点为根的 子树 包含的节点数相同，则认为该节点是一个 好节点。 
//
// 返回给定树中 好节点 的数量。 
//
// 子树 指的是一个节点以及它所有后代节点构成的一棵树。 
//
// 
//
// 
//
// 示例 1： 
//
// 
// 输入：edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]] 
// 
//
// 输出：7 
//
// 说明： 
// 
// 树的所有节点都是好节点。 
//
// 示例 2： 
//
// 
// 输入：edges = [[0,1],[1,2],[2,3],[3,4],[0,5],[1,6],[2,7],[3,8]] 
// 
//
// 输出：6 
//
// 说明： 
// 
// 树中有 6 个好节点。上图中已将这些节点着色。 
//
// 示例 3： 
//
// 
// 输入：edges = [[0,1],[1,2],[1,3],[1,4],[0,5],[5,6],[6,7],[7,8],[0,9],[9,10],[9,1
// 2],[10,11]]
// 
//
// 输出：12 
//
// 解释： 
// 
// 除了节点 9 以外其他所有节点都是好节点。 
//
// 
//
// 提示： 
//
// 
// 2 <= n <= 10⁵ 
// edges.length == n - 1 
// edges[i].length == 2 
// 0 <= ai, bi < n 
// 输入确保 edges 总表示一棵有效的树。 
// 
//
// Related Topics 树 深度优先搜索 👍 17 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 统计好节点的数目
 *
 * @author Lin
 * @date 2024-11-14 09:21:07
 */
public class P3249_CountTheNumberOfGoodNodes {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P3249_CountTheNumberOfGoodNodes().new Solution();
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
        System.out.println(solution.countGoodNodes(edges));
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int ans = 0;
        List<Integer>[] tree;

        public int countGoodNodes(int[][] edges) {
            int n = edges.length;
            // 建树
            tree = new ArrayList[n + 1];
            Arrays.setAll(tree, i -> new ArrayList<>());
            for (int[] edge : edges) {
                int x = edge[0], y = edge[1];
                tree[x].add(y);
                tree[y].add(x);
            }
            dfs(0, -1, tree);
            return ans;
        }

        public int dfs(int node, int parent, List<Integer>[] tree) {
            int treeSize = 0;
            int subTreeSize = 0;
            boolean valid = true;
            for (int child : tree[node]) {
                if (child != parent) {
                    int size = dfs(child, node, tree);
                    if (subTreeSize == 0) {
                        // 叶子节点
                        subTreeSize = size;
                    } else if (size != subTreeSize) {
                        valid = false;
                    }
                    treeSize += size;
                }
            }
            ans += valid ? 1 : 0;
            return treeSize + 1;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}
