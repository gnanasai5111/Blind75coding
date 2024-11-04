Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:

Input: p = [1,2,3], q = [1,2,3]
Output: true

First approach : Recursion

- The function compares two binary trees to check if they are identical.
- It returns true if both trees are structurally the same and have identical node values.
- First, it checks if both nodes are null, which means both trees are empty at that point, so they are identical.
- If only one node is null, the trees are not identical, so it returns false. 
- If the values of the nodes differ, it also returns false.
- Otherwise, it recursively checks the left and right subtrees for equality.

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }
        if(p==null || q==null){
            return false;
        }
        if(p.val!=q.val){
            return false;
        }
        return isSameTree(p.left,q.left) && isSameTree(p.right , q.right);
    }
}

Time complexity - o(N)
Space complexity - (N)

Second approach : Iterative

- The function checks if two binary trees are identical using an iterative approach. 
- It utilizes two queues to perform a level-by-level comparison. Both root nodes are initially added to their respective queues. 
- For each iteration, it removes one node from each queue and compares them.
- If both nodes are null, it skips to the next iteration. If one node is null while the other is not, or if their values are different,
  it returns false, indicating the trees are not the same.
- If nodes are equal, it adds the left and right children of each node to their respective queues. 
- After the loop, it confirms that both queues are empty, ensuring that both trees have identical structures and values throughout.

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> q1=new LinkedList<>();
        Queue<TreeNode> q2=new LinkedList<>();
        q1.add(p);
        q2.add(q);
        while(!q1.isEmpty() && !q2.isEmpty()){
            TreeNode node1=q1.poll();
            TreeNode node2=q2.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            q1.add(node1.left);
            q1.add(node1.right);
            q2.add(node2.left);
            q2.add(node2.right);     
        }
        return q1.isEmpty() && q2.isEmpty();
    }
}

Time complexity - o(N)
Space complexity - (N)
