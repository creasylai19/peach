package com.creasy.algorithm;

/**
 * leetcode中的题目，两数相加
 */
public class P2AddTwoNumber {

    public static void main(String[] args) {
        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(3);

        l11.next = l12;
        l12.next = l13;
        l13.next = l14;

        ListNode l21 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);

        l21.next = l22;
        l22.next = l23;

        printListNode(addTwoNumbers(l11, l21));
    }

    private static void printListNode(ListNode listNode) {
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if( l1 == null ) {
            return l2;
        }
        if( l2 == null ) {
            return l1;
        }

        ListNode ret = new ListNode(0);
        ListNode first = ret;
        while ( l1 != null && l2 != null ) {
            int sum = l1.val + l2.val + ret.val;
            int val = sum % 10;
            int addToNext = sum / 10;
            ret.val = val;

            l1 = l1.next;
            l2 = l2.next;

            if( l1 != null || l2 != null || addToNext > 0) {
                ret.next = new ListNode(addToNext);
                ret = ret.next;
            }
        }
        addTail(l1, ret);
        addTail(l2, ret);
        return first;
    }

    /**
     * 两者均为当前应该相加的节点
     * @param listNode
     * @param ret
     */
    private static void addTail(ListNode listNode, ListNode ret) {
        while( listNode != null ) {
            int sum = listNode.val + ret.val;
            int val = sum % 10;
            int addToNext = sum / 10;
            ret.val = val;

            if( addToNext == 0 ) {
                ret.next = listNode.next;
                break;
            }
            ret.next = new ListNode(addToNext);
            listNode = listNode.next;
            ret = ret.next;
        }
    }

}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}
