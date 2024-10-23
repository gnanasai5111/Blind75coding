Given a string s, return the number of palindromic substrings in it.
A string is a palindrome when it reads the same backward as forward.
A substring is a contiguous sequence of characters within the string.

Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

First approach : 

- This solution counts all palindromic substrings in a given string. 
- It uses a helper method isPalindrome that checks whether a substring from start to end is a palindrome.
- The method compares characters from both ends of the substring, moving inward.
- If at any point the characters don't match, it returns false, otherwise it returns true if the entire substring is a palindrome.
- In the main method countSubstrings, the algorithm iterates through all possible substrings of the string by using two nested loops.
- The outer loop starts at each character in the string, and the inner loop generates all possible substrings from that starting point
  to the end of the string. For each substring, it calls the isPalindrome method to check if it is a palindrome. 
- If it is, the counter c is incremented.
- Finally, the method returns the total count of palindromic substrings found.

class Solution {
    public boolean isPalindrome(int start,int end,String s){
        while(start<end){
            if(s.charAt(start)==s.charAt(end)){
                start++;
                end--;
            }
            else{
                return false;
            }
        }
        return true;
    }
    public int countSubstrings(String s) {
        int c=0;
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                if(isPalindrome(i,j,s)){
                    c++;
                }
            }
        }
        return c;
        
    }
}

Time complexity - o(N*N*N)
Space complexity - o(1)

Approach 2 :

- This solution counts all palindromic substrings in a string using dynamic programming. 
- It defines a 2D array dp, where dp[i][j] is 1 if the substring s[i...j] is a palindrome, and 0 otherwise.
- Initially, single-character substrings are palindromes, so the diagonal elements dp[i][i] are set to 1, and the count is incremented.
- For two consecutive characters, it checks if they are the same, marking dp[i][i+1] as 1 if they form a palindrome and incrementing
  the count.
- For substrings of length greater than 2, the algorithm iterates through all possible starting and ending indices.
- If the outer characters match and the inner substring dp[i+1][j-1] is also a palindrome, it marks dp[i][j] as 1 and increments
  the count.
- Finally, the algorithm returns the total count of palindromic substrings.

class Solution {
    public boolean isPalindrome(int start,int end,String s){
        while(start<end){
            if(s.charAt(start)==s.charAt(end)){
                start++;
                end--;
            }
            else{
                return false;
            }
        }
        return true;
    }
    public int countSubstrings(String s) {
        int c=0;
        int n=s.length();
        int dp[][]=new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i]=1;
            c++;
            if(i<n-1){
                if(s.charAt(i) == s.charAt(i + 1)){
                    dp[i][i+1]=1;
                    c++;
                }
            }
        }
         // Check for lengths greater than 2
        for (int len = 3; len <= n; len++) { // length of substring
            for (int i = 0; i <= n - len; i++) { // start index
                int j = i + len - 1; // end index
                
                // Check if s[i..j] is a palindrome
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]==1) {
                    dp[i][j] = 1;
                    
                    c++;
                }
            }
        }
        return c;
        
    }
}

Time complexity - o(N*N)
Space complexity - o(N*N)

Approach 3 :

- This solution counts all palindromic substrings using the expand-around-center technique.
- It iterates through each character in the string and treats it as the center of potential palindromes.
- For each character, the algorithm checks two cases:
Odd-length palindromes: It expands from a single character center.
Even-length palindromes: It expands from two consecutive characters as the center.
- The helper method countPalindromesAroundCenter expands outward from the center and counts the palindromes while the characters 
  at the boundaries match. If they don't match, it stops expanding. The result is the total count of palindromic substrings.

Finally, the total count of palindromes is returned after considering all possible centers.

class Solution {
    public int countSubstrings(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); ++i) {
            // odd-length palindromes, single character center
            ans += countPalindromesAroundCenter(s, i, i);

            // even-length palindromes, consecutive characters center
            ans += countPalindromesAroundCenter(s, i, i + 1);
        }

        return ans;
    }

    private int countPalindromesAroundCenter(String ss, int lo, int hi) {
        int ans = 0;

        while (lo >= 0 && hi < ss.length()) {
            if (ss.charAt(lo) != ss.charAt(hi))
                break;      // the first and last characters don't match!

            // expand around the center
            lo--;
            hi++;

            ans++;
        }

        return ans;
    }
}

Time complexity - o(N*N)
Space complexity - o(1)
