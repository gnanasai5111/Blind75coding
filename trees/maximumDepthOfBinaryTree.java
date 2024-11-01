Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 3

Approach 1 : DFS

- 
This Java method calculates the maximum depth of a binary tree. If the root node is null, it returns 0, indicating an empty tree. Otherwise, it recursively finds the depth of the left and right subtrees by calling maxDepth on root.left and root.right. It then takes the maximum of these two depths and adds 1 to account for the root level itself. The method ultimately returns the maximum depth of the tree
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left=maxDepth(root.left);
        int right=maxDepth(root.right);
        return 1+Math.max(left,right);
        
    }
}

Time complexity - o(N)
Space complexity - o(N)
