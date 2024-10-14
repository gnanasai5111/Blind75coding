Given the head of a singly linked list, reverse the list, and return the reversed list.
Example 1:

Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]


First approach : Brute Force 

- The solution defines a method to reverse a linked list. It first creates an empty list to store the values of the nodes. 
- Then, it iterates through the linked list, adding each node's value to the list.
- After this, a new linked list is constructed by going through the values in reverse order. 
- For the first value, it creates the head of the new list. For the rest, it appends new nodes to the previous one.
- Finally, the method returns the head of the reversed linked list.

class Solution {
    public ListNode reverseList(ListNode head) {
        ArrayList<Integer> list=new ArrayList<>();
        ListNode node=head;
        while(node!=null){
            list.add(node.val);
            node=node.next;
        }
        ListNode newHead=null;
        ListNode newNode=null;
        for(int i=list.size()-1;i>=0;i--){
            if(i==list.size()-1){
                ListNode curr=new ListNode(list.get(i));
                newHead=curr;
                newNode=curr;
            }
            else{
                ListNode curr=new ListNode(list.get(i));
                newNode.next=curr;
                newNode=curr;
            }
        }
        return newHead;
    }
}

Time complexity - o(N)
Space complexity - o(N)


Second approach - Stack

- The solution uses a stack to reverse a linked list. It first pushes the values of each node onto the stack by traversing the original list.
- Then, it creates a new linked list by popping the values from the stack, effectively reversing the order of the nodes.
- The first value popped becomes the new head of the reversed list. Afterward, new nodes are created for each remaining value popped from the stack and are linked to the
  previously created node. Finally, the method returns the head of the reversed linked list.

class Solution {
    public ListNode reverseList(ListNode head) {
        Stack<Integer> s=new Stack<>();
        ListNode node=head;
        while(node!=null){
            s.push(node.val);
            node=node.next;
        }
        ListNode newHead=null;
        ListNode newNode=null;
        if(!s.isEmpty()){
            ListNode curr=new ListNode(s.pop());
            newHead=curr;
            newNode=curr;
        }
        while(!s.isEmpty()){
            ListNode curr=new ListNode(s.pop());
            newNode.next=curr;
            newNode=curr;
        }
        return newHead;
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 3 : Iterative

- The solution reverses a linked list by using three pointers: curr, prev, and next.
- Initially, curr points to the head of the list, and prev is set to null.
- In each iteration, the next pointer stores the next node in the original list.
- Then, the current node's next pointer is updated to point to prev, effectively reversing the link.
- After that, prev is moved to the current node, and curr is moved to the next node. 
- The process continues until all nodes are reversed, and the method returns prev, which now points to the new head of the reversed list.


class Solution {
    public ListNode reverseList(ListNode head) {
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
}

Time complexity - o(N)
Space complexity - o(1)

Approach 4 : Recursive

- The solution uses recursion to reverse the linked list. If the list is empty or has only one node, it returns the head.
- Otherwise, it recursively reverses the rest of the list and updates the pointers.
- After the recursive call, the next node's next pointer is set to the current node, effectively reversing the link between the two.
- The current node's next is then set to null to mark the new end of the list. The method finally returns the new head of the reversed list.

class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode newHead = reverseList(head.next);
        ListNode next=head.next;
        next.next=head;
        head.next=null;
        return newHead;
    }
}

Time complexity - o(N)
Space complexity - o(N)
