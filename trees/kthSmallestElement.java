Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes
in the tree.

Example 1:

Input: root = [3,1,4,null,2], k = 1
Output: 1

  Approach 1 :

- The code defines a Solution class that finds the k-th smallest element in a binary search tree.
- The helper method performs an in-order traversal, adding each node’s value to an ArrayList, a, which will then contain the 
  tree’s elements in ascending order.
- In the kthSmallest method, an empty list a is initialized and populated by calling helper. 
- Finally, the k-th smallest element (index k-1 in the list) is returned.

class Solution {
    public void helper(TreeNode root,ArrayList<Integer> a){
        if(root==null){
            return;
        }
        helper(root.left,a);
        a.add(root.val);
        helper(root.right,a);
    }
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> a=new ArrayList<>();
        helper(root,a);
        return a.get(k-1);
    }
}

Time complexity - o(N)
Space complexity - o(N)

Approach 2 :

- This code defines a Solution class to find the k-th smallest element in a binary search tree using in-order traversal without
  building a list of elements. 
- The helper method traverses the tree while maintaining a count of visited nodes in an integer array, count.
- When the count reaches k, the current node’s value is stored in res, another integer array.
- The kthSmallest method initializes count and res arrays, then calls helper to fill res with the k-th smallest value,
  which it returns

class Solution {
    public void helper(TreeNode root,int count[],int res[],int k){
        if(root==null){
            return;
        }
        helper(root.left,count,res,k);
        count[0]++;
        if(count[0]==k){
            res[0]=root.val;
            return;
        }
        helper(root.right,count,res,k);
    }
    public int kthSmallest(TreeNode root, int k) {
        int count[]=new int[1];
        int res[]=new int[1];
        helper(root,count,res,k);
        return res[0];
    }
}

Time complexity - o(N)
Space complexity - o(N)
