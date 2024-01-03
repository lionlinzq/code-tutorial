package pers.lionlinzq.algo.leetcode.editor.cn;

// 给你一个链表的头节点 head 。
//
// 移除每个右侧有一个更大数值的节点。 
//
// 返回修改后链表的头节点 head 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
// 输入：head = [5,2,13,3,8]
// 输出：[13,8]
// 解释：需要移除的节点是 5 ，2 和 3 。
//- 节点 13 在节点 5 右侧。
//- 节点 13 在节点 2 右侧。
//- 节点 8 在节点 3 右侧。
// 
//
// 示例 2： 
//
// 
// 输入：head = [1,1,1,1]
// 输出：[1,1,1,1]
// 解释：每个节点的值都是 1 ，所以没有需要移除的节点。
// 
//
// 
//
// 提示： 
//
// 
// 给定列表中的节点数目在范围 [1, 10⁵] 内 
// 1 <= Node.val <= 10⁵ 
// 
//
// Related Topics 栈 递归 链表 单调栈 👍 76 👎 0

import pers.lionlinzq.algo.base.ListNode;

import java.util.ArrayDeque;

/**
 * 从链表中移除节点
 *
 * @author lzq
 */
public class P2487_RemoveNodesFromLinkedList {
    public static void main(String[] args) {
        Solution solution = new P2487_RemoveNodesFromLinkedList().new Solution();
        ListNode e = new ListNode(8, null);
        ListNode d = new ListNode(3, e);
        ListNode c = new ListNode(13, d);
        ListNode b = new ListNode(2, c);
        ListNode a = new ListNode(5, b);
        System.out.println(solution.removeNodes(a));
    }

    // leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode removeNodes(ListNode head) {
            ArrayDeque<ListNode> stack = new ArrayDeque<>();
            stack.push(head);
            ListNode dumpy = new ListNode(-1);
            dumpy.next = head;
            head = head.next;
            while (head != null) {
                while (!stack.isEmpty() && stack.peek().val < head.val) {
                    stack.pop();
                    if (!stack.isEmpty()) {
                        stack.peek().next = head;
                    }
                }
                stack.push(head);
                if (stack.size() == 1) {
                    dumpy.next = head;
                }
                head = head.next;
            }
            return dumpy.next;
        }
    }
// leetcode submit region end(Prohibit modification and deletion)

}
