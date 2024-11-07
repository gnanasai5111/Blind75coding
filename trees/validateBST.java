Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [2,1,3]
Output: true

Approach 1 :

- The isValidBST method checks if a binary tree is a valid binary search tree (BST).
- It uses a helper method, isValid, which recursively verifies that each node’s value lies within a specific range, defined by 
  min and max limits.
- Starting with the entire range of valid values (Long.MIN_VALUE to Long.MAX_VALUE), the function narrows the range as it moves
  down the tree:
- For the left child of a node, the value must be less than the current node's value, so max is updated to the current node’s value.
- For the right child, the value must be greater than the current node's value, so min is updated to the current node’s value.
- If a node's value violates these boundaries, the function returns false, meaning the tree is not a valid BST. 
- If all nodes satisfy the constraints, the function returns true, confirming the tree is a valid BST.
- This approach ensures each node respects the BST properties not just with its immediate parent, but with the entire path up 
  to the root.


class Solution {
    public boolean isValid(TreeNode root,long min,long max){
        if(root==null){
            return true;
        }
        if(root.val>=max || root.val<=min){
            return false;
        }
        return isValid(root.left,min,root.val) && isValid(root.right,root.val,max);
    }
    public boolean isValidBST(TreeNode root) {
        return isValid(root,Long.MIN_VALUE,Long.MAX_VALUE);
        
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 2 :

- The isValidBST method determines if a binary tree is a valid binary search tree (BST) by performing a level-order traversal. 
- Using a queue, it processes each node with associated minimum and maximum boundary values that define the valid range for the
  node's value.
- Initialization: It starts by enqueuing the root node with initial boundary values set to Long.MIN_VALUE and Long.MAX_VALUE.
  Level-Order Traversal: For each node in the queue:
- It checks if the node’s value is within its specified range (min to max). If the value is out of this range, it returns false, 
  indicating the tree is not a BST.
- It then adds the left child to the queue with an updated maximum boundary (set to the current node’s value) and the right child
  with an updated minimum boundary (also set to the current node’s value).
- Validation Completion: If all nodes satisfy the BST properties according to their respective ranges, the function returns true, 
  confirming the tree is a valid BST.
- This method ensures each node adheres to the BST constraints not only with its direct parent but within the context of the
  entire tree structure.

class Solution {
    class NodeRange{
        TreeNode node;
        long min;
        long max;
        public NodeRange(TreeNode node,long min,long max){
            this.node=node;
            this.min=min;
            this.max=max;
        }
    }
    public boolean isValidBST(TreeNode root) {
        Queue<NodeRange> q= new LinkedList<>();
        if(root==null){
            return true;
        } 
        q.add(new NodeRange(root,Long.MIN_VALUE,Long.MAX_VALUE));
        while(!q.isEmpty()){
            NodeRange n=q.poll();
            TreeNode node=n.node;
            long min=n.min;
            long max=n.max;
            if(node.val<=min || node.val>=max){
                return false;
            }
            if(node.left!=null){
                q.add(new NodeRange(node.left,min,node.val));
            }
            if(node.right!=null){
                q.add(new NodeRange(node.right,node.val,max));
            }
        }
        return true;
        
    }
}

Time complexity - o(N)
Space complexity - o(N)
