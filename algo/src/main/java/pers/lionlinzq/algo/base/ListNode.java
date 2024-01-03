package pers.lionlinzq.algo.base;

/**
 * 列表节点
 *
 * @author lin
 * @date 2024/01/03
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return val + "->" + next;
    }
}
