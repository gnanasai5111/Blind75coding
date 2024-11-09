Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node
in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Example 1:


Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.


Approach  1:

- This code defines a Solution class to find the lowest common ancestor (LCA) of two nodes, p and q, in a binary search tree.
- The lowestCommonAncestor method begins by checking if either p or q is the root itself; if so, it returns the root as the LCA. 
- Then, it compares the values of p and q with the current root’s value. If both values are smaller than the root's value, 
  it recursively searches the left subtree. 
- If both values are greater, it searches the right subtree. If neither of these conditions is true, the root is the LCA, and it
  returns the root.

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p==root ||  q==root){
            return root;
        }
        if(p.val<root.val && q.val<root.val){
            return lowestCommonAncestor(root.left,p,q);
        }
        if(p.val>root.val && q.val>root.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
        
    }
}

Time complexity- o(N)
Space complexity - o(N)

Approach 2 :

- The code defines a Solution class to find the lowest common ancestor (LCA) of two nodes, p and q, in a binary search tree using 
  an iterative approach. Starting at the root, a variable node is used to traverse the tree.
- The loop continues until node is null:
- If both p and q have values greater than node.val, the traversal moves to the right subtree.
- If both p and q have values less than node.val, it moves to the left subtree.
- If neither of these conditions is true, node is the LCA, so it returns node.
- If the loop ends with node as null (an edge case), it returns null

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while(node!=null){
            if(p.val>node.val && q.val>node.val){
                node=node.right;
            }
            else if(p.val<node.val && q.val<node.val){
                node=node.left;
            }
            else{
                return node;
            }
        }
        return null;
        
    }
}

Time complexity- o(N)
Space complexity - o(1)
