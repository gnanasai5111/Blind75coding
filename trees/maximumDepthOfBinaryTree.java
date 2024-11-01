Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 3

Approach 1 : DFS

- This Java method calculates the maximum depth of a binary tree. If the root node is null, it returns 0, indicating an empty tree.
- Otherwise, it recursively finds the depth of the left and right subtrees by calling maxDepth on root.left and root.right. 
- It then takes the maximum of these two depths and adds 1 to account for the root level itself.
- The method ultimately returns the maximum depth of the tree
    
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

Approach 2 :

class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        int level=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }
            }
            level++;
        }

        return level;
        
    }
}

- This method calculates the maximum depth of a binary tree using an iterative breadth-first search approach. 
- If the root node is null, it returns 0. Otherwise, it initializes a queue to hold tree nodes, starting with the root node.
- The level variable tracks the depth of the tree. In each level of the tree, the method processes all nodes at that level,
  polling each node from the queue and adding its children (left and right nodes) to the queue if they exist.
- After processing all nodes at a given depth level, it increments level by 1. When the queue is empty, it returns the total depth 
  level as the maximum depth of the tree.

Time complexity - o(N)
Space complexity - o(N)
