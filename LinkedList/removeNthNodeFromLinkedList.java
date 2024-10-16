Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:

Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

First approach :

- The method removeNthFromEnd removes the nth node from the end of a singly-linked list.
- First, it calculates the total length of the linked list by iterating through the list with a pointer curr.
- After determining the length, it calculates the position of the node to be removed, counting from the start of the list.
- If the node to be removed is the first node, it returns the next node as the new head of the list. 
- Otherwise, the method iterates through the list again to find the node to be removed.
- It keeps track of both the current node and the previous node. 
- When the correct node is found, it adjusts the pointers to remove the node from the list, ensuring the previous node now 
  points to the node after the one being removed. The method finally returns the updated list

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr=head;
        int length=0;
        while(curr!=null){
            curr=curr.next;
            length++;
        }
        int nodeToRemove=length-n+1;
        curr=head;
        int index=0;
        ListNode prev=null;
        if(nodeToRemove==1){
            return head.next;
        }
        while(curr!=null){
            index++;
            if(index==nodeToRemove){
                prev.next=curr.next;
                curr.next=null;
                curr=prev.next;
            }
            else{
                prev=curr;
                curr=curr.next;
            }
        }
        return head;
        
    }
}

Time complexity - o(N)
Space complexity - o(1)


Approach 2 :

- This method, removeNthFromEnd, removes the nth node from the end of a singly linked list using a two-pointer approach. 
- Two pointers, slow and fast, are initially set to the head of the list.
- The fast pointer moves n steps ahead to create a gap between fast and slow.
- Then, both pointers move one step at a time until fast reaches the end of the list.
- At this point, the slow pointer will be at the node that needs to be removed.
- A prev pointer is used to track the node just before slow.
- If the prev pointer is still null, it means the node to be removed is the first node, so the method returns head.next as the new head. 
- Otherwise, it updates prev.next to skip the node pointed to by slow, effectively removing it from the list.
- The method then returns the updated list.

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow=head;
        ListNode fast=head;
        for(int i=0;i<n;i++){
            fast=fast.next;
        }
        ListNode prev=null;
        while(fast!=null){
            fast=fast.next;
            prev=slow;
            slow=slow.next;
        }
        if(prev==null){
            return head.next;
        }
        prev.next=slow.next;
        return head;
        
    }
}

Time complexity - o(N)
Space complexity - o(1)
