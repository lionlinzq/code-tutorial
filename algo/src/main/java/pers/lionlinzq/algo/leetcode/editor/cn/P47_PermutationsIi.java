// 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
// 
//
// 示例 1： 
//
// 
// 输入：nums = [1,1,2]
// 输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
// 输入：nums = [1,2,3]
// 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 数组 回溯 👍 1623 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 全排列 II
 *
 * @author Lin
 * @date 2024-11-08 13:43:22
 */
public class P47_PermutationsIi {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P47_PermutationsIi().new Solution();
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> permuteUnique(int[] nums) {
            ArrayList<Integer> state = new ArrayList<>();
            List<List<Integer>> res = new ArrayList<>();
            boolean[] selected = new boolean[nums.length];
            backtrack(state, nums, selected, res);
            return res;
        }

        /**
         * 回溯
         *
         * @param state    当前的状态
         * @param choices  可以选择的情况
         * @param selected 已经选择的情况
         * @param res      结果
         */
        void backtrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
            // 终止条件，当前选择的状态已经达到最后一个数据
            if (state.size() == choices.length) {
                res.add(new ArrayList<>(state));
                return;
            }
            Set<Integer> duplicated = new HashSet<>();
            for (int i = 0; i < choices.length; i++) {
                // 获取一个状态
                int choice = choices[i];
                // 根据是否选择过进行处理
                if (!selected[i] && !duplicated.contains(choice)) {
                    state.add(choice);
                    duplicated.add(choice);
                    selected[i] = true;
                    backtrack(state, choices, selected, res);
                    selected[i] = false;
                    state.remove(state.size() - 1);
                }
            }

        }

    }
// leetcode submit region end(Prohibit modification and deletion)

}
