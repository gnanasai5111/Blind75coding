Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and 
false otherwise.

A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. 
The tree tree could also be considered as a subtree of itself.

Example 1:

Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Approach 1 :

- The code checks if one binary tree (subRoot) is a subtree of another binary tree (root).
- The isIdentical helper method verifies if two trees are identical by comparing each node's value and recursively checking their left and right subtrees.
- If either tree is null, it returns true only if both are null.
- The isSubtree method recursively checks if subRoot is a subtree of root.
- If root is null, it returns false as there's no tree to compare.
- If isIdentical returns true for the current node in root, it confirms that subRoot matches this part of root, so isSubtree returns true.
- Otherwise, it recursively calls itself on the left and right subtrees of root to keep searching for a match with subRoot.

class Solution {
    public boolean isIdentical(TreeNode root,TreeNode subRoot){
        if(root==null || subRoot==null){
            return root==subRoot;
        }
        return root.val==subRoot.val && isIdentical(root.left,subRoot.left) && isIdentical(root.right,subRoot.right);
    }
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null){
            return false;
        }
        if(isIdentical(root,subRoot)){
            return true;
        }
        return isSubtree(root.left,subRoot) || isSubtree(root.right,subRoot);
        
    }
}

Time complexity - o(M*N)
Space complexity - O(M+N)

(There are few more approaches as well , please refer )
