Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or
transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 
Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
 
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 
Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please
be creative and come up with different approaches yourself.


Example 1:


Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]

Approach 1 :

- This class provides methods to encode a binary tree into a single string and to decode that string back into the original binary tree.
- The serialize method takes the root node of a tree and uses a level-order traversal (breadth-first search) to convert the tree to a string representation.
- It appends each node’s value, separated by commas, and uses "#" to represent null nodes (i.e., where a child is missing).
- A queue manages the traversal, and each node is processed by adding its value to the StringBuilder.
- If a node is null, "#," is appended; otherwise, the node’s value and its children are added to the queue.
- The deserialize method reconstructs the tree from the encoded string. It first manually splits the string by commas to collect tokens into an ArrayList,
  handling each token as either a value or "#". The method then initializes the root node and processes nodes level-by-level, building left and right
  children as it encounters valid tokens. 
- Each child is added back to the queue for continued processing until all nodes are reconnected.
- This approach allows recreating the original tree structure efficiently from the serialized data.

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
         if(root==null){
            return "";
         }
         StringBuilder sb = new StringBuilder();
         Queue<TreeNode> q=new LinkedList<>();
         q.add(root);
         while(!q.isEmpty()){
            TreeNode node=q.poll();
            if (node == null) {
                sb.append("#,");
            } else {
                sb.append(node.val).append(",");
                q.add(node.left);
                q.add(node.right);
            }
            
         } 
         return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length()==0){
            return null;
        }
        ArrayList<String> a = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c == ',') {
                a.add(token.toString());
                token.setLength(0);
            } else {
                token.append(c);
            }
        }
        Queue<TreeNode> q=new LinkedList<>();
        int i=0;
        TreeNode root = new TreeNode(Integer.parseInt(a.get(i)));
        i++;
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node=q.poll();
            if(i<a.size()){
                if(!a.get(i).equals("#")){
                    TreeNode left=new TreeNode(Integer.parseInt(a.get(i)));
                    node.left=left;
                    q.add(left);
                }
                i++;
            }
            if(i<a.size()){
                if(!a.get(i).equals("#")){
                    TreeNode right=new TreeNode(Integer.parseInt(a.get(i)));
                    node.right=right;
                    q.add(right);
                }
                i++;
            }
            

        }
        return root;
    }
}

Time complexity - o(N) (in both serialization and deserialization functions)
Space complexity - o(N) (in both serialization and deserialization functions)
