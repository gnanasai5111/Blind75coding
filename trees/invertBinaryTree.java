Given the root of a binary tree, invert the tree, and return its root.

Example 1:

Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

First approach : Recursion

- This function inverts a binary tree recursively, swapping the left and right subtrees for each node. Starting from the root, 
   it first checks if the node is null. 
- If so, it returns null, as there’s nothing to invert. For non-null nodes, it recursively inverts the right and left subtrees, storing them temporarily.
- It then assigns the inverted right subtree to the left and the inverted left subtree to the right. 
- Finally, it returns the root node, with all subtrees inverted.

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }
}

Time complexity - o(N)
Spaxe complexity - o(N)

Approach 2 :

- This function inverts a binary tree using an iterative approach with a queue. It starts by checking if the root is null; if so, it returns null.
- Otherwise, it initializes a queue and adds the root node. In a loop, it removes a node from the queue, swaps its left and right children, and then
  adds the non-null children to the queue for further processing.
- This continues until all nodes have been processed, ensuring each node’s left and right children are swapped.
- Finally, it returns the root node of the inverted tree.

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node=q.poll();
            TreeNode right = node.right;
            TreeNode left = node.left;
            node.left = right;
            node.right = left;
            if(node.left!=null){
                q.add(node.left);
            }
            if(node.right!=null){
                q.add(node.right);
            }
        }
        return root;
        
    }
}

Time complexity - o(N)
Spaxe complexity - o(N)
