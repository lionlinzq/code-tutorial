/**
给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。 

 初始化 A 和 B 的元素数量分别为 m 和 n。 

 示例: 

 输入:
A = [1,2,3,0,0,0], m = 3
B = [2,5,6],       n = 3

输出: [1,2,2,3,5,6] 

 说明: 

 
 A.length == n + m 
 

 Related Topics 数组 双指针 排序 👍 178 👎 0

*/

package pers.lionlinzq.algo.leetcode.editor.cn;

import java.util.Arrays;

/**
 * 合并排序的数组
 * @author Lin
 * @date 2024-08-23 10:22:43
 */
public class SortedMergeLcci{
	public static void main(String[] args) {
	 	//测试代码
	 	Solution solution = new SortedMergeLcci().new Solution();
		solution.merge(new int[]{2,3,5,0,0,0},3, new int[]{1,4,6},3);
	}
	 
//力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
		int end = A.length - 1;
		int am = m - 1, bn = n - 1;
		while (am >= 0 && bn >= 0){
			if (A[am] >= B[bn]){
				A[end] = A[am--];
			}else {
				A[end] = B[bn--];
			}
			end--;
		}
		while (bn >= 0){
			A[end--] = B[bn--];
		}
	}
}
//leetcode submit region end(Prohibit modification and deletion)


}
