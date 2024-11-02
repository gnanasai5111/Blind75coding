1 . Preorder (DFS) - Recursive

class Solution {
    public void traversal(TreeNode root,List<Integer> res){
        if(root==null){
            return;
        }
        res.add(root.val);
        traversal(root.left,res);
        traversal(root.right,res);
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        traversal(root,res);
        return res;
    }
}

2. Inorder(DFS) - Recursive

class Solution {
    public void traversal(TreeNode root,List<Integer> res){
        if(root==null){
            return;
        }
        traversal(root.left,res);
        res.add(root.val);
        traversal(root.right,res);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        traversal(root,res);
        return res;
    }
}

3. PostOrder(DFS) - Recursive

class Solution {
    public void traversal(TreeNode root,List<Integer> res){
        if(root==null){
            return;
        }
        traversal(root.left,res);
        traversal(root.right,res);
         res.add(root.val);
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        traversal(root,res);
        return res;
    }
}

- The code consists of three different implementations for Depth-First Search (DFS) traversals of a binary tree: preorder, inorder, 
  and postorder. Each implementation uses a helper function called traversal that takes a tree node and a result list.
- If the node is null, it simply returns.
- For preorder traversal, it first adds the node's value to the result list, then recursively processes the left and right subtrees.
- This gives a node-left-right traversal order.
- In inorder traversal, the code first processes the left subtree, then adds the node's value to the result list, and finally
  processes the right subtree. This results in a left-node-right order.
- In postorder traversal, the code first recursively processes the left and right subtrees, and then adds the node's value to the
  result list, yielding a left-right-node order.
- Each traversal method returns the result list with the nodes visited in the respective DFS order.

Time and Space complexity - o(N)

4 . Preorder - Iterative 

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> s=new Stack<>();
        List<Integer> res=new ArrayList<>();
        if(root==null){
            return res;
        }
        s.push(root);
        while(!s.isEmpty()){
            TreeNode node=s.pop();
            res.add(node.val);
            if(node.right!=null){
                s.push(node.right);
            }
            if(node.left!=null){
                s.push(node.left);
            }
            
        }
        return res;
    }
}

- The code performs an iterative preorder traversal of a binary tree using a stack.
- It initializes a stack and a result list. If the root is null, it simply returns an empty list.
- Otherwise, it pushes the root node onto the stack to start the traversal.
- The loop continues while the stack isn’t empty. For each iteration, it pops the node at the top of the stack, adds its
  value to the result list, and then checks its right and left children.
- If the right child exists, it’s pushed onto the stack first, followed by the left child. 
- This ordering ensures that the left subtree is processed before the right subtree, adhering to preorder’s node-left-right
  traversal order. Once the stack is empty, it returns the result list containing the values in preorder traversal order.

Time and Space complexity - o(N)

5 . Inorder - Iterative

- The code performs an iterative inorder traversal of a binary tree using a stack. 
- It initializes an empty list to store the result and a stack to manage the nodes.
- Starting with the root node as the current node, it enters a loop that continues as long as there is a current node or 
  the stack is not empty.
- Within the loop, it first traverses the left subtree. For each left child, it pushes the current node onto the stack 
  and moves to the left child, repeating this until there are no more left children. 
- Once it reaches a null node, it pops a node from the stack, adds its value to the result list, and sets the current node
  to the right child of the popped node.
- This process allows it to visit nodes in left-node-right order, consistent with inorder traversal.
- The traversal continues until all nodes are processed, at which point it returns the list containing the nodes in inorder sequence.

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        Stack<TreeNode> s=new Stack<>();
        TreeNode curr=root;
        while(curr!=null || !s.isEmpty()){
            while(curr!=null){
                s.push(curr);
                curr=curr.left;
            }
            curr=s.pop();
            res.add(curr.val);
            curr=curr.right;

        }
        return res;
    }
}

Time and Space complexity - o(N)

6 . Postorder - Iterative

- This code performs an iterative postorder traversal of a binary tree using a stack.
- It initializes an empty list to store the traversal result, a stack for managing nodes, and starts from the root as the current node. 
- The traversal continues while there is a current node or the stack is not empty.
- The process first traverses the left subtree. For each left child, it pushes the current node onto the stack and
  moves to the left child. When there are no more left children, it checks the right child of the node at the top of the stack. 
- If the right child is null, it pops the node, adds its value to the result, and then continues to check the parent node on the stack.
- This popping and adding process repeats until it finds a node with an unvisited right child.
- If the right child exists, it sets the current node to that right child, shifting focus to the right subtree.
- This sequence ensures nodes are visited in left-right-root order, consistent with postorder traversal.
- The traversal continues until all nodes are processed, and finally, the result list is returned with nodes in postorder.

  
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        Stack<TreeNode> s=new Stack<>();
        TreeNode curr=root;
        while(curr!=null || !s.isEmpty()){
            if(curr!=null){
                s.push(curr);
                curr=curr.left;
            }
            else{
                TreeNode temp=s.peek().right;
                if(temp==null){
                    temp=s.pop();
                    res.add(temp.val);
                    while(!s.isEmpty() && s.peek().right==temp){
                        temp=s.pop();
                        res.add(temp.val);
                    }
                }
                else{
                    curr=temp;
                }
            }
        } 
        return res;
        
    }
}

Time and Space complexity - o(N)
  
