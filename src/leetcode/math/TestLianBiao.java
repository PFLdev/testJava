package leetcode.math;

import model.ListNode;

import java.util.List;

public class TestLianBiao {

    /**
     * https://blog.csdn.net/2201_75299492/article/details/136405782?spm=1001.2014.3001.5506
     *
     * 206、203、237、19、430、61、24、、92、25、2、445、21、23
     * 中等：237
     */

    public static void main(String[] args) {
        TestLianBiao test = new TestLianBiao();

    }

    //21输入：l1 = [1,2,4], l2 = [1,3,4]
    //输出：[1,1,2,3,4,4]
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode next = res;
        while (list1 != null && list2 != null) {
            if (list1.getVal() <= list2.getVal()) {
                next.setNext(list1);
                list1 = list1.getNext();
            } else {
                next.setNext(list2);
                list2 = list2.getNext();
            }
            next = next.getNext();
        }
        next.setNext(list1 == null ? list2 : list1);
        return res.getNext();
    }

    // 206:反转链表
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 203:移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        ListNode zeroNode = new ListNode(0, head);
        ListNode temp = zeroNode;
        while (temp.getNext() != null) {
            if (temp.getNext().getVal() == val) {
                temp.setNext(temp.getNext().getNext());
            } else {
                temp = temp.getNext();
            }
        }
        return zeroNode.getNext();
    }
}
