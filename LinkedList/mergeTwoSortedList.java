You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.
Example 1:

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]


First approach :

- This solution merges two sorted linked lists into one by first collecting all values from both lists into an ArrayList. 
- It iterates through list1 and list2, adding their values to the list.
- After gathering all the values, it sorts the ArrayList to ensure the result is in order. 
- Then, a new linked list is constructed by creating nodes for each value in the sorted list. 
- The first node becomes the head of the new list, and the subsequent nodes are linked sequentially.
- Finally, the head of the merged linked list is returned.


class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode l1=list1;
        ListNode l2=list2;
        ArrayList<Integer> list=new ArrayList<>();
        while(l1!=null){
            list.add(l1.val);
            l1=l1.next;
        }
        while(l2!=null){
            list.add(l2.val);
            l2=l2.next;
        }
        Collections.sort(list);
        ListNode newHead=null;
        ListNode node=null;
        for(int i=0;i<list.size();i++){
            ListNode curr=new ListNode(list.get(i));
            if(i==0){
                newHead=curr;
                node=curr;
            }
            else{
                node.next=curr;
                node=curr;
            }

        }
        return newHead;
        
    }
}

Time complexity - o((N+M)log(N+M))
Space complexity - o(N+M)


Second approach : 

- This solution merges two sorted linked lists into one in a more efficient way by using a dummy node. 
- A new node, head, is initialized with a value of -1, and another pointer node is used to build the merged list.
- The algorithm iterates through both input lists, list1 and list2, comparing the values at each node. 
- The smaller value node is attached to the node pointer, and the pointer moves forward. 
- Once one of the lists is fully traversed, the remaining nodes of the other list are appended.
- The merged list starts from head.next, which is returned at the end.
  
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode l1=list1;
        ListNode l2=list2;
        ListNode head=new ListNode(-1);
        ListNode node=head;

        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                node.next=l1;
                l1=l1.next;
            }
            else{
                node.next=l2;
                l2=l2.next;
            }
            node=node.next;
        }
        if(l1!=null){
            node.next=l1;
        }
        if(l2!=null){
            node.next=l2;
        }
        return head.next;
       
        
    }
}

Time complexity - o(N+M)
Space complexity - o(1)
