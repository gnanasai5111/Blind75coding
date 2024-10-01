Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing
the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.
  
Example 1:
Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

First approach (Recursion) :

- Generate all possible subsequences for both the strings by take and not take approach and all the strings to hashset.
- Traverse through a hashset of any string and check if the element exists in other hashset . if it exists , find the max length.

class Solution {
    public void generateAllSubsequences(int index, String text,HashSet<String> sublist,String sub){
        if(index>=text.length()){
            sublist.add(sub);
            return;
        }
        generateAllSubsequences(index+1,text,sublist,sub);
        generateAllSubsequences(index+1,text,sublist,sub+text.charAt(index));
    }
    public int longestCommonSubsequence(String text1, String text2) {
        HashSet<String> sublist1=new HashSet<>();
        HashSet<String> sublist2=new HashSet<>();
        generateAllSubsequences(0,text1,sublist1,"");
        generateAllSubsequences(0,text2,sublist2,"");
     
        int max=0;
        for (String element : sublist1) {
            if(sublist2.contains(element)){
                max=Math.max(max,element.length());
            }
        }
        return max;
        
    }
}

Time complexity - exponential
Space complexity - o(N) + o(M)

Second approach : Optimised Recursion 

- This code is for finding the longest common subsequence between two strings, text1 and text2. It does this using recursion.
- The main method, longestCommonSubsequence, calls the helper method generateAllSubsequences starting at the first character of
  both strings (indices 0 and 0).
- The generateAllSubsequences method works as follows: it checks if either index1 (for text1) or index2 (for text2) has reached the 
  end of the string. If so, it returns 0, as no further common subsequence is possible.
- If the characters at the current indices of both strings match, it adds 1 to the result and recursively calls itself, advancing
  both indices by 1, indicating that a match has been found and we move to the next characters in both strings.
- If the characters at the current indices do not match, it checks the two possibilities: moving forward in either text1 or text2.
- It returns the maximum result from these two possibilities, which ensures that the longest subsequence is found by exploring all
  options.

class Solution {
    public int generateAllSubsequences(int index1,int index2, String text1,String text2){
        if(index1>=text1.length() || index2>=text2.length()){
            return 0;
        }
        if(text1.charAt(index1)==text2.charAt(index2)){
            return 1+generateAllSubsequences(index1+1,index2+1,text1,text2);
        }
        return Math.max(generateAllSubsequences(index1+1,index2,text1,text2),generateAllSubsequences(index1,index2+1,text1,text2));
    }
    public int longestCommonSubsequence(String text1, String text2) {
        return generateAllSubsequences(0,0,text1,text2);   
    }
}

Time complexity -  exponential
Space complexity - O(N*M)

Third approach : Memoization 

- This code is an optimized version of the longest common subsequence problem, using memoization to avoid recalculating overlapping
  subproblems in the recursion.
- The longestCommonSubsequence method initializes a memo 2D array of size text1.length() x text2.length(), where each element stores
  the result of previously computed subproblems. This helps in reducing redundant calculations, significantly improving performance.
- The recursive helper method generateAllSubsequences takes additional input: the memo array. 
- It first checks if either index1 or index2 (representing positions in text1 and text2, respectively) has reached the end
  of the strings. If so, it returns 0 because no further subsequence can be found.
- Next, it checks if the result for the current index1 and index2 has already been computed and stored in the memo array. 
- If it's not 0, it means the value is already cached, so it returns the cached value to avoid re-computation.
- If the characters at the current indices in both strings match, it adds 1 to the result (indicating a match) and moves both indices 
  forward (recursively checking the next characters in both strings). The result is stored in the memo array for future use.
- If the characters don't match, the function explores two possibilities: either moving forward in text1 or text2.
- It then stores the maximum result between these two possibilities in the memo array and returns it.

class Solution {
    public int generateAllSubsequences(int index1,int index2, String text1,String text2,int memo[][]){
        if(index1>=text1.length() || index2>=text2.length()){
            return 0;
        }
        if(memo[index1][index2]!=0){
            return memo[index1][index2];
        }
        if(text1.charAt(index1)==text2.charAt(index2)){
            return memo[index1][index2]=1+generateAllSubsequences(index1+1,index2+1,text1,text2,memo);
        }
        return memo[index1][index2]=Math.max(generateAllSubsequences(index1+1,index2,text1,text2,memo),
                                             generateAllSubsequences(index1,index2+1,text1,text2,memo));
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int memo[][]=new int[text1.length()][text2.length()];
        return generateAllSubsequences(0,0,text1,text2,memo);   
    }
}

Time complexity -  O(N*M)
Space complexity -O(N*M) + O(N+M)

Fourth approach : Tabulation 

- This code finds the longest common subsequence (LCS) between two strings, text1 and text2, using dynamic programming.
- It creates a 2D array dp where each element dp[i][j] stores the length of the LCS between the first i characters of text1 and the 
  first j characters of text2.
- The code initializes the dp array with dimensions [text1.length() + 1][text2.length() + 1].
- The extra row and column are used to handle the base case when one of the strings is empty. 
- The loops start from i=1 and j=1 because the dp array starts indexing at 1 for characters of the strings, allowing it to compare
  characters at i-1 and j-1.
- If the characters at text1[i-1] and text2[j-1] are the same, it means they contribute to the LCS, so the value at dp[i][j] is set to
  1 + dp[i-1][j-1], representing the addition of a common character to the LCS length.
- If the characters do not match, the algorithm takes the maximum value between dp[i-1][j] (ignoring the current character in text1)
  and dp[i][j-1] (ignoring the current character in text2).
- Finally, the result is stored in dp[text1.length()][text2.length()], which represents the length of the longest common subsequence
  between the two full strings.

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int dp[][]=new int[text1.length()+1][text2.length()+1];
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];   
    }
}

Time complexity -  O(N*M)
Space complexity -O(N*M)

Fifth approach : space optimised

- This code efficiently finds the longest common subsequence (LCS) between two strings, text1 and text2.
- It first ensures that the shorter string is always text2 by swapping the strings if text2 is longer than text1.
- This optimization reduces space complexity since the algorithm only needs to keep track of results for the smaller string at each
  step.
- The algorithm uses a dynamic programming approach where two 1D arrays, prev and curr, store the results of subproblems.
- prev represents the LCS lengths for the previous row, while curr stores the current row's values.
- The array sizes are based on text2.length() + 1 because the dynamic programming process only needs to track the smaller string.
- For each character in text1, the code compares it with every character in text2. If characters match, curr[j] is updated by 
  adding 1 to the diagonal value from prev[j-1], representing a match in the sequence.
- If they don’t match, curr[j] takes the maximum value from either prev[j] (ignoring the character in text1) or curr[j-1]
  (ignoring the character in text2).
- After iterating through all characters, the prev array holds the LCS lengths, and the final result is
  returned as prev[text2.length()], representing the longest common subsequence between text1 and text2.
- This method reduces space complexity by only using two 1D arrays instead of a full 2D matrix.


class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text2.length() > text1.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }
        int prev[]=new int[text2.length()+1];
        for(int i=1;i<=text1.length();i++){
            int curr[]=new int[text2.length()+1];
            for(int j=1;j<=text2.length();j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    curr[j]=1+prev[j-1];
                }
                else{
                    curr[j]=Math.max(prev[j],curr[j-1]);
                }
            }
            prev=curr;
        }
        return prev[text2.length()];   
    }
}

Time complexity -  O(N*M)
Space complexity -O(N)
