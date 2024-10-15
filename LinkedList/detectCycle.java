Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. 
Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Brute Force : Hashing

- This Java solution checks if a linked list has a cycle using a HashSet to keep track of visited nodes.
- It starts with the head of the list and iterates through each node.
- For each node, it checks if the node is already in the HashSet, which indicates a cycle. If a cycle is found, it returns true.
- If the node is not in the set, it is added, and the loop continues to the next node.
- If the end of the list is reached (i.e., null), the function returns false, indicating no cycle is present.

public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set=new HashSet<>();
        ListNode node=head;
        while(node!=null){
            if(set.contains(node)){
                return true;
            }
            set.add(node);
            node=node.next;
        }
        return false;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 2 : (Floyd’s Cycle Detection Algorithm)

- This solution detects if a linked list has a cycle using the two-pointer technique, also known as the Floyd’s Cycle Detection Algorithm
- It uses two pointers, slow and fast, both starting at the head of the list.
- The slow pointer moves one step at a time, while the fast pointer moves two steps. 
- If there’s a cycle, the fast pointer will eventually catch up to the slow pointer, and the method will return true.
- If the fast pointer reaches the end of the list (null), it means there is no cycle, and the method returns false

public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set=new HashSet<>();
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
             if(slow==fast){
                return true;
            }
        }
        return false;
        
    }
}

Time complexity - o(N)
Space complexity - o(1)
