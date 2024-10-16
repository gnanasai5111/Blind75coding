You are given the head of a singly linked-list. The list can be represented as:
L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

Example 1:

Input: head = [1,2,3,4]
Output: [1,4,2,3]

First approach :

- This method, reorderList, rearranges a singly-linked list by alternating nodes from the beginning and the end of the list.
- First, it checks if the list is empty or has only one node, in which case no reordering is needed.
- An ArrayList is used to store all the nodes of the list, so each node can be easily accessed by index. 
- It then uses two pointers, i starting from the beginning of the list and j from the end, to alternate the nodes.
- The while loop runs as long as i is less than j, alternating nodes from both ends by updating the next pointers. 
- The process continues until the two pointers meet in the middle.
- If the list has an odd number of nodes, the last remaining node is linked correctly before terminating the list by setting the next
  pointer of the final node to null, ensuring the list ends cleanly without any cycles.

class Solution {
    public void reorderList(ListNode head) {
        if(head==null || head.next==null){
            return;
        }
        ArrayList<ListNode> list=new ArrayList<>();
        ListNode curr=head;
        while(curr!=null){
            list.add(curr);
            curr=curr.next;
        }
        int i=0;
        int j=list.size()-1;
        ListNode node=null;
        while(i<j){
            if(i==0){
                node=list.get(i);
            }
            else{
                node.next = list.get(i);
                node = node.next;
            }
            node.next = list.get(j);
            node = node.next;
            i++;
            j--;
        }
         if (i == j) {
            node.next = list.get(i);
            node = node.next;
        }
        node.next = null;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)


Approach 2: 

- Finding the middle node: The findMiddle method uses the two-pointer technique (slow and fast pointers) to locate the middle
  node of the linked list.
- Reversing the second half: The findReverse method takes the second half of the list, starting from middle.next, and reverses it.
- Breaking the list: After finding the middle node, the link between the first and second halves of the list is broken by setting
  middle.next = null.
- Merging the two halves: The reorderList method then merges the first half and the reversed second half in an alternating fashion.
- It does so by keeping track of the next nodes in both halves and updating their next pointers accordingly.
- Termination: The loop continues until one of the halves is exhausted, ensuring the reordered list is formed correctly

class Solution {
    public ListNode findMiddle(ListNode head){
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
    public ListNode findReverse(ListNode head){
        ListNode curr=head;
        ListNode prev=null;
        while(curr!=null){
            ListNode next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
    public void reorderList(ListNode head) {
        ListNode middle=findMiddle(head);
        ListNode reverse=findReverse(middle.next);
        middle.next=null;
        ListNode c1=head;
        ListNode c2=reverse;
        while(c1!=null && c2!=null){
            ListNode firstHalfNext=c1.next;
            ListNode secondHalfNext=c2.next;
            c1.next=c2;
            c2.next=firstHalfNext;
            c1=firstHalfNext;
            c2=secondHalfNext;

        }

    }
}

Time complexity - o(N)
Space complexity - o(1)
  
