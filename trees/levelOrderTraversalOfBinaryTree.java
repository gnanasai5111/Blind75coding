Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

First approach :DFS

- This code performs a level order traversal of a binary tree using a recursive helper function.
- If the root node is null, the method returns an empty list. Otherwise, it initializes a result list to store each level's nodes.
- The helper function recursively traverses the tree, taking the current node, level, and result list as arguments.
- If the current level is equal to the size of the result list, a new list is added for that level. 
- The current node's value is then added to the list corresponding to its level.
- The function recursively calls itself for the left and right children, incrementing the level by 1 for each recursive call.
- Finally, levelOrder returns the result list with nodes grouped by their level.

class Solution {
    public void helper(TreeNode root,int level,List<List<Integer>> res){
        if(root==null){
            return;
        }
        if(level==res.size()){
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        helper(root.left,level+1,res);
        helper(root.right,level+1,res);

    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<List<Integer>> res=new ArrayList<>();
        helper(root,0,res);
        return res;       
        
    }
}

Time complexity - o(N)
Space complexity - o(N)

Second approach : BFS

- This method performs a level order traversal on a binary tree, returning a list of lists where each inner list contains the
  values of nodes at the same depth level. If the root node is null, it returns an empty list.
- Otherwise, it initializes a queue to store nodes and starts with the root node. 
- For each level, it creates an inner list to store values of nodes at that depth.
- It iterates through all nodes at the current level, polling each node from the queue, adding its value to the inner list,
  and adding its children to the queue if they exist. After processing all nodes at a level, it adds the inner list to the result list.
- When all levels are processed, it returns the result list containing values of nodes level by level.

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<List<Integer>> res=new ArrayList<>();
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size=q.size();
            ArrayList<Integer> inner=new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node=q.poll();
                inner.add(node.val);
                if(node.left!=null){
                    q.add(node.left);
                }
                if(node.right!=null){
                    q.add(node.right);
                }                        
            }
            res.add(inner);    
        } 
        return res;       
        
    }
}

Time complexity - o(N)
Space complexity - o(N)
