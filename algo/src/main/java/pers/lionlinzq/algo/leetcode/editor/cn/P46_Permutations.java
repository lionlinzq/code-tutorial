// 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
//
// 
//
// 示例 1： 
//
// 
// 输入：nums = [1,2,3]
// 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
// 输入：nums = [0,1]
// 输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
// 输入：nums = [1]
// 输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
//
// Related Topics 数组 回溯 👍 2988 👎 0

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 *
 * @author Lin
 * @date 2024-11-08 10:51:57
 */
public class P46_Permutations {
    public static void main(String[] args) {
        // 测试代码
        Solution solution = new P46_Permutations().new Solution();
    }

    // 力扣代码
    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            ArrayList<Integer> state = new ArrayList<>();
            List<List<Integer>> res = new ArrayList<>();
            boolean[] selected = new boolean[nums.length];
            backtrack(state,nums,selected,res);
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
            for (int i = 0; i < choices.length; i++) {
                // 获取一个状态
                int choice = choices[i];
                // 根据是否选择过进行处理
                if (!selected[i]){
                    state.add(choice);
                    selected[i] = true;
                    backtrack(state,choices,selected,res);
                    selected[i] = false;
                    state.remove(state.size() - 1);
                }
            }

        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}
