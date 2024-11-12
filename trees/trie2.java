Ninja has to implement a data structure ”TRIE” from scratch. Ninja has to complete some functions.

1) Trie(): Ninja has to initialize the object of this “TRIE” data structure.

2) insert(“WORD”): Ninja has to insert the string “WORD”  into this “TRIE” data structure.

3) countWordsEqualTo(“WORD”): Ninja has to return how many times this “WORD” is present in this “TRIE”.

4) countWordsStartingWith(“PREFIX”): Ninjas have to return how many words are there in this “TRIE” that have the string “PREFIX” as 
  a prefix.

5) erase(“WORD”): Ninja has to delete one occurrence of the string “WORD” from the “TRIE”.
Note:

1. If erase(“WORD”) function is called then it is guaranteed that the “WORD” is present in the “TRIE”.

2. If you are going to use variables with dynamic memory allocation then you need to release the memory associated with them at 
the end of your solution.
Can you help Ninja implement the "TRIE" data structure?

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 <= “T” <= 50
1 <= “N” <= 10000
 “WORD” = {a to z}
1 <= | “WORD” | <= 1000



First approach :

- This code defines a Trie data structure for managing words, tracking how many words end at a given node and how many share a common 
  prefix. It includes two classes: TrieNode and Trie.
- The TrieNode class represents each node in the Trie and maintains an array links of child nodes, where each index corresponds to a 
  letter in the alphabet.
- Two counters, cntPrefix and cntEndWith, are used to track the number of words that share this prefix and the number that end at this
  node, respectively.
- Key methods in TrieNode include put to insert a child node, containsKey to check if a character has a corresponding node, get to 
  retrieve the child node for a character, increaseEnd and decreaseEnd to update the word-ending count, and increasePrefix and
  reducePrefix to update the prefix count.
- The Trie class is the main structure and includes a root node for the Trie. The insert method adds words to the Trie, updating the 
  prefix count for each character along the path and the word-ending count for the final node. 
- The countWordsEqualTo method checks the exact number of times a word is stored by navigating through each character and returning
  the cntEndWith value at the end node if it exists.
- The countWordsStartingWith method returns the number of words that start with a given prefix by checking the cntPrefix at the 
  last character's node.
- The erase method removes a word by decreasing the prefix count at each node and the word-ending count at the last node,
  effectively "removing" the word from the Trie.
- This structure supports efficient insertion, search, and deletion, making it useful for tasks involving prefix-based queries
  and word counts.

import java.util.* ;
import java.io.*;

class TrieNode{
    TrieNode links[];
     int cntPrefix;
    int cntEndWith;
    public TrieNode(){
        links=new TrieNode[26];
        cntEndWith = 0;
        cntPrefix = 0;
    }
    public void put(char c, TrieNode node){
        links[c-'a']=node;
    }
    public boolean containsKey(char c){
        return links[c-'a']!=null;
    }
    public TrieNode get(char c){
        return links[c-'a'];
    }
    public void increaseEnd(){
        cntEndWith++;
    }
    public void decreaseEnd(){
        cntEndWith--;
    }
    public void increasePrefix(){
        cntPrefix++;
    }
    public void reducePrefix(){
        cntPrefix--;
    }
}
public class Trie {
    TrieNode root;
    public Trie() {
        root=new TrieNode();
    }
    

    public void insert(String word) {
        // Write your code here.
        TrieNode node=root;
        for(int i=0;i<word.length();i++){
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i),new TrieNode());
            }
            node=node.get(word.charAt(i));
            node.increasePrefix();
        }
        node.increaseEnd();
    }

    public int countWordsEqualTo(String word) {
        TrieNode node=root;
        for(int i=0;i<word.length();i++){
            if(!node.containsKey(word.charAt(i))){
                return 0;
            }
            node=node.get(word.charAt(i));
        }
        return node.cntEndWith;
    }

    public int countWordsStartingWith(String word) {
        // Write your code here.
        TrieNode node=root;
        for (int i = 0; i < word.length(); i++) {
            if (node.containsKey(word.charAt(i))) {
                node = node.get(word.charAt(i));
            } else {

                return 0;
            }
        }
        return node.cntPrefix;
    }

    public void erase(String word) {
        TrieNode node=root;
        for (int i = 0; i < word.length(); i++) {
            if (node.containsKey(word.charAt(i))) {
                node = node.get(word.charAt(i));
                node.reducePrefix();
            } else {
                return;
            }
        }
        node.decreaseEnd();
    }

}


Time complexity - o(N) -(insertion, counting words equal to a given word, counting words starting with a prefix, and erasing a word)
Space complexity - o(N)
