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
  
