Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one
or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".


Brute Force - Recursion 1 

- This solution solves the "Word Break" problem using a recursive approach.
- The main task is to determine if the input string can be segmented into a sequence of valid words from a provided dictionary.
- The function existInDictionary checks if a given substring (represented by a StringBuilder) exists in the word dictionary. 
- The recursive function validWord explores every possible segmentation of the string by trying all possible substrings starting 
  from the current index.
- If a valid word is found in the dictionary, the function recurses from the next index to check if the remaining portion of the 
  string can also be segmented.
- The recursion continues until the end of the string is reached, meaning the entire string has been segmented into valid words. 
- If the string can be segmented, the function returns true; otherwise, it returns false.
- Time complexity for this approach is exponential due to the recursive exploration of all substring combinations, 
  while space complexity is mainly influenced by the recursion stack depth.

class Solution {
    public boolean existInDictionary(StringBuilder sb,List<String> wordDict){
        for(int i=0;i<wordDict.size();i++){
            if(sb.toString().equals(wordDict.get(i))){
                return true;
            }
        }
        return false;
    }
    public boolean validWord(int index,String s,List<String> wordDict){
        if(index==s.length()){
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for(int i=index;i<s.length();i++){
            sb.append(s.charAt(i));
            if (existInDictionary(sb, wordDict)) {
                if (validWord(i + 1, s, wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
           return validWord(0, s, wordDict);
    }
}

Time complexity - exponential
space complexity - o(N)


Recursion - 2 :

- This recursive solution aims to determine whether the input string can be segmented into words found in a given dictionary. 
- The function validWord takes the current index of the string, checks whether any word from the dictionary matches the substring 
  starting from that index, and recursively verifies the remaining part of the string. 
- If the current substring matches a word in the dictionary, the function proceeds to the next segment of the string.
- If all parts of the string can be segmented, the function returns true, indicating a successful segmentation. 
- Otherwise, if no valid segmentation is possible, it returns false.
- The method wordBreak starts the recursion from the beginning of the string by calling validWord with the index set to zero.

class Solution {
    public boolean validWord(int index, String s, List<String> wordDict) {
        if (index == s.length()) {
            return true; // Successfully segmented the entire string
        }
        
        for (String word : wordDict) {
            int wordLength = word.length();
            if (index + wordLength <= s.length() && s.substring(index, index + wordLength).equals(word)) {
     
                if (validWord(index + wordLength, s, wordDict)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        return validWord(0, s, wordDict);
    }
}

Time complexity - exponential
space complexity - o(N)


Approach 3 :

- The function checks if a given string can be segmented into one or more valid words from a given dictionary. 
- It uses a recursive approach along with memoization to optimize repeated checks for the same starting index. 
- The function recursively tries to match the substring starting at the current index with words from the dictionary.
- If a match is found, it moves to the next part of the string and checks if the remaining substring can also be segmented. 
- If it reaches the end of the string, it returns true, meaning the entire string has been successfully segmented.
- Memoization is used to store results for each index, where -1 means the result for that index is not yet computed, 1 means the
  substring can be segmented, and 0 means it cannot. 
- This avoids recalculating results for previously visited indices, improving efficiency.

class Solution {
    public boolean validWord(int index, String s, List<String> wordDict,int memo[]) {
        if (index == s.length()) {
            return true; // Successfully segmented the entire string
        }
        if(memo[index]!=-1){
            return memo[index]==1;
        }
        
        for (String word : wordDict) {
            int wordLength = word.length();
            if (index + wordLength <= s.length() && s.substring(index, index + wordLength).equals(word)) {
     
                if (validWord(index + wordLength, s, wordDict,memo)) {
                     memo[index] = 1;
                     return true;
                }
            }
        }
        
        memo[index] =0;
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int memo[]=new int[s.length()];
          Arrays.fill(memo, -1);
        return validWord(0, s, wordDict,memo);
    }
}

Time complexity -O(N*M*K)
space complexity - o(N)
