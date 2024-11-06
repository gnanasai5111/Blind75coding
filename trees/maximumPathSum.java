A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.
  
Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Approach 1 :

- The code defines a method to find the maximum path sum in a binary tree. Starting from the root node, the getMaxSum method calculates the maximum sum
  of paths that can be obtained by including each node. If a node is null, it returns 0, as there is no path contribution.
- For each node, the method calculates the maximum path sum from its left and right children, ensuring only non-negative paths are considered
  (using Math.max(0, ...)). 
- It then checks if the sum of the current node's value and the maximum path sums from both children is greater than the current maximum stored in max[0].
- If so, it updates max[0].
- The function returns the maximum sum from either the left or right child, plus the node's value, which contributes to the path of the parent node.
- The maxPathSum method initializes max to hold the smallest possible integer and calls getMaxSum to populate it with the maximum path sum.
- Finally, it returns max[0], which contains the highest path sum in the binary tree.

class Solution {
    public int getMaxSum(TreeNode root,int max[]){
        if(root==null){
            return 0;
        }
        int leftMax=Math.max(0,getMaxSum(root.left,max));
        int rightMax=Math.max(0,getMaxSum(root.right,max));
        max[0]=Math.max(max[0],leftMax+rightMax+root.val);
        return Math.max(leftMax,rightMax)+root.val;
    }
    public int maxPathSum(TreeNode root) {
        int max[]=new int[1];
        max[0]=Integer.MIN_VALUE;
        getMaxSum(root,max);
        return max[0];
        
    }
}

Time complexity - o(N)
Space complexity - o(N)
