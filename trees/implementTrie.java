A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of 
strings. 
There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false
otherwise.
 

Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Approach 1 :

- This code defines a Trie data structure, often used for storing strings efficiently for quick retrieval.
- It consists of two main classes, TrieNode and Trie.
- The TrieNode class represents each node in the Trie, containing an array of child nodes (links) for each letter in the English 
  alphabet and a flag (isEnd) that marks the end of a word. 
- Key methods in TrieNode include containsKey(char c), which checks if a character has an associated node; put(char c, TrieNode node), 
  which assigns a node to a specific character; get(char c), which retrieves the node for a character; and setEnd(), which marks 
  the current node as the end of a word.
- The Trie class is the main structure that uses TrieNode objects to build and manage the Trie. 
- It has a root node initialized as an empty TrieNode. The insert(String word) method iterates through each character in the word, 
  adding nodes as needed and marking the final node as the end of the word.
- The search(String word) method checks if a word is in the Trie by navigating through each character, 
  returning true only if the word exists and reaches an end node.
- Finally, the startsWith(String prefix) method verifies if any word in the Trie starts with a given prefix by ensuring each 
  character in the prefix has a corresponding node.
- This Trie implementation enables efficient word storage and prefix-based searches, commonly used in applications like
  autocomplete and spell-checkers.

class TrieNode{
    TrieNode[] links;
    boolean isEnd;
    public TrieNode(){
        links=new TrieNode[26];
    }
    public boolean containsKey(char c){
        return links[c-'a']!=null;
    }
    public void put(char c,TrieNode node){
        links[c-'a']=node;
    }
    public TrieNode get(char c){
        return links[c-'a'];
    }
    public void setEnd(){
        isEnd=true;
    }
    public boolean isEnd(){
        return isEnd;
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        root=new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node=root;
        for(int i=0;i<word.length();i++){
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i),new TrieNode());
            }
            node=node.get(word.charAt(i));
        }
        node.setEnd();
    }
    
    public boolean search(String word) {
        TrieNode node=root;
        for(int i=0;i<word.length();i++){
            if(!node.containsKey(word.charAt(i))){
                return false;
            }
            node=node.get(word.charAt(i));
        }
        if(node.isEnd()){
            return true;
        }
        return false;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node=root;
        for(int i=0;i<prefix.length();i++){
            if(!node.containsKey(prefix.charAt(i))){
                return false;
            }
            node=node.get(prefix.charAt(i));
        }
        return true;

    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

Time complexity - o(N) Insertion,search,prefix search
Space complexity - o(N)
