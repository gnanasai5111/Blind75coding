

First approach :

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<Integer> list =new ArrayList<>();
        for(int i=0;i<lists.length;i++){
            ListNode head=lists[i];
            while(head!=null){
                list.add(head.val);
                head=head.next;
            }
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

Time complexity - o(NlogN) N is all nodes 
Space complexity - o(N) 

Approach 2 :

- This solution merges k sorted linked lists by repeatedly merging two lists at a time. 
- The mergeTwoLists method takes two sorted linked lists, list1 and list2, and merges them into a single sorted list.
- It uses a dummy node head to build the merged list by comparing the values at each node of list1 and list2.
- The smaller value is added to the merged list, and the pointer moves forward. 
- If one list is exhausted, the remaining nodes from the other list are appended.
- The mergeKLists method initializes the merged list as null and iterates through each list in the input array. 
- It uses the mergeTwoLists method to merge the current mergedHead with each list. 
- The result is a fully merged sorted list that combines all the input lists.

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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode mergedHead=null;
        for(int i=0;i<lists.length;i++){
            mergedHead = mergeTwoLists(mergedHead, lists[i]);          
        }
        return mergedHead;
    }
}

Time complexity - o(NlogN) N is all nodes 
Space complexity - o(1) 
